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

  // Wheat Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "copper_token_coin_with_wheat", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "gold_token_coin_with_wheat", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "iron_token_coin_with_wheat", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "steel_token_coin_with_wheat", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_WHEAT = ITEMS.register(
      "netherite_token_coin_with_wheat", CoinItem::new);

  // Wheat Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "copper_coin_stamp_with_wheat",
      () -> new CoinStampItem(CoinStampItemType.Material.COPPER, CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "gold_coin_stamp_with_wheat",
      () -> new CoinStampItem(CoinStampItemType.Material.GOLD, CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "iron_coin_stamp_with_wheat",
      () -> new CoinStampItem(CoinStampItemType.Material.IRON, CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "steel_coin_stamp_with_wheat",
      () -> new CoinStampItem(CoinStampItemType.Material.STEEL, CoinStampItemType.Motive.WHEAT));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_WHEAT = ITEMS.register(
      "netherite_coin_stamp_with_wheat",
      () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE, CoinStampItemType.Motive.WHEAT));

  // Poppy Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("copper_token_coin_with_poppy", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("gold_token_coin_with_poppy", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("iron_token_coin_with_poppy", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("steel_token_coin_with_poppy", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_POPPY =
      ITEMS.register("netherite_token_coin_with_poppy", CoinItem::new);

  // Poppy Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "copper_coin_stamp_with_poppy",
      () -> new CoinStampItem(CoinStampItemType.Material.COPPER, CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_POPPY =
      ITEMS.register("gold_coin_stamp_with_poppy",
          () -> new CoinStampItem(CoinStampItemType.Material.GOLD, CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_POPPY =
      ITEMS.register("iron_coin_stamp_with_poppy",
          () -> new CoinStampItem(CoinStampItemType.Material.IRON, CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_POPPY = ITEMS.register(
      "steel_coin_stamp_with_poppy",
      () -> new CoinStampItem(CoinStampItemType.Material.STEEL, CoinStampItemType.Motive.POPPY));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_POPPY =
      ITEMS.register("netherite_coin_stamp_with_poppy",
          () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE,
              CoinStampItemType.Motive.POPPY));

  // Skeleton Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("copper_token_coin_with_skeleton", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("gold_token_coin_with_skeleton", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("iron_token_coin_with_skeleton", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("steel_token_coin_with_skeleton", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_SKELETON =
      ITEMS.register("netherite_token_coin_with_skeleton", CoinItem::new);

  // Skeleton Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_SKELETON = ITEMS.register(
      "copper_coin_stamp_with_skeleton", () -> new CoinStampItem(CoinStampItemType.Material.COPPER,
          CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_SKELETON = ITEMS.register(
      "gold_coin_stamp_with_skeleton",
      () -> new CoinStampItem(CoinStampItemType.Material.GOLD, CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_SKELETON = ITEMS.register(
      "iron_coin_stamp_with_skeleton",
      () -> new CoinStampItem(CoinStampItemType.Material.IRON, CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_SKELETON = ITEMS.register(
      "steel_coin_stamp_with_skeleton",
      () -> new CoinStampItem(CoinStampItemType.Material.STEEL, CoinStampItemType.Motive.SKELETON));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_SKELETON =
      ITEMS.register("netherite_coin_stamp_with_skeleton",
          () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE,
              CoinStampItemType.Motive.SKELETON));

  // Dragon Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("copper_token_coin_with_dragon", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("gold_token_coin_with_dragon", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("iron_token_coin_with_dragon", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("steel_token_coin_with_dragon", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN_WITH_DRAGON =
      ITEMS.register("netherite_token_coin_with_dragon", CoinItem::new);

  // Dragon Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "copper_coin_stamp_with_dragon",
      () -> new CoinStampItem(CoinStampItemType.Material.COPPER, CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "gold_coin_stamp_with_dragon",
      () -> new CoinStampItem(CoinStampItemType.Material.GOLD, CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "iron_coin_stamp_with_dragon",
      () -> new CoinStampItem(CoinStampItemType.Material.IRON, CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_DRAGON = ITEMS.register(
      "steel_coin_stamp_with_dragon",
      () -> new CoinStampItem(CoinStampItemType.Material.STEEL, CoinStampItemType.Motive.DRAGON));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_DRAGON =
      ITEMS.register("netherite_coin_stamp_with_dragon",
          () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE,
              CoinStampItemType.Motive.DRAGON));

  // Zombie Coins
  public static final RegistryObject<Item> COPPER_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("copper_token_coin_with_zombie", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("gold_token_coin_with_zombie", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("iron_token_coin_with_zombie", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN_WITH_ZOMBIE =
      ITEMS.register("steel_token_coin_with_zombie", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_STAMP_WITH_ZOMBIE =
      ITEMS.register("netherite_token_coin_with_zombie", CoinItem::new);

  // Zombie Coin Stamps
  public static final RegistryObject<Item> COPPER_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "copper_coin_stamp_with_zombie",
      () -> new CoinStampItem(CoinStampItemType.Material.COPPER, CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> GOLD_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "gold_coin_stamp_with_zombie",
      () -> new CoinStampItem(CoinStampItemType.Material.GOLD, CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> IRON_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "iron_coin_stamp_with_zombie",
      () -> new CoinStampItem(CoinStampItemType.Material.IRON, CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> STEEL_COIN_STAMP_WITH_ZOMBIE = ITEMS.register(
      "steel_coin_stamp_with_zombie",
      () -> new CoinStampItem(CoinStampItemType.Material.STEEL, CoinStampItemType.Motive.ZOMBIE));
  public static final RegistryObject<Item> NETHERITE_COIN_STAMP_WITH_ZOMBIE =
      ITEMS.register("netherite_coin_stamp_with_zombie",
          () -> new CoinStampItem(CoinStampItemType.Material.NETHERITE,
              CoinStampItemType.Motive.ZOMBIE));

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
      ITEMS.register("copper_token_coin", CoinItem::new);
  public static final RegistryObject<Item> GOLD_TOKEN_COIN =
      ITEMS.register("gold_token_coin", CoinItem::new);
  public static final RegistryObject<Item> IRON_TOKEN_COIN =
      ITEMS.register("iron_token_coin", CoinItem::new);
  public static final RegistryObject<Item> STEEL_TOKEN_COIN =
      ITEMS.register("steel_token_coin", CoinItem::new);
  public static final RegistryObject<Item> NETHERITE_TOKEN_COIN =
      ITEMS.register("netherite_token_coin", CoinItem::new);

  @TemplateEntryPoint("Register Block Items")
  public static final RegistryObject<Item> COIN_PRESS =
      ITEMS.register("coin_press", () -> new BlockItem(ModBlocks.COIN_PRESS.get(),
          new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

}
