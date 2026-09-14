class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {

        int drink =numBottles;
        while( numBottles>= numExchange){

            int newBottles= (numBottles/ numExchange);
            int remBottles=(numBottles % numExchange);


              drink = drink + newBottles;
              numBottles = newBottles +  remBottles;


        }

        return drink;
        
    }
}