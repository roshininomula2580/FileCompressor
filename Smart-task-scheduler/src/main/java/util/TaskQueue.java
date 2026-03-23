package util;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TaskQueue {

    private PriorityQueue<Task> queue;

    public TaskQueue() {

        queue = new PriorityQueue<>();

        // 🔹 Load saved tasks
        List<Task> savedTasks = FileHandler.loadTasks();

        if (savedTasks != null) {
            queue.addAll(savedTasks);
        }
    }

    public void addTask(Task task) {

        queue.add(task);

        // 🔥 SAVE AFTER ADD
        FileHandler.saveTasks(new ArrayList<>(queue));
    }

    public Task getNextTask() {

        Task task = queue.poll();

        FileHandler.saveTasks(new ArrayList<>(queue));

        return task;
    }

    public void showAllTasks() {

        if (queue.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int i = 1;
        for (Task task : queue) {
            System.out.println(i++ + ". " + task);
        }
    }

    public void deleteTask(int index) {

        List<Task> list = new ArrayList<>(queue);

        if (index < 1 || index > list.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        list.remove(index - 1);

        queue.clear();
        queue.addAll(list);

        FileHandler.saveTasks(list);

        System.out.println("Task deleted successfully!");
    }
}