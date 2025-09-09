package betty.task;

public enum Priority {
    LOW("LOW", 1),
    MEDIUM("MEDIUM", 2),
    HIGH("HIGH", 3),
    NONE("NONE", 0);

    private final String label;
    private final int level;
    Priority(String label, int level) {
        this.label = label;
        this.level = level;
    }

    public static Priority getPriority(String priority) {
        return switch(priority.toLowerCase()) {
        case "low" -> LOW;
        case "medium" -> MEDIUM;
        case "high" -> HIGH;
        default -> NONE;
        };
    }
    @Override
    public String toString() {
        return this.label;
    }
}
