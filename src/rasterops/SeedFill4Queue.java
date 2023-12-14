package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.function.Predicate;

public class SeedFill4Queue<P> implements SeedFill<P>{
    @Override
    public void fill(@NotNull RasterImage<P> image, int c, int r, @NotNull P pixelValue, @NotNull Predicate<P> isInArea) {


    }
}
