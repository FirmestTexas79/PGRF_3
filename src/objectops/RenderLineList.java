package objectops;

import objectdata.Scene;
import objectdata.Solid;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;
import rasterops.Liner;
import transform.Mat4;
import transform.Point2D;
import transform.Point3D;
import transform.Vec3D;

import java.util.List;
import java.util.stream.IntStream;

public class RenderLineList<P> implements Renderer<P>{

    @Override
    public void renderScene(@NotNull Scene scene, @NotNull Mat4 viewMatrix, @NotNull Mat4 projectMatrix,
                            @NotNull RasterImage<P> img, P pixelValue, @NotNull Liner<P> liner) {
        //for each solid
        for (int i=0; i< scene.getSolids().size();i++) {
            //create transMatrix
            final @NotNull Mat4 transMatrix=scene.getModelMats().get(i).mul(viewMatrix).mul(projectMatrix);

            //zavola metodu renderSolid()
            renderSolid(scene.getSolids().get(i),transMatrix,img,pixelValue,liner);
        }
    }

    private boolean isWithinRange(Point3D point) {
        double w = point.getW();
        return point.getX() <= w && point.getX() >= -w &&
                point.getY() <= w && point.getY() >= -w &&
                point.getZ() <= w && point.getZ() >= 0;
    }

    private Point2D transformToViewport(Vec3D point, RasterImage<P> raster) {
        return new Point2D(
                (int) Math.round((point.getX() + 1) / 2 * (raster.getWidth() - 1)),
                (int) Math.round((1 - (point.getY() + 1) / 2) * (raster.getHeight() - 1))
        );
    }

    @Override
    public void renderSolid(@NotNull Solid solid, @NotNull Mat4 transMatrix, @NotNull RasterImage<P> img, @NotNull P pixelValue, @NotNull Liner<P> liner) {


        final @NotNull List<Point3D> transformedVertices=solid.vertices().stream()
                .map(v->v.mul(transMatrix))
                .toList();

        //for each lineSegment
        IntStream.iterate(0,i->i+2).limit(solid.indices().size()/2).parallel().forEach(i->{
            final @NotNull Point3D p1= transformedVertices.get(solid.indices().get(i));
            final @NotNull Point3D p2= transformedVertices.get(solid.indices().get(i+1));
            //clip by w
            if (
                    p1.getZ() >= 0 && p1.getZ() <= p1.getW() &&
                            p1.getX() >= -p1.getW() && p1.getX() <= p1.getW() &&
                            p1.getY() >= -p1.getW() && p1.getY() <= p1.getW() &&
                            p2.getZ() >= 0 && p2.getZ() <= p2.getW() &&
                            p2.getX() >= -p2.getW() && p2.getX() <= p2.getW() &&
                            p2.getY() >= -p2.getW() && p2.getY() <= p2.getW()
            ) {
                p1.dehomog().ifPresent(start->p2.dehomog().ifPresent(end->{
                    //transformation to viewport
                    final int c1=(int)((start.getX()+1) /2.*(img.getWidth()-1));
                    final int c2=(int)((end.getX()+1)/2.*(img.getWidth()-1));
                    final int r1=(int)((1-(start.getY()+1)/2.)*(img.getHeight()-1));
                    final int r2=(int)((1-(end.getY()+1)/2.)*(img.getHeight()-1));
                    //draw lines using liner
                    liner.drawLine(img,c1,r1,c2,r2,pixelValue);
                }));
            }
        });
    }
}




