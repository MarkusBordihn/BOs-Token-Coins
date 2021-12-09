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
public class CopperCoinItem extends CoinItem {

  private static int coinValue = COMMON.copperTokenCoinValue.get();

  @SubscribeEvent
  public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
    coinValue = COMMON.copperTokenCoinValue.get();
    log.info("🪙 Copper Token Coins has a value of {}", coinValue);
  }

  public CopperCoinItem() {
    super(TokenCoinType.Material.COPPER, TokenCoinType.Motive.NONE);
  }

  public CopperCoinItem(TokenCoinType.Motive motive) {
    super(TokenCoinType.Material.COPPER, motive);
  }

  public CopperCoinItem(TokenCoinType.Motive motive, RegistryObject<Block> block) {
    super(TokenCoinType.Material.COPPER, motive, block);
  }

  @Override
  public int getValue() {
    return coinValue;
  }

}
