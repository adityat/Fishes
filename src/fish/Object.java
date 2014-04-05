/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fish;

/**
 *
 * @author abc
 */
public class Object {
    double x; // Position
    double y;
    double speed;
    
    double[] socheading=new double[2];
    double[] goal= new double[2];
    
    double[] heading = new double[2];
    
    double omega;
    
    int choice;
    
    
    
    
    
   public Object(double x, double y, double speed, double[] socheading,double[] goal,double omega,int choice){
       this.x=x;
       this.y=y;
       this.speed=speed;
       this.socheading=socheading;
       this.goal=goal;
       this.omega=omega;
       this.choice=choice;
   }
   
}
