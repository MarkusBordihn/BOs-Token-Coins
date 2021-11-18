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

package de.markusbordihn.tokencoins.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.tokencoins.Annotations.TemplateEntryPoint;
import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.coinstack.*;
import de.markusbordihn.tokencoins.block.entity.CoinPressBlockEntity;
import de.markusbordihn.tokencoins.block.entity.PiggyBankBlockEntity;
import de.markusbordihn.tokencoins.block.piggybank.*;
import de.markusbordihn.tokencoins.item.ModItems;

public class ModBlocks {

  protected ModBlocks() {

  }

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

  public static final DeferredRegister<BlockEntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.MOD_ID);

  @TemplateEntryPoint("Register Blocks")

  // Creeper Coin Stack
  public static final RegistryObject<Block> COPPER_COIN_STACK_WITH_CREEPER =
      BLOCKS.register("copper_coin_stack_with_creeper",
          () -> new CopperCoinStackBlock(ModItems.COPPER_TOKEN_COIN_WITH_CREPPER));
  public static final RegistryObject<Block> GOLD_COIN_STACK_WITH_CREEPER =
      BLOCKS.register("gold_coin_stack_with_creeper",
          () -> new GoldCoinStackBlock(ModItems.GOLD_TOKEN_COIN_WITH_CREPPER));
  public static final RegistryObject<Block> IRON_COIN_STACK_WITH_CREEPER =
      BLOCKS.register("iron_coin_stack_with_creeper",
          () -> new IronCoinStackBlock(ModItems.IRON_TOKEN_COIN_WITH_CREPPER));
  public static final RegistryObject<Block> STEEL_COIN_STACK_WITH_CREEPER =
      BLOCKS.register("steel_coin_stack_with_creeper",
          () -> new SteelCoinStackBlock(ModItems.STEEL_TOKEN_COIN_WITH_CREPPER));
  public static final RegistryObject<Block> NETHERITE_COIN_STACK_WITH_CREEPER =
      BLOCKS.register("netherite_coin_stack_with_creeper",
          () -> new NetheriteCoinStackBlock(ModItems.NETHERITE_TOKEN_COIN_WITH_CREPPER));

  // Basic Coin Stack
  public static final RegistryObject<Block> COPPER_COIN_STACK = BLOCKS.register(
      CopperCoinStackBlock.NAME, () -> new CopperCoinStackBlock(ModItems.COPPER_TOKEN_COIN));
  public static final RegistryObject<Block> GOLD_COIN_STACK = BLOCKS
      .register(GoldCoinStackBlock.NAME, () -> new GoldCoinStackBlock(ModItems.GOLD_TOKEN_COIN));
  public static final RegistryObject<Block> IRON_COIN_STACK = BLOCKS
      .register(IronCoinStackBlock.NAME, () -> new IronCoinStackBlock(ModItems.IRON_TOKEN_COIN));
  public static final RegistryObject<Block> STEEL_COIN_STACK = BLOCKS
      .register(SteelCoinStackBlock.NAME, () -> new SteelCoinStackBlock(ModItems.STEEL_TOKEN_COIN));
  public static final RegistryObject<Block> NETHERITE_COIN_STACK =
      BLOCKS.register(NetheriteCoinStackBlock.NAME,
          () -> new NetheriteCoinStackBlock(ModItems.NETHERITE_TOKEN_COIN));

  // Coin Press
  public static final RegistryObject<Block> COIN_PRESS = BLOCKS.register(CoinPressBlock.NAME,
      () -> new CoinPressBlock(
          BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5f)
              .sound(SoundType.STONE).lightLevel(blockState -> {
                return Boolean.TRUE.equals(blockState.getValue(CoinPressBlock.POWERED)) ? 14 : 0;
              })));

  // Piggy Banks
  public static final RegistryObject<Block> PIGGY_BANK_GHAST =
      BLOCKS.register(PiggyBankGhastBlock.NAME,
          () -> new PiggyBankGhastBlock(BlockBehaviour.Properties.of(Material.DECORATION)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> PIGGY_BANK_NOTE_BLOCK =
      BLOCKS.register(PiggyBankNoteBlockBlock.NAME,
          () -> new PiggyBankNoteBlockBlock(BlockBehaviour.Properties.of(Material.DECORATION)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> PIGGY_BANK_PIG = BLOCKS.register(PiggyBankPigBlock.NAME,
      () -> new PiggyBankPigBlock(BlockBehaviour.Properties.of(Material.DECORATION)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> PIGGY_BANK_SAFE =
      BLOCKS.register(PiggyBankSafeBlock.NAME,
          () -> new PiggyBankSafeBlock(BlockBehaviour.Properties.of(Material.DECORATION)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> PIGGY_BANK_SKELETON =
      BLOCKS.register(PiggyBankSkeletonBlock.NAME,
          () -> new PiggyBankSkeletonBlock(BlockBehaviour.Properties.of(Material.DECORATION)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> PIGGY_BANK_TNT = BLOCKS.register(PiggyBankTNTBlock.NAME,
      () -> new PiggyBankTNTBlock(BlockBehaviour.Properties.of(Material.DECORATION)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> PIGGY_BANK = BLOCKS.register(PiggyBankBlock.NAME,
      () -> new PiggyBankBlock(BlockBehaviour.Properties.of(Material.DECORATION)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));

  @TemplateEntryPoint("Register Entity")

  // Coin Press Block Entity
  public static final RegistryObject<BlockEntityType<CoinPressBlockEntity>> COIN_PRESS_ENTITY =
      ENTITIES.register(CoinPressBlock.NAME, () -> BlockEntityType.Builder
          .of(CoinPressBlockEntity::new, COIN_PRESS.get()).build(null));

  // Piggy Bank Block Entity
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_GHAST_ENTITY =
      ENTITIES.register(PiggyBankGhastBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK_GHAST.get()).build(null));
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_NOTE_BLOCK_ENTITY =
      ENTITIES.register(PiggyBankNoteBlockBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK_NOTE_BLOCK.get()).build(null));
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_PIG_ENTITY =
      ENTITIES.register(PiggyBankPigBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK_PIG.get()).build(null));
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_SAFE_ENTITY =
      ENTITIES.register(PiggyBankSafeBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK_SAFE.get()).build(null));
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_SKELETON_ENTITY =
      ENTITIES.register(PiggyBankSkeletonBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK_SKELETON.get()).build(null));
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_TNT_ENTITY =
      ENTITIES.register(PiggyBankTNTBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK_TNT.get()).build(null));
  public static final RegistryObject<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK_ENTITY =
      ENTITIES.register(PiggyBankBlock.NAME, () -> BlockEntityType.Builder
          .of(PiggyBankBlockEntity::new, PIGGY_BANK.get()).build(null));

}
