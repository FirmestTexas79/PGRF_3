package rasterops;

import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.awt.*;
import java.util.function.Predicate;

public interface ScanLine<P> {

    void fill(final @NotNull Polygon2D polygon2D,final @NotNull RasterImage<P> img,final @NotNull Polygoner2D <P> polygoner2D,final @NotNull P areaPixel,final @NotNull P polygonPixel,final @NotNull Liner<P> liner);
}
