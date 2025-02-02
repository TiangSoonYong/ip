import java.util.Scanner;

public class Doraemon {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        List list = new List();
        String[] words;

        line = "\t Hello! I'm Doraemon!\n\t What can I do for you?";
        echo(line);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) list.printTasks();
            else if (line.startsWith("mark")) {
                words = line.split(" ");
                list.markAsDone(Integer.parseInt(words[1]) - 1);
            } else if (line.startsWith("unmark")) {
                words = line.split(" ");
                list.markAsNotDone(Integer.parseInt(words[1]) - 1);
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

