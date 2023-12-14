package rasterdata;


import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RasterImage<P> {
    int getWidth();
    int getHeight();
    Optional<P> getPixel(int c, int r);
    void setPixel(int c, int r, P newValue);

    void clear(final @NotNull P newValue);
}

