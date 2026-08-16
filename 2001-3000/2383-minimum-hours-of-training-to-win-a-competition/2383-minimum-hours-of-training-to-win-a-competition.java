/*class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        
    }
}*/
//seen//lgtm



class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience,
                                int[] energy, int[] experience) {

        int hours = 0;

        // ----- Energy -----
        int totalEnergy = 0;
        for (int e : energy) {
            totalEnergy += e;
        }

        int requiredEnergy = totalEnergy + 1;

        if (initialEnergy < requiredEnergy) {
            hours += requiredEnergy - initialEnergy;
        }

        // ----- Experience -----
        int currExp = initialExperience;

        for (int exp : experience) {

            if (currExp <= exp) {

                int need = exp + 1 - currExp;

                hours += need;

                currExp += need;     // training increases experience
            }

            currExp += exp;          // gain experience after victory
        }

        return hours;
    }
}//better