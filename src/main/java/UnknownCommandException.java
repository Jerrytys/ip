public class UnknownCommandException extends Exception{

    public UnknownCommandException() {
        super("Error! Unknown command provided.");
    }
}
