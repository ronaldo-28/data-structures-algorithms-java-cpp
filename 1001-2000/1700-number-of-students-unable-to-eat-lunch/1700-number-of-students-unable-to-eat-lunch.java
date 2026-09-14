class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        // Array to count the number of students who prefer each type of sandwich
        // cnt[0] for students who prefer sandwich type 0, and cnt[1] for sandwich type 1
        int[] count = new int[2];
      
        // Count the number of students preferring each type of sandwich
        for (int student : students) {
            count[student]++;
        }
      
        // Iterate through the sandwiches
        for (int sandwich : sandwiches) {
            // If no students want the current type of sandwich, break and return the number of students
            // who want the other type of sandwich
            if (count[sandwich] == 0) {
                // The other type is obtained by XORing the current type with 1
                // If current is 0, 0^1 will be 1 (the other type)
                // If current is 1, 1^1 will be 0 (the other type)
                return count[sandwich ^ 1];
            }
            // Decrement the count as one student takes a sandwich of the type they prefer
            count[sandwich]--;
        }
      
        // If all students got their preferred sandwiches, return 0
        return 0;
    }
}