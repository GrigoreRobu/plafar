public class PlantaMedicinala {
    private String denumire;
    private int cantitateDisponibila;
    private double pret;

    public PlantaMedicinala(String denumire, int cantitateDisponibila, double pret) {
        this.denumire = denumire;
        this.cantitateDisponibila = cantitateDisponibila;
        this.pret = pret;
    }

    // Getters și setters
    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getCantitateDisponibila() {
        return cantitateDisponibila;
    }

    public void setCantitateDisponibila(int cantitateDisponibila) {
        this.cantitateDisponibila = cantitateDisponibila;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return denumire + " - " + cantitateDisponibila + " unități - " + pret + " lei";
    }
}
