//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4Transl extends Mat4Identity {
    public Mat4Transl(double x, double y, double z) {
        this.mat[3][0] = x;
        this.mat[3][1] = y;
        this.mat[3][2] = z;
    }

    public Mat4Transl(Vec3D v) {
        this(v.getX(), v.getY(), v.getZ());
    }
}
