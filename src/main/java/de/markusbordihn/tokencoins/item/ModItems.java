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

package de.markusbordihn.tokencoins.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.Annotations.TemplateEntryPoint;

public class ModItems {

  protected ModItems() {

  }

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  @TemplateEntryPoint("Register Items")

  // Creeper Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "copper_coin_stamp_with_creeper",
      () -> new CoinStampItem(CoinStampItemType.Material.COPPER, CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "gold_coin_stamp_with_creeper",
      () -> new CoinStampItem(CoinStampItemType.Material.GOLD, CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "iron_coin_stamp_with_creeper",
      () -> new CoinStampItem(CoinStampItemType.Material.IRON, CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "steel_coin_stamp_with_creeper",
      () -> new CoinStampItem(CoinStampItemType.Material.STEEL, CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("netherite_coin_stamp_with_creeper",
          () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE,
              CoinStampItemType.Motive.CREEPER));

  // Crepper Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("copper_token_coin_with_creeper", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("gold_token_coin_with_creeper", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("iron_token_coin_with_creeper", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("steel_token_coin_with_creeper", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("netherite_token_coin_with_creeper", CoinItem::new);

  // Basic Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP = ITEMS.register("copper_coin_stamp",
      () -> new CoinStampItem(CoinStampItemType.Material.COPPER));
  public static final RegistryObject<Item> GOLD_COIN_STAMP =
      ITEMS.register("gold_coin_stamp", () -> new CoinStampItem(CoinStampItemType.Material.GOLD));
  public static final RegistryObject<Item> IRON_COIN_STAMP =
      ITEMS.register("iron_coin_stamp", () -> new CoinStampItem(CoinStampItemType.Material.IRON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP =
      ITEMS.register("steel_coin_stamp", () -> new CoinStampItem(CoinStampItemType.Material.STEEL));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP = ITEMS.register(
      "netherite_coin_stamp", () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE));
  public static final RegistryObject<Item> COIN_STAMP =
      ITEMS.register("coin_stamp", () -> new CoinStampItem(CoinStampItemType.Material.COPPER));

  // Basic Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN =
      ITEMS.register("copper_token_coin", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN =
      ITEMS.register("gold_token_coin", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN =
      ITEMS.register("iron_token_coin", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN =
      ITEMS.register("steel_token_coin", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN =
      ITEMS.register("netherite_token_coin", CoinItem::new);
  public static final RegistryObject<Item> TOKEN_COIN = ITEMS.register("token_coin", CoinItem::new);

  @TemplateEntryPoint("Register Block Items")
  public static final RegistryObject<Item> COIN_PRESS =
      ITEMS.register("coin_press", () -> new BlockItem(ModBlocks.COIN_PRESS.get(),
          new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

}
