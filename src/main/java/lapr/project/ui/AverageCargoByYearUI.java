package lapr.project.ui;


import lapr.project.controller.AverageCargoByYearController;

import javax.annotation.processing.SupportedSourceVersion;

public class AverageCargoByYearUI implements Runnable{


    @Override
    public void run() {
        AverageCargoByYearController averageCargoByYearController = new AverageCargoByYearController();
        int mmsi ;
        int year;

        do {
            try {
                mmsi = Utils.readIntegerFromConsole("Ship MMSI?");
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid MMSI!");
                mmsi = 0;
            }
        }while(mmsi == 0);

        System.out.println();

        do{
            try{
                year = Utils.readIntegerFromConsole("Year?");
            }catch (Exception ex){
                System.out.println("Please enter a valid year!");
                year = 0;
            }

        }while(year == 0);

        try{

            String str = averageCargoByYearController.AverageCargoByYearController(mmsi,year);

            if(str == null){
                System.out.println("Operation Failed!");
            }
            else {
                System.out.println(str);
            }

            }
        catch(NullPointerException exception){
            System.out.println("The ship introduced doesn't exist.");
        }
    }
}
