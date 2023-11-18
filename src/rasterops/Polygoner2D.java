package rasterops;

import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class Polygoner2D<P> {
    public void drawPolygon(final @NotNull RasterImage<P> image, final @NotNull P pixel,
                            final @NotNull Liner<P> liner, final @NotNull Polygon2D polygon){
        int listSize = polygon.getPoints().size();
        for (int i = 0; i < listSize; i++) {
            int next = (i + 1) % listSize;
            liner.drawLine(image,polygon.getPoint(i).getC1(),polygon.getPoint(i).getR1(),
                    polygon.getPoint(next).getC1(),polygon.getPoint(next).getR1(),pixel);
        }



    }
}
