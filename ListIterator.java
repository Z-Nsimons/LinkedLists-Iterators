package cmsc256;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
   A class that represents an iterator for the ADT list.
*/
public class ListIterator<T> implements Iterator<T>{
   private ListInterface<T> list;
   private int              nextPosition;  // Position of entry last returned by next()
   private boolean nextWasCalled; // Needed by remove

   public ListIterator(ListInterface<T> aList) {
      list = aList;
      nextPosition = 0;
      nextWasCalled = false;
   } 
   
   public boolean hasNext() {
      return nextPosition < list.getLength();
   } 
   
   public T next() {
      if (hasNext()) {
         nextWasCalled = true;
         nextPosition++;			
         return list.getEntry(nextPosition);
      }
      else
         throw new NoSuchElementException("Illegal call to next(); " +
                                          "iterator is after end of list.");
   } 
   
   public void remove() {
      if (nextWasCalled) {
         // nextPosition was incremented by the call to next(), so
         // it is the position number of the entry to be removed
         list.remove(nextPosition);
         nextPosition--;        // A subsequent call to next() must be
                                // unaffected by this removal
         nextWasCalled = false; // Reset flag
      }
      else
         throw new IllegalStateException("Illegal call to remove(); " +
                                         "next() was not called.");
   }
} 
