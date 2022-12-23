package com.example.inventorymanagementsystem;

import javafx.collections.ObservableList;

/**
 * this class extends part and stores parts associated with the product list
 */
public class associatedPart extends Part  {
    /**
     * variable to reference the productid
     */
    private  int productID;

    /**
     * creates an associated part
     * @param id part id
     * @param name part name
     * @param price part price
     * @param stock part current inventory
     * @param min part minimum inventory
     * @param max part maximum inventory
     * @param productID part product id to reference the product associated
     */
    public associatedPart(int id, String name, double price, int stock, int min, int max, int productID) {
        super(id, name, price, stock, min, max);
        this.productID = productID;
    }

    /**
     * getter for the product id
     * @return the product id
     */
    public int getProductID(){
        return productID;
    }

    /**
     * setter for the product id
     * @param productID the product id
     */
    public  void setProductID(int productID){
        this.productID = productID;
    }

}
