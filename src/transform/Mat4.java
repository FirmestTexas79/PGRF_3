//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class Mat4 {
    protected final double[][] mat;

    public Mat4() {
        this(0.0);
    }

    public Mat4(double value) {
        this.mat = new double[4][4];

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.mat[i][j] = value;
            }
        }

    }

    public Mat4(Point3D p1, Point3D p2, Point3D p3, Point3D p4) {
        this.mat = new double[4][4];
        this.mat[0][0] = p1.getX();
        this.mat[0][1] = p1.getY();
        this.mat[0][2] = p1.getZ();
        this.mat[0][3] = p1.getW();
        this.mat[1][0] = p2.getX();
        this.mat[1][1] = p2.getY();
        this.mat[1][2] = p2.getZ();
        this.mat[1][3] = p2.getW();
        this.mat[2][0] = p3.getX();
        this.mat[2][1] = p3.getY();
        this.mat[2][2] = p3.getZ();
        this.mat[2][3] = p3.getW();
        this.mat[3][0] = p4.getX();
        this.mat[3][1] = p4.getY();
        this.mat[3][2] = p4.getZ();
        this.mat[3][3] = p4.getW();
    }

    public Mat4(Mat4 m) {
        this.mat = new double[4][4];

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.mat[i][j] = m.mat[i][j];
            }
        }

    }

    public Mat4(Mat3 m) {
        this.mat = new double[4][4];

        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.mat[i][j] = m.mat[i][j];
            }
        }

        for(i = 0; i < 3; ++i) {
            this.mat[i][3] = 0.0;
            this.mat[3][i] = 0.0;
        }

        this.mat[3][3] = 1.0;
    }

    public Mat4(double[] m) {
        this.mat = new double[4][4];

        assert m.length >= 16;

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.mat[i][j] = m[i * 4 + j];
            }
        }

    }

    public Mat4(double[][] m) {
        this.mat = new double[4][4];

        assert m.length >= 4;

        for(int i = 0; i < 4; ++i) {
            assert m[i].length >= 4;

            for(int j = 0; j < 4; ++j) {
                this.mat[i][j] = m[i][j];
            }
        }

    }

    public Mat4 add(Mat4 m) {
        Mat4 result = new Mat4();

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                result.mat[i][j] = this.mat[i][j] + m.mat[i][j];
            }
        }

        return result;
    }

    public Mat4 mul(double d) {
        Mat4 result = new Mat4();

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                result.mat[i][j] = this.mat[i][j] * d;
            }
        }

        return result;
    }

    public Mat4 mul(Mat4 m) {
        Mat4 result = new Mat4();

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                double sum = 0.0;

                for(int k = 0; k < 4; ++k) {
                    sum += this.mat[i][k] * m.mat[k][j];
                }

                result.mat[i][j] = sum;
            }
        }

        return result;
    }

    public Mat4 withElement(int row, int column, double element) {
        assert row >= 0 && row < 4 && column >= 0 && column < 4;

        Mat4 result = new Mat4(this);
        result.mat[row][column] = element;
        return result;
    }

    public Mat4 withRow(int index, Point3D row) {
        assert index >= 0 && index < 4;

        Mat4 result = new Mat4(this);
        result.mat[index][0] = row.getX();
        result.mat[index][1] = row.getY();
        result.mat[index][2] = row.getZ();
        result.mat[index][3] = row.getW();
        return result;
    }

    public Mat4 withColumn(int index, Point3D column) {
        assert index >= 0 && index < 4;

        Mat4 result = new Mat4(this);
        result.mat[0][index] = column.getX();
        result.mat[1][index] = column.getY();
        result.mat[2][index] = column.getZ();
        result.mat[3][index] = column.getW();
        return result;
    }

    public double get(int row, int column) {
        assert row >= 0 && row < 4 && column >= 0 && column < 4;

        return this.mat[row][column];
    }

    public Point3D getRow(int row) {
        assert row >= 0 && row < 4;

        return new Point3D(this.mat[row][0], this.mat[row][1], this.mat[row][2], this.mat[row][3]);
    }

    public Point3D getColumn(int column) {
        assert column >= 0 && column < 4;

        return new Point3D(this.mat[0][column], this.mat[1][column], this.mat[2][column], this.mat[3][column]);
    }

    public Vec3D getTranslate() {
        return new Vec3D(this.mat[3][0], this.mat[3][1], this.mat[3][2]);
    }

    public Mat4 transpose() {
        Mat4 result = new Mat4();

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                result.mat[i][j] = this.mat[j][i];
            }
        }

        return result;
    }

    public double det() {
        double s0 = this.mat[0][0] * this.mat[1][1] - this.mat[1][0] * this.mat[0][1];
        double s1 = this.mat[0][0] * this.mat[1][2] - this.mat[1][0] * this.mat[0][2];
        double s2 = this.mat[0][0] * this.mat[1][3] - this.mat[1][0] * this.mat[0][3];
        double s3 = this.mat[0][1] * this.mat[1][2] - this.mat[1][1] * this.mat[0][2];
        double s4 = this.mat[0][1] * this.mat[1][3] - this.mat[1][1] * this.mat[0][3];
        double s5 = this.mat[0][2] * this.mat[1][3] - this.mat[1][2] * this.mat[0][3];
        double c5 = this.mat[2][2] * this.mat[3][3] - this.mat[3][2] * this.mat[2][3];
        double c4 = this.mat[2][1] * this.mat[3][3] - this.mat[3][1] * this.mat[2][3];
        double c3 = this.mat[2][1] * this.mat[3][2] - this.mat[3][1] * this.mat[2][2];
        double c2 = this.mat[2][0] * this.mat[3][3] - this.mat[3][0] * this.mat[2][3];
        double c1 = this.mat[2][0] * this.mat[3][2] - this.mat[3][0] * this.mat[2][2];
        double c0 = this.mat[2][0] * this.mat[3][1] - this.mat[3][0] * this.mat[2][1];
        return s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
    }

    public Optional<Mat4> inverse() {
        double s0 = this.mat[0][0] * this.mat[1][1] - this.mat[1][0] * this.mat[0][1];
        double s1 = this.mat[0][0] * this.mat[1][2] - this.mat[1][0] * this.mat[0][2];
        double s2 = this.mat[0][0] * this.mat[1][3] - this.mat[1][0] * this.mat[0][3];
        double s3 = this.mat[0][1] * this.mat[1][2] - this.mat[1][1] * this.mat[0][2];
        double s4 = this.mat[0][1] * this.mat[1][3] - this.mat[1][1] * this.mat[0][3];
        double s5 = this.mat[0][2] * this.mat[1][3] - this.mat[1][2] * this.mat[0][3];
        double c5 = this.mat[2][2] * this.mat[3][3] - this.mat[3][2] * this.mat[2][3];
        double c4 = this.mat[2][1] * this.mat[3][3] - this.mat[3][1] * this.mat[2][3];
        double c3 = this.mat[2][1] * this.mat[3][2] - this.mat[3][1] * this.mat[2][2];
        double c2 = this.mat[2][0] * this.mat[3][3] - this.mat[3][0] * this.mat[2][3];
        double c1 = this.mat[2][0] * this.mat[3][2] - this.mat[3][0] * this.mat[2][2];
        double c0 = this.mat[2][0] * this.mat[3][1] - this.mat[3][0] * this.mat[2][1];
        double det = s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
        if (det == 0.0) {
            return Optional.empty();
        } else {
            double iDet = 1.0 / det;
            Mat4 res = new Mat4();
            res.mat[0][0] = (this.mat[1][1] * c5 - this.mat[1][2] * c4 + this.mat[1][3] * c3) * iDet;
            res.mat[0][1] = (-this.mat[0][1] * c5 + this.mat[0][2] * c4 - this.mat[0][3] * c3) * iDet;
            res.mat[0][2] = (this.mat[3][1] * s5 - this.mat[3][2] * s4 + this.mat[3][3] * s3) * iDet;
            res.mat[0][3] = (-this.mat[2][1] * s5 + this.mat[2][2] * s4 - this.mat[2][3] * s3) * iDet;
            res.mat[1][0] = (-this.mat[1][0] * c5 + this.mat[1][2] * c2 - this.mat[1][3] * c1) * iDet;
            res.mat[1][1] = (this.mat[0][0] * c5 - this.mat[0][2] * c2 + this.mat[0][3] * c1) * iDet;
            res.mat[1][2] = (-this.mat[3][0] * s5 + this.mat[3][2] * s2 - this.mat[3][3] * s1) * iDet;
            res.mat[1][3] = (this.mat[2][0] * s5 - this.mat[2][2] * s2 + this.mat[2][3] * s1) * iDet;
            res.mat[2][0] = (this.mat[1][0] * c4 - this.mat[1][1] * c2 + this.mat[1][3] * c0) * iDet;
            res.mat[2][1] = (-this.mat[0][0] * c4 + this.mat[0][1] * c2 - this.mat[0][3] * c0) * iDet;
            res.mat[2][2] = (this.mat[3][0] * s4 - this.mat[3][1] * s2 + this.mat[3][3] * s0) * iDet;
            res.mat[2][3] = (-this.mat[2][0] * s4 + this.mat[2][1] * s2 - this.mat[2][3] * s0) * iDet;
            res.mat[3][0] = (-this.mat[1][0] * c3 + this.mat[1][1] * c1 - this.mat[1][2] * c0) * iDet;
            res.mat[3][1] = (this.mat[0][0] * c3 - this.mat[0][1] * c1 + this.mat[0][2] * c0) * iDet;
            res.mat[3][2] = (-this.mat[3][0] * s3 + this.mat[3][1] * s1 - this.mat[3][2] * s0) * iDet;
            res.mat[3][3] = (this.mat[2][0] * s3 - this.mat[2][1] * s1 + this.mat[2][2] * s0) * iDet;
            return Optional.of(res);
        }
    }

    public float[] floatArray() {
        float[] result = new float[16];

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                result[i * 4 + j] = (float)this.mat[i][j];
            }
        }

        return result;
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Mat4 && ((Mat4)obj).getRow(0).equals(this.getRow(0)) && ((Mat4)obj).getRow(1).equals(this.getRow(1)) && ((Mat4)obj).getRow(2).equals(this.getRow(2)) && ((Mat4)obj).getRow(3).equals(this.getRow(3));
    }

    public int hashCode() {
        return Arrays.hashCode(this.floatArray());
    }

    public boolean eEquals(Mat4 mat, double epsilon) {
        return this == mat || mat != null && this.getRow(0).eEquals(mat.getRow(0), epsilon) && this.getRow(1).eEquals(mat.getRow(1), epsilon) && this.getRow(2).eEquals(mat.getRow(2), epsilon) && this.getRow(3).eEquals(mat.getRow(3), epsilon);
    }

    public boolean eEquals(Mat4 mat) {
        return this.eEquals(mat, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "{{" + format + "," + format + "," + format + "," + format + "},\n {" + format + "," + format + "," + format + "," + format + "},\n {" + format + "," + format + "," + format + "," + format + "},\n {" + format + "," + format + "," + format + "," + format + "}}", this.mat[0][0], this.mat[0][1], this.mat[0][2], this.mat[0][3], this.mat[1][0], this.mat[1][1], this.mat[1][2], this.mat[1][3], this.mat[2][0], this.mat[2][1], this.mat[2][2], this.mat[2][3], this.mat[3][0], this.mat[3][1], this.mat[3][2], this.mat[3][3]);
    }
}
