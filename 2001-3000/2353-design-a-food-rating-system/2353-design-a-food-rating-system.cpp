class FoodRatings {
private:
    // Custom comparator for the set to sort by rating (descending) 
    // and then by food name (ascending/lexicographically smaller).
    struct Compare {
        bool operator()(const pair<int, string>& a, const pair<int, string>& b) const {
            if (a.first != b.first) {
                return a.first > b.first; // Higher rating comes first
            }
            return a.second < b.second;   // Alphabetically smaller name comes first
        }
    };

    // Maps food name -> pair(rating, cuisine)
    unordered_map<string, pair<int, string>> foodMap;
    
    // Maps cuisine -> sorted set of pair(rating, food name)
    unordered_map<string, set<pair<int, string>, Compare>> cuisineMap;

public:
    FoodRatings(vector<string>& foods, vector<string>& cuisines, vector<int>& ratings) {
        int n = foods.size();
        for (int i = 0; i < n; ++i) {
            foodMap[foods[i]] = {ratings[i], cuisines[i]};
            cuisineMap[cuisines[i]].insert({ratings[i], foods[i]});
        }
    }
    
    void changeRating(string food, int newRating) {
        // Get the cuisine type for this food
        string cuisine = foodMap[food].second;
        
        // Update the master rating in our map
        foodMap[food].first = newRating;
        
        // Insert the newly updated pair into the cuisine's sorted set
        cuisineMap[cuisine].insert({newRating, food});
    }
    
    string highestRated(string cuisine) {
        // Look at the top element of the set for this cuisine
        auto& highestSet = cuisineMap[cuisine];
        
        // Clean up any outdated "ghost" ratings at the top of the set
        while (!highestSet.empty()) {
            auto topElement = *(highestSet.begin());
            string foodName = topElement.second;
            int setRating = topElement.first;
            
            // If the rating in the set matches the actual current rating, it's valid!
            if (foodMap[foodName].first == setRating) {
                return foodName;
            }
            
            // Otherwise, it's an old rating. Delete it and keep checking.
            highestSet.erase(highestSet.begin());
        }
        
        return "";
    }
};

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings* obj = new FoodRatings(foods, cuisines, ratings);
 * obj->changeRating(food,newRating);
 * string param_2 = obj->highestRated(cuisine);
 */