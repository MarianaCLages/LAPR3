package lapr.project.data.DataBaseScripts;

import lapr.project.data.DatabaseConnection;
import lapr.project.shared.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class GetMaterialsScript {

    private DatabaseConnection databaseConnection = null;

    public GetMaterialsScript(){}


    public String getThermalResistance(int containerID, DatabaseConnection databaseConnection) throws SQLException {

        this.databaseConnection = databaseConnection;
        StringBuilder stringBuilder = new StringBuilder();
        String [] walls  = {"Outer Wall","Inner Wall","Intermediate Material"};
        DecimalFormat df = new DecimalFormat("#.#######");

        for(int i = 0 ; i < walls.length ; i++){
            stringBuilder.append(walls[i] +":\n");
            stringBuilder.append(df.format(Constants.GROSS / (getKConstant(containerID,walls[i]) * Constants.AREA)) + " W/(m·K)\n");
        }




        return stringBuilder.toString();
    }

    public String materialScript(int temperature,DatabaseConnection databaseConnection) throws SQLException {

        this.databaseConnection = databaseConnection;
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("Outer Walls for " +temperature + "ºC :\n");

        int k = countMaterialsByTemperature(temperature);
        int count2 = 0;

        if(temperature == 7){
            while (k != 0){

                if(count2 == 3) stringBuilder.append("\nIntermediate Material for "+temperature+ "ºC :\n");
                else if(count2 == 7)stringBuilder.append("\nInner Walls Material for "+temperature+ "ºC :\n");
                stringBuilder.append(getMaterialByTemperature(temperature,count2));
                stringBuilder.append("\n");
                count2++;
                k--;

            }}

        else{
            while( k != 0){

                if(count2 == 1)stringBuilder.append("\nIntermediate Material for "+temperature+ "ºC :\n");
                else if (count2 == 2)stringBuilder.append("\nInner Walls Material for "+temperature+ "ºC :\n");
                stringBuilder.append(getMaterialByTemperature(temperature,count2));
                stringBuilder.append("\n");
                count2++;
                k--;
            }
        }



        return stringBuilder.toString();
    }

    public double getKConstant(int containerId, String wallType) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT m.PROPORTIONALITYCONSTANT from MATERIAL m\n" +
                "where m.ID = (Select  cm.MATERIALID from CONTAINERMATERIAL cm\n" +
                "where cm.REFRIGERATEDCONTAINERID = "+containerId+"\n" +
                "and cm.WALLTYPE = '"+wallType+"')";

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getDouble("PROPORTIONALITYCONSTANT");
                } else {
                    return 0;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    public String getMaterialByTemperature(int temperature, int j) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT mt.TYPE from MATERIALTYPE  mt\n" +
                "inner join MATERIAL m\n" +
                "on mt.ID = m.ID\n" +
                "where m.TEMPERATURE =" + temperature;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {


                for(int i = 0 ; i < j ; i++){
                    resultSet.next();
                }

                if(resultSet.next()){

                    return resultSet.getString("TYPE");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public int countMaterialsByTemperature(int temperature) throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "SELECT count(mt.TYPE) COUNTMATERIALS from MATERIALTYPE  mt\n" +
                "inner join MATERIAL m\n" +
                "on mt.ID = m.ID\n" +
                "where m.TEMPERATURE = "+temperature;

        try (PreparedStatement getPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = getPreparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("COUNTMATERIALS");
                } else {
                    return 0;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return  0;
    }
}
