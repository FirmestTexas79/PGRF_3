import objectdata.Point2D;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasterdata.Presentable;
import rasterdata.RasterImageBI;
import rasterdata.RasterImage;
import rasterops.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.function.Predicate;


public class Canvas {


	private JFrame frame;
	private JPanel panel;


	private final @NotNull RasterImage<Integer> RImage;
	private final @NotNull Presentable<Graphics> presenter;
	private final @NotNull Liner<Integer> liner;
	private final @NotNull Liner<Integer> dottedLiner;
	private final @NotNull Polygon2D polygon2D;
	private final @NotNull Polygoner2D<Integer> polygoner2D;
	private final @NotNull MidPointLiner<Integer> mpLiner;
	private final @NotNull EquilateralTriangle<Integer> eqtriangle;
	private final @NotNull SeedFill4<Integer> seedFill4;
	private final @NotNull SeedFill8<Integer> seedFill8;
	private final @NotNull SeedFill4Queue<Integer> seedFill4Queue;
	private final @NotNull ScanLine<Integer> scanLine;
	private final @NotNull PatternFillImpl patternFill;
	private final @NotNull PolygonCutter<Integer> polygonCutter;
	private int c1, r1, c2, r2;
	private int mode = 1;
	private int x;
	private int y;

	boolean polygonReady=false;
	boolean cropperReady=false;
	boolean triangleReady=false;
	boolean bodSeedFill=false;
	int seedFillX;
	int seedFillY;
	Polygon2D polygonA=new Polygon2D();
	Polygon2D polygonCrop=new Polygon2D();



	public Canvas(int width, int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("PGRF Maťašovský : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final @NotNull RasterImageBI auxRasterImageBI = new RasterImageBI(width, height);
		RImage = auxRasterImageBI;
		presenter = auxRasterImageBI;
		polygon2D = new Polygon2D();
		polygoner2D = new Polygoner2D<>();
		liner = new TrivialLiner<>();
		dottedLiner = new DottedLiner<>(1, 5);
		mpLiner = new MidPointLiner<>();
		eqtriangle = new EquilateralTriangle<>();
		seedFill4 = new SeedFill4<>();
		seedFill8 = new SeedFill8<>();
		seedFill4Queue = new SeedFill4Queue<>();
		scanLine = new ScanLineImpl<>();
		patternFill = new PatternFillImpl();
		polygonCutter = new PolygonCutter<>();


		panel = new JPanel() {		// Inicializace panelu pro kreslení

			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				clear();
				c2 = e.getX();
				r2 = e.getY();


				if(mode==1){
					liner.drawLine(RImage,c1, r1, e.getX(), e.getY(), 0x6a00a3);
				}

				if(mode==2){
					dottedLiner.drawLine(RImage, c1, r1, e.getX(), e.getY(), 0x00ffea);
				}

				if(mode==3){
					if (polygonReady==true){
						liner.drawLine(RImage, polygonA.getX(0), polygonA.getY(0), e.getX(), e.getY(), 0xff6f61);
						liner.drawLine(RImage, x, y, e.getX(), e.getY(), 0xd65dfe);
					}
					polygoner2D.drawPolygon(RImage, 0xe78be7, liner, polygonA);
					polygoner2D.drawPolygon(RImage, 0xa52a2a, liner, polygonCrop);
				}
				if(mode==4){
					if (polygonReady==true){
						liner.drawLine(RImage, polygonA.getPoint(0).getC1(), polygonA.getPoint(0).getR1(), e.getX(), e.getY(), 0xff00ff);
						liner.drawLine(RImage, x, y, e.getX(), e.getY(), 0xff00ff);
					}
					eqtriangle.drawTriangle(RImage, 0x00f1a1, liner, polygon2D);
					if (triangleReady==true){
						dottedLiner.drawLine(RImage,
								(int) mpLiner.getMidPoint(RImage,polygonA.getPoint(0).getC1(), polygonA.getPoint(0).getR1(),polygonA.getPoint(1).getC1(), polygonA.getPoint(1).getR1(), 0xffffff).getX(),
								(int) mpLiner.getMidPoint(RImage,polygonA.getPoint(0).getC1(), polygonA.getPoint(0).getR1(),polygonA.getPoint(1).getC1(), polygonA.getPoint(1).getR1(), 0xffffff).getY(),
								c2, r2, 0xff00ff);
					}
				}
				if(mode==6){
					if (cropperReady==true){
						liner.drawLine(RImage, polygonCrop.getX(0), polygonCrop.getY(0), e.getX(), e.getY(), 0x00d8ff);
						liner.drawLine(RImage, x, y, e.getX(), e.getY(), 0xffff00);
					}
					polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
					polygoner2D.drawPolygon(RImage, 0xffff00, liner, polygonA);
				}
				present();
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
				c2 = e.getX();
				r2 = e.getY();
				if(mode==2){
					draw(() -> dottedLiner.drawLine(RImage, c1, r1, c2, r2, 0xFFC0CB));
				}
				else if(mode==1){
					draw(() -> liner.drawLine(RImage, c1, r1, c2, r2, 0xA52A2A));
				}
				else if(mode==3){
					mousePressed(e);
					draw(() -> {
						x=e.getX();
						y=e.getY();
						polygonA.addPoint(new Point2D(e.getX(), e.getY()));
						polygoner2D.drawPolygon(RImage, 0x00ff00, liner, polygonA);
						polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
						polygonReady = true;
					});
				}
				else if(mode==4){
					mousePressed(e);
					draw(() -> {
						x=e.getX();
						y=e.getY();
						polygon2D.addPoint(new Point2D(e.getX(), e.getY()));
						eqtriangle.drawTriangle(RImage, 0xFFA500, liner, polygon2D);
						polygonReady=true;
					});
					if (triangleReady==true){

						dottedLiner.drawLine(RImage,
								(int) mpLiner.getMidPoint(RImage,polygonA.getPoint(0).getC1(), polygonA.getPoint(0).getR1(),polygonA.getPoint(1).getC1(), polygonA.getPoint(1).getR1(), 0xffffff).getX(),
								(int) mpLiner.getMidPoint(RImage,polygonA.getPoint(0).getC1(), polygonA.getPoint(0).getR1(),polygonA.getPoint(1).getC1(), polygonA.getPoint(1).getR1(), 0xffffff).getY(),
								c2, r2, 0x00fff5);
					}
					triangleReady=true;

				}else if(mode==5){
					seedFillX=e.getX();
					seedFillY=e.getY();
					draw(() -> {
						polygoner2D.drawPolygon(RImage, 0xffff00, liner, polygonA);
						polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
					});
					bodSeedFill=true;
					mode=3;
				}
				else if(mode==6){
					mousePressed(e);
					draw(() -> {
						x=e.getX();
						y=e.getY();
						polygonCrop.addPoint(new Point2D(e.getX(), e.getY()));
						polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);

						polygoner2D.drawPolygon(RImage, 0xffff00, liner, polygonA);
						cropperReady=true;
					});
				}
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
				if (e.getKeyCode() == KeyEvent.VK_C) {
					System.out.println("C was pressed!-Clear the board");
					clear();
					present();
				} else if (e.getKeyCode() == KeyEvent.VK_L) {
					System.out.println("L was pressed-TrivialLiner");
					mode=1;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("D was pressed-DottedLiner");
					mode=2;
				}else if (e.getKeyCode() == KeyEvent.VK_P) {
					System.out.println("P was pressed-Polygon");
					mode=3;
				}else if (e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("S was pressed-Delete all Points");
					polygonReady=false;
					reset();
					present();
				}else if (e.getKeyCode() == KeyEvent.VK_T) {
					System.out.println("T was pressed-Triangle");
					mode=4;

				}else if (e.getKeyCode() == KeyEvent.VK_F) {
					System.out.println("ScanLine");
					draw(() -> {
						scanLine.fill(polygonA, RImage,polygoner2D,patternFill.fill(c1%2,r1%2),0xffff00,liner);
						polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
					});

				}else if (e.getKeyCode() == KeyEvent.VK_B) {
					System.out.println("Vyber si bod pro seedfill");
					mode=5;


				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("SeedFill4-Barva pozadi");


					if (bodSeedFill==true){
						draw(() -> {
							polygoner2D.drawPolygon(RImage, 0xffff00, liner, polygonA);
							polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
							RImage.getPixel(seedFillX, seedFillY).ifPresent(p -> {
								seedFill4Queue.fill(RImage, seedFillX, seedFillY,patternFill.fill(c1,r1), new Predicate<Integer>() {
									@Override
									public boolean test(Integer integer) {
										return Objects.equals(p, integer);
									}
								});
							});
						});
					}}else if (e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("SeedFill4-Barva hranice");


					if (bodSeedFill==true){
						draw(() -> {
							polygoner2D.drawPolygon(RImage, 0xffff00, liner, polygonA);
							polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
							RImage.getPixel(seedFillX, seedFillY).ifPresent(p -> {
								seedFill4Queue.fill(RImage, seedFillX, seedFillY,patternFill.fill(c1,r1), new Predicate<Integer>() {
									@Override
									public boolean test(Integer integer) {
										return Objects.equals(p, integer);
									}
								});
							});
						});
					}}
				else if (e.getKeyCode() == KeyEvent.VK_H) {
					System.out.println("HRANICE");
					mode=6;


				}else if (e.getKeyCode() == KeyEvent.VK_J) {
					System.out.println("CROP");
					draw(()->{
						polygoner2D.drawPolygon(RImage, 0xffff00, liner, polygonA);
						polygoner2D.drawPolygon(RImage, 0x0000ff, liner, polygonCrop);
						polygonCutter.cut(polygonA,polygonCrop, RImage,polygoner2D,patternFill.fill(c1%2,r1%2),0xfff0ff,liner);

					});
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	public void draw(final @NotNull Runnable r) {
		clear();
		r.run();
		present();
	}
	public void clear() {
		RImage.clear(0x2f2f2f);
		RImage.drawHelp();
	}

	public void reset() {
		RImage.clear(0x2f2f2f);
		polygon2D.removeAllPoints();
		polygonA.removeAllPoints();
		polygonCrop.removeAllPoints();
		RImage.drawHelp();
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

	public void start() {
		clear();
		present();
	}

	public static void main(String[] args) {		// Metoda pro vytvoření instance třídy Canvas a spuštění aplikace
		SwingUtilities.invokeLater(() -> new Canvas(800, 700).start());
	}

}
