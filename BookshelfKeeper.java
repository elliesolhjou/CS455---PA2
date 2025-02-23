// Name: Ellie Solhjou
// USC NetID: 1424729265
// CSCI455 PA2
// Spring 2025

import java.util.ArrayList;
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
      Representation invariant:

      <put rep. invar. comment here>

   */
   
   private int bookshelfSize;
   private int callToMutator;
   private ArrayList<Integer> bookArray;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookArray = new ArrayList<Integer>();

   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelfSize = sortedBookshelf.size();
      bookArray = sortedBookshelf.getBooks();
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
  
      callToMutator = 0;
      for (int i = position; i< bookshelfSize -1 ; i++){
          
         bookArray.set(i, bookArray.get(i+1));
         callToMutator++;

      }

      bookshelfSize--; 
         
      return callToMutator;
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {

      return 0;   // dummy code to get stub to compile
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      
       return 0;   // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      
       return 0;   // dummy code to get stub to compile
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
      
      return "";   // dummy code to get stub to compile
      
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      return false;  // dummy code to get stub to compile

   }

   // add any other private methods here


}
