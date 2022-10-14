/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;



/**
 *
 * @author namdh1
 */
public class NiceNumber {
    private String isdn;
    private String price;
    private String status ;
    private int type;
    private int typeIsdn;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeIsdn() {
        return typeIsdn;
    }

    public void setTypeIsdn(int typeIsdn) {
        this.typeIsdn = typeIsdn;
    }
    
    
    
            
}
