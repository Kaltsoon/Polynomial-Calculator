/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import domain.Polynomial;

/**
 *
 * @author Kalle
 */
public class MouseInfo implements MouseListener, MouseMotionListener{
    private Component piirtoalusta;
    private List<Polynomial> polynomials;
    private List<Point> points;
    private Scaler scale;
    public MouseInfo(Component piirtoalusta, List<Polynomial> polynomials, List<Point> points, Scaler scale){
        this.piirtoalusta=piirtoalusta;
        this.polynomials=polynomials;
        this.points=points;
        this.scale=scale;
    }
    @Override
    public void mouseClicked(MouseEvent e) {       
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //polynomial.setScale(25);
        for (int i = 0; i < polynomials.size(); i++) {
            points.get(i).moveTo(e.getX(), (scale.getOriginY()-(int)(polynomials.get(i).valueAt((double)(e.getX()-scale.getOriginX())))));
            piirtoalusta.repaint();
        }
    }
    
}
