package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class DottedLiner<P> implements Liner<P> {

    private final double length;
    private final double spaceLength;

    public DottedLiner(double length, double spaceLength) {
        this.length = length;
        this.spaceLength = spaceLength;
    }

    @Override
    public void drawLine(@NotNull RasterImage<P> img, int c1, int r1, int c2, int r2, @NotNull P pixelValue) {

        // Výpočet směrnice a y-ového posunu přímky
        final double k = (r2 - r1) / (double) (c2 - c1);
        final double q = r1 - k * c1;

        // Podmínka pro výběr směru kreslení (podle toho, zda je větší změna v ose x nebo y)
        if (Math.abs(r2 - r1) < Math.abs(c2 - c1)) {
            if (c1 > c2 ) {
                for (int c = c2; c <= c1; c += spaceLength) {
                    int r = (int) Math.round(k * c + q);
                    img.setPixel(c, r, pixelValue);
                }
            }
            for (int c = c1; c <= c2; c += spaceLength) {
                int r = (int) Math.round(k * c + q);
                img.setPixel(c, r, pixelValue);
            }

        } else {
            if (r1 > r2) {
                for (int r = r2; r <= r1; r += spaceLength) {
                    int c = (int) Math.round((r - q) / k);
                    img.setPixel(c, r, pixelValue);
                }
            }
            for (int r = r1; r <= r2; r += spaceLength) {
                int c = (int) Math.round((r - q) / k);
                img.setPixel(c, r, pixelValue);
            }
        }
    }
}
