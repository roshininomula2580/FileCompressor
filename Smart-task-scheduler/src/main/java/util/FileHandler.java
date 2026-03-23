package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Task;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "tasks.json";

    public static void saveTasks(List<Task> tasks) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();

            gson.toJson(tasks, writer);

        } catch (Exception e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static List<Task> loadTasks() {

        try (FileReader reader = new FileReader(FILE_NAME)) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            Type type = new TypeToken<List<Task>>() {}.getType();

            return gson.fromJson(reader, type);

        } catch (Exception e) {
            return null;
        }
    }
}