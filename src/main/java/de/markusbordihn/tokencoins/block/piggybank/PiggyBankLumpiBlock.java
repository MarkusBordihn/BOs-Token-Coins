package de.markusbordihn.tokencoins.block.piggybank;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
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
import de.markusbordihn.tokencoins.item.ModItems;

public class PiggyBankLumpiBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_lumpi";

  public PiggyBankLumpiBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return PiggyBankBlock.SHAPE_8_10_12_AABB;
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    ItemStack handItemStack = player.getItemInHand(hand);
    if (handItemStack.is(Items.FLINT_AND_STEEL)) {
      playSound(player, SoundEvents.FLINTANDSTEEL_USE);
      addParticleOnTop(level, ParticleTypes.FLAME, blockPos);
      addParticleOnTop(level, ParticleTypes.FLAME, blockPos);
      playSound(player, SoundEvents.WOLF_HURT);
      addParticle(level, ParticleTypes.ANGRY_VILLAGER, blockPos);
      return InteractionResult.SUCCESS;
    } else if (handItemStack.is(ModItems.COPPER_TOKEN_COIN_WITH_LEKOOPA.get())) {
      playSound(player, SoundEvents.WOLF_PANT);
      addParticle(level, ParticleTypes.HAPPY_VILLAGER, blockPos);
    }
    return super.use(blockState, level, blockPos, player, hand, hitResult);
  }

  @Override
  public boolean consumeTokenCoinEffects(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    return itemStack.is(ModItems.COPPER_TOKEN_COIN_WITH_LEKOOPA.get());
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PiggyBankBlockEntity(ModBlocks.PIGGY_BANK_LUMPI_ENTITY.get(), blockPos, blockState);
  }

}
