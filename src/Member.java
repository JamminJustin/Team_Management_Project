/**
 * This class describes a member in the team.
 *
 * @author Your friendly CS1331 TAs
 * @version v-13.31
 */
public class Member implements Comparable<Member> {
    private final String name;
    private final Group subgroup;
    private final int hoursWorked;

    /**
     * 3-arg constructor for a Member instance.
     *
     * @param name        - name of the member
     * @param subgroup    - the subgroup this member belongs to
     * @param hoursWorked - number of hours this member worked last week
     */
    public Member(String name, Group subgroup, int hoursWorked) {
        this.name = name;
        this.subgroup = subgroup;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Copy constructor for a Member instance.
     *
     * @param other - the object to copy from
     */
    public Member(Member other) {
        this.name = other.name;
        this.subgroup = other.subgroup;
        this.hoursWorked = other.hoursWorked;
    }

    @Override
    public String toString() {
        return String.format("%s worked for %d hours in the %s subgroup last week.", name, hoursWorked, subgroup);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !other.getClass().equals(this.getClass())) {
            return false;
        }

        Member otherMember = (Member) other;
        return this.name.equals(otherMember.name)
                && this.subgroup == otherMember.subgroup
                && this.hoursWorked == otherMember.hoursWorked;
    }

    @Override
    public int compareTo(Member otherMember) {
        if (otherMember == null) {
            throw new NullPointerException("The specified object is null.");
        }

        if (!this.name.equals(otherMember.name)) {
            return this.name.compareTo(otherMember.name);
        }
        if (this.subgroup != otherMember.subgroup) {
            return this.subgroup.compareTo(otherMember.subgroup);
        }
        return this.hoursWorked - otherMember.hoursWorked;
    }

    @Override
    public int hashCode() {
        //Just to eliminate Checkstyle error, you should not need to use this.
        int result = 17;
        result = 11 * result + name.hashCode();
        result = 13 * result + subgroup.hashCode();
        result = 23 * result + hoursWorked;
        return result;
    }

    /**
     * Getter method for name.
     *
     * @return name of the Member
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for subgroup.
     *
     * @return subgroup of the Member
     */
    public Group getSubgroup() {
        return subgroup;
    }

    /**
     * Getter method for hoursWorked.
     *
     * @return subgroup of the Member
     */
    public int getHoursWork() {
        return hoursWorked;
    }
    public static void insertionSort(int[] values) {
        for(int i = 1; i < values.length; i++) {
            int currentValue = values[i];
            int j = i - 1;
        while(j>=0 && values[j] > currentValue) {
            values[j+1] = values[j];
            j--;
        }
        values[j + 1] = currentValue;
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[] {10, 7, 4, 2, 9, 5, 1, 8, 9, 0};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        insertionSort(arr);
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}