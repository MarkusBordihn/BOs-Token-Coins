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
    log.info("{} Coins common config ...", Constants.LOG_REGISTER_PREFIX);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
  }

  public static class Config {

    public final ForgeConfigSpec.IntValue cookieTokenCoinValue;
    public final ForgeConfigSpec.IntValue copperTokenCoinValue;
    public final ForgeConfigSpec.IntValue goldTokenCoinValue;
    public final ForgeConfigSpec.IntValue ironTokenCoinValue;
    public final ForgeConfigSpec.IntValue steelTokenCoinValue;
    public final ForgeConfigSpec.IntValue netheriteTokenCoinValue;

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
    }
  }
}
