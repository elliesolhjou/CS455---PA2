// Name: Ellie Solhjou
// USC NetID: 1424729265
// CSCI455 PA2
// Spring 2025


import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

public class Bookshelf {

   /**
      Representation invariant:

      * all books in shelf must have a positive height ( > 0)
      * shelf.size() always must be >= 0
      * non null values (no gap) should exist on shelf (in shelf array)
      

   */
   
   private ArrayList<Integer> shelf;
   private int book; 
   
  


   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      
     
      shelf = new ArrayList<Integer>();
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
      

   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      
      shelf = new ArrayList<Integer> (pileOfBooks);
      
      assert isValidBookshelf();
      

   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      
      assert height > 0: "Height should be a positive number." ;
            
      shelf.add(0, height);
      
      assert isValidBookshelf();
      
      
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      
      assert height > 0: "Height should be a positive nuymber.";
      
      shelf.add(height);
      
      assert isValidBookshelf();
     
      
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      
      assert shelf.size() > 0 : "Cannot remove from an empty bookshelf.";
      
      book = shelf.get(0);
      shelf.remove(0);

      assert isValidBookshelf();

      return book;
      
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      
      assert shelf.size () > 0 : "Cannot remove from an empty bookshelf.";
      
      book = shelf.get(shelf.size()-1);
      shelf.remove(shelf.size()-1);
      
      assert isValidBookshelf();
      
      return book;
      
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      
      assert 0 <= position && position < shelf.size(): "Invalid book position."; 
      book = shelf.get(position);
      
      assert isValidBookshelf();
      return book;
      
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      
      assert isValidBookshelf();
      return shelf.size();

   }
   
   public ArrayList<Integer> getBooks (){
      
      assert isValidBookshelf();
      
      return new ArrayList<Integer>(shelf);
      
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      
      assert isValidBookshelf();
      
      return shelf.toString();
      
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      
      assert isValidBookshelf();
     
      if (shelf.size() >0){
         for (int i=0; i< shelf.size() - 1 ; i++){
            return !(shelf.get(i) > shelf.get(i+1));
         }
      }

      
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf(){
      
      
      for (int bookOnShelf: shelf) {

         return !(bookOnShelf <= 0);
      }
      return true;

   }
   

      
      

}
