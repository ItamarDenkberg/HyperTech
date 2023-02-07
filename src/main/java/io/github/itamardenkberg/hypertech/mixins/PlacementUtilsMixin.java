package io.github.itamardenkberg.hypertech.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import io.github.itamardenkberg.hypertech.world.placements.OrePlacements;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

@Mixin(PlacementUtils.class)
public class PlacementUtilsMixin {
	@Inject(method = "bootstrap", at = @At("HEAD"))
	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		OrePlacements.bootstrap(context);
	}
}
