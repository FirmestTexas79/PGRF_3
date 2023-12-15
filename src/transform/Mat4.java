package transform;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class Mat4 {
    protected final double mat[][] = new double[4][4];

    /**
     * Vytvoří nulovou matici 4x4
     */
    public Mat4() {
        this(0.0);
    }

    /**
     * Vytvoří matici 4x4 se všemi prvky nastavenými na zadanou hodnotu
     *
     * @param value
     *            hodnota všech prvků matice
     */
    public Mat4(final double value) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                mat[i][j] = value;
    }

    /**
     * Vytvoří matici 4x4 ze vstupních vektorů
     *
     * @param p1
     *            vektor řádku 0 (M00, M01, M02, M03)
     * @param p2
     *            vektor řádku 1 (M10, M11, M12, M13)
     * @param p3
     *            vektor řádku 2 (M20, M21, M22, M23)
     * @param p4
     *            vektor řádku 3 (M30, M31, M32, M33)
     */
    public Mat4(final Point3D p1, final Point3D p2, final Point3D p3, final Point3D p4) {
        mat[0][0] = p1.getX();
        mat[0][1] = p1.getY();
        mat[0][2] = p1.getZ();
        mat[0][3] = p1.getW();
        mat[1][0] = p2.getX();
        mat[1][1] = p2.getY();
        mat[1][2] = p2.getZ();
        mat[1][3] = p2.getW();
        mat[2][0] = p3.getX();
        mat[2][1] = p3.getY();
        mat[2][2] = p3.getZ();
        mat[2][3] = p3.getW();
        mat[3][0] = p4.getX();
        mat[3][1] = p4.getY();
        mat[3][2] = p4.getZ();
        mat[3][3] = p4.getW();
    }

    /**
     * Vytvoří matici 4x4 jako klon zadané matice 4x4
     *
     * @param m
     *            matice 4x4 klonování
     */
    public Mat4(final Mat4 m) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                mat[i][j] = m.mat[i][j];
    }

    /**
     * Vytvoří matici 4x4 kopírováním 3x3 submatice jednotkové matice 4x4
     * zadané 3x3 matice
     *
     * @param m
     *            3x3 matice k kopírování do submatice
     */
    public Mat4(final Mat3 m) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3 ; j++)
                mat[i][j] = m.mat[i][j];
        for (int i = 0; i < 3; i++){
            mat[i][3] = 0;
            mat[3][i] = 0;
        }
        mat[3][3] = 1;
    }


    /**
     * Vytvoří matici 4x4 z řádkového pole 16 prvků typu double
     *
     * @param m
     *            pole double délky 16 (předpokládáno)
     */
    public Mat4(final double[] m) {
        assert(m.length >= 16);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                mat[i][j] = m[i * 4 + j];
    }

    /**
     * Vytvoří matici 4x4 z dvourozměrného pole prvků typu double o velikosti 4x4
     *
     * @param m
     *            2D pole double o velikosti 4x4 (předpokládáno)
     */
    public Mat4(final double[][] m) {
        assert(m.length >= 4);
        for (int i = 0; i < 4; i++){
            assert(m[i].length >= 4);
            for (int j = 0; j < 4; j++)
                mat[i][j] = m[i][j];
        }
    }

    /**
     * Vrací výsledek elementárního součtu s danou maticí 4x4
     *
     * @param m
     *            matice 4x4
     * @return nová instance Mat4
     */
    public Mat4 add(final Mat4 m) {
        final Mat4 result = new Mat4();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                result.mat[i][j] = mat[i][j] + m.mat[i][j];
        return result;
    }

    /**
     * Vrací výsledek elementárního násobení skalárem dané hodnoty
     *
     * @param d
     *            hodnota skaláru typu double
     * @return nová instance Mat4
     */
    public Mat4 mul(final double d) {
        final Mat4 result = new Mat4();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                result.mat[i][j] = mat[i][j] * d;
        return result;
    }

    /**
     * Vrací výsledek maticového násobení danou maticí 4x4
     *
     * @param m
     *            matice 4x4
     * @return nová instance Mat4
     */
    public Mat4 mul(final Mat4 m) {
        final Mat4 result = new Mat4();
        double sum;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                sum = 0.0;
                for (int k = 0; k < 4; k++)
                    sum += mat[i][k] * m.mat[k][j];
                result.mat[i][j] = sum;
            }
        return result;
    }

    /**
     * Vrací klon této matice s daným prvkem nahrazeným zadanou hodnotou
     *
     * @param row
     *            0-based index řádku prvku ke změně
     * @param column
     *            0-based index sloupce prvku ke změně
     * @param element
     *            nová hodnota prvku
     * @return nová instance Mat4
     */
    public Mat4 withElement(final int row, final int column, final double element) {
        assert(row >= 0 && row < 4 && column >= 0 && column < 4);
        final Mat4 result = new Mat4(this);
        result.mat[row][column] = element;
        return result;
    }

    /**
     * Vrací klon této matice s daným řádkem nahrazeným zadaným vektorem
     *
     * @param index
     *            0-based index řádku
     * @param row
     *            nový řádkový vektor
     * @return nová instance Mat4
     */
    public Mat4 withRow(final int index, final Point3D row) {
        assert(index >= 0 && index < 4);
        final Mat4 result = new Mat4(this);
        result.mat[index][0] = row.getX();
        result.mat[index][1] = row.getY();
        result.mat[index][2] = row.getZ();
        result.mat[index][3] = row.getW();
        return result;
    }

    /**
     * Vrací klon této matice s daným sloupcem nahrazeným zadaným vektorem
     *
     * @param index
     *            0-based index sloupce
     * @param column
     *            nový sloupcový vektor
     * @return nová instance Mat4
     */
    public Mat4 withColumn(final int index, final Point3D column) {
        assert(index >= 0 && index < 4);
        final Mat4 result = new Mat4(this);
        result.mat[0][index] = column.getX();
        result.mat[1][index] = column.getY();
        result.mat[2][index] = column.getZ();
        result.mat[3][index] = column.getW();
        return result;
    }

    /**
     * Vrací hodnotu prvku matice
     *
     * @param row
     *            0-based index řádku prvku
     * @param column
     *            0-based index sloupce prvku
     * @return hodnota prvku
     */
    public double get(final int row, final int column) {
        assert(row >= 0 && row < 4 && column >= 0 && column < 4);
        return mat[row][column];
    }

    /**
     * Vrací řádkový vektor na daném indexu
     *
     * @param row
     *            0-based index řádku
     * @return matice řádek jako novou instanci Point3D
     */
    public Point3D getRow(final int row) {
        assert(row >= 0 && row < 4);
        return new Point3D(mat[row][0], mat[row][1], mat[row][2], mat[row][3]);
    }

    /**
     * Vrací sloupcový vektor na daném indexu
     *
     * @param column
     *            0-based index sloupce
     * @return matice sloupec jako novou instanci Point3D
     */
    public Point3D getColumn(final int column) {
        assert(column >= 0 && column < 4);
        return new Point3D(mat[0][column], mat[1][column], mat[2][column], mat[3][column]);
    }

    /**
     * Vrací translační vektor matice, (poslední řádek)
     *
     * @return matice sloupec jako novou instanci Point3D
     */
    public Vec3D getTranslate() {
        return new Vec3D(mat[3][0], mat[3][1], mat[3][2]);
    }

    /**
     * Vrací transpozici této matice
     *
     * @return novou instanci Mat3
     */
    public Mat4 transpose() {
        final Mat4 result = new Mat4();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                result.mat[i][j] = mat[j][i];
            }
        return result;
    }

    /**
     * Vrací determinant této matice
     *
     * @return hodnota determinantu typu double
     */
    public double det() {
        final double s0 = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        final double s1 = mat[0][0] * mat[1][2] - mat[1][0] * mat[0][2];
        final double s2 = mat[0][0] * mat[1][3] - mat[1][0] * mat[0][3];
        final double s3 = mat[0][1] * mat[1][2] - mat[1][1] * mat[0][2];
        final double s4 = mat[0][1] * mat[1][3] - mat[1][1] * mat[0][3];
        final double s5 = mat[0][2] * mat[1][3] - mat[1][2] * mat[0][3];

        final double c5 = mat[2][2] * mat[3][3] - mat[3][2] * mat[2][3];
        final double c4 = mat[2][1] * mat[3][3] - mat[3][1] * mat[2][3];
        final double c3 = mat[2][1] * mat[3][2] - mat[3][1] * mat[2][2];
        final double c2 = mat[2][0] * mat[3][3] - mat[3][0] * mat[2][3];
        final double c1 = mat[2][0] * mat[3][2] - mat[3][0] * mat[2][2];
        final double c0 = mat[2][0] * mat[3][1] - mat[3][0] * mat[2][1];
        return s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
    }

    /**
     * Vrací inverzi této matice, pokud existuje, nebo prázdný Optional.
     *
     * @return nová instance Optional<Mat4>
     */
    public Optional<Mat4> inverse() {
        final double s0 = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        final double s1 = mat[0][0] * mat[1][2] - mat[1][0] * mat[0][2];
        final double s2 = mat[0][0] * mat[1][3] - mat[1][0] * mat[0][3];
        final double s3 = mat[0][1] * mat[1][2] - mat[1][1] * mat[0][2];
        final double s4 = mat[0][1] * mat[1][3] - mat[1][1] * mat[0][3];
        final double s5 = mat[0][2] * mat[1][3] - mat[1][2] * mat[0][3];

        final double c5 = mat[2][2] * mat[3][3] - mat[3][2] * mat[2][3];
        final double c4 = mat[2][1] * mat[3][3] - mat[3][1] * mat[2][3];
        final double c3 = mat[2][1] * mat[3][2] - mat[3][1] * mat[2][2];
        final double c2 = mat[2][0] * mat[3][3] - mat[3][0] * mat[2][3];
        final double c1 = mat[2][0] * mat[3][2] - mat[3][0] * mat[2][2];
        final double c0 = mat[2][0] * mat[3][1] - mat[3][0] * mat[2][1];
        final double det = s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;

        if (det == 0)
            return Optional.empty();

        final double iDet = 1 / det;
        final Mat4 res = new Mat4();
        res.mat[0][0] = (mat[1][1] * c5 - mat[1][2] * c4 + mat[1][3] * c3) * iDet;
        res.mat[0][1] = (-mat[0][1] * c5 + mat[0][2] * c4 - mat[0][3] * c3) * iDet;
        res.mat[0][2] = (mat[3][1] * s5 - mat[3][2] * s4 + mat[3][3] * s3) * iDet;
        res.mat[0][3] = (-mat[2][1] * s5 + mat[2][2] * s4 - mat[2][3] * s3) * iDet;

        res.mat[1][0] = (-mat[1][0] * c5 + mat[1][2] * c2 - mat[1][3] * c1) * iDet;
        res.mat[1][1] = (mat[0][0] * c5 - mat[0][2] * c2 + mat[0][3] * c1) * iDet;
        res.mat[1][2] = (-mat[3][0] * s5 + mat[3][2] * s2 - mat[3][3] * s1) * iDet;
        res.mat[1][3] = (mat[2][0] * s5 - mat[2][2] * s2 + mat[2][3] * s1) * iDet;

        res.mat[2][0] = (mat[1][0] * c4 - mat[1][1] * c2 + mat[1][3] * c0) * iDet;
        res.mat[2][1] = (-mat[0][0] * c4 + mat[0][1] * c2 - mat[0][3] * c0) * iDet;
        res.mat[2][2] = (mat[3][0] * s4 - mat[3][1] * s2 + mat[3][3] * s0) * iDet;
        res.mat[2][3] = (-mat[2][0] * s4 + mat[2][1] * s2 - mat[2][3] * s0) * iDet;

        res.mat[3][0] = (-mat[1][0] * c3 + mat[1][1] * c1 - mat[1][2] * c0) * iDet;
        res.mat[3][1] = (mat[0][0] * c3 - mat[0][1] * c1 + mat[0][2] * c0) * iDet;
        res.mat[3][2] = (-mat[3][0] * s3 + mat[3][1] * s1 - mat[3][2] * s0) * iDet;
        res.mat[3][3] = (mat[2][0] * s3 - mat[2][1] * s1 + mat[2][2] * s0) * iDet;
        return Optional.of(res);
    }

    /**
     * Vrací tuto matici uloženou v řádku ve floatovém poli.
     *
     * @return nové floatové pole
     */
    public float[] floatArray() {
        final float[] result = new float[16];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                result[i * 4 + j] = ((float) mat[i][j]);
        return result;
    }

    /**
     * Porovnává tento objekt s daným objektem.
     *
     * @param obj objekt k porovnání.
     * @return {@code true}, pokud jsou objekty stejné; {@code false} jinak.
     */
    @Override
    public boolean equals(Object obj) {
        return (this == obj) || (obj != null) && (obj instanceof Mat4)
                && (((Mat4) obj).getRow(0)).equals(getRow(0))
                && (((Mat4) obj).getRow(1)).equals(getRow(1))
                && (((Mat4) obj).getRow(2)).equals(getRow(2))
                && (((Mat4) obj).getRow(3)).equals(getRow(3));
    }

    /**
     * Vrací hodnotu hash kódu pro objekt.
     *
     * @return hodnota hash kódu pro tento objekt.
     */
    @Override
    public int hashCode(){
        return Arrays.hashCode(floatArray());
    }

    /**
     * Porovnává tuto matici s danou maticí s maximálním rozdílem epsilon mezi
     * aktuální a specifikovanou hodnotou, pro který jsou obě matice stále považovány za shodné.
     *
     * @param mat matice k porovnání.
     * @param epsilon maximální povolený rozdíl mezi skutečnou a specifikovanou hodnotou
     * @return {@code true}, pokud jsou objekty považovány za shodné; {@code false} jinak.
     */
    public boolean eEquals(Mat4 mat, double epsilon) {
        return (this == mat) || (mat != null)
                && getRow(0).eEquals(mat.getRow(0), epsilon)
                && getRow(1).eEquals(mat.getRow(1), epsilon)
                && getRow(2).eEquals(mat.getRow(2), epsilon)
                && getRow(3).eEquals(mat.getRow(3), epsilon);
    }

    /**
     * Porovnává tuto matici s danou maticí.
     *
     * @param mat matice k porovnání.
     * @return {@code true}, pokud jsou objekty považovány za shodné; {@code false} jinak.
     */
    public boolean eEquals(Mat4 mat) {
        return eEquals(mat, Compare.EPSILON);
    }

    /**
     * Vrací textovou reprezentaci této matice.
     *
     * @return hodnoty oddělené čárkami s plovoucí desetinnou čárkou na řádku, řádky oddělené čárkou v složených závorkách
     */
    @Override
    public String toString() {
        return toString("%4.1f");
    }

    /**
     * Vrací textovou reprezentaci této matice s prvky formátovanými
     * podle zadaného formátu, viz
     * {@link java.lang.String#format(String, Object...)}
     *
     * @param format řetězcový formát použitý pro každý prvek
     * @return hodnoty oddělené čárkami s plovoucí desetinnou čárkou na řádku, řádky oddělené čárkou v složených závorkách
     */
    public String toString(final String format) {
        return String.format(Locale.US, "{{" + format + "," + format + "," + format + "," + format + "},\n" +
                        " {" + format + "," + format + "," + format + "," + format + "},\n" +
                        " {" + format + "," + format + "," + format + "," + format + "},\n" +
                        " {" + format + "," + format + "," + format + "," + format + "}}",
                mat[0][0], mat[0][1], mat[0][2], mat[0][3],
                mat[1][0], mat[1][1], mat[1][2], mat[1][3],
                mat[2][0], mat[2][1], mat[2][2], mat[2][3],
                mat[3][0], mat[3][1], mat[3][2], mat[3][3]);
    }

}
