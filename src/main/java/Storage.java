import java.io.File;
import java.io.IOException;

public class Storage {

    private String filePath;
    private File taskFile;

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
}
