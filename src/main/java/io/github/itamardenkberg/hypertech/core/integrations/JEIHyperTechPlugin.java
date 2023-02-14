package io.github.itamardenkberg.hypertech.core.integrations;

import java.util.List;
import java.util.Objects;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.common.recipes.EnergyGenerationRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIHyperTechPlugin implements IModPlugin {
	public static RecipeType<EnergyGenerationRecipe> ENERGY_GENERATION_TYPE = new RecipeType<>(
			EnergyGenerationRecipeCategory.UID, EnergyGenerationRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(HyperTech.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration
				.addRecipeCategories(new EnergyGenerationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		@SuppressWarnings("resource")
		RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<EnergyGenerationRecipe> recipesEG = manager.getAllRecipesFor(EnergyGenerationRecipe.Type.INSTANCE);
		registration.addRecipes(ENERGY_GENERATION_TYPE, recipesEG);
	}
}
