//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat3RotX extends Mat3Identity {
    public Mat3RotX(double alpha) {
        this.mat[1][1] = Math.cos(alpha);
        this.mat[2][2] = Math.cos(alpha);
        this.mat[2][1] = -Math.sin(alpha);
        this.mat[1][2] = Math.sin(alpha);
    }
}
