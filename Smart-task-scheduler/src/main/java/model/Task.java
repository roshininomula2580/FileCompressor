package model;

import java.time.LocalDate;

public class Task implements Comparable<Task> {

    private String title;
    private int priority;
    private LocalDate deadline;

    // ⭐ Required for Gson (do not remove)
    public Task() {
    }

    // ⭐ Constructor used when adding task from UI
    public Task(String title, int priority, LocalDate deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task other) {

        // 🔥 Higher priority first
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }

        // 🔥 If priority same → earlier deadline first
        return this.deadline.compareTo(other.deadline);
    }

    @Override
    public String toString() {
        return title +
                " | Priority: " + priority +
                " | Deadline: " + deadline;
    }
}