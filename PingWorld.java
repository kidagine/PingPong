import greenfoot.*;


public class PingWorld extends World
{
    Level level = new Level();
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    public PingWorld()
    { 
        // calling the other constructor with gameStarted = false.
        this(false);       
    }
    public Level getLevel()
    {       
        return level;
    }
    
    public PingWorld(boolean gameStarted)
    {
        
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            background.fillRect(0, 0, getWidth(), getHeight());
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
            addObject(level, 100, 40);
            int randX = Greenfoot.getRandomNumber(400) + 50;
            int randY = Greenfoot.getRandomNumber(281) + 120;
            int randZ = Greenfoot.getRandomNumber(70) + 30 ;
            addObject(new EnemyPaddle(randZ,20), randX, randY); 
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
        
    }
   
}
