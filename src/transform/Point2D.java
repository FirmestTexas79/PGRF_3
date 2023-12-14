//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Point2D {
    private final double x;
    private final double y;
    private final double w;

    public Point2D() {
        this.x = this.y = 0.0;
        this.w = 1.0;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.w = 1.0;
    }

    public Point2D(double x, double y, double w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public Point2D(Vec2D v) {
        this.x = v.getX();
        this.y = v.getY();
        this.w = 1.0;
    }

    public Point2D(Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
        this.w = p.getW();
    }

    public Point2D(Point3D p) {
        this.x = p.getX();
        this.y = p.getY();
        this.w = p.getW();
    }

    public Point2D(double[] array) {
        assert array.length >= 3;

        this.x = array[0];
        this.y = array[1];
        this.w = array[3];
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getW() {
        return this.w;
    }

    public Point2D withX(double x) {
        return new Point2D(x, this.getY(), this.getW());
    }

    public Point2D withY(double y) {
        return new Point2D(this.getX(), y, this.getW());
    }

    public Point2D withW(double w) {
        return new Point2D(this.getX(), this.getY(), w);
    }

    public Point2D mul(Mat3 mat) {
        return new Point2D(mat.mat[0][0] * this.x + mat.mat[1][0] * this.y + mat.mat[2][0] * this.w, mat.mat[0][1] * this.x + mat.mat[1][1] * this.y + mat.mat[2][1] * this.w, mat.mat[0][2] * this.x + mat.mat[1][2] * this.y + mat.mat[2][2] * this.w);
    }

    public Point2D add(Point2D p) {
        return new Point2D(this.x + p.x, this.y + p.y, this.w + p.w);
    }

    public Point2D add(Vec2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY(), this.w);
    }

    public Point2D mul(double d) {
        return new Point2D(this.x * d, this.y * d, this.w * d);
    }

    public Optional<Vec2D> dehomog() {
        return this.w == 0.0 ? Optional.empty() : Optional.of(new Vec2D(this.x / this.w, this.y / this.w));
    }

    public Vec2D ignoreW() {
        return new Vec2D(this.x, this.y);
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Point2D && (new Double(((Point2D)obj).getX())).equals(this.getX()) && (new Double(((Point2D)obj).getY())).equals(this.getY()) && (new Double(((Point2D)obj).getW())).equals(this.getW());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getX(), this.getY(), this.getW()});
    }

    public boolean eEquals(Point2D point, double epsilon) {
        return this == point || point != null && Compare.eEquals(this.getX(), point.getX(), epsilon) && Compare.eEquals(this.getY(), point.getY(), epsilon) && Compare.eEquals(this.getW(), point.getW(), epsilon);
    }

    public boolean eEquals(Point2D point) {
        return this.eEquals(point, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + "," + format + "," + format + ")", this.x, this.y, this.w);
    }
}
