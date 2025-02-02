import java.util.Scanner;

public class Doraemon {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        line = "\tHello! I'm Doraemon!\n\tWhat can I do for you?";
        echo(line);
        line = in.nextLine();

        List list = new List();
        while (!line.equals("bye")) {
            if (line.equals("list")) list.printTasks();
            else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                list.markAsDone(Integer.parseInt(words[1]));
            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                list.markAsNotDone(Integer.parseInt(words[1]));
            } else list.addTask(line);
            line = in.nextLine();
        }
        line = "\tBye. Hope to see you again soon!";
        echo(line);
    }

    private static void echo(String line) {
        System.out.println("\t__________________________________________________");
        System.out.println(line);
        System.out.println("\t__________________________________________________");
    }
}

