//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;
import java.util.Objects;

public class Quat {
    protected final double r;
    protected final double i;
    protected final double j;
    protected final double k;

    public Quat() {
        this.i = this.j = this.k = this.r = 0.0;
    }

    public Quat(double r, double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.r = r;
    }

    public Quat(double r, Vec3D v) {
        this.i = v.getX();
        this.j = v.getY();
        this.k = v.getZ();
        this.r = r;
    }

    public Quat(Quat q) {
        this.i = q.i;
        this.j = q.j;
        this.k = q.k;
        this.r = q.r;
    }

    public double getR() {
        return this.r;
    }

    public double getI() {
        return this.i;
    }

    public double getJ() {
        return this.j;
    }

    public double getK() {
        return this.k;
    }

    public Vec3D getIJK() {
        return new Vec3D(this.i, this.j, this.k);
    }

    public Quat add(Quat q) {
        return new Quat(this.r + q.r, this.i + q.i, this.j + q.j, this.k + q.k);
    }

    public Quat sub(Quat q) {
        return new Quat(this.r - q.r, this.i - q.i, this.j - q.j, this.k - q.k);
    }

    public Quat mul(double a) {
        return new Quat(a * this.r, a * this.i, a * this.j, a * this.k);
    }

    public Quat mulR(Quat q) {
        return new Quat(this.r * q.r - this.i * q.i - this.j * q.j - this.k * q.k, this.r * q.i + this.i * q.r + this.j * q.k - this.k * q.j, this.r * q.j - this.i * q.k + this.j * q.r + this.k * q.i, this.r * q.k + this.i * q.j - this.j * q.i + this.k * q.r);
    }

    public Quat mulL(Quat q) {
        return new Quat(q.r * this.r - q.i * this.i - q.j * this.j - q.k * this.k, q.r * this.i + q.i * this.r + q.j * this.k - q.k * this.j, q.r * this.j + q.j * this.r + q.k * this.i - q.i * this.k, q.r * this.k + q.k * this.r + q.i * this.j - q.j * this.i);
    }

    public Quat mul(Quat q) {
        return this.mulR(q);
    }

    public Quat inverse() {
        double norm = this.norm();
        norm *= norm;
        return norm > 0.0 ? new Quat(this.r / norm, -this.i / norm, -this.j / norm, -this.k / norm) : new Quat(0.0, 0.0, 0.0, 0.0);
    }

    public Quat log() {
        if (this.i == 0.0 && this.i == 0.0 && this.i == 0.0) {
            if (this.r > 0.0) {
                return new Quat(Math.log(this.r), 0.0, 0.0, 0.0);
            } else {
                return this.r < 0.0 ? new Quat(Math.log(-this.r), 1.0, 0.0, 0.0) : new Quat();
            }
        } else {
            double s = Math.sqrt(this.i * this.i + this.j * this.j + this.k * this.k);
            double a = Math.atan2(s, this.r) / s;
            return new Quat(Math.log(this.norm()), a * this.i, a * this.j, a * this.k);
        }
    }

    public Quat exp() {
        if (this.i == 0.0 && this.j == 0.0 && this.k == 0.0) {
            return new Quat(Math.exp(this.r), 0.0, 0.0, 0.0);
        } else {
            double s = Math.sqrt(this.i * this.i + this.j * this.j + this.k * this.k);
            double cos = Math.cos(s);
            s = Math.exp(this.r) * Math.sin(s) / s;
            return new Quat(Math.exp(this.r) * cos, s * this.i, s * this.j, s * this.k);
        }
    }

    public Quat opposite() {
        return new Quat(-this.r, -this.i, -this.j, -this.k);
    }

    public double norm() {
        return Math.sqrt(this.r * this.r + this.i * this.i + this.j * this.j + this.k * this.k);
    }

    public double dot(Quat q) {
        return this.i * q.i + this.j * q.j + this.k * q.k + this.r * q.r;
    }

    public Quat normalized() {
        double norm = this.norm();
        return norm > 0.0 ? new Quat(this.r / norm, this.i / norm, this.j / norm, this.k / norm) : new Quat(0.0, 0.0, 0.0, 0.0);
    }

    public Mat4 toRotationMatrix() {
        Mat4 res = new Mat4Identity();
        this.normalized();
        res.mat[0][0] = 1.0 - 2.0 * (this.j * this.j + this.k * this.k);
        res.mat[1][0] = 2.0 * (this.i * this.j - this.r * this.k);
        res.mat[2][0] = 2.0 * (this.r * this.j + this.i * this.k);
        res.mat[0][1] = 2.0 * (this.i * this.j + this.r * this.k);
        res.mat[1][1] = 1.0 - 2.0 * (this.i * this.i + this.k * this.k);
        res.mat[2][1] = 2.0 * (this.k * this.j - this.i * this.r);
        res.mat[0][2] = 2.0 * (this.i * this.k - this.r * this.j);
        res.mat[1][2] = 2.0 * (this.k * this.j + this.i * this.r);
        res.mat[2][2] = 1.0 - 2.0 * (this.i * this.i + this.j * this.j);
        return res;
    }

    public static Quat fromRotationMatrix(Mat4 mat) {
        double diagonal = mat.mat[0][0] + mat.mat[1][1] + mat.mat[2][2];
        double r;
        double i;
        double j;
        double k;
        if (diagonal > 0.0) {
            r = 0.5 * Math.sqrt(diagonal + mat.mat[3][3]);
            i = (mat.mat[2][1] - mat.mat[1][2]) / (4.0 * r);
            j = (mat.mat[0][2] - mat.mat[2][0]) / (4.0 * r);
            k = (mat.mat[1][0] - mat.mat[0][1]) / (4.0 * r);
        } else {
            int[] indices = new int[]{1, 2, 0};
            int a = 0;
            if (mat.mat[1][1] > mat.mat[0][0]) {
                a = 1;
            }

            if (mat.mat[2][2] > mat.mat[a][a]) {
                a = 2;
            }

            int b = indices[a];
            int c = indices[b];
            diagonal = mat.mat[a][a] - mat.mat[b][b] - mat.mat[c][c] + mat.mat[3][3];
            r = 0.5 * Math.sqrt(diagonal);
            i = (mat.mat[a][b] + mat.mat[b][a]) / (4.0 * r);
            j = (mat.mat[a][c] + mat.mat[c][a]) / (4.0 * r);
            k = (mat.mat[b][c] - mat.mat[c][b]) / (4.0 * r);
        }

        return new Quat(r, i, j, k);
    }

    public static Quat fromEulerAngles(double alpha, double beta, double gamma) {
        Quat Qi = fromEulerAngle(alpha, 1.0, 0.0, 0.0);
        Quat Qj = fromEulerAngle(beta, 0.0, 1.0, 0.0);
        Quat Qk = fromEulerAngle(gamma, 0.0, 0.0, 1.0);
        return new Quat(Qk.mul(Qj).mul(Qi));
    }

    public static Quat fromEulerAngle(double angle, double x, double y, double z) {
        return new Quat(Math.cos(angle / 2.0), Math.sin(angle / 2.0) * x, Math.sin(angle / 2.0) * y, Math.sin(angle / 2.0) * z);
    }

    public static Quat fromEulerAngle(double angle, Vec3D xyz) {
        return fromEulerAngle(angle, xyz.getX(), xyz.getY(), xyz.getZ());
    }

    public Point3D toEulerAngle() {
        double angle = 2.0 * Math.acos(this.r);
        double x = this.i;
        double y = this.j;
        double z = this.k;
        double s = Math.sqrt(x * x + y * y + z * z);
        return s < 1.0E-4 ? new Point3D(angle, 1.0, 0.0, 0.0) : new Point3D(angle, x / s, y / s, z / s);
    }

    public Quat lerp(Quat q, double t) {
        if (t >= 1.0) {
            return new Quat(q);
        } else {
            return t <= 0.0 ? new Quat(this) : new Quat(this.mul(1.0 - t).add(q.mul(t)));
        }
    }

    public Quat slerp(Quat q, double t) {
        double c = this.dot(q);
        if (c > 1.0) {
            c = 1.0;
        } else if (c < -1.0) {
            c = -1.0;
        }

        double uhel = Math.acos(c);
        if (Math.abs(uhel) < 1.0E-5) {
            return new Quat(this);
        } else {
            double s = 1.0 / Math.sin(uhel);
            if (t >= 1.0) {
                return new Quat(this);
            } else {
                return t <= 0.0 ? new Quat(q) : (new Quat(this.normalized().mul(Math.sin((1.0 - t) * uhel) * s).add(q.normalized().mul(Math.sin(t * uhel) * s)))).normalized();
            }
        }
    }

    public Quat squad(Quat q, Quat q1, Quat q2, double t) {
        return new Quat(this.slerp(q, t).slerp(q1.slerp(q2, t), 2.0 * t * (1.0 - t)));
    }

    private Quat quadrangle(Quat q1, Quat q2) {
        Quat s1 = this.inverse().mul(q1);
        Quat s2 = this.inverse().mul(q2);
        return new Quat(s1.log().add(s2.log()).mul(0.0).exp());
    }

    public Quat squad2(Quat q1, Quat q2, Quat q3, double t) {
        Quat s1 = this.quadrangle(q1, q2);
        Quat s2 = q2.quadrangle(this, q3);
        return new Quat(this.slerp(q2, t).slerp(s1.slerp(s2, t), 2.0 * t * (1.0 - t)));
    }

    public boolean equals(Object obj) {
        return obj != null && obj instanceof Vec3D && (new Double(((Quat)obj).getR())).equals(this.getR()) && (new Double(((Quat)obj).getI())).equals(this.getI()) && (new Double(((Quat)obj).getJ())).equals(this.getJ()) && (new Double(((Quat)obj).getK())).equals(this.getK());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getR(), this.getI(), this.getJ(), this.getK()});
    }

    public boolean eEquals(Quat quat, double epsilon) {
        return quat != null && Compare.eEquals(this.getR(), quat.getR(), epsilon) && Compare.eEquals(this.getI(), quat.getI(), epsilon) && Compare.eEquals(this.getJ(), quat.getJ(), epsilon) && Compare.eEquals(this.getK(), quat.getK(), epsilon);
    }

    public boolean eEquals(Quat quat) {
        return this.eEquals(quat, 1.0E-15);
    }

    public String toString() {
        return this.toString("%4.1f");
    }

    public String toString(String format) {
        return String.format(Locale.US, "(" + format + "," + format + "," + format + "," + format + ")", this.r, this.i, this.j, this.k);
    }
}
