package app;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ISeznam
 */
public interface ISeznam<E> extends Iterable<E> {

    boolean jePrazdny();
    int mohutnost();
    void vloz(ePozice pozice, E data) throws IllegalArgumentException;
    E odeber(ePozice pozice);
    E ukaz(ePozice pozice);
    
    //void forEach(Consumer<E> akce);
    boolean any(Predicate<E> podminka);
    boolean all(Predicate<E> podminka);
    ISeznam<E> filter(Predicate<E> podminka);

    public static enum ePozice{
        ZACATEK, KONEC, PRED , ZA, AKTUALNI;
    }
}