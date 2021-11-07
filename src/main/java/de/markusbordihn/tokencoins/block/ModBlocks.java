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
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.Annotations.TemplateEntryPoint;
import de.markusbordihn.tokencoins.block.entity.CoinPressBlockEntity;

public class ModBlocks {

  protected ModBlocks() {

  }

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

  public static final DeferredRegister<BlockEntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.MOD_ID);

  @TemplateEntryPoint("Register Blocks")
  public static final RegistryObject<Block> COIN_PRESS = BLOCKS.register(CoinPressBlock.NAME,
      () -> new CoinPressBlock(
          BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops()
              .strength(3.5f).sound(SoundType.STONE).lightLevel(blockState -> {
                return Boolean.TRUE.equals(blockState.getValue(CoinPressBlock.POWERED)) ? 14 : 0;
              })));

  @TemplateEntryPoint("Register Entity")
  public static final RegistryObject<BlockEntityType<CoinPressBlockEntity>> COIN_PRESS_ENTITY =
      ENTITIES.register("coin_press", () -> BlockEntityType.Builder
          .of(CoinPressBlockEntity::new, COIN_PRESS.get()).build(null));

}