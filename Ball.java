                        
                        import greenfoot.*;
                        
                        
                        public class Ball extends SmoothMover
                        {
                            private static final int BALL_SIZE = 25;
                            private static final int BOUNCE_DEVIANCE_MAX = 5;
                            private static final int STARTING_ANGLE_WIDTH = 90;
                            private static final int DELAY_TIME = 100;
                        
                            public int level = 0;
                            private int speed;
                            private int checkPaddleHits = 0;
                            private int delay;
                            private int delayCollision = 0;
                            private int lives = 3;
                            private boolean hasBouncedHorizontally;
                            private boolean hasBouncedVertically;
                            private boolean hasBouncedPaddle;
                            private boolean hitFloor = false;
                            

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        init();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.WHITE);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddle();
            checkBounceOffEnemyPaddle();
            checkRestart();
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                Greenfoot.playSound("paddle.wav");
                revertHorizontally();
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (! hasBouncedVertically)
            {
                Greenfoot.playSound("paddle.wav");
                revertVertically();
                hitFloor = false;
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }
    
    private void setLevel()
    {
            checkPaddleHits++;
            if (checkPaddleHits % 10 == 0)
            {
            speed ++;
            World myWorld = getWorld();
            PingWorld pingworld = (PingWorld)myWorld;
            Level level = pingworld.getLevel();
            level.addLevel();
            }
    }
    
    private void checkBounceOffPaddle()
    {
        Actor paddle = getOneIntersectingObject(Paddle.class);

        if (paddle != null)
        {
            if (! hasBouncedPaddle)
            {
                Greenfoot.playSound("paddle.wav");
                revertVertically();
                setLevel();
                hitFloor = true;
            }
        }
        else
        {
            hasBouncedPaddle = false;
        }
        
    }
    
    private void checkBounceOffEnemyPaddle()
    {
        Actor enemy = getOneIntersectingObject(EnemyPaddle.class);
        
        if (enemy != null){
            if (!hasBouncedPaddle && hitFloor == true   )
            {
            Greenfoot.playSound("paddle.wav");
            revertVertically();
            }
        }
        else
        {
            hasBouncedPaddle = false;
        }
    }
    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            Greenfoot.playSound("paddle.wav");
            checkGameOver();
            init();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }

    public void checkGameOver()
    {
    lives --;
    if (lives == 0)
        {
         Greenfoot.playSound("paddle.wav");
         Greenfoot.setWorld(new GameOverWorld());
        }
    }
    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    

}
