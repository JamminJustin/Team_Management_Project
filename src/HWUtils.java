/**
 * This class implements some useful methods for the homework.
 *
 * @author Your friendly CS1331 TAs
 * @version v-13.31
 */
public class HWUtils {
    /**
     * Helper method for merging two arrays. The two input arrays must be already sorted.
     *
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @return an array of length arr1.length + arr2.length, with all elements properly sorted
     */
    public static Member[] merge(Member[] arr1, Member[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        Member[] res = new Member[n1 + n2];

        int i = 0;
        int j = 0;
        int index = 0;

        while (i < n1 && j < n2) {
            res[index++] = (arr1[i].compareTo(arr2[j]) < 0) ? arr1[i++] : arr2[j++];
        }
        while (i < n1) {
            res[index++] = arr1[i++];
        }
        while (j < n2) {
            res[index++] = arr2[j++];
        }

        return res;
    }

    /**
     * Copies the specified range of the specified Member array into a new Member array.
     * The initial index of the range (start) must lie between zero and original.length, inclusive.
     * The value at original[start] is placed into the initial element
     * of the copy(unless start == original.length or start == end).
     * Values from subsequent elements in the original array are placed into subsequent elements in the copy.
     * The final index of the range (end) must be greater than or equal to start.
     * The length of the returned array will be end - start.
     * The resulting array is of exactly the same class as the original array.
     *
     * @param original the Member array from which a range is to be copied
     * @param start    the initial index of the range to be copied, inclusive
     * @param end      the final index of the range to be copied, exclusive.
     * @return Member[] starting at index start to end
     * @throws ArrayIndexOutOfBoundsException if start < 0, start > original.length, end < 0, or end > original.length
     * @throws IllegalArgumentException       if start > end
     * @throws NullPointerException           if original is null
     */
    public static Member[] copyOfRange(Member[] original, int start, int end) throws ArrayIndexOutOfBoundsException,
                                                                                     IllegalArgumentException,
                                                                                     NullPointerException {
        if (original == null) {
            throw new NullPointerException("array reference \"original\" is null");
        } else if (start > end) {
            throw new IllegalArgumentException(
                    String.format("start index %d is greater than end index %d", start, end));
        } else if (start < 0 || start > original.length) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("start index %d is out of bounds for array of length %d", start, original.length));
        } else if (end > original.length) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("end index %d is out of bounds for array of length %d", end, original.length));
        }

        Member[] copy = new Member[end - start];
        for (int i = start; i < end; i += 1) {
            copy[i - start] = original[i];
        }
        return copy;
    }
}