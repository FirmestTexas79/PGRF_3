package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.function.Predicate;

public class SeedFill4<P> implements SeedFill<P> {


    @Override
    public void fill(@NotNull RasterImage<P> image, int c, int r,
                     @NotNull P pixelValue, @NotNull Predicate<P> isInArea) {

        // Get pixel value at (c, r)
        // Test if pixel lies in area using isInArea Predicate
        // Set pixel to it's new value
        // Recursively repeat for it's neighborhood
        image.getPixel(c, r).ifPresent(p -> {
            if(isInArea.test(p)) {
                image.setPixel(c, r, pixelValue);
                fill(image, c+1, r, pixelValue, isInArea);
                fill(image, c, r+1, pixelValue, isInArea);
                fill(image, c-1, r, pixelValue, isInArea);
                fill(image, c, r-1, pixelValue, isInArea);


            }
        });


    }
}
