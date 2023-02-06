package io.github.itamardenkberg.hypertech.core.init;

import java.util.List;

import io.github.itamardenkberg.hypertech.HyperTech;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeaturesInit {
	public static final ResourceKey<PlacedFeature> ORE_BAUXITE = PlacementUtils
			.createKey(HyperTech.MOD_ID + ":" + "ore_bauxite");

	public static void placedBootstrap(BootstapContext<PlacedFeature> bootstrap) {
		PlacementUtils.register(bootstrap, ORE_BAUXITE,
				bootstrap.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeaturesInit.ORE_BAUXITE),
				List.of(CountPlacement.of(8), InSquarePlacement.spread(),
						HeightRangePlacement.triangle(VerticalAnchor.absolute(-25), VerticalAnchor.absolute(63)),
						BiomeFilter.biome()));
	}
}
