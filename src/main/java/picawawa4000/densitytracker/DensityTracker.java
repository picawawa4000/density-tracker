package picawawa4000.densitytracker;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import picawawa4000.densitytracker.util.LoggerDensityFunction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.serialization.MapCodec;

public class DensityTracker implements ModInitializer {
	public static final String MOD_ID = "density-tracker";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static FileWriter OUTPUT;
	private static AtomicInteger NUM_OUTPUTS = new AtomicInteger();
	private static final int MAX_OUTPUTS = 16384;

	@Override
	public void onInitialize() {
		DensityTracker.LOGGER.info("INIT!");

		File fileHandle = FabricLoader.getInstance().getGameDir().resolve(Path.of("density_tracker_log.log")).toFile();
		try {
			DensityTracker.OUTPUT = new FileWriter(fileHandle);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// register the density function type so that I can (hopefully) use it in datapacks
		Registry<MapCodec<? extends DensityFunction>> densityFunctionTypesRegistry = Registries.DENSITY_FUNCTION_TYPE;
		Registry.register(densityFunctionTypesRegistry, "log", LoggerDensityFunction.CODEC_HOLDER.codec());
	}

	public static void log(String msg) {
		try {
			if (DensityTracker.NUM_OUTPUTS.getAndIncrement() >= DensityTracker.MAX_OUTPUTS) {
				DensityTracker.OUTPUT.close();
				return;
			}
			//DensityTracker.LOGGER.info(String.format("%d", DensityTracker.NUM_OUTPUTS.get()));
			DensityTracker.OUTPUT.write(msg);
			DensityTracker.OUTPUT.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}