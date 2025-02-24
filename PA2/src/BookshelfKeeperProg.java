import java.util.Scanner;
import java.util.ArrayList;

public class BookshelfKeeperProg {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read and validate the initial arrangement.
        ArrayList<Integer> initBooks = readInitialBooks(in);
        validateInitialArrangement(initBooks);

        // Create the bookshelf and its keeper.
        Bookshelf bookshelf = new Bookshelf(initBooks);
        BookshelfKeeper keeper = new BookshelfKeeper(bookshelf);

        // Print initial configuration: shelf representation, last op count (0), total operations (0)
        printBookshelfStatus(keeper, 0);
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

        // Process user commands interactively.
        runCommands(keeper, in);

        in.close();
    }

    // Reads the initial arrangement of books from one user input line.
    private static ArrayList<Integer> readInitialBooks(Scanner in) {
        System.out.println("Please enter initial arrangement of books followed by newline:");
        String initialLine = in.nextLine();
        Scanner lineScanner = new Scanner(initialLine);
        ArrayList<Integer> books = new ArrayList<Integer>();
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                books.add(lineScanner.nextInt());
            } else {
                // Skip tokens that are not integers.
                lineScanner.next();
            }
        }
        lineScanner.close();
        return books;
    }

    // Validating the list of book heights is non-empty (or empty is allowed),
    // all heights are positive, and the list is in non-decreasing order.
    private static void validateInitialArrangement(ArrayList<Integer> books) {
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

    // Prints the current status of the bookshelf in the required format.
    private static void printBookshelfStatus(BookshelfKeeper keeper, int opCount) {
        System.out.println(keeper.toString() + " " + opCount + " " + keeper.getTotalOperations());
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
                lineScanner.close();
                System.out.println("Exiting Program.");
                System.exit(0);
            } else if (command.equals("pick")) {
                if (lineScanner.hasNextInt()) {
                    int index = lineScanner.nextInt();
                    if (index < 0 || index >= keeper.getNumBooks()) {
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        System.out.println("Exiting Program.");
                        lineScanner.close();
                        System.exit(0);
                    }
                    int opCount = keeper.pickPos(index);
                    printBookshelfStatus(keeper, opCount);
                } else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    System.out.println("Exiting Program.");
                    lineScanner.close();
                    System.exit(0);
                }
            } else if (command.equals("put")) {
                if (lineScanner.hasNextInt()) {
                    int height = lineScanner.nextInt();
                    if (height <= 0) {
                        System.out.println("ERROR: Height of a book must be positive.");
                        System.out.println("Exiting Program.");
                        lineScanner.close();
                        System.exit(0);
                    }
                    int opCount = keeper.putHeight(height);
                    printBookshelfStatus(keeper, opCount);
                } else {
                    System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                    System.out.println("Exiting Program.");
                    lineScanner.close();
                    System.exit(0);
                }
            } else {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                System.out.println("Exiting Program.");
                lineScanner.close();
                System.exit(0);
            }
            lineScanner.close();
        }
    }
}
