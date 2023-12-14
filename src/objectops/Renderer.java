package objectops;

import objectdata.Scene;
import objectdata.Solid;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;
import rasterops.Liner;
import transform.Mat4;

public interface Renderer<P> {

    void renderScene(final @NotNull Scene scene, final @NotNull Mat4 viewMatrix, final
    @NotNull Mat4 projectMatrix, final @NotNull RasterImage<P> img, final P pixelValue, final @NotNull Liner<P> liner);

    void renderSolid(final @NotNull Solid solid,final @NotNull Mat4 tranMatrix,final @NotNull RasterImage<P> img,
                     final @NotNull P pixelValue,final @NotNull Liner<P> liner);

}
