class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        
        int steps = 0, temp = capacity;
        for(int i = 0 ; i < plants.length ; i++){
            if(plants[i] > capacity ){
                steps += 2*i;
                capacity = temp - plants[i];
            }else{
                capacity -= plants[i];
            }

            steps += 1;
        }

        return steps;
    }
}