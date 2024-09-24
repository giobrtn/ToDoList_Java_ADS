import javax.swing.*;
import java.awt.*;


public class ToDoListGUI extends JFrame {
    private final TaskManager taskManager;
    private final DefaultListModel<Task> taskListModel;
    private final JList<Task> taskList;
    private final JTextField taskInput;

    public ToDoListGUI() {
        taskManager = new TaskManager();

        //Config geral
        setTitle("ToDo List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        taskInput = new JTextField(20);

        JButton addButton = new JButton("Adicionar Tarefa");
        JButton removeButton = new JButton("Remover Tarefa");
        JButton completeButton = new JButton("Completar Tarefa");

        //Painel de entrada de tarefa
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(taskInput);
        inputPanel.add(addButton);

        //Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);

        //Scroll para a lista de tarefas
        JScrollPane scrollPane = new JScrollPane(taskList);

        //Adicionando componentes ao frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //Adicionando listeners aos botões
        addButton.addActionListener(_ -> addTask());

        removeButton.addActionListener(_ -> removeTask());

        completeButton.addActionListener(_ -> completeTask());
    }


    private void addTask() {
        String description = taskInput.getText();
        if (!description.isEmpty()) {
            taskManager.addTask(description);
            taskListModel.addElement(taskManager.getTask(taskManager.getTaskCount() - 1));
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "A descrição da tarefa não pode estar vazia.");
        }
    }


    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.removeTask(selectedIndex);
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para remover.");
        }
    }

    //
    private void completeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.completeTask(selectedIndex);
            taskList.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para completar.");
        }
    }


}
