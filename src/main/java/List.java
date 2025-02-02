public class List {
    private final String[] tasks = new String[100];
    private int taskCount = 0;

    public void addTask(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("\t__________________________________________________");
        System.out.println("\t added: " + task);
        System.out.println("\t__________________________________________________");
    }

    public void printTasks(){
        System.out.println("\t__________________________________________________");
        for (int i = 0; i < taskCount; i++){
            System.out.printf("\t%d. %s%n", i+1, tasks[i]);
        }
        System.out.println("\t__________________________________________________");

    }
}
