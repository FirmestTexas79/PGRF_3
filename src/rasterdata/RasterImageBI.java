package rasterdata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class RasterImageBI implements RasterImage<Integer>, Presentable<Graphics> {

    private final BufferedImage bufferedImage;

    public RasterImageBI(int width, int height) {
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public Optional<Integer> getPixel(int c, int r) {
        if(c < getWidth() && r < getHeight() && c >= 0 && r >= 0) {
            return Optional.of(bufferedImage.getRGB(c, r));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void setPixel(int c, int r, Integer newValue) {
        if(c < getWidth() && r < getHeight() && c >= 0 && r >= 0) {
            bufferedImage.setRGB(c, r, newValue);
        }
    }

    @Override
    public void clear(final @NotNull Integer newValue) {
        final @Nullable Graphics g = bufferedImage.getGraphics();
        if (g != null) {
            g.setColor(new Color(newValue));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public Graphics present(final Graphics device) {
        device.drawImage(bufferedImage, 0, 0, null);
        return device;
    }
}
