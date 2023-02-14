package io.github.itamardenkberg.hypertech.core.integrations;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.common.recipes.EnergyGenerationRecipe;
import io.github.itamardenkberg.hypertech.core.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class EnergyGenerationRecipeCategory implements IRecipeCategory<EnergyGenerationRecipe> {
	public final static ResourceLocation UID = new ResourceLocation(HyperTech.MOD_ID, "energy_generation");
	public final static ResourceLocation TEXTURE = new ResourceLocation(HyperTech.MOD_ID,
			"textures/gui/container/copper_generator.png");

	private final IDrawable background;
	private final IDrawable icon;

	public EnergyGenerationRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 40, 5, 107, 76);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(BlockInit.COPPER_GENERATOR.get()));
	}

	@Override
	public RecipeType<EnergyGenerationRecipe> getRecipeType() {
		return JEIHyperTechPlugin.ENERGY_GENERATION_TYPE;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("jei.title." + HyperTech.MOD_ID + ".energy_generation");
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, EnergyGenerationRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 16, 29).addIngredients(recipe.getInput());
	}
}
