package picawawa4000.densitytracker.mixin;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import picawawa4000.densitytracker.DensityTracker;
import picawawa4000.densitytracker.util.LoggerDensityFunction;

@Mixin(DensityFunctions.class)
@Debug(export=true)
public abstract class DensityFunctionsMixin {
    @Redirect(
        method="registerSlopedCheeseFunction(Lnet/minecraft/registry/Registerable;Lnet/minecraft/registry/RegistryEntryLookup;Lnet/minecraft/world/gen/densityfunction/DensityFunction;Lnet/minecraft/registry/RegistryEntry;Lnet/minecraft/registry/RegistryEntry;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/RegistryKey;Z)V",
        at=@At(
            value="INVOKE",
            target="Lnet/minecraft/world/gen/densityfunction/DensityFunctions;createInitialDensityFunction(Lnet/minecraft/world/gen/densityfunction/DensityFunction;Lnet/minecraft/world/gen/densityfunction/DensityFunction;)Lnet/minecraft/world/gen/densityfunction/DensityFunction;"
        )
    )
    public static DensityFunction attachSlopedCheeseLogger(DensityFunction initialDensity, DensityFunction baseOverworldNoise) {
        DensityTracker.LOGGER.info("Creating sloped cheese logger...");
        return new LoggerDensityFunction("Sloped Cheese", DensityFunctionTypes.add(initialDensity, baseOverworldNoise));
    }

    @Inject(
        method="createSurfaceNoiseRouter(Lnet/minecraft/registry/RegistryEntryLookup;Lnet/minecraft/registry/RegistryEntryLookup;ZZ)Lnet/minecraft/world/gen/noise/NoiseRouter;",
        at=@At(
            value="HEAD"
        )
    )
    public static void logMessage(CallbackInfoReturnable<?> cir) {
        DensityTracker.LOGGER.info("Creating surface noise router...");
    }
}
