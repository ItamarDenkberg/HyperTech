package io.github.itamardenkberg.hypertech.core.init;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.common.recipes.EnergyGenerationRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipesInit {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, HyperTech.MOD_ID);

	public static final RegistryObject<RecipeSerializer<EnergyGenerationRecipe>> ENERGY_GENERATION_SERIALIZER = RECIPE_SERIALIZERS
			.register("energy_generation", () -> EnergyGenerationRecipe.Serializer.INSTANCE);
}
