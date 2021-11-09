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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
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

  public static final String COOKING_TIME = "cookingtime";
  public static final String COUNT = "count";
  public static final String EXPERIENCE = "experience";
  public static final String GROUP = "result";
  public static final String INGREDIENT = "ingredient";
  public static final String RESULT = "result";
  public static final String STAMP_BOTTOM = "stamp_bottom";
  public static final String STAMP_TOP = "stamp_top";

  private static NonNullList<Ingredient> itemsFromJson(JsonArray jsonArray) {
    NonNullList<Ingredient> nonnulllist = NonNullList.create();
    for (int i = 0; i < jsonArray.size(); ++i) {
      Ingredient ingredient = Ingredient.fromJson(jsonArray.get(i));
      if (!ingredient.isEmpty()) {
        nonnulllist.add(ingredient);
      }
    }
    return nonnulllist;
  }

  protected CoinPressRecipe createRecipe(ResourceLocation recipeId, String group,
      Ingredient ingredient, NonNullList<Ingredient> stampTop, NonNullList<Ingredient> stampBottom,
      ItemStack result, float experience, int cookingTime) {
    return new CoinPressRecipe(recipeId, group, ingredient, stampTop, stampBottom, result,
        experience, cookingTime);
  }

  @SuppressWarnings("deprecation")
  @Override
  public CoinPressRecipe fromJson(ResourceLocation location, JsonObject jsonObject) {
    if (!jsonObject.has(RESULT))
      throw new com.google.gson.JsonSyntaxException(
          "Missing result, expected to find a string or jsonObject");
    if (!jsonObject.has(INGREDIENT))
      throw new com.google.gson.JsonSyntaxException(
          "Missing ingredient, expected to find a string or jsonObject");
    if (!jsonObject.has(STAMP_TOP))
      throw new com.google.gson.JsonSyntaxException(
          "Missing stampTop, expected to find a string or object");
    if (!jsonObject.has(STAMP_BOTTOM))
      throw new com.google.gson.JsonSyntaxException(
          "Missing stampBottom, expected to find a string or object");
    if (!jsonObject.has(COOKING_TIME))
      throw new com.google.gson.JsonSyntaxException(
          "Missing cookingtime, expected to find a string or jsonObject");

    // Group
    String group = GsonHelper.getAsString(jsonObject, GROUP, "");

    // Ingredient
    Ingredient ingredient = Ingredient.fromJson(GsonHelper.isArrayNode(jsonObject, INGREDIENT)
        ? GsonHelper.getAsJsonArray(jsonObject, INGREDIENT)
        : GsonHelper.getAsJsonObject(jsonObject, INGREDIENT));

    // Stamps
    NonNullList<Ingredient> stampTop =
        itemsFromJson(GsonHelper.getAsJsonArray(jsonObject, STAMP_TOP));
    NonNullList<Ingredient> stampBottom =
        itemsFromJson(GsonHelper.getAsJsonArray(jsonObject, STAMP_BOTTOM));

    // Result
    ItemStack itemStack;
    if (jsonObject.get(RESULT).isJsonObject())
      itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, RESULT));
    else {
      int count = GsonHelper.getAsInt(jsonObject, COUNT);
      String resultItem = GsonHelper.getAsString(jsonObject, RESULT);
      ResourceLocation resourceLocation = new ResourceLocation(resultItem);
      itemStack = new ItemStack(Registry.ITEM.getOptional(resourceLocation).orElseThrow(() -> {
        return new IllegalStateException("Item: " + resultItem + " does not exist");
      }), count);
    }

    // Experience
    float experience = GsonHelper.getAsFloat(jsonObject, EXPERIENCE, 0.0F);

    // Cooking Time
    int cookingTime = GsonHelper.getAsInt(jsonObject, COOKING_TIME, 100);

    // Recipe
    return new CoinPressRecipe(location, group, ingredient, stampTop, stampBottom, itemStack,
        experience, cookingTime);
  }

  @Override
  public CoinPressRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
    // Handle General Data
    ResourceLocation id = buffer.readResourceLocation();
    String group = buffer.readUtf();

    // Handle Material ingredient
    Ingredient ingredient = Ingredient.of(buffer.readItem());

    // Handle Stamp Top ingredient
    int stampTopSize = buffer.readVarInt();
    NonNullList<Ingredient> stampTop = NonNullList.withSize(stampTopSize, Ingredient.EMPTY);
    for (int i = 0; i < stampTop.size(); ++i) {
      stampTop.set(i, Ingredient.of(buffer.readItem()));
    }

    // Handle Stamp Bottom ingredient
    int stampBottomSize = buffer.readVarInt();
    NonNullList<Ingredient> stampBottom = NonNullList.withSize(stampBottomSize, Ingredient.EMPTY);
    for (int i = 0; i < stampBottom.size(); ++i) {
      stampBottom.set(i, Ingredient.of(buffer.readItem()));
    }

    // Handle Results
    ItemStack result = buffer.readItem();
    float experience = buffer.readFloat();
    int cookingtime = buffer.readInt();
    return createRecipe(id, group, ingredient, stampTop, stampBottom, result, experience,
        cookingtime);
  }

  @Override
  public void toNetwork(FriendlyByteBuf buffer, CoinPressRecipe recipe) {
    // Handle General Data
    buffer.writeResourceLocation(recipe.getId());
    buffer.writeUtf(recipe.getGroup());

    // Handle Material ingredient
    buffer.writeItem(recipe.getIngredient().getItems()[0]);

    // Handle Stamp Top ingredient
    buffer.writeVarInt(recipe.getStampTop().size());
    for (Ingredient ingredient : recipe.getStampTop()) {
      buffer.writeItem(ingredient.getItems()[0]);
    }

    // Handle Stamp Bottom ingredient
    buffer.writeVarInt(recipe.getStampBottom().size());
    for (Ingredient ingredient : recipe.getStampBottom()) {
      buffer.writeItem(ingredient.getItems()[0]);
    }

    // Handle Results
    buffer.writeItem(recipe.getResultItem());
    buffer.writeFloat(recipe.getExperience());
    buffer.writeInt(recipe.getCookingTime());
  }
}
