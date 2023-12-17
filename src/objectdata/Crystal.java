package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Crystal implements Solid{
    private final @NotNull List<Point3D> vertices;
    private final @NotNull List<Integer> indices;

    public Crystal(){
        vertices=new ArrayList<>();
        vertices.add(new Point3D(2,0,0.8));


        vertices.add(new Point3D(2.8,0.8,-0.8));
        vertices.add(new Point3D(2.8,-0.8,-0.8));
        vertices.add(new Point3D(1.2,-0.8,-0.8));
        vertices.add(new Point3D(1.2,0.8,-0.8));
        vertices.add(new Point3D(2,0,-2.4));


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


        indices.add(5);
        indices.add(1);
        indices.add(5);
        indices.add(2);
        indices.add(5);
        indices.add(3);
        indices.add(5);
        indices.add(4);


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
