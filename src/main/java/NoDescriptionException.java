public class NoDescriptionException extends Exception{

    public NoDescriptionException(String task) {
        super("Error! The description of " + task + " cannot be empty.");
    }
}

