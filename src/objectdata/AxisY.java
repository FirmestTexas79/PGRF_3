package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class AxisY implements Solid{

    private final @NotNull List<Point3D> vertices;
    private final @NotNull List<Integer> indices;

    public AxisY(){
        vertices=new ArrayList<>();
        vertices.add(new Point3D(0,-2,0));
        vertices.add(new Point3D(0,2,0));

        indices=new ArrayList<>();

        for(int i=0;i<=vertices.size();i++){
            indices.add(i);

        }
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
