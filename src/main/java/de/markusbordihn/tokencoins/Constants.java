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

package de.markusbordihn.tokencoins;

import de.markusbordihn.tokencoins.recipe.CoinPressRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

public final class Constants {

  protected Constants() {}

  // General Mod definitions
  public static final String LOG_NAME = "Bo's Token Coins";
  public static final String MOD_COMMAND = "token_coins";
  public static final String MOD_ID = "token_coins";
  public static final String MOD_NAME = "Bo's Token Coins";
  public static final String TEXT_PREFIX = "text.token_coins.";

  // Functional Blocks
  public static final String COIN_PRESS = "coin_press";

  // Recipes
  public static final ResourceLocation COIN_PRESS_RECIPE_ID =
      new ResourceLocation(MOD_ID, COIN_PRESS);
  public static final RecipeType<CoinPressRecipe> COIN_PRESS_RECIPE_TYPE =
      RecipeType.register(MOD_ID + ":" + COIN_PRESS);
}
