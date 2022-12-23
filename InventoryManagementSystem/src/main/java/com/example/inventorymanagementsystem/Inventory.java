package com.example.inventorymanagementsystem;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * creates the inventory class for the main inventory
 */
public class Inventory {
    /**
     * creates an observable list of type Part for the part inventory list
     */
    private static ObservableList<Part> partInventoryList = FXCollections.observableArrayList();

    /**
     * adds a part to the part inventory list
     * @param part part
     */
    public static void addPart(Part part){
        partInventoryList.add(part);

    }

    /**
     * getter for the part Inventory List
     * @return part inventory list
     */
    public static ObservableList<Part> getPartInventoryList(){
        return partInventoryList;

    }

    /**
     * searches the product list using a string
     * if the string is empty, return the entire list
     * otherwise, search the list for any matching strings
     * next, check if the string is actually an integer
     * if it is, search the list again using the integer and comparing it against the product ids
     * return all matching results
     * @param search string to search
     * @return matching products
     */
    public static ObservableList<Product>searchProducts(String search){
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        if(search.length() == 0){
            return productInventoryList;
        }
        for(int i = 0; i < productInventoryList.size(); i++){
            if(productInventoryList.get(i).getName().toLowerCase().contains(search.toLowerCase()))
                foundProducts.add(productInventoryList.get(i));
        }
        boolean check = true;
        for (int i = 0; i < search.length(); i++){
            char c = search.charAt(i);
            if(c < '0' || c > '9'){
                check = false;
            }
        }
        if (check == true)


        {if (Integer.parseInt(search) > 0) {
            int x = Integer.valueOf(search);
            for (int i = 0; i < productInventoryList.size(); i++) {
                if (productInventoryList.get(i).getId() == x)
                    foundProducts.add(productInventoryList.get(i));
            }
        }
        }

        return foundProducts;

    }

    /**
     * searches the part list using a string
     * if the string is empty, return the entire list
     * otherwise, search the list for any matching strings
     * next, check if the string is actually an integer
     * if it is, search the list again using the integer and comparing it against the part ids
     * return all matching results
     * @param search string to search
     * @return matching parts
     */
    public static ObservableList<Part>searchParts(String search) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        if (search.length() == 0) {
            return partInventoryList;
        }
        for (int i = 0; i < partInventoryList.size(); i++) {
            if (partInventoryList.get(i).getName().toLowerCase().contains(search.toLowerCase()))
                foundParts.add(partInventoryList.get(i));
        }

        boolean check = true;
        for (int i = 0; i < search.length(); i++) {
            char c = search.charAt(i);
            if (c < '0' || c > '9') {
                check = false;
            }
        }
            if (check == true)


              {if (Integer.parseInt(search) > 0) {
                    int x = Integer.valueOf(search);
                    for (int i = 0; i < partInventoryList.size(); i++) {
                        if (partInventoryList.get(i).getId() == x)
                            foundParts.add(partInventoryList.get(i));
                    }
                }
            }

            return foundParts;
        }

    /**
     * creates an observable list of type Part product inventory list
     */
    private static ObservableList<Product> productInventoryList = FXCollections.observableArrayList();

    /**
     * adds products to the product inventory list
     * @param product product
     */
    public static void addProduct(Product product){
        productInventoryList.add(product);
    }

    /**
     * gets the product inventory list
     * @return product inventory list
     */
    public static ObservableList<Product> getProductInventoryList(){
        return productInventoryList;
    }

    /**
     * takes an ID and replaces the part inventory list at that ID and replaces it with the part
     * @param ID id to be replaced
     * @param part part to be replaced
     */
    public static void updatePart(int ID, Part part){
        Inventory.getPartInventoryList().set(ID, part);
    }

    /**
     * takes an ID and replaces the part inventory list at that ID and replaces it with the part
     * @param ID id to be replaced
     * @param product part to be replaced
     */
    public static void updateProduct(int ID, Product product){

        Inventory.getProductInventoryList().set(ID, product);
    }

    /**
     * checks a string of text to see if its an integer
     * @param text string input
     * @return true or false
     */
    public static Boolean checkDigit(String text){
        boolean check = true;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < '0' || c > '9') {
                check = false;
            }
        }
        return check;
    }

    /**
     * observable list of type part of parts associated with products
     */
    public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * observable list of type part.  temporary list that stores parts before adding them to the associated part list
     */
    public static ObservableList<Part> temp = FXCollections.observableArrayList();

    /**
     * gets the temporary part list
     * @return temporary part list
     */
    public static ObservableList<Part> getTemp(){
        return temp;
    }

    /**
     * adds a temporary part
     * @param part part
     */
    public static void addATemp(Part part) {

            temp.add(part);

    }

    /**
     * adds an associated part
     * @param part part
     */
    public static void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * gets the associated part list
     * @return associated parts
     */
    public static ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }

    /**
     * updates the associated parts
     * @param ID id of part
     * @param part part to be replaced
     */
    public static void updateAssociatedParts(int ID, Part part){
        Inventory.getAssociatedParts().set(ID, part);
    }

    /**
     * creates an associated observable list
     */
    public static ObservableList<Part> associated = FXCollections.observableArrayList();

    /**
     * gets the associated parts
     * @param ID id to search for parts
     * @return parts to be returned
     */
    public static ObservableList<Part> getAssociated(int ID){
        for(int i = 0; i < Inventory.getAssociatedParts().size(); i++){
            if(ID == ((associatedPart)Inventory.getAssociatedParts().get(i)).getProductID()){
                associated.add(Inventory.getAssociatedParts().get(i));
            }

        }
        return associated;
    }

    /**
     * deletes the associated list
     */
    public static void clearAssociated(){
        associated.removeAll();
    }

}
