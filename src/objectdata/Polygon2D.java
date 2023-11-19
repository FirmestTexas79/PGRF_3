package objectdata;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon2D {

    // Seznam bodů polygonu
    private final List<Point2D> points = new ArrayList<>();

    public Polygon2D() {
    }


    /**
     * Metoda pro přidání bodu do polygonu.
     *
     * @param point Bod k přidání
     */
    public void addPoint(Point2D point){
        points.add(point);
    }


    /**
     * Metoda pro získání bodu na daném indexu.
     *
     * @param index Index bodu
     * @return Bod na zadaném indexu
     * @throws IndexOutOfBoundsException Pokud je index mimo rozsah seznamu bodů
     */
    public  Point2D getPoint(int index){
        if (index >= 0 && index < points.size()) {
            return points.get(index);
        }
        throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + points.size());
    }

    /**
     * Metoda pro získání seznamu všech bodů polygonu.
     *
     * @return Seznam bodů polygonu
     */
    public List<Point2D> getPoints(){
        return points;
    }

    /**
     * Metoda pro odstranění všech bodů z polygonu.
     */
    public  void removeAllPoints() {
        points.clear();
    }

    /**
     * Metoda pro získání souřadnice x bodu na zadaném indexu.
     *
     * @param index Index bodu
     * @return Souřadnice x bodu na zadaném indexu
     */
    public int getX(final int index) {
        return points.get(index).getC1();
    }

    /**
     * Metoda pro získání souřadnice y bodu na zadaném indexu.
     *
     * @param index Index bodu
     * @return Souřadnice y bodu na zadaném indexu
     */
    public int getY(final int index) {
        return points.get(index).getR1();
    }

    /**
     * Metoda pro získání středového bodu mezi dvěma body.
     *
     * @param point1 První bod
     * @param point2 Druhý bod
     * @return Středový bod
     */
    public Point getMidPoint(final @NotNull Point point1, final @NotNull Point point2) {
        double midX = (point1.getX() + point2.getX()) / 2.0;
        double midY = (point1.getY() + point2.getY()) / 2.0;

        return new Point((int)midX,(int) midY);
    }

}