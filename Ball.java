import java.util.*;

public class Ball extends PhysicsElement {
   private static int  id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private double pos_t;     // current position at time t
   private double pos_tPlusDelta;  // next position in delta time in future
   private double speed_t;   // speed at time t
   private double speed_tPlusDelta;   // speed in delta time in future
   
   private Ball(){   // nobody can create a block without state
     this(1.0,0.1,0,0);
   }
   public Ball(double mass, double radius, double position, double speed){
      super(id++);
      this.mass = mass;
      pos_t = position;
      speed_t = speed;
      this.radius = radius;
   }
   public double getRadius() {
      return radius;
   }
   public double getSpeed() {
      return speed_t;
   }
   
   public double getMass(){
	   return mass;
   }
   
   public double getPosition(){
	   return pos_t;
   }
   
   public void computeNextState(double delta_t, MyWorld world) {
     Ball b;  // Assumption: on collision we only change speed.   
     if ((b=world.findCollidingBall(this))!= null){ /* elastic collision */
        speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
        pos_tPlusDelta = pos_t + speed_tPlusDelta*delta_t;
     } else {
        speed_tPlusDelta = speed_t;
        pos_tPlusDelta = pos_t + speed_t*delta_t;
     }
   }
   public boolean collide(Ball b) {
	   
   // to be coded by you
	   return false;
    }
   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }
   
   public String getState(){
	   return " " + pos_t + " " + speed_t;
   }
   public String getDescription() {
   // to be coded by you
	   return "Ball_" + String.valueOf(getId()) + ":x";
   }
}
