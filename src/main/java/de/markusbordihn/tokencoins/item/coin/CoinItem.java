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

package de.markusbordihn.tokencoins.item.coin;

import java.util.List;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.block.TokenCoinCompatible;
import de.markusbordihn.tokencoins.block.coinstack.CoinStackBlock;
import de.markusbordihn.tokencoins.tabs.TokenCoinsTab;

public class CoinItem extends Item {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  private CoinItemType.Material coinMaterial = CoinItemType.Material.COPPER;
  private CoinItemType.Motive coinMotive = CoinItemType.Motive.NONE;

  public int coinValue = 1;

  public CoinItem() {
    super(new Item.Properties().tab(TokenCoinsTab.COINS));
  }

  public CoinItem(Item.Properties properties) {
    super(properties.tab(TokenCoinsTab.COINS));
  }

  public CoinItem(CoinItemType.Material material) {
    super(new Item.Properties().tab(TokenCoinsTab.COINS));
    this.coinMaterial = material;
  }

  public CoinItem(CoinItemType.Material material, CoinItemType.Motive motive) {
    super(new Item.Properties().tab(TokenCoinsTab.COINS));
    this.coinMaterial = material;
    this.coinMotive = motive;
  }

  public CoinItemType.Material getMaterial() {
    return this.coinMaterial;
  }

  public CoinItemType.Motive getMotive() {
    return this.coinMotive;
  }

  public int getValue() {
    return coinValue;
  }

  public boolean placeCoinStack(Level level, BlockPos blockPos, ItemStack itemStack,
      Direction direction) {
    BlockState blockState = null;
    Item item = itemStack.getItem();
    if (item instanceof CopperCoinItem) {
      blockState = ModBlocks.COPPER_COIN_STACK.get().defaultBlockState();
    } else if (item instanceof GoldCoinItem) {
      blockState = ModBlocks.GOLD_COIN_STACK.get().defaultBlockState();
    } else if (item instanceof IronCoinItem) {
      blockState = ModBlocks.IRON_COIN_STACK.get().defaultBlockState();
    } else if (item instanceof SteelCoinItem) {
      blockState = ModBlocks.STEEL_COIN_STACK.get().defaultBlockState();
    } else if (item instanceof NetheriteCoinItem) {
      blockState = ModBlocks.NETHERITE_COIN_STACK.get().defaultBlockState();
    }
    if (blockState != null) {
      level.setBlockAndUpdate(blockPos, blockState.setValue(CoinStackBlock.FACING, direction));
      if (level.getBlockState(blockPos).getBlock() instanceof CoinStackBlock
          && itemStack.getItem() instanceof CoinItem) {
        return true;
      } else {
        log.warn("Unable to place coin stack block at {} in {}", blockPos, level);
      }
    } else {
      log.warn("Unsupported token coin item {}", item);
    }
    return false;
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    BlockState blockState = level.getBlockState(blockPos);
    Block block = blockState.getBlock();

    // Only interact with the Block if it is Token Coin compatible.
    if (block instanceof TokenCoinCompatible tokenCoinCompatibleBlock) {
      ItemStack itemStack = context.getItemInHand();
      BlockEntity blockEntity = level.getBlockEntity(blockPos);
      Player player = context.getPlayer();
      // Make sure that the block can consume the token, we only accept server-side confirmations.
      if (!level.isClientSide) {
        if (tokenCoinCompatibleBlock.canConsumeTokenCoin(level, blockPos, blockState, blockEntity,
            player, itemStack)) {
          return tokenCoinCompatibleBlock.consumeTokenCoin(level, blockPos, blockState, blockEntity,
              itemStack, context);
        }
        return InteractionResult.FAIL;
      }
      return InteractionResult.sidedSuccess(level.isClientSide);
    }

    // Check if we clicked an empty block and maybe could place a coin stack
    BlockPos blockPosAbove = blockPos.above();
    BlockState blockStateBlockAbove = level.getBlockState(blockPosAbove);
    if (blockStateBlockAbove.is(Blocks.AIR)) {
      if (!level.isClientSide) {
        ItemStack itemStack = context.getItemInHand();
        Direction direction = context.getHorizontalDirection().getOpposite();
        if (placeCoinStack(level, blockPosAbove, itemStack, direction)) {
          return InteractionResult.PASS;
        }
        return InteractionResult.FAIL;
      }
      return InteractionResult.sidedSuccess(level.isClientSide);
    }

    log.info("Maybe just throw the coin ...");
    return InteractionResult.PASS;
  }

  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable Level level,
      List<Component> tooltipList, TooltipFlag tooltipFlag) {
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + "coin_value", this.coinValue)
        .withStyle(ChatFormatting.YELLOW));
  }
}
