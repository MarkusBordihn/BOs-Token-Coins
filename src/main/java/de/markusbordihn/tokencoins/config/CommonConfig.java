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

package de.markusbordihn.tokencoins.config;

import org.apache.commons.lang3.tuple.Pair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

import de.markusbordihn.tokencoins.Constants;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CommonConfig {

  private static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final ForgeConfigSpec commonSpec;
  public static final Config COMMON;

  protected CommonConfig() {}

  static {
    com.electronwill.nightconfig.core.Config.setInsertionOrderPreserved(true);
    final Pair<Config, ForgeConfigSpec> specPair =
        new ForgeConfigSpec.Builder().configure(Config::new);
    commonSpec = specPair.getRight();
    COMMON = specPair.getLeft();
    log.info("{} Common config ...", Constants.LOG_REGISTER_PREFIX);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
  }

  public static class Config {

    public final ForgeConfigSpec.IntValue cookieTokenCoinValue;
    public final ForgeConfigSpec.IntValue copperTokenCoinValue;
    public final ForgeConfigSpec.IntValue goldTokenCoinValue;
    public final ForgeConfigSpec.IntValue ironTokenCoinValue;
    public final ForgeConfigSpec.IntValue steelTokenCoinValue;
    public final ForgeConfigSpec.IntValue netheriteTokenCoinValue;

    public final ForgeConfigSpec.BooleanValue enableTokenCoinStacks;

    public final ForgeConfigSpec.BooleanValue enablePiggyBankEffects;

    Config(ForgeConfigSpec.Builder builder) {
      builder.comment("Token Coin's (General configuration)");

      builder.push("Token Coins Values");
      cookieTokenCoinValue = builder.comment("Defines the cookie token coin value.")
          .defineInRange("cookieTokenCoinValue", 0, 0, 1000);
      copperTokenCoinValue = builder.comment("Defines the copper token coin value.")
          .defineInRange("copperTokenCoinValue", 1, 0, 1000);
      goldTokenCoinValue = builder.comment("Defines the gold token coin value.")
          .defineInRange("goldTokenCoinValue", 10, 0, 1000);
      ironTokenCoinValue = builder.comment("Defines the iron token coin value.")
          .defineInRange("ironTokenCoinValue", 25, 0, 1000);
      steelTokenCoinValue = builder.comment("Defines the steel token coin value.")
          .defineInRange("steelTokenCoinValue", 50, 0, 1000);
      netheriteTokenCoinValue = builder.comment("Defines the netherite token coin value.")
          .defineInRange("netheriteTokenCoinValue", 1000, 0, 1000);
      builder.pop();

      builder.push("Token Coins Stacks");
      enableTokenCoinStacks = builder.comment("Enable/Disable the token coin stacks feature.")
          .define("enableTokenCoinStacks", true);
      builder.pop();

      builder.push("Piggy Bank");
      enablePiggyBankEffects = builder.comment("Enable/Disable the piggy bank effects with special items.")
          .define("enablePiggyBankEffects", true);
      builder.pop();
    }
  }
}
