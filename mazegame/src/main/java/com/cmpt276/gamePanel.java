package com.cmpt276;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class to provide a GUI for the user
 */
public class gamePanel extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 650;
    private static final int DELAY = 25;
    private static final int TIMER_DELAY = 1000;
    private int sanityStat;

    private static boolean gameRunning = false;
    private static boolean gameWon = false; // set it to true when gameRunning is FALSE and the player has won
    private static Player mainPlayer = new Player();
    private static Controller keyMovement = new Controller(mainPlayer);

    private static JLabel sanityLabel = new JLabel(); // Display Current sanity
    private static JLabel timerLabel; // Current timer
    private static JLabel gameOverLabel;
    private AudioInputStream audioInputStream;
    private Clip clip;

    private static int mazeCellW = 145;// maze cell's width and height

    // Timers
    private static Timer startTimer;

    // In game progress timer
    private static Timer inGameTimer = new Timer(TIMER_DELAY, new ActionListener() {
        // Format Timer
        private int elapsedTime = 0;
        private int seconds = 0;
        private int minutes = 0;
        private String secondString = String.format("%02d", seconds);
        private String minutesString = String.format("%02d", minutes);

        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + TIMER_DELAY;
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            secondString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            timerLabel.setText("Timer: " + minutesString + ":" + secondString);
        }
    });

    // Special Timer to deal with despawn and spawning of SpecialPickup
    private static long specialSeconds = 0;
    private Timer specialTimer = new Timer(TIMER_DELAY, new ActionListener() {
        // Format Timer
        public void actionPerformed(ActionEvent e) {
            specialSeconds++;
        }
    });

    // lists of in-game objects
    private static ArrayList<MobileEnemy> mobileEnemies = new ArrayList<>();
    private static ArrayList<StaticEnemy> staticEnemies = new ArrayList<>();
    private static ArrayList<Pickup> normalPickups = new ArrayList<>();
    private static ArrayList<Barrier> barriers = new ArrayList<>();
    private static ArrayList<SpecialPickup> specialPickups = new ArrayList<>();
    private static ArrayList<Integer> xCord = new ArrayList<>(); // Coord list for special pickups
    private static ArrayList<Integer> yCord = new ArrayList<>();
    private static ExitTile finishTile;
    private static int numPickupsNeededToWin;
    private static boolean canViewDimensions = false;
    private static ImageObserver ensureCorrectImgDimensions = new ImageObserver() {
        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            canViewDimensions = true;
            return false;
        }
    };

    /**
     * Default constructor. Creates an instance of the game.
     */
    gamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.gray);
        this.addKeyListener(keyMovement);

        gameRunning = true;

        initializeGameLogicElements();
        createGameobjects();

        playBackgroundMusic();
    }

    private void initializeGameLogicElements() {
        startTimers();
        initializeSanityLabel();
        initializeTimerLabel();
        initializeSpecialPickupLocations();
    }

    private void createGameobjects() {
        createNormalPickups();
        createMobileEnemies();
        createStaticEnemies();
        createInnerMaze();
        createWallBoundary();
        createInternalBarriers();
        createExitTile();
    }

    private void startTimers() {
        startTimer = new Timer(DELAY, this);
        startTimer.start();
        inGameTimer.start();
        specialTimer.start();
    }

    private void initializeSanityLabel() {
        this.setLayout(null);
        this.sanityStat = mainPlayer.getSanity();
        sanityLabel = new JLabel("Sanity: " + this.sanityStat);
        sanityLabel.setLocation(35, -5);
        sanityLabel.setSize(100, 100);
        sanityLabel.setFont(new Font("Serif", Font.BOLD, 20));
        sanityLabel.setForeground(Color.RED);
        this.add(sanityLabel);
    }

    private void initializeTimerLabel() {
        timerLabel = new JLabel("Timer: 00:00");
        timerLabel.setLocation(35, 20);
        timerLabel.setSize(500, 100);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        timerLabel.setForeground(Color.RED);
        this.add(timerLabel);
    }

    private void initializeSpecialPickupLocations() {
        // add top left, middle left, middle, top right, and bottom right coordinates
        xCord.addAll(Arrays.asList(80, 80, 380, 670, 490));
        yCord.addAll(Arrays.asList(120, 340, 380, 250, 540));
    }

    /**
     * Instantiates several MobileEnemy objects throughout the scene.
     */
    public static void createMobileEnemies() {
        int[][] mobileEnemyPositions = new int[][] { { 335, 270 }, { 250, 100 } };

        for (int[] position : mobileEnemyPositions) {
            mobileEnemies.add(new MobileEnemy(position[0], position[1]));
        }
    }

    /**
     * Instantiates several StaticEnemy objects throughout the scene.
     */
    public static void createStaticEnemies() {
        final int staticEnemyImpact = -15;
        int[][] staticEnemyPositions = new int[][] { { 510, 45 }, { 150, 250 }, { 400, 560 }, { 130, 395 },
                { 695, 400 }, { 680, 120 }, { 410, 65 } };

        for (int[] position : staticEnemyPositions) {
            staticEnemies.add(new StaticEnemy(position[0], position[1], staticEnemyImpact));
        }
    }

    /**
     * Instantiates several Pickup objects throughout the scene.
     */
    public static void createNormalPickups() {

        final int pickupSanityImpact = 5;
        int[][] pickupPositions = new int[][] { { 500, 140 }, { 280, 200 }, { 350, 580 }, { 700, 475 }, { 120, 135 } };

        for (int[] position : pickupPositions) {
            normalPickups.add(new Pickup(position[0], position[1], pickupSanityImpact));
        }

        numPickupsNeededToWin = normalPickups.size();
    }

    /**
     * Function to create special pickups with set positions and add to
     * ArrayList for Special Pickups
     * 
     */
    public static void createSpecialPickups() {
        int coord = (int) Math.floor((Math.random() * Math.min(xCord.size(), yCord.size())));
        int xPos = xCord.get(coord);
        int yPos = yCord.get(coord);
        specialPickups.add(new SpecialPickup(xPos, yPos, 10));
    }

    /**
     * Creates one fixed maze inside of play area.
     * Using mazeCellW value, divide the game screen by cell (145 width and height).
     * Determines the number of walls needed to create a maze and the desired layout.
     * Use for-loop to fill out the desired side with the wall.
     * Maze is also a type of barrier, so they are created using an Barrier array.
     */
    public static void createInnerMaze() {
        int boundaryW = 35;
        int rnum = 4, cnum = 4;
        int wallSize = 10;
        Barrier[] mazeB = new Barrier[4];        
        //0: no wall, 1:bottom wall, 2:right wall, 3: bottom and right wall
        int[][] mazeCell={{1,1,2,1},{0,3,2,0},{1,0,1,3},{0,2,0,0}};//2d array

        // create bottom side maze
        for (int c = 0; c < cnum; c++) {
            for (int r = 0; r < rnum; r++) {
                if (mazeCell[r][c]==(1)||mazeCell[r][c]==(3)) {
                    for (int i = mazeCellW * c; i < mazeCellW * (c + 1); i += wallSize) {
                        mazeB[r] = new Barrier(boundaryW + i, boundaryW + mazeCellW * (r + 1), BarrierType.Wall);
                        mazeB[r].setDimensions(wallSize, wallSize);
                        barriers.add(mazeB[r]);
                    }
                }
            }
        }
        // create right side maze
        for (int r = 0; r < rnum; r++) {
            for (int c = 0; c < cnum; c++) {
                if (mazeCell[r][c]==(2)||mazeCell[r][c]==(3)) {
                    for (int i = mazeCellW * r; i < mazeCellW * (r + 1); i += wallSize) {
                        mazeB[r] = new Barrier(boundaryW + mazeCellW * (c + 1), boundaryW + i, BarrierType.Wall);
                        mazeB[r].setDimensions(wallSize, wallSize);
                        barriers.add(mazeB[r]);
                    }
                }
            }
        }
    }

    /**
     * Creates a surrounding wall around the play area.
     */
    public static void createWallBoundary() {
        int wallSize = 35;
        for (int i = Controller.leftBound; i < Controller.rightBound + wallSize; i += wallSize) {
            // Top Wall
            Barrier topWall = new Barrier(i, 0, BarrierType.Wall);
            topWall.setDimensions(wallSize, wallSize);
            barriers.add(topWall);

            // Bottom Wall
            Barrier bottomWall = new Barrier(i + wallSize * 2, Controller.bottomBound, BarrierType.Wall);
            bottomWall.setDimensions(wallSize, wallSize);
            barriers.add(bottomWall);
        }

        for (int i = Controller.topBound + wallSize; i < Controller.bottomBound - wallSize; i += wallSize) {
            // Left Wall
            Barrier leftWall = new Barrier(0, i, BarrierType.Wall);
            leftWall.setDimensions(wallSize, wallSize);
            barriers.add(leftWall);

            // Right Wall
            Barrier rightWall = new Barrier(Controller.rightBound, i + wallSize * 2, BarrierType.Wall);
            rightWall.setDimensions(wallSize, wallSize);
            barriers.add(rightWall);
        }
    }

    /**
     * Create barriers within the game
     */
    public static void createInternalBarriers() {
        int[][] bookshelfPositions = new int[][] { { 260, 455 }, { 560, 115 } };
        int[][] floorSignPositions = new int[][] { { 180, 330 }, { 630, 530 } };

        for (int[] position : bookshelfPositions) {
            barriers.add(new Barrier(position[0], position[1], BarrierType.Bookshelf));
        }

        for (int[] position : floorSignPositions) {
            barriers.add(new Barrier(position[0], position[1], BarrierType.FloorSign));
        }
    }

    /**
     * Creates an ExitTile in the top right corner of the scene.
     */
    public static void createExitTile() {
        finishTile = new ExitTile(770, 50);
    }

    /**
     * Returns a boolean value indicating if there would be a barrier object within
     * the passed in object.
     * 
     * @param yourTargetBounds a Rectangle indicating the object's size and target
     *                         location
     * @param forceObserver    whether to force usage of an ImageObserver to
     *                         guarantee accurate results; will take
     *                         extra time on the scale of tens of milliseconds
     * @return boolean true if there are no barriers overlapping the proposed area;
     *         false otherwise.
     */
    // public static boolean checkNoBarrier(int[] newPosition, int[] yourBounds,
    // boolean forceObserver) {
    public static boolean checkNoBarrier(Rectangle yourTargetBounds, boolean forceObserver) {
        // newPosition is the proposed position of the unit
        // check screen boundaries
        if (yourTargetBounds.x < Controller.leftBound || yourTargetBounds.x > Controller.rightBound
                || yourTargetBounds.y < Controller.topBound || yourTargetBounds.y > Controller.bottomBound) {
            return false;
        }
        // check barrier boundaries
        boolean noBarrier = true;
        for (Barrier barrierItem : barriers) {
            Rectangle barrierBound;
            if (forceObserver) {
                prepImgForViewing(barrierItem);
            }
            barrierBound = barrierItem.getCurrBoundaries();
            if (yourTargetBounds.intersects(barrierBound)) {
                noBarrier = false;
                break;
            }
        }
        return noBarrier;
    }

    /**
     * Checks for potential interaction between the main player and any Entities in
     * the stage.
     * If the player collides with any StaticEnemies, the player's sanity is
     * decreased and the StaticEnemy is removed.
     * If the player collides with any Pickups, the player's sanity is increased,
     * their pickup count is incremented, and
     * the pickup is removed.
     * If the player collides with a MobileEnemy, the game terminates.
     *
     * @param forceObserver whether to force usage of an ImageObserver to guarantee
     *                      accurate results; will take
     *                      extra time on the scale of tens of milliseconds
     * @return returns true if there were no errors getting dimensions of all
     *         objects; false otherwise.
     */
    public static synchronized boolean checkCollision(boolean forceObserver) {
        assert (mainPlayer != null);
        if (forceObserver) {
            prepImgForViewing(mainPlayer);
        }

        ArrayList<Entity> allEntities = new ArrayList<Entity>();
        allEntities.addAll(mobileEnemies);
        allEntities.addAll(normalPickups);
        allEntities.addAll(staticEnemies);
        allEntities.addAll(specialPickups);

        boolean ret = true;
        boolean collisionDetected = false;
        Rectangle playerBound = mainPlayer.getCurrBoundaries();

        for (Entity entity : allEntities) {
            assert (entity != null);
            if (forceObserver) {
                prepImgForViewing(entity);
            }
            Rectangle barrierBound = entity.getCurrBoundaries();
            ret = (barrierBound.getWidth() == -1 || barrierBound.getHeight() == -1) ? false : ret;
            if (!collisionDetected && playerBound.intersects(barrierBound)) {
                collisionDetected = true;
                if (entity instanceof MobileEnemy) {
                    gameRunning = false; // Collided with enemy, exit game!
                    continue;
                }
                mainPlayer.updateSanity(entity.getSanityImpact());
                if (entity instanceof StaticEnemy) {
                    staticEnemies.remove(entity);
                } else if (entity instanceof SpecialPickup) { // SpecialPickup extends Pickup, test before pickup
                    specialPickups.remove(entity);
                    mainPlayer.incrementSpecialPickup();
                    specialSeconds = 0;
                } else { // Pickup
                    mainPlayer.incrementPickup();
                    normalPickups.remove(entity);
                }
                gamePanel.sanityLabel.setText("Sanity: " + mainPlayer.getSanity()); // update sanity score on screen
            }
        }
        return ret;
    }

    /**
     * Returns a boolean indicating whether there would be an overlap between any
     * enemy, and the object with location
     * and dimensions specified in parameters. The calling
     * MobileEnemy must pass them self as a pointer;
     * otherwise, this function will always return false.
     * 
     * @param newPosition    the proposed position for the object testing for
     *                       collisions
     * @param yourDimensions the dimensions of the object testing for collisions
     * @param you            a pointer to the calling MobileEnemy
     * @return a boolean; true for no overlapping entities; false for any overlap
     */
    public static boolean noOverlappingEntities(int[] newPosition, int[] yourDimensions, GameObject you,
            boolean forceObserver) {
        // make a rectangle with the sprite's proposed position
        Rectangle proposedPosition = new Rectangle(newPosition[0], newPosition[1], yourDimensions[0],
                yourDimensions[1]);
        // check if it would step on any mobile enemies
        for (MobileEnemy me : mobileEnemies) {
            if (forceObserver) {
                prepImgForViewing(me);
            }
            Rectangle mobileEnemyBounds = me.getCurrBoundaries();
            if (proposedPosition.intersects(mobileEnemyBounds) && you != me) {
                return false;
            }
        }
        // if not, we're good to return true; we have no overlapping entities
        return true;
    }

    /**
     * Draws all objects in the scene in their current locations.
     * This function is called once every frame update.
     * 
     * @param graphic the graphic object used in drawing
     */
    public void createObjects(Graphics graphic) {
        if (gameRunning) {
            // Draw the main player and finish tile
            graphic.drawImage(finishTile.sprite, finishTile.x, finishTile.y, this);
            graphic.drawImage(mainPlayer.sprite, mainPlayer.x, mainPlayer.y, this);

            // Draw the mobile and static enemies
            for (MobileEnemy enemy : mobileEnemies) {
                graphic.drawImage(enemy.sprite, enemy.x, enemy.y, this);
            }
            for (StaticEnemy s : staticEnemies) {
                graphic.drawImage(s.sprite, s.x, s.y, this);
            }

            // Draw the normal pickups
            for (Pickup p : normalPickups) {
                graphic.drawImage(p.sprite, p.x, p.y, this);
            }

            // Draw the barriers
            for (Barrier b : barriers) {
                graphic.drawImage(b.sprite, b.x, b.y, this);
            }
            repaint();
        }
    }

    /**
     * Special function for drawing special pickups as special pickups aren't always
     * drawn
     * and only need to be drawn when created for a certain time.
     * 
     * @param graphic the graphic to be drawn
     */
    public void drawSpecialPickups(Graphics graphic) {
        if (gameRunning) {
            SpecialPickup s = specialPickups.get(0);
            graphic.drawImage(s.sprite, s.x, s.y, this);
            repaint();
        }
    }

    /**
     * Enables background music for the current panel.
     * Music will run until stopped via actionPerformed.
     */
    public void playBackgroundMusic() {
        try {
            URL audioFile = this.getClass().getResource("/HenesysField.wav");
            if (audioFile == null) {
                System.out.println("audioFile unavailable");
                return;
            }
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Renders all objects currently in the scene.
     */
    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        createObjects(graphic);
        if (specialPickups.size() > 0) {
            drawSpecialPickups(graphic);
        }
    }

    /**
     * Called every frame.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // After each time delay, perform the following actions
        // Game is still running
        if (gameRunning) {
            keyMovement.keyProcess();
            for (MobileEnemy m : mobileEnemies) {
                m.moveToPlayer(mainPlayer);
            }
            if (mainPlayer.getSanity() < 0) {
                gameRunning = false;
            }
            if (finishTile.checkForFinish(mainPlayer, numPickupsNeededToWin)) {
                gameRunning = false;
                gameWon = true;
            }
            if (specialPickups.size() == 0 && specialSeconds == 12) {
                specialSeconds = 0;
                createSpecialPickups();
            }
            if (specialSeconds == 10 && specialPickups.size() > 0) {
                specialSeconds = 0;
                specialPickups.remove(0);
            }
        } else {
            // Game has terminated
            inGameTimer.stop();
            this.setBackground(Color.BLACK);
            gameOverLabel = new JLabel();
            gameOverLabel.setSize(100, 100);
            gameOverLabel.setFont(new Font("Serif", Font.BOLD, 20));
            gameOverLabel.setForeground(Color.CYAN);
            clip.stop();

            if (gameWon) {
                gameOverLabel.setLocation(330, 215);
                sanityLabel.setLocation(330, 250);
                timerLabel.setLocation(330, 285);
                gameOverLabel.setText("Game Won");
            } else {
                // game lost
                this.remove(sanityLabel);
                this.remove(timerLabel);
                gameOverLabel.setLocation(330, 250);
                gameOverLabel.setText("Game Over");
            }
            this.add(gameOverLabel);
        }
    }

    private static synchronized void prepImgForViewing(GameObject objectToView) {
        Rectangle bounds = objectToView.getCurrBoundaries(ensureCorrectImgDimensions);
        if (bounds.getWidth() != -1 && bounds.getHeight() != -1) {
            return; // we already have the img dimensions
        }
        while (!canViewDimensions) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException ie) {
                System.out.println("thread.sleep interrupted.");
            }
        }
        canViewDimensions = false;
    }

    /**
     * Adds a possible set of coordinates (x, y) to the spawn options for
     * specialPickups.
     * 
     * @param x the target x coordinate
     * @param y the target y coordinate
     */
    public static void addCoordsToSpecialPickup(int x, int y) {
        xCord.add(x);
        yCord.add(y);
    }

    /**
     * Get function for gameRunning boolean
     * 
     * @return whether the game is in a running state
     */
    public static boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Get function for user's Player object.
     * 
     * @return the main Player
     */
    public static Player getMainPlayer() {
        return mainPlayer;
    }

    /**
     * Clears enemies, pickups, barriers, finish tile;
     * clears all possible spawn locations for specialPickups;
     * creates a new player and sets game to run.
     */
    public static void resetGameAttributes() {
        mobileEnemies.clear();
        staticEnemies.clear();
        normalPickups.clear();
        specialPickups.clear();
        xCord.clear();
        yCord.clear();
        barriers.clear();
        finishTile = null;
        mainPlayer = new Player();
        keyMovement = new Controller(mainPlayer);
        gameRunning = true;
    }
}
