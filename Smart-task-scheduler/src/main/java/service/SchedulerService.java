package service;

import java.time.LocalDate;
import model.Task;
import util.TaskQueue;

public class SchedulerService {

    private TaskQueue taskQueue = new TaskQueue();

    public void addTask(String title, int priority, LocalDate deadline) {

        Task task = new Task(title, priority, deadline);
        taskQueue.addTask(task);

        System.out.println("Task added successfully!");
    }

    public void executeTask() {

        Task task = taskQueue.getNextTask();

        if (task == null) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Executing → " + task);
        }
    }

    public void showTasks() {
        taskQueue.showAllTasks();
    }

    public void deleteTask(int index) {
        taskQueue.deleteTask(index);
    }
}