package de.markusbordihn.tokencoins.integration;

import javax.annotation.Nonnull;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;

import net.minecraft.resources.ResourceLocation;

import de.markusbordihn.tokencoins.Constants;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

  private static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, "main");

  @Nonnull
  @Override
  public ResourceLocation getPluginUid() {
    return ID;
  }

}
