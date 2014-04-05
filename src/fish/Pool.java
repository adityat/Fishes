/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fish;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author abc
 */
public class Pool {
    
    static Object[] array = new Object[Constants.n];
    
    public static void create(int number){
        Random rand = new Random();
        
        
        int i=0;
    
        double dir[]=new double[2];
        dir[0]=1;
        dir[1]=0;
        double goal1[]=new double[2];
        goal1[0]=1;
        goal1[1]=0;
        
        
        for(i=0;i<Constants.N1;i++){
           array[i]=new Object(Constants.intitialx+Constants.initialwidth*rand.nextGaussian(),Constants.initialy+Constants.initialwidth*rand.nextGaussian(),Constants.speed,dir,goal1,Constants.omega1,1);
           
        }
        
          for(i=Constants.N1;i<Constants.N1+Constants.N2;i++){
           array[i]=new Object(Constants.intitialx+Constants.initialwidth*rand.nextGaussian(),Constants.initialy+Constants.initialwidth*rand.nextGaussian(),Constants.speed,dir,goal1,Constants.omega2,2);
           
        }
          
            for(i=Constants.N1+Constants.N2;i<array.length;i++){
           array[i]=new Object(Constants.intitialx+Constants.initialwidth*rand.nextGaussian(),Constants.initialy+Constants.initialwidth*rand.nextGaussian(),Constants.speed,dir,goal1,0,3);
           
        }
       /* for(i=array.length/2;i<array.length;i++){
           array[i]=new Object(200+20*rand.nextGaussian(),100+20*rand.nextGaussian(),10,dir,goal2);
           
        }*/
        
    }
    
    static void run(){
        //Pool.create(10);
        
        int i=0;
        int j=0;
        
        
        double dt=Constants.stepsize;
        double t=0;
        
        
                
      
        for(t=0;t<Constants.finaltime;t++){
            t=t+dt;
            i=0;
          
            for(Object o:Pool.array){
                o.socheading[0]=0;
                o.socheading[1]=0;
                //o.heading[0]=0;
                //o.heading[1]=0;
            } 
            
            for (Object o: Pool.array){
               
               // o.socheading[0]=-Math.sin(Math.atan2((o.y-100),(o.x-100)));
                //o.socheading[1]=Math.cos(Math.atan2((o.y-100),(o.x-100)));
                
               double norm=Math.sqrt(Math.pow(o.socheading[0],2)+Math.pow(o.socheading[1],2));
               /* if(norm!=0){
                o.socheading[0]=o.socheading[0]/norm;
                o.socheading[1]=o.socheading[1]/norm;
                }*/
                
                for (Object p: Pool.array){
                    double dist=Math.sqrt((p.x-o.x)*(p.x-o.x)+(p.y-o.y)*(p.y-o.y));
                    double pnorm=Math.sqrt(Math.pow(p.socheading[0],2)+Math.pow(p.socheading[1],2));
                    
                    if(pnorm!=0){
                        p.socheading[0]=p.socheading[0]/pnorm;
                        p.socheading[1]=p.socheading[1]/pnorm;
                    }
                    
                    if(o!=p&&dist<Constants.repulsionradius&&dist>0){
                    o.socheading[0]=o.socheading[0]-(p.x-o.x)/dist;
                    o.socheading[1]=o.socheading[1]-(p.y-o.y)/dist;
                            }
                    
                    if(o!=p&&dist<Constants.allignmentradius&&dist>Constants.repulsionradius){
                        o.socheading[0]=o.socheading[0]+(p.x-o.x)/dist+p.heading[0];
                    o.socheading[1]=o.socheading[1]+(p.y-o.y)/dist+p.heading[1];
                    }
                    
                    //System.out.print(""+Math.pow(o.socheading[0],2)+Math.pow(o.socheading[1],2));
                }
                
                
               norm=Math.sqrt(Math.pow(o.socheading[0],2)+Math.pow(o.socheading[1],2));
                if(norm!=0){
                o.socheading[0]=o.socheading[0]/norm;
                o.socheading[1]=o.socheading[1]/norm;
                }
                
                double distancetogoal1=Math.sqrt((Constants.target1x-o.x)*(Constants.target1x-o.x)+(Constants.target1y-o.y)*(Constants.target1y-o.y));
                 double distancetogoal2=Math.sqrt((Constants.target2x-o.x)*(Constants.target2x-o.x)+(Constants.target2y-o.y)*(Constants.target2y-o.y));
                
                if(o.choice==1){
                   o.goal[0]=((double)Constants.target1x-o.x)/distancetogoal1;
                o.goal[1]=((double)Constants.target1y-o.y)/distancetogoal1;
                }
                
                 if(o.choice==2){
                o.goal[0]=((double)Constants.target2x-o.x)/distancetogoal2;
                o.goal[1]=((double)Constants.target2y-o.y)/distancetogoal2;
                }
                 
                  if(o.choice==3){
                   o.goal[0]=0;
                o.goal[1]=0;
                }
            
                norm=Math.sqrt(Math.pow(o.goal[0],2)+Math.pow(o.goal[1],2));
                //System.out.print(norm+"");
                double socnorm=Math.sqrt(Math.pow(o.socheading[0],2)+Math.pow(o.socheading[1],2));
                if(norm!=0){
                o.goal[0]=o.goal[0]/norm;
                o.goal[1]=o.goal[1]/norm;
                }
                
                //System.out.print(""+socnorm);
                o.heading[0]=o.socheading[0]/socnorm+o.omega*o.goal[0];                                                            
                o.heading[1]=o.socheading[1]/socnorm+o.omega*o.goal[1];
                
                double oheadingnorm=Math.sqrt(Math.pow(o.heading[0],2)+Math.pow(o.heading[1],2));
                
                o.heading[0]=o.heading[0]/oheadingnorm;
                o.heading[1]=o.heading[1]/oheadingnorm;
                
                oheadingnorm=Math.sqrt(Math.pow(o.heading[0],2)+Math.pow(o.heading[1],2));
                //System.out.print(""+oheadingnorm);
                
                o.x=o.x+dt*(o.heading[0]*o.speed);
                o.y=o.y+dt*(o.heading[1]*o.speed);
                         
                if(o.choice==2)
                System.out.print(o.omega*o.goal[0]+"");
                          
                               
            }
            
             /*
            for (Object a: Pool.array){
               double norm=Math.sqrt(Math.pow(a.socheading[0],2)+Math.pow(a.socheading[1],2));
               double hnorm=Math.sqrt(Math.pow(a.heading[0],2)+Math.pow(a.heading[1],2));
                
               
               if(a.choice==1){
                a.goal[0]=(Constants.target1x-a.x);
                a.goal[1]=(Constants.target1y-a.y);
               }
               
               if(a.choice==2){
                   a.goal[0]=(Constants.target2x-a.x);
                a.goal[1]=(Constants.target2y-a.y);
               }
               
               if(a.choice==3){
                   a.goal[0]=0;
                   a.goal[1]=0;
               }
               double anorm=Math.sqrt(Math.pow(a.goal[0],2)+Math.pow(a.goal[1],2));
               if(anorm>=0.0001){
                   a.goal[0]=a.goal[0]/anorm;
                   a.goal[1]=a.goal[1]/anorm;
               }
               
               if(hnorm>0.0001){
               a.heading[0]=a.heading[0]/hnorm;
               a.heading[1]=a.heading[1]/hnorm;
               }
               if(norm>0.0001){
                a.socheading[0]=a.socheading[0]/norm;
                a.socheading[1]=a.socheading[1]/norm;
               }
            }*/
            
            
            
            
            
            //System.out.println("go");
              fish.GUI.Arena.paintImmediately(0, 0, 2000, 2000);
            fish.GUI.Arena.revalidate();
            //System.out.println("printing");
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
         
   
          
           
        }
        
      
            
                
             
        
        }
        }

  

