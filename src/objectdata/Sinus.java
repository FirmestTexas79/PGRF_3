package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Sinus implements Solid{
    final @NotNull List<Point3D> vertices;
    final @NotNull List<Integer> indices;

    public Sinus(final double smoothness) {
        vertices=new ArrayList<>();
        indices=new ArrayList<>();

        double step=(Math.PI*2/smoothness);

        for (int i=0;i<smoothness;i++){
            double x=i*step-Math.PI;
            double z=Math.sin(x);
            vertices.add(new Point3D(x,0,z));
        }
        for (int i=0;i< vertices().size()-1;i++){
            indices.add(i);
            indices.add(i+1);

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
