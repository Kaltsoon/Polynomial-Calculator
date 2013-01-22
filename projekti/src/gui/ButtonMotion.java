/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Kalle
 */
public class ButtonMotion implements MouseListener{
    private javax.swing.JButton jButton;
    public ButtonMotion(javax.swing.JButton jButton){
        this.jButton=jButton;
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
        jButton.setBackground(new Color(89,124,164));
        jButton.setForeground(Color.white);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        jButton.setBackground(Color.white);
        jButton.setForeground(new Color(89,124,164));
    }
}
