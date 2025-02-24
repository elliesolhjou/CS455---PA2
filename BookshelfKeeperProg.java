import java.util.ArrayList;
import java.util.Scanner;

public class BookshelfKeeperProg{
    public static void main (String[] args){
        Scanner in = new Scanner();

        // String input = int.NextLine();
        // System.out.print(input.toString());
    }
}



private static ArrayList<Integer> readInput(Scanner in){
    ArrayList<Integer> initialBookList = new ArrayList<Integer>();
    System.out.println("Please enter initial arrangement of books followed by newline:");
    String initialLine = in.nextLine();
    Scanner lineScanner = new Scanner(initialLine);
    while (lineScanner.hasNext()){
        if (lineScanner.hasNextInt()){
            initialBookList.add(lineScanner.nextInt());
        }
        else{
            lineScanner.next();
        }
        return initialBookList;
    }

}

private static void validateInput(){}

private static void runcCommands(){}