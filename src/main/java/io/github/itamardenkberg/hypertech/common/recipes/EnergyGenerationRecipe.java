package io.github.itamardenkberg.hypertech.common.recipes;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;

import io.github.itamardenkberg.hypertech.HyperTech;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class EnergyGenerationRecipe extends AbstractEnergyGenerationRecipe {
	private final Ingredient input;
	private final int output;
	// private final NonNullList<Ingredient> items;

	protected EnergyGenerationRecipe(ResourceLocation id, Ingredient input, int output) {
		super(id);
		this.input = input;
		if (output <= 0F) {
			throw new IllegalArgumentException("Energy needs to be greater than 0");
		}

		this.output = output;
	}

	@Override
	public boolean matches(Container container, Level world) {
		if (world.isClientSide) {
			return false;
		}

		return input.test(container.getItem(0));
	}

//	@Override
//	public ItemStack assemble(SimpleContainer container) {
//		return output;
//	}

	public Ingredient getInput() {
		return input;
	}

	public int getOutput() {
		return output;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<EnergyGenerationRecipe> {
		private Type() {

		}

		public static final Type INSTANCE = new Type();
		public static final String ID = "energy_generation";
	}

	public static class Serializer implements RecipeSerializer<EnergyGenerationRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(HyperTech.MOD_ID, "energy_generation");

		@Override
		public EnergyGenerationRecipe fromJson(ResourceLocation id, JsonObject recipe) {
			int output = GsonHelper.getAsInt(recipe, "output");
			Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonArray(recipe, "ingredient"));

			return new EnergyGenerationRecipe(id, input, output);
		}

		@Override
		public @Nullable EnergyGenerationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
			Ingredient input = Ingredient.fromNetwork(buffer);
			int output = buffer.readInt();

			return new EnergyGenerationRecipe(id, input, output);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, EnergyGenerationRecipe recipe) {
			buffer.writeInt(recipe.getIngredients().size());

			for (Ingredient ing : recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(recipe.getResultItem(), false);
		}
	}
}
