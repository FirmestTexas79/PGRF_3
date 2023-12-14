package objectdata;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;
import transform.Point2D;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Pyramid implements Solid{

    private final @NotNull List<Point3D> vertices;
    private final @NotNull List<Integer> indices;

    public Pyramid(){
        vertices=new ArrayList<>();
        vertices.add(new Point3D(0,0,0.8));


        vertices.add(new Point3D(0.8,0.8,-0.8));
        vertices.add(new Point3D(0.8,-0.8,-0.8));
        vertices.add(new Point3D(-0.8,-0.8,-0.8));
        vertices.add(new Point3D(-0.8,0.8,-0.8));


        indices=new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(0);
        indices.add(2);
        indices.add(0);
        indices.add(3);
        indices.add(0);
        indices.add(4);

        indices.add(1);
        indices.add(2);
        indices.add(2);
        indices.add(3);
        indices.add(3);
        indices.add(4);
        indices.add(4);
        indices.add(1);


    }

    @Override
    public @NotNull List<Point3D> vertices() {
        return vertices;
    }

    @Override
    public @NotNull List<Integer> indices() {
        return indices;
    }

}