//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Optional;

public class Mat4Rot extends Mat4Identity {
    public Mat4Rot(double alpha, double x, double y, double z) {
        this(Math.sin(alpha), Math.cos(alpha), new Vec3D(x, y, z));
    }

    public Mat4Rot(double sinAlpha, double cosAlpha, Vec3D rotAxis) {
        Optional<Vec3D> norm = rotAxis.normalized();
        if (norm.isPresent()) {
            double ac = 1.0 - cosAlpha;
            Vec3D axis = (Vec3D)norm.get();
            this.mat[0][0] = axis.getX() * axis.getX() * ac + cosAlpha;
            this.mat[0][1] = axis.getX() * axis.getY() * ac + axis.getZ() * sinAlpha;
            this.mat[0][2] = axis.getX() * axis.getZ() * ac - axis.getY() * sinAlpha;
            this.mat[1][0] = axis.getY() * axis.getX() * ac - axis.getZ() * sinAlpha;
            this.mat[1][1] = axis.getY() * axis.getY() * ac + cosAlpha;
            this.mat[1][2] = axis.getY() * axis.getZ() * ac + axis.getX() * sinAlpha;
            this.mat[2][0] = axis.getZ() * axis.getX() * ac + axis.getY() * sinAlpha;
            this.mat[2][1] = axis.getZ() * axis.getY() * ac - axis.getX() * sinAlpha;
            this.mat[2][2] = axis.getZ() * axis.getZ() * ac + cosAlpha;
        }

    }

    public Mat4Rot(double alpha, Vec3D axis) {
        this(alpha, axis.getX(), axis.getY(), axis.getZ());
    }
}
