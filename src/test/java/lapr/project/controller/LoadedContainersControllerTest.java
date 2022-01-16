package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoadedContainersControllerTest {

    @Test
    void getLoadContainers() {

        LoadedContainersController loadedContainersController = new LoadedContainersController();


        try {

            String actual = loadedContainersController.getLoadContainers(303296000, "1");



        } catch (SQLException e) {

        }


    }
}