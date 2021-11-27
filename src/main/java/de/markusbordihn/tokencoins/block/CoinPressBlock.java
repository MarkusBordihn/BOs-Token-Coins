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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.entity.CoinPressBlockEntity;

public class CoinPressBlock extends BaseEntityBlock {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "coin_press";

  // Define custom block states for better animation and model control.
  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
  public static final BooleanProperty POWERED = BlockStateProperties.LIT;
  public static final BooleanProperty WORKING = BooleanProperty.create("working");
  public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 6);

  // General map to be able animate each model individual regardless of the current block state.
  private Map<String, Boolean> lastWorkingStateMap = new HashMap<>();
  private Map<String, Boolean> lastPoweredStateMap = new HashMap<>();

  protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

  public CoinPressBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
        .setValue(POWERED, Boolean.valueOf(false)).setValue(WORKING, Boolean.valueOf(false))
        .setValue(VARIANT, 0));
  }

  protected void openContainer(Level level, BlockPos blockPos, Player player) {
    BlockEntity blockEntity = level.getBlockEntity(blockPos);
    if (blockEntity instanceof CoinPressBlockEntity furnace) {
      player.openMenu(furnace);
    }
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand interactionHand, BlockHitResult blockHitResult) {
    if (!level.isClientSide) {
      this.openContainer(level, blockPos, player);
    }
    return InteractionResult.sidedSuccess(level.isClientSide);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
    blockState.add(POWERED, WORKING, FACING, VARIANT);
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING,
        context.getHorizontalDirection().getOpposite());
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return SHAPE;
  }

  @Override
  public RenderShape getRenderShape(BlockState blockState) {
    return RenderShape.MODEL;
  }

  @Override
  public BlockState rotate(BlockState blockState, Rotation rotation) {
    return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
  }

  @Override
  @SuppressWarnings("deprecation")
  public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2,
      boolean flag) {
    if (!blockState.is(blockState2.getBlock())) {
      BlockEntity blockentity = level.getBlockEntity(blockPos);
      if (blockentity instanceof CoinPressBlockEntity coinPressBlockEntity) {
        if (level instanceof ServerLevel) {
          Containers.dropContents(level, blockPos, coinPressBlockEntity);
        }
        level.updateNeighbourForOutputSignal(blockPos, this);
      }
      super.onRemove(blockState, level, blockPos, blockState2, flag);
    }
  }

  @Override
  @Nullable
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
      BlockEntityType<T> blockEntityType) {
    return level.isClientSide ? null
        : createTickerHelper(blockEntityType, ModBlocks.COIN_PRESS_ENTITY.get(),
            CoinPressBlockEntity::serverTick);
  }

  @Override
  public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {

    // Get the current stats based on the block position.
    boolean powerState = Boolean.TRUE.equals(blockState.getValue(POWERED));
    boolean workingState = Boolean.TRUE.equals(blockState.getValue(WORKING));
    boolean lastPoweredState =
        this.lastPoweredStateMap.getOrDefault(blockPos.toShortString(), false);
    boolean lastWorkingState =
        this.lastWorkingStateMap.getOrDefault(blockPos.toShortString(), false);
    SoundEvent soundEvent = null;
    SimpleParticleType particleTypes = null;

    // Early return if nothing was changed.
    if (powerState == lastPoweredState && workingState == lastWorkingState) {
      return;
    }

    // Device is turned on / off or start / stop working
    if (powerState && !lastPoweredState) {
      soundEvent = SoundEvents.STONE_BUTTON_CLICK_ON;
      particleTypes = ParticleTypes.ELECTRIC_SPARK;
      this.lastPoweredStateMap.put(blockPos.toShortString(), powerState);
    } else if (!powerState && lastPoweredState) {
      soundEvent = SoundEvents.STONE_BUTTON_CLICK_OFF;
      this.lastPoweredStateMap.put(blockPos.toShortString(), powerState);
    } else if (workingState && !lastWorkingState) {
      soundEvent = SoundEvents.UI_LOOM_SELECT_PATTERN;
      particleTypes = ParticleTypes.INSTANT_EFFECT;
      this.lastWorkingStateMap.put(blockPos.toShortString(), workingState);
    } else if (!workingState && lastWorkingState) {
      soundEvent = SoundEvents.UI_LOOM_TAKE_RESULT;
      this.lastWorkingStateMap.put(blockPos.toShortString(), workingState);
    }

    // Handle Sound Event
    if (soundEvent != null) {
      double d0 = blockPos.getX() + 0.5D;
      double d1 = blockPos.getY();
      double d2 = blockPos.getZ() + 0.5D;
      level.playLocalSound(d0, d1, d2, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F, false);
    }

    // Handle Particle
    if (particleTypes != null) {
      double d0 = blockPos.getX() + 0.05D;
      double d1 = blockPos.getY() + 0.15D;
      double d2 = blockPos.getZ() + 0.5D;
      Direction direction = blockState.getValue(FACING);
      Direction.Axis directionAxis = direction.getAxis();
      double d4 = random.nextDouble() * 0.6D - 0.3D;
      double d5 = directionAxis == Direction.Axis.X ? direction.getStepX() * 0.52D : d4;
      double d6 = random.nextDouble() * 6.0D / 16.0D;
      double d7 = directionAxis == Direction.Axis.Z ? direction.getStepZ() * 0.52D : d4;
      level.addParticle(particleTypes, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
    }
  }

  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new CoinPressBlockEntity(blockPos, blockState);
  }

}
