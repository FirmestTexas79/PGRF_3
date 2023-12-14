//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Vec2D {
    private final double x;
    private final double y;

    public Vec2D() {
        this.x = this.y = 0.0;
    }

    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2D(double value) {
        this.x = value;
        this.y = value;
    }

    public Vec2D(Vec2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Vec2D withX(double x) {
        return new Vec2D(x, this.getY());
    }

    public Vec2D withY(double y) {
        return new Vec2D(this.getX(), y);
    }

    public Vec2D add(Vec2D v) {
        return new Vec2D(this.x + v.x, this.y + v.y);
    }

    public Vec2D sub(Vec2D v) {
        return new Vec2D(this.x - v.x, this.y - v.y);
    }

    public Vec2D mul(double d) {
        return new Vec2D(this.x * d, this.y * d);
    }

    public Vec2D mul(Vec2D v) {
        return new Vec2D(this.x * v.x, this.y * v.y);
    }

    public double dot(Vec2D v) {
        return this.x * v.x + this.y * v.y;
    }

    public Optional<Vec2D> normalized() {
        double len = this.length();
        return len == 0.0 ? Optional.empty() : Optional.of(new Vec2D(this.x / len, this.y / len));
    }

    public Vec2D opposite() {
        return new Vec2D(-this.x, -this.y);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Vec2D && (new Double(((Vec2D)obj).getX())).equals(this.getX()) && (new Double(((Vec2D)obj).getY())).equals(this.getY());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getX(), this.getY()});
    }

    public boolean eEquals(Vec2D vec, double epsilon) {
        return this == vec || vec != null && Compare.eEquals(this.getX(), vec.getX(), epsilon) && Compare.eEquals(this.getY(), vec.getY(), epsilon);
    }

    public boolean eEquals(Vec2D vec) {
        return this.eEquals(vec, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + "," + format + ")", this.x, this.y);
    }
}
