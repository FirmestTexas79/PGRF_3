//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;

public class Col {
    private final double r;
    private final double g;
    private final double b;
    private final double a;

    public Col() {
        this.r = this.g = this.b = 0.0;
        this.a = 1.0;
    }

    public Col(Col c) {
        this.r = c.r;
        this.g = c.g;
        this.b = c.b;
        this.a = c.a;
    }

    public Col(Point3D p) {
        this.r = p.getX();
        this.g = p.getY();
        this.b = p.getZ();
        this.a = p.getW();
    }

    public Col(int rgb) {
        this.a = 1.0;
        this.r = (double)((long)(rgb >> 16) & 255L) / 255.0;
        this.g = (double)((long)(rgb >> 8) & 255L) / 255.0;
        this.b = (double)((long)rgb & 255L) / 255.0;
    }

    public Col(int argb, boolean isAlpha) {
        if (isAlpha) {
            this.a = (double)((long)(argb >> 24) & 255L) / 255.0;
        } else {
            this.a = 1.0;
        }

        this.r = (double)((long)(argb >> 16) & 255L) / 255.0;
        this.g = (double)((long)(argb >> 8) & 255L) / 255.0;
        this.b = (double)((long)argb & 255L) / 255.0;
    }

    public Col(int r, int g, int b) {
        this.a = 1.0;
        this.r = (double)r / 255.0;
        this.g = (double)g / 255.0;
        this.b = (double)b / 255.0;
    }

    public Col(int r, int g, int b, int a) {
        this.a = (double)a / 255.0;
        this.r = (double)r / 255.0;
        this.g = (double)g / 255.0;
        this.b = (double)b / 255.0;
    }

    public Col(double r, double g, double b) {
        this.a = 1.0;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Col(double r, double g, double b, double a) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double getR() {
        return this.r;
    }

    public double getG() {
        return this.g;
    }

    public double getB() {
        return this.b;
    }

    public double getA() {
        return this.a;
    }

    public Col addna(Col c) {
        return new Col(this.r + c.r, this.g + c.g, this.b + c.b);
    }

    public Col mulna(double x) {
        return new Col(this.r * x, this.g * x, this.b * x);
    }

    public Col add(Col c) {
        return new Col(this.r + c.r, this.g + c.g, this.b + c.b, this.a + c.a);
    }

    public Col mul(double x) {
        return new Col(this.r * x, this.g * x, this.b * x, this.a * x);
    }

    public Col mul(Col c) {
        return new Col(this.r * c.r, this.g * c.g, this.b * c.b, this.a * c.a);
    }

    public Col gamma(double gamma) {
        return new Col(Math.pow(this.r, gamma), Math.pow(this.g, gamma), Math.pow(this.b, gamma), this.a);
    }

    public Col saturate() {
        return new Col(Math.max(0.0, Math.min(this.r, 1.0)), Math.max(0.0, Math.min(this.g, 1.0)), Math.max(0.0, Math.min(this.b, 1.0)), this.a);
    }

    public int getRGB() {
        return (int)(this.r * 255.0) << 16 | (int)(this.g * 255.0) << 8 | (int)(this.b * 255.0);
    }

    public int getARGB() {
        return (int)(this.a * 255.0) << 24 | (int)(this.r * 255.0) << 16 | (int)(this.g * 255.0) << 8 | (int)(this.b * 255.0);
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Col && (new Double(((Col)obj).getR())).equals(this.getR()) && (new Double(((Col)obj).getG())).equals(this.getG()) && (new Double(((Col)obj).getB())).equals(this.getB()) && (new Double(((Col)obj).getA())).equals(this.getA());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getR(), this.getG(), this.getB(), this.getA()});
    }

    public boolean eEquals(Col col, double epsilon) {
        return this == col || col != null && Compare.eEquals(this.getR(), col.getR(), epsilon) && Compare.eEquals(this.getG(), col.getG(), epsilon) && Compare.eEquals(this.getB(), col.getB(), epsilon) && Compare.eEquals(this.getA(), col.getA(), epsilon);
    }

    public boolean eEquals(Col col) {
        return this.eEquals(col, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + "," + format + "," + format + "," + format + ")", this.r, this.g, this.b, this.a);
    }
}
