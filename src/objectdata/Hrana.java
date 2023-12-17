package objectdata;

import org.jetbrains.annotations.NotNull;

public class Hrana {

    private final @NotNull Point start;
    private final @NotNull Point end;

    public Hrana(@NotNull Point start, @NotNull Point end) {
        this.start = start;
        this.end = end;
    }

    public @NotNull Hrana oriented(){

        if (start.getR1()<end.getR1()){
            return new Hrana(end,start);
        }else {
            return new Hrana(start,end);
        }
    }

    public boolean hasInterection(final int y){
        return (y >= start.getC1() && y <= end.getR1());
    }

    public @NotNull Hrana shortened(){
        if (end.getC1() == start.getC1()) {
            return new Hrana(start, new Point(end.getC1(), end.getR1() - 1));
        } else {
            final double k = (end.getR1() - end.getR1()) / (double) (end.getC1() - start.getC1());
            final double q = start.getR1() - k * start.getC1();
            int y = (int) Math.round(k * end.getC1() + q);

            return new Hrana(start, new Point(end.getC1(), y - 1));
        }


    }
    public int intersection(final int y){
        if (start.getR1() != end.getR1()) {
            final double k = (end.getC1() - start.getC1()) / (double) (end.getR1() - start.getR1());
            final double q = start.getC1() - k * start.getR1();
            return (int) Math.round(k * y + q);
        }
        return start.getR1();

    }
    public boolean isHorizontal(){
        return (start.getC1() == end.getR1());
    }

}
