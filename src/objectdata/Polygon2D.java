package objectdata;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon2D {

    private  final List<Point2D> points = new ArrayList<>();

    public Polygon2D() {
    }

    public void addPoint(Point2D point){
        points.add(point);
    }

    public  Point2D getPoint(int index){
        if (index >= 0 && index < points.size()) {
            return points.get(index);
        }
        throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + points.size());
    }

    public List<Point2D> getPoints(){
        return points;
    }
    public  void removeAllPoints() {
        points.clear();
    }
    public int getX(final int index) {
        return points.get(index).getC1();
    }

    public int getY(final int index) {
        return points.get(index).getR1();
    }


    public Point getMidPoint(final @NotNull Point point1, final @NotNull Point point2) {
        double midX = (point1.getX() + point2.getX()) / 2.0;
        double midY = (point1.getY() + point2.getY()) / 2.0;

        return new Point((int)midX,(int) midY);
    }

}