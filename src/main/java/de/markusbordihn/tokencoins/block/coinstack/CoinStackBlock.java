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

package de.markusbordihn.tokencoins.block.coinstack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.RegistryObject;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.TokenCoinCompatible;
import de.markusbordihn.tokencoins.item.coin.CoinItem;
import de.markusbordihn.tokencoins.item.coin.CoinItemType;

public class CoinStackBlock extends FallingBlock implements TokenCoinCompatible {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "coin_stack";

  public static final int MAX_STACK_SIZE = 16;
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public static final IntegerProperty AMOUNT = IntegerProperty.create("amount", 1, MAX_STACK_SIZE);

  public static final VoxelShape SHAPE_DEFAULT_AABB = Block.box(0.1, 0, 0.1, 15.9, 15.9, 15.9);
  public static final VoxelShape SHAPE_1_10_AABB = Block.box(0.1, 0, 0.1, 15.9, 0.26, 15.9);
  public static final VoxelShape SHAPE_11_20_AABB = Block.box(0.1, 0, 0.1, 15.9, 0.53, 15.9);

  private CoinItemType.Material coinMaterial = null;
  private CoinItemType.Motive coinMotive = null;
  private CoinItem coinItem = null;
  private RegistryObject<Item> registryItem = null;

  public CoinStackBlock(BlockBehaviour.Properties properties) {
    super(properties);
    this.registerDefaultState(
        this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, 1));
  }

  public CoinStackBlock(RegistryObject<Item> item, BlockBehaviour.Properties properties) {
    super(properties);
    this.registerDefaultState(
        this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, 1));
    registryItem = item;
  }

  public void setCoinItem(CoinItem coin) {
    coinItem = coin;
    coinMaterial = coin.getMaterial();
    coinMotive = coin.getMotive();
  }

  public boolean ensureCoinItem() {
    if (coinItem != null) {
      return true;
    }
    if (registryItem != null) {
      setCoinItem((CoinItem) registryItem.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean canConsumeTokenCoin(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, Player player, ItemStack itemStack) {
    return (itemStack.getItem() instanceof CoinItem coinItemInstance && ensureCoinItem()
        && coinItemInstance.getMotive() == coinMotive
        && coinItemInstance.getMaterial() == coinMaterial
        && blockState.getValue(AMOUNT) < MAX_STACK_SIZE);
  }

  @Override
  public InteractionResult consumeTokenCoin(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    // If we are able to store any coins shrink their hand.
    level.setBlockAndUpdate(blockPos, blockState.setValue(AMOUNT, blockState.getValue(AMOUNT) + 1));
    itemStack.shrink(1);
    return InteractionResult.CONSUME;
  }

  @Override
  public void attack(BlockState blockState, Level level, BlockPos blockPos, Player player) {
    // Return if the player is on the client.
    if (level.isClientSide || !ensureCoinItem()) {
      return;
    }

    int storedAmount = blockState.getValue(AMOUNT);
    if (storedAmount > 0) {
      ItemStack itemStack = new ItemStack(coinItem);
      // Only process if we are able to store the item in the players inventory.
      if (player.addItem(itemStack)) {
        if (storedAmount == 1) {
          // Remove block if there are no longer any coins.
          level.removeBlock(blockPos, false);
        } else {
          // Decrease stored coin amount.
          level.setBlockAndUpdate(blockPos,
              blockState.setValue(AMOUNT, blockState.getValue(AMOUNT) - 1));
        }
      }
    }
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
    blockState.add(AMOUNT, FACING);
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING,
        context.getHorizontalDirection().getOpposite());
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    int storedAmount = blockState.getValue(AMOUNT);
    if (storedAmount <= 10) {
      return SHAPE_1_10_AABB;
    } else if (storedAmount <= 20) {
      return SHAPE_11_20_AABB;
    }
    return SHAPE_DEFAULT_AABB;
  }

  @Override
  public BlockState rotate(BlockState blockState, Rotation rotation) {
    return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState blockState, Mirror mirror) {
    return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
  }

}
