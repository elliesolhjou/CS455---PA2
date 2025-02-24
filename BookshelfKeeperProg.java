import java.util.ArrayList;
import java.util.Scanner;

public class BookshelfKeeperProg{
    public static void main (String[] args){
        Scanner in = new Scanner();
        Bookshelf bookshelf = readInput(in);
        validateInput(bookshelf);

        //After validation create bookshelf and bookshelfKeeper objects
        Bookshelf initialBooks = new Bookshelf(Bookshelf);
        BookshelfKeeper keeper = new BookshelfKeeper(initialBooks);


        //Print initial arrangement
        // printCurrentBookshelf(BookshelfKeeper keeper, in 0);

        // run commands 

    }

}
private static printCurrentBookshelf(BookshelfKeeper keeper, int operationCount){
    System.out.println(keeper.toString() + " " + operationCount + " " + keeper.getTotalOperations());
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

// Error-checking helper function for valid arrangement and book height
private static void validateInput(Bookshelf books){
    for (int i = 0; i < books.size(); i++){
        if (books.get(i) <= 0) { 
            System.out.println("Height of a book must be positive");
            System.out.println("Exiting Program");
            System.exit(0);
        }
    }
    for (int i= 1; i< books.size(); i++){
        if (books.get(i-1) > books.get(i)){
            System.out.println("Heights must be specified in non-decreasing order.");
            System.out.println("Exiting Program.");
            System.exit(0);
        }
    }
}

// Helper function for reading commands
private static void runCommands(BookshelfKeeper keeper, string command){
    System.out.print("yay");
}