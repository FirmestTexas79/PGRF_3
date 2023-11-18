package rasterops;

import objectdata.Point2D;

public interface PaternFill<P> {
    P fill(int x, int y);
    default P fill(Point2D point2D) {
        return fill(point2D.getC1(), point2D.getR1());
    }
}
