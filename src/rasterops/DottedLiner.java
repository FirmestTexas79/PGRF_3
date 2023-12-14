package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

/**
 * Represent an algorithms for drawing dotted lines on a given {@link rasterdata.RasterImage} with pixels of the given type
 * @param <P> pixel type
 */
public class DottedLiner<P> implements Liner<P>{

    private final double length;
    private final double spaceLength;

    public DottedLiner(double length, double spaceLength) {
        this.length = length;
        this.spaceLength = spaceLength;
    }

    @Override
    public void drawLine(@NotNull RasterImage<P> img, int c1, int r1, int c2, int r2, @NotNull P pixelValue) {
        // TODO implement rasterization of a dotted line
        final double k = (r2 - r1) / (double) (c2 - c1);
        final double q = r1 - k * c1;
        int g=0;
        if (Math.abs(r2 - r1) < Math.abs(c2 - c1)) { //iterate over c
            if (c1 > c2 ) { // swap start and end points
                for (int c = c2; c <= c1; c++){
                    c+=2;
                    int r = (int) Math.round(k * c + q);
                    img.setPixel(c, r, pixelValue);
                }
            }
            for (int c = c1; c <= c2; c++){
                c+=2;
                int r = (int) Math.round(k * c + q);
                img.setPixel(c, r, pixelValue);
            }
            // iterating over c and calculating r
        } else { // iterate over r
            if (r1 > r2) { // swap start and end points
                for (int r = r2; r <= r1; r++){
                    r+=2;
                    int c = (int) Math.round((r-q)/k);
                    img.setPixel(c, r, pixelValue);
                }
            }
            for (int r = r1; r <= r2; r++){
                r+=2;
                int c = (int) Math.round((r-q)/k);
                img.setPixel(c, r, pixelValue);
            }
            // iterating over r and calculating c
        }
    }
}

