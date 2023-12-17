package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Pyramida implements Solid {

    private final @NotNull List<Point3D> vertices;
    private final @NotNull List<Integer> indices;
    private static final double SCALE_FACTOR = 1.5; // Měřítko
    private static final double X_TRANSLATION = -5.0; // Posunutí po -x

    public Pyramida() {
        vertices = new ArrayList<>();
        vertices.add(new Point3D(0, 0, 0.8 * SCALE_FACTOR));

        vertices.add(new Point3D(0.8 * SCALE_FACTOR, 0.8 * SCALE_FACTOR, -0.8 * SCALE_FACTOR));
        vertices.add(new Point3D(0.8 * SCALE_FACTOR, -0.8 * SCALE_FACTOR, -0.8 * SCALE_FACTOR));
        vertices.add(new Point3D(-0.8 * SCALE_FACTOR, -0.8 * SCALE_FACTOR, -0.8 * SCALE_FACTOR));
        vertices.add(new Point3D(-0.8 * SCALE_FACTOR, 0.8 * SCALE_FACTOR, -0.8 * SCALE_FACTOR));

        indices = new ArrayList<>();
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

        // Posunutí všech bodů po -x
        for (int i = 0; i < vertices.size(); i++) {
            Point3D vertex = vertices.get(i);
            vertices.set(i, new Point3D(vertex.getX() + X_TRANSLATION, vertex.getY(), vertex.getZ()));
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
