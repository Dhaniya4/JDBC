
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class JavaSwingProject extends JFrame implements ActionListener {

    JTextField idField, nameField, courseField;
    JButton addBtn, showBtn;
    JTable table;
    DefaultTableModel model;

    Connection conn;

    public JavaSwingProject() {

        // -------- CONNECT TO MYSQL --------
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dhaniya?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                ""
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "DB ERROR: " + e.getMessage());
        }

        setTitle("Student Management System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // -------- TITLE --------
        JLabel title = new JLabel("Student Management", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // -------- FORM PANEL --------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Enter Details"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        idField = new JTextField(15);
        formPanel.add(idField, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        // Course
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Course:"), gbc);

        gbc.gridx = 1;
        courseField = new JTextField(15);
        formPanel.add(courseField, gbc);

        // -------- BUTTON PANEL --------
        JPanel btnPanel = new JPanel();

        addBtn = new JButton("Add");
        showBtn = new JButton("Show");

        styleButton(addBtn);
        styleButton(showBtn);

        addBtn.addActionListener(this);
        showBtn.addActionListener(this);

        btnPanel.add(addBtn);
        btnPanel.add(showBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.WEST);

        // -------- TABLE --------
        model = new DefaultTableModel(new String[]{"ID", "Name", "Course"}, 0);
        table = new JTable(model);

        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // -------- BUTTON STYLE --------
    void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            addStudent();
        } else if (e.getSource() == showBtn) {
            showStudents();
        }
    }

    // -------- CLEAR FIELDS --------
    void clearFields() {
        idField.setText("");
        nameField.setText("");
        courseField.setText("");
        idField.requestFocus();
    }

    // -------- ADD --------
    void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());

            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO students (id, name, course) VALUES (?, ?, ?)"
            );

            pst.setInt(1, id);
            pst.setString(2, nameField.getText());
            pst.setString(3, courseField.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Added!");
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // -------- SHOW --------
    void showStudents() {
        try {
            model.setRowCount(0);

            PreparedStatement pst;

            if (idField.getText().isEmpty()) {
                pst = conn.prepareStatement("SELECT * FROM students");
            } else {
                pst = conn.prepareStatement(
                    "SELECT * FROM students WHERE id = ?"
                );
                pst.setInt(1, Integer.parseInt(idField.getText()));
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("course")
                });
            }

            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new JavaSwingProject();
    }
}