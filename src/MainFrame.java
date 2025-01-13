import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa MainFrame reprezinta interfata grafica a aplicatiei de gestionare a plantelor medicinale.
 */
public class MainFrame extends JFrame {
    private Service service;
    private DefaultListModel<String> listModel;
    private JList<String> plantaList;
    private JTextField denumireField, cantitateField, pretField, cumparaCantitateField;

    /**
     * Constructor pentru clasa MainFrame.
     *
     * @param service Serviciul utilizat pentru interactiunea cu datele aplicatiei.
     */
    public MainFrame(Service service) {
        this.service = service;
        setTitle("Magazin Plafar");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
    }

    /**
     * Initializeaza componentele interfetei grafice.
     */
    private void initComponents() {
        listModel = new DefaultListModel<>();
        plantaList = new JList<>(listModel);
        incarcaPlante();

        denumireField = new JTextField(10);
        cantitateField = new JTextField(5);
        pretField = new JTextField(5);
        cumparaCantitateField = new JTextField(5);

        JButton adaugaButton = new JButton("Adauga");
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaPlanta();
            }
        });

        JButton cumparaButton = new JButton("Cumpara");
        cumparaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cumparaPlanta();
            }
        });

        JPanel adaugaPanel = new JPanel(new FlowLayout());
        adaugaPanel.add(new JLabel("Denumire:"));
        adaugaPanel.add(denumireField);
        adaugaPanel.add(new JLabel("Cantitate:"));
        adaugaPanel.add(cantitateField);
        adaugaPanel.add(new JLabel("Pret:"));
        adaugaPanel.add(pretField);
        adaugaPanel.add(adaugaButton);

        JPanel cumparaPanel = new JPanel(new FlowLayout());
        cumparaPanel.add(new JLabel("Cantitate:"));
        cumparaPanel.add(cumparaCantitateField);
        cumparaPanel.add(cumparaButton);

        add(new JScrollPane(plantaList), BorderLayout.CENTER);
        add(adaugaPanel, BorderLayout.NORTH);
        add(cumparaPanel, BorderLayout.SOUTH);
    }

    /**
     * Incarca plantele disponibile in lista grafica.
     */
    private void incarcaPlante() {
        listModel.clear();
        for (PlantaMedicinala planta : service.getPlante()) {
            listModel.addElement(planta.toString());
        }
    }

    /**
     * Adauga o planta noua in lista.
     */
    private void adaugaPlanta() {
        String denumire = denumireField.getText();
        int cantitate = Integer.parseInt(cantitateField.getText());
        double pret = Double.parseDouble(pretField.getText());
        service.adaugaPlanta(denumire, cantitate, pret);
        incarcaPlante();
        clearFields();
    }

    /**
     * Cumpara o planta din lista, reducand cantitatea disponibila.
     */
    private void cumparaPlanta() {
        try {
            String selectedValue = plantaList.getSelectedValue();
            if (selectedValue != null && !selectedValue.isEmpty()) {
                String denumire = selectedValue.split(" - ")[0];
                int cantitate = Integer.parseInt(cumparaCantitateField.getText());
                service.cumparaPlanta(denumire, cantitate);
                incarcaPlante();
                cumparaCantitateField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Selectati o planta pentru cumparare!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduceti o cantitate valida!", "Eroare", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reseteaza campurile de introducere a datelor.
     */
    private void clearFields() {
        denumireField.setText("");
        cantitateField.setText("");
        pretField.setText("");
    }

    /**
     * Punctul de intrare al aplicatiei.
     *
     * @param args Argumentele liniei de comanda.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginDialog loginDlg = new LoginDialog(null);
                loginDlg.setVisible(true);

                if (loginDlg.isSucceeded()) {
                    Repository repository = new Repository("plante.txt", "PlanteVandute.txt");
                    Service service = new Service(repository);
                    MainFrame mainFrame = new MainFrame(service);
                    mainFrame.setVisible(true);
                } else {
                    System.exit(0);
                }
            }
        });
    }
}
