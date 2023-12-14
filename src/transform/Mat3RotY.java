//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat3RotY extends Mat3Identity {
    public Mat3RotY(double alpha) {
        this.mat[0][0] = Math.cos(alpha);
        this.mat[2][2] = Math.cos(alpha);
        this.mat[2][0] = Math.sin(alpha);
        this.mat[0][2] = -Math.sin(alpha);
    }
}
