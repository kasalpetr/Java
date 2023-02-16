package app;

import java.time.LocalDate;

/**
 * Osoba
 */
public class Osoba implements Comparable<Osoba> {

    private String jmeno, prijmeni;
    private LocalDate datumNarozeni;
    private Pohlavi pohlavi;

    // public Osoba() {
    // kdybychom chteli vytvořit prazdnou osobu
    // }
    public Osoba(String jmeno, String prijmeni, LocalDate datumNarozeni, Pohlavi pohlavi) {
        this.setJmeno(jmeno);
        this.setPrijmeni(prijmeni);
        this.setDatumNarozeni(datumNarozeni);
        this.setPohlavi(pohlavi);
    }

    public Pohlavi getPohlavi() {
        return pohlavi;
    }

    public void setPohlavi(Pohlavi pohlavi) {
        this.pohlavi = pohlavi;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }


    @Override
    public String toString(){
        return this.jmeno + " " + this.prijmeni;
    }
    // vnorena trida
    static enum Pohlavi { // vnorena trida enum
       MUZ, ZENA, JINE , _42;
   }

    @Override
    public int compareTo(Osoba oko) {
        if(oko == null){
            throw new NullPointerException();
        }
        if(this.prijmeni.compareTo(oko.prijmeni) == 0){ // tímhle si určíme pořadí, pokud použijeme dale compareTo
            return this.jmeno.compareTo(oko.jmeno);
        }else{
        return this.prijmeni.compareTo(oko.prijmeni);
        }
    }
}