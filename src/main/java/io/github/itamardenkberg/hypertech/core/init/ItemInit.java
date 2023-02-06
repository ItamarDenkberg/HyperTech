package io.github.itamardenkberg.hypertech.core.init;

import io.github.itamardenkberg.hypertech.HyperTech;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HyperTech.MOD_ID);

	public static final RegistryObject<Item> RAW_BAUXITE = ITEMS.register("raw_bauxite",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> BAUXITE_DUST = ITEMS.register("bauxite_dust",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> ALUMINIUM_NUGGET = ITEMS.register("aluminium_nugget",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> ALUMINIUM_DUST = ITEMS.register("aluminium_dust",
			() -> new Item(new Item.Properties()));

	// Blocks

	public static final RegistryObject<BlockItem> BAUXITE_ORE = ITEMS.register("bauxite_ore",
			() -> new BlockItem(BlockInit.BAUXITE_ORE.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> DEEPSLATE_BAUXITE_ORE = ITEMS.register("deepslate_bauxite_ore",
			() -> new BlockItem(BlockInit.DEEPSLATE_BAUXITE_ORE.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> RAW_BAUXITE_BLOCK = ITEMS.register("raw_bauxite_block",
			() -> new BlockItem(BlockInit.RAW_BAUXITE_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> ALUMINIUM_BLOCK = ITEMS.register("aluminium_block",
			() -> new BlockItem(BlockInit.ALUMINIUM_BLOCK.get(), new Item.Properties()));
}
