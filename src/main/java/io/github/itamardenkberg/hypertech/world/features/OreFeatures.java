package io.github.itamardenkberg.hypertech.world.features;

import java.util.List;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BAUXITE = FeatureUtils.createKey("ore_bauxite");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest ruletest1 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest ruletest2 = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		List<OreConfiguration.TargetBlockState> list = List.of(
				OreConfiguration.target(ruletest1, Blocks.IRON_ORE.defaultBlockState()),
				OreConfiguration.target(ruletest2, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState()));
		FeatureUtils.register(context, ORE_BAUXITE, Feature.ORE, new OreConfiguration(list, 9));
	}
}
