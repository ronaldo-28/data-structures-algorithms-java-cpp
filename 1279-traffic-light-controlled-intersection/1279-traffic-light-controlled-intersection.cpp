class TrafficLight {
public:
    int current_road_id = 1;
    std::mutex mtx;
    TrafficLight() {}

    void carArrived(
        int carId,                   // ID of the car
        int roadId,                  // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        int direction,               // Direction of the car
        function<void()> turnGreen,  // Use turnGreen() to turn light to green on current road
        function<void()> crossCar    // Use crossCar() to make car cross the intersection
    ) {
	    std::lock_guard<std::mutex> lck (mtx);
        if(roadId != current_road_id)
        {
            current_road_id = roadId;
            turnGreen();
        }
        crossCar();
    }
};