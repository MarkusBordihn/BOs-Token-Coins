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

import com.google.gson.JsonObject;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class CoinPressRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
		implements RecipeSerializer<CoinPressRecipe> {

	@SuppressWarnings("deprecation")
	@Override
	public CoinPressRecipe fromJson(ResourceLocation location, JsonObject object) {
		if (!object.has("result"))
			throw new com.google.gson.JsonSyntaxException(
					"Missing result, expected to find a string or object");
		if (!object.has("ingredient"))
			throw new com.google.gson.JsonSyntaxException(
					"Missing ingredient, expected to find a string or object");
		if (!object.has("stampTop"))
			throw new com.google.gson.JsonSyntaxException(
					"Missing stampTop, expected to find a string or object");
		if (!object.has("stampBottom"))
			throw new com.google.gson.JsonSyntaxException(
					"Missing stampBottom, expected to find a string or object");
		if (!object.has("cookingtime"))
			throw new com.google.gson.JsonSyntaxException(
					"Missing cookingtime, expected to find a string or object");

		// Ingredient
		Ingredient ingredient = Ingredient.fromJson(GsonHelper.isArrayNode(object, "ingredient")
				? GsonHelper.getAsJsonArray(object, "ingredient")
				: GsonHelper.getAsJsonObject(object, "ingredient"));

		// Stamp Top
		Ingredient stampTop = Ingredient.fromJson(
				GsonHelper.isArrayNode(object, "stampTop") ? GsonHelper.getAsJsonArray(object, "stampTop")
						: GsonHelper.getAsJsonObject(object, "stampTop"));

		// Stamp Bottom
		Ingredient stampBottom = Ingredient.fromJson(GsonHelper.isArrayNode(object, "stampBottom")
				? GsonHelper.getAsJsonArray(object, "stampBottom")
				: GsonHelper.getAsJsonObject(object, "stampBottom"));

		// Result
		ItemStack itemStack;
		if (object.get("result").isJsonObject())
			itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(object, "result"));
		else {
			int count = GsonHelper.getAsInt(object, "count");
			String resultItem = GsonHelper.getAsString(object, "result");
			ResourceLocation resourceLocation = new ResourceLocation(resultItem);
			itemStack = new ItemStack(Registry.ITEM.getOptional(resourceLocation).orElseThrow(() -> {
				return new IllegalStateException("Item: " + resultItem + " does not exist");
			}), count);
		}

		String group = GsonHelper.getAsString(object, "group", "");
		float experience = GsonHelper.getAsFloat(object, "experience", 0.0F);
		int cookingTime = GsonHelper.getAsInt(object, "cookingtime", 100);
		return new CoinPressRecipe(location, group, ingredient, stampTop, stampBottom, itemStack,
				experience, cookingTime);
	}

	@Override
	public CoinPressRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		ResourceLocation id = buffer.readResourceLocation();
		String group = buffer.readUtf();
		ItemStack result = buffer.readItem();
		Ingredient ingredient = Ingredient.of(buffer.readItem());
		Ingredient stampTop = Ingredient.of(buffer.readItem());
		Ingredient stampBottom = Ingredient.of(buffer.readItem());
		float xp = buffer.readFloat();
		int time = buffer.readInt();
		return createRecipe(id, group, ingredient, stampTop, stampBottom, result, xp, time);
	}

	protected CoinPressRecipe createRecipe(ResourceLocation recipeId, String group,
			Ingredient ingredient, Ingredient stampTop, Ingredient stampBottom, ItemStack result,
			float experience, int cookingTime) {
		return new CoinPressRecipe(recipeId, group, ingredient, stampTop, stampBottom, result,
				experience, cookingTime);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, CoinPressRecipe recipe) {
		buffer.writeResourceLocation(recipe.getId());
		buffer.writeUtf(recipe.getGroup());
		buffer.writeItem(recipe.getIngredient().getItems()[0]);
		buffer.writeItem(recipe.getStampTop().getItems()[0]);
		buffer.writeItem(recipe.getStampBottom().getItems()[0]);
		buffer.writeItem(recipe.getResultItem());
		buffer.writeFloat(recipe.getExperience());
		buffer.writeInt(recipe.getCookingTime());
	}
}
