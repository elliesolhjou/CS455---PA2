import java.util.Scanner;
import java.util.ArrayList;

public class BookshelfKeeperProg {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read and confirm the initial list from user.
        ArrayList<Integer> initBooks = readInput(in);
        validateInput(initBooks);

        // Create the bookshelf and keeper after validating input.
        Bookshelf bookshelf = new Bookshelf(initBooks);
        BookshelfKeeper keeper = new BookshelfKeeper(bookshelf);

        // Print initial arrangement: shelf representation, latest callToMutator Exp Val. (0), totalMove Exp.val (0)
        printCurrentBookshelf(keeper);
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

        // Process user commands.
        runCommands(keeper, in);

    }

    // Reads the initial arrangement of books from one user input line.
    private static ArrayList<Integer> readInput(Scanner in) {
        System.out.println("Please enter initial arrangement of books followed by newline:");
        String initialLine = in.nextLine();
        Scanner lineScanner = new Scanner(initialLine);
        ArrayList<Integer> books = new ArrayList<Integer>();
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                books.add(lineScanner.nextInt());
            }
            else {
                // Skip if that are not integers.
                lineScanner.next();
            }
        }
        return books;
    }

    // Error-checking helper function for valid arrangement and book height
    private static void validateInput(ArrayList<Integer> books) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) <= 0) {
                System.out.println("ERROR: Height of a book must be positive.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
        for (int i = 1; i < books.size(); i++) {
            if (books.get(i) < books.get(i - 1)) {
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
    }

    // Prints the current status of the bookshelf. Used a separate function for printing, since method name helps with understanding of code
    private static void printCurrentBookshelf(BookshelfKeeper keeper) {
        System.out.println(keeper.toString());
    }

    // Processes input commands from user
    private static void runCommands(BookshelfKeeper keeper, Scanner in) {
        while (in.hasNextLine()) {
            String commandLine = in.nextLine().trim(); // Removes whitespaces on input line ends
            if (commandLine.equals("")) {
                continue;
            }
            Scanner lineScanner = new Scanner(commandLine); // Reads tokens on trimmed input line
            String command = lineScanner.next(); //Grabs next Token as string

            if (command.toLowerCase().equals("end")) {
                System.out.println("Exiting Program.");
                System.exit(0);
            } else if (command.toLowerCase().equals("pick")) {
                if (lineScanner.hasNextInt()) { // Expects an index corresponding to a book's position on the shelf
                    int index = lineScanner.nextInt();
                    // Check if the index is within the valid range of books on the shelf
                    if (index < 0 || index >= keeper.getNumBooks()) {
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        System.out.println("Exiting Program.");
                        System.exit(0);
                    }
                    keeper.pickPos(index);
                    printCurrentBookshelf(keeper);
                //Error-checking statements
                } else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    System.out.println("Exiting Program.");
                    System.exit(0);
                }
            } else if (command.toLowerCase().equals("put")) {
                if (lineScanner.hasNextInt()) { // Expects a positive integer representing the book's height
                    int height = lineScanner.nextInt();
                    // Confirms that the book height is positive
                    if (height <= 0) {
                        System.out.println("ERROR: Height of a book must be positive.");
                        System.out.println("Exiting Program.");
                        System.exit(0);
                    }
                    keeper.putHeight(height);
                    printCurrentBookshelf(keeper);
                //Error-checking condition statements
                } else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    System.out.println("Exiting Program.");
                    System.exit(0);
                }
            //Error-checking condition statements
            } else {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
    }
}
