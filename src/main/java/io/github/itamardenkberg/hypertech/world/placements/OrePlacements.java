package io.github.itamardenkberg.hypertech.world.placements;

import java.util.List;

import io.github.itamardenkberg.hypertech.world.features.OreFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class OrePlacements {
	public static final ResourceKey<PlacedFeature> ORE_BAUXITE = PlacementUtils.createKey("ore_bauxite");

	private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
		return List.of(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
	}

	private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(count), modifier);
	}

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
		Holder<ConfiguredFeature<?, ?>> holder = holdergetter.getOrThrow(OreFeatures.ORE_BAUXITE);
		PlacementUtils.register(context, ORE_BAUXITE, holder, commonOrePlacement(8,
				HeightRangePlacement.triangle(VerticalAnchor.absolute(-25), VerticalAnchor.absolute(63))));
	}
}