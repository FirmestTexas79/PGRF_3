package rasterops;


import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;
import transform.Mat3;


/**
 * Represents and algorithms for drawing a {@link Polygon2D} on a {@link RasterImage} with pixels of a given pixel type
 * @param <P> pixel type
 */
public class Polygoner2D<P> {


    public void drawPolygon(final @NotNull RasterImage<P> image, final @NotNull P pixel,
                            final @NotNull Liner<P> liner, final @NotNull Polygon2D polygon){
        int listSize = polygon.getPoints().size();
        for (int i = 0; i < listSize; i++) {
            int next = (i + 1) % listSize;
            liner.drawLine(image, (int)polygon.getPoint(i).getX(),(int)polygon.getPoint(i).getY(),
                    (int) polygon.getPoint(next).getX(),(int)polygon.getPoint(next).getY(),pixel);
        }
    }
}
