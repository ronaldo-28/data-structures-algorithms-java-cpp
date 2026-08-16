class FoodRatings {

    public record FoodRating(String food, String cuisine, int rating) {}

    Map<String, FoodRating> foodRatings = new HashMap<>(); // food -> FoodRating
    Map<String, PriorityQueue<FoodRating>> foodsByCuisine = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for(int i=0; i<foods.length; i++) {
            add(new FoodRating(foods[i], cuisines[i], ratings[i]));
        }
    }

    void add(FoodRating f) {
        foodRatings.put(f.food(), f);
        foodsByCuisine.computeIfAbsent(f.cuisine(), k -> new PriorityQueue<>((a,b) -> {
            if (a.rating() != b.rating()) return b.rating() - a.rating(); // highest rating first
            return a.food().compareTo(b.food()); // If there is a tie, return the item with the lexicographically smaller name.
        })).add(f);
    }
    
    public void changeRating(String food, int newRating) {
        FoodRating f = foodRatings.get(food);
        if (f != null) {
            add(new FoodRating(food, f.cuisine(), newRating));
        }
    }
    
    public String highestRated(String cuisine) {
        PriorityQueue<FoodRating> pq = foodsByCuisine.get(cuisine);
        if (pq != null) {
            while(!pq.isEmpty()) {
                FoodRating f = pq.peek();
                if (foodRatings.get(f.food()) == f) return f.food();
                pq.poll();
            }
            // cleanup empty q -- this should not happen
            foodsByCuisine.remove(cuisine);
        }
        return "";
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */