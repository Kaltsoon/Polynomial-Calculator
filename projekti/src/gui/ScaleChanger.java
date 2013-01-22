/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kalle
 */
public class ScaleChanger implements KeyListener{
    private Scaler scale;
    private Component piirtoalusta;
    private List<Integer> availableScales;
    private int currentScale = 1;
    public ScaleChanger(Scaler scale, Component piirtoalusta){
        this.scale=scale;
        this.piirtoalusta=piirtoalusta;
        this.availableScales=new ArrayList<Integer>();
        this.availableScales.add(10);
        this.availableScales.add(25);
        this.availableScales.add(50);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==45 && currentScale>0){
            currentScale--;
            scale.setScale(availableScales.get(currentScale));
        }
        if(e.getKeyCode()==521 && currentScale<availableScales.size()-1){
            currentScale++;
            scale.setScale(availableScales.get(currentScale));
        }
        if(e.getKeyCode()==37){
            scale.setOriginX(scale.getOriginX()+scale.getScale());
        }else if(e.getKeyCode()==39){
            scale.setOriginX(scale.getOriginX()-scale.getScale());
        }else if(e.getKeyCode()==40){
            scale.setOriginY(scale.getOriginY()-scale.getScale());
        }else if(e.getKeyCode()==38){
            scale.setOriginY(scale.getOriginY()+scale.getScale());
        }
        piirtoalusta.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
