package InventoryAllocator;

import java.util.HashMap;
import java.util.Map;

// This class represents a warehouse which has a name and an inventory.
public class WareHouse implements Comparable<WareHouse> {
    public String name;
    public Map<String, Integer> inventory;

    public WareHouse(String name) {
        this.name = name;
        inventory = new HashMap<>();
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        }

        if(obj == null || !(obj instanceof WareHouse)) {
            return false;
        }

        WareHouse wareHouse = (WareHouse) obj;

        return wareHouse.name.equals(this.name)  && wareHouse.inventory.equals(this.inventory);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.inventory.hashCode();
    }

    @Override
    public int compareTo(WareHouse other) {
        return this.name.compareTo(other.name);
    }
}
