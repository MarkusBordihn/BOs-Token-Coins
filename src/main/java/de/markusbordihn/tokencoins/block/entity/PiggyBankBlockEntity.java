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

package de.markusbordihn.tokencoins.block.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.block.PiggyBankBlock;

public class PiggyBankBlockEntity extends BlockEntity {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
  private int storedValue = 0;
  private String owner = "";

  public PiggyBankBlockEntity(BlockPos blockPos, BlockState blockState) {
    super(ModBlocks.PIGGY_BANK_ENTITY.get(), blockPos, blockState);
  }

  public PiggyBankBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos,
      BlockState blockState) {
    super(blockEntityType, blockPos, blockState);
  }

  public void increaseStoredValue(int value) {
    this.storedValue = this.storedValue + value;
  }

  public Component getDefaultName() {
    return new TranslatableComponent(Constants.BLOCK_TEXT_PREFIX + "piggy_bank");
  }

  public int getStoredValue() {
    return this.storedValue;
  }

  public int getContainerSize() {
    return this.items.size();
  }

  public ItemStack getItem(int index) {
    return this.items.get(index);
  }

  public void setOwner(LivingEntity livingEntity) {
    this.owner = livingEntity.getStringUUID();
    this.setChanged();
  }

  public void updateLevel(Level level) {
    if (this.level == null && this.level != level && !level.isClientSide()) {
      this.level = level;
    }
  }

  public boolean canStoreTokenCoin(Item item) {
    // Cheap reverse pre-check to make sure we are able to store the token coin.
    for (int index = this.items.size() - 1; 0 <= index; index--) {
      ItemStack existingItems = this.items.get(index);
      if (existingItems.isEmpty()) {
        return true;
      }
      if (existingItems.is(item) && existingItems.getCount() < existingItems.getMaxStackSize()) {
        return true;
      }
    }
    return false;
  }

  public int storeTokenCoin(ItemStack itemStack) {
    int result = 0;
    Item item = itemStack.getItem();
    for (int index = 0; index < itemStack.getCount(); index++) {
      result = result + storeTokenCoin(item);
    }
    return result;
  }

  public int storeTokenCoin(Item item) {
    // Iterate trough item stacks to find a matching or empty item stack to store the item.
    for (int index = 0; index < this.items.size(); index++) {
      ItemStack existingItems = this.items.get(index);
      if (!existingItems.isEmpty() && existingItems.is(item)
          && existingItems.getCount() < existingItems.getMaxStackSize()) {
        existingItems.grow(1);
        return 1;
      }
      if (existingItems.isEmpty()) {
        this.items.set(index, new ItemStack(item));
        return 1;
      }
    }
    return 0;
  }

  public void dropTokenCoins(Level level, BlockPos blockPos, BlockState blockState, Player player) {
    if (player.getStringUUID().equals(this.owner)) {
      log.debug("Owner {} destroyed Piggy Bank at {}", player, blockPos);
    } else {
      log.debug("{} destroyed Piggy Bank from {} at {}", player, this.owner, blockPos);
    }
    for (int index = 0; index < this.items.size(); index++) {
      ItemStack tokenCoinItems = this.items.get(index);
      if (!tokenCoinItems.isEmpty()) {
        level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(),
            blockPos.getZ(), tokenCoinItems));
      }
    }
    this.storedValue = 0;
  }

  public void recheckAnimationState(Level level, BlockState blockState, BlockPos blockPos) {
    if (blockState.getValue(PiggyBankBlock.STATE) != 0) {
      level.setBlock(blockPos, blockState.setValue(PiggyBankBlock.STATE, 0), 3);
    }
  }

  @Override
  public void load(CompoundTag compoundTag) {
    super.load(compoundTag);
    this.storedValue = compoundTag.getInt("storedValue");
    this.owner = compoundTag.getString("owner");
    this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    ContainerHelper.loadAllItems(compoundTag, this.items);
  }

  @Override
  public CompoundTag save(CompoundTag compoundTag) {
    super.save(compoundTag);
    compoundTag.putInt("storedValue", this.storedValue);
    compoundTag.putString("owner", this.owner);
    ContainerHelper.saveAllItems(compoundTag, this.items);
    return compoundTag;
  }

}
