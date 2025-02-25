// Name:
// USC NetID:
// CSCI455 PA2
// Spring 2025

import java.util.ArrayList;

/**
 * Class BookshelfKeeper
 * Enables users to perform efficient putPos or pickHeight operations on a bookshelf of books
 * kept in non-decreasing order by height. Single books can only be added or removed from one
 * of the two ends of the bookshelf. Pick or put operations are performed using the minimum
 * number of such calls.
 */
public class BookshelfKeeper {

   /**
    * Representation invariants:
    * 1. The contained bookshelf (Bookshelf bookshelf) is always in non-decreasing order.
    * 2. Every book height is positive.
    */

   private Bookshelf bookshelf;
   private int totalMoves;
   //public static final int SIZE_INDICATOR_HELPER = 2;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf();
      totalMoves = 0;
      assert isValidBookshelfKeeper() : "Bookshelf should be valid";
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      assert sortedBookshelf.isSorted() : "Bookshelf is not sorted";
      bookshelf = sortedBookshelf;
      totalMoves = 0;
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps the bookshelf sorted.
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * @param position indicates which book at index "position" is requested to be removed
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      assert position >= 0 && position < bookshelf.size() : "Position is out of bounds";
      int callToMutator = 0;
      ArrayList<Integer> tempBookHolder = new ArrayList<Integer>();//temp storage for removing books from either ends

      if (position < middlePosition()) {
         for (int i = 0; i < position; i++) {
            int removedBook = bookshelf.removeFront();
            tempBookHolder.add(removedBook);
            totalMoves++;
            callToMutator++;
         }
         bookshelf.removeFront();
         totalMoves++;
         callToMutator++;
         // adding back the end books which were removed earlier
         for (int i = tempBookHolder.size() - 1; i >= 0; i--) {
            bookshelf.addFront(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      } else {
         for (int i = bookshelf.size() - 1; i > position; i--) {
            tempBookHolder.add(bookshelf.removeLast());
            totalMoves++;
            callToMutator++;
         }
         bookshelf.removeLast();
         totalMoves++;
         callToMutator++;
         // adding back the end books which were removed earlier
         for (int i = tempBookHolder.size() - 1; i >= 0; i--) {
            bookshelf.addLast(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      }
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
      return callToMutator;
   }

   /**
    * Inserts a book with the specified height into the shelf.
    * Keeps the contained bookshelf sorted after the insertion.
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * @param height shows new book's height we want to add to shelf
    * PRE: height > 0
    */
   public int putHeight(int height) {
      assert height > 0 : "Height must be greater than 0";
      ArrayList<Integer> tempBookHolder = new ArrayList<Integer>(); //temp storage for removing books from either ends
      int callToMutator = 0;
      int heightIndex = heightLocator(height);
      int n = bookshelf.size();

      // Determining if removing heightIndex books is less (or equal) than removing (n - heightIndex) books.
      if (heightIndex <= n - heightIndex) {
         for (int i = 0; i < heightIndex; i++) {
            tempBookHolder.add(bookshelf.removeFront());
            totalMoves++;
            callToMutator++;
         }

         bookshelf.addFront(height);
         totalMoves++;
         callToMutator++;

         // Adding back the end books which were removed earlier
         for (int i = tempBookHolder.size() - 1; i >= 0; i--) {
            bookshelf.addFront(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      } else {
         // Remove (n - heightIndex) books from the back.
         int booksToRemove = n - heightIndex;
         for (int i = 0; i < booksToRemove; i++) {
            tempBookHolder.add(bookshelf.removeLast());
            totalMoves++;
            callToMutator++;
         }
         // Insert the new book at the back.
         bookshelf.addLast(height);
         totalMoves++;
         callToMutator++;

         // Adding back the end books which were removed earlier
         for (int i = tempBookHolder.size() - 1; i >= 0; i--) {
            bookshelf.addLast(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      }
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
      return callToMutator;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pickPos and putHeight operations.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
      return totalMoves;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
      return bookshelf.size();
   }


    /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed
    * by the number of bookshelf mutator calls made to perform the last pick or put operation,
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    */
   public String toString() {
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
      return bookshelf.toString();
   }

   /**
    * Checks the representation invariant of this BookshelfKeeper.
    */
   private boolean isValidBookshelfKeeper() {
      for (int i = 0; i < bookshelf.size() - 1; i++) {
         if (bookshelf.getBooks().get(i) <= 0 ||
                 bookshelf.getBooks().get(i) > bookshelf.getBooks().get(i + 1)) {
            return false;
         }
      }
      return true;
   }

   // add any other private methods here.

   /**
    * Returns the middle index of the bookshelf.
    * Iff the bookshelf is empty (size 0), it returns 0.
    */
   private int middlePosition() {
      return bookshelf.size() / 2;
   }

   /**
    * Returns the first index at which a new book of the given height should be inserted so that the bookshelf remains sorted.
    * @param height is the height of book we want to insert.
    * Iff no such index exists, the new book goes at the end.
    */
   private int heightLocator(int height) {
      ArrayList<Integer> books = bookshelf.getBooks();
      int n = books.size();
      for (int i = 0; i < n; i++) {
         if (books.get(i) > height) {
            return i;
         }
      }
      return n;
   }
}
