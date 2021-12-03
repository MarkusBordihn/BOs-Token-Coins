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

import net.minecraftforge.registries.RegistryObject;
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

  protected ModItems() {}

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  @TemplateEntryPoint("Register Items")

  // LeKoopa Coins
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_LEKOOPA = ITEMS.register(
      "cookie_token_coin_with_lekoopa", () -> new CookieCoinItem(TokenCoinType.Motive.LEKOOPA));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_LEKOOPA = ITEMS.register(
      "copper_token_coin_with_lekoopa", () -> new CopperCoinItem(TokenCoinType.Motive.LEKOOPA,
          ModBlocks.COPPER_COIN_STACK_WITH_LEKOOPA));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_LEKOOPA = ITEMS.register(
      "gold_token_coin_with_lekoopa",
      () -> new GoldCoinItem(TokenCoinType.Motive.LEKOOPA, ModBlocks.GOLD_COIN_STACK_WITH_LEKOOPA));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_LEKOOPA = ITEMS.register(
      "iron_token_coin_with_lekoopa",
      () -> new IronCoinItem(TokenCoinType.Motive.LEKOOPA, ModBlocks.IRON_COIN_STACK_WITH_LEKOOPA));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_LEKOOPA = ITEMS.register(
      "steel_token_coin_with_lekoopa", () -> new SteelCoinItem(TokenCoinType.Motive.LEKOOPA,
          ModBlocks.STEEL_COIN_STACK_WITH_LEKOOPA));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_LEKOOPA = ITEMS.register(
      "netherite_token_coin_with_lekoopa", () -> new NetheriteCoinItem(TokenCoinType.Motive.LEKOOPA,
          ModBlocks.NETHERITE_COIN_STACK_WITH_LEKOOPA));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_WITHER = ITEMS.register(
      "cookie_token_coin_with_wither", () -> new CookieCoinItem(TokenCoinType.Motive.WITHER));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_WITHER = ITEMS.register(
      "copper_token_coin_with_wither", () -> new CopperCoinItem(TokenCoinType.Motive.WITHER,
          ModBlocks.COPPER_COIN_STACK_WITH_WITHER));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_WITHER = ITEMS.register(
      "gold_token_coin_with_wither",
      () -> new GoldCoinItem(TokenCoinType.Motive.WITHER, ModBlocks.GOLD_COIN_STACK_WITH_WITHER));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_WITHER = ITEMS.register(
      "iron_token_coin_with_wither",
      () -> new IronCoinItem(TokenCoinType.Motive.WITHER, ModBlocks.IRON_COIN_STACK_WITH_WITHER));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_WITHER = ITEMS.register(
      "steel_token_coin_with_wither",
      () -> new SteelCoinItem(TokenCoinType.Motive.WITHER, ModBlocks.STEEL_COIN_STACK_WITH_WITHER));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_WITHER = ITEMS.register(
      "netherite_token_coin_with_wither", () -> new NetheriteCoinItem(TokenCoinType.Motive.WITHER,
          ModBlocks.NETHERITE_COIN_STACK_WITH_WITHER));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "cookie_token_coin_with_wheat", () -> new CookieCoinItem(TokenCoinType.Motive.WHEAT));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "copper_token_coin_with_wheat",
      () -> new CopperCoinItem(TokenCoinType.Motive.WHEAT, ModBlocks.COPPER_COIN_STACK_WITH_WHEAT));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("gold_token_coin_with_wheat",
          () -> new GoldCoinItem(TokenCoinType.Motive.WHEAT, ModBlocks.GOLD_COIN_STACK_WITH_WHEAT));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_WHEAT =
      ITEMS.register("iron_token_coin_with_wheat",
          () -> new IronCoinItem(TokenCoinType.Motive.WHEAT, ModBlocks.IRON_COIN_STACK_WITH_WHEAT));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "steel_token_coin_with_wheat",
      () -> new SteelCoinItem(TokenCoinType.Motive.WHEAT, ModBlocks.STEEL_COIN_STACK_WITH_WHEAT));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "netherite_token_coin_with_wheat", () -> new NetheriteCoinItem(TokenCoinType.Motive.WHEAT,
          ModBlocks.NETHERITE_COIN_STACK_WITH_WHEAT));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_POPPY = ITEMS.register(
      "cookie_token_coin_with_poppy", () -> new CookieCoinItem(TokenCoinType.Motive.POPPY));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_POPPY = ITEMS.register(
      "copper_token_coin_with_poppy",
      () -> new CopperCoinItem(TokenCoinType.Motive.POPPY, ModBlocks.COPPER_COIN_STACK_WITH_POPPY));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("gold_token_coin_with_poppy",
          () -> new GoldCoinItem(TokenCoinType.Motive.POPPY, ModBlocks.GOLD_COIN_STACK_WITH_POPPY));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("iron_token_coin_with_poppy",
          () -> new IronCoinItem(TokenCoinType.Motive.POPPY, ModBlocks.IRON_COIN_STACK_WITH_POPPY));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_POPPY = ITEMS.register(
      "steel_token_coin_with_poppy",
      () -> new SteelCoinItem(TokenCoinType.Motive.POPPY, ModBlocks.STEEL_COIN_STACK_WITH_POPPY));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_POPPY = ITEMS.register(
      "netherite_token_coin_with_poppy", () -> new NetheriteCoinItem(TokenCoinType.Motive.POPPY,
          ModBlocks.NETHERITE_COIN_STACK_WITH_POPPY));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_SKELETON = ITEMS.register(
      "cookie_token_coin_with_skeleton", () -> new CookieCoinItem(TokenCoinType.Motive.SKELETON));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_SKELETON = ITEMS.register(
      "copper_token_coin_with_skeleton", () -> new CopperCoinItem(TokenCoinType.Motive.SKELETON,
          ModBlocks.COPPER_COIN_STACK_WITH_SKELETON));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_SKELETON = ITEMS.register(
      "gold_token_coin_with_skeleton", () -> new GoldCoinItem(TokenCoinType.Motive.SKELETON,
          ModBlocks.GOLD_COIN_STACK_WITH_SKELETON));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_SKELETON = ITEMS.register(
      "iron_token_coin_with_skeleton", () -> new IronCoinItem(TokenCoinType.Motive.SKELETON,
          ModBlocks.IRON_COIN_STACK_WITH_SKELETON));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_SKELETON = ITEMS.register(
      "steel_token_coin_with_skeleton", () -> new SteelCoinItem(TokenCoinType.Motive.SKELETON,
          ModBlocks.STEEL_COIN_STACK_WITH_SKELETON));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("netherite_token_coin_with_skeleton",
          () -> new NetheriteCoinItem(TokenCoinType.Motive.SKELETON,
              ModBlocks.NETHERITE_COIN_STACK_WITH_SKELETON));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_DRAGON = ITEMS.register(
      "cookie_token_coin_with_dragon", () -> new CookieCoinItem(TokenCoinType.Motive.DRAGON));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_DRAGON = ITEMS.register(
      "copper_token_coin_with_dragon", () -> new CopperCoinItem(TokenCoinType.Motive.DRAGON,
          ModBlocks.COPPER_COIN_STACK_WITH_DRAGON));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_DRAGON = ITEMS.register(
      "gold_token_coin_with_dragon",
      () -> new GoldCoinItem(TokenCoinType.Motive.DRAGON, ModBlocks.GOLD_COIN_STACK_WITH_DRAGON));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_DRAGON = ITEMS.register(
      "iron_token_coin_with_dragon",
      () -> new IronCoinItem(TokenCoinType.Motive.DRAGON, ModBlocks.IRON_COIN_STACK_WITH_DRAGON));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_DRAGON = ITEMS.register(
      "steel_token_coin_with_dragon",
      () -> new SteelCoinItem(TokenCoinType.Motive.DRAGON, ModBlocks.STEEL_COIN_STACK_WITH_DRAGON));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_DRAGON = ITEMS.register(
      "netherite_token_coin_with_dragon", () -> new NetheriteCoinItem(TokenCoinType.Motive.DRAGON,
          ModBlocks.NETHERITE_COIN_STACK_WITH_DRAGON));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_ZOMBIE = ITEMS.register(
      "cookie_token_coin_with_zombie", () -> new CookieCoinItem(TokenCoinType.Motive.ZOMBIE));
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_ZOMBIE = ITEMS.register(
      "copper_token_coin_with_zombie", () -> new CopperCoinItem(TokenCoinType.Motive.ZOMBIE,
          ModBlocks.COPPER_COIN_STACK_WITH_ZOMBIE));
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_ZOMBIE = ITEMS.register(
      "gold_token_coin_with_zombie",
      () -> new GoldCoinItem(TokenCoinType.Motive.ZOMBIE, ModBlocks.GOLD_COIN_STACK_WITH_ZOMBIE));
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_ZOMBIE = ITEMS.register(
      "iron_token_coin_with_zombie",
      () -> new IronCoinItem(TokenCoinType.Motive.ZOMBIE, ModBlocks.IRON_COIN_STACK_WITH_ZOMBIE));
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_ZOMBIE = ITEMS.register(
      "steel_token_coin_with_zombie",
      () -> new SteelCoinItem(TokenCoinType.Motive.ZOMBIE, ModBlocks.STEEL_COIN_STACK_WITH_ZOMBIE));
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_ZOMBIE = ITEMS.register(
      "netherite_token_coin_with_zombie", () -> new NetheriteCoinItem(TokenCoinType.Motive.ZOMBIE,
          ModBlocks.NETHERITE_COIN_STACK_WITH_ZOMBIE));

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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN_WITH_CREPPER = ITEMS.register(
      "cookie_token_coin_with_creeper", () -> new CookieCoinItem(TokenCoinType.Motive.CREEPER));
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
  public static final RegistryObject<Item> COOKIE_TOKEN_COIN =
      ITEMS.register("cookie_token_coin", () -> new CookieCoinItem(TokenCoinType.Motive.NONE));
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
  public static final RegistryObject<Item> PIGGY_BANK_LUMPI =
      ITEMS.register(PiggyBankLumpiBlock.NAME, () -> new BlockItem(ModBlocks.PIGGY_BANK_LUMPI.get(),
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
