//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat3Transl2D extends Mat3Identity {
    public Mat3Transl2D(double x, double y) {
        this.mat[2][0] = x;
        this.mat[2][1] = y;
    }

    public Mat3Transl2D(Vec2D v) {
        this(v.getX(), v.getY());
    }
}
