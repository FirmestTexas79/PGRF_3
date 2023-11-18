package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public interface Liner<P> {
    void drawLine(final @NotNull RasterImage<P> img, final int c1,
                  final int r1, final int c2, final int r2, final @NotNull P pixelValue);
}
