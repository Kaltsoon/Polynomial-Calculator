/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Kalle
 */
public class Point {
    private int x;
    private int y;
    private Scaler scale;
    public Point(int x, int y, Scaler scale){
        this.x=x;
        this.y=y;
        this.scale=scale;
    }
    public void moveTo(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void draw(Graphics g){
        g.setColor(new Color(184,86,86));
        g.fillOval(this.x-4, this.y-4, 9, 9);
        String str = (double)(this.x-scale.getOriginX())/scale.getScale() + "," + (double)(scale.getOriginY()-this.y)/scale.getScale();
        g.setFont(new Font("Arial",Font.PLAIN,14));
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(str, g);
        g.setColor(Color.white);
        g.fillRect(this.x+20,this.y+20-fm.getAscent(),(int)rect.getWidth(),(int)rect.getHeight());
        g.setColor(new Color(184,86,86));
        g.drawString(str,this.x+20,this.y+20);
    }
}
