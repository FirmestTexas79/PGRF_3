package objectdata;

import org.jetbrains.annotations.NotNull;

public class Hrana {

    // Počáteční bod hrany
    private final @NotNull Point2D start;

    // Koncový bod hrany
    private final @NotNull Point2D end;

    /**
     * Konstruktor pro vytvoření hrany mezi zadanými body.
     *
     * @param start Počáteční bod hrany
     * @param end   Koncový bod hrany
     */
    public Hrana(@NotNull Point2D start, @NotNull Point2D end) {
        this.start = start;
        this.end = end;
    }


    /**
     * Metoda pro získání počátečního bodu hrany.
     *
     * @return Počáteční bod hrany
     */
    public Point2D getStart() {
        return start;
    }

    /**
     * Metoda pro získání koncového bodu hrany.
     *
     * @return Koncový bod hrany
     */
    public Point2D getEnd() {
        return end;
    }


    /**
     * Metoda pro získání orientované hrany, kde počáteční bod má nižší y-ovou souřadnici než koncový bod.
     *
     * @return Orientovaná hrana
     */
    public @NotNull Hrana oriented(){

        if (start.getR1()>end.getR1()){
            return new Hrana(end,start);
        }else {
            return new Hrana(start,end);
        }
    }

    /**
     * Metoda pro ověření, zda hrana má průsečík s danou y-ovou souřadnicí.
     *
     * @param y Y-ová souřadnice pro ověření průsečíku
     * @return True, pokud hrana má průsečík; jinak false
     */
    public boolean hasInterection(final int y){
        return (y >= start.getR1() && y <= end.getR1() || y >= end.getR1() && y <= start.getR1() );
    }


    /**
     * Metoda pro výpočet x-ové souřadnice průsečíku hrany s danou y-ovou souřadnicí.
     *
     * @param y Y-ová souřadnice pro výpočet průsečíku
     * @return X-ová souřadnice průsečíku
     */
    public int intersection(final int y){
        if (start.getR1() != end.getR1()) {
            final double k = (end.getC1() - start.getC1()) / (double) (end.getR1() - start.getR1());
            final double q = start.getC1() - k * start.getR1();
            return (int) Math.round(k * y + q);
        }
        return start.getR1();


    }

    /**
     * Metoda pro zkrácení hrany tak, aby koncový bod měl o jednotku nižší y-ovou souřadnici.
     *
     * @return Zkrácená hrana
     */
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
    /**
     * Metoda pro ověření, zda je hrana horizontální.
     *
     * @return True, pokud je hrana horizontální; jinak false
     */
    public boolean isHorizontal(){
        return (start.getR1() == end.getR1());
    }
}
