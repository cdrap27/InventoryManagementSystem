package com.example.inventorymanagementsystem;

/**
 * creates a class for in house parts
 */
public class inHouse extends Part{
    /**
     * variable used exclusively for inhouse parts
     */
    private int machineID;

    /**
     * creates a inHouse part
     * @param id inhouse id
     * @param name inhouse name
     * @param price inhouse price
     * @param stock inhouse inventory
     * @param min inhouse minimum
     * @param max inhouse maximum
     * @param machineID inhouse machine id
     */
    public inHouse(int id, String name, double price, int stock, int min, int max, int machineID){
        super(id, name, price, stock, min, max);

        this.machineID = machineID;

    }

    /**
     * getter for the machine id
     * @return machine id
     */
    public int getMachineID(){
        return machineID;
    }

    /**
     * setter for the machine id
     * @param machineID machine id
     */
    public void setMachineID(int machineID){
        this.machineID = machineID;
    }
}
