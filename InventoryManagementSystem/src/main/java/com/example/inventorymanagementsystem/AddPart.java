package com.example.inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The class to add a part to the Inventory
 */
public class AddPart {

    /**
     * reference for the GUI
     */
    public TextField partID;
    /**
     * reference for the GUI
     */
    public Text machineID;
    /**
     * reference for the GUI
     */
    public TextField name;
    /**
     * reference for the GUI
     */
    public TextField inv;
    /**
     * reference for the GUI
     */
    public TextField price;
    /**
     * reference for the GUI
     */
    public TextField max;
    /**
     * reference for the GUI
     */
    public TextField mid;
    /**
     * reference for the GUI
     */
    public TextField min;
    /**
     * reference for the GUI
     */
    public RadioButton inHouseRadio;

    /**
     * initializes the screen
     */
    public void initialize(){
        int ID = Inventory.getPartInventoryList().size() + 1;
        partID.setText(Integer.toString(ID));    }

    /**
     * adds functionality to the Cancel button.  Takes the user back to the main page
     * @param actionEvent activates when the cancel button is clicked
     * @throws IOException ioexception
     */
    public void onAddCancel(ActionEvent actionEvent) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
       Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
       Scene scene = new Scene(root, 1022, 480);
       stage.setTitle("Main");
       stage.setScene(scene);
       stage.show();

    }

    /**
     * Changes the text for the bottom box to "Machine ID" if inHouse is selected
     * @param actionEvent activates when the radio button is selected
     */
    public void inHouse(ActionEvent actionEvent) {
        machineID.setText("Machine ID");

    }

    /**
     *
     * Changes the text for the bottom box to "Company Name" if outsourced is selected
     * @param actionEvent activates when the radio button is selected
     */
    public void outsourced(ActionEvent actionEvent) {
        machineID.setText("Company Name");
    }

    /**
     * checks all the text in every text field and prints an error with any of the text fields
     * if their's no errors, it adds the part to the inventory
     * if a part is added, it returns to the main screen.  If there is an error, it stays on the addPart screen
     * @param actionEvent activates when the add button is clicked
     * @throws IOException ioexception
     */
    public void addPart(ActionEvent actionEvent) throws IOException {
        String temp;
        int stock, most, least, machineID;
        double cost;
        Boolean check = true;
        int ID = Inventory.getPartInventoryList().size() + 1;
        partID.setText(Integer.toString(ID));
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
            temp = mid.getText();
            if(  check != false && inHouseRadio.isSelected()) {
                if(Inventory.checkDigit(temp) == false || temp.length()<1){
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Machine ID Error");
                errorAlert.setContentText("Machine ID Error");
                errorAlert.showAndWait();
                }
            }
            else if (check != false && temp.length()<1 && !inHouseRadio.isSelected()){
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Company Name Error");
                errorAlert.setContentText("Company Name Error");
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

        //add an in house part
        if(check == true && inHouseRadio.isSelected()) {

                String theName = name.getText();
                stock = Integer.parseInt(inv.getText());
                cost = Double.parseDouble(price.getText());
                most = Integer.parseInt(max.getText());
                least = Integer.parseInt(min.getText());
                machineID = Integer.parseInt(mid.getText());
                inHouse x = new inHouse(ID, theName, cost, stock, least, most, machineID);
                Inventory.addPart(x);
            }

        //add an outsourced part
        else if(check == true && !inHouseRadio.isSelected()){


            String theName = name.getText();
             stock = Integer.parseInt(inv.getText());
             cost = Double.parseDouble(price.getText());
             most = Integer.parseInt(max.getText());
             least = Integer.parseInt(min.getText());
            String companyName = mid.getText();
            Outsourced x = new Outsourced(ID, theName, cost, stock, least, most, companyName);
            Inventory.addPart(x);
        }

        //return to main screen
        if(check == true){
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1022, 480);
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
        }
    }

}
