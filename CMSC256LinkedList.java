package cmsc256;

/**
 A class that implements the ADT list by using a chain of
 linked nodes that has a head reference.
 */
public class CMSC256LinkedList<T> implements ListInterface<T>{
        private Node firstNode;            // Reference to first node of chain
        private int  numberOfEntries;
        
        public CMSC256LinkedList() {
            initializeDataFields();
        }
        
        public void clear() {
            initializeDataFields();
        }
        
        public void add (T newEntry) {
            Node newNode = new Node(newEntry);
            
            if (isEmpty())
                firstNode = newNode;
            else   {                           // Add to end of non-empty list
                Node lastNode = getNodeAt(numberOfEntries);
                lastNode.setNextNode(newNode); // Make last node reference new node
            }
            
            numberOfEntries++;
        }
        
        public void add (int newPosition, T newEntry)	{
            if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
            {
                Node newNode = new Node(newEntry);
                
                if (newPosition == 1)                  // Case 1: Adding to the front
                {
                    newNode.setNextNode(firstNode);
                    firstNode = newNode;
                }
                else	{								         // Case 2: list is not empty
                    Node nodeBefore = getNodeAt(newPosition - 1);
                    Node nodeAfter = nodeBefore.getNextNode();
                    newNode.setNextNode(nodeAfter);
                    nodeBefore.setNextNode(newNode);
                }
                
                numberOfEntries++;
            }
            else
                throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
        
        public T remove(int givenPosition)	{
            T result = null;                           // Return value
            
            if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))   {
                assert !isEmpty();
                
                if (givenPosition == 1) {                // Case 1: Remove first entry
                    result = firstNode.getData();        // Save entry to be removed
                    firstNode = firstNode.getNextNode(); // Remove entry
                }
                else   {                                 // Case 2: Not first entry
                    
                    Node nodeBefore = getNodeAt(givenPosition - 1);
                    Node nodeToRemove = nodeBefore.getNextNode();
                    result = nodeToRemove.getData();     // Save entry to be removed
                    Node nodeAfter = nodeToRemove.getNextNode();
                    nodeBefore.setNextNode(nodeAfter);   // Remove entry
                }
                
                numberOfEntries--;                      // Update count
                return result;                          // Return removed entry
            }
            else
                throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
        
        public T replace(int givenPosition, T newEntry) {
            if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))  {
                Node desiredNode = getNodeAt(givenPosition);
                T originalEntry = desiredNode.getData();
                desiredNode.setData(newEntry);
                return originalEntry;
            }
            else
                throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
        
        public T getEntry(int givenPosition) {
            if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))	{
                assert !isEmpty();
                return getNodeAt(givenPosition).getData();
            }
            else
                throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
        }
        
        public T[] toArray() {
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] result = (T[])new Object[numberOfEntries];
            
            int index = 0;
            Node currentNode = firstNode;
            while ((index < numberOfEntries) && (currentNode != null)) {
                result[index] = currentNode.getData();
                currentNode = currentNode.getNextNode();
                index++;
            }
            
            return result;
        }
        
        public boolean contains(T anEntry) {
            boolean found = false;
            Node currentNode = firstNode;
            
            while (!found && (currentNode != null))	{
                if (anEntry.equals(currentNode.getData()))
                    found = true;
                else
                    currentNode = currentNode.getNextNode();
            }
            
            return found;
        }
        
        public int getLength() {
            return numberOfEntries;
        }
        
        public boolean isEmpty() {
            boolean result;
            
            if (numberOfEntries == 0) {  // Or getLength() == 0
                assert firstNode == null;
                result = true;
            }
            else {
                assert firstNode != null;
                result = false;
            }
            
            return result;
        }
        
        // Initializes the class's data fields to indicate an empty list.
        private void initializeDataFields()  {
            firstNode = null;
            numberOfEntries = 0;
        }
        
        // Returns a reference to the node at a given position.
        // Precondition: The chain is not empty;
        //               1 <= givenPosition <= numberOfEntries.
        private Node getNodeAt(int givenPosition)	{
            assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
            Node currentNode = firstNode;
            
            // Traverse the chain to locate the desired node
            // (skipped if givenPosition is 1)
            for (int counter = 1; counter < givenPosition; counter++)
                currentNode = currentNode.getNextNode();
            
            assert currentNode != null;
            
            return currentNode;
        }
        
        public void addAll(T[] items){
            for(int i = 0; i < items.length; i++)
                add(items[i]);
        }
        
        private class Node	{
            private T    data; // Entry in list
            private Node next; // Link to next node
            
            private Node(T dataPortion)  {
                data = dataPortion;
                next = null;
            }
            
            private Node(T dataPortion, Node nextNode) {
                data = dataPortion;
                next = nextNode;
            }
            
            private T getData()  {
                return data;
            }
            
            private void setData(T newData)  {
                data = newData;
            }
            
            private Node getNextNode() {
                return next;
            }
            
            private void setNextNode(Node nextNode)  {
                next = nextNode;
            }
        }
    }

