import java.util.Scanner;
import java.util.ArrayList;


/**
 * This program manages a "bookshelf" of books (represented by their heights) in non-decreasing order.
 *
 * 1. The program first prompts the user to enter an initial arrangement of books (each height must be positive
 *    and sorted in non-decreasing order).
 * 2. Once validated, the user can enter commands:
 *    - "pick <index>": removes the book at the given index.
 *    - "put <height>": inserts a new book of the given height into the shelf, maintaining sorted order.
 *    - "end": exits the program.
 * 3. After each valid "pick" or "put" command, the program displays the updated shelf status.
 *
 * Invalid input (negative or zero height, out-of-range index, or invalid command) causes an error message
 * and terminates the program.
 */

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

    // Prints the current status of the bookshelf.
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
                if (lineScanner.hasNextInt()) {
                    int index = lineScanner.nextInt();
                    validateIndex(keeper, index);
                    keeper.pickPos(index);
                    printCurrentBookshelf(keeper);
                } else {
                    invalidCommandPrinter();
                }
            } else if (command.toLowerCase().equals("put")) {
                if (lineScanner.hasNextInt()) {
                    int height = lineScanner.nextInt();
                    validateHeight(keeper, height);
                    keeper.putHeight(height);
                    printCurrentBookshelf(keeper);
                } else {
                    invalidCommandPrinter();
                }
            } else {
                invalidCommandPrinter();
            }
        }
    }

    // Checks if the index is within the valid range of books on the shelf
    private static void validateIndex(BookshelfKeeper helperKeeper, int helperIndex) {
        if (helperIndex < 0 || helperIndex >= helperKeeper.getNumBooks()) {
            System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
            System.out.println("Exiting Program.");
            System.exit(0);
        }
    }

    // Confirms that the book height is positive
    private static void validateHeight(BookshelfKeeper helperKeeper, int helperHeight) {
        if (helperHeight <= 0) {
            System.out.println("ERROR: Height of a book must be positive.");
            System.out.println("Exiting Program.");
            System.exit(0);
        }
    }

    // Prints error messages once input command is neither "pick", "put" nor "end"
    private static void invalidCommandPrinter(){
        System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
        System.out.println("Exiting Program.");
        System.exit(0);
    }
}
