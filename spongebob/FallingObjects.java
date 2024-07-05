import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FallingObjects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FallingObjects extends Actor
{
    /**
     * Act - do whatever the FallingObjects wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int dropSpeed=1;
    private boolean onBottom=false;
    
    public void act() 
    {
        // Add your action code here.
        fall();
    }    
  public void fall(){
      if (!onBottom) {
          this.setLocation(getX(),getY()+dropSpeed);
          onBottom=checkBottom();
      }
    }

  public boolean  checkBottom(){
      
      if (getY()>570){
          return true;
        }
        else {
        return false;
    }
 }
}