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

/**
 * class for the modify part
 */
public class ModifyPart {
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
     * reference for the GUI
     */
        public RadioButton outSourcedRadio;

    /**
     * initializes the modify part page
      */
    public void initialize(){
            Part modif = MainController.getModifyPart();
            int ID = modif.getId();
            partID.setText(Integer.toString(ID));
            name.setText(modif.getName());
            inv.setText(Integer.toString(modif.getStock()));
            price.setText(Double.toString(modif.getPrice()));
            max.setText(Integer.toString(modif.getMax()));
            min.setText(Integer.toString(modif.getMin()));
            if(modif instanceof Outsourced){
                mid.setText(((Outsourced) modif).getCompanyName());
                machineID.setText("Company Name");
                outSourcedRadio.setSelected(true);
            }
            else if(modif instanceof inHouse){
                mid.setText(Integer.toString(((inHouse) modif).getMachineID()));
                machineID.setText("Machine ID");

            }
        }

    /**
     * returns to the main page upon clicking cancel
     * @param actionEvent action event
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
     * changes the text to machine id if inhouse is selected
     * @param actionEvent action event
     */
    public void inHouse(ActionEvent actionEvent) {
            machineID.setText("Machine ID");

        }

    /**
     * changes the text to company name if outsourced is selected
     * @param actionEvent action event
     */
    public void outsourced(ActionEvent actionEvent) {
            machineID.setText("Company Name");
        }

    /**
     * checks all the inputs and makes sure they're valid.  displays an error if not
     * if everything is valid, a part is created using the inputs and that part is used to update the
     * part inventory list. next it returns to the main screen
     * if something is not valid, it stays on the modify part screen
     * @param actionEvent action event
     * @throws IOException ioexception
     */
    public void addPart(ActionEvent actionEvent) throws IOException {
            String temp;
            int stock, most, least, machineID;
            double cost;
            Boolean check = true;
            int ID =Integer.parseInt(partID.getText());
            //partID.setText(Integer.toString(ID));
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
                ID -=1;
                Inventory.updatePart(ID, x);
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
                ID -=1;
                Inventory.updatePart(ID, x);
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

