//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Mat4Identity extends Mat4 {
    public Mat4Identity() {
        for(int i = 0; i < 4; ++i) {
            this.mat[i][i] = 1.0;
        }

    }
}
