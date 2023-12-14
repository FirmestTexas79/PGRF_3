package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P>{

    @Override
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

            for (int y = Math.max(r1,0); y <= Math.min(r2, img.getHeight()); y++) {
                img.setPixel(c1, y, pixelValue);
            }}else{
            if (Math.abs(r2 - r1) < Math.abs(c2 - c1)) { //iterate over c
                if (c1 > c2) { // swap start and end points
                    for (int c = Math.max(c2,0); c <= Math.min(c1, img.getWidth()); c++){
                        int r = (int) Math.round(k * c + q);
                        img.setPixel(c, r, pixelValue);
                    }
                }
                for (int c = Math.max(c1,0); c <= Math.min(c2, img.getWidth()); c++){
                    int r = (int) Math.round(k * c + q);
                    img.setPixel(c, r, pixelValue);
                }
                // iterating over c and calculating r
            } else { // iterate over r
                if (r1 > r2) { // swap start and end points
                    for (int r = Math.max(r2,0); r <= Math.min(r1, img.getHeight()); r++){
                        int c = (int) Math.round((r-q)/k);
                        img.setPixel(c, r, pixelValue);
                    }
                }
                for (int r = Math.max(r1,0); r <= Math.min(r2, img.getHeight()); r++){
                    int c = (int) Math.round((r-q)/k);
                    img.setPixel(c, r, pixelValue);
                }
                // iterating over r and calculating c
            }}
    }
}
