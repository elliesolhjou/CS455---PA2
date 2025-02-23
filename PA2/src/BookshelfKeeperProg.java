import java.util.Scanner;
import java.util.ArrayList;

public class BookshelfKeeperProg {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter initial arrangement of books followed by newline:");
        String initialLine = in.nextLine();

        Scanner lineScanner = new Scanner(initialLine);
        ArrayList<Integer> initBooks = new ArrayList<Integer>();

        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                initBooks.add(lineScanner.nextInt());
            } else {
                lineScanner.next();
            }
        }

        // Error-checking
        // 1. Each height must be positive.
        // 2. The list must be in non-decreasing order.
        for (int i = 0; i < initBooks.size(); i++) {
            if (initBooks.get(i) <= 0) {
                System.out.println("ERROR: Height of a book must be positive.");
                System.out.println("Exiting Program.");
                in.close();
                return;
            }
            if (i > 0 && initBooks.get(i) < initBooks.get(i - 1)) {
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
                System.out.println("Exiting Program.");
                in.close();
                return;
            }
        }

        // Create a Bookshelf and a BookshelfKeeper using the initial arrangement captured from user input.
        Bookshelf bookshelf = new Bookshelf(initBooks);
        BookshelfKeeper keeper = new BookshelfKeeper(bookshelf);

        // Print the initial configuration: shelf, operation count for last op, total operations.
        System.out.println(keeper.toString() + " 0 0");
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");


        while (in.hasNextLine()) {
            String commandLine = in.nextLine().trim();
            if (commandLine.equals("")) {
                // Skipping blank lines
                continue;
            }

            // Use a lineScanner to parse the command line.
            Scanner cmdScanner = new Scanner(commandLine);
            String command = cmdScanner.next();

            if (command.equals("end")) {
                cmdScanner.close();
                System.out.println("Exiting Program.");
                in.close();
                return;
            } else if (command.equals("pick")) {
                if (cmdScanner.hasNextInt()) {
                    int index = cmdScanner.nextInt();

                    if (index < 0 || index >= keeper.getNumBooks()) {
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        System.out.println("Exiting Program.");
                        cmdScanner.close();
                        in.close();
                        return;
                    }
                    int opCount = keeper.pickPos(index);
                    System.out.println(keeper.toString() + " " + opCount + " " + keeper.getTotalOperations());
                }
                // If the integer argument were missing, we assume test input always has it.
            } else if (command.equals("put")) {
                // Expect a put height
                if (cmdScanner.hasNextInt()) {
                    int height = cmdScanner.nextInt();
                    if (height <= 0) {
                        System.out.println("ERROR: Height of a book must be positive.");
                        System.out.println("Exiting Program.");
                        cmdScanner.close();
                        in.close();
                        return;
                    }
                    int opCount = keeper.putHeight(height);
                    System.out.println(keeper.toString() + " " + opCount + " " + keeper.getTotalOperations());
                }
            } else {
                // Invalid command entered.
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                System.out.println("Exiting Program.");
                cmdScanner.close();
                in.close();
                return;
            }
            cmdScanner.close();
        }

        in.close();
    }
}
