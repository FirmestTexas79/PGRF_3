package objectdata;

import org.jetbrains.annotations.NotNull;

public class Hrana {
    private final @NotNull Point2D start;
    private final @NotNull Point2D end;

    public Hrana(@NotNull Point2D start, @NotNull Point2D end) {
        this.start = start;
        this.end = end;
    }

    public Point2D getEnd() {
        return end;
    }

    public Point2D getStart() {
        return start;
    }

    public @NotNull Hrana oriented(){

        if (start.getR1()>end.getR1()){
            return new Hrana(end,start);
        }else {
            return new Hrana(start,end);
        }
    }

    public boolean hasInterection(final int y){
        return (y >= start.getR1() && y <= end.getR1() || y >= end.getR1() && y <= start.getR1() );
    }


    public int intersection(final int y){
        if (start.getR1() != end.getR1()) {
            final double k = (end.getC1() - start.getC1()) / (double) (end.getR1() - start.getR1());
            final double q = start.getC1() - k * start.getR1();
            return (int) Math.round(k * y + q);
        }
        return start.getR1();


    }

    public @NotNull Hrana shortened(){
        if (end.getC1() == start.getC1()) {
            return new Hrana(start, new Point2D(end.getC1(), end.getR1() - 1));
        } else {
            final double k = (end.getR1() - start.getR1()) / (double) (end.getC1() - start.getC1());
            final double q = start.getR1() - k * start.getC1();
            int y = (int) Math.round(k * end.getC1() + q);

            return new Hrana(start, new Point2D(end.getC1(), y - 1));
        }


    }
    public boolean isHorizontal(){
        return (start.getR1() == end.getR1());
    }
}
