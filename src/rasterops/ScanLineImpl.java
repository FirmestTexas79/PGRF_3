package rasterops;

import objectdata.Hrana;
import objectdata.Point2D;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLineImpl <P> implements ScanLine<P>{


    /**
     * Metoda pro vyplnění polygonu na obrázku pomocí algoritmu Scan-Line.
     *
     * @param polygon2D    Polygon2D instance reprezentující polygon
     * @param img          RasterImage instance pro vykreslení
     * @param polygoner2D  Polygoner2D instance pro vykreslení polygonu
     * @param areaPixel    Hodnota pixelu pro vyplnění oblasti
     * @param polygonPixel Hodnota pixelu pro vyplnění polygonu
     * @param liner        Liner instance pro kreslení
     */
    @Override
    public void fill(
            final @NotNull Polygon2D polygon2D,
            final @NotNull RasterImage<P> img,
            final @NotNull Polygoner2D<P> polygoner2D,
            final @NotNull P areaPixel,
            final @NotNull P polygonPixel,
            final @NotNull Liner<P> liner) {

        //initialize an empty list of edges


        List<Hrana> edges = new ArrayList<Hrana>();

        int listSize = polygon2D.getPoints().size();
        if (listSize == 0) {
            System.err.println("Nezapomněl jsi na polygon?");
            return;
        }
        for (int i = 0; i < listSize; i++) {
            int next = (i + 1) % listSize;
            int y1 = polygon2D.getPoint(i).getR1();
            int y2 = polygon2D.getPoint(next).getR1();

            if (y1 != y2) { //isHorizontal
                Hrana edge = new Hrana(new Point2D(polygon2D.getPoint(i).getC1(), polygon2D.getPoint(i).getR1()),
                        new Point2D(polygon2D.getPoint(next).getC1(), polygon2D.getPoint(next).getR1()));
                edge = edge.oriented();
                edge = edge.shortened();
                edges.add(edge);


            }
        }

        //find yMin,yMax
        int yMin = polygon2D.getPoint(0).getR1();
        int yMax = polygon2D.getPoint(0).getR1();
        for (int i = 0; i <= listSize - 1; i++) {
            if (yMin > polygon2D.getPoint(i).getR1()) {
                yMin = polygon2D.getPoint(i).getR1();
            }
            if (yMax < polygon2D.getPoint(i).getR1()) {
                yMax = polygon2D.getPoint(i).getR1();
            }

        }

        List<Integer> intersections = new ArrayList<Integer>();
        for (int y = yMin; y <= yMax; y++) {
            intersections.clear(); // Moved outside the inner loop

            for (Hrana edge : edges) {
                if (edge.hasInterection(y)) {
                    intersections.add(edge.intersection(y));
                }
            }

            Collections.sort(intersections);

            if (intersections.size() % 2 != 0) {
                System.err.println("Odd number of intersection points found! Count: " + intersections.size() + ", at y=" + y);
            } else {
                for (int x = 0; x < intersections.size(); x += 2) {
                    liner.drawLine(img, intersections.get(x), y, intersections.get(x + 1), y, areaPixel);
                }
            }
            polygoner2D.drawPolygon(img,polygonPixel,liner,polygon2D);
        }
    }
}
