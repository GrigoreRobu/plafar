import java.util.List;

public class Service {
    private Repository repository;

    /**
     * Constructor pentru clasa Service.
     *
     * @param repository Instanta repository care gestioneaza datele.
     */
    public Service(Repository repository) {
        this.repository = repository;
    }

    /**
     * Returneaza lista de plante medicinale.
     *
     * @return Lista de plante medicinale.
     */
    public List<PlantaMedicinala> getPlante() {
        return repository.getPlante();
    }

    /**
     * Cumpara o anumita cantitate dintr-o planta specificata.
     *
     * @param denumire Denumirea plantei care va fi cumparata.
     * @param cantitate Cantitatea care va fi cumparata.
     * @throws IllegalArgumentException daca planta nu exista sau cantitatea depaseste stocul.
     */
    public void cumparaPlanta(String denumire, int cantitate) {
        repository.actualizeazaCantitate(denumire, cantitate);
    }

    /**
     * Adauga o planta noua in lista si actualizeaza fisierul.
     *
     * @param denumire Denumirea plantei.
     * @param cantitate Cantitatea disponibila initial.
     * @param pret Pretul plantei.
     */
    public void adaugaPlanta(String denumire, int cantitate, double pret) {
        PlantaMedicinala planta = new PlantaMedicinala(denumire, cantitate, pret);
        repository.adaugaPlanta(planta);
    }
}
