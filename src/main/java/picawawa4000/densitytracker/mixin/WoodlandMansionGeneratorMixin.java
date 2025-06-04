package picawawa4000.densitytracker.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.structure.WoodlandMansionGenerator;
import net.minecraft.util.Pair;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.Random;
import picawawa4000.densitytracker.DensityTracker;

@Mixin(WoodlandMansionGenerator.MansionParameters.class)
public abstract class WoodlandMansionGeneratorMixin {
    /*@Redirect(method = "updateRoomFlags(Lnet/minecraft/structure/WoodlandMansionGenerator$FlagMatrix;Lnet/minecraft/structure/WoodlandMansionGenerator$FlagMatrix;)V", at = @At(value="INVOKE", target="Lnet/minecraft/util/Util;shuffle(Ljava/util/List;Lnet/minecraft/util/math/random/Random;)V"))
    public <T> void shufflePrinter(List<T> list, Random random) {
        DensityTracker.LOGGER.info(String.format("Ingoing seed: %d", ((CheckedRandom)random).seed.get()));

        Util.shuffle(list, random);

        // probably unsafe but whatever
        List<Pair<Integer, Integer>> castedList = (List<Pair<Integer, Integer>>)list;
        for (Pair<Integer, Integer> pos : castedList) {
            DensityTracker.LOGGER.info(String.format("{%d, %d}", pos.getLeft(), pos.getRight()));
        }
        DensityTracker.LOGGER.info("END");
    }*/
}
