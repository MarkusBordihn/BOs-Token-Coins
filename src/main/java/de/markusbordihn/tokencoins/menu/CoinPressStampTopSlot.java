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

import de.markusbordihn.tokencoins.item.coinstamp.CoinStampItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CoinPressStampTopSlot extends Slot {

  public CoinPressStampTopSlot(Container container, int p_40224_, int p_40225_, int p_40226_) {
    super(container, p_40224_, p_40225_, p_40226_);
  }

  @Override
  public boolean mayPlace(ItemStack itemStack) {
    ItemStack partnerItem = this.container.getItem(CoinPressMenu.STAMP_BOTTOM_SLOT);
    return CoinPressMenu.isCoinStamp(itemStack)
        && (partnerItem.isEmpty() || (((CoinStampItem) partnerItem.getItem())
            .getStampMotive() == ((CoinStampItem) itemStack.getItem()).getStampMotive()));
  }

}
