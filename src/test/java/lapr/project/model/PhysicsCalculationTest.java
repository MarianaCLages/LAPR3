package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PhysicsCalculationTest {
    LinkedHashMap<Integer, Double> map;


    @BeforeEach
    public void setUp() {
        map = new LinkedHashMap<>();

        System.setProperty("thermalConductivity.inner.minus5", "0.033");
        System.setProperty("thermalConductivity.mid.minus5", "25");
        System.setProperty("thermalConductivity.outer.minus5", "0.13");
        System.setProperty("thermalConductivity.outer.7", "15");
        System.setProperty("thermalConductivity.mid.7", "0.028");
        System.setProperty("thermalConductivity.inner.7", "0.13");

    }

    @Test
    void Minus5ZeroTest() {

        try {
            assertEquals(0, PhysicsCalculation.calculateEnergyConsumptionMinus5(map, 0, 0, 0));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void SevenZeroTest() {
        try {
            assertEquals(0, PhysicsCalculation.calculateEnergyConsumption7(map, 0, 0, 0));
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    void SevenTest2Section() {

        map.put(7200, 20.0);
        map.put(3600, 30.0);
        try {
            assertEquals(2.3352179108136587E7, PhysicsCalculation.calculateEnergyConsumption7(map, 5, 4, 5));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void SevenMinus5Section() {

        map.put(7200, 20.0);
        map.put(3600, 30.0);
        try {
            assertEquals(859132.8908588162, PhysicsCalculation.calculateEnergyConsumptionMinus5(map, 5, 4, 5));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void calculateEnergyConsumptionDeterminedTripMinusFive() {

        try {
            double value = PhysicsCalculation.calculateEnergyConsumptionDeterminedTripMinus5C();
            assertEquals(13433.827436138068, value);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void calculateEnergyConsumptionDeterminedTripSeven() {

        try {
            double value = PhysicsCalculation.calculateEnergyConsumptionDeterminedTrip7C();
            assertEquals(85126.65028210469, value);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void calculateSuppliesNeededFor7(){
        try {
            double value = PhysicsCalculation.calculateSuppliesNeededFor7(2,20,9000);
            assertEquals(2.0, value);
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    void calculateSuppliesNeededForMinus5(){
        try {
            double value = PhysicsCalculation.calculateSuppliesNeededForMinus5(2,20,9000);
            assertEquals(1.0, value);
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    void calculateSuppliesNeededForMinus5Ex(){
        try {
            double value = PhysicsCalculation.calculateSuppliesNeededForMinus5(2,0,0);
            assertEquals(1.0, value);
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    void calculateSuppliesNeededFor7Ex(){
        try {
            double value = PhysicsCalculation.calculateSuppliesNeededFor7(2,0,0);
            assertEquals(1.0, value);
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    void calculateCenterMassOneDimZero() {
        ArrayList<Double> list = new ArrayList<>();

        assertEquals(0, PhysicsCalculation.calculateCenterMassOneDim(list));
    }


    @Test
    void calculateCenterMassOneDimSomeValues() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(11.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);
        list.add(13.0);

        assertEquals(11.9, PhysicsCalculation.calculateCenterMassOneDim(list));
    }


    @Test
    void calculateCenterMassOneDimHugeValues() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(67.0);
        list.add(69.0);
        list.add(69.0);
        list.add(71.0);
        list.add(71.0);
        list.add(73.0);
        list.add(73.0);
        list.add(75.0);
        list.add(75.0);
        list.add(77.0);
        list.add(77.0);
        list.add(79.0);
        list.add(79.0);
        list.add(81.0);
        list.add(81.0);
        list.add(83.0);
        list.add(83.0);
        list.add(85.0);
        list.add(85.0);
        list.add(87.0);

        assertEquals(77, PhysicsCalculation.calculateCenterMassOneDim(list));
    }

}
