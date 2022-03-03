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

package de.markusbordihn.tokencoins.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import de.markusbordihn.tokencoins.Constants;

public class ModItemTags {

  protected ModItemTags() {}

  // Token Coin Tags
  public static final TagKey<Item> tokenCoinBlank =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_blank"));
  public static final TagKey<Item> tokenCoinWithCreeper =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_creeper"));
  public static final TagKey<Item> tokenCoinWithDragon =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_dragon"));
  public static final TagKey<Item> tokenCoinWithLeKoopa =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_lekoopa"));
  public static final TagKey<Item> tokenCoinWithPoppy =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_poppy"));
  public static final TagKey<Item> tokenCoinWithSkeleton =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_skeleton"));
  public static final TagKey<Item> tokenCoinWithWheat =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_wheat"));
  public static final TagKey<Item> tokenCoinWithWither =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_wither"));
  public static final TagKey<Item> tokenCoinWithZombie =
      ItemTags.create(new ResourceLocation(Constants.MOD_ID, "token_coin_with_zombie"));
}
