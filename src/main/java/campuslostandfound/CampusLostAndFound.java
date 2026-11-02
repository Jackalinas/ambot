/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package campuslostandfound;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class CampusLostAndFound extends JFrame {

    private ItemLinkedList itemList = new ItemLinkedList();

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField locationField;
    private JTextField personField;
    private JTextField searchField;

    private JComboBox<String> typeBox;
    private JComboBox<String> statusBox;

    private JTable table;
    private DefaultTableModel model;

    public CampusLostAndFound() {

        setTitle("Campus Lost-and-Found Tracker");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // HEADER
        JLabel title = new JLabel(
                "CAMPUS LOST-AND-FOUND TRACKER",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(new Color(30, 70, 120));

        add(title, BorderLayout.NORTH);

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Item Information"));

        nameField = new JTextField();
        descriptionField = new JTextField();
        locationField = new JTextField();
        personField = new JTextField();

        typeBox = new JComboBox<>(
                new String[]{"Lost Item", "Found Item"});

        statusBox = new JComboBox<>(
                new String[]{"Lost", "Found", "Claimed"});

        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Location:"));
        inputPanel.add(locationField);

        inputPanel.add(new JLabel("Reported By:"));
        inputPanel.add(personField);

        inputPanel.add(new JLabel("Record Type:"));
        inputPanel.add(typeBox);

        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(statusBox);

        // TABLE
        String[] columns = {
            "Item Name", "Description",
            "Location", "Date Reported", "Type",
            "Reported By", "Status"
        };

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Record");
        JButton searchButton = new JButton("Search");
        JButton showButton = new JButton("Show All");
        JButton updateButton = new JButton("Update Status");
        JButton deleteButton = new JButton("Delete");

        searchField = new JTextField(15);

        buttonPanel.add(addButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(showButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // BUTTON ACTIONS
        addButton.addActionListener(e -> addRecord());
        searchButton.addActionListener(e -> searchRecord());
        searchField.addActionListener(e -> searchRecord());
        showButton.addActionListener(e -> displayRecords());
        updateButton.addActionListener(e -> updateRecord());
        deleteButton.addActionListener(e -> deleteRecord());
    }

    // ADD RECORD
    private void addRecord() {

        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String location = locationField.getText().trim();
        String person = personField.getText().trim();

        if (name.isEmpty() || description.isEmpty()
                || location.isEmpty() || person.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this, "Please fill in all fields!");

            return;
        }

        String type = (String) typeBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();
        String date = LocalDate.now().toString();

        item newitem;

        if (type.equals("Lost Item")) {
            newitem = new LostItem(
                    name, description, location,
                    date, status, person);
        } else {
            newitem = new FoundItem(
                    name, description, location,
                    date, status, person);
        }

        itemList.addItem(newitem);

        displayRecords();
        clearFields();

        JOptionPane.showMessageDialog(
                this, "Record added successfully!");
    }

    // SEARCH RECORD
    private void searchRecord() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Enter an Item Name!");
            return;
        }

        item found = itemList.searchitem(keyword);

        if (found == null) {
            JOptionPane.showMessageDialog(
                    this, "Item not found!");
            return;
        }

        displayRecords();

        Node current = itemList.getHead();
        int row = 0;

        while (current != null) {
            if (current.getData() == found) {
                table.setRowSelectionInterval(row, row);
                table.scrollRectToVisible(
                        table.getCellRect(row, 0, true));
                break;
            }

            current = current.getNext();
            row++;
        }

        JOptionPane.showMessageDialog(
                this,
                "ITEM FOUND!\n\n"
                + "Name: " + found.getItemName()
                + "\nLocation: " + found.getLocation()
                + "\nStatus: " + found.getStatus());
    }

    // DISPLAY RECORDS USING POLYMORPHISM
private void displayRecords() {

    model.setRowCount(0);

    Node current = itemList.getHead();

    while (current != null) {

        item currentItem = current.getData();

        Object[] row = {
            currentItem.getItemName(),
            currentItem.getDescription(),
            currentItem.getLocation(),
            currentItem.getDateReported(),
            currentItem.getItemType(),
            currentItem.getPersonName(),
            currentItem.getStatus()
        };

        model.addRow(row);

        current = current.getNext();
    }
}

    // UPDATE STATUS
    private void updateRecord() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "Please select a record first!");
            return;
        }

        String[] statuses = {"Lost", "Found", "Claimed"};

        String newStatus = (String) JOptionPane.showInputDialog(
                this,
                "Select new status:",
                "Update Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statuses,
                statuses[0]);

        if (newStatus != null) {
            itemList.updateStatus(selectedRow, newStatus);
            displayRecords();

            JOptionPane.showMessageDialog(
                    this, "Status updated successfully!");
        }
    }

    // DELETE RECORD
    private void deleteRecord() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "Please select a record first!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            itemList.deleteItem(selectedRow);
            displayRecords();

            JOptionPane.showMessageDialog(
                    this, "Record deleted successfully!");
        }
    }

    // CLEAR FIELDS
    private void clearFields() {

        nameField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        personField.setText("");
        searchField.setText("");
    }

    // APPLICATION START
    public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {
        LostFoundFrame app = new LostFoundFrame();
        app.setVisible(true);
    });
}
}

