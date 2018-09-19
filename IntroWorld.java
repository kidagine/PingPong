import greenfoot.*;


public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        setBackground(new GreenfootImage("menu.jpg" ));
        background.setColor(Color.WHITE);
        background.drawString("Press Enter", WORLD_WIDTH / 2-30, WORLD_HEIGHT / 2);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
