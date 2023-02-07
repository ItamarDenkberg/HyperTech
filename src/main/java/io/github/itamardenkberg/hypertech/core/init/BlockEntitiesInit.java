package io.github.itamardenkberg.hypertech.core.init;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.common.blockentities.CopperGeneratorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesInit {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HyperTech.MOD_ID);

	public static final RegistryObject<BlockEntityType<CopperGeneratorBlockEntity>> COPPER_GENERATOR = BLOCK_ENTITIES
			.register("copper_generator", () -> BlockEntityType.Builder
					.of(CopperGeneratorBlockEntity::new, BlockInit.COPPER_GENERATOR.get()).build(null));
}
