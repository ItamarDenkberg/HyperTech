package io.github.itamardenkberg.hypertech.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import io.github.itamardenkberg.hypertech.world.features.OreFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

@Mixin(FeatureUtils.class)
public class FeatureUtilsMixin {
	@Inject(method = "bootstrap", at = @At("HEAD"))
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		OreFeatures.bootstrap(context);
	}
}
