package de.markusbordihn.tokencoins.block.piggybank;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import de.markusbordihn.tokencoins.block.PiggyBankBlock;

public class PiggyBankSkeletonBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_skeleton";

  public PiggyBankSkeletonBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return PiggyBankBlock.SHAPE_10_10_10_AABB;
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    ItemStack handItemStack = player.getItemInHand(hand);
    if (level.isClientSide && handItemStack.is(Items.CARROT)) {
      player.playSound(SoundEvents.HORSE_EAT, 1.0F, 1.0F);
      level.addParticle(ParticleTypes.HEART, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
          0.0D, 0.0D, 0.0D);
      return InteractionResult.CONSUME;
    }
    return super.use(blockState, level, blockPos, player, hand, hitResult);
  }

}
