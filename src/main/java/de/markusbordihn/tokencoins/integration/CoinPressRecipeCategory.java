/**
 * Copyright 2021 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusbordihn.tokencoins.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;
import de.markusbordihn.tokencoins.recipe.CoinPressRecipe;

public class CoinPressRecipeCategory implements IRecipeCategory<CoinPressRecipe> {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  // Static values for better performance on draw
  private static final int HEIGH = 85;
  private static final int WIDTH = 150;
  private static final int PADDING_LEFT = 6;
  private static final int PADDING_TOP = 5;
  private static final int FLAME_LEFT = CoinPressMenu.FUEL_SLOT_LEFT - PADDING_LEFT + 2;
  private static final int FLAME_TOP = CoinPressMenu.FUEL_SLOT_TOP - PADDING_TOP + 2;
  private static final int PROGRESS_BAR_LEFT = 97 - PADDING_LEFT;
  private static final int PROGRESS_BAR_TOP = 42 - PADDING_TOP;

  private IDrawable background;
  private IDrawable overlay;
  private IDrawable icon;
  private LoadingCache<Integer, IDrawableAnimated> cachedArrows;
  protected IDrawableStatic staticFlame;
  protected IDrawableAnimated animatedFlame;

  public CoinPressRecipeCategory(IGuiHelper guiHelper) {
    this.background = guiHelper.createBlankDrawable(WIDTH, HEIGH);
    this.overlay = guiHelper.createDrawable(
        new ResourceLocation(Constants.MOD_ID, "textures/container/coin_press_gui.png"),
        PADDING_LEFT, PADDING_TOP, WIDTH, HEIGH);
    this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.COIN_PRESS.get()));
    this.staticFlame = guiHelper.createDrawable(
        new ResourceLocation("jei", "textures/gui/gui_vanilla.png"), 82, 114, 14, 14);
    this.animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300,
        IDrawableAnimated.StartDirection.TOP, true);
    this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
      @Override
      public IDrawableAnimated load(Integer cookTime) {
        return guiHelper
            .drawableBuilder(new ResourceLocation("jei", "textures/gui/gui_vanilla.png"), 82, 128,
                24, 17)
            .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
      }
    });
  }

  @Nonnull
  @Override
  public ResourceLocation getUid() {
    return Constants.COIN_PRESS_RECIPE_ID;
  }

  @Nonnull
  @Override
  public Class<? extends CoinPressRecipe> getRecipeClass() {
    return CoinPressRecipe.class;
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return new TranslatableComponent(Constants.TEXT_PREFIX + "coin_press");
  }

  @Nonnull
  @Override
  public IDrawable getBackground() {
    return background;
  }

  @Nonnull
  @Override
  public IDrawable getIcon() {
    return icon;
  }

  @Override
  public void setIngredients(CoinPressRecipe recipe, IIngredients iIngredients) {
    List<List<ItemStack>> ingredientList = new ArrayList<>();

    // Material
    ingredientList.add(Arrays.asList(recipe.getIngredient().getItems()));

    // Stamps Bottom
    List<ItemStack> stampBottomList = new ArrayList<>();
    for (Ingredient ingredient : recipe.getStampTop()) {
      stampBottomList.add(ingredient.getItems()[0]);
    }
    ingredientList.add(stampBottomList);

    // Stamps Top
    List<ItemStack> stampTopList = new ArrayList<>();
    for (Ingredient ingredient : recipe.getStampTop()) {
      stampTopList.add(ingredient.getItems()[0]);
    }
    ingredientList.add(stampTopList);

    iIngredients.setInputLists(VanillaTypes.ITEM, ingredientList);
    iIngredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
  }

  protected void drawExperience(CoinPressRecipe recipe, PoseStack poseStack, int y) {
    float experience = recipe.getExperience();
    if (experience > 0) {
      TranslatableComponent experienceString =
          new TranslatableComponent("gui.jei.category.smelting.experience", experience);
      Minecraft minecraft = Minecraft.getInstance();
      Font fontRenderer = minecraft.font;
      int stringWidth = fontRenderer.width(experienceString);
      fontRenderer.draw(poseStack, experienceString, (float) background.getWidth() - stringWidth, y,
          0xFF808080);
    }
  }

  protected void drawCookTime(CoinPressRecipe recipe, PoseStack poseStack, int y) {
    int cookTime = recipe.getCookingTime();
    if (cookTime > 0) {
      int cookTimeSeconds = cookTime / 20;
      TranslatableComponent timeString =
          new TranslatableComponent("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
      Minecraft minecraft = Minecraft.getInstance();
      Font fontRenderer = minecraft.font;
      int stringWidth = fontRenderer.width(timeString);
      fontRenderer.draw(poseStack, timeString, (float) background.getWidth() - stringWidth, y,
          0xFF808080);
    }
  }

  protected IDrawableAnimated getArrow(CoinPressRecipe recipe) {
    int cookTime = recipe.getCookingTime();
    if (cookTime <= 0) {
      cookTime = 200;
    }
    return this.cachedArrows.getUnchecked(cookTime);
  }

  @Override
  public void draw(CoinPressRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
    overlay.draw(poseStack);

    // Fuel Flames
    animatedFlame.draw(poseStack, FLAME_LEFT, FLAME_TOP);

    // Progress Bar
    IDrawableAnimated arrow = getArrow(recipe);
    arrow.draw(poseStack, PROGRESS_BAR_LEFT, PROGRESS_BAR_TOP);

    // Experience and Cooking Time
    drawExperience(recipe, poseStack, 0);
    drawCookTime(recipe, poseStack, 60);
  }

  @Override
  public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull CoinPressRecipe recipe,
      @Nonnull IIngredients ingredients) {
    // Material Slot
    recipeLayout.getItemStacks().init(CoinPressMenu.MATERIAL_SLOT, true,
        CoinPressMenu.MATERIAL_SLOT_LEFT - PADDING_LEFT - 1,
        CoinPressMenu.MATERIAL_SLOT_TOP - PADDING_TOP - 1);
    recipeLayout.getItemStacks().set(CoinPressMenu.MATERIAL_SLOT,
        ingredients.getInputs(VanillaTypes.ITEM).get(CoinPressMenu.MATERIAL_SLOT));

    // Stamp Bottom Slot
    recipeLayout.getItemStacks().init(CoinPressMenu.STAMP_BOTTOM_SLOT, true,
        CoinPressMenu.STAMP_BOTTOM_SLOT_LEFT - PADDING_LEFT - 1,
        CoinPressMenu.STAMP_BOTTOM_SLOT_TOP - PADDING_TOP - 1);
    recipeLayout.getItemStacks().set(CoinPressMenu.STAMP_BOTTOM_SLOT,
        ingredients.getInputs(VanillaTypes.ITEM).get(CoinPressMenu.STAMP_BOTTOM_SLOT));

    // Stamp Top Slot
    recipeLayout.getItemStacks().init(CoinPressMenu.STAMP_TOP_SLOT, true,
        CoinPressMenu.STAMP_TOP_SLOT_LEFT - PADDING_LEFT - 1,
        CoinPressMenu.STAMP_TOP_SLOT_TOP - PADDING_TOP - 1);
    recipeLayout.getItemStacks().set(CoinPressMenu.STAMP_TOP_SLOT,
        ingredients.getInputs(VanillaTypes.ITEM).get(CoinPressMenu.STAMP_TOP_SLOT));

    /**
     * // Fuel Top Slot recipeLayout.getItemStacks().init(CoinPressMenu.FUEL_SLOT, true,
     * CoinPressMenu.FUEL_SLOT_LEFT - PADDING_LEFT, CoinPressMenu.FUEL_SLOT_TOP - PADDING_TOP);
     * recipeLayout.getItemStacks().set(CoinPressMenu.FUEL_SLOT,
     * ingredients.getInputs(VanillaTypes.ITEM).get(CoinPressMenu.FUEL_SLOT));
     */
    // Result Slot
    recipeLayout.getItemStacks().init(CoinPressMenu.RESULT_SLOT, true,
        CoinPressMenu.RESULT_SLOT_LEFT - PADDING_LEFT - 1,
        CoinPressMenu.RESULT_SLOT_TOP - PADDING_TOP - 1);
    recipeLayout.getItemStacks().set(CoinPressMenu.RESULT_SLOT,
        ingredients.getOutputs(VanillaTypes.ITEM).get(0));

  }

}
