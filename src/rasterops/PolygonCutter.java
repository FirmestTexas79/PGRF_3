package rasterops;

import objectdata.Hrana;
import objectdata.Point2D;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolygonCutter<P> {
    int polygonSize;
    int cutterSize;
    List<Hrana> vrcholyOrez;
    List<Point2D> invrcholyPol;
    List<Point2D> cropVrcholy;
    Polygon2D vyslednyPol=new Polygon2D();
    public void cut(final @NotNull Polygon2D polygon,final @NotNull Polygon2D polygonCutter, final @NotNull RasterImage<P> img,
                    final @NotNull Polygoner2D<P> polygoner2D, final @NotNull P areaPixel, final @NotNull P polygonPixel, final @NotNull Liner<P> liner){


        invrcholyPol= new ArrayList<Point2D>();
        List<Point2D> outvrcholyPol= new ArrayList<Point2D>();

        cropVrcholy= new ArrayList<Point2D>();

        vrcholyOrez= new ArrayList<Hrana>();

        List<Hrana> vrcholyPol= new ArrayList<Hrana>();

        cutterSize = polygonCutter.getPoints().size();
        polygonSize = polygon.getPoints().size();

        if (cutterSize==0){
            System.err.println("Nakresli polygon");
            return;}
        for (int i = 0; i < cutterSize; i++) {
            int next = (i + 1) % cutterSize;
            Hrana edge=new Hrana(new Point2D(polygonCutter.getPoint(i).getC1(),polygonCutter.getPoint(i).getR1()),
                    new Point2D(polygonCutter.getPoint(next).getC1(),polygonCutter.getPoint(next).getR1()));
            edge=edge.oriented();
            edge=edge.shortened();
            vrcholyOrez.add(edge);
        }
        for (int i = 0; i < polygonSize; i++) {
            int next = (i + 1) % polygonSize;
            Hrana edge=new Hrana(new Point2D(polygon.getPoint(i).getC1(),polygon.getPoint(i).getR1()),
                    new Point2D(polygon.getPoint(next).getC1(),polygon.getPoint(next).getR1()));
            edge=edge.oriented();
            edge=edge.shortened();
            vrcholyPol.add(edge);
        }


        Point2D last=(polygon.getPoint(0));
        for (int i = 0; i < polygonSize; i++) {
            Point2D point2D=new Point2D(polygon.getPoint(i).getC1(),polygon.getPoint(i).getR1());
            last=(polygon.getPoint(i));
            invrcholyPol.add(point2D);
        }

        for (int i = 0; i < cutterSize; i++) {
            Point2D point2D=new Point2D(polygonCutter.getPoint(i).getC1(),polygonCutter.getPoint(i).getR1());
            cropVrcholy.add(point2D);
        }
        // System.out.println(vrcholyOrez.size() +"     "+ invrcholyPol.size());

        //find yMin,yMax
        int yMin=polygonCutter.getPoint(0).getR1();
        int yMax=polygonCutter.getPoint(0).getR1();
        for (int i=0;i<=cutterSize-1;i++){
            if (yMin>polygonCutter.getPoint(i).getR1()){
                yMin=polygonCutter.getPoint(i).getR1();
            }
            if (yMax<polygonCutter.getPoint(i).getR1()){
                yMax=polygonCutter.getPoint(i).getR1();
            }

        }
        int xMin=polygonCutter.getPoint(0).getC1();
        int xMax=polygonCutter.getPoint(0).getC1();
        for (int i=0;i<=cutterSize-1;i++){
            if (xMin>polygonCutter.getPoint(i).getC1()){
                xMin=polygonCutter.getPoint(i).getC1();
            }
            if (xMax<polygonCutter.getPoint(i).getC1()){
                xMax=polygonCutter.getPoint(i).getC1();
            }

        }
        /**
         * Sutherland Hodgman algoritmus -- bohužel jsem nepřišel na to, co s tím, pokus byl
         */
        for (Hrana edge:vrcholyOrez){

            outvrcholyPol.clear();
            Point2D v1=last;

            for (Point2D v2 : invrcholyPol){
                if (isInside(v2)){
                    if (!isInside(v1))
                        if (lineIntersect(v1,v2,edge.getStart(),edge.getEnd()));{
                        outvrcholyPol.add(xy_intersect(v1,v2,edge.getStart(),edge.getEnd()));
                        vyslednyPol.addPoint(xy_intersect(v1,v2,edge.getStart(),edge.getEnd()));
                        outvrcholyPol.add(v2);
                        vyslednyPol.addPoint(v2);}

                }else{
                    if (isInside(v1))
                        if (lineIntersect(v1,v2,edge.getStart(),edge.getEnd()));{
                        outvrcholyPol.add(xy_intersect(v1,v2,edge.getStart(),edge.getEnd()));
                        vyslednyPol.addPoint(xy_intersect(v1,v2,edge.getStart(),edge.getEnd()));}
                }
                v1 = v2;

            }
            System.out.println("Počet bodu orezaneho polygonu: "+ outvrcholyPol.size());

            polygoner2D.drawPolygon(img,polygonPixel,liner,vyslednyPol);
            return;
        }

        polygoner2D.drawPolygon(img,polygonPixel,liner,vyslednyPol);
    }

    private Point2D xy_intersect(Point2D A, Point2D B, Point2D C, Point2D D)
    {
        int x1= A.getC1();
        int y1= A.getR1();
        int x2= B.getC1();
        int y2= B.getR1();
        int x3= C.getC1();
        int y3= C.getR1();
        int x4= D.getC1();
        int y4=D.getR1();

        int numX = (x1*y2 - y1*x2) * (x3-x4) -
                (x1-x2) * (x3*y4 - y3*x4);
        int denX = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4);
        int xvys= numX/denX;
        int numY = (x1*y2 - y1*x2) * (y3-y4) -
                (y1-y2) * (x3*y4 - y3*x4);
        int denY = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4);
        int yvys= (numY/denY);
        Point2D vysledek=new Point2D(xvys,yvys);

        System.out.println("Prusecik bod X:"+ vysledek.getC1()+"       bod Y:"+vysledek.getR1());
        return (vysledek);
    }

    private boolean isInside(Point2D point2D){
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = cutterSize - 1; i < cutterSize; j = i++) {
            if ((cropVrcholy.get(i).getR1() > point2D.getR1()) != (cropVrcholy.get(j).getR1() > point2D.getR1()) &&
                    (point2D.getC1() < (cropVrcholy.get(j).getC1() - cropVrcholy.get(i).getC1() * (point2D.getR1() - cropVrcholy.get(i).getR1())
                            / (cropVrcholy.get(j).getR1()-cropVrcholy.get(i).getR1()) + cropVrcholy.get(i).getC1()))) {
                result = !result;
                System.out.println("V polygonu je Bod X:"+ point2D.getC1() +"   Bod Y:"+ point2D.getR1());
            }
        }
        return result;
    }

    private boolean lineIntersect(Point2D A, Point2D B, Point2D C, Point2D D){
        int x1= A.getC1();
        int y1= A.getR1();
        int x2= B.getC1();
        int y2= B.getR1();
        int x3= C.getC1();
        int y3= C.getR1();
        int x4= D.getC1();
        int y4=D.getR1();
        return(Line2D.linesIntersect(x1,y1,x2,y2,x3,y3,x4,y4));
    }
}