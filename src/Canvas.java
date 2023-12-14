import objectdata.*;
import objectdata.Point;
import objectops.RenderLineList;
import objectops.Renderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.*;
import transform.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Canvas {

	private JFrame frame;
	private JPanel panel;
	private final @NotNull RasterImage<Integer> img;
	private final @NotNull Presentable<Graphics> presenter;
	private final @NotNull Liner<Integer> liner;
	private final @NotNull Liner<Integer> dottedLiner;
	private final @NotNull Polygon2D polygon2D;
	private final @NotNull Polygoner2D<Integer> polygoner2D;

	private int c1, r1, c2, r2;
	private final @NotNull SeedFill<Integer> seedFill;
	private final Scene scene;
	private final Scene scenex;
	private final Scene sceney;
	private final Scene scenez;
	private final Renderer<Integer> renderer;
	private Camera camera;
	private Mat4 cubeTransform;
	private boolean rotace;
	private int modRotace=0;
	private boolean isCube=true;
	private boolean isPyramid;
	private boolean isPrism=true;
	private boolean isSinus=true;
	private boolean isAnimace=false;
	private final @NotNull Cube cube;
	private final @NotNull Cube animatedCube;
	private final @NotNull Pyramid pyramid;
	private final @NotNull Prism prism;


	private final @NotNull Sinus sinus;
	private @NotNull Mat4 projectionMatrix;
	private @NotNull Mat4 orthMatrix;
	private Vec3D pos;
	private final Mat4 anim2;
	private final Mat4 anim1;
	private final Mat4 zvetseniAnim;
	int modProjekce=0;


	public Canvas(int width, int height) {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : Maťašovský Samuel" + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		final @NotNull RasterImageBI auxRasterImageBI = new RasterImageBI(width, height);
		img = auxRasterImageBI;
		presenter = auxRasterImageBI;
		polygon2D = new Polygon2D();
		polygoner2D = new Polygoner2D<>();
		cube=new Cube();
		animatedCube=new Cube();
		pyramid=new Pyramid();
		prism=new Prism();
		sinus=new Sinus(100);
		liner = new TrivialLiner<>();
		dottedLiner = new DottedLiner<>(1, 4);
		seedFill = new SeedFill4<>();
		scene=new Scene();
		scene.addSolid(cube);
		scene.addSolid(prism);
		scenex=new Scene();
		scenex.addSolid(new AxisX());
		sceney=new Scene();
		sceney.addSolid(new AxisY());
		scenez=new Scene();
		scenez.addSolid(new AxisZ());
		scene.addSolid(sinus);
		anim1 = new Mat4RotZ(0.03).mul(new Mat4Scale(1.01)).mul(new Mat4RotY(0.03)).mul(new Mat4RotX(-0.01));
		anim2 = new Mat4RotZ(0.03).mul(new Mat4Scale(0.99)).mul(new Mat4RotY(0.03)).mul(new Mat4RotX(-0.01));
		zvetseniAnim = new Mat4Scale(2);
		sceney.addSolid(new Plocha());

		scenex.addSolid(new Bezier(50000, new Point3D(-5, -2, 3), new Point3D(-3, -1, 2), new Point3D(0, -4, 1), new Point3D(2, -3, 5)));
		scenex.addSolid(new Ferguson(50000, new Point3D(-2, -3, 4), new Point3D(0, -2, 1), new Point3D(2, -4, 3), new Point3D(4, -3, 4)));
		scenex.addSolid(new Coons(50000, new Point3D(-3, -5, 2), new Point3D(-1, -6, 0), new Point3D(1, -5, 2), new Point3D(3, -6, 3)));



		renderer=new RenderLineList<>();
		pos = new Vec3D(3.2, 4, 3);
		camera=new Camera()
				.withPosition(pos)
				.withAzimuth(getAzimuthToOrigin(pos))
				.withZenith(getZenithToOrigin(pos));

		projectionMatrix=new Mat4PerspRH(Math.PI/4,1,0.1,200);
		orthMatrix=new Mat4OrthoRH(img.getWidth() / 40.0, img.getHeight() / 40.0, -200, 200);
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e){
				final double dc=c1-e.getX();
				final double dr=r1-e.getY();
				c1=e.getX();
				r1=e.getY();
				camera=camera.addAzimuth(dc/img.getWidth()).addZenith(dr/img.getHeight());
				renderScene();
			}
		});
		final double[] mouseWheelMoved = {0};
		panel.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseWheelMoved[0] += e.getPreciseWheelRotation();

				final double[] moveBy = {0.1};
				if (mouseWheelMoved[0] != 0) {
					camera = camera.move(camera.getViewVector().mul(moveBy[0] * 5 * -mouseWheelMoved[0]));
					mouseWheelMoved[0] = 0;
					renderScene();
				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				c1 = e.getX();
				r1 = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});


		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);


		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_D) {
					camera=camera.right(0.1);
					renderScene();
				} if (e.getKeyCode() == KeyEvent.VK_W){
					camera=camera.forward(0.1);
					renderScene();

				} if (e.getKeyCode() == KeyEvent.VK_S){
					camera=camera.backward(0.1);
					renderScene();
				} if (e.getKeyCode() == KeyEvent.VK_A){
					camera=camera.left(0.1);
					renderScene();
				} if (e.getKeyCode() == KeyEvent.VK_SPACE){

					camera=camera.up(0.1);
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_SHIFT){
					camera=camera.down(0.1);
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_Z){
					modRotace=1;
					rotace=!rotace;
					if(rotace==true){
						timer.start();
					}else{timer.stop();}
				}
				if (e.getKeyCode() == KeyEvent.VK_X){
					modRotace=2;
					rotace=!rotace;
					if(rotace==true){
						timer.start();
					}else{timer.stop();}
				}
				if (e.getKeyCode() == KeyEvent.VK_Y){
					modRotace=3;
					rotace=!rotace;
					if(rotace==true){
						timer.start();
					}else{timer.stop();}
				}
				if (e.getKeyCode() == KeyEvent.VK_O){
					modProjekce=1;
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_P){
					modProjekce=0;
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_C){
					isCube=!isCube;
					if (isCube==true) {

						scene.addSolid(cube);
					}else if (isCube==false){
						scene.removeSolid(cube);
					}
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_V){
					isPyramid=!isPyramid;
					if (isPyramid==true) {
						scene.addSolid(pyramid);
					}else{
						scene.removeSolid(pyramid);
					}
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_B){
					isPrism=!isPrism;
					if (isPrism==true) {
						scene.addSolid(prism);
					}else{
						scene.removeSolid(prism);
					}
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_N){
					isSinus=!isSinus;
					if (isSinus==true) {
						scene.addSolid(sinus);
					}else{
						scene.removeSolid(sinus);
					}
					renderScene();
				}
				if (e.getKeyCode() == KeyEvent.VK_M){
					isAnimace=!isAnimace;
					if (isAnimace==true) {
						scene.addSolid(animatedCube);
					}else{
						scene.removeSolid(animatedCube);
					}
					renderScene();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

	}
	ActionListener al=new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			cubeTransform = scene.getModelMats().get(0);
			if(modRotace==1){
				cubeTransform = cubeTransform.mul(new Mat4RotZ(0.0174533));
			}else if(modRotace==2){
				cubeTransform = cubeTransform.mul(new Mat4RotX(0.0174533));
			}else if(modRotace==3){
				cubeTransform = cubeTransform.mul(new Mat4RotY(0.0174533));
			}
			else if(isAnimace==true){
				scene.addSolid(animatedCube,anim1);
			}



			scene.getModelMats().set(0, cubeTransform);
			renderScene();
		}
	};
	Timer timer=new Timer(10,al);


	public void draw(final @NotNull Runnable r) {
		clear();
		r.run();
		present();
	}

	public void clear() {
		img.clear(0x2f2f2f);
	}

	public void present(final @NotNull Graphics graphics) {
		presenter.present(graphics);
	}

	public void present() {
		final @Nullable Graphics g = panel.getGraphics();
		if (g != null) {
			presenter.present(g);
		}
	}

	private double getAzimuthToOrigin(final @NotNull Vec3D pos){
		final @NotNull Vec3D v=pos.opposite();
		final double alpha=v.ignoreZ().normalized()
				.map(vNorm->Math.acos(vNorm.dot(new Vec2D(1,0))))
				.orElse(0.0);
		return (v.getY()>0)? alpha : Math.PI*2- alpha;
	}

	private double getZenithToOrigin(final @NotNull Vec3D pos){
		final @NotNull Vec3D v=pos.opposite();
		final double alpha= v.normalized()
				.map(vNorm-> Math.acos(vNorm.dot(new Vec3D(0,0,1))))
				.orElse(Math.PI/2);
		return Math.PI/2 - alpha;
		//sklární součin
	}

	public void renderScene(){
		clear();
		if (modProjekce==0){
			renderer.renderScene(scene, camera.getViewMatrix(), projectionMatrix, img, 0xff00f0, liner);
			renderer.renderScene(scenex, camera.getViewMatrix(), projectionMatrix, img, 0x0000ff, liner);
			renderer.renderScene(sceney, camera.getViewMatrix(), projectionMatrix, img, 0x00ff00, liner);
			renderer.renderScene(scenez, camera.getViewMatrix(), projectionMatrix, img, 0xff0000, liner);}
		if (modProjekce==1){
			renderer.renderScene(scene, camera.getViewMatrix(), orthMatrix, img, 0xff00f0, liner);
			renderer.renderScene(scenex, camera.getViewMatrix(), orthMatrix, img, 0x0000ff, liner);
			renderer.renderScene(sceney, camera.getViewMatrix(), orthMatrix, img, 0x00ff00, liner);
			renderer.renderScene(scenez, camera.getViewMatrix(), orthMatrix, img, 0xff0000, liner);
		}
		present();
	}

	public Camera updateCamera(Vec3D pos){
		camera=new Camera()
				.withPosition(pos)
				.withAzimuth(getAzimuthToOrigin(pos))
				.withZenith(getZenithToOrigin(pos));
		return camera;
	}

	public Vec3D rotaceX(Vec3D pos){

		return pos;
	}

	public void start() {
		clear();
		draw(() -> {
			renderer.renderScene(scene, camera.getViewMatrix(), projectionMatrix, img, 0xff00f0, liner);
			renderer.renderScene(scenex, camera.getViewMatrix(), projectionMatrix, img, 0x0000ff, liner);
			renderer.renderScene(sceney, camera.getViewMatrix(), projectionMatrix, img, 0x00ff00, liner);
			renderer.renderScene(scenez, camera.getViewMatrix(), projectionMatrix, img, 0xff0000, liner);
		});
		present();
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new Canvas(800, 600).start());

	}

}