import java.util.Scanner;

public class Doraemon {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        line = "\tHello! I'm Doraemon!\n\tWhat can I do for you?";
        printOut(line);
        line = in.nextLine();

        while(!line.equals("bye")){
            printOut("\t"+line);
            line = in.nextLine();
        }
        line = "\tBye. Hope to see you again soon!";
        printOut(line);
    }
    private static void printOut(String line){
        System.out.println("\t__________________________________________________");
        System.out.println(line);
        System.out.println("\t__________________________________________________");
    }
}

