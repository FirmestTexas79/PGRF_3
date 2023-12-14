package transform;

public class Bicubic {
    private final Cubic c1;
    private final Cubic c2;
    private final Cubic c3;
    private final Cubic c4;
    private final Mat4 baseMat;

    // Konstruktor pro inicializaci Bicubické plochy pomocí kontrolních bodů
    public Bicubic(Mat4 baseMat, Point3D p11, Point3D p12, Point3D p13, Point3D p14,
                   Point3D p21, Point3D p22, Point3D p23, Point3D p24,
                   Point3D p31, Point3D p32, Point3D p33, Point3D p34,
                   Point3D p41, Point3D p42, Point3D p43, Point3D p44) {
        this.baseMat = baseMat;
        this.c1 = new Cubic(baseMat, p11, p12, p13, p14);
        this.c2 = new Cubic(baseMat, p21, p22, p23, p24);
        this.c3 = new Cubic(baseMat, p31, p32, p33, p34);
        this.c4 = new Cubic(baseMat, p41, p42, p43, p44);
    }

    // Konstruktor pro inicializaci Bicubické plochy pomocí pole kontrolních bodů
    public Bicubic(Mat4 baseMat, Point3D[] points) {
        this(baseMat, points, 0);
    }

    // Konstruktor pro inicializaci Bicubické plochy pomocí pole kontrolních bodů a indexu
    public Bicubic(Mat4 baseMat, Point3D[] points, int startIndex) {
        this.baseMat = baseMat;
        this.c1 = new Cubic(baseMat, points, startIndex);
        this.c2 = new Cubic(baseMat, points, startIndex + 4);
        this.c3 = new Cubic(baseMat, points, startIndex + 8);
        this.c4 = new Cubic(baseMat, points, startIndex + 12);
    }

    // Metoda pro výpočet hodnoty na Bicubické ploše pro dané parametry u a v
    public Point3D compute(double paramU, double paramV) {
        // Omezení parametrů u a v na rozsah 0.0 až 1.0
        double u = Math.max(0.0, Math.min(paramU, 1.0));
        double v = Math.max(0.0, Math.min(paramV, 1.0));

        // Výpočet hodnot pomocí Cubic interpolací
        Point3D u1 = this.c1.compute(u);
        Point3D u2 = this.c2.compute(u);
        Point3D u3 = this.c3.compute(u);
        Point3D u4 = this.c4.compute(u);

        // Výpočet hodnoty na Bicubické ploše
        return (new Cubic(this.baseMat, u1, u2, u3, u4)).compute(v);
    }
}
