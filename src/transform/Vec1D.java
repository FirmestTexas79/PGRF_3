//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;

public class Vec1D {
    private final double x;

    public Vec1D() {
        this.x = 0.0;
    }

    public Vec1D(double x) {
        this.x = x;
    }

    public Vec1D(Vec1D v) {
        this.x = v.x;
    }

    public double getX() {
        return this.x;
    }

    public Vec1D add(Vec1D v) {
        return new Vec1D(this.x + v.x);
    }

    public Vec1D sub(Vec1D v) {
        return new Vec1D(this.x - v.x);
    }

    public Vec1D mul(double d) {
        return new Vec1D(this.x * d);
    }

    public Vec1D opposite() {
        return new Vec1D(-this.x);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getX()});
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Vec1D && (new Double(((Vec1D)obj).getX())).equals(this.getX());
    }

    public boolean eEquals(Vec1D vec, double epsilon) {
        return this == vec || vec != null && Compare.eEquals(this.getX(), vec.getX(), epsilon);
    }

    public boolean eEquals(Vec1D vec) {
        return this.eEquals(vec, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + ")", this.x);
    }
}
