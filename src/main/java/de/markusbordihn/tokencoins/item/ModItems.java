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
import de.markusbordihn.tokencoins.item.coin.*;
import de.markusbordihn.tokencoins.item.coinstamp.*;
import de.markusbordihn.tokencoins.tabs.TokenCoinsTab;
import de.markusbordihn.tokencoins.Annotations.TemplateEntryPoint;

public class ModItems {

  protected ModItems() {

  }

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  @TemplateEntryPoint("Register Items")

  // Wither Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_WITHER =
      ITEMS.register("copper_token_coin_with_wither", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_WITHER =
      ITEMS.register("gold_token_coin_with_wither", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_WITHER =
      ITEMS.register("iron_token_coin_with_wither", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_WITHER =
      ITEMS.register("steel_token_coin_with_wither", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_WITHER =
      ITEMS.register("netherite_token_coin_with_wither", NetheriteCoinItem::new);

  // Wither Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_WITHER =
      ITEMS.register("copper_coin_stamp_with_wither",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.WITHER));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_WITHER = ITEMS.register(
      "gold_coin_stamp_with_wither", () -> new GoldCoinStampItem(CoinStampItemType.Motive.WITHER));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_WITHER = ITEMS.register(
      "iron_coin_stamp_with_wither", () -> new IronCoinStampItem(CoinStampItemType.Motive.WITHER));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_WITHER =
      ITEMS.register("steel_coin_stamp_with_wither",
          () -> new SteelCoinStampItem(CoinStampItemType.Motive.WITHER));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_WITHER =
      ITEMS.register("netherite_coin_stamp_with_wither",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.WITHER));

  // Wheat Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("copper_token_coin_with_wheat", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("gold_token_coin_with_wheat", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("iron_token_coin_with_wheat", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("steel_token_coin_with_wheat", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("netherite_token_coin_with_wheat", NetheriteCoinItem::new);

  // Wheat Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_WHEAT =
      ITEMS.register("copper_coin_stamp_with_wheat",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "gold_coin_stamp_with_wheat", () -> new GoldCoinStampItem(CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "iron_coin_stamp_with_wheat", () -> new IronCoinStampItem(CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "steel_coin_stamp_with_wheat", () -> new SteelCoinStampItem(CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_WHEAT =
      ITEMS.register("netherite_coin_stamp_with_wheat",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.WHEAT));

  // Poppy Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("copper_token_coin_with_poppy", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("gold_token_coin_with_poppy", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("iron_token_coin_with_poppy", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("steel_token_coin_with_poppy", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("netherite_token_coin_with_poppy", NetheriteCoinItem::new);

  // Poppy Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_POPPY =
      ITEMS.register("copper_coin_stamp_with_poppy",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "gold_coin_stamp_with_poppy", () -> new GoldCoinStampItem(CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "iron_coin_stamp_with_poppy", () -> new IronCoinStampItem(CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "steel_coin_stamp_with_poppy", () -> new SteelCoinStampItem(CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_POPPY =
      ITEMS.register("netherite_coin_stamp_with_poppy",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.POPPY));

  // Skeleton Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("copper_token_coin_with_skeleton", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("gold_token_coin_with_skeleton", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("iron_token_coin_with_skeleton", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("steel_token_coin_with_skeleton", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("netherite_token_coin_with_skeleton", NetheriteCoinItem::new);

  // Skeleton Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("copper_coin_stamp_with_skeleton",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("gold_coin_stamp_with_skeleton",
          () -> new GoldCoinStampItem(CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("iron_coin_stamp_with_skeleton",
          () -> new IronCoinStampItem(CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("steel_coin_stamp_with_skeleton",
          () -> new SteelCoinStampItem(CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("netherite_coin_stamp_with_skeleton",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.SKELETON));

  // Dragon Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("copper_token_coin_with_dragon", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("gold_token_coin_with_dragon", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("iron_token_coin_with_dragon", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("steel_token_coin_with_dragon", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("netherite_token_coin_with_dragon", NetheriteCoinItem::new);

  // Dragon Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_DRAGON =
      ITEMS.register("copper_coin_stamp_with_dragon",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "gold_coin_stamp_with_dragon", () -> new GoldCoinStampItem(CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "iron_coin_stamp_with_dragon", () -> new IronCoinStampItem(CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_DRAGON =
      ITEMS.register("steel_coin_stamp_with_dragon",
          () -> new SteelCoinStampItem(CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_DRAGON =
      ITEMS.register("netherite_coin_stamp_with_dragon",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.DRAGON));

  // Zombie Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("copper_token_coin_with_zombie", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("gold_token_coin_with_zombie", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("iron_token_coin_with_zombie", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("steel_token_coin_with_zombie", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_STAMP_WITH_ZOMBIE =
      ITEMS.register("netherite_token_coin_with_zombie", NetheriteCoinItem::new);

  // Zombie Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_ZOMBIE =
      ITEMS.register("copper_coin_stamp_with_zombie",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "gold_coin_stamp_with_zombie", () -> new GoldCoinStampItem(CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "iron_coin_stamp_with_zombie", () -> new IronCoinStampItem(CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_ZOMBIE =
      ITEMS.register("steel_coin_stamp_with_zombie",
          () -> new SteelCoinStampItem(CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_ZOMBIE =
      ITEMS.register("netherite_coin_stamp_with_zombie",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.ZOMBIE));

  // Crepper Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("copper_token_coin_with_creeper", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("gold_token_coin_with_creeper", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("iron_token_coin_with_creeper", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("steel_token_coin_with_creeper", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_CREPPER =
      ITEMS.register("netherite_token_coin_with_creeper", NetheriteCoinItem::new);

  // Creeper Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("copper_coin_stamp_with_creeper",
          () -> new CopperCoinStampItem(CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("gold_coin_stamp_with_creeper",
          () -> new GoldCoinStampItem(CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("iron_coin_stamp_with_creeper",
          () -> new IronCoinStampItem(CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("steel_coin_stamp_with_creeper",
          () -> new SteelCoinStampItem(CoinStampItemType.Motive.CREEPER));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("netherite_coin_stamp_with_creeper",
          () -> new NetheriteCoinStampItem(CoinStampItemType.Motive.CREEPER));

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

  // Basic Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN =
      ITEMS.register("copper_token_coin", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN =
      ITEMS.register("gold_token_coin", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN =
      ITEMS.register("iron_token_coin", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN =
      ITEMS.register("steel_token_coin", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN =
      ITEMS.register("netherite_token_coin", NetheriteCoinItem::new);

  @TemplateEntryPoint("Register Block Items")

  // Piggy Banks
  public static final RegistryObject<Item> PIGGY_BANK_GHAST =
      ITEMS.register("piggy_bank_ghast", () -> new BlockItem(ModBlocks.PIGGY_BANK_GHAST.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));
  public static final RegistryObject<Item> PIGGY_BANK =
      ITEMS.register("piggy_bank", () -> new BlockItem(ModBlocks.PIGGY_BANK.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));

  // Coin Press
  public static final RegistryObject<Item> COIN_PRESS =
      ITEMS.register("coin_press", () -> new BlockItem(ModBlocks.COIN_PRESS.get(),
          new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

}
