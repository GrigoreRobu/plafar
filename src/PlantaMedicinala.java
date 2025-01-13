/**
 * Reprezinta o planta medicinala disponibila in magazin.
 * Fiecare planta are un nume, o cantitate disponibila si un pret.
 */
public class PlantaMedicinala {
    private String denumire;
    private int cantitateDisponibila;
    private double pret;

    /**
     * Constructor pentru crearea unei noi instante a clasei PlantaMedicinala.
     *
     * @param denumire            Denumirea plantei medicinale.
     * @param cantitateDisponibila Cantitatea disponibila din stoc.
     * @param pret                Pretul per unitate al plantei.
     */
    public PlantaMedicinala(String denumire, int cantitateDisponibila, double pret) {
        this.denumire = denumire;
        this.cantitateDisponibila = cantitateDisponibila;
        this.pret = pret;
    }

    /**
     * Obtine denumirea plantei medicinale.
     *
     * @return Denumirea plantei medicinale.
     */
    public String getDenumire() {
        return denumire;
    }


    /**
     * Obtine cantitatea disponibila din stoc.
     *
     * @return Cantitatea disponibila.
     */
    public int getCantitateDisponibila() {
        return cantitateDisponibila;
    }

    /**
     * Seteaza cantitatea disponibila pentru planta medicinala.
     *
     * @param cantitateDisponibila Noua cantitate disponibila.
     */
    public void setCantitateDisponibila(int cantitateDisponibila) {
        this.cantitateDisponibila = cantitateDisponibila;
    }

    /**
     * Obtine pretul per unitate al plantei medicinale.
     *
     * @return Pretul plantei medicinale.
     */
    public double getPret() {
        return pret;
    }

    /**
     * Seteaza un nou pret pentru planta medicinala.
     *
     * @param pret Noul pret al plantei.
     */
    public void setPret(double pret) {
        this.pret = pret;
    }

    /**
     * Reprezentarea textuala a unei plante medicinale.
     *
     * @return O reprezentare sub forma unui sir de caractere a plantei,
     *         incluzand denumirea, cantitatea disponibila si pretul.
     */
    @Override
    public String toString() {
        return denumire + " - " + cantitateDisponibila + " unitati - " + pret + " lei";
    }
}
