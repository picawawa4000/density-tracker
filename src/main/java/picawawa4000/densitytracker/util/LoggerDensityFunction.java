package picawawa4000.densitytracker.util;

import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import picawawa4000.densitytracker.DensityTracker;

public class LoggerDensityFunction implements DensityFunction {
    // What even is a codec holder?
    public static final CodecHolder<LoggerDensityFunction> CODEC_HOLDER = CodecHolder.of(DensityFunction.FUNCTION_CODEC.fieldOf("argument").xmap(input -> new LoggerDensityFunction("???", input), LoggerDensityFunction::getDelegate));

    private DensityFunction delegate;
    private String name;

    public LoggerDensityFunction(String name, DensityFunction delegate) {
        DensityTracker.LOGGER.info("Created LoggerDensityFunction " + name);
        this.delegate = delegate;
        this.name = name;
    }

    public DensityFunction getDelegate()  {
        return this.delegate;
    }

    @Override
    public double maxValue() {
        return this.delegate.maxValue();
    }

    @Override
    public double minValue() {
        return this.delegate.minValue();
    }

    @Override
    public void fill(double[] targets, DensityFunction.EachApplier eachApplier) {
        eachApplier.fill(targets, this);
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return LoggerDensityFunction.CODEC_HOLDER;
    }

    @Override
    public DensityFunction apply(DensityFunction.DensityFunctionVisitor visitor) {
        DensityTracker.LOGGER.info(String.format("LoggerDensityFunction %s applied to visitor!", this.name));
        return new LoggerDensityFunction(this.name, this.delegate.apply(visitor));
    }

    @Override
    public double sample(DensityFunction.NoisePos pos) {
        double value = this.delegate.sample(pos);
        DensityTracker.log(String.format("%s [%d, %d, %d] %f", this.name, pos.blockX(), pos.blockY(), pos.blockZ(), value));
        return value;
    }
}
