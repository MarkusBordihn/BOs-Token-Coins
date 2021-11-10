package de.markusbordihn.tokencoins.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

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

  public boolean mayPlace(ItemStack itemStack) {
    return false;
  }

  public ItemStack remove(int p_39548_) {
    if (this.hasItem()) {
      this.removeCount += Math.min(p_39548_, this.getItem().getCount());
    }

    return super.remove(p_39548_);
  }

  public void onTake(Player player, ItemStack itemStack) {
    this.checkTakeAchievements(itemStack);
    super.onTake(player, itemStack);
  }

  protected void onQuickCraft(ItemStack itemStack, int p_39556_) {
    this.removeCount += p_39556_;
    this.checkTakeAchievements(itemStack);
  }

  protected void checkTakeAchievements(ItemStack itemStack) {
    itemStack.onCraftedBy(this.player.level, this.player, this.removeCount);
    if (this.player instanceof ServerPlayer serverPlayer
        && this.container instanceof CoinPressBlockEntity coinPressBlockEntity) {
      coinPressBlockEntity.awardUsedRecipesAndPopExperience(serverPlayer);
    }

    this.removeCount = 0;
    net.minecraftforge.fmllegacy.hooks.BasicEventHooks.firePlayerSmeltedEvent(this.player,
        itemStack);
  }
}
