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

package de.markusbordihn.tokencoins.recipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;

@EventBusSubscriber
public class CoinPressRecipe extends AbstractCookingRecipe {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  private static ConcurrentHashMap<String, CoinPressRecipe> recipeMap = new ConcurrentHashMap<>();
  private static boolean cachedRecipes = false;

  private NonNullList<Ingredient> stampTop;
  private NonNullList<Ingredient> stampBottom;

  public CoinPressRecipe(ResourceLocation resourceLocation, String group, Ingredient ingredient,
			NonNullList<Ingredient> stampTop, NonNullList<Ingredient> stampBottom, ItemStack result,
			float experience, int cookingTime) {
		this(resourceLocation, group, ingredient, result, experience, cookingTime);
		this.stampTop = stampTop;
		this.stampBottom = stampBottom;
	}

  public CoinPressRecipe(ResourceLocation resourceLocation, String group, Ingredient ingredient,
      ItemStack result, float experience, int cookingTime) {
    super(Constants.COIN_PRESS_RECIPE_TYPE, resourceLocation, group, ingredient, result, experience,
        cookingTime);
  }

  @SubscribeEvent
  public static void handleServerAboutToStartEvent(ServerAboutToStartEvent event) {
    // Make sure to clear recipe cache on server start to avoid side effects.
    recipeMap = new ConcurrentHashMap<>();
    cachedRecipes = false;
  }

  @Override
  public ItemStack getToastSymbol() {
    return new ItemStack(ModBlocks.COIN_PRESS.get());
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return ModRecipes.COIN_PRESS_SERIALIZER.get();
  }

  public static CoinPressRecipe getRecipeFor(Container container, Level level) {
    ItemStack containerMaterial = container.getItem(CoinPressMenu.MATERIAL_SLOT);
    ItemStack containerStampTop = container.getItem(CoinPressMenu.STAMP_TOP_SLOT);
    ItemStack containerStampBottom = container.getItem(CoinPressMenu.STAMP_BOTTOM_SLOT);

    // Makes sure that all slots are filled before looking for any recipe
    if (containerMaterial.isEmpty() || containerStampTop.isEmpty()
        || containerStampBottom.isEmpty()) {
      return null;
    }

    // Cache recipes for easier and faster lookup
    cacheRecipes(level);

    // Use unique ID for lookups
    String recipeId = getRecipeId(containerMaterial, containerStampTop, containerStampBottom);
    return recipeMap.getOrDefault(recipeId, null);
  }

  public static void cacheRecipes(Level level) {
    if (cachedRecipes) {
      return;
    } else {
      cachedRecipes = true;
    }
    log.info("🚀 Pre-Caching Bo's Token Coins custom recipes for faster and easier access ...");
    long startTime = System.currentTimeMillis();
    Collection<CoinPressRecipe> recipes = getAllRecipes(level);
    for (CoinPressRecipe recipe : recipes) {
      ItemStack ingredientItem = recipe.getIngredient().getItems()[0];
      NonNullList<Ingredient> stampTopItems = recipe.getStampTop();
      NonNullList<Ingredient> stampBottomItems = recipe.getStampBottom();

      // Cache possible combinations for coin stamps for top and bottom.
      for (Ingredient stampTopEntry : stampTopItems) {
        ItemStack stampTopItem = stampTopEntry.getItems()[0];
        for (Ingredient stampBottomEntry : stampBottomItems) {
          ItemStack stampBottomItem = stampBottomEntry.getItems()[0];
          String recipeId = getRecipeId(ingredientItem, stampTopItem, stampBottomItem);
          if (recipeMap.containsKey(recipeId)) {
            log.warn(
                "Skipping duplicated Bo's Token Coins custom recipes {}, found possible duplicated recipe!",
                recipeId);
          } else {
            recipeMap.put(recipeId, recipe);
          }
        }
      }
    }
    if (recipeMap.size() > 0) {
      log.info(
          "Processed {} coin press recipes and cached {} Bo's Token Coins custom recipes in {} ms.",
          recipes.size(), recipeMap.size(), System.currentTimeMillis() - startTime);
    }
  }

  public static String getRecipeId(ItemStack ingredientItem, ItemStack stampTopItem,
      ItemStack stampBottomItem) {
    return ingredientItem.getItem().getRegistryName().toString() + "::"
        + stampTopItem.getItem().getRegistryName().toString() + "::"
        + stampBottomItem.getItem().getRegistryName().toString();
  }

  public static Collection<CoinPressRecipe> getAllRecipes(Level level) {
    return level.getRecipeManager().getAllRecipesFor(Constants.COIN_PRESS_RECIPE_TYPE);
  }

  public Ingredient getIngredient() {
    return this.ingredient;
  }

  public NonNullList<Ingredient> getStampTop() {
    return this.stampTop;
  }

  public NonNullList<Ingredient> getStampBottom() {
    return this.stampBottom;
  }
}
