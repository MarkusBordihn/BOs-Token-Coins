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

package de.markusbordihn.tokencoins.block.piggybank;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.block.PiggyBankBlock;
import de.markusbordihn.tokencoins.block.entity.PiggyBankBlockEntity;

public class PiggyBankPigBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_pig";

  public PiggyBankPigBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    Direction facing = blockState.getValue(PiggyBankBlock.FACING);
    if (facing == Direction.EAST || facing == Direction.WEST) {
      return PiggyBankBlock.SHAPE_10_12_12_90DEG_AABB;
    }
    return PiggyBankBlock.SHAPE_10_12_12_AABB;
  }

  @Override
  public InteractionResult useEffect(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    ItemStack handItemStack = player.getItemInHand(hand);
    if (handItemStack.is(Items.CARROT)) {
      playSound(player, SoundEvents.HORSE_EAT);
      addParticle(level, ParticleTypes.HEART, blockPos);
      return InteractionResult.SUCCESS;
    } else if (handItemStack.is(Items.CARROT_ON_A_STICK)) {
      playSound(player, SoundEvents.PIGLIN_ANGRY);
      addParticle(level, ParticleTypes.ANGRY_VILLAGER, blockPos);
      return InteractionResult.SUCCESS;
    } else if (handItemStack.is(Items.FLINT_AND_STEEL)) {
      playSound(player, SoundEvents.FLINTANDSTEEL_USE);
      addParticleOnTop(level, ParticleTypes.FLAME, blockPos, 2);
      playSound(player, SoundEvents.PIGLIN_ANGRY);
      addParticle(level, ParticleTypes.ANGRY_VILLAGER, blockPos);
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.PASS;
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PiggyBankBlockEntity(ModBlocks.PIGGY_BANK_PIG_ENTITY.get(), blockPos, blockState);
  }

}
