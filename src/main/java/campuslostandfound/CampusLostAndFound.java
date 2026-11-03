package campuslostandfound;

public class CampusLostAndFound extends javax.swing.JFrame {

    private ItemLinkedList itemList = new ItemLinkedList();

    private javax.swing.JTextField nameField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JTextField locationField;
    private javax.swing.JTextField personField;
    private javax.swing.JTextField searchField;

    private javax.swing.JComboBox<String> typeBox;
    private javax.swing.JComboBox<String> statusBox;

    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel model;

    public CampusLostAndFound() {

        setTitle("Campus Lost-and-Found Tracker");
        setSize(1100, 650);

        setDefaultCloseOperation(
                javax.swing.JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout(10, 10));

        // HEADER
        javax.swing.JLabel title = new javax.swing.JLabel(
                "CAMPUS LOST-AND-FOUND TRACKER",
                javax.swing.SwingConstants.CENTER);

        title.setFont(new java.awt.Font(
                "Arial", java.awt.Font.BOLD, 23));

        title.setForeground(new java.awt.Color(30, 70, 120));

        add(title, java.awt.BorderLayout.NORTH);

        // INPUT PANEL
        javax.swing.JPanel inputPanel =
                new javax.swing.JPanel(
                        new java.awt.GridLayout(6, 2, 8, 8));

        inputPanel.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        "Item Information"));

        nameField = new javax.swing.JTextField();
        descriptionField = new javax.swing.JTextField();
        locationField = new javax.swing.JTextField();
        personField = new javax.swing.JTextField();

        typeBox = new javax.swing.JComboBox<>(
                new String[]{"Lost Item", "Found Item"});

        statusBox = new javax.swing.JComboBox<>(
                new String[]{"Lost", "Found", "Claimed"});

        inputPanel.add(new javax.swing.JLabel("Item Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new javax.swing.JLabel("Description:"));
        inputPanel.add(descriptionField);

        inputPanel.add(new javax.swing.JLabel("Location:"));
        inputPanel.add(locationField);

        inputPanel.add(new javax.swing.JLabel("Reported By:"));
        inputPanel.add(personField);

        inputPanel.add(new javax.swing.JLabel("Record Type:"));
        inputPanel.add(typeBox);

        inputPanel.add(new javax.swing.JLabel("Status:"));
        inputPanel.add(statusBox);

        // TABLE
        String[] columns = {
            "Item Name",
            "Description",
            "Location",
            "Date Reported",
            "Type",
            "Reported By",
            "Status"
        };

        model = new javax.swing.table.DefaultTableModel(
                columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row, int column) {
                return false;
            }
        };

        table = new javax.swing.JTable(model);
        table.setRowHeight(25);

        javax.swing.JScrollPane scrollPane =
                new javax.swing.JScrollPane(table);

        javax.swing.JPanel centerPanel =
                new javax.swing.JPanel(
                        new java.awt.BorderLayout(10, 10));

        centerPanel.add(
                inputPanel, java.awt.BorderLayout.NORTH);

        centerPanel.add(
                scrollPane, java.awt.BorderLayout.CENTER);

        add(centerPanel, java.awt.BorderLayout.CENTER);

        // BUTTON PANEL
        javax.swing.JPanel buttonPanel =
                new javax.swing.JPanel(
                        new java.awt.FlowLayout());

        javax.swing.JButton addButton =
                new javax.swing.JButton("Add Record");

        javax.swing.JButton searchButton =
                new javax.swing.JButton("Search");

        javax.swing.JButton showButton =
                new javax.swing.JButton("Show All");

        javax.swing.JButton updateButton =
                new javax.swing.JButton("Update Status");

        javax.swing.JButton deleteButton =
                new javax.swing.JButton("Delete");

        searchField = new javax.swing.JTextField(15);

        buttonPanel.add(addButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(showButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, java.awt.BorderLayout.SOUTH);

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

            javax.swing.JOptionPane.showMessageDialog(
                    this, "Please fill in all fields!");

            return;
        }

        String type = (String) typeBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();

        String date = java.time.LocalDate.now().toString();

        try {

            Item newItem;

            if (type.equals("Lost Item")) {

                newItem = new LostItem(
                        name, description, location,
                        date, status, person);

            } else {

                newItem = new FoundItem(
                        name, description, location,
                        date, status, person);
            }

            itemList.addItem(newItem);

            displayRecords();
            clearFields();

            javax.swing.JOptionPane.showMessageDialog(
                    this, "Record added successfully!");

        } catch (IllegalArgumentException ex) {

            javax.swing.JOptionPane.showMessageDialog(
                    this, ex.getMessage());
        }
    }

    // SEARCH RECORD
    private void searchRecord() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(
                    this, "Enter an Item Name!");

            return;
        }

        Item found = itemList.searchItem(keyword);

        if (found == null) {

            javax.swing.JOptionPane.showMessageDialog(
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

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "ITEM FOUND!\n\n"
                + "Name: " + found.getItemName()
                + "\nLocation: " + found.getLocation()
                + "\nStatus: " + found.getStatus());
    }

    // DISPLAY RECORDS
    private void displayRecords() {

        model.setRowCount(0);

        Node current = itemList.getHead();

        while (current != null) {

            Item currentItem = current.getData();

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

            javax.swing.JOptionPane.showMessageDialog(
                    this, "Please select a record first!");

            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);

        String[] statuses = {
            "Lost", "Found", "Claimed"
        };

        String newStatus =
                (String) javax.swing.JOptionPane.showInputDialog(
                        this,
                        "Select new status:",
                        "Update Status",
                        javax.swing.JOptionPane.QUESTION_MESSAGE,
                        null,
                        statuses,
                        statuses[0]);

        if (newStatus != null) {

            try {

                itemList.updateStatus(modelRow, newStatus);

                displayRecords();

                javax.swing.JOptionPane.showMessageDialog(
                        this, "Status updated successfully!");

            } catch (IllegalArgumentException ex) {

                javax.swing.JOptionPane.showMessageDialog(
                        this, ex.getMessage());
            }
        }
    }

    // DELETE RECORD
    private void deleteRecord() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            javax.swing.JOptionPane.showMessageDialog(
                    this, "Please select a record first!");

            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this record?",
                "Confirm Delete",
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {

            if (itemList.deleteItem(modelRow)) {

                displayRecords();

                javax.swing.JOptionPane.showMessageDialog(
                        this, "Record deleted successfully!");

            } else {

                javax.swing.JOptionPane.showMessageDialog(
                        this, "Unable to delete record!");
            }
        }
    }

    // CLEAR INPUT FIELDS
    private void clearFields() {

        nameField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        personField.setText("");
        searchField.setText("");
    }

    // APPLICATION START
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            CampusLostAndFound app =
                    new CampusLostAndFound();

            app.setVisible(true);
        });
    }
}