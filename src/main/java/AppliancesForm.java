import controllers.CategoryController;
import controllers.ManufacturerController;
import controllers.ProductController;
import models.CategoryModel;
import models.ManufacturerModel;
import models.ProductModel;
import views.ProductView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class AppliancesForm {
    private final Vector<Object> tableTitles = new Vector<>();
    private final ProductController productController = new ProductController();
    private JPanel jPanel;
    private JCheckBox productNameCheckBox;
    private JTextField filterByProductName;
    private JCheckBox manufacturerNameCheckBox;
    private JTextField filterByManufacturerName;
    private JButton filterButton;
    private JRadioButton nameRadioButton;
    private JRadioButton priceRadioButton;
    private JRadioButton noneOrder;
    private JButton addProductButton;
    private JButton gatherDataFromFileButton;
    private JTable productsTable;
    private JTextField addProductName;
    private JTextField addProductPrice;
    private JTextField addCategoryName;
    private JTextField addManufacturerName;

    private String orderBy = null;

    public AppliancesForm() throws SQLException {
        tableTitles.add("Product Name");
        tableTitles.add("Product Price");
        tableTitles.add("Category Name");
        tableTitles.add("Manufacturer Name");

        filterByProductName.setEnabled(false);
        filterByManufacturerName.setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        group.add(nameRadioButton);
        group.add(priceRadioButton);
        group.add(noneOrder);


        nameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameRadioButton.isSelected()){
                    orderBy = "p.name_product";
                }
            }
        });

        priceRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(priceRadioButton.isSelected()){
                    orderBy = "p.price";
                }
            }
        });

        noneOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(noneOrder.isSelected()){
                    orderBy = "";
                }
            }
        });


        productNameCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filterByProductName.setEnabled(true);
                    filterByProductName.setText("");
                    orderBy = "p.name_product";
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    filterByProductName.setEnabled(false);
                    filterByProductName.setText("");
                }
            }
        });

        manufacturerNameCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filterByManufacturerName.setEnabled(true);
                    filterByManufacturerName.setText("");
                    orderBy = "m.name_manufacturer";
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    filterByManufacturerName.setEnabled(false);
                    filterByManufacturerName.setText("");
                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateProducts();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addProduct(new ProductView(
                            addProductName.getText(),
                            Long.parseLong(addProductPrice.getText()),
                            addCategoryName.getText(),
                            addManufacturerName.getText()
                    ));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        gatherDataFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readFile();
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            }
        });



        updateProducts();
    }

    public static void main(String[] args) throws SQLException {
        JFrame myJFrame = new JFrame("AppliancesForm");
        myJFrame.setContentPane(new AppliancesForm().jPanel);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.pack();
        myJFrame.setVisible(true);
    }

    public void updateProducts() throws SQLException {
        List<ProductView> productViews = productController.search(orderBy, filterByManufacturerName.getText(), filterByProductName.getText());
        Vector<Vector<Object>> tableData = new Vector<>();
        productViews.forEach(productView -> {
            Vector<Object> productObject = new Vector<>();
            productObject.add(productView.getProductName());
            productObject.add(productView.getProductPrice());
            productObject.add(productView.getCategoryName());
            productObject.add(productView.getManufacturerName());
            tableData.add(productObject);
        });
        productsTable.setModel(new DefaultTableModel(tableData, tableTitles));
    }

    private void addProduct(ProductView productView) throws SQLException {
        CategoryModel categoryModel = new CategoryController().addNameCategory(new CategoryModel(productView.getCategoryName()));
        ManufacturerModel manufacturerModel = new ManufacturerController().addNameManufacturer(new ManufacturerModel(productView.getManufacturerName()));
        new ProductController().addProduct(new ProductModel(productView.getProductName(), productView.getProductPrice(), categoryModel, manufacturerModel));
        updateProducts();
    }

    private void readFile() throws IOException, SQLException {
        BufferedReader csvReader = new BufferedReader(new FileReader("productsData.csv"));
        String fileRow;
        while ((fileRow = csvReader.readLine()) != null) {
            String[] fileArray = fileRow.split(",");
            ProductView productView = new ProductView(
                    fileArray[0],
                    Long.parseLong(fileArray[1]),
                    fileArray[2],
                    fileArray[3]
            );
            addProduct(productView);
        }
        csvReader.close();
    }
}
