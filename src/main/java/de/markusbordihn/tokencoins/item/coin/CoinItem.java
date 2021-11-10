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

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.tabs.TokenCoinsTab;

public class CoinItem extends Item {

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

  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable Level level,
      List<Component> tooltipList, TooltipFlag tooltipFlag) {
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + "coin_value", this.coinValue)
        .withStyle(ChatFormatting.YELLOW));
  }
}
