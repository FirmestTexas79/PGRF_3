package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.function.Predicate;

/**
 * Representing algorithms capable of filling a polygon defined in a {@link RasterImage} with pixels of the given type
 * @param <P> pixel type
 */
public interface SeedFill<P> {

    /**
     * Fills a polygon defined by a seed address and isInArea function with a new pixel value
     * @param image {@link RasterImage} used for filling
     * @param c seed column address
     * @param r seed row address
     * @param pixelValue pixel value used for filling
     * @param isInArea {@link Predicate} indicating whether seed lies in an area
     */
    void fill(@NotNull RasterImage<P> image, int c, int r, @NotNull P pixelValue, @NotNull Predicate<P> isInArea);

}
