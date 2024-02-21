/**
 * ArrayList import.
 */
import java.util.ArrayList;
/**
 * NoSuchElementException import.
 */
import java.util.NoSuchElementException;
/**
 * Random import.
 */
import java.util.Random;
/**
 * @author Justin Kamina
 * @version 1.1337
 * Team class.
 * contains an array of Member instances
 */
public class Team {
    private Member[] members;
    /**
     * Constructor for instantiating a Team.
     * Takes a Members array and deep copies it to this.members.
     * Throws IAE if passed in array is null or contains null elements.
     * @param otherMembers array to deep copy to this.members
     */
    public Team(Member[] otherMembers) {
        if (otherMembers == null) {
            throw new IllegalArgumentException("This array should not be null.");
        }
        for (int i = 0; i < otherMembers.length; i++) {
            if (otherMembers[i] == null) {
                throw new IllegalArgumentException("One or more of the elements in this array is null.");
            }
        }
        this.members = new Member[otherMembers.length];
        for (int j = 0; j < otherMembers.length; j++) {
            this.members[j] = otherMembers[j];
        }
    }
    /**
     * Method that sorts members array in increasing order.
     * Uses merge sort algorithm.
     */
    public void mergeSortMembers() {
        if (members == null || members.length == 0) {
            return;
        }
        members = mergeHelper(members);
    }
    /**
     * RECURSIVE method that performs the merge sort.
     * A separate third method merges the sorted halves of the members array.
     * @param arr Array of Member instances to be sorted.
     * @return returns sorted members array.
     */
    private Member[] mergeHelper(Member[] arr) {
        int memberLength = arr.length;
        if (memberLength < 2) {
            return arr;
        }
        int midIndex = memberLength / 2;
        Member[] leftHalf = new Member[midIndex];
        Member[] rightHalf = new Member[memberLength - midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = arr[i];
        }
        for (int j = midIndex; j < memberLength; j++) {
            rightHalf[j - midIndex] = arr[j];
        }
        return HWUtils.merge(mergeHelper(leftHalf), mergeHelper(rightHalf));
    }
    /**
     * Method that takes in a 2D array and merges it with the members array.
     * The resulting members array is sorted as the mergeSortMembers method is called.
     * @param arr 2D array to be merged with members array.
     */
    public void mergeAllMembers(Member[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            mergeHelper(arr[i]);
            members = HWUtils.merge(members, arr[i]);
        }
    }
    /**
     * Takes in a Member instance and uses binary search to search for it.
     * members array is sorted first as that is a requirement for binary search.
     * If found, the instance in the array is returned.
     * Otherwise a NoSuchElementException is thrown.
     * @param memberToFind Member instance to search for in members array
     * @return returns the Member instance in the array or an exception is thrown.
     */
    public Member searchMember(Member memberToFind) {
        if (memberToFind == null) {
            throw new IllegalArgumentException("This input cannot be null.");
        }
        mergeSortMembers();
        Member foundOrNot = searchHelper(memberToFind, 0, members.length - 1);
        if (foundOrNot == null) {
            throw new NoSuchElementException("This member is not in the members array.");
        }
        return foundOrNot;
    }
    /**
     * RECURSIVE helper method that performs the binary search.
     * @return Returns the Member instance if the member is found; Returns null if the member is not found.
     */
    private Member searchHelper(Member memberToFind, int low, int high) {
        if (high >= low && low <= members.length - 1) {
            int middlePosition = low + (high - low) / 2;
            Member middleMember = members[middlePosition];
            if (memberToFind.equals(middleMember)) {
                return middleMember;
            }
            if (memberToFind.compareTo(middleMember) < 0) {
                return searchHelper(memberToFind, low, middlePosition - 1);
            } else {
                return searchHelper(memberToFind, middlePosition + 1, high);
            }
        }
        return null;
    }
    /**
     * Method that reverses the order of the sorted members array such that it is decreasing.
     * @return returns a deep copy of the members array in reversed order.
     */
    public Member[] reverseMembers() {
        Member[] reversed = new Member[members.length];
        reverseHelper(reversed, 0, reversed.length);
        return reversed;
    }
    /**
     * RECURSIVE method that traverses through the members array.
     * Assigns Members in descending order to the instantiated reversed array above
     * @param arr New array that will hold the Members in reverse order.
     * @param index int which increments each time the method is recursively called.
     * @param arrLength int index of member that will be assigned to arr each time and decrements.
     */
    private void reverseHelper(Member[] arr, int index, int arrLength) {
        if (index > members.length - 1) {
            return;
        } else {
            arr[index] = members[arrLength - 1];
            reverseHelper(arr, index + 1, arrLength - 1);
        }
    }
    /**
     * Method that randomly selects two Member instances.
     * from the members array to be leaders and puts them in an ArrayList
     * The subgroup of the first Member is FRONTEND and the second is BACKEND
     * If there are no Members with one or either subgroup, a NoSuchElementException will be thrown.
     * @return returns the resulting leaders ArrayList if an exception wasn't thrown.
     */
    public ArrayList<Member> selectLeaderboard() {
        ArrayList<Member> front = new ArrayList<Member>();
        ArrayList<Member> back = new ArrayList<Member>();
        leaderHelper(front, back, 0);
        if (front.size() == 0 && back.size() == 0) {
            throw new NoSuchElementException("Failed to select leaders for both subgroups.");
        } else if (front.size() == 0) {
            throw new NoSuchElementException("Failed to select a leader for the FRONTEND subgroup.");
        } else if (back.size() == 0) {
            throw new NoSuchElementException("Failed to select a leader for the BACKEND subgroup.");
        }
        ArrayList<Member> leaders = new ArrayList<Member>();
        Random rand = new Random();
        leaders.add(front.get(rand.nextInt(front.size())));
        leaders.add(back.get(rand.nextInt(back.size())));
        return leaders;
    }
    /**
     * RECURSIVE method that traverses through the members array.
     * Adds a Member instance to either the front or back array mentioned in the above method(or neither)
     * depending on the Member's subgroup
     * @param arr1 ArrayList to hold FRONTEND Members
     * @param arr2 ArrayList to hold BACKEND Members
     * @param index int to be incrememted while traversing the array.
     */
    private void leaderHelper(ArrayList<Member> arr1, ArrayList<Member> arr2, int index) {
        if (index > members.length - 1) {
            return;
        } else if (members[index].getSubgroup().equals(Group.FRONTEND)) {
            arr1.add(members[index]);
            leaderHelper(arr1, arr2, index + 1);
        } else if (members[index].getSubgroup().equals(Group.BACKEND)) {
            arr2.add(members[index]);
            leaderHelper(arr1, arr2, index + 1);
        } else {
            leaderHelper(arr1, arr2, index + 1);
        }
    }
    /**
     * Main method to test code.
     * @param args main method
     */
    public static void main(String[] args) {
        Member m1 = new Member("Ken", Group.BACKEND, 9);
        Member m2 = new Member("Erich", Group.BACKEND, 8);
        Member m3 = new Member("Nathalie", Group.FRONTEND, 7);
        Member m4 = new Member("Eman", Group.FRONTEND, 6);
        Member m5 = new Member("Erich", Group.FRONTEND, 8);
        Member m6 = new Member("Erich", Group.BACKEND, 10);
        Member m7 = new Member("Pat", Group.FRONTEND, 7);
        Member m8 = new Member("Harrison", Group.ADMIN, 11);
        Member m9 = new Member("Klaus", Group.FRONTEND, 4);
        Member m10 = new Member("Frank", Group.ADMIN, 8);
        Member m11 = new Member("Ricky", Group.ADMIN, 12);
        Member m12 = new Member("Joe", Group.ADMIN, 10);
        Member[] array1 = new Member[] {m1, m2, m3, m4, m5, m6};
        Member[] array2 = new Member[] {m7};
        Member[] array3 = new Member[] {m1};
        Member[] array4 = new Member[] {m1, m2, m3, m4, m5, m6, m11, m12};
        Member[][] arr2D = {{m2, m1}, {m4, m9, m10}, {m8}};
        System.out.println("mergeSortMembers tests:");
        Team myTeam = new Team(array3);
        myTeam.mergeSortMembers();
        for (int i = 0; i < myTeam.members.length; i++) {
            System.out.println(myTeam.members[i]);
        }
        System.out.println();
        myTeam.members = array1;
        myTeam.mergeSortMembers();
        for (int i = 0; i < myTeam.members.length; i++) {
            System.out.println(myTeam.members[i]);
        }
        System.out.println();
        myTeam.members = array4;
        myTeam.mergeSortMembers();
        for (int i = 0; i < myTeam.members.length; i++) {
            System.out.println(myTeam.members[i]);
        }
        System.out.println();
        System.out.println("mergeAllMembers test:");
        myTeam.members = array2;
        myTeam.mergeAllMembers(arr2D);
        for (int i = 0; i < myTeam.members.length; i++) {
            System.out.println(myTeam.members[i]);
        }
        System.out.println();
        System.out.println("searchMember tests:");
        Member harrison = new Member("Harrison", Group.ADMIN, 11);
        System.out.println(myTeam.searchMember(harrison));
        System.out.println(myTeam.searchMember(harrison) == harrison);
        System.out.println(myTeam.searchMember(new Member("Eman", Group.FRONTEND, 6)));
        System.out.println(myTeam.searchMember(new Member("Pat", Group.FRONTEND, 7)));
        System.out.println();
        System.out.println("reverseMembers test:");
        Member[] test2 = myTeam.reverseMembers();
        System.out.println("Reversed Array:");
        for (int i = 0; i < test2.length; i++) {
            System.out.println(test2[i]);
        }
        System.out.println();
        System.out.println("Unchanged members array:");
        for (int i = 0; i < myTeam.members.length; i++) {
            System.out.println(myTeam.members[i]);
        }
        System.out.println();
        System.out.println("selectLeaderboard tests:");
        ArrayList leaderBoard = myTeam.selectLeaderboard();
        System.out.println(leaderBoard.get(0));
        System.out.println(leaderBoard.get(1));
        System.out.println();
    }
}