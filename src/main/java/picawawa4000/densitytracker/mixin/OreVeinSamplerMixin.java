package picawawa4000.densitytracker.mixin;

import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.gen.OreVeinSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import picawawa4000.densitytracker.DensityTracker;

@Mixin(OreVeinSampler.class)
public abstract class OreVeinSamplerMixin {
    /*
    @Inject(method="method_40547(Lnet/minecraft/world/gen/densityfunction/DensityFunction;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/RandomSplitter;Lnet/minecraft/world/gen/densityfunction/DensityFunction;Lnet/minecraft/world/gen/densityfunction/DensityFunction;Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;)Lnet/minecraft/block/BlockState;", at=@At(value="HEAD"))
    private static void argCapture(DensityFunction veinToggle, BlockState state, RandomSplitter splitter, DensityFunction veinRidged, DensityFunction veinGap, DensityFunction.NoisePos pos, CallbackInfoReturnable<BlockState> cir) {
        if (DensityTracker.NUM_OUTPUTS.get() >= DensityTracker.MAX_OUTPUTS) return;
        try {
            DensityTracker.OUTPUT.write(String.format("[%d, %d, %d] Vein Toggle: %f, Vein Ridged: %f, Vein Gap: %f, Positional Random: %f\n", pos.blockX(), pos.blockY(), pos.blockZ(), veinToggle.sample(pos), veinRidged.sample(pos), veinGap.sample(pos), splitter.split(pos.blockX(), pos.blockY(), pos.blockZ()).nextFloat()));
            if (DensityTracker.NUM_OUTPUTS.incrementAndGet() == DensityTracker.MAX_OUTPUTS) {
                DensityTracker.OUTPUT.close();
                return;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */
}
