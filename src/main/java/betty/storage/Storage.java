package betty.storage;

import betty.parser.Parser;
import betty.task.Task;
import betty.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final File taskFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = this.getFile();
    }

    // Method to get/create file to store task
    public File getFile() {
        File myFile = new File(this.filePath);
        try {
            // Create directories if not present
            File parent = myFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            // If file does not exist, create a new file
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return myFile;
    }

    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        // Use scanner to read file and parse each line to become a task object
        try {
            Scanner scanner = new Scanner(this.taskFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTask(line);
                taskList.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        return taskList;
    }
    // Function to store a taskList into your data list
    public void store(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskFile);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
