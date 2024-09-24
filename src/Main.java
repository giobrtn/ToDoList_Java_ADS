import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        ToDoListGUI frame = new ToDoListGUI();
        frame.setVisible(true);
    });
}