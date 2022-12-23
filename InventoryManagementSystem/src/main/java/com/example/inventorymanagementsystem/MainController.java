package com.example.inventorymanagementsystem;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * main controller
 */
public class MainController implements Initializable {
    /**
     * reference for the GUI
     */
    private static Part modifyPart;
    /**
     * reference for the GUI
     */
    private static Product modifyProduct;

    /**
     * reference for the GUI
     */
    public TableColumn partID;
    /**
     * reference for the GUI
     */
    public TableColumn partName;
    /**
     * reference for the GUI
     */
    public TableColumn inventoryLevel;
    /**
     * reference for the GUI
     */
    public TableColumn priceCPU;
    /**
     * reference for the GUI
     */
    public TableColumn productID;
    /**
     * reference for the GUI
     */
    public TableColumn partName2;
    /**
     * reference for the GUI
     */
    public TableColumn inventoryLevel2;
    /**
     * reference for the GUI
     */
    public TableColumn priceCPU2;
    /**
     * reference for the GUI
     */
    public TableView partsTable;
    /**
     * reference for the GUI
     */
    public TableView productsTable;
    /**
     * reference for the GUI
     */
    public TextField partSearch;
    /**
     * reference for the GUI
     */
    public TextField productSearch;

    /**
     * gets a part to be modified
     * @return modify part
     */
    public static Part getModifyPart(){
        return modifyPart;

    }
    public static Product getModifyProduct(){
        return modifyProduct;
    }
    /**
     * initializes the main
     * @param url url
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){


        //Set the items from the parts table
        partsTable.setItems(Inventory.getPartInventoryList());
        //Set parts column equal to the associated parts
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCPU.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Set the items from the products table
        productsTable.setItems(Inventory.getProductInventoryList());
        //Set parts column equal to the associated parts
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCPU2.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * goes to the add part page upon clicking add
     * @param actionEvent action event
     * @throws IOException ioexception
     */
    public void onButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 599, 738);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();


    }

    /**
     * closes the application upon clicking exit
     * @param actionEvent action event
     */
    public void onExit(ActionEvent actionEvent) {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * upon pressing enter in the search parts, shows all matching parts
     * if no matching parts are found, an error is displayed
     * @param actionEvent actionevent
     */
    public void partsSearch(ActionEvent actionEvent) {
        String search = partSearch.getText();

        if(search.length() > 0 && Inventory.searchParts(search).size() == 0)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Matching Result Found");
            errorAlert.setContentText("No Matching Result Found");
            errorAlert.showAndWait();
        }
        partsTable.setItems(Inventory.searchParts(search));


    }

    /**
     * checks if a part is selected, displays an error if their is not
     * gets the part selected and stores it
     * then loads the modify part page.
     * @param actionEvent action event
     * @throws IOException ioexception
     */
    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        if(!partsTable.getSelectionModel().isEmpty()) {
            modifyPart = (Part) partsTable.getSelectionModel().getSelectedItem();


            Parent root = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 599, 738);
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();
        }

        //if nothing is selected, print an error message
        else
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Nothing Selected");
            errorAlert.setContentText("Nothing Selected to Modify");
            errorAlert.showAndWait();
        }
    }

    /**
     * makes sure a part is selected and displays an error if not
     * displays an confirmation asking if the user wishes to delete the record
     * does nothing if cancel is selected
     * deletes the record if ok is selected
     * @param actionEvent action event
     */
    public void deletePart(ActionEvent actionEvent) {
        if(!partsTable.getSelectionModel().isEmpty()){
            int i = partsTable.getSelectionModel().getSelectedIndex();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Record?");
            alert.setContentText("Are you sure you want to delete this part?");
            ButtonType CANCEL = new ButtonType("Cancel");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
            Inventory.getPartInventoryList().remove(i);
            }
            else
                return;

        }
        //if nothing is selected, print an error message
        else
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Nothing Selected");
            errorAlert.setContentText("Nothing Selected to Delete");
            errorAlert.showAndWait();
        }    }

    /**
     * searches the product list
     * if no matches, an error is displayed
     * shows all mathcing products
     * @param actionEvent action event
     */
    public void productSearch(ActionEvent actionEvent) {
        String search = productSearch.getText();

        if(search.length() > 0 && Inventory.searchProducts(search).size() == 0)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Matching Result Found");
            errorAlert.setContentText("No Matching Result Found");
            errorAlert.showAndWait();
        }
        productsTable.setItems(Inventory.searchProducts(search));
    }

    /**
     * goes to the add product screen upon clicking add
     * @param actionEvent action event
     * @throws IOException ioexception
     */
    public void addProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addProducts.fxml")));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1078 , 778);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * checks if a product is selected
     * if not, displays an error
     * stores the product selected under modifypart
     * loads the modify product screen
     * @param actionEvent action event
     * @throws IOException ioexception
     */
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {
        if(!productsTable.getSelectionModel().isEmpty()) {
            modifyProduct = (Product) productsTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("modifyProduct.fxml")));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1078, 778);
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();
        }
        //if nothing is selected, print an error message
        else
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Nothing Selected");
            errorAlert.setContentText("Nothing Selected to Modify");
            errorAlert.showAndWait();
        }
    }

    /**
     * checks if a product is selected
     * displays an error if their is not
     * displays a confirmation asking if the user wants to delete the record.
     * does nothing if the user clicks cancel
     * deletes the product from the product list and deletes the associated parts from the associated part list if the
     * user clicks ok
     * @param actionEvent action event
     */
    public void onDeleteProduct(ActionEvent actionEvent) {
        if(!productsTable.getSelectionModel().isEmpty()){
            int i = productsTable.getSelectionModel().getSelectedIndex();
            int x = ((Product)productsTable.getSelectionModel().getSelectedItem()).getId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Record?");
            alert.setContentText("Are you sure you want to delete this product?");
            ButtonType CANCEL = new ButtonType("Cancel");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                boolean check = false;
                for(int j = 0; j < Inventory.getAssociatedParts().size(); j++) {
                    for (int k = 0; k < Inventory.getAssociatedParts().size(); k++) {
                        if (((associatedPart) Inventory.getAssociatedParts().get(k)).getProductID() == x) {
                          check = true;
                        }
                    }
                }
                if(check == false){
                Inventory.getProductInventoryList().remove(i);
                }
                else if (check == true){
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Cannot Delete Product");
                    errorAlert.setContentText("The Product cannot be deleted because it has associated parts.");
                    errorAlert.showAndWait();

                }
            }
            else
                return;

        }
        //if nothing is selected, print an error message
        else
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Nothing Selected");
            errorAlert.setContentText("Nothing Selected to Delete");
            errorAlert.showAndWait();
        }
    }
}