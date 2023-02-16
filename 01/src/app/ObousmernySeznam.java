package app;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * ObousmernySeznam
 */
public class ObousmernySeznam<E> implements ISeznam<E> {

    private Node<E> prvni = null, posledni = null, aktualni = null;
    private int pocetPrvku = 0;

    @Override
    public boolean jePrazdny() {
        return prvni == null;

    }

    @Override
    public int mohutnost() {
        return this.pocetPrvku;

        // if(jePrazdny());
        // return 0;

        // int pocet = 1;
        // Node<E> prvek = prvni;
        // while ((prvek = prvek.next) != null) {
        // pocet++;

        // }return pocet;

    }

    @Override
    public void vloz(ePozice pozice, E data) throws IllegalArgumentException {
        Node<E> novy = new Node<>();
        novy.data = data;
        switch (pozice) {
        case ZACATEK:
            novy.next = this.prvni;
            if (this.prvni != null) {
                this.prvni.prev = novy;

            }
            this.prvni = novy;

            if (novy.next == null) {

                // provede se jen pri vkladani uplne prvniho prvku do prazdneho seznamu
                this.posledni = novy;
            }

            break;
        case KONEC:
            novy.prev = this.posledni;
            if (this.posledni != null) {
                this.posledni.next = novy;
            }
            this.posledni = novy;
            if (novy.prev == null) {
                this.prvni = novy;
            }

            break;
        case PRED:
            if (this.jePrazdny()) {
                vloz(ePozice.ZACATEK, data);
                this.pocetPrvku--;
                // volani metody vloz navysi pocet prvku a tejne tak by se potom jeste navysilo
                // znovu v ramci toho volani,
                // proto jednou snizime
                break;
            }
            if (this.aktualni.prev != null) {
                this.aktualni.prev.next = novy;
                novy.prev = this.aktualni.prev;
                this.aktualni.prev = novy;
                novy.next = this.aktualni;
            }
            if (novy.prev == null) {
                this.prvni = novy;
            }
            break;
        case ZA:
            if (this.jePrazdny()) {
                vloz(ePozice.ZACATEK, data);
                this.pocetPrvku--;
                break;
            }
            if (this.aktualni.next != null) {
                this.aktualni.next.prev = novy;
                novy.next = this.aktualni.next;
                this.aktualni.next = novy;
                novy.prev = this.aktualni;
            }
            // else{
            // this.pocetPrvku--; // neni to spatne jen jsme to v hodine osetrili jinak
            // vloz(ePozice.KONEC, novy.data);
            // }
            if (novy.next == null) {
                this.posledni = novy;
            }

            break;

        default:
            throw new IllegalArgumentException();
        // break;
        }
        this.pocetPrvku++;
        this.aktualni = novy;

    }

    @Override
    public E odeber(ePozice pozice) {
        if (this.jePrazdny()) {
            throw new IllegalStateException("Seznam je prázdný.");
        }
        Node<E> odebirany;
        switch (pozice) {
        default:
            throw new IllegalArgumentException();
        // protoze kompilatoru chybi tahle moznost kvuli moznostem pro return...
        case ZACATEK:
            this.aktualni = this.prvni;
            return this.odeber(ePozice.AKTUALNI);
        case KONEC:
            this.aktualni = this.posledni;
            return this.odeber(ePozice.AKTUALNI);

        case PRED:
            if (this.aktualni.prev == null) {
                throw new IllegalStateException();
            } else {
                this.aktualni = this.aktualni.prev;
                return this.odeber(ePozice.AKTUALNI);
            }

        case ZA:
            if (this.aktualni.next == null) {
                throw new IllegalStateException();
            } else {
                this.aktualni = this.aktualni.next;
                return this.odeber(ePozice.AKTUALNI);
            }

        case AKTUALNI:
            odebirany = this.aktualni;
            boolean nemaNaslednika = false, nemaPredchudce = false;
            if (this.aktualni.prev != null) {
                this.aktualni.prev.next = this.aktualni.next;
            } else {
                // aktualni je v tuhle chvili prvni
                this.prvni = this.aktualni.next;
                nemaPredchudce = true;
            }
            if (this.aktualni.next != null) {
                this.aktualni.next.prev = this.aktualni.prev;
            } else {
                this.posledni = this.aktualni.prev;
                nemaNaslednika = true;
            }
            if (nemaNaslednika) {
                this.aktualni = this.aktualni.prev;
            } else {
                this.aktualni = this.aktualni.next;
            }
            this.pocetPrvku--;
            return odebirany.data;
        }

    }

    @Override
    public E ukaz(ePozice pozice) {
        if (this.jePrazdny()) {
            throw new IllegalStateException("Seznam je prázdný.");
        }
        switch (pozice) {
        case ZACATEK:
            this.aktualni = this.prvni;
            break;
        case KONEC:
            this.aktualni = this.posledni;
            break;
        case PRED:
            if (this.aktualni.prev == null) {
                return null; // kdyz tady probehne return, to dal uz se neprovede
            }
            this.aktualni = this.aktualni.prev;
            break;
        case ZA:
            if (this.aktualni.next == null) {
                return null;
            }
            this.aktualni = this.aktualni.next;
            break;
        }
        return this.aktualni.data;

    }

    static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;
    }

    @Override
    public boolean any(Predicate<E> podminka) {
        for (E e : this) {
            if (podminka.test(e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean all(Predicate<E> podminka) {
        for (E e : this) {
            if (!podminka.test(e)) {
                return false;
            }
        }

                return true;
    }

    @Override
    public ISeznam<E> filter(Predicate<E> podminka) {
        ObousmernySeznam<E> seznamfilter = new ObousmernySeznam<>();
        for (E e : this) {
            if (podminka.test(e)) {
                seznamfilter.vloz(ePozice.KONEC, e);
            }
        }


        return seznamfilter;
    }

    static class SeznamIterator<E> implements Iterator<E> {

        private Node<E> prvek;
        private int citac;

        public SeznamIterator(Node<E> prvni) {
            this.prvek = prvni;
            this.citac = 0;
        }

        // vraci true, pokud jsou v kolekci jeste nejake nenavstivene prvky
        @Override
        public boolean hasNext() {

            // TODO Auto-generated method stub
            return prvek != null;

        }

        // vraci data prvku a posouva se na dalsi, pokud existuje
        @Override
        public E next() {
            // TODO Auto-generated method stub

            if (prvek == null) {
                throw new NoSuchElementException("Kolekce neobsahuje dalsi prvky");

            }
            E data = prvek.data;
            prvek = prvek.next;
            return data;

        }

    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return new SeznamIterator<>(this.prvni);
    }

    

}