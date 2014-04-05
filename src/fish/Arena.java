/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fish;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

/**
 *
 * @author abc
 */
public class Arena extends javax.swing.JPanel {

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        this.setBackground(new Color(153, 217, 234));
        g2.setColor(Color.BLACK);
        g2.fillOval(Constants.target1x,Constants.target1y,10,10);
         g2.fillOval(Constants.target2x,Constants.target2y,10,10);
        //System.out.println(Forest.selected!=null);
        /*
         * if(Forest.selected!=null){ for(Tree t: Forest.arrayOfAliveTrees){
         * if(t!=null){ Forest.selectedtree=t; t.color=Constants.selectedcolor;
         * Forest.selected=null; } }
             }
         */
               

       int i=0,j=0;
       //System.out.println(Pool.array.length);
       for (i=0;i<Pool.array.length;i++){
           //System.out.print(Pool.array[i].x);
           if(Pool.array[i]!=null){
               if(Pool.array[i].choice==1){
                     g2.setColor(Color.RED);
                     j++;
               }
                 
               if(Pool.array[i].choice==2)
               {
                   g2.setColor(Color.GREEN);
                  // j++;
               }
               if(Pool.array[i].choice==3)
                    g2.setColor(Color.orange);
        g2.fill(new Ellipse2D.Double(Pool.array[i].x,Pool.array[i].y,5,5));
               
        
           }
       }
       System.out.println(""+j);
    }
}
