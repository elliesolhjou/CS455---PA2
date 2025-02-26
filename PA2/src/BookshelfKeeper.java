

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    * Representation Invariants (RI):
    * 1. The bookshelf is always in non-decreasing order by book height.
    * 2. Every book height is positive (height > 0).
    * 3. totalMoves is always >= 0.
    * 4. callToMutator is always >= 0.
    */

   private Bookshelf bookshelf;
   private int totalMoves;
   private int callToMutator;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {

      bookshelf = new Bookshelf();
      totalMoves = 0;
      callToMutator = 0;

      assert isValidBookshelfKeeper() : "Bookshelf should be valid";
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {

      assert sortedBookshelf.isSorted() : "Bookshelf is not sorted";

      bookshelf = sortedBookshelf;
      totalMoves = 0;
      callToMutator = 0;

      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {

      assert position >= 0 && position < bookshelf.size() : "Position is out of bounds";

      callToMutator = 0; // resetting mutator for pickPos operation
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
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
    * after the insertion.
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {

      assert height > 0 : "Height must be greater than 0";

      ArrayList<Integer> tempBookHolder = new ArrayList<Integer>(); //temp storage for removing books from either ends
      callToMutator = 0; // resetting mutator for putHeight operation
      int heightIndex = heightLocator(height);

      // Determining if removing heightIndex books is less (or equal) than removing (n - heightIndex) books.
      if (heightIndex <= bookshelf.size() - heightIndex) {
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
         int booksToRemove = bookshelf.size() - heightIndex;
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
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
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
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {
      assert isValidBookshelfKeeper() : "Bookshelf is not valid";
      return (bookshelf.toString()+ " " + callToMutator +  " " +totalMoves);
   }

   /**
    * Checks the representation invariant of this BookshelfKeeper.
    * Returns true iff all book heights are positive books are sorted in non-decreasing order
    */
   private boolean isValidBookshelfKeeper() {
      // Check that every book is positive.
      for (int i = 0; i < bookshelf.size(); i++) {
         if (bookshelf.getHeight(i) <= 0 ){
            return false;
         }
      }
      // Check that the bookshelf is in non-decreasing order.
      if(!bookshelf.isSorted()){
         return false;
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
    * @param height is the height of given book to be inserted.
    * Iff no books is higher than the given book, the new book goes at the end.
    */
   private int heightLocator(int height) {
      ArrayList<Integer> books = new ArrayList<Integer>();
      for (int i = 0; i < bookshelf.size(); i++) {
         books.add(bookshelf.getHeight(i));
      }
      int n = books.size();
      for (int i = 0; i < n; i++) {
         if (books.get(i) >= height) {
            return i;
         }
      }
      return n;
   }
}
