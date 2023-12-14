//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4PerspRH extends Mat4Identity {
    public Mat4PerspRH(double alpha, double k, double zn, double zf) {
        double h = 1.0 / Math.tan(alpha / 2.0);
        double w = k * h;
        this.mat[0][0] = w;
        this.mat[1][1] = h;
        this.mat[2][2] = zf / (zn - zf);
        this.mat[3][2] = zn * zf / (zn - zf);
        this.mat[2][3] = -1.0;
        this.mat[3][3] = 0.0;
    }
}
