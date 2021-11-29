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

import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.item.ModItems;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;
import de.markusbordihn.tokencoins.recipe.CoinPressRecipe;
import de.markusbordihn.tokencoins.recipe.ModRecipes;

@Mod(Constants.MOD_ID)
public class TokenCoins {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public TokenCoins() {
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(this::setupCommon);
    modEventBus.addListener(ClientSetup::new);
    modEventBus.addGenericListener(MenuType.class, this::registerContainers);

    log.info("{} Items ...", Constants.LOG_REGISTER_PREFIX);
    ModItems.ITEMS.register(modEventBus);

    log.info("{} Blocks ...", Constants.LOG_REGISTER_PREFIX);
    ModBlocks.BLOCKS.register(modEventBus);

    log.info("{} Block Entities ...", Constants.LOG_REGISTER_PREFIX);
    ModBlocks.ENTITIES.register(modEventBus);

    log.info("{} Recipe Serializers ...", Constants.LOG_REGISTER_PREFIX);
    ModRecipes.RECIPES_SERIALIZERS.register(modEventBus);
  }

  private void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
    log.info("{} Container ...", Constants.LOG_REGISTER_PREFIX);
    event.getRegistry()
        .registerAll(new MenuType<>(CoinPressMenu::new).setRegistryName(Constants.COIN_PRESS));
  }

  private void setupCommon(final FMLCommonSetupEvent event) {
    log.info("{} Recipe Type ...", Constants.LOG_REGISTER_PREFIX);
    Registry.register(Registry.RECIPE_TYPE, Constants.COIN_PRESS_RECIPE_ID,
        new RecipeType<CoinPressRecipe>() {
          public String toString() {
            return Constants.COIN_PRESS;
          }
        });
  }
}
