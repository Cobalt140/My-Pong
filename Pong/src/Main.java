import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Copyright MineStein 2014©
 * All files included within the project are subject under the standard
 * GNU license. Any and all assets are the sole property of MineStein.
 */
public class Main extends JFrame {

    //double buffering
    Image dbImage;
    Graphics dbg;

    //Ball objects
    static Ball b = new Ball(193,143);


    Dimension dimension = new Dimension(400, 300);

    public Main() {
        this.setTitle("PingPong");
        this.setSize(dimension);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);

        addKeyListener(new AC());
    }

    public class AC extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        Main core = new Main();
        //create and start threads
        Thread ball = new Thread(b);
        ball.start();
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        p1.start();
        p2.start();


    }

    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage,0,0,this);
    }

    public void draw(Graphics g){
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);

        g.setColor(Color.WHITE);
        g.drawString(""+b.p1Score, 20, 50);
        g.drawString(""+b.p2Score, 370, 50);
        repaint();



    }
}