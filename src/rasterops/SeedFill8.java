package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.function.Predicate;

public class SeedFill8<P> implements SeedFill<P> {
    @Override
    public void fill(@NotNull RasterImage<P> image, int c, int r, @NotNull P pixelValue, @NotNull Predicate<P> isInArea) {
        image.getPixel(c, r).ifPresent(p -> {
            if(isInArea.test(p)) {
                image.setPixel(c, r, pixelValue);
                fill(image, c+1, r, pixelValue, isInArea);
                fill(image, c, r+1, pixelValue, isInArea);
                fill(image, c-1, r, pixelValue, isInArea);
                fill(image, c, r-1, pixelValue, isInArea);
                fill(image, c+1, r+1, pixelValue, isInArea);
                fill(image, c+1, r-1, pixelValue, isInArea);
                fill(image, c-1, r+1, pixelValue, isInArea);
                fill(image, c-1, r-1, pixelValue, isInArea);
            }
        });
    }
}
