//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4OrthoRH extends Mat4Identity {
    public Mat4OrthoRH(double w, double h, double zn, double zf) {
        this.mat[0][0] = 2.0 / w;
        this.mat[1][1] = 2.0 / h;
        this.mat[2][2] = 1.0 / (zn - zf);
        this.mat[3][2] = zn / (zn - zf);
    }
}
