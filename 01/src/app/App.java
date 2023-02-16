package app;

import java.nio.file.DirectoryStream.Filter;
import java.time.DayOfWeek;
import java.time.LocalDate;

import app.ISeznam.ePozice;

public class App {
    public static void main(String[] args) throws Exception {
        ObousmernySeznam<Osoba> seznam = new ObousmernySeznam<>();

        Osoba o = new Osoba("František", "Havelka", LocalDate.of(2003, 3, 10), Osoba.Pohlavi.MUZ);
        seznam.vloz(ePozice.KONEC, o);

        o = new Osoba("Tomáš", "Dostál", LocalDate.of(2002, 11, 26), Osoba.Pohlavi.ZENA);
        seznam.vloz(ePozice.KONEC, o);

        o = new Osoba("Marek", "Halamka", LocalDate.of(2003, 5, 4), Osoba.Pohlavi._42);
        seznam.vloz(ePozice.KONEC, o);
        o = new Osoba("Libor", "Bajer", LocalDate.of(2003, 5, 4), Osoba.Pohlavi.MUZ);
        seznam.vloz(ePozice.KONEC, o);

        o = new Osoba("Filip", "Křemenák", LocalDate.of(2003, 5, 4), Osoba.Pohlavi._42);
        seznam.vloz(ePozice.KONEC, o);

        seznam.vloz(ePozice.KONEC, new Osoba("Jan", "Trunec", LocalDate.of(2002, 1, 2), Osoba.Pohlavi._42));
        seznam.vloz(ePozice.KONEC, new Osoba("Tomas", "Bezdek", LocalDate.of(2003, 2, 24), Osoba.Pohlavi._42));
        seznam.vloz(ePozice.KONEC, new Osoba("Petr", "Kasal", LocalDate.of(2002, 9, 9), Osoba.Pohlavi._42));
        seznam.vloz(ePozice.KONEC, new Osoba("Marek", "Halamka", LocalDate.of(2002, 9, 17), Osoba.Pohlavi._42));

        if (seznam.any((osoba)-> {
            return osoba.getDatumNarozeni().getDayOfWeek() == DayOfWeek.MONDAY;
        })) {
            System.out.println("nekdo ze skupiny se narodil v pondeli");
        }else{
            System.out.println("nic");

            System.out.println("BajerLibor".hashCode());
        }
    }
}