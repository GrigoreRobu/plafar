import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<PlantaMedicinala> plante;
    private String fisierPlante;
    private String fisierVanzari;

    public Repository(String fisierPlante, String fisierVanzari) {
        this.fisierPlante = fisierPlante;
        this.fisierVanzari = fisierVanzari;
        plante = new ArrayList<>();
        incarcaPlante();
    }

    private void incarcaPlante() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fisierPlante))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] parti = linie.split(",");
                String denumire = parti[0];
                int cantitate = Integer.parseInt(parti[1]);
                double pret = Double.parseDouble(parti[2]);
                plante.add(new PlantaMedicinala(denumire, cantitate, pret));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PlantaMedicinala> getPlante() {
        return plante;
    }

    public void adaugaPlanta(PlantaMedicinala planta) {
        plante.add(planta);
        salveazaPlante();
    }

    public void actualizeazaCantitate(String denumire, int cantitate) {
        for (PlantaMedicinala planta : plante) {
            if (planta.getDenumire().equals(denumire)) {
                planta.setCantitateDisponibila(planta.getCantitateDisponibila() - cantitate);
                salveazaVanzare(denumire, cantitate);
                salveazaPlante();
                return;
            }
        }
    }

    private void salveazaPlante() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisierPlante))) {
            for (PlantaMedicinala planta : plante) {
                writer.write(planta.getDenumire() + "," + planta.getCantitateDisponibila() + "," + planta.getPret());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salveazaVanzare(String denumire, int cantitate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisierVanzari, true))) {
            writer.write(denumire + "," + cantitate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
