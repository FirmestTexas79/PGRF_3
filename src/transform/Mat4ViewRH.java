//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4ViewRH extends Mat4Identity {
    public Mat4ViewRH(Vec3D e, Vec3D v, Vec3D u) {
        Vec3D z = (Vec3D)v.mul(-1.0).normalized().orElse(new Vec3D(1.0, 0.0, 0.0));
        Vec3D x = (Vec3D)u.cross(z).normalized().orElse(new Vec3D(1.0, 0.0, 0.0));
        Vec3D y = z.cross(x);
        this.mat[0][0] = x.getX();
        this.mat[1][0] = x.getY();
        this.mat[2][0] = x.getZ();
        this.mat[3][0] = -e.dot(x);
        this.mat[0][1] = y.getX();
        this.mat[1][1] = y.getY();
        this.mat[2][1] = y.getZ();
        this.mat[3][1] = -e.dot(y);
        this.mat[0][2] = z.getX();
        this.mat[1][2] = z.getY();
        this.mat[2][2] = z.getZ();
        this.mat[3][2] = -e.dot(z);
    }
}
