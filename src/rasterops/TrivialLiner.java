package rasterops;
import org.jetbrains.annotations.NotNull;

import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P>{
    public void drawLine(final @NotNull RasterImage<P> img, final int c1, int r1,
                         final int c2, int r2, final @NotNull P pixelValue) {
        final double k = (r2 - r1) / (double) (c2 - c1);
        final double q = r1 - k * c1;


        if (c1 == c2) {
            if (r1 > r2) {
                int y = r1;
                r1 = r2;
                r2 = y;
            }

            for (int y = r1; y <= r2; y++) {
                img.setPixel(c1, y, pixelValue);
            }}else{
            if (Math.abs(r2 - r1) < Math.abs(c2 - c1)) { //iterate over c
                if (c1 > c2) { // swap start and end points
                    for (int c = c2; c <= c1; c++){
                        int r = (int) Math.round(k * c + q);
                        img.setPixel(c, r, pixelValue);
                    }
                }
                for (int c = c1; c <= c2; c++){
                    int r = (int) Math.round(k * c + q);
                    img.setPixel(c, r, pixelValue);
                }
                // iterating over c and calculating r
            } else { // iterate over r
                if (r1 > r2) { // swap start and end points
                    for (int r = r2; r <= r1; r++){
                        int c = (int) Math.round((r-q)/k);
                        img.setPixel(c, r, pixelValue);
                    }
                }
                for (int r = r1; r <= r2; r++){
                    int c = (int) Math.round((r-q)/k);
                    img.setPixel(c, r, pixelValue);
                }
                // iterating over r and calculating c
            }}
    }
}
