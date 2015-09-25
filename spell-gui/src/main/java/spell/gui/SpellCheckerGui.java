package spell.gui;

import org.apache.felix.ipojo.annotations.*;
import spell.services.SpellChecker;

import javax.swing.*;

/**
 * A very simple Gui interacting with the CheckSpeller service
 */
@Component
@Instantiate
public class SpellCheckerGui extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Swing component where the user write the passage to check.
     */
    private JTextField passage = null;

    /**
     * Area where the result is displayed.
     */
    private JLabel result = null;

    /**
     * Service dependency on the SpellChecker.
     */
    @Requires
    private SpellChecker checker;

    /**
     * Constructor.
     * Initialize the GUI.
     */
    public SpellCheckerGui() {
        super();
        initComponents();
        this.setTitle("Spellchecker Gui");
    }

    /**
     * Initialize the Swing Gui.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        // The check button
        JButton checkButton = new JButton();
        result = new JLabel();
        passage = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Stop Felix...
        getContentPane().setLayout(new java.awt.GridBagLayout());

        checkButton.setText("Check");
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                check();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(checkButton, gridBagConstraints);

        result.setPreferredSize(new java.awt.Dimension(175, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(result, gridBagConstraints);

        passage.setPreferredSize(new java.awt.Dimension(175, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(passage, gridBagConstraints);

        pack();
    }

    /**
     * Check Button action.
     * Collects the user input and checks it.
     */
    private void check() {
        String[] result = checker.check(passage.getText());
        if (result != null) {
            this.result.setText(result.length + " word(s) are misspelled");
        } else {
            this.result.setText("All words are correct");
        }
    }

    /**
     * Start callback.
     * This method will be called when the instance becomes valid.
     * It set the Gui visibility to true.
     */
    @Validate
    public void start() {
        this.setVisible(true);
    }

    /**
     * Stop callback.
     * This method will be called when the instance becomes invalid or stops.
     * It deletes the Gui.
     */
    @Invalidate
    public void stop() {
        this.dispose();
    }
}