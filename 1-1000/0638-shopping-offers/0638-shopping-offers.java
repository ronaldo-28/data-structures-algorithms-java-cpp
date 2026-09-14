class Solution {
    int minPrice;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        minPrice=directlyBuy(price,needs);
        help(price,special,needs,0,0);
        return minPrice;
    }
    private int directlyBuy(List<Integer> price,List<Integer> needs){
        int total=0;
        int n=needs.size();
        for(int i=0;i<n;i++){
            total+=price.get(i)*needs.get(i);
        }
        return total;
    }
    private boolean canUse(List<Integer> offer,List<Integer> needs){
        int n=needs.size();
        for(int i=0;i<n;i++){
            if(offer.get(i)>needs.get(i))return false;
        }
        return true;
    }
    private void help(List<Integer> price,List<List<Integer>> special,List<Integer> needs,int used,int index){
        if(used>=minPrice)return;
        if(index==special.size()){
            used+=directlyBuy(price,needs);
            if(used<minPrice){
                minPrice=used;
            }
            return;
        }
        List<Integer> offer=special.get(index);
        if(canUse(offer,needs)){
            int n=needs.size();
            List<Integer> updatedNeeds=new ArrayList<>();
            for(int i=0;i<n;i++){
                updatedNeeds.add(needs.get(i)-offer.get(i));
            }
            help(price,special,updatedNeeds,used+offer.get(n),index);
        }
        help(price,special,needs,used,index+1);
    }
}