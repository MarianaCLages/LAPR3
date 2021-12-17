package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class WarehouseTest {

    @Test
    void warehouseTest() {
        try {
            Country c1 = new Country("United Kingdom", "UK".toCharArray(), "UNK".toCharArray(), 25, Continent.EUROPE);

            Warehouse warehouse = new Warehouse("29002", "Liverpool", "Europe", c1, new FacilityLocation(53.46666667, -3.033333333), 0);
        } catch (Exception e) {
            fail();
        }
    }
}