package transform;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/**
 * 3x3 matice s běžnými operacemi, neměnná
 */
public class Mat3 {
    protected final double mat[][] = new double[3][3];

    /**
     * Vytvoří nulovou 3x3 matici
     */
    public Mat3() {
        this(0.0);
    }

    /**
     * Vytvoří 3x3 matici hodnoty
     *
     * @param value
     * hodnota všech prvků matice
     */
    public Mat3(final double value) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                mat[i][j] = value;
    }

    /**
     * Vytvoří 3x3 matici ze vstupních vektorů
     *
     * @param v1
     * vektor 0 (M00, M01, M02)
     * @param v2
     * vektor 1 (M10, M11, M12)
     * @param v3
     * vektor 2 (M20, M21, M22)
     */
    public Mat3(final Vec3D v1, final Vec3D v2, final Vec3D v3) {
        mat[0][0] = v1.getX();
        mat[0][1] = v1.getY();
        mat[0][2] = v1.getZ();
        mat[1][0] = v2.getX();
        mat[1][1] = v2.getY();
        mat[1][2] = v2.getZ();
        mat[2][0] = v3.getX();
        mat[2][1] = v3.getY();
        mat[2][2] = v3.getZ();
    }

    /**
     * Vytvoří 3x3 matici jako klon dané 3x3 matice
     *
     * @param m
     * matice 3x3 k zkopírování
     */
    public Mat3(final Mat3 m) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                mat[i][j] = m.mat[i][j];
    }

    /**
     * Vytvoří 3x3 matici řádkově z pole o délce 9 prvků
     *
     * @param m
     * pole double hodnot o délce 9 (ověřeno)
     */
    public Mat3(final double[] m) {
        assert(m.length >= 9);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                mat[i][j] = m[i * 3 + j];
    }

    /**
     * Vytvoří 3x3 matici z pole 3x3 double hodnot
     *
     * @param m
     * 2D pole double hodnot o délce 3x3 (ověřeno)
     */
    public Mat3(final double[][] m) {
        assert(m.length >= 3);
        for (int i = 0; i < 3; i++) {
            assert(m[i].length >= 3);
            for (int j = 0; j < 3; j++)
                mat[i][j] = m[i][j];
        }
    }

    /**
     * Vytvoří 3x3 matici ze submatice 4x4 matice
     *
     * @param m
     * matice 4x4
     */
    public Mat3(final Mat4 m) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                mat[i][j] = m.mat[i][j];
    }

    /**
     * Vrátí výsledek elementárního sčítání s danou 3x3 maticí
     *
     * @param m
     * matice 3x3
     * @return nová instance Mat3
     */
    public Mat3 add(final Mat3 m) {
        final Mat3 result = new Mat3();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                result.mat[i][j] = mat[i][j] + m.mat[i][j];
        return result;
    }

    /**
     * Vrátí výsledek elementárního násobení skalárem
     *
     * @param d
     * skalární hodnota typu double
     * @return nová instance Mat3
     */
    public Mat3 mul(final double d) {
        final Mat3 result = new Mat3();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                result.mat[i][j] = mat[i][j] * d;
        return result;
    }

    /**
     * Vrátí výsledek maticového násobení danou 3x3 maticí
     *
     * @param m
     * matice 3x3
     * @return nová instance Mat3
     */
    public Mat3 mul(final Mat3 m) {
        final Mat3 result = new Mat3();
        double sum;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                sum = 0.0;
                for (int k = 0; k < 3; k++)
                    sum += mat[i][k] * m.mat[k][j];
                result.mat[i][j] = sum;
            }
        return result;
    }

    /**
     * Vrátí klon této matice s daným prvkem nahrazeným danou hodnotou
     *
     * @param row
     * index řádku prvku k nahrazení (počítáno od 0)
     * @param column
     * index sloupce prvku k nahrazení (počítáno od 0)
     * @param element
     * nová hodnota prvku
     * @return nová instance Mat3
     */
    public Mat3 withElement(final int row, final int column, final double element) {
        assert(row >= 0 && row < 3 && column >= 0 && column < 3);
        final Mat3 result = new Mat3(this);
        result.mat[row][column] = element;
        return result;
    }

    /**
     * Vrátí klon této matice s daným řádkem nahrazeným daným vektorem
     *
     * @param index
     * index řádku (počítáno od 0)
     * @param row
     * nový řádkový vektor
     * @return nová instance Mat3
     */
    public Mat3 withRow(final int index, final Vec3D row) {
        assert(index >= 0 && index < 3);
        final Mat3 result = new Mat3(this);
        result.mat[index][0] = row.getX();
        result.mat[index][1] = row.getY();
        result.mat[index][2] = row.getZ();
        return result;
    }

    /**
     * Vrátí klon této matice s daným sloupcem nahrazeným daným vektorem
     *
     * @param index
     * index sloupce (počítáno od 0)
     * @param column
     * nový sloupcový vektor
     * @return nová instance Mat3
     */
    public Mat3 withColumn(final int index, final Vec3D column) {
        assert(index >= 0 && index < 3);
        final Mat3 result = new Mat3(this);
        result.mat[0][index] = column.getX();
        result.mat[1][index] = column.getY();
        result.mat[2][index] = column.getZ();
        return result;
    }

    /**
     * Vrátí prvek matice
     *
     * @param row
     * index řádku prvku (počítáno od 0)
     * @param column
     * index sloupce prvku (počítáno od 0)
     * @return hodnota prvku
     */
    public double get(final int row, final int column) {
        assert(row >= 0 && row < 3 && column >= 0 && column < 3);
        return mat[row][column];
    }

    /**
     * Vrátí řádkový vektor na daném indexu
     *
     * @param row
     * index řádku (počítáno od 0)
     * @return matice řádku jako novou instanci Vec3D
     */
    public Vec3D getRow(final int row) {
        assert(row >= 0 && row < 3);
        return new Vec3D(mat[row][0], mat[row][1], mat[row][2]);
    }

    /**
     * Vrátí sloupcový vektor na daném indexu
     *
     * @param column
     * index sloupce (počítáno od 0)
     * @return matice sloupce jako novou instanci Vec3D
     */
    public Vec3D getColumn(final int column) {
        assert(column >= 0 && column < 3);
        return new Vec3D(mat[0][column], mat[1][column], mat[2][column]);
    }

    /**
     * Vrátí transpozici této matice
     *
     * @return nová instance Mat3
     */
    public Mat3 transpose() {
        final Mat3 result = new Mat3();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                result.mat[i][j] = mat[j][i];
        return result;
    }

    /**
     * Vrátí determinant této matice
     *
     * @return hodnota determinantu typu double
     */
    public double det() {
        return mat[0][0] * (mat[1][1] * mat[2][2] - mat[2][1] * mat[1][2])
                - mat[0][1] * (mat[1][0] * mat[2][2] - mat[2][0] * mat[1][2])
                + mat[0][2] * (mat[1][0] * mat[2][1] - mat[2][0] * mat[1][1]);
    }

    /**
     * Vrátí inverzi této matice, pokud existuje, nebo prázdný Optional
     *
     * @return nová instance Optional<Mat3>
     */
    public Optional<Mat3> inverse() {
        final double det = det();
        if (det == 0)
            return Optional.empty();
        final Mat3 res = new Mat3();

        res.mat[0][0] = (mat[1][1] * mat[2][2] - mat[1][2] * mat[2][1]) / det;
        res.mat[0][1] = (mat[0][2] * mat[2][1] - mat[0][1] * mat[2][2]) / det;
        res.mat[0][2] = (mat[0][1] * mat[1][2] - mat[0][2] * mat[1][1]) / det;

        res.mat[1][0] = (mat[1][2] * mat[2][0] - mat[1][0] * mat[2][2]) / det;
        res.mat[1][1] = (mat[0][0] * mat[2][2] - mat[0][2] * mat[2][0]) / det;
        res.mat[1][2] = (mat[0][2] * mat[1][0] - mat[0][0] * mat[1][2]) / det;

        res.mat[2][0] = (mat[1][0] * mat[2][1] - mat[1][1] * mat[2][0]) / det;
        res.mat[2][1] = (mat[0][1] * mat[2][0] - mat[0][0] * mat[2][1]) / det;
        res.mat[2][2] = (mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0]) / det;

        return Optional.of(res);
    }

    /**
     * Vrátí tuto matici uloženou řádkově v poli floatů
     *
     * @return nové pole floatů
     */
    public float[] floatArray() {
        final float[] result = new float[9];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                result[i * 3 + j] = ((float) mat[i][j]);
        return result;
    }

    /**
     * Porovná tuto instanci s určitým objektem.
     * @param   obj
     * 				objekt, který má být porovnán.
     * @return  {@code true} pokud jsou objekty stejné;
     *          {@code false} jinak.
     */
    @Override
    public boolean equals(Object obj) {
        return (this == obj) || (obj != null) && (obj instanceof Mat3)
                && (((Mat3) obj).getRow(0)).equals(getRow(0))
                && (((Mat3) obj).getRow(1)).equals(getRow(1))
                && (((Mat3) obj).getRow(2)).equals(getRow(2));
    }

    /**
     * Vrátí hodnotu hash kódu pro tuto instanci.
     *
     * @return  hodnota hash kódu pro tuto instanci.
     */
    @Override
    public int hashCode(){
        return Arrays.hashCode(floatArray());
    }

    /**
     * Porovná tuto instanci Mat3 s určitou instancí Mat3.
     *
     * @param mat
     *            matice, se kterou má být porovnána.
     * @param epsilon
     *            maximální odchylka mezi aktuální a specifikovanou hodnotou, při
     *            které jsou obě matice stále považovány za stejné.
     * @return {@code true} pokud jsou objekty považovány za stejné; {@code false}
     *         jinak.
     */
    public boolean eEquals(Mat3 mat, double epsilon) {
        return (this == mat) || (mat != null)
                && getRow(0).eEquals( mat.getRow(0), epsilon)
                && getRow(1).eEquals( mat.getRow(1), epsilon)
                && getRow(2).eEquals( mat.getRow(2), epsilon);
    }

    /**
     * Porovná tuto instanci Mat3 s určitou instancí Mat3.
     *
     * @param mat
     *            matice, se kterou má být porovnána.
     * @return {@code true} pokud jsou objekty považovány za stejné; {@code false}
     *         jinak.
     */
    public boolean eEquals(Mat3 mat) {
        return eEquals(mat, Compare.EPSILON);
    }

    /**
     * Vrátí řetězcovou reprezentaci této matice.
     *
     * @return čárkou oddělené hodnoty s plovoucí řádovou čárkou na řádku, řádky oddělené čárkou v závorkách.
     */
    @Override
    public String toString() {
        return toString("%4.1f");
    }

    /**
     * Vrátí řetězcovou reprezentaci této matice s hodnotami formátovanými
     * podle zadaného formátu, viz
     * {@link java.lang.String#format(String, Object...)}
     *
     * @param format
     *            řetězcový formát aplikovaný na každý prvek
     * @return čárkou oddělené hodnoty s plovoucí řádovou čárkou na řádku, řádky oddělené čárkou v závorkách.
     */
    public String toString(final String format) {
        return String.format(Locale.US, "{{"+format+","+format+","+format+"},"+
                        "{"+format+","+format+","+format+"},\n"+
                        "{"+format+","+format+","+format+"}\n",
                mat[0][0], mat[0][1], mat[0][2],
                mat[1][0], mat[1][1], mat[1][2],
                mat[2][0], mat[2][1], mat[2][2]);
    }
}