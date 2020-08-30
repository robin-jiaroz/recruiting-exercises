package InventoryAllocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Solution for the cheapest shipment problem.
public class CheapestShipment {

    public List<WareHouse> cheapestShipment(Map<String, Integer> order, List<WareHouse> wareHouseList) {
        List<WareHouse> output = new ArrayList<>();
        if (order == null || order.size() == 0) {
            return output;
        }
        if (wareHouseList == null || wareHouseList.size() == 0) {
            return output;
        }

        // create a copy of input data in order not to modify input data
        Map<String, Integer> orderCopy = new HashMap<>(order);

        // iterate through the given warehouse list
        for (WareHouse wareHouse : wareHouseList) {
            WareHouse curShipment = new WareHouse(wareHouse.name);
            // go through the inventory of the current warehouse to check if it contains any item in the given order
            for (Map.Entry<String, Integer> entry : wareHouse.inventory.entrySet()) {
                if (orderCopy.containsKey(entry.getKey())) {
                    int count = orderCopy.get(entry.getKey());
                    if (entry.getValue() >= count) {
                        curShipment.inventory.put(entry.getKey(), count);
                        orderCopy.remove(entry.getKey());
                    } else {
                        curShipment.inventory.put(entry.getKey(), entry.getValue());
                        orderCopy.put(entry.getKey(), count - entry.getValue());
                    }
                }
            }
            if (curShipment.inventory.size() > 0) {
                output.add(curShipment);
            }
        }

        // if there are still some ordered items left in the orderCopy, the given order couldn't be shipped
        if (orderCopy.size() > 0) {
            output.clear();
        }
        return output;
    }

}
