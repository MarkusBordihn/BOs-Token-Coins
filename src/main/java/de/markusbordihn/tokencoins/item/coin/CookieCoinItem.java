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

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.event.server.ServerAboutToStartEvent;

import de.markusbordihn.tokencoins.item.TokenCoinType;
import de.markusbordihn.tokencoins.Constants;

@Mod.EventBusSubscriber
public class CookieCoinItem extends CoinItem {

  private static int coinValue = COMMON.cookieTokenCoinValue.get();

  @SubscribeEvent
  public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
    coinValue = COMMON.cookieTokenCoinValue.get();
    log.info("🪙 Cookie Token Coins has a value of {}", coinValue);
  }

  public CookieCoinItem() {
    super(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.COOKIE),
        TokenCoinType.Material.COOKIE, TokenCoinType.Motive.NONE);
  }

  public CookieCoinItem(TokenCoinType.Motive motive) {
    super(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.COOKIE),
        TokenCoinType.Material.COOKIE, motive);
  }

  public CookieCoinItem(TokenCoinType.Motive motive, RegistryObject<Block> block) {
    super(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.COOKIE),
        TokenCoinType.Material.COOKIE, motive, block);
  }

  @Override
  public int getValue() {
    return coinValue;
  }

  @Override
  public String getCoinDescription() {
    return Constants.TEXT_PREFIX + "cookie_coin_description";
  }

}
