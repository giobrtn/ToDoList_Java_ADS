import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI extends JFrame {
    private TaskManager taskManager;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskInput;
    private JButton addButton, removeButton, completeButton;

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

        addButton = new JButton("Adicionar Tarefa");
        removeButton = new JButton("Remover Tarefa");
        completeButton = new JButton("Completar Tarefa");

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeTask();
            }
        });
    }


    private void addTask() {
        String description = taskInput.getText();
        if (!description.isEmpty()) {
            taskManager.addTask(description); // Adiciona ao TaskManager
            taskListModel.addElement(taskManager.getTask(taskManager.getTaskCount() - 1)); // Atualiza a lista visual
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
