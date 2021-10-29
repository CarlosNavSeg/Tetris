
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Tetris extends JPanel {
    private Piece curPiece, nextPiece;
    private Board b;
    private int score;
    private int level;
    private int pieces;
    private int time;
    private int cp, np;
    private boolean gameOver;
    private Random rnd;

    public Tetris() {
        init();
    }

    private void init() {
        Sound.Tetris.loop();
        b = new Board();

        rnd = new Random();
        int n = rnd.nextInt(7) + 1;
        cp = n;
        nextPiece = new Piece(n);
        newPiece();

        score = 0;
        level = 1;
        pieces = 0;
        time = 1000;
        gameOver = false;

        // Create the main window
        JFrame mainWindow = new JFrame("Tetris");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(525, 605);
        setBackground( Color.DARK_GRAY );
        mainWindow.setVisible(true);
        mainWindow.add(this);

        mainWindow.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                    int code = e.getKeyCode();
                    switch(code) {
                        case KeyEvent.VK_UP:
                        KeyRotateLeftPressed();
                        break;
                        case KeyEvent.VK_DOWN:
                        KeyRotateRightPressed();
                        break;
                        case KeyEvent.VK_LEFT:
                        KeyMoveLeftPressed();
                        break;
                        case KeyEvent.VK_RIGHT:
                        KeyMoveRightPressed();
                        break;
                        case KeyEvent.VK_SPACE:
                        KeyMoveDownPressed();
                        break;
                    }
                }

                public void keyReleased(KeyEvent e) {
                }
            });

        // Make the falling piece fall every 'time' milliseconds
        new Thread() {
            @Override public void run() {
                while (true) {
                    try {
                        Thread.sleep(time);
                        if( !gameOver )
                            tic();
                    } catch ( InterruptedException e ) {}
                }
            }
        }.start();              
    }

    /************************************************************
     * This code is so that the Team 7 can perform the unit tests.
     * It must be properly implemented by Team 8
     ************************************************************/
    private void newPiece() {
        curPiece = new Piece(cp);
        curPiece.setPositionToOrigin();
        switch(cp){
                case(1):Sound.piece0.play();break;
                case(2):Sound.piece1.play();break;
                case(3):Sound.piece2.play();break;
                case(4):Sound.piece3.play();break;
                case(5):Sound.piece4.play();break;
                case(6):Sound.piece5.play();break;
                case(7):Sound.piece6.play();break;
            }
        pieces++;
        if((pieces%30)==0 && level < 10){
            level++;
            Sound.levelup.play();
            switch(level){
                case(2):time = 500;break;
                case(3):time = 300;break;
                case(4):time = 200;break;
                case(5):time = 175;break;
                case(6):time = 150;break;
                case(7):time = 125;break;
                case(8):time = 100;break;
                case(9):time = 75;break;
                case(10):time = 50;break;
            }
        }
        if(b.checkForCollision(curPiece)){
            gameOver = true;
            Sound.Tetris.stop();
            Sound.gameover.play();
        }
        np = rnd.nextInt(7)+1;
        nextPiece = new Piece(np);
        repaint();        
    }

    private synchronized void KeyMoveLeftPressed() {
        curPiece.moveLeft();
        Sound.move.play();
        if(b.checkForCollision(curPiece)){
            curPiece.moveRight();
        }
        repaint();
    }

    private synchronized void KeyMoveRightPressed() {
        curPiece.moveRight();
        Sound.move.play();
        if(b.checkForCollision(curPiece)){
            curPiece.moveLeft();
        }
        repaint();
    }

    private synchronized void KeyMoveDownPressed() {
        curPiece.moveDown();
        Sound.move.play();
        if(b.checkForCollision(curPiece)){
            curPiece.moveUp();
        }
        repaint();
    }

    private synchronized void KeyRotateRightPressed() {
        curPiece.rotateRight();
        if(b.checkForCollision(curPiece)){
            curPiece.rotateLeft();
            Sound.rotatefail.play();
        }
        repaint();
    }

    private synchronized void KeyRotateLeftPressed() {
        curPiece.rotateLeft();
        if(b.checkForCollision(curPiece)){
            curPiece.rotateRight();
            Sound.rotatefail.play();
        }
        repaint();
    }

    private synchronized void tic() {
        curPiece.moveDown();
        if(b.checkForCollision(curPiece)){
            Sound.drop.play();
            curPiece.moveUp();
            b.fixToGrid(curPiece);
            score = score+10;
            int f = b.deleteFilledLines();
            switch(f){
                case (0):break;
                case(1):score +=40;
                Sound.erase1.play();break;
                case(2):score +=100;
                Sound.erase2.play();break;
                case(3):score+=300;
                Sound.erase3.play();break;
                default:score+=1200;
                Sound.erase4.play();break;
            }
            cp = np;
            newPiece();
        }
        repaint();
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                   // Draw the window
        if( b != null ) b.draw(g);                 // Draw the board
        if( curPiece != null ) curPiece.draw(g);   // Draw the current piece
        if( nextPiece != null ) nextPiece.draw(g); // Draw the next piece
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 350, 425);
        g.drawString("Level: " + level, 350, 475);
        if( gameOver ) {
            g.setColor(Color.RED);
            g.drawString("Game Over", 350, 275);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        g.drawString("(c) Students ARA group 2017/18", 310, 540);
        g.drawString("ETSIT - UPV", 375, 565);
    }

    public static  void main(String[] args) {           
        Tetris theGame = new Tetris();
    }
}