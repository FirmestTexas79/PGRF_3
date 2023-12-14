//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Point3D {
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Point3D() {
        this.x = this.y = this.z = 0.0;
        this.w = 1.0;
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0;
    }

    public Point3D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Point3D(Vec3D v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
        this.w = 1.0;
    }

    public Point3D(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        this.w = p.w;
    }

    public Point3D(Point2D p, double z) {
        this.x = p.getX();
        this.y = p.getY();
        this.z = z;
        this.w = p.getW();
    }

    public Point3D(double[] array) {
        assert array.length >= 4;

        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
        this.w = array[3];
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double getW() {
        return this.w;
    }

    public Point3D withX(double x) {
        return new Point3D(x, this.getY(), this.getZ(), this.getW());
    }

    public Point3D withY(double y) {
        return new Point3D(this.getX(), y, this.getZ(), this.getW());
    }

    public Point3D withZ(double z) {
        return new Point3D(this.getX(), this.getY(), z, this.getW());
    }

    public Point3D withW(double w) {
        return new Point3D(this.getX(), this.getY(), this.getZ(), w);
    }

    public Point3D mul(Mat4 mat) {
        return new Point3D(mat.mat[0][0] * this.x + mat.mat[1][0] * this.y + mat.mat[2][0] * this.z + mat.mat[3][0] * this.w, mat.mat[0][1] * this.x + mat.mat[1][1] * this.y + mat.mat[2][1] * this.z + mat.mat[3][1] * this.w, mat.mat[0][2] * this.x + mat.mat[1][2] * this.y + mat.mat[2][2] * this.z + mat.mat[3][2] * this.w, mat.mat[0][3] * this.x + mat.mat[1][3] * this.y + mat.mat[2][3] * this.z + mat.mat[3][3] * this.w);
    }

    public Optional<Point3D> mul(Quat q) {
        return this.dehomog().map((affinePoint) -> {
            return new Point3D(affinePoint.mul(q));
        });
    }

    public Point3D add(Point3D p) {
        return new Point3D(this.x + p.x, this.y + p.y, this.z + p.z, this.w + p.w);
    }

    public Point3D mul(double d) {
        return new Point3D(this.x * d, this.y * d, this.z * d, this.w * d);
    }

    public Optional<Vec3D> dehomog() {
        return this.w == 0.0 ? Optional.empty() : Optional.of(new Vec3D(this.x / this.w, this.y / this.w, this.z / this.w));
    }

    public Vec3D ignoreW() {
        return new Vec3D(this.x, this.y, this.z);
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Point3D && (new Double(((Point3D)obj).getX())).equals(this.getX()) && (new Double(((Point3D)obj).getY())).equals(this.getY()) && (new Double(((Point3D)obj).getZ())).equals(this.getZ()) && (new Double(((Point3D)obj).getW())).equals(this.getW());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getX(), this.getY(), this.getZ(), this.getW()});
    }

    public boolean eEquals(Point3D point, double epsilon) {
        return this == point || point != null && Compare.eEquals(this.getX(), point.getX(), epsilon) && Compare.eEquals(this.getY(), point.getY(), epsilon) && Compare.eEquals(this.getZ(), point.getZ(), epsilon) && Compare.eEquals(this.getW(), point.getW(), epsilon);
    }

    public boolean eEquals(Point3D point) {
        return this.eEquals(point, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + "," + format + "," + format + "," + format + ")", this.x, this.y, this.z, this.w);
    }
}
