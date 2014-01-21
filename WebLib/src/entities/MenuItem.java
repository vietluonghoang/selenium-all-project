/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Admin
 */
public class MenuItem {
    private WebElement currentElement;
    private ArrayList<MenuItem> childItems;

    public MenuItem(WebElement item) {
        this.currentElement=item;
        childItems=new ArrayList<MenuItem>();        
    }    
    
    public boolean hasChilds(){
        if(childItems.size()>0){
            return true;
        }
        return false;
    }
    public void addChildItem(MenuItem item){
        childItems.add(item);
    }
    public MenuItem getChildItem(int index){
        if(hasChilds()){
            return childItems.get(index);
        }
        return null;
    }
    
    public int size(){
        return childItems.size();
    }
    
    public WebElement getItem(){
        return currentElement;
    }
}
