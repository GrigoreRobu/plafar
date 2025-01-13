import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa LoginDialog reprezinta o fereastra de dialog pentru autentificare.
 */
public class LoginDialog extends JDialog {
    private JTextField userField;
    private JPasswordField passwordField;
    private boolean succeeded;

    /**
     * Constructor pentru clasa LoginDialog.
     *
     * @param parent Fereastra parinte pentru dialog.
     */
    public LoginDialog(Frame parent) {
        super(parent, "Login", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(userLabel, cs);

        userField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(userField, cs);

        JLabel passwordLabel = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(passwordLabel, cs);

        passwordField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(passwordField, cs);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login Panel - user&pass: aaa"));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate(getUsername(), getPassword())) {
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Login invalid",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    succeeded = false;
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                succeeded = false;
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(loginButton);
        bp.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    /**
     * Returneaza numele de utilizator introdus.
     *
     * @return Numele de utilizator.
     */
    public String getUsername() {
        return userField.getText().trim();
    }

    /**
     * Returneaza parola introdusa.
     *
     * @return Parola.
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Returneaza starea autentificarii.
     *
     * @return True daca autentificarea a reusit, altfel False.
     */
    public boolean isSucceeded() {
        return succeeded;
    }

    /**
     * Metoda pentru autentificarea utilizatorului.
     *
     * @param username Numele de utilizator.
     * @param password Parola.
     * @return True daca autentificarea este valida, altfel False.
     */
    private boolean authenticate(String username, String password) {
        // Verificare simplificata, ar trebui implementata o metoda mai robusta.
        return "aaa".equals(username) && "aaa".equals(password);
    }
}
