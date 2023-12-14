//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class Mat3 {
    protected final double[][] mat;

    public Mat3() {
        this(0.0);
    }

    public Mat3(double value) {
        this.mat = new double[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.mat[i][j] = value;
            }
        }

    }

    public Mat3(Vec3D v1, Vec3D v2, Vec3D v3) {
        this.mat = new double[3][3];
        this.mat[0][0] = v1.getX();
        this.mat[0][1] = v1.getY();
        this.mat[0][2] = v1.getZ();
        this.mat[1][0] = v2.getX();
        this.mat[1][1] = v2.getY();
        this.mat[1][2] = v2.getZ();
        this.mat[2][0] = v3.getX();
        this.mat[2][1] = v3.getY();
        this.mat[2][2] = v3.getZ();
    }

    public Mat3(Mat3 m) {
        this.mat = new double[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.mat[i][j] = m.mat[i][j];
            }
        }

    }

    public Mat3(double[] m) {
        this.mat = new double[3][3];

        assert m.length >= 9;

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.mat[i][j] = m[i * 3 + j];
            }
        }

    }

    public Mat3(double[][] m) {
        this.mat = new double[3][3];

        assert m.length >= 3;

        for(int i = 0; i < 3; ++i) {
            assert m[i].length >= 3;

            for(int j = 0; j < 3; ++j) {
                this.mat[i][j] = m[i][j];
            }
        }

    }

    public Mat3(Mat4 m) {
        this.mat = new double[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.mat[i][j] = m.mat[i][j];
            }
        }

    }

    public Mat3 add(Mat3 m) {
        Mat3 result = new Mat3();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                result.mat[i][j] = this.mat[i][j] + m.mat[i][j];
            }
        }

        return result;
    }

    public Mat3 mul(double d) {
        Mat3 result = new Mat3();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                result.mat[i][j] = this.mat[i][j] * d;
            }
        }

        return result;
    }

    public Mat3 mul(Mat3 m) {
        Mat3 result = new Mat3();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                double sum = 0.0;

                for(int k = 0; k < 3; ++k) {
                    sum += this.mat[i][k] * m.mat[k][j];
                }

                result.mat[i][j] = sum;
            }
        }

        return result;
    }

    public Mat3 withElement(int row, int column, double element) {
        assert row >= 0 && row < 3 && column >= 0 && column < 3;

        Mat3 result = new Mat3(this);
        result.mat[row][column] = element;
        return result;
    }

    public Mat3 withRow(int index, Vec3D row) {
        assert index >= 0 && index < 3;

        Mat3 result = new Mat3(this);
        result.mat[index][0] = row.getX();
        result.mat[index][1] = row.getY();
        result.mat[index][2] = row.getZ();
        return result;
    }

    public Mat3 withColumn(int index, Vec3D column) {
        assert index >= 0 && index < 3;

        Mat3 result = new Mat3(this);
        result.mat[0][index] = column.getX();
        result.mat[1][index] = column.getY();
        result.mat[2][index] = column.getZ();
        return result;
    }

    public double get(int row, int column) {
        assert row >= 0 && row < 3 && column >= 0 && column < 3;

        return this.mat[row][column];
    }

    public Vec3D getRow(int row) {
        assert row >= 0 && row < 3;

        return new Vec3D(this.mat[row][0], this.mat[row][1], this.mat[row][2]);
    }

    public Vec3D getColumn(int column) {
        assert column >= 0 && column < 3;

        return new Vec3D(this.mat[0][column], this.mat[1][column], this.mat[2][column]);
    }

    public Mat3 transpose() {
        Mat3 result = new Mat3();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                result.mat[i][j] = this.mat[j][i];
            }
        }

        return result;
    }

    public double det() {
        return this.mat[0][0] * (this.mat[1][1] * this.mat[2][2] - this.mat[2][1] * this.mat[1][2]) - this.mat[0][1] * (this.mat[1][0] * this.mat[2][2] - this.mat[2][0] * this.mat[1][2]) + this.mat[0][2] * (this.mat[1][0] * this.mat[2][1] - this.mat[2][0] * this.mat[1][1]);
    }

    public Optional<Mat3> inverse() {
        double det = this.det();
        if (det == 0.0) {
            return Optional.empty();
        } else {
            Mat3 res = new Mat3();
            res.mat[0][0] = (this.mat[1][1] * this.mat[2][2] - this.mat[1][2] * this.mat[2][1]) / det;
            res.mat[0][1] = (this.mat[0][2] * this.mat[2][1] - this.mat[0][1] * this.mat[2][2]) / det;
            res.mat[0][2] = (this.mat[0][1] * this.mat[1][2] - this.mat[0][2] * this.mat[1][1]) / det;
            res.mat[1][0] = (this.mat[1][2] * this.mat[2][0] - this.mat[1][0] * this.mat[2][2]) / det;
            res.mat[1][1] = (this.mat[0][0] * this.mat[2][2] - this.mat[0][2] * this.mat[2][0]) / det;
            res.mat[1][2] = (this.mat[0][2] * this.mat[1][0] - this.mat[0][0] * this.mat[1][2]) / det;
            res.mat[2][0] = (this.mat[1][0] * this.mat[2][1] - this.mat[1][1] * this.mat[2][0]) / det;
            res.mat[2][1] = (this.mat[0][1] * this.mat[2][0] - this.mat[0][0] * this.mat[2][1]) / det;
            res.mat[2][2] = (this.mat[0][0] * this.mat[1][1] - this.mat[0][1] * this.mat[1][0]) / det;
            return Optional.of(res);
        }
    }

    public float[] floatArray() {
        float[] result = new float[9];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                result[i * 3 + j] = (float)this.mat[i][j];
            }
        }

        return result;
    }

    public boolean equals(Object obj) {
        return this == obj || obj != null && obj instanceof Mat3 && ((Mat3)obj).getRow(0).equals(this.getRow(0)) && ((Mat3)obj).getRow(1).equals(this.getRow(1)) && ((Mat3)obj).getRow(2).equals(this.getRow(2));
    }

    public int hashCode() {
        return Arrays.hashCode(this.floatArray());
    }

    public boolean eEquals(Mat3 mat, double epsilon) {
        return this == mat || mat != null && this.getRow(0).eEquals(mat.getRow(0), epsilon) && this.getRow(1).eEquals(mat.getRow(1), epsilon) && this.getRow(2).eEquals(mat.getRow(2), epsilon);
    }

    public boolean eEquals(Mat3 mat) {
        return this.eEquals(mat, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "{{" + format + "," + format + "," + format + "},{" + format + "," + format + "," + format + "},\n{" + format + "," + format + "," + format + "}\n", this.mat[0][0], this.mat[0][1], this.mat[0][2], this.mat[1][0], this.mat[1][1], this.mat[1][2], this.mat[2][0], this.mat[2][1], this.mat[2][2]);
    }
}
