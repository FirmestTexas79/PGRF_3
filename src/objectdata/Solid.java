//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package objectdata;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import transform.Point3D;

public interface Solid {
    @NotNull List<Point3D> vertices();

    @NotNull List<Integer> indices();
}
