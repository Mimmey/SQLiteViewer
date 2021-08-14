package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SQLiteViewer extends JFrame {

    public SQLiteViewer() {
        super("SQLite Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();

        setLayout(null);
        setVisible(true);
    }

    private void initComponents() {
        JTextField fileNameTextField = new JTextField();
        fileNameTextField.setBounds(20,20, 520,30);
        fileNameTextField.setName("FileNameTextField");
        fileNameTextField.setVisible(true);
        add(fileNameTextField);

        JButton openFileButton = new JButton("Open");
        openFileButton.setBounds(560, 20, 100, 30);
        openFileButton.setName("OpenFileButton");
        openFileButton.setVisible(true);
        add(openFileButton);

        JComboBox<String> tablesComboBox = new JComboBox<>();
        tablesComboBox.setBounds(20, 70, 640, 30);
        tablesComboBox.setName("TablesComboBox");
        tablesComboBox.setVisible(true);
        add(tablesComboBox);

        JTextArea queryTextArea = new JTextArea();
        queryTextArea.setBounds(20, 120, 520, 100);
        queryTextArea.setName("QueryTextArea");
        queryTextArea.setVisible(true);
        queryTextArea.setEnabled(false);
        add(queryTextArea);

        JButton executeButton = new JButton("Execute");
        executeButton.setBounds(560, 120, 100, 30);
        executeButton.setName("ExecuteQueryButton");
        executeButton.setEnabled(false);
        executeButton.setVisible(true);
        add(executeButton);

        JTable table = new JTable();
        table.setBounds(20, 240, 650, 200);
        table.setName("Table");
        table.setVisible(true);
        add(table);

        openFileButton.addActionListener(e -> {
            tablesComboBox.removeAllItems();
            String databaseName = fileNameTextField.getText();

            if (databaseName != null && databaseName.trim().length() != 0) {
                ArrayList<String> tableNames = SQLiteInterface.getTables(databaseName);

                if (tableNames != null) {
                    for (String s : tableNames) {
                        tablesComboBox.addItem(s);
                        queryTextArea.setEnabled(true);
                        executeButton.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(new Frame(), "File doesn't exist!");
                    queryTextArea.setEnabled(false);
                    executeButton.setEnabled(false);
                }
            }
        });

        executeButton.addActionListener(e -> {
            String databaseName = fileNameTextField.getText();
            String query = queryTextArea.getText();
            DefaultTableModel model = SQLiteInterface.getTableModel(databaseName, query);
            if (model == null) {
                JOptionPane.showMessageDialog(new Frame(), "SQL error!");
            } else {
                table.setModel(model);
            }
        });

        tablesComboBox.addActionListener(e -> {
            String tableName = (String) tablesComboBox.getSelectedItem();
            queryTextArea.setText(TextGenerators.generateSelectFrom(tableName));
        });

    }
}
