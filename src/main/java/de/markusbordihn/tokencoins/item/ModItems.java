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
import de.markusbordihn.tokencoins.block.piggybank.*;
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

  // LeKoopa Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_LEKOOPA =
      ITEMS.register("copper_token_coin_with_lekoopa", CopperCoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_LEKOOPA =
      ITEMS.register("gold_token_coin_with_lekoopa", GoldCoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_LEKOOPA =
      ITEMS.register("iron_token_coin_with_lekoopa", IronCoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_LEKOOPA =
      ITEMS.register("steel_token_coin_with_lekoopa", SteelCoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_LEKOOPA =
      ITEMS.register("netherite_token_coin_with_lekoopa", NetheriteCoinItem::new);

  // LeKoopa Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_LEKOOPA =
      ITEMS.register("copper_coin_stamp_with_lekoopa",
          () -> new CopperCoinStampItem(TokenCoinType.Motive.LEKOOPA));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_LEKOOPA = ITEMS.register(
      "gold_coin_stamp_with_lekoopa", () -> new GoldCoinStampItem(TokenCoinType.Motive.LEKOOPA));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_LEKOOPA = ITEMS.register(
      "iron_coin_stamp_with_lekoopa", () -> new IronCoinStampItem(TokenCoinType.Motive.LEKOOPA));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_LEKOOPA = ITEMS.register(
      "steel_coin_stamp_with_lekoopa", () -> new SteelCoinStampItem(TokenCoinType.Motive.LEKOOPA));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_LEKOOPA =
      ITEMS.register("netherite_coin_stamp_with_lekoopa",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.LEKOOPA));

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
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_WITHER = ITEMS.register(
      "copper_coin_stamp_with_wither", () -> new CopperCoinStampItem(TokenCoinType.Motive.WITHER));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_WITHER = ITEMS.register(
      "gold_coin_stamp_with_wither", () -> new GoldCoinStampItem(TokenCoinType.Motive.WITHER));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_WITHER = ITEMS.register(
      "iron_coin_stamp_with_wither", () -> new IronCoinStampItem(TokenCoinType.Motive.WITHER));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_WITHER = ITEMS.register(
      "steel_coin_stamp_with_wither", () -> new SteelCoinStampItem(TokenCoinType.Motive.WITHER));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_WITHER =
      ITEMS.register("netherite_coin_stamp_with_wither",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.WITHER));

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
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "copper_coin_stamp_with_wheat", () -> new CopperCoinStampItem(TokenCoinType.Motive.WHEAT));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "gold_coin_stamp_with_wheat", () -> new GoldCoinStampItem(TokenCoinType.Motive.WHEAT));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "iron_coin_stamp_with_wheat", () -> new IronCoinStampItem(TokenCoinType.Motive.WHEAT));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "steel_coin_stamp_with_wheat", () -> new SteelCoinStampItem(TokenCoinType.Motive.WHEAT));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_WHEAT =
      ITEMS.register("netherite_coin_stamp_with_wheat",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.WHEAT));

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
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "copper_coin_stamp_with_poppy", () -> new CopperCoinStampItem(TokenCoinType.Motive.POPPY));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "gold_coin_stamp_with_poppy", () -> new GoldCoinStampItem(TokenCoinType.Motive.POPPY));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "iron_coin_stamp_with_poppy", () -> new IronCoinStampItem(TokenCoinType.Motive.POPPY));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "steel_coin_stamp_with_poppy", () -> new SteelCoinStampItem(TokenCoinType.Motive.POPPY));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_POPPY =
      ITEMS.register("netherite_coin_stamp_with_poppy",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.POPPY));

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
          () -> new CopperCoinStampItem(TokenCoinType.Motive.SKELETON));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_SKELETON = ITEMS.register(
      "gold_coin_stamp_with_skeleton", () -> new GoldCoinStampItem(TokenCoinType.Motive.SKELETON));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_SKELETON = ITEMS.register(
      "iron_coin_stamp_with_skeleton", () -> new IronCoinStampItem(TokenCoinType.Motive.SKELETON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("steel_coin_stamp_with_skeleton",
          () -> new SteelCoinStampItem(TokenCoinType.Motive.SKELETON));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("netherite_coin_stamp_with_skeleton",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.SKELETON));

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
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "copper_coin_stamp_with_dragon", () -> new CopperCoinStampItem(TokenCoinType.Motive.DRAGON));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "gold_coin_stamp_with_dragon", () -> new GoldCoinStampItem(TokenCoinType.Motive.DRAGON));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "iron_coin_stamp_with_dragon", () -> new IronCoinStampItem(TokenCoinType.Motive.DRAGON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "steel_coin_stamp_with_dragon", () -> new SteelCoinStampItem(TokenCoinType.Motive.DRAGON));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_DRAGON =
      ITEMS.register("netherite_coin_stamp_with_dragon",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.DRAGON));

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
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "copper_coin_stamp_with_zombie", () -> new CopperCoinStampItem(TokenCoinType.Motive.ZOMBIE));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "gold_coin_stamp_with_zombie", () -> new GoldCoinStampItem(TokenCoinType.Motive.ZOMBIE));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "iron_coin_stamp_with_zombie", () -> new IronCoinStampItem(TokenCoinType.Motive.ZOMBIE));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "steel_coin_stamp_with_zombie", () -> new SteelCoinStampItem(TokenCoinType.Motive.ZOMBIE));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_ZOMBIE =
      ITEMS.register("netherite_coin_stamp_with_zombie",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.ZOMBIE));

  // Crepper Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_CREPPER = ITEMS.register(
      "copper_token_coin_with_creeper", () -> new CopperCoinItem(TokenCoinType.Motive.CREEPER,
          ModBlocks.COPPER_COIN_STACK_WITH_CREEPER));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_CREPPER = ITEMS.register(
      "gold_token_coin_with_creeper",
      () -> new GoldCoinItem(TokenCoinType.Motive.CREEPER, ModBlocks.GOLD_COIN_STACK_WITH_CREEPER));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_CREPPER = ITEMS.register(
      "iron_token_coin_with_creeper",
      () -> new IronCoinItem(TokenCoinType.Motive.CREEPER, ModBlocks.IRON_COIN_STACK_WITH_CREEPER));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_CREPPER = ITEMS.register(
      "steel_token_coin_with_creeper", () -> new SteelCoinItem(TokenCoinType.Motive.CREEPER,
          ModBlocks.STEEL_COIN_STACK_WITH_CREEPER));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_CREPPER = ITEMS.register(
      "netherite_token_coin_with_creeper", () -> new NetheriteCoinItem(TokenCoinType.Motive.CREEPER,
          ModBlocks.NETHERITE_COIN_STACK_WITH_CREEPER));

  // Creeper Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("copper_coin_stamp_with_creeper",
          () -> new CopperCoinStampItem(TokenCoinType.Motive.CREEPER));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "gold_coin_stamp_with_creeper", () -> new GoldCoinStampItem(TokenCoinType.Motive.CREEPER));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "iron_coin_stamp_with_creeper", () -> new IronCoinStampItem(TokenCoinType.Motive.CREEPER));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_CREPPER = ITEMS.register(
      "steel_coin_stamp_with_creeper", () -> new SteelCoinStampItem(TokenCoinType.Motive.CREEPER));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_CREPPER =
      ITEMS.register("netherite_coin_stamp_with_creeper",
          () -> new NetheriteCoinStampItem(TokenCoinType.Motive.CREEPER));

  // Basic Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP =
      ITEMS.register("copper_coin_stamp", () -> new CopperCoinStampItem(TokenCoinType.Motive.NONE));
  public static final RegistryObject<Item> GOLD_COIN_STAMP =
      ITEMS.register("gold_coin_stamp", () -> new GoldCoinStampItem(TokenCoinType.Motive.NONE));
  public static final RegistryObject<Item> IRON_COIN_STAMP =
      ITEMS.register("iron_coin_stamp", () -> new IronCoinStampItem(TokenCoinType.Motive.NONE));
  public static final RegistryObject<Item> STEEL_COIN_STAMP =
      ITEMS.register("steel_coin_stamp", () -> new SteelCoinStampItem(TokenCoinType.Motive.NONE));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP = ITEMS.register(
      "netherite_coin_stamp", () -> new NetheriteCoinStampItem(TokenCoinType.Motive.NONE));

  // Basic Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN = ITEMS.register("copper_token_coin",
      () -> new CopperCoinItem(TokenCoinType.Motive.NONE, ModBlocks.COPPER_COIN_STACK));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN = ITEMS.register("gold_token_coin",
      () -> new GoldCoinItem(TokenCoinType.Motive.NONE, ModBlocks.GOLD_COIN_STACK));
  public static final RegistryObject<Item> IRON_TOKEN_COIN = ITEMS.register("iron_token_coin",
      () -> new IronCoinItem(TokenCoinType.Motive.NONE, ModBlocks.IRON_COIN_STACK));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN = ITEMS.register("steel_token_coin",
      () -> new SteelCoinItem(TokenCoinType.Motive.NONE, ModBlocks.STEEL_COIN_STACK));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN =
      ITEMS.register("netherite_token_coin",
          () -> new NetheriteCoinItem(TokenCoinType.Motive.NONE, ModBlocks.NETHERITE_COIN_STACK));

  @TemplateEntryPoint("Register Block Items")

  // Piggy Banks
  public static final RegistryObject<Item> PIGGY_BANK_GHAST =
      ITEMS.register(PiggyBankGhastBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_GHAST.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));
  public static final RegistryObject<Item> PIGGY_BANK_NOTE_BLOCK = ITEMS.register(
      PiggyBankNoteBlockBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_NOTE_BLOCK.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));
  public static final RegistryObject<Item> PIGGY_BANK_PIG =
      ITEMS.register(PiggyBankPigBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_PIG.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));
  public static final RegistryObject<Item> PIGGY_BANK_SAFE =
      ITEMS.register(PiggyBankSafeBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_SAFE.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));
  public static final RegistryObject<Item> PIGGY_BANK_SKELETON = ITEMS.register(
      PiggyBankSkeletonBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_SKELETON.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));
  public static final RegistryObject<Item> PIGGY_BANK_TNT =
      ITEMS.register(PiggyBankTNTBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_TNT.get(),
          new Item.Properties().tab(TokenCoinsTab.PIGGY_BANK)));

  // Coin Press
  public static final RegistryObject<Item> COIN_PRESS =
      ITEMS.register("coin_press", () -> new BlockItem(ModBlocks.COIN_PRESS.get(),
          new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

}
