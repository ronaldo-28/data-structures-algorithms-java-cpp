class Solution {
    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;
        int twenty = 0;
        for(int i = 0; i < bills.length && i < 1000; i++) {
            if(bills[i] == 5) {
                five++;
            } else if(bills[i] == 10) {
                ten++;
                if(five == 0) {
                    return false;
                } else {
                    five--;
                }
            } else {
                twenty++;
                if(ten == 0) {
                    if (five < 3) {
                        return false;
                    } else {
                        five -= 3;
                    }
                } else if(ten == 1) {
                    if(five < 1) {
                        return false;
                    } else {
                        ten--;
                        five--;
                     }
                } else {
                    if(five < 1) {
                        return false;
                    } else {
                        ten--;
                        five--;
                    }
                }
            }
        }
        return true;
        
    }
}