package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Plocha implements Solid{
    private final @NotNull List<Point3D> vertices;
    private final @NotNull List<Integer> indices;

    public Plocha() {
        vertices = new ArrayList<>();
        indices = new ArrayList<>();

        double translationX = -3.0; // Posunutí ve směru X
        double translationY = 0.0; // Žádné posunutí ve směru Y
        double translationZ = 0.0; // Žádné posunutí ve směru Z

// Přepsání souřadnic vrcholů
        vertices.add(new Point3D(-2 + translationX, -2 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1.5 + translationX, -2 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1 + translationX, -2 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-0.5 + translationX, -2 + translationY, -2 + translationZ));

        vertices.add(new Point3D(-2 + translationX, -1.5 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1.5 + translationX, -1.5 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1 + translationX, -1.5 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-0.5 + translationX, -1.5 + translationY, -2 + translationZ));

        vertices.add(new Point3D(-2 + translationX, -1 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1.5 + translationX, -1 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1 + translationX, -1 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-0.5 + translationX, -1 + translationY, -2 + translationZ));

        vertices.add(new Point3D(-2 + translationX, -0.5 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1.5 + translationX, -0.5 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-1 + translationX, -0.5 + translationY, -2 + translationZ));
        vertices.add(new Point3D(-0.5 + translationX, -0.5 + translationY, -2 + translationZ));

        indices.add(0);
        indices.add(1);
        indices.add(0);
        indices.add(4);

        indices.add(1);
        indices.add(5);
        indices.add(1);
        indices.add(2);

        indices.add(2);
        indices.add(6);
        indices.add(2);
        indices.add(3);

        indices.add(3);
        indices.add(7);

        indices.add(4);
        indices.add(5);
        indices.add(4);
        indices.add(8);

        indices.add(5);
        indices.add(9);
        indices.add(5);
        indices.add(6);

        indices.add(6);
        indices.add(10);
        indices.add(6);
        indices.add(7);

        indices.add(7);
        indices.add(11);


        indices.add(8);
        indices.add(9);
        indices.add(8);
        indices.add(12);

        indices.add(9);
        indices.add(10);
        indices.add(9);
        indices.add(13);

        indices.add(10);
        indices.add(11);
        indices.add(10);
        indices.add(14);

        indices.add(11);
        indices.add(15);


        indices.add(12);
        indices.add(13);

        indices.add(13);
        indices.add(14);

        indices.add(14);
        indices.add(15);

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