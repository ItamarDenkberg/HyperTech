package io.github.itamardenkberg.hypertech.core.interfaces;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.itamardenkberg.hypertech.HyperTech;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CopperGeneratorScreen extends AbstractContainerScreen<CopperGeneratorMenu> {
	private static final ResourceLocation TEXUTRE = new ResourceLocation(HyperTech.MOD_ID,
			"textures/gui/container/copper_generator.png");

	public CopperGeneratorScreen(CopperGeneratorMenu menu, Inventory inventory, Component component) {
		super(menu, inventory, component);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXUTRE);
		int x = leftPos;
		int y = topPos;

		this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);
		renderProgress(stack, x, y);
	}

	private void renderProgress(PoseStack stack, int x, int y) {
		if (menu.isGenerating()) {
			blit(stack, x + 79, y + 34, 176, 0, menu.getScaledProgress() + 1, 17);
		}
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, delta);
		renderTooltip(stack, mouseX, mouseY);
	}
}
