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

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fmlserverevents.FMLServerAboutToStartEvent;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;

@EventBusSubscriber
public class CoinPressRecipe extends AbstractCookingRecipe {

	public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

	private static ConcurrentHashMap<String, CoinPressRecipe> recipeMap = new ConcurrentHashMap<>();
	private static boolean cachedRecipes = false;

	private Ingredient stampTop;
	private Ingredient stampBottom;

	public CoinPressRecipe(ResourceLocation resourceLocation, String group, Ingredient ingredient,
			Ingredient stampTop, Ingredient stampBottom, ItemStack result, float experience,
			int cookingTime) {
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
  public static void handleServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
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

	public static Recipe<?> getRecipeFor(Container container, Level level) {
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
		Collection<CoinPressRecipe> recipes = getAllRecipes(level);
		for (CoinPressRecipe recipe : recipes) {
			ItemStack ingredientItem = recipe.getIngredient().getItems()[0];
			ItemStack stampTopItem = recipe.getStampTop().getItems()[0];
			ItemStack stampBottomItem = recipe.getStampBottom().getItems()[0];
			String recipeId = getRecipeId(ingredientItem, stampBottomItem, stampBottomItem);
			log.info("{}", recipeId);
			log.info("{} {} {}", ingredientItem, stampTopItem, stampBottomItem);
			recipeMap.putIfAbsent(recipeId, recipe);
		}
		log.info("Cached {} Bo's Token Coins custom recipes.", recipeMap.size());
	}

	public static String getRecipeId(ItemStack ingredientItem, ItemStack stampTopItem,
			ItemStack stampBottomItem) {
		return ingredientItem.getItem().getRegistryName().toString() + "::"
				+ stampTopItem.getItem().getRegistryName().toString() + "::"
				+ stampBottomItem.getItem().getRegistryName().toString();
	};

	public static Collection<CoinPressRecipe> getAllRecipes(Level level) {
		return level.getRecipeManager().getAllRecipesFor(Constants.COIN_PRESS_RECIPE_TYPE);
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	public Ingredient getStampTop() {
		return this.stampTop;
	}

	public Ingredient getStampBottom() {
		return this.stampBottom;
	}
}
