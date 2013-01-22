/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import domain.Polynomial;
/**
 *
 * @author Kalle
 */
public class Graph extends JPanel{
    private Polynomial poly;
    private List<Point> points;
    private List<Polynomial> polynomials;
    private Scaler scale;
    private List<Color> colors;
    public Graph(List<Polynomial> polynomials, List<Point> points, Scaler scale){
        super.setBackground(new Color(244,245,247));
        this.polynomials=polynomials;
        this.points=points;
        this.colors = new ArrayList<Color>();
        colors.add(new Color(89,124,164));
        colors.add(new Color(148,179,96));
        colors.add(new Color(150,130,101));
        colors.add(Color.black);
        this.scale=scale;
    }
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        for (int i = 0; i < polynomials.size(); i++) {
            polynomials.get(i).setScale(scale.getScale());
        }        
        Graphics2D g3 = (Graphics2D)graphics;
        g3.setColor(Color.white);
        drawGrid(g3);
        Graphics2D g = (Graphics2D)graphics;
        g.setColor(new Color(166,180,189));
        drawAxis(g);
        drawCoordinates(g);
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setStroke(new BasicStroke(2));  
        g2.setColor(colors.get(0));
        drawGraphs(g2);
        Graphics2D g4 = (Graphics2D)graphics;
        drawPoints(g4);
    }
    private void drawPoints(Graphics g){
        for (int i = 0; i < points.size(); i++) {
            points.get(i).draw(g);
        }
    }
    private void drawAxis(Graphics g){
        g.drawLine(scale.getOriginX(), 0, scale.getOriginX(), 500);
        g.drawLine(0, scale.getOriginY(), 500, scale.getOriginY());
    }
    private void drawGrid(Graphics g){
         for(int i=scale.getOriginY(); i<=500; i+=scale.getScale()){
            g.drawLine(0, i, 500, i);
         }
         for(int i=scale.getOriginY(); i>=0; i-=scale.getScale()){
            g.drawLine(0, i, 500, i);
         }
         for(int i=scale.getOriginX(); i<=500; i+=scale.getScale()){
            g.drawLine(i, 0, i, 500);
         }
         for(int i=scale.getOriginX(); i>=0; i-=scale.getScale()){
            g.drawLine(i, 0, i, 500);
         }
    }
    private void drawGraphs(Graphics g){
        int n=0;
        for (int i = 0; i < polynomials.size(); i++) {
            if(n==colors.size()){
                n=0;
            }
            g.setColor(colors.get(n));
            drawGraph(g,polynomials.get(i));
            drawFunctionName(g,polynomials.get(i),colors.get(n),i);
            n++;
        }
    }
    private void drawCoordinates(Graphics g) {
        // Numbers
        g.drawString("1", scale.getOriginX()+scale.getScale()-3, scale.getOriginY()+scale.getScale()+5);
        g.drawString("1", scale.getOriginX()-20, scale.getOriginY()+scale.getScale()+scale.getScale()*(-2)+5);
        // Right side
        for(int i=scale.getOriginX(); i<=500; i+=scale.getScale()){
            g.drawLine(i, scale.getOriginY()-3, i, scale.getOriginY()+3);
        }
        // Left side
        for(int i=scale.getOriginX(); i>=0; i-=scale.getScale()){     
            g.drawLine(i, scale.getOriginY()-3, i, scale.getOriginY()+3);        
        }
        // Top
        for(int i=scale.getOriginY(); i>=0; i-=scale.getScale()){     
            g.drawLine(scale.getOriginX()-3, i, scale.getOriginX()+3, i);
        }
        // Bottom
        for(int i=scale.getOriginY(); i<=500; i+=scale.getScale()){     
            g.drawLine(scale.getOriginX()-3, i, scale.getOriginX()+3, i);
        }
    }

    private void drawGraph(Graphics g,Polynomial polynomial) {
        polynomial.setScale(scale.getScale());
        for(int x=(250-scale.getOriginX()); x<=250+(250-scale.getOriginX()); x++){
            g.drawLine(scale.getOriginX()+x, (scale.getOriginY()-(int)(polynomial.valueAt((double)x))), scale.getOriginX()+x+1, (scale.getOriginY()-(int)polynomial.valueAt((double)x+1)));
        }
        for(int x=-scale.getOriginX(); x<=(250-scale.getOriginX()); x++){
            g.drawLine(scale.getOriginX()+x, scale.getOriginY()-(int)polynomial.valueAt((double)x), scale.getOriginX()+x+1, scale.getOriginY()-(int)polynomial.valueAt((double)x+1));
        }
    }

    private void drawFunctionName(Graphics g, Polynomial poly, Color fontColor,int i) {
        String str="f(x)="+poly.toString()+"";
        g.setFont(new Font("Arial",Font.PLAIN,14));
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(str, g);
        g.setColor(Color.white);
        g.fillRect(25,35+(35*i)-fm.getAscent(),(int)rect.getWidth(),(int)rect.getHeight());
        g.setColor(fontColor);
        g.drawString(str, 25, 35+(35*i));
    }
}
