package io.github.itamardenkberg.hypertech;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import io.github.itamardenkberg.hypertech.core.init.BlockInit;
import io.github.itamardenkberg.hypertech.core.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HyperTech.MOD_ID)
public class HyperTech {
	public static final String MOD_ID = "hypertech";
	public static final Logger LOGGER = LogUtils.getLogger();

	public HyperTech() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
	}
}