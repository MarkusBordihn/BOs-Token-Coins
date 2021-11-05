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

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CoinPressFuelSlot extends Slot {

  public CoinPressFuelSlot(Container p_39521_, int p_39522_, int p_39523_, int p_39524_) {
    super(p_39521_, p_39522_, p_39523_, p_39524_);
  }

  @Override
  public boolean mayPlace(ItemStack p_39526_) {
    return CoinPressMenu.isFuel(p_39526_) || isBucket(p_39526_);
  }

  @Override
  public int getMaxStackSize(ItemStack p_39528_) {
    return isBucket(p_39528_) ? 1 : super.getMaxStackSize(p_39528_);
  }

  public static boolean isBucket(ItemStack p_39530_) {
    return p_39530_.is(Items.BUCKET);
  }
}
