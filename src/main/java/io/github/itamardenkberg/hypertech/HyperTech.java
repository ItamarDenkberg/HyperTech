package io.github.itamardenkberg.hypertech;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HyperTech.MODID)
public class HyperTech
{
    public static final String MODID = "hypertech";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HyperTech()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
