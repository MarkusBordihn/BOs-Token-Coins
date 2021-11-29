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
import de.markusbordihn.tokencoins.item.coin.CoinItem;
import de.markusbordihn.tokencoins.tags.ModItemTags;

public class PiggyBankNoteBlockBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_note_block";

  public PiggyBankNoteBlockBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return PiggyBankBlock.SHAPE_10_10_10_AABB;
  }

  @Override
  public InteractionResult useEffect(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    ItemStack handItemStack = player.getItemInHand(hand);
    if (handItemStack.is(Items.FLINT_AND_STEEL)) {
      playSound(player, SoundEvents.FLINTANDSTEEL_USE);
      addParticleOnTop(level, ParticleTypes.FLAME, blockPos, 2);
      playSound(player, SoundEvents.FIRE_AMBIENT);
      return InteractionResult.SUCCESS;
    } else if (handItemStack.is(Items.POPPY)) {
      playSound(player, SoundEvents.CHORUS_FLOWER_GROW);
      addParticle(level, ParticleTypes.NOTE, blockPos, 3);
      return InteractionResult.SUCCESS;
    } else if (handItemStack.is(ModItemTags.tokenCoinWithCreeper)) {
      playSound(player, SoundEvents.CREEPER_PRIMED);
      addParticleOnTop(level, ParticleTypes.SMOKE, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithDragon)) {
      playSound(player, SoundEvents.ENDER_DRAGON_AMBIENT);
      addParticleOnTop(level, ParticleTypes.DRAGON_BREATH, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithLeKoopa)) {
      playSound(player, SoundEvents.TURTLE_SHAMBLE);
      addParticle(level, ParticleTypes.EFFECT, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithPoppy)) {
      playSound(player, SoundEvents.CHORUS_FLOWER_GROW);
      addParticle(level, ParticleTypes.AMBIENT_ENTITY_EFFECT, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithSkeleton)) {
      playSound(player, SoundEvents.SKELETON_AMBIENT);
      addParticleOnTop(level, ParticleTypes.SOUL, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithWheat)) {
      playSound(player, SoundEvents.CROP_BREAK);
      addParticleOnTop(level, ParticleTypes.COMPOSTER, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithWither)) {
      playSound(player, SoundEvents.WITHER_AMBIENT);
      addParticle(level, ParticleTypes.WHITE_ASH, blockPos);
    } else if (handItemStack.is(ModItemTags.tokenCoinWithZombie)) {
      playSound(player, SoundEvents.ZOMBIE_AMBIENT);
      addParticle(level, ParticleTypes.DAMAGE_INDICATOR, blockPos);
    } else if (handItemStack.is(ModItems.GOLD_TOKEN_COIN.get())) {
      playSound(player, SoundEvents.NOTE_BLOCK_CHIME);
      addParticle(level, ParticleTypes.NOTE, blockPos);
    } else if (handItemStack.is(ModItems.IRON_TOKEN_COIN.get())) {
      playSound(player, SoundEvents.NOTE_BLOCK_BASS);
      addParticle(level, ParticleTypes.NOTE, blockPos);
    } else if (handItemStack.is(ModItems.STEEL_TOKEN_COIN.get())) {
      playSound(player, SoundEvents.NOTE_BLOCK_BASEDRUM);
      addParticle(level, ParticleTypes.NOTE, blockPos);
    } else if (handItemStack.is(ModItems.NETHERITE_TOKEN_COIN.get())) {
      playSound(player, SoundEvents.NOTE_BLOCK_GUITAR);
      addParticle(level, ParticleTypes.NOTE, blockPos);
    } else if (handItemStack.is(ModItems.COPPER_TOKEN_COIN.get())
        || handItemStack.getItem() instanceof CoinItem) {
      playSound(player, SoundEvents.NOTE_BLOCK_BELL);
      addParticle(level, ParticleTypes.NOTE, blockPos);
    }
    return InteractionResult.PASS;
  }

  @Override
  public boolean consumeTokenCoinEffects(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    return PiggyBankBlock.enablePiggyBankEffects;
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PiggyBankBlockEntity(ModBlocks.PIGGY_BANK_NOTE_BLOCK_ENTITY.get(), blockPos,
        blockState);
  }

}
