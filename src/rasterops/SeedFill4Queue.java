package rasterops;

import objectdata.Point2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class SeedFill4Queue<P> implements SeedFill<P>{

    @Override
    public void fill(@NotNull RasterImage<P> img, int c, int r, @NotNull P pixelValue, @NotNull Predicate<P> isInArea) {
        Queue<Point2D> queue = new LinkedList<>();
        queue.add(new Point2D(c,r));

        while (!queue.isEmpty()) {
            Point2D queuePoint = queue.poll();

            int x = queuePoint.getC1();
            int y = queuePoint.getR1();

            img.getPixel(x, y).ifPresent(p -> {
                if (isInArea.test(p)) {
                    img.setPixel(x, y, pixelValue);

                    queue.add(new Point2D(x + 1, y));
                    queue.add(new Point2D(x, y + 1));
                    queue.add(new Point2D(x - 1, y));
                    queue.add(new Point2D(x, y - 1));
                }
            });
        }

    }
}
