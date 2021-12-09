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
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
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

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.entity.PiggyBankBlockEntity;
import de.markusbordihn.tokencoins.config.CommonConfig;
import de.markusbordihn.tokencoins.item.coin.CoinItem;

@Mod.EventBusSubscriber
public class PiggyBankBlock extends BaseEntityBlock
    implements TokenCoinCompatible, CookieTokenCoinCompatible {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "piggy_bank";

  // Config
  public static final CommonConfig.Config COMMON = CommonConfig.COMMON;
  public static boolean enablePiggyBankEffects = COMMON.enablePiggyBankEffects.get();

  // Predefined Voxel Shapes
  protected static final VoxelShape SHAPE_10_10_10_AABB = Block.box(3, 0, 3, 13, 10, 13);
  protected static final VoxelShape SHAPE_10_12_12_90DEG_AABB = Block.box(1, 0, 3, 15, 10, 13);
  protected static final VoxelShape SHAPE_10_12_12_AABB = Block.box(3, 0, 1, 13, 10, 15);
  protected static final VoxelShape SHAPE_8_10_12_AABB = Block.box(4, 0, 0, 12, 10, 16);
  protected static final VoxelShape SHAPE_8_15_14_AABB = Block.box(4, 0, 0, 12, 15, 14);
  protected static final VoxelShape SHAPE_AABB = Block.box(4, 0, 4, 12, 8, 12);

  // Block States
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  public static final IntegerProperty STATE = IntegerProperty.create("state", 0, 2);
  public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);

  @SubscribeEvent
  public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
    enablePiggyBankEffects = COMMON.enablePiggyBankEffects.get();
    if (enablePiggyBankEffects) {
      log.info("🪙 \u25BA Enable Piggy Bank Effects ...");
    } else {
      log.info("🪙 \u25A0 Disable Piggy Bank Effects ...");
    }
  }

  public PiggyBankBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
        .setValue(STATE, 0).setValue(TYPE, 0));
  }

  public void addParticle(Level level, ParticleOptions particle, double x, double y, double z) {
    if (level.isClientSide) {
      level.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
    }
  }

  public void addParticle(Level level, ParticleOptions particle, BlockPos blockPos) {
    addParticle(level, particle, blockPos.getX() + 0.5, blockPos.getY() + 0.5,
        blockPos.getZ() + 0.5);
  }

  public void addParticle(Level level, ParticleOptions particle, BlockPos blockPos, int repeat) {
    for (int i = 0; i < repeat; ++i) {
      addParticle(level, particle, blockPos);
    }
  }

  public void addParticleOnTop(Level level, ParticleOptions particle, BlockPos blockPos) {
    addParticle(level, particle, blockPos.getX() + 0.5, blockPos.getY() + 0.75,
        blockPos.getZ() + 0.5);
  }

  public void addParticleOnTop(Level level, ParticleOptions particle, BlockPos blockPos,
      int repeat) {
    for (int i = 0; i < repeat; ++i) {
      addParticleOnTop(level, particle, blockPos);
    }
  }

  public void playSound(Player player, SoundEvent sound, float volume, float pitch) {
    if (player.level.isClientSide) {
      player.playSound(sound, volume, pitch);
    }
  }

  public void playSound(Player player, SoundEvent sound) {
    playSound(player, sound, 1.0F, 1.0F);
  }

  public void shootEgg(Level level, BlockPos blockPos, BlockState blockState, Player player) {
    Direction direction = blockState.getValue(PiggyBankBlock.FACING);
    ThrownEgg thrownEgg = new ThrownEgg(level, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    thrownEgg.shoot(direction.getStepX(), direction.getStepY(), direction.getStepZ(), 0.5F, 0.5F);
    level.addFreshEntity(thrownEgg);
  }

  public void shootSnowball(Level level, BlockPos blockPos, BlockState blockState, Player player) {
    Direction direction = blockState.getValue(PiggyBankBlock.FACING);
    Snowball snowball = new Snowball(level, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    snowball.shoot(direction.getStepX(), direction.getStepY(), direction.getStepZ(), 0.5F, 0.5F);
    level.addFreshEntity(snowball);
  }

  public BlockState setState(Level level, BlockPos blockPos, BlockState blockState, int state) {
    BlockState newBlockState = blockState.setValue(PiggyBankBlock.STATE, state);
    if (blockState.getValue(PiggyBankBlock.STATE) != state) {
      level.setBlock(blockPos, newBlockState, 3);
    }
    return newBlockState;
  }

  public BlockState setType(Level level, BlockPos blockPos, BlockState blockState, int type) {
    BlockState newBlockState =
        blockState.setValue(PiggyBankBlock.STATE, 0).setValue(PiggyBankBlock.TYPE, type);
    if (blockState.getValue(PiggyBankBlock.STATE) != 0
        || blockState.getValue(PiggyBankBlock.TYPE) != type) {
      level.setBlock(blockPos, newBlockState, 3);
    }
    return newBlockState;
  }

  public BlockState setStateAndType(Level level, BlockPos blockPos, BlockState blockState,
      int state, int type) {
    BlockState newBlockState =
        blockState.setValue(PiggyBankBlock.STATE, state).setValue(PiggyBankBlock.TYPE, type);
    if (blockState.getValue(PiggyBankBlock.STATE) != state
        || blockState.getValue(PiggyBankBlock.TYPE) != type) {
      level.setBlock(blockPos, newBlockState, 3);
    }
    return newBlockState;
  }

  /**
   * Allows to add individual and additional effects for consumed token coins. Return of true will
   * avoid the default sound.
   */
  public boolean consumeTokenCoinEffects(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    return false;
  }

  private void scheduleTick(Level level, BlockPos blockPos) {
    if (!level.getBlockTicks().hasScheduledTick(blockPos, this)) {
      level.scheduleTick(blockPos, this, 25);
    }
  }

  /**
   * Allows adding additional use effects for fun or for more interaction.
   */
  public InteractionResult useEffect(BlockState blockState, Level level, BlockPos blockPos,
      Player player, InteractionHand hand, BlockHitResult hitResult) {
    return InteractionResult.PASS;
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
    blockState.add(FACING, STATE, TYPE);
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
  public boolean canConsumeTokenCoin(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, Player player, ItemStack itemStack) {
    if (blockEntity instanceof PiggyBankBlockEntity blockEntityInstance
        && itemStack.getItem() instanceof CoinItem coinItem && coinItem.getValue() >= 1) {
      if (blockEntityInstance.canStoreTokenCoin(coinItem)) {
        return true;
      } else {
        // Send the player an message if the piggy bank could be full. (server-side)
        if (!level.isClientSide && player.isAlive()) {
          Block block = blockState.getBlock();
          player
              .sendMessage(
                  new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_full",
                      block.getName().getString()).withStyle(ChatFormatting.YELLOW),
                  player.getUUID());
        }
      }
    }
    return false;
  }

  @Override
  public InteractionResult consumeTokenCoin(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    CoinItem coinItem = (CoinItem) itemStack.getItem();
    int storedTokenCoins = 0;

    // Get Piggy Bank Entity and update level reference.
    PiggyBankBlockEntity blockEntityInstance = (PiggyBankBlockEntity) blockEntity;
    blockEntityInstance.updateLevel(level);

    // Schedule Tick for Animation reset
    scheduleTick(level, blockPos);

    // If the user is crouching try to consume the whole stack
    if (context.getPlayer().isCrouching()) {
      storedTokenCoins = blockEntityInstance.storeTokenCoin(itemStack);
    } else {
      storedTokenCoins = blockEntityInstance.storeTokenCoin(itemStack.getItem());
    }
    // If we are able to store any coins shrink their hand.
    if (storedTokenCoins > 0) {
      // Update block state for animation.
      if (blockState.getValue(PiggyBankBlock.STATE) != 1) {
        level.setBlock(blockPos, blockState.setValue(PiggyBankBlock.STATE, 1), 3);
      }

      // Play a confirmation sound and effects
      if (!consumeTokenCoinEffects(level, blockPos, blockState, blockEntity, itemStack, context)) {
        level.playSound(null, blockPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F,
            1.0F);
      }

      // Shrink item stack with the stored amount and inform block entity about the change.
      itemStack.shrink(storedTokenCoins);
      blockEntityInstance.increaseStoredValue(coinItem.getValue() * storedTokenCoins);
      blockEntityInstance.setChanged();
      return InteractionResult.CONSUME;
    }
    return InteractionResult.FAIL;
  }

  @Override
  public InteractionResult consumeCookieTokenCoin(Level level, BlockPos blockPos,
      BlockState blockState, BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    // Get Piggy Bank Entity and update level reference.
    PiggyBankBlockEntity blockEntityInstance = (PiggyBankBlockEntity) blockEntity;
    blockEntityInstance.updateLevel(level);

    // Schedule Tick for Animation reset.
    scheduleTick(level, blockPos);

    // Update block state for animation.
    if (blockState.getValue(PiggyBankBlock.STATE) != 1) {
      level.setBlock(blockPos, blockState.setValue(PiggyBankBlock.STATE, 1), 3);
    }

    // Shrink item stack and inform block entity about the change.
    itemStack.shrink(1);
    blockEntityInstance.setChanged();

    return InteractionResult.CONSUME;
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    // Check if there are any use effect and early return if result is not pass.
    if (enablePiggyBankEffects) {
      InteractionResult interactionResultEffect =
          useEffect(blockState, level, blockPos, player, hand, hitResult);
      if (interactionResultEffect != InteractionResult.PASS) {
        return interactionResultEffect;
      }
    }

    // Return fail, if server or client thinks that the hand is not empty.
    if (!player.getMainHandItem().isEmpty() || !player.getItemInHand(hand).isEmpty()) {
      return InteractionResult.FAIL;
    }

    // Return fail, if the player is crouching on the client.
    if (level.isClientSide) {
      return player.isCrouching() ? InteractionResult.FAIL : InteractionResult.SUCCESS;
    }

    // Only interact with the block when there is no token coin item in the hand and player stands.
    if (player.getMainHandItem().isEmpty() && !player.isCrouching()
        && level.getBlockEntity(blockPos) instanceof PiggyBankBlockEntity blockEntityInstance) {
      // Schedule Tick for Animation reset
      scheduleTick(level, blockPos);

      player.sendMessage(
          new TranslatableComponent(Constants.TEXT_PREFIX + "piggy_bank_check_balance",
              blockEntityInstance.getDefaultName().getString()).withStyle(ChatFormatting.YELLOW),
          player.getUUID());
      level.playSound(null, blockPos, SoundEvents.VILLAGER_WORK_WEAPONSMITH, SoundSource.BLOCKS,
          1.0F, 1.0F);

      // Custom Messages depending on the balance
      int piggyBankValue = blockEntityInstance.getStoredValue();
      String textResult = "piggy_bank_check_balance_normal";
      if (piggyBankValue == 0) {
        textResult = "piggy_bank_check_balance_empty";
      } else if (piggyBankValue < 100) {
        textResult = "piggy_bank_check_balance_low";
      } else if (piggyBankValue <= 1000) {
        textResult = "piggy_bank_check_balance_high";
      } else if (piggyBankValue > 1000) {
        textResult = "piggy_bank_check_balance_very_high";
      }
      player
          .sendMessage(new TranslatableComponent(Constants.TEXT_PREFIX + textResult, piggyBankValue)
              .withStyle(ChatFormatting.YELLOW), player.getUUID());
      return InteractionResult.CONSUME;
    }

    return InteractionResult.FAIL;
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PiggyBankBlockEntity(ModBlocks.PIGGY_BANK_ENTITY.get(), blockPos, blockState);
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
  public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
    BlockEntity blockEntity = level.getBlockEntity(blockPos);
    if (blockEntity instanceof PiggyBankBlockEntity blockEntityInstance) {
      blockEntityInstance.recheckAnimationState(level, blockState, blockPos);
    }
  }

  @Override
  public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {
    // Increase animation state value for simple two steps animation (client-only).
    // Reset type with the last step.
    if (blockState.getValue(PiggyBankBlock.STATE) == 1) {
      level.setBlock(blockPos, blockState.setValue(PiggyBankBlock.STATE, 2), 3);
    } else if (blockState.getValue(PiggyBankBlock.STATE) == 2) {
      level.setBlock(blockPos, blockState.setValue(PiggyBankBlock.STATE, 0), 3);
    }
  }
}
