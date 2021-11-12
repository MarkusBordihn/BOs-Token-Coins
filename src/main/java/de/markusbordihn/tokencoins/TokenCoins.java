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
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.client.screen.CoinPressScreen;
import de.markusbordihn.tokencoins.item.ModItems;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;
import de.markusbordihn.tokencoins.recipe.CoinPressRecipe;
import de.markusbordihn.tokencoins.recipe.ModRecipes;

@Mod(Constants.MOD_ID)
public class TokenCoins {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public TokenCoins() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(MenuType.class,
        this::registerContainers);

    log.info("🪙 Register Bo's Token Coins Items ...");
    ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    log.info("🪙 Register Bo's Token Coins Blocks ...");
    ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

    log.info("🪙 Register Bo's Token Coins Blocks Entities ...");
    ModBlocks.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

    log.info("🪙 Register Bo's Token Coins Recipes Serializers ...");
    ModRecipes.RECIPES_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  private void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
    log.info("🪙 Register Bo's Token Coins Container ...");
    event.getRegistry()
        .registerAll(new MenuType<>(CoinPressMenu::new).setRegistryName(Constants.COIN_PRESS));
  }

  private void setupClient(final FMLClientSetupEvent event) {
    log.info("🪙 Register Bo's Token Coins Client setup ...");
    event.enqueueWork(() -> {
      // Coin Press UI screen
      MenuScreens.register(CoinPressMenu.TYPE, CoinPressScreen::new);

      // Coin Press Block
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.COIN_PRESS.get(), RenderType.cutout());

      // Piggy Banks
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIGGY_BANK_GHAST.get(), RenderType.cutout());
    });
  }

  private void setupCommon(final FMLCommonSetupEvent event) {
    log.info("🪙 Register Bo's Token Coins Recipe Type");
    Registry.register(Registry.RECIPE_TYPE, Constants.COIN_PRESS_RECIPE_ID,
        new RecipeType<CoinPressRecipe>() {
          public String toString() {
            return Constants.COIN_PRESS;
          }
        });
  }
}
