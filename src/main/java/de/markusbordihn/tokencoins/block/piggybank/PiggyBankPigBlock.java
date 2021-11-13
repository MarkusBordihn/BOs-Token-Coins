package de.markusbordihn.tokencoins.block.piggybank;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import de.markusbordihn.tokencoins.block.PiggyBankBlock;

public class PiggyBankPigBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_pig";

  public PiggyBankPigBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return PiggyBankBlock.SHAPE_10_12_12_AABB;
  }

}
