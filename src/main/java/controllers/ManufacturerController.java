package controllers;

import Database.Database;
import models.ManufacturerModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerController {
    private final Database database = new Database();

    public ManufacturerModel addNameManufacturer(ManufacturerModel manufacturerModel) throws SQLException {
        String selectRequest = "SELECT * FROM manufacturers WHERE name_manufacturer = '" + manufacturerModel.getName_manufacturer() + "'";
        String insertRequest = "INSERT INTO `manufacturers` (`name_manufacturer`) VALUES ('" + manufacturerModel.getName_manufacturer() + "');";
        ManufacturerModel insertedManufacturer = new ManufacturerModel();
        database.connect();
        if (!database.execRequest(selectRequest).next()) {
            database.execUpdate(insertRequest);

        }
        ResultSet res = database.execRequest(selectRequest);
        res.next();
        insertedManufacturer.setId_manufacturer(res.getLong("id_manufacturer"));
        insertedManufacturer.setName_manufacturer(res.getString("name_manufacturer"));
        database.close();
        return insertedManufacturer;
    }
}
