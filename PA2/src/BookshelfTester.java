// Name: Ellie Solhjou
// USC NetID: 1424729265
// CSCI455 PA2
// Spring 2025



import java.util.ArrayList;

public class BookshelfTester{


   public static void main(String[] args){
      
      Bookshelf emptyBookshelf = new Bookshelf();
      
      System.out.printf("Books on empty shelf [exp: <empty>]: %s%n", emptyBookshelf.getBooks());
      
      System.out.printf("Length of empty shelf [exp: 0]: %d%n", emptyBookshelf.size());

      System.out.printf("String format of emptyBookshelf [exp: [] ]: %s%n ", emptyBookshelf.toString());
      
      System.out.printf("Checking if shelf is sorted [exp: true]: %s%n%n", emptyBookshelf.isSorted());
      
         
         
      ArrayList<Integer> pileOfBook = new ArrayList<Integer> ();
      pileOfBook.add(17);
      pileOfBook.add(3);
      pileOfBook.add(6);
      pileOfBook.add(8);
      pileOfBook.add(5);
      pileOfBook.add(2);
      
      Bookshelf bookshelf2 = new Bookshelf(pileOfBook);

      System.out.printf("Books on bookshelf2 [exp: [17, 3, 6, 8, 5, 2 ]]: %s%n", bookshelf2.getBooks());
      
      System.out.printf("String format of bookshelf2 [Exp: [17, 3, 6, 8, 5, 2 ]]: %s%n%n", bookshelf2.toString());
      
      
      //Ex3. 
      System.out.printf("Length of bookshelf2 [exp: 6]: %d%n%n",bookshelf2.size());

      System.out.printf("Checking if shelf is sorted [exp: false]: %s%n%n", bookshelf2.isSorted());
      
      System.out.printf("Height of book at position 4 [Exp: 5]: %d%n%n", bookshelf2.getHeight(4));
      
      
      //Ex4.
      System.out.printf("Testing Remove Last Method [exp: 2]: %d%n", bookshelf2.removeLast());
      System.out.printf("Testing length of shelf after removing last book [exp: 5]: %d%n", bookshelf2.size());
   
      System.out.printf("Testing Remove Front Method [exp: 17]: %d%n", bookshelf2.removeFront());
      System.out.printf("Testing Removing Front Method [exp: [3, 6, 8, 5]]: %s%n", bookshelf2.getBooks());
      System.out.printf("Testing length of shelf after removing Front book [exp: 4]: %d%n%n", bookshelf2.size());
      
      bookshelf2.addFront(20);
      //bug here I have to shift it in 
      System.out.printf("Testing adding Front Method [exp: [20, 3, 6, 8, 5]]: %s%n", bookshelf2.getBooks());
      System.out.printf("Testing length of shelf after adding Front book [exp: 5]: %d%n%n", bookshelf2.size());
      
      bookshelf2.addLast(25);
    
      System.out.printf("Testing adding Last Method [exp: [20, 3, 6, 8, 5, 25]]: %s%n", bookshelf2.getBooks());
      System.out.printf("Testing length of shelf after adding Last book [exp: 6]: %d%n%n", bookshelf2.size());
   
   
   
   
   
   
   }









}