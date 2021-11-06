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

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ObjectHolder;

import de.markusbordihn.tokencoins.item.ModItems;
import de.markusbordihn.tokencoins.Constants;

public class CoinPressMenu extends AbstractContainerMenu {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  @ObjectHolder("token_coins:coin_press")
  public static MenuType<CoinPressMenu> TYPE;

  public static final int MATERIAL_SLOT = 0;
  public static final int STAMP_BOTTOM_SLOT = 1;
  public static final int STAMP_TOP_SLOT = 2;
  public static final int FUEL_SLOT = 3;
  public static final int RESULT_SLOT = 4;

  private static int containerSize = 5;
  private static int containerDataSize = 5;
  private static int slotSize = 18;
  private static int slotSpacing = 8;
  private final Container container;
  private final ContainerData data;
  protected final Level level;

  public CoinPressMenu(int windowIdIn, Inventory inventory) {
    this(windowIdIn, inventory, new SimpleContainer(containerSize),
        new SimpleContainerData(containerDataSize));
  }

  public CoinPressMenu(final int windowId, final Inventory playerInventory,
      final Container container, final ContainerData containerData) {
    super(TYPE, windowId);

    // Make sure the passed container matched the expected sizes
    checkContainerSize(container, containerSize);
    checkContainerDataCount(containerData, containerDataSize);
    this.container = container;
    this.data = containerData;
    this.level = playerInventory.player.level;

    // Define slots and position on UI (note: order sensitive)
    this.addSlot(new CoinPressMaterialSlot(container, MATERIAL_SLOT, 62, 41));
    this.addSlot(new CoinPressStampBottomSlot(container, STAMP_BOTTOM_SLOT, 62, 66));
    this.addSlot(new CoinPressStampTopSlot(container, STAMP_TOP_SLOT, 62, 16));
    this.addSlot(new CoinPressFuelSlot(container, FUEL_SLOT, 8, 66));
    this.addSlot(new FurnaceResultSlot(playerInventory.player, container, RESULT_SLOT, 134, 43));

    // Player Inventory Slots
    int playerInventoryStartPositionY = 99;
    for (int inventoryRow = 0; inventoryRow < 3; ++inventoryRow) {
      for (int inventoryColumn = 0; inventoryColumn < 9; ++inventoryColumn) {
        this.addSlot(new Slot(playerInventory, inventoryColumn + inventoryRow * 9 + 9,
            slotSpacing + inventoryColumn * slotSize,
            playerInventoryStartPositionY + inventoryRow * slotSize));
      }
    }

    // Player Hotbar
    int hotbarStartPositionY = 157;
    for (int playerInventorySlot = 0; playerInventorySlot < 9; ++playerInventorySlot) {
      this.addSlot(new Slot(playerInventory, playerInventorySlot,
          slotSpacing + playerInventorySlot * slotSize, hotbarStartPositionY));
    }

    this.addDataSlots(containerData);
  }

  @Override
  public ItemStack quickMoveStack(Player player, int slotIndex) {
    Slot slot = this.slots.get(slotIndex);
    if (slot == null || !slot.hasItem()) {
      return ItemStack.EMPTY;
    }
    ItemStack slotItemStack = slot.getItem();
    if (isFuel(slotItemStack)) {
      if (slotIndex == FUEL_SLOT) {
        this.moveItemStackTo(slotItemStack, 5, 40, false);
      } else {
        this.moveItemStackTo(slotItemStack, FUEL_SLOT, 4, false);
      }
    } else if (isNugget(slotItemStack)) {
      if (slotIndex == MATERIAL_SLOT) {
        this.moveItemStackTo(slotItemStack, 5, 40, false);
      } else {
        this.moveItemStackTo(slotItemStack, MATERIAL_SLOT, 4, false);
      }
    } else if (isCoinPressStamp(slotItemStack)) {
      if (slotIndex == STAMP_BOTTOM_SLOT || slotIndex == STAMP_TOP_SLOT) {
        this.moveItemStackTo(slotItemStack, 5, 40, false);
      } else if (!this.slots.get(STAMP_BOTTOM_SLOT).hasItem()) {
        this.moveItemStackTo(slotItemStack, STAMP_BOTTOM_SLOT, 4, false);
      } else if (!this.slots.get(STAMP_TOP_SLOT).hasItem()) {
        this.moveItemStackTo(slotItemStack, STAMP_TOP_SLOT, 4, false);
      }
    } else if (slotIndex == RESULT_SLOT) {
      this.moveItemStackTo(slotItemStack, 5, 40, false);
    }

    if (slotItemStack.isEmpty()) {
      slot.set(ItemStack.EMPTY);
    } else {
      slot.setChanged();
    }

    return ItemStack.EMPTY;
  }

  public void clearCraftingContent() {
    this.getSlot(FUEL_SLOT).set(ItemStack.EMPTY);
    this.getSlot(MATERIAL_SLOT).set(ItemStack.EMPTY);
    this.getSlot(STAMP_BOTTOM_SLOT).set(ItemStack.EMPTY);
    this.getSlot(STAMP_TOP_SLOT).set(ItemStack.EMPTY);
  }

  public boolean stillValid(Player player) {
    return this.container.stillValid(player);
  }

  public static boolean isFuel(ItemStack itemStack) {
    return net.minecraftforge.common.ForgeHooks.getBurnTime(itemStack,
        Constants.COIN_PRESS_RECIPE_TYPE) > 0;
  }

  public static boolean isNugget(ItemStack itemStack) {
    return (itemStack.is(Items.GOLD_NUGGET) || itemStack.is(Items.IRON_NUGGET)
        || itemStack.is(de.markusbordihn.materialelements.item.ModItems.COPPER_NUGGET.get())
        || itemStack.is(de.markusbordihn.materialelements.item.ModItems.STEEL_NUGGET.get())
        || itemStack.is(de.markusbordihn.materialelements.item.ModItems.NETHERITE_NUGGET.get()));
  }

  public static boolean isCoinPressStamp(ItemStack itemStack) {
    return (itemStack.is(ModItems.COIN_STAMP.get())
        || itemStack.is(ModItems.COPPER_COIN_STAMP.get())
        || itemStack.is(ModItems.GOLD_COIN_STAMP.get())
        || itemStack.is(ModItems.IRON_COIN_STAMP.get())
        || itemStack.is(ModItems.STEEL_COIN_STAMP.get())
        || itemStack.is(ModItems.NETHERITE_COIN_STAMP.get()));
  }

  public int getBurnProgress() {
    int i = this.data.get(2);
    int j = this.data.get(3);
    return j != 0 && i != 0 ? i * 24 / j : 0;
  }

  public int getLitProgress() {
    int i = this.data.get(1);
    if (i == 0) {
      i = 200;
    }
    return this.data.get(0) * 46 / i;
  }

  public boolean isLit() {
    return this.data.get(0) > 0;
  }

}
