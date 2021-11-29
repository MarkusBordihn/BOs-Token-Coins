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
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

import de.markusbordihn.tokencoins.Constants;

public class ModItemTags {

  protected ModItemTags() {}

  // Token Coin Tags
  public static final Tag.Named<Item> tokenCoinBlank =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_blank").toString());
  public static final Tag.Named<Item> tokenCoinWithCreeper =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_creeper").toString());
  public static final Tag.Named<Item> tokenCoinWithDragon =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_dragon").toString());
  public static final Tag.Named<Item> tokenCoinWithLeKoopa =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_lekoopa").toString());
  public static final Tag.Named<Item> tokenCoinWithPoppy =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_poppy").toString());
  public static final Tag.Named<Item> tokenCoinWithSkeleton =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_skeleton").toString());
  public static final Tag.Named<Item> tokenCoinWithWheat =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_wheat").toString());
  public static final Tag.Named<Item> tokenCoinWithWither =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_wither").toString());
  public static final Tag.Named<Item> tokenCoinWithZombie =
      ItemTags.bind(new ResourceLocation(Constants.MOD_ID, "token_coin_with_zombie").toString());
}
