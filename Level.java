import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level extends Actor
{
    private int level = 0;
    public void act() 
    {
        setImage(new GreenfootImage("Level : " + level, 24, Color.WHITE, Color.BLACK));
    }    
    public void restartLevel()
    {
        level = 0;
    }
    public void addLevel()
    {
        level ++;
    }
}