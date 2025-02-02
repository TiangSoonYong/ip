public class List {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        System.out.println("\t__________________________________________________");
        System.out.println("\t added: " + description);
        System.out.println("\t__________________________________________________");
    }

    public void printTasks() {
        System.out.println("\t__________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("\t %d.[%s] %s%n", i + 1, tasks[i].getStatusIcon(), tasks[i].description);
        }
        System.out.println("\t__________________________________________________");
    }

    public void markAsDone(int taskIndex) {
        tasks[taskIndex].setAsDone();
        System.out.println("\t__________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.printf("\t   [%s] %s%n", tasks[taskIndex].getStatusIcon(), tasks[taskIndex].description);
        System.out.println("\t__________________________________________________");
    }

    public void markAsNotDone(int taskIndex) {
        tasks[taskIndex].setAsNotDone();
        System.out.println("\t__________________________________________________");
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.printf("\t   [%s] %s%n", tasks[taskIndex].getStatusIcon(), tasks[taskIndex].description);
        System.out.println("\t__________________________________________________");
    }
}
