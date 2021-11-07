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

package de.markusbordihn.tokencoins.item;

import de.markusbordihn.tokencoins.tabs.TokenCoinsTab;
import net.minecraft.world.item.Item;

public class CoinStampItem extends Item {

  private CoinStampItemType.Material stampMaterial = CoinStampItemType.Material.COPPER;
  private CoinStampItemType.Motive stampMotive = CoinStampItemType.Motive.NONE;

  public CoinStampItem(CoinStampItemType.Material material) {
    super(getItemPropertiesPerMaterial(material, new Item.Properties()));
    this.stampMaterial = material;
  }

  public CoinStampItem(CoinStampItemType.Material material, CoinStampItemType.Motive motive) {
    super(getItemPropertiesPerMaterial(material, new Item.Properties()));
    this.stampMaterial = material;
    this.stampMotive = motive;
  }

  public CoinStampItem(CoinStampItemType.Material material, Item.Properties properties) {
    super(getItemPropertiesPerMaterial(material, properties));
    this.stampMaterial = material;
  }

  public CoinStampItemType.Material getStampMaterial() {
    return this.stampMaterial;
  }

  public CoinStampItemType.Motive getStampMotive() {
    return this.stampMotive;
  }

  public static Item.Properties getItemPropertiesPerMaterial(CoinStampItemType.Material material,
      Item.Properties properties) {
    int stampDurability = 8;
    switch (material) {
      case COPPER:
        stampDurability = 8;
        break;
      case GOLD:
        stampDurability = 16;
        break;
      case IRON:
        stampDurability = 32;
        break;
      case STEEL:
        stampDurability = 64;
        break;
      case NETHERITE:
        stampDurability = 128;
        break;
    }
    return properties.durability(stampDurability).tab(TokenCoinsTab.COIN_STAMPS);
  }

}
