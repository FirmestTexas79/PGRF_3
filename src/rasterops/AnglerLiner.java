package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.awt.*;

public class AnglerLiner <P> {
    public void drawLine(@NotNull RasterImage<P> img, final @NotNull Liner<P> liner, int c1, int r1, int c2, int r2, @NotNull P pixelValue) {
        double startX = (c1 + c2) / 2.0;
        double startY = (r1 + r2) / 2.0;
        int angle=90;
        double endX = c2  * (int)Math.cos(Math.toRadians(angle));
        double endY = r2  * (int)Math.sin(Math.toRadians(angle));

        findAnglededPoint(c1, r1, c2, r2,(int)endY);
        liner.drawLine(img,(int)startX,(int)startY,(int)endX,(int)endY,pixelValue);
        liner.drawLine(img,(int)startX,(int)startY,(int)endX,(int)endY,pixelValue);
    }
    public Point getMidPoint(final @NotNull Point point1, final @NotNull Point point2) {
        double midX = (point1.getX() + point2.getX()) / 2.0;
        double midY = (point1.getY() + point2.getY()) / 2.0;

        return new Point((int)midX,(int) midY);
    }
    public Point findAnglededPoint(final int x1, final int y1, final int x2, final int y2, final int length) {
        double k, q, y;
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point midPoint = getMidPoint(p1, p2);

        if(y1 != y2) {
            if (y1 - y2 > -8 && y1 - y2 < 8) {
                k = -(x2 - x1) / (double) (y2 - y1);
                q = midPoint.getY() - k * midPoint.getX();
                y = k * (midPoint.getX() + length / 8.0) + q;
                return new Point((int)(midPoint.getX() + length / 8.0),(int) y);
            }
            k = -(x2 - x1) / (double) (y2 - y1);
            q = midPoint.getY() - k * midPoint.getX();
            y = k * (midPoint.getX() + length) + q;
        } else {
            y = midPoint.getX() + length * 20;
        }

        return new Point((int)midPoint.getX() + length,(int) y);

    }
}
