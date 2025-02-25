import java.util.Scanner;
import java.util.ArrayList;

public class BookshelfKeeperProg {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read and confirm the initial list from user.
        ArrayList<Integer> initBooks = readInputBooks(in);
        assertInput(initBooks);

        // Create the bookshelf and keeper after validating input.
        Bookshelf bookshelf = new Bookshelf(initBooks);
        BookshelfKeeper keeper = new BookshelfKeeper(bookshelf);

        // Print initial arrangement: shelf representation, last op count (0), total operations (0)
        printCurrentBookshelf(keeper, 0);
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

        // Process user commands.
        runCommands(keeper, in);

    }

    // Reads the initial arrangement of books from one user input line.
    private static ArrayList<Integer> readInputBooks(Scanner in) {
        System.out.println("Please enter initial arrangement of books followed by newline:");
        String initialLine = in.nextLine();
        Scanner lineScanner = new Scanner(initialLine);
        ArrayList<Integer> books = new ArrayList<Integer>();
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                books.add(lineScanner.nextInt());
            } else {
                // Skip if that are not integers.
                lineScanner.next();
            }
        }
        return books;
    }

    // Error-checking helper function for valid arrangement and book height
    private static void assertInput(ArrayList<Integer> books) {
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
    private static void printCurrentBookshelf(BookshelfKeeper keeper, int operationCount) {
        System.out.println(keeper.toString() + " " + operationCount + " " + keeper.getTotalOperations());
    }


    private static void runCommands(BookshelfKeeper keeper, Scanner in) {
        while (in.hasNextLine()) {
            String commandLine = in.nextLine().trim();
            if (commandLine.equals("")) {
                continue;
            }
            Scanner lineScanner = new Scanner(commandLine);
            String command = lineScanner.next();

            if (command.equals("end")) {
                System.out.println("Exiting Program.");
                System.exit(0);
            } else if (command.equals("pick")) {
                if (lineScanner.hasNextInt()) {
                    int index = lineScanner.nextInt();
                    if (index < 0 || index >= keeper.getNumBooks()) {
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        System.out.println("Exiting Program.");
                        System.exit(0);
                    }
                    int operationCount = keeper.pickPos(index);
                    printCurrentBookshelf(keeper, operationCount);
                } else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    System.out.println("Exiting Program.");
                    System.exit(0);
                }
            } else if (command.equals("put")) {
                if (lineScanner.hasNextInt()) {
                    int height = lineScanner.nextInt();
                    if (height <= 0) {
                        System.out.println("ERROR: Height of a book must be positive.");
                        System.out.println("Exiting Program.");
                        System.exit(0);
                    }
                    int operationCount = keeper.putHeight(height);
                    printCurrentBookshelf(keeper, operationCount);
                } else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    System.out.println("Exiting Program.");
                    System.exit(0);
                }
            } else {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
    }
}
