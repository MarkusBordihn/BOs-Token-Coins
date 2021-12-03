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

import net.minecraft.world.level.block.Block;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.event.server.ServerAboutToStartEvent;

import de.markusbordihn.tokencoins.item.TokenCoinType;

@Mod.EventBusSubscriber
public class IronCoinItem extends CoinItem {

  private static int coinValue = COMMON.ironTokenCoinValue.get();

  @SubscribeEvent
  public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
    coinValue = COMMON.ironTokenCoinValue.get();
  }

  public IronCoinItem() {
    super(TokenCoinType.Material.IRON, TokenCoinType.Motive.NONE);
  }

  public IronCoinItem(TokenCoinType.Motive motive) {
    super(TokenCoinType.Material.IRON, motive);
  }

  public IronCoinItem(TokenCoinType.Motive motive, RegistryObject<Block> block) {
    super(TokenCoinType.Material.IRON, motive, block);
  }

  @Override
  public int getValue() {
    return coinValue;
  }

}
