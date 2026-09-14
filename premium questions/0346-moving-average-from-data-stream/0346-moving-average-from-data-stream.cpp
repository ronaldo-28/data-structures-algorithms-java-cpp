class MovingAverage {
public:
    MovingAverage(int size):
        _size{size},
        _windowSum{0},
        _window{}
    {
    }
    
    double next(int val) {
        if(_window.size() == _size) {
            _windowSum -= _window.front();
            _window.pop();
        }
        _windowSum += val;
        _window.push(val);
        return _windowSum / static_cast<double>(_window.size());
    }
private:
    const int _size;
    int _windowSum;
    std::queue<int> _window;
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage* obj = new MovingAverage(size);
 * double param_1 = obj->next(val);
 */