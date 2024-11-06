/**
 * Zerlyne Nandwani-Simons
 * Oct. 9, 2024
 * CMSC-256-001
 *
 * Project 4 - Linked Lists & Iterators
 * The purpose of this program is to manipulate linked lists and use iterators to return the expected value(s).
 */

package cmsc256;

public class CustomList<T> extends CMSC256LinkedList<T> {
    public CustomList() {
        super();
    }

    /**
     * returns the middle element
     * if the # of elements in the list is even, there will be two middle nodes; so return the second one
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81], the method returns 22
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81, 101], the method returns 12
     *
     * @throws IllegalStateException if the list is empty or null
     * @return an instance T
     */
    public T getMiddleElement() {
        if (this.getLength() == 0 || this == null) {
            throw new IllegalStateException();
        }

        return this.getEntry(this.getLength() / 2 + 1);
    }

    /**
     * returns the first half of the list
     * if the # of elements in the list is odd, include the extra element
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81], the method returns a list [23, 34, 98, 22]
     *
     * @return an instance of a class that implements the ListInterface
     */
    public ListInterface<T> getFirstHalf() {
        int middle = this.getLength() / 2;

        if (this.getLength() % 2 != 0) {
            middle++;
        }

        CustomList z = new CustomList();

        for (int i = 0; i < middle; i++) {
            z.add(this.getEntry(i + 1));
        }

        return z;
    }

    /**
     * returns the last half of the list
     * if the # of elements in the list is odd, exclude the extra element
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81], the method returns a list containing [12, 76, 81]
     *
     * @return an instance of a class that implements the ListInterface
     */
    public ListInterface<T> getLastHalf() {
        int middle = this.getLength() / 2;

        if (this.getLength() % 2 != 0) {
            middle++;
        }

        CustomList z = new CustomList();

        for (int i = middle; i < this.getLength(); i++) {
            z.add(this.getEntry(i + 1));
        }

        return z;

    }

    /**
     * returns a List that consists of all and only the elements in every 4th positions (i.e. 4th, 8th, etc)
     * returns it in the current string, in the same order as the current list
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81, 101], the method returns [22, 101]
     *
     * @return an instance of a class that implements the ListInterface
     */
    public ListInterface<T> getEveryFourthElement() {
        CustomList result = new CustomList();

        if (this.getLength() >= 4) {
            for (int i = 4; i <= this.getLength(); i += 4) {
                result.add(this.getEntry(i));
            }
        }
        return result;
    }

    /**
     * @return true if there are any elements in the list that occur twice
     *      false otherwise
     * note: if an element occurs more than twice, do not include it
     * ex. if the list contains [23, 34, 22, 12, 76, 81, 101, 12, 23, 33, 23], the method returns true
     */
    public boolean hasDuplicates() {
        for (int i = 0; i < this.getLength(); i++) {
            T current = this.getEntry(i + 1);
            int count = 1;

            for (int j = i + 1; j < this.getLength(); j++) {
                T compare = this.getEntry(j + 1);

                if (current.equals(compare)) {
                    count++;
                }
            }
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return an instance of a class that implements the ListInterface that contains elements in list appearing >1x
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81, 101, 12, 23, 33, 23], the method returns [23, 12]
     */
    public ListInterface<T> getAllMultiples() {
        CustomList z = new CustomList();

        for (int i = 0; i < this.getLength(); i++) {
            T current = this.getEntry(i + 1);

            for (int j = i + 1; j < this.getLength(); j++) {
                T compare = this.getEntry(j + 1);

                if (current.equals(compare) && !z.contains(current)) {
                    z.add(current);
                }
            }
        }
        return z;
    }

    /**
     * alters list by deleting any elements that occur 2x in list
     * elements that occur >2x remain in the list
     * ex. if the list contains [23, 34, 98, 22, 12, 76, 81, 101, 12, 23, 33, 23], method removes 12
     *      list becomes [23, 34, 98, 22, 76, 81, 101, 23, 33, 23]
     */
    public void removeAllDuplicates() {
        CustomList<Integer> z = new CustomList();

        for (int i = 0; i < this.getLength(); i++) {
            T current = this.getEntry(i + 1);
            int count = 1;
            int innerpos = -1;

            for (int j = i + 1; j < this.getLength(); j++) {
                T compare = this.getEntry(j + 1);

                if (current.equals(compare)) {
                    count++;
                    innerpos = j;
                }
            }

            if (count == 2) {
                z.add(i + 1);
                z.add(innerpos + 1);

            }
        }
        for (int i = 1; i <= z.getLength(); i++) {
            this.remove(z.getEntry(i));
        }
    }

    /**
     * removes the nth node from the end of the list
     * ex. if the list contains [43, 68, 11, 5, 69, 37, 70] and 1 is passed to method, a value of 70 is returned
     *      list becomes [43, 68, 11, 5, 69, 37]
     *
     * @return the element from the position that was removed
     */
    public T removeNthNodeFromEnd(int n) {
        int pos = this.getLength() + 1 - n;
        T z = this.getEntry(pos);
        this.remove(pos);

        return z;
    }
}
