/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Kalle
 */
public class Scaler{
    private int scale=25;
    private int originX=250;
    private int originY=250;
    public int getScale(){
        return this.scale;
    }
    public void setScale(int scale){
        this.scale=scale;
    }
    public int getOriginX(){
        return originX;
    }
    public int getOriginY(){
        return originY;
    }
    public void setOriginX(int originX){
        this.originX=originX;
    }
    public void setOriginY(int originY){
        this.originY=originY;
    }
    
}
