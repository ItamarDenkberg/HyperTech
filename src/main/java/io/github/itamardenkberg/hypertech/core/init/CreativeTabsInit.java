package io.github.itamardenkberg.hypertech.core.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabsInit {
	public static final CreativeModeTab HYPERTECH_MAIN = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
			.title(Component.translatable("itemGroup.hypertech_main")).icon(() -> {
				return new ItemStack(BlockInit.BAUXITE_ORE.get());
			}).displayItems((flag, output, bool) -> {
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
			}).build();
}
