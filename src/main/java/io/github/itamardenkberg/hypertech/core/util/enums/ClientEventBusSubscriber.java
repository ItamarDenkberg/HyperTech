package io.github.itamardenkberg.hypertech.core.util.enums;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.core.init.ItemInit;
import io.github.itamardenkberg.hypertech.core.init.MenuTypesInit;
import io.github.itamardenkberg.hypertech.core.interfaces.CopperGeneratorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
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

	@SubscribeEvent
	public void buildContents(CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation(HyperTech.MOD_ID, "main"),
				builder -> builder.title(Component.translatable("item_group." + HyperTech.MOD_ID + ".main"))
						.icon(() -> new ItemStack(ItemInit.COPPER_GENERATOR.get()))
						.displayItems((enabledFlag, output, hasPermissions) -> {
							output.accept(ItemInit.BAUXITE_ORE.get());
							output.accept(ItemInit.DEEPSLATE_BAUXITE_ORE.get());
							output.accept(ItemInit.RAW_BAUXITE.get());
							output.accept(ItemInit.RAW_BAUXITE_BLOCK.get());
							output.accept(ItemInit.BAUXITE_DUST.get());
							output.accept(ItemInit.ALUMINIUM_BLOCK.get());
							output.accept(ItemInit.ALUMINIUM_BLOCK.get());
							output.accept(ItemInit.ALUMINIUM_INGOT.get());
							output.accept(ItemInit.ALUMINIUM_DUST.get());
							output.accept(ItemInit.ALUMINIUM_NUGGET.get());
							output.accept(ItemInit.COPPER_DUST.get());
							output.accept(ItemInit.GOLD_DUST.get());
							output.accept(ItemInit.IRON_DUST.get());
							output.accept(ItemInit.DIAMOND_DUST.get());
							output.accept(ItemInit.NETHERITE_DUST.get());
							output.accept(ItemInit.COPPER_GENERATOR.get());
						}));
	}
}
