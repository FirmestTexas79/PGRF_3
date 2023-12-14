//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4Scale extends Mat4Identity {
    public Mat4Scale(double x, double y, double z) {
        this.mat[0][0] = x;
        this.mat[1][1] = y;
        this.mat[2][2] = z;
    }

    public Mat4Scale(double scale) {
        this(scale, scale, scale);
    }

    public Mat4Scale(Vec3D v) {
        this(v.getX(), v.getY(), v.getZ());
    }
}
