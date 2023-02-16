package app;
import java.util.Arrays;

/**
 * sorty
 */
public class sorty {

    public static <T extends Comparable<T>> T[] bubbleSort( T [] pole){
        T[] vnitrni = Arrays.copyOf(pole, pole.length); //zkopirovani pole
        vnejsi:  for (int i = 0; i < vnitrni.length; i++) { //jmeno for aby jsme ho mohli ukoncit
            boolean isChange = false; //kdyby se to uz nahodou nemenilo
            for (int j = 0; j < vnitrni.length - i - 1; j++) {
                if (vnitrni [j].compareTo(vnitrni[j+1]) > 0 ){
                    T pom = vnitrni [j];
                    vnitrni [j] = vnitrni [j + 1];
                    vnitrni [j+1] = pom;
                    isChange = true;
                }
            }
            if (!isChange) break vnejsi; // ukonceni vnitrniho for
        }
            return vnitrni;
    }
    public static <T extends Comparable<T>> T[] mergeSort (T[] pole){
        if(pole.length > 1){
            int stred = pole.length/2;
            T[] levacast = Arrays.copyOfRange(pole, 0, stred);
            T[] pravacast = Arrays.copyOfRange(pole, stred, pole.length);
            levacast = mergeSort(levacast);
            pravacast = mergeSort(pravacast);
            return merge (levacast, pravacast);
        }
        return pole;    // vrátíme zpátky, protože to je jednoprvková řada -> konec reukrzivniho puleni
    }
    private static <T extends Comparable<T>> T[] merge(T[] prvni, T[] druhe){
        T[] vysledne = Arrays.copyOf(prvni, prvni.length + druhe.length);
            for (int indexVysledne = 0, indexPrvni = 0, indexDruhe = 0;
             indexVysledne < vysledne.length; indexVysledne++) {
                if ((indexDruhe >= druhe.length) 
                ||
                 (indexPrvni < prvni.length
                  && prvni[indexPrvni].compareTo(druhe[indexDruhe])< 0
                  )
                  ) {
                    //beru prvek z prvni rady
                    vysledne [indexVysledne] = prvni [indexPrvni++];
                }else {
                    // beru prvek z druhe rady
                    vysledne [indexVysledne] = druhe [indexDruhe++];
                }
            }
        return vysledne;
    }
}