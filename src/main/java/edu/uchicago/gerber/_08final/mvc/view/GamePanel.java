package edu.uchicago.gerber._08final.mvc.view;

import edu.uchicago.gerber._08final.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.mvc.controller.Game;
import edu.uchicago.gerber._08final.mvc.controller.Utils;
import edu.uchicago.gerber._08final.mvc.model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;


public class GamePanel extends Panel {

    // ==============================================================
    // FIELDS
    // ==============================================================
    private final Font fontNormal = new Font("SansSerif", Font.BOLD, 12);
    private final Font fontBig = new Font("SansSerif", Font.BOLD + Font.ITALIC, 36);
    private FontMetrics fontMetrics;
    private int fontWidth;
    private int fontHeight;

    //used to draw number of ships remaining
    private final Point[] pntShipsRemaining;

    //used for double-buffering
    private Image imgOff;
    private Graphics grpOff;


    // ==============================================================
    // CONSTRUCTOR
    // ==============================================================

    public GamePanel(Dimension dim) {

        GameFrame gameFrame = new GameFrame();

        gameFrame.getContentPane().add(this);

        // Robert Alef's awesome falcon design
        List<Point> listShip = new ArrayList<>();
        listShip.add(new Point(0,9));
        listShip.add(new Point(-1, 6));
        listShip.add(new Point(-1,3));
        listShip.add(new Point(-4, 1));
        listShip.add(new Point(4,1));
        listShip.add(new Point(-4,1));
        listShip.add(new Point(-4, -2));
        listShip.add(new Point(-1, -2));
        listShip.add(new Point(-1, -9));
        listShip.add(new Point(-1, -2));
        listShip.add(new Point(-4, -2));
        listShip.add(new Point(-10, -8));
        listShip.add(new Point(-5, -9));
        listShip.add(new Point(-7, -11));
        listShip.add(new Point(-4, -11));
        listShip.add(new Point(-2, -9));
        listShip.add(new Point(-2, -10));
        listShip.add(new Point(-1, -10));
        listShip.add(new Point(-1, -9));
        listShip.add(new Point(1, -9));
        listShip.add(new Point(1, -10));
        listShip.add(new Point(2, -10));
        listShip.add(new Point(2, -9));
        listShip.add(new Point(4, -11));
        listShip.add(new Point(7, -11));
        listShip.add(new Point(5, -9));
        listShip.add(new Point(10, -8));
        listShip.add(new Point(4, -2));
        listShip.add(new Point(1, -2));
        listShip.add(new Point(1, -9));
        listShip.add(new Point(1, -2));
        listShip.add(new Point(4,-2));
        listShip.add(new Point(4, 1));
        listShip.add(new Point(1, 3));
        listShip.add(new Point(1,6));
        listShip.add(new Point(0,9));

        pntShipsRemaining = listShip.toArray(new Point[0]);

        gameFrame.pack();
        initFontInfo();
        gameFrame.setSize(dim);
        //change the name of the game-frame to your game name
        gameFrame.setTitle("Game Base");
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        setFocusable(true);
    }


    // ==============================================================
    // METHODS
    // ==============================================================

    private void drawFalconStatus(final Graphics graphics){

        graphics.setColor(Color.white);
        graphics.setFont(fontNormal);

        //draw score always
        graphics.drawString("Score :  " + CommandCenter.getInstance().getScore(), fontWidth, fontHeight);

        //draw the level upper-left corner always
//        String levelText = "Level: " + CommandCenter.getInstance().getLevel();
//        graphics.drawString(levelText, 20, 30); //upper-left corner

        //build the status string array with possible messages in middle of screen
        List<String> statusArray = new ArrayList<>();
//        if (CommandCenter.getInstance().getLevel() >= 0) statusArray.add(levelText);
        if (CommandCenter.getInstance().getFalcon().isMaxSpeedAttained()) statusArray.add("WARNING - SLOW DOWN");
        if (CommandCenter.getInstance().getFalcon().getNukeMeter() > 0) statusArray.add("PRESS N for NUKE");



        //draw the statusArray strings to middle of screen
        if (!statusArray.isEmpty())
            displayTextOnScreen(graphics, statusArray.toArray(new String[0]));



    }

    //this is used for development, you can remove it from your final game
    private void drawNumFrame(Graphics g) {
        g.setColor(Color.white);
        g.setFont(fontNormal);
        g.drawString("FRAME :  " + CommandCenter.getInstance().getFrame(), fontWidth,
                Game.DIM.height  - (fontHeight + 22));

    }

    private void drawMeters(Graphics g){

        //will be a number between 0-100 inclusive
//        int shieldValue =   CommandCenter.getInstance().getFalcon().getShield() / 2;
        int nukeValue = CommandCenter.getInstance().getFalcon().getNukeMeter() /6;

//        drawOneMeter(g, Color.CYAN, 1, shieldValue);
        drawOneMeter(g, Color.YELLOW, 2, nukeValue);


    }

    private void drawOneMeter(Graphics g, Color color, int offSet, int percent) {

        int xVal = Game.DIM.width - (100 + 120 * offSet);
        int yVal = Game.DIM.height - 45;

        //draw meter
        g.setColor(color);
        g.fillRect(xVal, yVal, percent, 10);

        //draw gray box
        g.setColor(Color.DARK_GRAY);
        g.drawRect(xVal, yVal, 100, 10);
    }


    public void update(Graphics g) {

        // The following "off" vars are used for the off-screen double-buffered image.
        imgOff = createImage(Game.DIM.width, Game.DIM.height);
        //get its graphics context
        grpOff = imgOff.getGraphics();

        //Fill the off-screen image background with black.
        grpOff.setColor(Color.BLACK);
        grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);

        //this is used for development, you may remove drawNumFrame() in your final game.
        drawNumFrame(grpOff);

        if (CommandCenter.getInstance().isGameOver()) {
            displayTextOnScreen(grpOff,
                    "GAME OVER",
                    "use the arrow keys to turn and thrust",
                    "use the space bar to fire",
                    "'S' to Start",
                    "'P' to Pause",
                    "'Q' to Quit",
                    "'M' to toggle music"

            );
        } else if (CommandCenter.getInstance().isPaused()) {

            displayTextOnScreen(grpOff, "Game Paused");

        }

        //playing and not paused!
        else {

            moveDrawMovables(grpOff,
                    CommandCenter.getInstance().getMovBackgrounds(),
                    CommandCenter.getInstance().getMovFoes(),
                    CommandCenter.getInstance().getMovFloaters(),
                    CommandCenter.getInstance().getMovFriends(),
                    CommandCenter.getInstance().getMovDebris(),
                    CommandCenter.getInstance().getMovForegrounds()
                    );


            drawNumberShipsRemaining(grpOff);
            drawMeters(grpOff);
            drawFalconStatus(grpOff);
            drawStarCollecting(grpOff);

        }

        //after drawing all the movables or text on the offscreen-image, copy it in one fell-swoop to graphics context
        // of the game panel, and show it for ~40ms. If you attempt to draw sprites directly on the gamePanel, e.g.
        // without the use of a double-buffered off-screen image, you will see flickering.
        g.drawImage(imgOff, 0, 0, this);
    }


    //this method causes all sprites to move and draw themselves
    @SafeVarargs
    private final void moveDrawMovables(final Graphics g, List<Movable>... teams) {

        BiConsumer<Movable, Graphics> moveDraw = (mov, grp) -> {
            mov.move();
            mov.draw(grp);
        };


        Arrays.stream(teams) //Stream<List<Movable>>
                //we use flatMap to flatten the teams (List<Movable>[]) passed-in above into a single stream of Movables
                .flatMap(Collection::stream) //Stream<Movable>
                .forEach(m -> moveDraw.accept(m, g));


    }




    // Draw the number of falcons remaining on the bottom-right of the screen.
    private void drawNumberShipsRemaining(Graphics g) {
        int numFalcons = CommandCenter.getInstance().getNumFalcons();
        while (numFalcons > 0) {
            drawOneShip(g, numFalcons--);
        }
    }

    private void drawStarCollecting(Graphics g){

        int xVal = 345;
        int yVal =  90;
        BufferedImage img = loadGraphic("/imgs/UI/glassPanel_projection.png");


        g.setFont(fontNormal);
        g.drawString("LEVEL :  " + (CommandCenter.getInstance().getLevel()+1), 275,
                50);
        g.drawString(CommandCenter.getInstance().numStar + " / " +
                        CommandCenter.getInstance().maxStar, 730,
                50);
        if (img != null){
            Graphics2D g2d = (Graphics2D) g.create();

            // Calculate the required width to contain all squares
            int numSquares = CommandCenter.getInstance().maxStar;
            int squareWidth = 40; // Assuming each square has a width of 20 pixels
            int padding = 10; // Add some padding to the panel width
            int newWidth = numSquares * squareWidth + padding;

            // Calculate the scale factor based on the new width and the original image width
            double scale = (double)newWidth / img.getWidth();

            // Create a new AffineTransform instance for scaling
            AffineTransform at = new AffineTransform(g2d.getTransform());
            at.scale(scale, 0.5);
            g2d.setTransform( at);
            // Draw the image with the scaling transformation applied
            g2d.drawImage(img, xVal - newWidth / 2 , yVal - img.getHeight() / 2, null);

            g2d.dispose();
        }
        drawStarIcon(g);
        int num = CommandCenter.getInstance().numStar;
        int max = CommandCenter.getInstance().maxStar;
        for(int i=1; i<=max; ++i){
            if(i<=num){
                drawOneYellowSquare(g, i);
            }else{
                drawOneGreySquare(g, i);
            }
        }

    }


    private void drawStarIcon(Graphics g){

        int xVal = 370;
        int yVal =  45;
        BufferedImage img = loadGraphic("/imgs/powerUp/star_gold.png");
        if(img!=null){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(img, xVal - img.getWidth() / 2, yVal - img.getHeight() / 2, null);
        }

    }


    private void drawOneYellowSquare(Graphics g, int offSet){

        int xVal = 390 + (20 * offSet);
        int yVal =  45;
        BufferedImage img = loadGraphic("/imgs/UI/squareYellow.png");
        if (img != null){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(img, xVal - img.getWidth() / 2, yVal - img.getHeight() / 2, null);
        }
    }

    private void drawOneGreySquare(Graphics g, int offSet){

        int xVal = 390 + (20 * offSet);
        int yVal =  45;
        BufferedImage img = loadGraphic("/imgs/UI/square_shadow.png");
        if (img != null){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(img, xVal - img.getWidth() / 2, yVal - img.getHeight() / 2, null);
        }

    }



    private void drawOneShip(Graphics g, int offSet) {


        //rotate the ship 90 degrees
        double degrees90 = 90.0;
        int radius = 15;
        int xVal = 40 * offSet;
        int yVal =  45;

        BufferedImage img = loadGraphic("/imgs/playerShip/playerLife1_blue.png");
        if (img != null){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(img, xVal - img.getWidth() / 2, yVal - img.getHeight() / 2, null);
        }

    }

    private BufferedImage loadGraphic(String imagePath) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(Sprite.class.getResourceAsStream(imagePath)));
        }
        catch (IOException e) {
            e.printStackTrace();
            bufferedImage = null;
        }
        return bufferedImage;
    }

    private void initFontInfo() {
        Graphics g = getGraphics();            // get the graphics context for the panel
        g.setFont(fontNormal);                        // take care of some simple font stuff
        fontMetrics = g.getFontMetrics();
        fontWidth = fontMetrics.getMaxAdvance();
        fontHeight = fontMetrics.getHeight();
        g.setFont(fontBig);                    // set font info
    }


    // This method draws some text to the middle of the screen
    private void displayTextOnScreen(final Graphics graphics, String... lines) {

        //AtomicInteger is safe to pass into a stream
        final AtomicInteger spacer = new AtomicInteger(0);
        Arrays.stream(lines)
                .forEach(str ->
                            graphics.drawString(str, (Game.DIM.width - fontMetrics.stringWidth(str)) / 2,
                                    Game.DIM.height / 4 + fontHeight + spacer.getAndAdd(40))

                );


    }


}
