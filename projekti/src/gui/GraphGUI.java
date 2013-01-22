/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import domain.Polynomial;

public class GraphGUI implements Runnable {

    private JFrame frame;
    private Component piirtoalusta;
    private List<Polynomial> polynomials;
    public GraphGUI(List<Polynomial> polynomials) {
        this.polynomials=polynomials;
    }
    public GraphGUI(Polynomial polynomial){
        this.polynomials=new ArrayList<Polynomial>();
        polynomials.add(polynomial);
    }
    @Override
    public void run() {
        frame = new JFrame("Graph");
        frame.setPreferredSize(new Dimension(520, 546));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        
        Scaler scale = new Scaler();
        List<Point> points = pointFactory(polynomials.size(),scale);
        piirtoalusta = new Graph(polynomials,points,scale);
        frame.addKeyListener(new ScaleChanger(scale,piirtoalusta));
        piirtoalusta.addMouseMotionListener(new MouseInfo(piirtoalusta,polynomials,points,scale));
        piirtoalusta.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        container.add(piirtoalusta);
    }
    public JFrame getFrame() {
        return frame;
    }
    private List<Point> pointFactory(int n,Scaler scale){
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(0,0,scale));
        }
        return points;
    }
}