package io.github.itamardenkberg.hypertech.core.init;

import java.util.List;

import io.github.itamardenkberg.hypertech.HyperTech;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ConfiguredFeaturesInit {
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BAUXITE = FeatureUtils
			.createKey(HyperTech.MOD_ID + ":" + "ore_bauxite");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstrap) {
		var targetList = List.of(
				OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
						BlockInit.BAUXITE_ORE.get().defaultBlockState()),
				OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
						BlockInit.DEEPSLATE_BAUXITE_ORE.get().defaultBlockState()));
		FeatureUtils.register(bootstrap, ORE_BAUXITE, Feature.ORE, new OreConfiguration(targetList, 4));
	}
}
