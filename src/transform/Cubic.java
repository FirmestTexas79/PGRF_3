//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Cubic {
    public static final Mat4 BEZIER = new Mat4(new double[]{-1.0, 3.0, -3.0, 1.0, 3.0, -6.0, 3.0, 0.0, -3.0, 3.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0});
    public static final Mat4 COONS = (new Mat4(new double[]{-1.0, 3.0, -3.0, 1.0, 3.0, -6.0, 3.0, 0.0, -3.0, 0.0, 3.0, 0.0, 1.0, 4.0, 1.0, 0.0})).mul(0.16666666666666666);
    public static final Mat4 FERGUSON = new Mat4(new double[]{2.0, -2.0, 1.0, 1.0, -3.0, 3.0, -2.0, -1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0});
    private final Mat4 controlMat;

    public Cubic(Mat4 baseMat, Point3D p1, Point3D p2, Point3D p3, Point3D p4) {
        this.controlMat = baseMat.mul(new Mat4(p1, p2, p3, p4));
    }

    public Cubic(Mat4 baseMat, Point3D[] points) {
        this(baseMat, points, 0);
    }

    public Cubic(Mat4 baseMat, Point3D[] points, int startIndex) {
        this.controlMat = baseMat.mul(new Mat4(points[startIndex], points[startIndex + 1], points[startIndex + 2], points[startIndex + 3]));
    }

    public Point3D compute(double param) {
        double t = param > 0.0 ? (param < 1.0 ? param : 1.0) : 0.0;
        Point3D res = (new Point3D(t * t * t, t * t, t, 1.0)).mul(this.controlMat);
        return new Point3D(res.ignoreW());
    }
}
