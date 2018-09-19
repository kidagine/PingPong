import greenfoot.*;

public class EnemyPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public EnemyPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
    }

    /**
     * Act - do whatever the EnemyPaddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       tryChangeDirection();
       setLocation(getX() + dx, getY());        
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0) 
        {
            int randY = Greenfoot.getRandomNumber(281) + 120;
            int randZ = Greenfoot.getRandomNumber(70) + 30 ;
            GreenfootImage image = getImage();
            image.scale(randZ,20);
            setImage(image);
            setLocation(50,randY);
        }
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.WHITE);
        image.fill();
        setImage(image);
    }

}
