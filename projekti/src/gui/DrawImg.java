package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Kalle
 */
public class DrawImg extends JPanel{
    public DrawImg(){
        super.setBackground(Color.white);
    }
    private BufferedImage img = null;
    public void setImage(String src){
        try{
         img = ImageIO.read(new File(src));
        }
        catch(IOException e){
        }
    }
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(img, 13, 13, this);
    }
}
