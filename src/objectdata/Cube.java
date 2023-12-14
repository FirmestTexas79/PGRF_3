package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Cube implements Solid{

    private final @NotNull List<Point3D> vertices;
    private final @NotNull List<Integer> indices;

    public Cube(){
        vertices=new ArrayList<>();
        vertices.add(new Point3D(-1,-1,-1));
        vertices.add(new Point3D(1,-1,-1));
        vertices.add(new Point3D(1,1,-1));
        vertices.add(new Point3D(-1,1,-1));

        vertices.add(new Point3D(-1,-1,1));
        vertices.add(new Point3D(1,-1,1));
        vertices.add(new Point3D(1,1,1));
        vertices.add(new Point3D(-1,1,1));

        indices=new ArrayList<>();
        IntStream.rangeClosed(0,3).forEach(i->{
            indices.add(i);
            indices.add((i+1)%4);

            indices.add(i);
            indices.add(i+4);

            indices.add(i+4);
            indices.add((i+1)%4+4);
        });
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
