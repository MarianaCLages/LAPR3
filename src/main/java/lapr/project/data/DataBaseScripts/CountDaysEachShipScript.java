package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CountDaysEachShipScript {


    public CountDaysEachShipScript(){
        //Empty Constructor
    }

    public String CountDaysEachShip(DatabaseConnection connection,int year){

      String string = new String();
      String sqlString = "{? = call fnCountDays(?)}";
      StringBuilder sb = new StringBuilder();

      try(CallableStatement cstmt = connection.getConnection().prepareCall(sqlString)){

          cstmt.registerOutParameter(1, Types.VARCHAR);
          cstmt.setInt(2,year); //year

          cstmt.executeUpdate();

          string = cstmt.getString(1);
          String[] split = string.split(",");


          for (int i = 1; i < split.length; i++) {
              sb.append(split[i]).append("\n");
          }

      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }


    return sb.toString();
    }
}
