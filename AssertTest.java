// Name: Ellie Solhjou
// USC NetID: 1424729265
// CSCI455 PA2
// Spring 2025



import java.util.ArrayList;


public class AssertTest{
   
   public static void main(String[] args){
      System.out.println("creating VALID bookshelf");
      ArrayList<Integer> validBooks = new ArrayList<Integer>();
      validBooks.add(3);
      validBooks.add(5);
      validBooks.add(18);
      
      Bookshelf validShelf = new Bookshelf(validBooks);
      System.out.println("Susccessfully created valid bookshelf: "+ validShelf.toString());
      //System.out.println("getting a negative position to try pre-condition assert" + validShelf.getHeight(-2));
      System.out.println("...............................................");
      System.out.println();
      System.out.println();
      
      System.out.println("creating INVALID bookshelf");
      ArrayList<Integer> invalidBooks = new ArrayList<Integer>();
      invalidBooks.add(45);
      invalidBooks.add(-5);
      invalidBooks.add(18);
      
      Bookshelf invalidShelf = new Bookshelf(invalidBooks);
      System.out.println("if -ea hits here think again - created INVALID bookshelf: "+ invalidShelf.toString());
   }
   
   
   
}
   