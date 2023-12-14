//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat3Rot2D extends Mat3Identity {
    public Mat3Rot2D(double alpha) {
        this.mat[0][0] = Math.cos(alpha);
        this.mat[1][1] = Math.cos(alpha);
        this.mat[1][0] = -Math.sin(alpha);
        this.mat[0][1] = Math.sin(alpha);
    }
}