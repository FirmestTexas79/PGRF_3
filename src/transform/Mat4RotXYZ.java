//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4RotXYZ extends Mat4 {
    public Mat4RotXYZ(double alpha, double beta, double gamma) {
        super((new Mat4RotX(alpha)).mul(new Mat4RotY(beta)).mul(new Mat4RotZ(gamma)));
    }
}
