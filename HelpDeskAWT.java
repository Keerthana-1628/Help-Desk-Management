import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class Ticket {
    private int id;
    private boolean created;

    public Ticket(int id) {
        this.id = id;
        this.created = true;
    }

    public boolean isCreated() {
        return created;
    }

    public int getId() {
        return id;
    }
}

public class HelpDeskAWT extends Frame {
    private Map<String, String> users = new HashMap<>();
    private Map<Integer, Ticket> tickets = new HashMap<>();

    private TextField userNameField, ticketIdField, statusField;
    private TextArea outputArea;

    public HelpDeskAWT() {
        setTitle("Help Desk System");
        setSize(400, 400);
        setLayout(new FlowLayout());

        Label userLabel = new Label("User Name:");
        userNameField = new TextField(20);
        Button addUserButton = new Button("Add User");
        addUserButton.addActionListener(e -> addUser());

        Label ticketLabel = new Label("Ticket ID:");
        ticketIdField = new TextField(20);
        Button createTicketButton = new Button("Create Ticket");
        createTicketButton.addActionListener(e -> createTicket());

        Label statusLabel = new Label("Check Ticket ID:");
        statusField = new TextField(20);
        Button viewStatusButton = new Button("View Status");
        viewStatusButton.addActionListener(e -> viewTicketStatus());

        outputArea = new TextArea(10, 35);
        outputArea.setEditable(false);

        add(userLabel);
        add(userNameField);
        add(addUserButton);
        add(ticketLabel);
        add(ticketIdField);
        add(createTicketButton);
        add(statusLabel);
        add(statusField);
        add(viewStatusButton);
        add(outputArea);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void addUser() {
        String userName = userNameField.getText();
        if (userName.isEmpty()) {
            outputArea.append("Please enter a valid user name.\n");
            return;
        }
        users.put(userName, userName);
        outputArea.append("User added: " + userName + "\n");
        userNameField.setText("");
    }

    private void createTicket() {
        try {
            int ticketId = Integer.parseInt(ticketIdField.getText());
            if (tickets.containsKey(ticketId)) {
                outputArea.append("Ticket ID already exists. Please choose a different ID.\n");
            } else {
                tickets.put(ticketId, new Ticket(ticketId));
                outputArea.append("Ticket created with ID: " + ticketId + "\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid ticket ID. Please enter a numeric value.\n");
        }
        ticketIdField.setText("");
    }

    private void viewTicketStatus() {
        try {
            int ticketId = Integer.parseInt(statusField.getText());
            Ticket ticket = tickets.get(ticketId);
            if (ticket != null) {
                outputArea.append("Ticket ID: " + ticket.getId() + ", Status: " +
                        (ticket.isCreated() ? "Created" : "Not Created") + "\n");
            } else {
                outputArea.append("Ticket not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid ticket ID. Please enter a numeric value.\n");
        }
        statusField.setText("");
    }

    public static void main(String[] args) {
        new HelpDeskAWT();
    }
}
