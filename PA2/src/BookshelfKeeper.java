// Name:
// USC NetID:
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

   private Bookshelf bookshelf;
   private int totalMoves;
   public static final int SIZE_INDICATOR_HELPER = 2;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {

      bookshelf = new Bookshelf();
      totalMoves = 0;

   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      assert sortedBookshelf != null : "Bookshelf is null";
      assert sortedBookshelf.isSorted() : "Bookshelf is not sorted";
      bookshelf = sortedBookshelf;
      totalMoves = 0;
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
      int callToMutator = 0;
      ArrayList<Integer> tempBookHolder = new ArrayList<Integer>();
      int pickedBookedHeight = bookshelf.getBooks().get(position);
      if (position < middlePosition()){
         for (int i =0; i< position; i++ ){
            int removedBook = (bookshelf.removeFront());
            tempBookHolder.add(removedBook);
            totalMoves++;
            callToMutator++;
         }
         bookshelf.removeFront();
         for (int i =tempBookHolder.size()-1; i >= 0 ; i-- ){
            bookshelf.addFront(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      }
      else {
         for (int i =bookshelf.size()-1; i > position ; i-- ){
            int removedBook = bookshelf.removeLast();
            tempBookHolder.add(removedBook);
            totalMoves++;
            callToMutator++;
         }
         bookshelf.removeLast();
         for (int i = tempBookHolder.size()-1; i >= 0 ; i-- ){
            bookshelf.addLast(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      }
      return callToMutator++;
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
      assert height > 0 : "Height must be greater than 0";
      ArrayList<Integer> tempBookHolder = new ArrayList<Integer>();
      int callToMutator = 0;
      int heightIndex = heightLocator(height);
      if (heightIndex < middlePosition()){
         for (int i =0; i< heightIndex; i++ ){
            int removedBook = (bookshelf.removeFront());
            tempBookHolder.add(removedBook);
            totalMoves++;
            callToMutator++;
         }
         bookshelf.addFront(height);
         for (int i =tempBookHolder.size()-1; i >= 0 ; i-- ){
            bookshelf.addLast(tempBookHolder.get(i));
            totalMoves++;
            callToMutator++;
         }
      }
      else {
         for (int i = bookshelf.size()-1; i > heightIndex ; i-- ){
            int removedBook = bookshelf.removeLast();
            tempBookHolder.add(removedBook);
            totalMoves++;
            callToMutator++;
         }
         bookshelf.addLast(height);
      }
      return callToMutator++;
      //assert isValidBookshelfKeeper();
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {

      return totalMoves;   // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {

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

      return (bookshelf.toString());   // dummy code to get stub to compile

   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      for (int i = 0; i< bookshelf.size()-1; i++) {
         if (bookshelf.getBooks().get(i) <= 0) {return false;}
         if (bookshelf.getBooks().get(i) > bookshelf.getBooks().get(i+1)) {return false;}
      }
      return true;

   }

   // add any other private methods here
   private boolean isOddSize(){
      return (bookshelf.getBooks().size() % 2 != 0);
   }
   private double averageHeight(){
      ArrayList<Integer> currentBookList = bookshelf.getBooks();
      double averageHeight = 0.0;
      if (isOddSize()) {
         averageHeight = (double) currentBookList.get(middlePosition());
      }
      else {
         int middleIndexLow = middlePosition() - 1;
         int middleIndexHigh = middlePosition();
         averageHeight = ((double) (currentBookList.get(middleIndexLow) + currentBookList.get(middleIndexHigh))) / 2;
      }
      return averageHeight;
   }
   private int middlePosition() {
      return bookshelf.size() / 2;
   }

   private int heightLocator(int height) {
      int heightIndex = -1;
      if (height >= averageHeight()) {
         for (int i = bookshelf.size()-1; i > middlePosition(); i--) {
            if (bookshelf.getBooks().get(i) >= height && bookshelf.getBooks().get(i-1) < height) {
               heightIndex = i;
            }
         }
      }
      else {
         for (int i = 0; i < middlePosition(); i++) {
            if (bookshelf.getBooks().get(i) <= height && bookshelf.getBooks().get(i+1) > height) {
               heightIndex = i;
            }
         }
      }
      return heightIndex;
   }
//   private int pickPosOddSizeShelf(int position){
//      ArrayList<Integer> tempBookHolder = new ArrayList<Integer>();
//      int pickedBookedHeight = bookshelf.getBooks().get(position);
//      if (position < middlePosition()){
//         for (int i =0; i< position; i++ ){
//            int removedBook = (bookshelf.removeFront());
//            tempBookHolder.add(removedBook);
//            totalMoves++;
//         }
//         bookshelf.removeFront();
//         for (int i =tempBookHolder.size()-1; i >= 0 ; i-- ){
//            bookshelf.addFront(tempBookHolder.get(i));
//            totalMoves++;
//         }
//      }
//      else {
//         for (int i =bookshelf.size()-1; i > position ; i-- ){
//            int removedBook = bookshelf.removeLast();
//            tempBookHolder.add(removedBook);
//            totalMoves++;
//         }
//         bookshelf.removeLast();
//         for (int i = tempBookHolder.size()-1; i >= 0 ; i-- ){
//            bookshelf.addLast(tempBookHolder.get(i));
//            totalMoves++;
//         }
//      }
//      return pickedBookedHeight;
//   }








   public static void main(String[] args) {
      ArrayList<Integer> pileOfBook = new ArrayList<Integer> ();
      pileOfBook.add(1);
      pileOfBook.add(3);
      pileOfBook.add(5);
      pileOfBook.add(8);
      pileOfBook.add(15);
      pileOfBook.add(22);
//      pileOfBook.add(2);
//      pileOfBook.add(2);

      Bookshelf bookshelf2 = new Bookshelf(pileOfBook);
      System.out.println(bookshelf2.size());
      BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf2);
      System.out.println(bookshelfKeeper.isOddSize());
      System.out.println(bookshelfKeeper.middlePosition());
      System.out.println(bookshelfKeeper.averageHeight());
      System.out.println(bookshelfKeeper.pickPos(4));
      System.out.println(bookshelfKeeper.toString());
      System.out.println(bookshelfKeeper.getTotalOperations());
      System.out.println(bookshelfKeeper.heightLocator(20));
      System.out.println(bookshelfKeeper.putHeight(20));
      System.out.println(bookshelfKeeper.toString());

   }

}