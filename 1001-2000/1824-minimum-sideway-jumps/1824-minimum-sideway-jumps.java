class Solution {
    public int minSideJumps(int[] obstacles) {
        // jums array []
        // obstacle loop -> 1st obstacle. Can go lane 1 or 3. Frog take other lane on which ever comes 1st. 
        // Again same thing with other 2 number.
        // int[] candidateLanes = new int[4];
        int jumpsSoFar = 0;
        //only need to go till n (length is n+1)
        int possibleLane1=0;
        int possibleLane2=0;
        boolean changedLaneAtleastOnce = false;
        for(int i=1;i<obstacles.length;i++) {
            // System.out.println("~("+i+","+obstacles[i]+"), jumps: " + jumpsSoFar);
            if(!changedLaneAtleastOnce) {
                // System.out.print("("+i+","+obstacles[i]+")");
                if(obstacles[i]==2) {
                    changedLaneAtleastOnce=true;
                    jumpsSoFar++;
                    if(obstacles[i-1]==1){
                        possibleLane1=3;
                    } else if(obstacles[i-1]==3){
                        possibleLane1=1;
                    } else {
                        possibleLane1=1;
                        possibleLane2=3;
                    }
                }
            } else if(possibleLane1!=0 && possibleLane2!=0){
                // System.out.println("====("+i+","+obstacles[i]+")");
                // System.out.println();
                // System.out.println("obstacle:"+obstacles[i]+", poss1:"+possibleLane1 + ", poss2:"+possibleLane2 + ", jump:"+ jumpsSoFar);
                if(obstacles[i]==possibleLane1) {
                    possibleLane1=0;

                } else if(obstacles[i]==possibleLane2){
                    possibleLane2=0;
                }
                // System.out.println(" obstacle:"+obstacles[i]+", poss1:"+possibleLane1 + ", poss2:"+possibleLane2);
            } else if(possibleLane1!=0){
                if(obstacles[i]==possibleLane1) {
                    int[] lanes = getPossibleLane(obstacles, i);
                    possibleLane1 = lanes[0];
                    possibleLane2 = lanes[1];

                    // if(obstacles[i]==1){
                    //     possibleLane1=2;
                    //     possibleLane2=3;
                    // } else if(obstacles[i]==2){
                    //     possibleLane1=1;
                    //     possibleLane2=3;
                    // } else if(obstacles[i]==3){
                    //     possibleLane1=1;
                    //     possibleLane2=2;
                    // }
                    jumpsSoFar++;
                }
            } else {
                if(obstacles[i]==possibleLane2) {
                    int[] lanes = getPossibleLane(obstacles, i);
                    possibleLane1 = lanes[0];
                    possibleLane2 = lanes[1];

                    // if(obstacles[i]==1){
                    //     possibleLane1=2;
                    //     possibleLane2=3;
                    // } else if(obstacles[i]==2){
                    //     possibleLane1=1;
                    //     possibleLane2=3;
                    // } else if(obstacles[i]==3){
                    //     possibleLane1=1;
                    //     possibleLane2=2;
                    // }
                    jumpsSoFar++;
                }
            }
        }
        return jumpsSoFar;
    }
    private int[] getPossibleLane(int[] obstacles, int i){
    // private int getPossibleLane(int[] obstacles, int i, int lane){
        int[] result = new int[2];
            // if(obstacles[i-1]==1){
            //     possibleLane1=3;
            // } else if(obstacles[i-1]==3){
            //     possibleLane1=1;
            // } else {
            //     possibleLane1=1;
            //     possibleLane2=3;
            // }
        if(obstacles[i]==1){
            if(obstacles[i-1]==2){
                result[0]=0;
                result[1]=3;
            } else if(obstacles[i-1]==3){
                result[0]=2;
                result[1]=0;
            } else {
                result[0]=2;
                result[1]=3;
            }
        } else if(obstacles[i]==2){
            if(obstacles[i-1]==1){
                result[0]=0;
                result[1]=3;
            } else if(obstacles[i-1]==3){
                result[0]=1;
                result[1]=0;
            } else {
                result[0]=1;
                result[1]=3;
            }
        } else if(obstacles[i]==3){
            if(obstacles[i-1]==1){
                result[0]=2;
                result[1]=0;
            } else if(obstacles[i-1]==2){
                result[0]=1;
                result[1]=0;
            } else {
                result[0]=1;
                result[1]=2;
            }
        }
        return result;
    }
}