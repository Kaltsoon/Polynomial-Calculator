package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import commands.CommandCollection;
import utils.Template;
/**
 *
 * @author Kalle
 */
public class CommandLineGUI implements Runnable{
    private JFrame frame;
    private CommandCollection commandCollection;
    public CommandLineGUI(CommandCollection commandCollection){
        this.commandCollection=commandCollection;
    }
    @Override
    public void run() {
        frame = new JFrame("Command-line");
        frame.setPreferredSize(new Dimension(600, 650));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    private void luoKomponentit(Container container) {
        container.setBackground(new Color(244,245,247));
        UIManager.put("Button.background", Color.white);
        UIManager.put("Button.foreground", new Color(89,124,164));
        javax.swing.JButton jButton1;
        javax.swing.JLabel jLabel1;
        javax.swing.JPanel jPanel1;
        javax.swing.JPanel jPanel2;
        javax.swing.JScrollPane jScrollPane1;
        javax.swing.JTextField jTextField1;
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(206,210,219)));
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(206,210,219), 1));
        DrawImg draw = new DrawImg();
        jPanel2 = draw;
        jLabel1 = new javax.swing.JLabel();
        Template temp = new Template();
        jLabel2.setText(temp.welcome());
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setVerticalAlignment(SwingConstants.TOP);
        jLabel2.setBackground(new Color(244,245,247));
        jScrollPane1.setViewportView(jLabel2);
        jLabel2.setOpaque(true);
        jLabel2.setBackground(Color.white);
        jTextField1.addKeyListener(new KeyInfo(jTextField1,jLabel1,jPanel2,draw,commandCollection));
        jButton1.setText("=");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,35,0,0));
        jLabel1.setText("");
        jPanel2.hide();
        jScrollPane1.getVerticalScrollBar().setBackground(Color.white);
        jScrollPane1.getVerticalScrollBar().setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        jButton1.addActionListener(new ButtonInfo(jTextField1,jLabel2,commandCollection));
        Font font = new Font("Arial", Font.ITALIC, 15);
        jTextField1.setFont(font);
        jButton1.addMouseListener(new ButtonMotion(jButton1));
        jTextField1.setForeground(new Color(138,156,168));
        font = new Font("Arial", Font.BOLD, 15);
        jButton1.setFont(font);
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,15,0,15));
        //jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel1.setBackground(Color.white);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
        );


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(container);
        container.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        
    }
    public JFrame getFrame() {
        return frame;
    }
}
