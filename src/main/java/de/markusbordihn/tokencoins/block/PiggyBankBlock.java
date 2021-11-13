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

import java.util.Random;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.entity.PiggyBankBlockEntity;
import de.markusbordihn.tokencoins.item.coin.CoinItem;

public class PiggyBankBlock extends Block implements EntityBlock, TokenCoinCompatible {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "piggy_bank";

  // Predefined Voxel Shapes
  protected static final VoxelShape SHAPE_AABB = Block.box(4, 0, 4, 12, 8, 12);
  protected static final VoxelShape SHAPE_10_10_10_AABB = Block.box(3, 0, 3, 13, 10, 13);
  protected static final VoxelShape SHAPE_10_12_12_AABB = Block.box(3, 0, 1, 13, 10, 15);

  // Animation specific ticker
  public int animateTicker = 0;

  // Block States
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public static final IntegerProperty STATE = IntegerProperty.create("state", 0, 2);

  public PiggyBankBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(
        this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(STATE, 0));
  }

  @Override
  public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState,
      @Nullable LivingEntity livingEntity, ItemStack itemStack) {
    BlockEntity blockEntity = level.getBlockEntity(blockPos);
    if (blockEntity instanceof PiggyBankBlockEntity blockEntityInstance) {
      blockEntityInstance.updateLevel(level);
      blockEntityInstance.setOwner(livingEntity);
    }
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
    blockState.add(FACING, STATE);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return SHAPE_AABB;
  }

  @Override
  public RenderShape getRenderShape(BlockState blockState) {
    return RenderShape.MODEL;
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING,
        context.getHorizontalDirection().getOpposite());
  }

  @Override
  public BlockState rotate(BlockState blockState, Rotation rotation) {
    return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
  }

  @Override
  public InteractionResult consumeTokenCoin(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    Item item = itemStack.getItem();
    if (item instanceof CoinItem coinItem && coinItem.getValue() >= 1) {
      // Try to store the token coins
      int storedTokenCoins = 0;
      if (blockEntity instanceof PiggyBankBlockEntity blockEntityInstance) {
        blockEntityInstance.updateLevel(level);
        // If the user is crouching try to consume the whole stack
        if (context.getPlayer().isCrouching()) {
          storedTokenCoins = blockEntityInstance.storeTokenCoin(itemStack);
        } else {
          storedTokenCoins = blockEntityInstance.storeTokenCoin(itemStack.getItem());
        }
        // If we are able to store any coins shrink their hand and play a sound.
        if (storedTokenCoins > 0) {
          // Update block state for animation
          level.setBlock(blockPos, blockState.setValue(STATE, 1), 3);

          // Inform Block Entity about the change
          level.playSound(null, blockPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F,
              1.0F);
          itemStack.shrink(storedTokenCoins);
          blockEntityInstance.increaseStoredValue(coinItem.getValue() * storedTokenCoins);
          blockEntityInstance.setChanged();
          return InteractionResult.sidedSuccess(level.isClientSide);
        }
        // Send the player an message if the piggy bank could be full.
        if (coinItem.getValue() >= 1 && storedTokenCoins == 0) {
          Player player = context.getPlayer();
          if (player.isAlive()) {
            player.sendMessage(new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_full",
                blockEntityInstance.getDefaultName().getString()).withStyle(ChatFormatting.YELLOW),
                player.getUUID());
          }
        }
      }
    }
    return InteractionResult.FAIL;
  }

  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    BlockEntity blockEntity = level.getBlockEntity(blockPos);
    ItemStack handItemStack = player.getItemInHand(hand);
    // Only interact with the block when there is no token coin item in the hand and player stands.
    if (level.isClientSide) {
      return InteractionResult.SUCCESS;
    } else if (handItemStack.isEmpty() && !player.isCrouching()) {
      // Deactivate block state animation
      if (blockState.getValue(STATE) != 0) {
        level.setBlock(blockPos, blockState.setValue(STATE, 0), 3);
      }
      // Show current piggy bank balance
      if (blockEntity instanceof PiggyBankBlockEntity blockEntityInstance) {
        player.sendMessage(
            new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_check_balance",
                blockEntityInstance.getDefaultName().getString()).withStyle(ChatFormatting.YELLOW),
            player.getUUID());
        int piggyBankValue = blockEntityInstance.getStoredValue();
        level.playSound(null, blockPos, SoundEvents.VILLAGER_WORK_WEAPONSMITH, SoundSource.BLOCKS,
            1.0F, 1.0F);
        if (piggyBankValue == 0) {
          player.sendMessage(
              new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_check_balance_empty")
                  .withStyle(ChatFormatting.YELLOW),
              player.getUUID());
        } else if (piggyBankValue < 100) {
          player.sendMessage(
              new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_check_balance_low",
                  piggyBankValue).withStyle(ChatFormatting.YELLOW),
              player.getUUID());
        } else if (piggyBankValue >= 1000) {
          player.sendMessage(
              new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_check_balance_high",
                  piggyBankValue).withStyle(ChatFormatting.YELLOW),
              player.getUUID());
        } else {
          player.sendMessage(
              new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_check_balance_normal",
                  piggyBankValue).withStyle(ChatFormatting.YELLOW),
              player.getUUID());
        }
      }
      return InteractionResult.CONSUME;
    } else if (handItemStack.getItem() instanceof CoinItem) {
      return InteractionResult.FAIL;
    }
    if (blockState.getValue(STATE) != 0) {
      level.setBlock(blockPos, blockState.setValue(STATE, 0), 3);
    }
    return InteractionResult.FAIL;
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PiggyBankBlockEntity(blockPos, blockState);
  }

  @Override
  public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState,
      Player player) {
    // Dropping all token coins before the block breaks.
    if (!level.isClientSide
        && level.getBlockEntity(blockPos) instanceof PiggyBankBlockEntity blockEntity) {
      level.playSound(null, blockPos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
      blockEntity.dropTokenCoins(level, blockPos, blockState, player);
    }
    super.playerWillDestroy(level, blockPos, blockState, player);
  }

  @Override
  public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {
    if (blockState.getValue(STATE) == 0) {
      // Default state
    } else if (blockState.getValue(STATE) == 1) {
      level.setBlock(blockPos, blockState.setValue(STATE, 2), 3);
    } else if (blockState.getValue(STATE) == 2) {
      level.setBlock(blockPos, blockState.setValue(STATE, 0), 3);
    }
  }
}