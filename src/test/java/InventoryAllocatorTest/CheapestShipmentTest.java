package InventoryAllocatorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import InventoryAllocator.CheapestShipment;
import InventoryAllocator.WareHouse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class CheapestShipmentTest {
    private static CheapestShipment obj;

    @BeforeClass
    public static void initCheapestShipment() {
        obj = new CheapestShipment();
    }

    @Test
    public void testNullGivenOrder() {
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse = new WareHouse("owd");
        wareHouse.inventory.put("apple", 1);
        wareHouseList.add(wareHouse);
        List<WareHouse> output = obj.cheapestShipment(null, wareHouseList);
        assertTrue(output.isEmpty());
    }

    @Test
    public void testEmptyGivenOrder() {
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse = new WareHouse("owd");
        wareHouse.inventory.put("apple", 1);
        wareHouseList.add(wareHouse);
        List<WareHouse> output = obj.cheapestShipment(new HashMap<String, Integer>(), wareHouseList);
        assertTrue(output.isEmpty());
    }

    @Test
    public void testNullGivenWarehouseList() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 5);
        List<WareHouse> output = obj.cheapestShipment(order, null);
        assertTrue(output.isEmpty());
    }

    @Test
    public void testEmptyGivenWarehouseList() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 5);
        List<WareHouse> output = obj.cheapestShipment(new HashMap<String, Integer>(), new ArrayList<WareHouse>());
        assertTrue(output.isEmpty());
    }

    @Test
    public void testOderCanBeShippedUsingOneWarehouse() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 1);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse = new WareHouse("owd");
        wareHouse.inventory.put("apple", 1);
        wareHouseList.add(wareHouse);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        wareHouse = new WareHouse("owd");
        wareHouse.inventory.put("apple", 1);
        expectedOutput.add(wareHouse);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testOrderCanBeShippedUsingMultipleWarehouses1() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 5);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 5);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse3 = new WareHouse("owd");
        wareHouse3.inventory.put("apple", 5);
        WareHouse wareHouse4 = new WareHouse("dm");
        wareHouse4.inventory.put("apple", 5);
        expectedOutput.add(wareHouse4);
        expectedOutput.add(wareHouse3);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testOrderCanBeShippedUsingMultipleWarehouses2() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 3);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 10);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse3 = new WareHouse("owd");
        wareHouse3.inventory.put("apple", 3);
        WareHouse wareHouse4 = new WareHouse("dm");
        wareHouse4.inventory.put("apple", 7);
        expectedOutput.add(wareHouse4);
        expectedOutput.add(wareHouse3);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testOrderCanBeShippedUsingMultipleWarehouses3() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 15);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 3);
        wareHouse1.inventory.put("banana", 10);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 10);
        wareHouse2.inventory.put("banana", 20);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse3 = new WareHouse("owd");
        wareHouse3.inventory.put("apple", 3);
        wareHouse3.inventory.put("banana", 10);
        WareHouse wareHouse4 = new WareHouse("dm");
        wareHouse4.inventory.put("apple", 7);
        wareHouse4.inventory.put("banana", 5);
        expectedOutput.add(wareHouse4);
        expectedOutput.add(wareHouse3);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testShipmentIsCheapest1() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 10);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 10);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse3 = new WareHouse("owd");
        wareHouse3.inventory.put("apple", 10);
        expectedOutput.add(wareHouse3);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testShipmentIsCheapest2() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 15);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 1);
        wareHouse1.inventory.put("peach", 15);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 5);
        wareHouse2.inventory.put("banana", 17);
        WareHouse wareHouse3 = new WareHouse("st");
        wareHouse3.inventory.put("apple", 10);
        wareHouse3.inventory.put("banana", 15);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        wareHouseList.add(wareHouse3);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse4 = new WareHouse("owd");
        wareHouse4.inventory.put("apple", 1);
        WareHouse wareHouse5 = new WareHouse("dm");
        wareHouse5.inventory.put("apple", 5);
        wareHouse5.inventory.put("banana", 15);
        WareHouse wareHouse6 = new WareHouse("st");
        wareHouse6.inventory.put("apple", 4);
        expectedOutput.add(wareHouse4);
        expectedOutput.add(wareHouse5);
        expectedOutput.add(wareHouse6);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testShipmentIsCheapest3() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 5);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 2);
        wareHouse1.inventory.put("banana", 3);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 10);
        wareHouse2.inventory.put("banana", 5);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse3 = new WareHouse("owd");
        wareHouse3.inventory.put("apple", 2);
        wareHouse3.inventory.put("banana", 3);
        WareHouse wareHouse4 = new WareHouse("dm");
        wareHouse4.inventory.put("apple", 8);
        wareHouse4.inventory.put("banana", 2);
        expectedOutput.add(wareHouse3);
        expectedOutput.add(wareHouse4);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testShipmentIsCheapest4() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 5);
        order.put("peach", 8);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 2);
        wareHouse1.inventory.put("peach", 3);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 5);
        wareHouse2.inventory.put("banana", 5);
        WareHouse wareHouse3 = new WareHouse("st");
        wareHouse3.inventory.put("apple", 5);
        wareHouse3.inventory.put("banana", 5);
        wareHouse3.inventory.put("peach", 8);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        wareHouseList.add(wareHouse3);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse4 = new WareHouse("owd");
        wareHouse4.inventory.put("apple", 2);
        wareHouse4.inventory.put("peach", 3);
        WareHouse wareHouse5 = new WareHouse("dm");
        wareHouse5.inventory.put("apple", 5);
        wareHouse5.inventory.put("banana", 5);
        WareHouse wareHouse6 = new WareHouse("st");
        wareHouse6.inventory.put("apple", 3);
        wareHouse6.inventory.put("peach", 5);
        expectedOutput.add(wareHouse4);
        expectedOutput.add(wareHouse5);
        expectedOutput.add(wareHouse6);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testShipmentIsCheapest5() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 5);
        order.put("peach", 8);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 2);
        wareHouse1.inventory.put("peach", 3);
        wareHouse1.inventory.put("lemon", 3);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 5);
        wareHouse2.inventory.put("banana", 5);
        wareHouse2.inventory.put("grape", 5);
        WareHouse wareHouse3 = new WareHouse("st");
        wareHouse3.inventory.put("apple", 5);
        wareHouse3.inventory.put("banana", 5);
        wareHouse3.inventory.put("peach", 8);
        wareHouse3.inventory.put("watermelon", 8);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        wareHouseList.add(wareHouse3);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        List<WareHouse> expectedOutput = new ArrayList<>();
        WareHouse wareHouse4 = new WareHouse("owd");
        wareHouse4.inventory.put("apple", 2);
        wareHouse4.inventory.put("peach", 3);
        WareHouse wareHouse5 = new WareHouse("dm");
        wareHouse5.inventory.put("apple", 5);
        wareHouse5.inventory.put("banana", 5);
        WareHouse wareHouse6 = new WareHouse("st");
        wareHouse6.inventory.put("apple", 3);
        wareHouse6.inventory.put("peach", 5);
        expectedOutput.add(wareHouse4);
        expectedOutput.add(wareHouse5);
        expectedOutput.add(wareHouse6);
        Collections.sort(output);
        Collections.sort(expectedOutput);
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testOrderCannotBeShippedWithoutEnoughInventory1() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 1);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 0);
        wareHouseList.add(wareHouse1);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        assertTrue(output.isEmpty());
    }

    @Test
    public void testOrderCannotBeShippedWithoutEnoughInventory2() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 4);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 5);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        assertTrue(output.isEmpty());
    }

    @Test
    public void testOrderCannotBeShippedWithoutEnoughInventory3() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 10);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 4);
        wareHouse1.inventory.put("banana", 5);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 5);
        wareHouse2.inventory.put("banana", 5);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        assertTrue(output.isEmpty());
    }

    @Test
    public void testOrderCannotBeShippedWithoutEnoughInventory4() {
        Map<String, Integer> order = new HashMap<>();
        order.put("apple", 10);
        order.put("banana", 10);
        order.put("peach", 10);
        List<WareHouse> wareHouseList = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse("owd");
        wareHouse1.inventory.put("apple", 3);
        wareHouse1.inventory.put("banana", 5);
        wareHouse1.inventory.put("peach", 7);
        WareHouse wareHouse2 = new WareHouse("dm");
        wareHouse2.inventory.put("apple", 4);
        wareHouse2.inventory.put("banana", 5);
        WareHouse wareHouse3 = new WareHouse("st");
        wareHouse3.inventory.put("apple", 2);
        wareHouse3.inventory.put("banana", 5);
        wareHouse3.inventory.put("peach", 5);
        wareHouseList.add(wareHouse1);
        wareHouseList.add(wareHouse2);
        wareHouseList.add(wareHouse3);
        List<WareHouse> output = obj.cheapestShipment(order, wareHouseList);
        assertTrue(output.isEmpty());
    }
}
