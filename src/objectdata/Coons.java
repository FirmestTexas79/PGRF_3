package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Cubic;
import transform.Mat4;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Coons implements Solid{

    final @NotNull List<Point3D> vertices;
    final @NotNull List<Integer> indices;
    private Cubic cubic;
    double step;

    public Coons(final int smoothness, Point3D a,Point3D b,Point3D c,Point3D d) {
        Mat4 coons;
        vertices=new ArrayList<>();
        indices=new ArrayList<>();
        coons=Cubic.COONS;

        step=(Math.PI*2/smoothness);
        this.cubic = new Cubic(coons, a, b, c, d);

        for (int i = 0; i < smoothness; i++) {
            vertices.add(cubic.compute((double) i / smoothness));

            if (i != 0) {
                indices.add(i - 1);
                indices.add(i);
            }
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
