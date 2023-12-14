//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package transform;

import java.util.Locale;

public class Camera {
    private final double azimuth;
    private final double radius;
    private final double zenith;
    private final boolean firstPerson;
    private final Vec3D eye;
    private final Vec3D pos;
    private final Vec3D viewVector;
    private final Mat4 view;

    public Camera() {
        this(new Vec3D(0.0, 0.0, 0.0), 0.0, 0.0, 1.0, true);
    }

    public Camera(Camera cam, boolean firstPerson) {
        this(cam.getPosition(), cam.getAzimuth(), cam.getZenith(), cam.getRadius(), firstPerson);
    }

    public Camera(Camera cam, double radius) {
        this(cam.getPosition(), cam.getAzimuth(), cam.getZenith(), radius, cam.getFirstPerson());
    }

    public Camera(Camera cam, double azimuth, double zenith) {
        this(cam.getPosition(), azimuth, zenith, cam.getRadius(), cam.getFirstPerson());
    }

    public Camera(Camera cam, Vec3D pos) {
        this(pos, cam.getAzimuth(), cam.getZenith(), cam.getRadius(), cam.getFirstPerson());
    }

    public Camera(Vec3D pos, double azimuth, double zenith, double radius, boolean firstPerson) {
        this.pos = pos;
        this.azimuth = azimuth;
        this.zenith = zenith;
        this.radius = radius;
        this.firstPerson = firstPerson;
        this.viewVector = new Vec3D(Math.cos(azimuth) * Math.cos(zenith), Math.sin(azimuth) * Math.cos(zenith), Math.sin(zenith));
        Vec3D upVector = new Vec3D(Math.cos(azimuth) * Math.cos(zenith + 1.5707963267948966), Math.sin(azimuth) * Math.cos(zenith + 1.5707963267948966), Math.sin(zenith + 1.5707963267948966));
        if (firstPerson) {
            this.eye = new Vec3D(pos);
            this.view = new Mat4ViewRH(pos, this.viewVector.mul(radius), upVector);
        } else {
            this.eye = pos.add(this.viewVector.mul(-radius));
            this.view = new Mat4ViewRH(this.eye, this.viewVector.mul(radius), upVector);
        }

    }

    public Camera addAzimuth(double ang) {
        return new Camera(this, this.azimuth + ang, this.zenith);
    }

    public Camera addRadius(double dist) {
        return new Camera(this, Math.max(this.radius + dist, 0.1));
    }

    public Camera addZenith(double ang) {
        return new Camera(this, this.azimuth, Math.max(-1.5707963267948966, Math.min(this.zenith + ang, 1.5707963267948966)));
    }

    public Camera backward(double speed) {
        return this.forward(-speed);
    }

    public Camera down(double speed) {
        return this.up(-speed);
    }

    public Camera forward(double speed) {
        return new Camera(this, this.pos.add(this.viewVector.mul(speed)));
    }

    public double getAzimuth() {
        return this.azimuth;
    }

    public Vec3D getEye() {
        return this.eye;
    }

    public boolean getFirstPerson() {
        return this.firstPerson;
    }

    public Vec3D getPosition() {
        return this.pos;
    }

    public double getRadius() {
        return this.radius;
    }

    public Mat4 getViewMatrix() {
        return this.view;
    }

    public Vec3D getViewVector() {
        return this.viewVector;
    }

    public double getZenith() {
        return this.zenith;
    }

    public Camera left(double speed) {
        return this.right(-speed);
    }

    public Camera move(Vec3D dir) {
        return new Camera(this, this.pos.add(dir));
    }

    public Camera mulRadius(double scale) {
        return new Camera(this, Math.max(this.radius * scale, 0.1));
    }

    public Camera right(double speed) {
        return new Camera(this, this.pos.add((new Vec3D(Math.cos(this.azimuth - 1.5707963267948966), Math.sin(this.azimuth - 1.5707963267948966), 0.0)).mul(speed)));
    }

    public Camera up(double speed) {
        return new Camera(this, this.pos.add(new Vec3D(0.0, 0.0, speed)));
    }

    public Camera withAzimuth(double ang) {
        return new Camera(this, ang, this.zenith);
    }

    public Camera withFirstPerson(boolean firstPerson) {
        return new Camera(this, firstPerson);
    }

    public Camera withPosition(Vec3D pos) {
        return new Camera(this, pos);
    }

    public Camera withRadius(double radius) {
        return new Camera(this, radius);
    }

    public Camera withZenith(double ang) {
        return new Camera(this, this.azimuth, ang);
    }

    public String toString(String format) {
        return String.format(Locale.US, "Camera()\n\t.withFirstPerson(" + this.getFirstPerson() + ")\n\t.withPosition(new Vec3D" + this.getPosition().toString(format) + ")\n\t.withAzimuth(" + format + ")\n\t.withZenith(" + format + ")\n\t.withRadius(" + format + ")", this.getAzimuth(), this.getZenith(), this.getRadius());
    }

    public String toString() {
        return this.toString("%4.2f");
    }
}
