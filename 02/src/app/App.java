package app;

import java.util.Random;
import static app.sorty.*;
public class App {
    public static void main(String[] args) throws Exception {
        Random r = new Random();
        Integer[] pole = new Integer[100000];
        for (int i = 0; i < pole.length; i++) {
            pole [i] = r.nextInt(100000000);
        }
        System.out.println("BubbleSort");
        long start = System.currentTimeMillis();
        Integer [] serazeneB = bubbleSort(pole);
        long konec = System.currentTimeMillis();

        System.out.println("razeni BS trvalo " + (konec - start) + " MS");

        System.out.println("MergeSort");
         start = System.currentTimeMillis();
        Integer [] serazeniMergsortem = mergeSort(pole);
         konec = System.currentTimeMillis();

        System.out.println("razeni MS trvalo " + (konec - start) + " MS");

    }

   
}