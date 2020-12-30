package models;

public class ManufacturerModel {

    private Long id_manufacturer;
    private String name_manufacturer;

    public ManufacturerModel(Long id_manufacturer, String name_manufacturer) {
        this.id_manufacturer = id_manufacturer;
        this.name_manufacturer = name_manufacturer;
    }

    public ManufacturerModel(String name_manufacturer) {
        this.name_manufacturer = name_manufacturer;
    }

    public ManufacturerModel() {

    }

    public Long getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(Long id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public String getName_manufacturer() {
        return name_manufacturer;
    }

    public void setName_manufacturer(String name_manufacturer) {
        this.name_manufacturer = name_manufacturer;
    }

}


