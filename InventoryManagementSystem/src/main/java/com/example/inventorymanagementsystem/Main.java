package com.example.inventorymanagementsystem;

/**
 * @Author Chad Draper
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * LOGICAL/RUNTIME ERROR:  An error I encountered while making my code is modifying associated parts.  To modify parts,
 * I chose to remove all parts associated with the selected product, then add all the selected parts in the temporary
 * table back in.  The problem I encounter is that when there were multiple parts associated with the product, not all
 * of the parts would be removed.  This caused multiple instances of the same part being generated every time a product
 * was modified.  This error was caused because the associated parts were removed in a for loop.  If a part is removed,
 * the observable list gets smaller and shifts, but the variable still increments.  This causes the for loop to skip
 * over certain parts of the observable list.  To counter this, I took the number of associated parts for the product
 * as soon as the modifyProduct page is generated.  This integer is used to write a second for loop around the original.
 * This allows the associated part list to be fully searched in relation to how many associated parts there are for the
 * product selected.  This fixed the issue and allowed every associated part for the current product to be removed
 * before adding anything back in.
 *\                                                 \
 * FUTURE ENHANCEMENTS: If I were to continue working on this project, I can think of several features and quality of life improvements I
 *   would implement.  For starters, I would clean up the project.  I would make all of the pages look nicer and have
 *   everything stand out a bit more.  Additionally, I would streamline every function to only be a few lines and add
 *   other functions to call to make the code easier to read.  Finally, I would add a show all screen.  This screen would
 *   allow you to see every part and product in separate tables.  I would add functionality to this page to select any
 *   value of each part and product (other than the ID) and edit it.
 *\                                                    \
 * JAVADOC LOCATED IN JAVADOC FOLDER
 *\                                                    \
 * main code, controls application
 */
public class Main extends Application {


    /**
     * starts the application
     * @param stage stage
     * @throws IOException ioexception
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1022, 480);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * test data for the project.
     * includes 3 parts, 2 products, and 3 associated parts
     */
    public static void testData(){
        inHouse p = new inHouse(1, "Wheel", 26.99, 5, 2, 8, 6);
        Inventory.addPart(p);
        inHouse p2 = new inHouse(2,"Lugnuts", 1.25,36, 20, 100, 63);
        Inventory.addPart(p2);
        inHouse p3 = new inHouse(3, "Spark Plugs", 12.99, 7, 5, 10, 113);
        Inventory.addPart(p3);
        Product pr = new Product(1, "Truck", 1620.96, 4, 1, 5);
        Inventory.addProduct(pr);
        Product pr2 = new Product(2, "Car", 136.42, 3, 1, 5);
        Inventory.addProduct(pr2);
        Outsourced out = new Outsourced(4,"out", 36.99, 3,1,5,"outsource");
        Inventory.addPart(out);
        associatedPart a1 = new associatedPart(1,"Wheel", 26.99,5,2,8,1);
        Inventory.addAssociatedPart(a1);
        associatedPart a2 = new associatedPart(2,"Lugnuts", 1.25,36, 20, 100, 1);
        Inventory.addAssociatedPart(a2);
        associatedPart a3 = new associatedPart(1,"Wheel", 26.99,5,2,8,2);
        Inventory.addAssociatedPart(a3);



    }

    /**
     * plugs in the test data and launches the application
     * @param args args
     */
    public static void main(String[] args) {
        testData();
        launch();
    }
}