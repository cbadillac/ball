import java.util.*;
import java.io.*;

public class MyWorld {
   private PrintStream out;
   
   private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.

   public MyWorld(){
      this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
   }
   public MyWorld(PrintStream output){
      out = output;
      elements = new ArrayList<PhysicsElement>();     
   }

   public void addElement(PhysicsElement e) {
      elements.add(e);
   }

   public void printStateDescription(){
     String s ="Time\t";
     for (PhysicsElement e:elements)
       s+=e.getDescription() + "\t";
     out.println(s);
   }

   public void printState(double t){
	   String s;
	   s = String.valueOf(t) + "\t";
	   for (PhysicsElement e:elements){
		   if (e instanceof Ball){
			   s += String.valueOf(((Ball) e).getPosition()) + "\t";
		   }
	   }
	   System.out.println(s);
	   
	   
    //  to be coded by you
   }

   public void simulate (double delta_t, double endTime, double samplingTime) {  // simulate passing time
      double t=0;
      this.printStateDescription();
      //printState(t);
      while (t<endTime) {
         for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
           for (PhysicsElement e: elements)   // compute each element next state based on current global state  
              e.computeNextState(delta_t,this); // compute each element next state based on current global state
           for (PhysicsElement e: elements)  // for each element update its state. 
              e.updateState();     // update its state    
         }
         printState(t);
      }
   }   

   public Ball findCollidingBall(Ball me) {
	   double myFrontCollisionPosition = me.getPosition() + me.getRadius(); 
	   double myBackCollisionPosition  = me.getPosition() - me.getRadius();

	   for(PhysicsElement e: elements){
		   if ((!(e instanceof Ball )) && (me.equals(e)) )
			   continue;
		   
		   double yourFrontCollisionPosition = ((Ball) e).getPosition() + ((Ball) e).getRadius();
		   double yourBackCollisionPosition  = ((Ball) e).getPosition() - ((Ball) e).getRadius();
		   
		   boolean frontCollision = (yourBackCollisionPosition - myFrontCollisionPosition) < 0 && 
				   (yourBackCollisionPosition - myFrontCollisionPosition)>=-me.getRadius() ;
		   
		   boolean backCollision  = (myBackCollisionPosition - yourFrontCollisionPosition) < 0 &&
				   (myBackCollisionPosition - yourFrontCollisionPosition)>=-me.getRadius() ;
				  
		   if (frontCollision || backCollision){
			   return (Ball) e;
		   }
	   }
      // to be coded by you
	   return null;
   }  
} 
