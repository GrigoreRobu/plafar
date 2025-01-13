import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<PlantaMedicinala> plante;
    private String fisierPlante;
    private String fisierVanzari;

    /**
     * Constructor pentru clasa Repository.
     *
     * @param fisierPlante Numele fisierului unde se stocheaza numele plantelor, cantitatea si pretul lor.
     * @param fisierVanzari Numele fisierului unde se stocheaza numele plantelor si cantitatea vanduta.
     */
    public Repository(String fisierPlante, String fisierVanzari) {
        this.fisierPlante = fisierPlante;
        this.fisierVanzari = fisierVanzari;
        plante = new ArrayList<>();
        incarcaPlante();
    }

    /**
     * Functie care incarca informatiile din fisierul plante.txt.
     */
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

    /**
     * Returneaza lista de plante medicinale.
     *
     * @return Lista de plante medicinale.
     */
    public List<PlantaMedicinala> getPlante() {
        return plante;
    }

    /**
     * Functie care adauga o planta in lista si actualizeaza fisierul.
     *
     * @param planta Planta care va fi adaugata.
     */
    public void adaugaPlanta(PlantaMedicinala planta) {
        plante.add(planta);
        salveazaPlante();
    }

    /**
     * Functie care actualizeaza cantitatea unei plante.
     *
     * @param denumire Denumirea plantei.
     * @param cantitate Cantitatea care trebuie scazuta.
     * @throws IllegalArgumentException daca planta nu exista sau cantitatea solicitata depaseste stocul.
     */
    public void actualizeazaCantitate(String denumire, int cantitate) {
        for (PlantaMedicinala planta : plante) {
            if (planta.getDenumire().equals(denumire)) {
                if (planta.getCantitateDisponibila() < cantitate) {
                    throw new IllegalArgumentException("Cantitatea solicitata depaseste stocul disponibil!");
                }
                planta.setCantitateDisponibila(planta.getCantitateDisponibila() - cantitate);
                salveazaVanzare(denumire, cantitate);
                salveazaPlante();
                return;
            }
        }
        throw new IllegalArgumentException("Planta nu a fost gasita!");
    }

    /**
     * Functie care salveaza plantele in fisierul plante.txt.
     */
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

    /**
     * Functie care salveaza numele si cantitatea plantelor vandute in fisierul PlanteVandute.txt.
     *
     * @param denumire Numele plantei vandute.
     * @param cantitate Cantitatea vanduta.
     */
    private void salveazaVanzare(String denumire, int cantitate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fisierVanzari, true))) {
            writer.write(denumire + "," + cantitate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
