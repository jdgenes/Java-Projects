/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicsnake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Joshua Albright
 */
public class Snake implements ActionListener, KeyListener {

    public static Snake snake;
    public ArrayList<Point> snake_body = new ArrayList<>();
    public Point head, dot;
    public JFrame frame;
    boolean game_running = true;
    public static int face_up = 0, face_right = 1, face_down = 2, face_left = 3, size = 10;
    public int snake_direction = face_right;
    public int speed = 0, time, tail_length;
    public Random random;
    public Dimension dimension;
    public FieldDraw draw_field;
    public Timer timer = new Timer(100, this);
    
    public Snake() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(205, 228);
        frame.setLocation(dimension.width / 2 - frame.getWidth() / 2, dimension.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);
        frame.add(draw_field = new FieldDraw());
        frame.addKeyListener(this);
        frame.setVisible(game_running);
        head = new Point(0, 0);
        head.setLocation(0, 0);
        FieldDraw field = new FieldDraw();
    
        gameLoop();
        
    }
    
    public void gameLoop()
    {
        game_running = true;
        speed = 0;
        head = new Point(0, 0);
        time = 0;
        tail_length = 1;
        random = new Random();
        snake_body.clear();
        dot = new Point(random.nextInt(19), random.nextInt(19));
        timer.start();
        
                
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        draw_field.repaint();
        speed++;
        System.out.println(head.x + ", " + head.y);
            if (speed % 2 == 0 && head != null && game_running)
            {
            time++;
            snake_body.add(new Point(head.x, head.y));
            
            if (snake_direction == face_up)
            {
                if (head.y - 1 >= 0 && noTail(head.x, head.y - 1))
                {
                    head = new Point(head.x, head.y - 1);
                }
                else
                {
                    game_running = false;
                }
            }
            
            if (snake_direction == face_down)
            {
                if (head.y + 1 < 20 && noTail(head.x, head.y + 1))
                {
                    head = new Point(head.x, head.y + 1);
                }
                else
                {
                    game_running = false;
                }
            }
            
            if (snake_direction == face_left)
            {
                if (head.x - 1 >= 0 && noTail(head.x - 1, head.y))
                {
                    head = new Point(head.x - 1, head.y);
                }
                else
                {
                    game_running = false;
                }
            }
            
            if (snake_direction == face_right)
            {
                if (head.x + 1 < 20 && noTail(head.x + 1, head.y))
                {
                    head = new Point(head.x + 1, head.y);
                }
                else
                {
                    game_running = false;
                }
            }
            
            if (snake_body.size() > tail_length)
            {
                snake_body.remove(0);
            }
            
            if (dot != null)
            {
                if (head.equals(dot))
                {
                    tail_length++;
                    dot.setLocation(random.nextInt(19), random.nextInt(19));
                }
            }
        } else {
        }
    }
    
    public boolean noTail(int x, int y)
    {
        for (Point point : snake_body)
        {
            if (point.equals(new Point(x, y)))
            {
                return false;
            }
        }
        return true;
    }
    
        public static void main(String[] args)
        {
        
            snake = new Snake();
        
        }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        long lastKeyTime = 0;
            
            if (key == KeyEvent.VK_UP && snake_direction != face_down) {
                snake_direction = face_up;
                lastKeyTime = System.currentTimeMillis();
            }
            else if (key == KeyEvent.VK_DOWN && snake_direction != face_up) {
                snake_direction = face_down;
                lastKeyTime = System.currentTimeMillis();
            }
            else if (key == KeyEvent.VK_RIGHT && snake_direction != face_left) {
                snake_direction = face_right;
                lastKeyTime = System.currentTimeMillis();
            }
            else if (key == KeyEvent.VK_LEFT && snake_direction != face_right) {
                snake_direction = face_left;
                lastKeyTime = System.currentTimeMillis();
            }
        }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}