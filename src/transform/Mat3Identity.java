//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat3Identity extends Mat3 {
    public Mat3Identity() {
        for(int i = 0; i < 3; ++i) {
            this.mat[i][i] = 1.0;
        }

    }
}
