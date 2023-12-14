//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Vec3D {
    private final double x;
    private final double y;
    private final double z;

    public Vec3D() {
        this.x = this.y = this.z = 0.0;
    }

    public Vec3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3D(double value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    public Vec3D(Vec3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vec3D(Point3D point) {
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
    }

    public Vec3D(double[] array) {
        assert array.length >= 3;

        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
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

    public Vec3D withX(double x) {
        return new Vec3D(x, this.getY(), this.getZ());
    }

    public Vec3D withY(double y) {
        return new Vec3D(this.getX(), y, this.getZ());
    }

    public Vec3D withZ(double z) {
        return new Vec3D(this.getX(), this.getY(), z);
    }

    public Vec2D ignoreZ() {
        return new Vec2D(this.x, this.y);
    }

    public Vec3D add(Vec3D v) {
        return new Vec3D(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vec3D sub(Vec3D v) {
        return new Vec3D(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vec3D mul(double d) {
        return new Vec3D(this.x * d, this.y * d, this.z * d);
    }

    public Vec3D mul(Mat3 m) {
        return new Vec3D(m.mat[0][0] * this.x + m.mat[1][0] * this.y + m.mat[2][0] * this.z, m.mat[0][1] * this.x + m.mat[1][1] * this.y + m.mat[2][1] * this.z, m.mat[0][2] * this.x + m.mat[1][2] * this.y + m.mat[2][2] * this.z);
    }

    public Vec3D mul(Quat q) {
        Vec3D t = q.getIJK().mul(2.0).cross(this);
        return this.add(t.mul(q.getR())).add(q.getIJK().cross(t));
    }

    public Vec3D mul(Vec3D v) {
        return new Vec3D(this.x * v.x, this.y * v.y, this.z * v.z);
    }

    public double dot(Vec3D v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vec3D cross(Vec3D v) {
        return new Vec3D(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
    }

    public Optional<Vec3D> normalized() {
        double len = this.length();
        return len == 0.0 ? Optional.empty() : Optional.of(new Vec3D(this.x / len, this.y / len, this.z / len));
    }

    public Vec3D opposite() {
        return new Vec3D(-this.x, -this.y, -this.z);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Vec3D && (new Double(((Vec3D)obj).getX())).equals(this.getX()) && (new Double(((Vec3D)obj).getY())).equals(this.getY()) && (new Double(((Vec3D)obj).getZ())).equals(this.getZ());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getX(), this.getY(), this.getZ()});
    }

    public boolean eEquals(Vec3D vec, double epsilon) {
        return this == vec || vec != null && Compare.eEquals(this.getX(), vec.getX(), epsilon) && Compare.eEquals(this.getY(), vec.getY(), epsilon) && Compare.eEquals(this.getZ(), vec.getZ(), epsilon);
    }

    public boolean eEquals(Vec3D vec) {
        return this.eEquals(vec, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + "," + format + "," + format + ")", this.x, this.y, this.z);
    }
}
