/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upadminapplication;

/**
 *
 * @author Nkosinathi
 */
public class ComboItem {
    
    private String value;
    private String label;
    
    public ComboItem(String value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setValue() {
        this.value = value;
    }
    
    public void setLabel() {
        this.label = label;
    }
    
    @Override
    public String toString() {
        return label;
    }
    
}
