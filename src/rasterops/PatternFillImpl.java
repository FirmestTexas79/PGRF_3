package rasterops;

import java.awt.*;

public class PatternFillImpl implements PaternFill<Integer> {

    @Override
    public Integer fill(int c1, int r1) {
        return Color.HSBtoRGB(c1 * 0.0015f + r1 * 0.0015f, 1f, 1f);
    }

}
