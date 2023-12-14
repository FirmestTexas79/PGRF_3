//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

public class Compare {
    protected static final double EPSILON = 1.0E-15;

    public Compare() {
    }

    public static boolean eEquals(double value1, double value2, double epsilon) {
        return Math.abs(value1 - value2) <= epsilon * Math.abs(value1) && Math.abs(value1 - value2) <= epsilon * Math.abs(value2);
    }

    public static boolean eEquals(double value1, double value2) {
        return eEquals(value1, value2, 1.0E-15);
    }
}
