package app.rozhrani;

/**
 * BinarniStrom
 */
public interface BinarniStrom <E> extends Iterable <E> {

    public int mohutnost();
    public boolean JePrazdny ();
    public void vloz (E data, ePozice pozice );
    public E odeber(ePozice pozice);    
    public E ukaz (ePozice pozice);

    public static enum ePozice {
        KOREN, LEVY_SYN, PRAVY_SYN, OTEC, BRATR;
    }
}

