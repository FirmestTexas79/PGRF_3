package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.awt.*;

public class MidPointLiner<P> implements Liner<P> {

    /**
     * Metoda pro kreslení čáry na obrázku pomocí algoritmu MidPoint.
     *
     * @param img        RasterImage instance pro kreslení
     * @param c1         X-ová souřadnice prvního bodu čáry
     * @param r1         Y-ová souřadnice prvního bodu čáry
     * @param c2         X-ová souřadnice druhého bodu čáry
     * @param r2         Y-ová souřadnice druhého bodu čáry
     * @param pixelValue Hodnota pixelu pro kreslení čáry
     */
    @Override
    public void drawLine(@NotNull RasterImage<P> img, int c1, int r1, int c2, int r2, @NotNull P pixelValue) {
        {
            int sx, sy;

            sx = (c1 + c2) / 2;
            sy = (r1 + r2) / 2;

            img.setPixel(sx, sy, pixelValue);
            img.setPixel(c1, r1, pixelValue);
            if (Math.abs(c1 - sx) > 1 || Math.abs(r1 - sy) > 1) {
                drawLine(img, c1, r1, sx, sy, pixelValue);
            }
            if (Math.abs(c2 - sx) > 1 || Math.abs(r2 - sy) > 1) {
                drawLine(img, sx, sy, c2, r2, pixelValue);
            }
        }

    }
    /**
     * Metoda pro získání středového bodu mezi dvěma body čáry a jeho vykreslení.
     *
     * @param img        RasterImage instance pro kreslení
     * @param c1         X-ová souřadnice prvního bodu čáry
     * @param r1         Y-ová souřadnice prvního bodu čáry
     * @param c2         X-ová souřadnice druhého bodu čáry
     * @param r2         Y-ová souřadnice druhého bodu čáry
     * @param pixelValue Hodnota pixelu pro vykreslení středového bodu
     * @return Středový bod čáry
     */
    public Point getMidPoint(final @NotNull RasterImage<P> img, final int c1, final int r1,
                             final int c2, final int r2, final @NotNull P pixelValue) {
        double midX = (c1 + c2) / 2.0;
        double midY = (r1 + r2) / 2.0;

        img.setPixel((int)midX,(int)midY,pixelValue);
        drawLine(img,(int)midX,(int)midY,c2,r2,pixelValue);
        return new Point((int)midX, (int)midY);
    }
}
