package com.example.inventorymanagementsystem;

/**
 * creates the outsourced part class
 */
public class Outsourced extends Part{

    /**
     * string for the company name
     */
    private String companyName;

    /**
     * creates the outsourced class
     * @param id outsourced id
     * @param name outsourced name
     * @param price outsourced price
     * @param stock outsourced inventory
     * @param min outsourced minimum
     * @param max outsourced maximum
     * @param companyName outsourced company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);

        this.companyName = companyName;

    }

    /**
     * getter for the company name
     * @return company name
     */
    public String getCompanyName(){
        return companyName;
    }

    /**
     * setter for the company name
     * @param companyName company name
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
}
