//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4RotX extends Mat4Identity {
    public Mat4RotX(double alpha) {
        this.mat[1][1] = Math.cos(alpha);
        this.mat[2][2] = Math.cos(alpha);
        this.mat[2][1] = -Math.sin(alpha);
        this.mat[1][2] = Math.sin(alpha);
    }
}
