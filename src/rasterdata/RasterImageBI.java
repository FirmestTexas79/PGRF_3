package rasterdata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class RasterImageBI implements RasterImage<Integer>, Presentable<Graphics> {

    // Grafický kontext pro práci s obrázkem
    private final @Nullable Graphics graphics;

    // BufferedImage pro uchování pixelů obrázku
    private final BufferedImage bufferedImage;

    /**
     * Konstruktor pro vytvoření instance třídy s danou šířkou a výškou obrázku.
     *
     * @param width  Šířka obrázku
     * @param height Výška obrázku
     */
    public RasterImageBI(int width, int height) {
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = bufferedImage.getGraphics();
    }

    /**
     * Metoda pro získání šířky obrázku.
     *
     * @return Šířka obrázku
     */
    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    /**
     * Metoda pro získání výšky obrázku.
     *
     * @return Výška obrázku
     */
    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    /**
     * Metoda pro získání hodnoty pixelu na zadaných souřadnicích.
     *
     * @param column Sloupec (x-ová souřadnice) pixelu
     * @param row    Řádek (y-ová souřadnice) pixelu
     * @return Hodnota pixelu na zadaných souřadnicích v podobě Optional
     */
    @Override
    public Optional<Integer> getPixel(int column, int row) {  // Renamed 'c' to 'column', 'r' to 'row' for clarity
        if (isValidPixel(column, row)) {
            return Optional.of(bufferedImage.getRGB(column, row));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Metoda pro nastavení hodnoty pixelu na zadaných souřadnicích.
     *
     * @param column   Sloupec (x-ová souřadnice) pixelu
     * @param row      Řádek (y-ová souřadnice) pixelu
     * @param newValue Nová hodnota pixelu
     */
    @Override
    public void setPixel(int column, int row, Integer newValue) {  // Renamed 'c' to 'column', 'r' to 'row' for clarity
        if (isValidPixel(column, row)) {
            bufferedImage.setRGB(column, row, newValue);
        }
    }

    /**
     * Metoda pro vyčištění celého obrázku danou barvou.
     *
     * @param color Barva pro vyčištění
     */
    @Override
    public void clear(final @NotNull Integer color) {
        final @Nullable Graphics g = bufferedImage.getGraphics();
        if (g != null) {
            g.setColor(new Color(color));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Metoda pro kreslení nápovědy na obrázek.
     */
    public void drawHelp() {
        if (graphics != null) {
            graphics.setColor(new Color(255, 100, 200));
            graphics.fillRect(0, 0, 160, 230);
            graphics.setColor(new Color(0));

            setFontStyle(graphics);

            drawHelpText(graphics, "(L) Liner", 20);
            drawHelpText(graphics, "(D) DottedLiner", 40);
            drawHelpText(graphics, "(C) Clear", 60);
            drawHelpText(graphics, "(S) Smazani pointu", 80);

            drawHelpText(graphics, "(P) Polygon", 100);
            drawHelpText(graphics, "(T) Triangle", 120);
            drawHelpText(graphics, "(F) Scanline", 140);

            drawHelpText(graphics, "(B) Bod", 160);
            drawHelpText(graphics, "(W) Seedfill", 180);
            drawHelpText(graphics, "(H) Crop Hranice", 200);
            drawHelpText(graphics, "(J) Crop", 220);
        }
    }

    /**
     * Metoda pro nastavení stylu písma pro nápovědu.
     *
     * @param g Grafický kontext
     */
    private void setFontStyle(Graphics g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
    }

    private void drawHelpText(Graphics g, String text, int y) {
        g.drawString(text, 5, y);
    }

    /**
     * Metoda pro vykreslení obsahu obrázku na zadaný grafický kontext.
     *
     * @param device Grafický kontext pro vykreslení
     * @return Grafický kontext s vykresleným obsahem obrázku
     */
    @Override
    public Graphics present(final Graphics device) {
        device.drawImage(bufferedImage, 0, 0, null);
        drawHelp();
        return device;
    }

    private boolean isValidPixel(int column, int row) {
        return column < getWidth() && row < getHeight() && column >= 0 && row >= 0;
    }
}

