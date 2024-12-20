import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Borrowed: " + (isBorrowed ? "Yes" : "No");
    }
}

public class LibraryManagementSystemGUI {
    private ArrayList<Book> books = new ArrayList<>();
    private JFrame frame;
    private JTextArea displayArea;

    public LibraryManagementSystemGUI() {
        frame = new JFrame("Library Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Add Book");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton displayButton = new JButton("Display Books");

        addButton.addActionListener(e -> addBook());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());
        displayButton.addActionListener(e -> displayBooks());

        buttonPanel.add(addButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(displayButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addBook() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID:");
        String title = JOptionPane.showInputDialog(frame, "Enter Book Title:");
        String author = JOptionPane.showInputDialog(frame, "Enter Book Author:");

        try {
            int id = Integer.parseInt(idStr);
            books.add(new Book(id, title, author));
            displayArea.append("Book added: " + title + " by " + author + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.");
        }
    }

    private void borrowBook() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to borrow:");

        try {
            int id = Integer.parseInt(idStr);
            for (Book book : books) {
                if (book.getId() == id) {
                    if (!book.isBorrowed()) {
                        book.borrowBook();
                        displayArea.append("Book borrowed: " + book.getTitle() + "\n");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book is already borrowed.");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.");
        }
    }

    private void returnBook() {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Book ID to return:");

        try {
            int id = Integer.parseInt(idStr);
            for (Book book : books) {
                if (book.getId() == id) {
                    if (book.isBorrowed()) {
                        book.returnBook();
                        displayArea.append("Book returned: " + book.getTitle() + "\n");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book was not borrowed.");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.");
        }
    }

    private void displayBooks() {
        if (books.isEmpty()) {
            displayArea.append("No books available.\n");
        } else {
            displayArea.append("Books in the library:\n");
            for (Book book : books) {
                displayArea.append(book + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystemGUI();
    }
}
