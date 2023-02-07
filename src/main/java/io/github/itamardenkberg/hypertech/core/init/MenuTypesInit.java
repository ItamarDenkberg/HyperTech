package io.github.itamardenkberg.hypertech.core.init;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.core.interfaces.CopperGeneratorMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesInit {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
			HyperTech.MOD_ID);

	public static final RegistryObject<MenuType<CopperGeneratorMenu>> COPPER_GENERATOR_MENU = register(
			CopperGeneratorMenu::new, "copper_generator_menu");

	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(IContainerFactory<T> factory,
			String name) {
		return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
	}
}
