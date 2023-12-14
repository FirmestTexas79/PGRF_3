package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Mat3;
import transform.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Polygon2D {

    private final List<Point2D> points = new ArrayList<>();

    public Polygon2D() {
    }

    public void addPoint(Point2D point){
        points.add(point);
    }

    public Point2D getPoint(int index){
        return points.get(index);
    }

    public List<Point2D> getPoints(){
        return points;
    }

    public Polygon2D transform(final @NotNull Mat3 transMat){
        Polygon2D polygon2D=new Polygon2D();
        for (Point2D point2D:points){

            transform.Point2D p=new transform.Point2D(point2D.getX(),point2D.getY());
            p.mul(transMat);

            polygon2D.addPoint(point2D);

        }
        return polygon2D;
    }
}
