package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Position;
import lapr.project.model.Ship;
import lapr.project.model.stores.ShipStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionalMessageControllerTest {

    PositionalMessageController positionalMessageController = new PositionalMessageController();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String sdate = "31-12-2020 23:16";
    LocalDateTime date = LocalDateTime.parse(sdate, formatter);

    String sdate2 = "31-12-2020 23:56";
    LocalDateTime date2 = LocalDateTime.parse(sdate2, formatter);

    Ship shipgeral = new Ship(111111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);
    Position posgeral = new Position(1, 0, 0, 1, 1, date);

    Ship shipgeral2 = new Ship(121111111, "name", "IMO1111111", 1, 1, "A", "A", 1, 1, 1, 1);

    @Test
    void getPositionalMessages() {

        //Arrange
        shipgeral.insertPosition(posgeral);
        positionalMessageController.getShipStore().addShip(shipgeral);
        positionalMessageController.getShipStore().addShip(shipgeral2);
        //Act
        //Assert
        assertEquals(false,positionalMessageController.getPositionalMessages(123456789,date,date2));
        assertEquals(true,positionalMessageController.getPositionalMessages(111111111,date,date2));
        assertEquals(false,positionalMessageController.getPositionalMessages(121111111,date,date2));
        try{
            boolean b = positionalMessageController.getPositionalMessages(111111111,null,null);
        }catch (NullPointerException ex){
            assertEquals(false,ex.getMessage());
        }

        try{
            boolean b = positionalMessageController.getPositionalMessages(111111111,date,date2);
        }catch (RuntimeException e){
            assertEquals(false,e.getMessage());
        }
    }



    @Test
    void getShipStore(){

        //Arrange
        Company cmp = App.getInstance().getCompany();
        ShipStore ss = cmp.getShipStore();
        //Act + Assert
        assertEquals(ss,positionalMessageController.getShipStore());
    }
}
