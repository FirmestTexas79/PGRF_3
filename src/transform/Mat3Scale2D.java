//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat3Scale2D extends Mat3Identity {
    public Mat3Scale2D(double x, double y) {
        this.mat[0][0] = x;
        this.mat[1][1] = y;
    }

    public Mat3Scale2D(double scale) {
        this(scale, scale);
    }

    public Mat3Scale2D(Vec2D v) {
        this(v.getX(), v.getY());
    }
}
