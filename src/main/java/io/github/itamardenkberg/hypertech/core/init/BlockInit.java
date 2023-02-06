package io.github.itamardenkberg.hypertech.core.init;

import io.github.itamardenkberg.hypertech.HyperTech;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			HyperTech.MOD_ID);

	public static final RegistryObject<Block> BAUXITE_ORE = BLOCKS.register("bauxite_ore",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

	public static final RegistryObject<Block> DEEPSLATE_BAUXITE_ORE = BLOCKS.register("deepslate_bauxite_ore",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

	public static final RegistryObject<Block> RAW_BAUXITE_BLOCK = BLOCKS.register("raw_bauxite_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));

	public static final RegistryObject<Block> ALUMINIUM_BLOCK = BLOCKS.register("aluminium_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
}
