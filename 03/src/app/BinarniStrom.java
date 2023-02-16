package app;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * BinarniStrom
 */
public class BinarniStrom<E> implements app.rozhrani.BinarniStrom<E> {

    private BinTreeNode <E> koren = null, aktualni = null;
    private int PocetPrvku = 0;


    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int mohutnost() {
        return this.PocetPrvku;
    }

    @Override
    public boolean JePrazdny() {
        return this.koren == null;
    }

    @Override
    public void vloz(E data, ePozice pozice) {
        BinTreeNode <E>  novy = new BinTreeNode <E> (data);
        switch (pozice) {
            case KOREN:
                if (JePrazdny()) {
                    // novy.otec = null;
                    // novy.pravysyn = null;
                    // novy.levysyn = null;
                    this.koren = novy;
                    this.aktualni = novy;
                }else{
                    throw new IllegalStateException("nemuzes pridat koren");
                }
                break;
            case LEVY_SYN:
                if (this.aktualni.levysyn == null) {
                    this.aktualni.levysyn = novy;
                    this.aktualni.levysyn.otec = this.aktualni;
                    this.aktualni = novy;
                }else{
                    throw new IllegalStateException("uz ma leveho syna");
                }
                break;
            case PRAVY_SYN:
            if (this.aktualni.pravysyn == null) {
                this.aktualni.pravysyn = novy;
                this.aktualni.pravysyn.otec = this.aktualni;
                this.aktualni = novy;
            }else{
                throw new IllegalStateException("uz ma praveho syna");
            }
            break; 
            default:
            throw new IllegalStateException("uz ma. ");
        }
        this.PocetPrvku++;

    }

    @Override
    public E odeber(ePozice pozice) {
        E data = null;
        switch (pozice) {
            case LEVY_SYN : 
                if (this.aktualni.levysyn.JeList()) {
                    data = this.aktualni.levysyn.data;
                    this.aktualni.levysyn = null;
                   
                }else{
                    throw new IllegalStateException("neni list");
                }
                break;
                case PRAVY_SYN:
                if (this.aktualni.pravysyn.JeList()) {
                    data = this.aktualni.pravysyn.data;
                    this.aktualni.pravysyn = null;
                   
                }else{
                    throw new IllegalStateException("neni list");
                } 
                
                break;
                case BRATR: 

                if(this.aktualni.otec.levysyn == null || this.aktualni.otec.pravysyn == null){
                    throw new IllegalStateException("neni braťa");
                }
                if (this.aktualni.otec.levysyn == this.aktualni){
                    if (this.aktualni.otec.pravysyn.JeList()){
                    data = this.aktualni.otec.pravysyn.data;
                    this.aktualni.otec.pravysyn = null;
                   
                }else{
                    throw new IllegalStateException("neni list");
                 }     
             }
             if (this.aktualni.otec.pravysyn == this.aktualni){
                if (this.aktualni.otec.levysyn.JeList()){
                data = this.aktualni.otec.levysyn.data;
                this.aktualni.otec.levysyn = null;
               
            }else{
                throw new IllegalStateException("neni list");
             }   
            }
                break;
        
            default:
            throw new IllegalStateException("spatna pozice");
        }
        this.PocetPrvku --;
        return data;
        
    }

    @Override
    public E ukaz(ePozice pozice) {
    if (JePrazdny()){
        throw new IllegalStateException("uz ma. ");
    }
        switch (pozice) {
         case KOREN:
             this.aktualni = this.koren;
             break;
             case LEVY_SYN:
             this.aktualni = this.aktualni.levysyn;
             break;
             case PRAVY_SYN:
             this.aktualni = this.aktualni.pravysyn;
             break;
             case OTEC:
             this.aktualni = this.aktualni.otec;
             break;
             case BRATR:
             if (this.aktualni.otec.levysyn != null && this.aktualni.otec.pravysyn != null) {
                 if (this.aktualni.otec.levysyn == this.aktualni) {
                     this.aktualni = this.aktualni.otec.pravysyn;
                 }else{
                    this.aktualni = this.aktualni.otec.levysyn;
                 }
             }else{
                throw new IllegalStateException("nema bratra ");
             }
             break;     
         default:
         throw new IllegalStateException("uz ma. ");
     }
     return this.aktualni.data;
    }
    

    class inOrderProhlidka<E> implements Iterator<E>{
        
        @Override
        public boolean hasNext() {
            
            return prvek != null;
        }

        @Override 
        public E next() {
         if (prvek == null) {
             throw new NoSuchElementException();
         }
         data = prvek.data;
            if (prvek.pravysyn != null) {
                while (prvek.levysyn != null) {
                    prvek = prvek.levysyn;
                }
            }else{
            do {
                prechoziprvek = prvek;
                prvek = prvek.otec;
            } while (prechoziprvek == prvek.pravysyn);
            // prechoziprvek = prvek;
            // prvek = prvek.otec;
            }
            return data;
        }

    }


    class BinTreeNode <E> {
        final E data;
        BinTreeNode <E> otec, levysyn, pravysyn;

        public BinTreeNode(E data) {
            this.data = data;
        }

        public Boolean JeList(){
            return this.levysyn == null && this.pravysyn == null;
        }
    }
;

    
}