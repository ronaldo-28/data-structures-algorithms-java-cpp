enum class OrderType
{
    BUY = 0,
    SELL,
    NONE
};

class OrderManagementSystem {
public:
    OrderManagementSystem()
    {    
    }
    
    void addOrder(int orderId, string orderType, int price) 
    {
        auto type = GetEnumOptionByString(orderType);
        
        switch(type)
        {
            case OrderType::BUY:
                priceListForBuy[price].push_front(orderId);
                orders[orderId] = {orderId, price, type, priceListForBuy[price].begin()};
                break;
            case OrderType::SELL:
                priceListForSell[price].push_front(orderId);
                orders[orderId] = {orderId, price, type, priceListForSell[price].begin()};
                break;
            case OrderType::NONE:
                assert(false);
                break;
        }
    }
    
    void modifyOrder(int orderID, int newPrice) {
        auto& orderInfo = orders[orderID];

        switch(orderInfo.orderType)
        {
            case OrderType::BUY:
                priceListForBuy[orderInfo.price].erase(orderInfo.it);
                priceListForBuy[newPrice].push_front(orderID);
                orderInfo.it = priceListForBuy[newPrice].begin();
                break;
            case OrderType::SELL:
                priceListForSell[orderInfo.price].erase(orderInfo.it);
                priceListForSell[newPrice].push_front(orderID);
                orderInfo.it = priceListForSell[newPrice].begin();
                break;
            case OrderType::NONE:
                assert(false);
                break;
        }
        
        orderInfo.price = newPrice;
    }
    
    void cancelOrder(int orderId) {
        auto& orderInfo = orders[orderId];
        switch(orderInfo.orderType)
        {
            case OrderType::BUY:
                priceListForBuy[orderInfo.price].erase(orderInfo.it);
                break;
            case OrderType::SELL:
                priceListForSell[orderInfo.price].erase(orderInfo.it);
                break;
            case OrderType::NONE:
                assert(false);
        }

        orders.erase(orderId);
    }
    
    vector<int> getOrdersAtPrice(string orderType, int price) 
    {
        vector<int> res;
        auto type = GetEnumOptionByString(orderType);
        switch(type)
        {
            case OrderType::BUY:
                res.assign(priceListForBuy[price].begin(), priceListForBuy[price].end());
                break;
            case OrderType::SELL:
                res.assign(priceListForSell[price].begin(), priceListForSell[price].end());
                break;
            case OrderType::NONE:
                assert(false);
                break;
        }
        return res;
    }

    OrderType GetEnumOptionByString(const std::string& optionStr)
    {
        if(optionStr == "buy")
        {
            return OrderType::BUY;
        }
        else if(optionStr == "sell")
        {
            return OrderType::SELL;
        }

        // shouldn't be reached
        //
        assert(false);
        return OrderType::NONE;
    }

private:

    struct OrderInfo
    {
        // OrderInfo() = delete;
        // OrderInfo(int order_id, int price_, OrderType order_type) 
        // : 
        // orderID(order_id), 
        // price(price_),
        // orderType(order_type)
        // {
        // }

        int orderID = 0;
        int price = 0;
        OrderType orderType = OrderType::NONE;
        list<int>::iterator it;
    };

    unordered_map<int, list<int>> priceListForBuy;
    unordered_map<int, list<int>> priceListForSell;
    unordered_map<int, OrderInfo> orders;
};

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * OrderManagementSystem* obj = new OrderManagementSystem();
 * obj->addOrder(orderId,orderType,price);
 * obj->modifyOrder(orderId,newPrice);
 * obj->cancelOrder(orderId);
 * vector<int> param_4 = obj->getOrdersAtPrice(orderType,price);
 */