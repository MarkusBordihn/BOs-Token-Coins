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

public class PiggyBankSafeBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_safe";

  public PiggyBankSafeBlock(Properties properties) {
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
    if (handItemStack.is(Items.FLINT_AND_STEEL)) {
      playSound(player, SoundEvents.FLINTANDSTEEL_USE);
      addParticleOnTop(level, ParticleTypes.SMOKE, blockPos);
      playSound(player, SoundEvents.VILLAGER_NO);
      return InteractionResult.SUCCESS;
    }
    return super.use(blockState, level, blockPos, player, hand, hitResult);
  }

}
