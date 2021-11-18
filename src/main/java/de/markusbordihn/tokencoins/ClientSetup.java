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

package de.markusbordihn.tokencoins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.client.screen.CoinPressScreen;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;

public class ClientSetup {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public ClientSetup(final FMLClientSetupEvent event) {
    log.info("🪙 Register Bo's Token Coins Client setup ...");
    event.enqueueWork(() -> {
      // Coin Press UI screen
      MenuScreens.register(CoinPressMenu.TYPE, CoinPressScreen::new);

      // Coin Press Block (transparent cutout)
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.COIN_PRESS.get(), RenderType.cutoutMipped());

      // Piggy Banks (transparent cutout)
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_GHAST.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_NOTE_BLOCK.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_PIG.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_SAFE.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_SKELETON.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_TNT.get(),
          RenderType.cutoutMipped());

      //@TemplateEntryPoint("Set Render Layer")

      // Creeper Coin Stack (transparent cutout)
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.COPPER_COIN_STACK_WITH_CREEPER.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLD_COIN_STACK_WITH_CREEPER.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.IRON_COIN_STACK_WITH_CREEPER.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_COIN_STACK_WITH_CREEPER.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.NETHERITE_COIN_STACK_WITH_CREEPER.get(),
          RenderType.cutoutMipped());

      // Coin Stack (transparent cutout)
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.COPPER_COIN_STACK.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLD_COIN_STACK.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.IRON_COIN_STACK.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.STEEL_COIN_STACK.get(),
          RenderType.cutoutMipped());
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.NETHERITE_COIN_STACK.get(),
          RenderType.cutoutMipped());
    });
  }
}
