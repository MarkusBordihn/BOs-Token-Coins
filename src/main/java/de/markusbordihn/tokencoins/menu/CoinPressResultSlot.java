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

package de.markusbordihn.tokencoins.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.event.ForgeEventFactory;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.block.entity.CoinPressBlockEntity;

public class CoinPressResultSlot extends Slot {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  private final Player player;
  private int removeCount;

  public CoinPressResultSlot(Player player, Container container, int p_39544_, int p_39545_,
      int p_39546_) {
    super(container, p_39544_, p_39545_, p_39546_);
    this.player = player;
  }

  protected void playSound(Player player) {
    if (player != null && player.isAlive()) {
      player.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT, 1.0F, 1.0F);
    }
  }

  @Override
  public boolean mayPlace(ItemStack itemStack) {
    return false;
  }

  @Override
  public ItemStack remove(int p_39548_) {
    if (this.hasItem()) {
      this.removeCount += Math.min(p_39548_, this.getItem().getCount());
    }

    return super.remove(p_39548_);
  }

  @Override
  public void onTake(Player player, ItemStack itemStack) {
    this.checkTakeAchievements(itemStack);
    this.playSound(player);
    super.onTake(player, itemStack);
  }

  @Override
  protected void onQuickCraft(ItemStack itemStack, int p_39556_) {
    this.removeCount += p_39556_;
    this.checkTakeAchievements(itemStack);
  }

  @Override
  protected void checkTakeAchievements(ItemStack itemStack) {
    itemStack.onCraftedBy(this.player.level, this.player, this.removeCount);
    if (this.player instanceof ServerPlayer serverPlayer
        && this.container instanceof CoinPressBlockEntity coinPressBlockEntity) {
      coinPressBlockEntity.awardUsedRecipesAndPopExperience(serverPlayer);
    }

    this.removeCount = 0;
    ForgeEventFactory.firePlayerSmeltedEvent(this.player, itemStack);
  }
}
