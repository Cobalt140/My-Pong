import java.awt.*;
import java.util.Random;

/**
 * Created by john on 4/6/2015.
 */

public class Ball implements Runnable{
    // Global variables
    int x,y,xDir,yDir;

    Paddle p1 = new Paddle(15,140, 1);
    Paddle p2 = new Paddle(370,140, 2);

    Rectangle ball;

    int p1Score, p2Score;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        //Set Ball moving randomly
        Random r = new Random();
        int rDir = r.nextInt(2);
        if(rDir == 0)
            rDir--;
        setXDirection(rDir);
        int yrDir = r.nextInt(2);
        if(yrDir == 0)
            yrDir--;
        setYDirection(yrDir);
        // create ball
        ball = new Rectangle(this.x, this.y, 15,15);

        p1Score = p2Score = 0;
    }

    public void setXDirection(int xdir){
        xDir = xdir;
    }
    public void setYDirection(int ydir){
        yDir = ydir;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(ball.x,ball.y,ball.width,ball.height);

    }

    public void move(){
        ball.x += xDir;
        ball.y += yDir;
        //Bouce ball if enge is detected
        if(ball.x <= 0){
            setXDirection(+1);
            p2Score++;
            ball.x = 193;
            ball.y = 143;
        }
        if(ball.x >= 385){
            setXDirection(-1);
            p1Score++;
            ball.x = 193;
            ball.y = 143;
        }
        if(ball.y <= 20){
            setYDirection(+1);
        }
        if(ball.y >= 285){
            setYDirection(-1);
        }

        if(ball.intersects(p1.paddle)){
            if(xDir > 0)
                setXDirection(-1);
            else
                setXDirection(+1);
        }
        if(ball.intersects(p2.paddle)){
            if(xDir > 0)
                setXDirection(-1);
            else
                setXDirection(+1);
        }


    }


    @Override
    public void run() {
        try {
            while(true){
                move();
                Thread.sleep(6);
            }



        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
