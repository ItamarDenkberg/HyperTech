package io.github.itamardenkberg.hypertech.core.util.enums;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.core.init.MenuTypesInit;
import io.github.itamardenkberg.hypertech.core.interfaces.CopperGeneratorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HyperTech.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(MenuTypesInit.COPPER_GENERATOR_MENU.get(), CopperGeneratorScreen::new);
	}
}
