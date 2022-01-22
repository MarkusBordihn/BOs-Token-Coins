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

package de.markusbordihn.tokencoins.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import de.markusbordihn.tokencoins.Constants;
import de.markusbordihn.tokencoins.menu.CoinPressMenu;

public class CoinPressScreen extends AbstractContainerScreen<CoinPressMenu> {
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(Constants.MOD_ID, "textures/container/coin_press_gui.png");

	public CoinPressScreen(CoinPressMenu coinPressMenu, Inventory inventory, Component component) {
		super(coinPressMenu, inventory, component);
	}

	@Override
	public void init() {
		super.init();
		this.imageHeight = 180;
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
		this.topPos = (this.height - this.imageHeight) / 2;
		this.inventoryLabelY = this.imageHeight - 93;
	}

	@Override
	public void render(PoseStack poseStack, int x, int y, float partialTicks) {
		this.renderBackground(poseStack);
		super.render(poseStack, x, y, partialTicks);
		this.renderTooltip(poseStack, x, y);
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int leftPos = this.leftPos;
		int topPos = this.topPos;

		// Cut main screen
		this.blit(poseStack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

		// Lit Progress
		if (this.menu.isLit()) {
			int litProgress = this.menu.getLitProgress();
			int litProgressLeftPos = leftPos + 10;
			int litProgressTopPos = topPos + 16;
			int litProgressRelativeTop = (46 - litProgress);
			this.blit(poseStack, litProgressLeftPos, litProgressTopPos + litProgressRelativeTop, 10,
					190 + litProgressRelativeTop, 14, litProgress);
		}

		// Burn Progress
		int burnProgress = this.menu.getBurnProgress();
		int burnProgressLeftPos = leftPos + 97;
		int burnProgressTopPos = topPos + 42;
		this.blit(poseStack, burnProgressLeftPos, burnProgressTopPos, 176, 14, burnProgress + 1, 16);
	}

}
