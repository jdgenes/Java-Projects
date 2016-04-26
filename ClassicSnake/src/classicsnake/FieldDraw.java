/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicsnake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Joshua Albright
 */
public class FieldDraw extends JPanel
{
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Snake snake = Snake.snake;
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 200, 200);
        g.setColor(Color.ORANGE);
        
        for (Point point : snake.snake_body)
        {
            g.fillRect(point.x * Snake.size, point.y * Snake.size, Snake.size, Snake.size);
        }
        
        g.fillRect(snake.head.x * Snake.size, snake.head.y * Snake.size, Snake.size, Snake.size);
        g.setColor(Color.GREEN);
        g.fillRect(snake.dot.x * Snake.size, snake.dot.y * Snake.size, Snake.size, Snake.size);        
        
    }
}

