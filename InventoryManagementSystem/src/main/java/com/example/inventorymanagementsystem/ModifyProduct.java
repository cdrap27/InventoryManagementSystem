package com.example.inventorymanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * class for the modify product screen
 */
public class ModifyProduct {
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
    public TableView partsTable;
    /**
     * reference for the GUI
     */
    public TableView associatedView;
    /**
     * reference for the GUI
     */
    public TableColumn productID1;
    /**
     * reference for the GUI
     */
    public TableColumn partName21;
    /**
     * reference for the GUI
     */
    public TableColumn inventoryLevel21;
    /**
     * reference for the GUI
     */
    public TableColumn priceCPU21;
    /**
     * reference for the GUI
     */
    public Button addPart;
    /**
     * reference for the GUI
     */
    public TextField prodID;
    /**
     * reference for the GUI
     */
    public TextField min;
    /**
     * reference for the GUI
     */
    public TextField max;
    /**
     * reference for the GUI
     */
    public TextField price;
    /**
     * reference for the GUI
     */
    public TextField inv;
    /**
     * reference for the GUI
     */
    public TextField name;

    /**
     * reference for the GUI
     */
    public int initialTemp;
    /**
     * reference for the GUI
     */
    public TextField partSearch;



    /**
     * initializes the modify product page
     */
    public void initialize(){

        Product modif = MainController.getModifyProduct();

        int ID = modif.getId();
        prodID.setText(Integer.toString(ID));
        min.setText(Integer.toString(modif.getMin()));
        max.setText(Integer.toString(modif.getMax()));
        price.setText(Double.toString(modif.getPrice()));
        inv.setText(Integer.toString(modif.getStock()));
        name.setText(modif.getName());
        Part x;
        for(int i = 0; i < Inventory.getAssociatedParts().size(); i++) {
          if(((associatedPart)Inventory.getAssociatedParts().get(i)).getProductID() == modif.getId()) {
                x = Inventory.getAssociatedParts().get(i);
                Inventory.addATemp(x);
            }
        }
        associatedView.setItems(Inventory.getTemp());
        productID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName21.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel21.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCPU21.setCellValueFactory(new PropertyValueFactory<>("price"));

        initialTemp = Inventory.getTemp().size();

        //Set the items from the parts table
        partsTable.setItems(Inventory.getPartInventoryList());
        //Set parts column equal to the associated parts
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCPU.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedView.setItems(Inventory.getTemp());
        productID1.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName21.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel21.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCPU21.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * adds a part from the parts Table into the selected parts table
     * additionally, moves all added parts into a temporary inventory list before saving
     * checks to see if the part has already been added.  If so, prints an error window.
     * @param actionEvent activates when the add part button is clicked
     */
    public void onAddPart(ActionEvent actionEvent) {
        if(!partsTable.getSelectionModel().isEmpty()) {
            Part x = (Part) partsTable.getSelectionModel().getSelectedItem();
            Boolean check = true;
            for (int i = 0; i < Inventory.getTemp().size(); i++) {
                if (x.getId() == Inventory.getTemp().get(i).getId()) {

                    check = false;


                }
            }
            if (check == true) {

                Inventory.addATemp(x);
                associatedView.setItems(Inventory.getTemp());
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Part Already Added");
                errorAlert.setContentText("Part Already Added");
                errorAlert.showAndWait();
            }
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Part Selected");
            errorAlert.setContentText("No Part Selected");
            errorAlert.showAndWait();
        }
    }

    /**
     * when the cancel button is clicked, returns to the main screen
     * @param actionEvent action event
     * @throws IOException ioexception
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Inventory.getTemp().clear();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1022, 480);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * checks all text fields to make sure the values are appropriate.
     * updates the product in the product inventory list
     * deletes all parts associated with this product from the associated parts list
     * adds the parts in the temporary list to the associated parts table.
     * returns to the main screen
     * if any issues with the text fields, an error is displayed and the stage remains on the addProducts screen
     */
    public void onSave(ActionEvent actionEvent) throws IOException {

        String temp;
        int stock, most, least, machineID;
        double cost;
        Boolean check = true;

        //check all the inputs to make sure they pass, print an error if they do not
        temp = name.getText();
        if(temp.length() < 1) {
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Name Error");
            errorAlert.setContentText("Name Error");
            errorAlert.showAndWait();
        }
        temp = inv.getText();
        if(Inventory.checkDigit(temp) == false || temp.length()<1 && check != false) {
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Inventory Error");
            errorAlert.setContentText("Inventory Error");
            errorAlert.showAndWait();
        }
        temp = price.getText();
        if(check!=false) {
            try {
                Double.parseDouble(temp);
                check = true;
            } catch (NumberFormatException e) {
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Price Error");
                errorAlert.setContentText("Price Error");
                errorAlert.showAndWait();
            }
        }
        if(temp.length()<1 && check!= false){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Price Error");
            errorAlert.setContentText("Price Error");
            errorAlert.showAndWait();
        }
        temp = max.getText();
        if(Inventory.checkDigit(temp) == false || temp.length()<1 && check != false) {
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Max Error");
            errorAlert.setContentText("Max Error");
            errorAlert.showAndWait();
        }
        temp = min.getText();
        if(Inventory.checkDigit(temp) == false || temp.length()<1 && check != false) {
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Min Error");
            errorAlert.setContentText("Min Error");
            errorAlert.showAndWait();
        }


        if(check == true ) {
            stock = Integer.parseInt(inv.getText());
            most = Integer.parseInt(max.getText());
            least = Integer.parseInt(min.getText());
            if (least > most && check != false) {
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Max Min Error");
                errorAlert.setContentText("Minimum Stock cannot be greater than Max Stock");
                errorAlert.showAndWait();
            }
            if (stock > most && check != false) {
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Max Stock Error");
                errorAlert.setContentText("Inventory cannot be greater than Max Stock");
                errorAlert.showAndWait();
            }
            if (stock < least && check != false) {
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Min Stock Error");
                errorAlert.setContentText("Inventory cannot be less than Minimum Stock");
                errorAlert.showAndWait();
            }

        }
        if(check == true){
            //add the product to the product list
            int ID = Integer.parseInt(prodID.getText());
            String theName = name.getText();
            stock = Integer.parseInt(inv.getText());
            cost = Double.parseDouble(price.getText());
            most = Integer.parseInt(max.getText());
            least = Integer.parseInt(min.getText());
            Product product = new Product(ID,theName, cost, stock, least, most);
            ID -=1;
            Inventory.updateProduct(ID, product);

            //add the associated parts to the list
            ID = Integer.parseInt(prodID.getText());
            for(int j = 0; j < initialTemp; j++){
            for(int i = 0; i < Inventory.getAssociatedParts().size(); i++){
                if(((associatedPart)Inventory.getAssociatedParts().get(i)).getProductID() == ID){
                   Inventory.getAssociatedParts().remove(i);
                }
            }
            }
            for(int i = 0; i < Inventory.getTemp().size(); i++) {

                    associatedPart x = new associatedPart(Inventory.getTemp().get(i).getId(), Inventory.getTemp().get(i).getName(),
                            Inventory.getTemp().get(i).getPrice(), Inventory.getTemp().get(i).getStock(), Inventory.getTemp().get(i).getMin(),
                            Inventory.getTemp().get(i).getMax(), ID);
                    Inventory.addAssociatedPart(x);

            }
            Inventory.getTemp().clear();

            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1022, 480);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();

        }

    }
    /**
     * removes the selected item from the secondary table and removes that entry from the temporary list.
     * @param actionEvent activates when the remove part button is clicked
     */
    public void onRemove(ActionEvent actionEvent) {
        if(!associatedView.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Record?");
            alert.setContentText("Are you sure you want to remove this part?");
            ButtonType CANCEL = new ButtonType("Cancel");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                Part x = (Part) associatedView.getSelectionModel().getSelectedItem();
                Inventory.getTemp().remove(x);

            }
            else
                return;
            }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No Part Selected");
            errorAlert.setContentText("No Part Selected");
            errorAlert.showAndWait();
        }

    }

    /**
     * searches the parts table by part id and name
     * if no matches are found, an error is displayed.
     * @param actionEvent activates on 'enter' when using the search box
     */
    public void onSearch(ActionEvent actionEvent) {
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
}
