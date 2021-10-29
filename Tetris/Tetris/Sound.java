
 

/**
 * @author Source from mike73 @dreamincode.net forum with some modifications.
 * @09/12/2017
 *
 * This class creates objects of type Sound, which are objects that open an AudioStream in order to
 * play some music and sound effects in the game when we call different methods inside this class also.
 */
import javax.sound.sampled.*;
public class Sound {
    /**
     * The Clip interface represents a special kind of data line whose audio data can be loaded prior 
     * to playback, instead of being streamed in real time. It is used to store the music prior to
     * execute it in the game
     */

    private Clip clip;

    /**
     * These are objects of type Sound created to store the different sounds in the game. There is
     * one for each track.
     */
    public static Sound Tetris = new Sound("se/Tetris.wav");
    public static Sound piece0 = new Sound("se/piece0.wav");
    public static Sound piece1 = new Sound("se/piece1.wav");
    public static Sound piece2 = new Sound("se/piece2.wav");
    public static Sound piece3 = new Sound("se/piece3.wav");
    public static Sound piece4 = new Sound("se/piece4.wav");
    public static Sound piece5= new Sound("se/piece5.wav");
    public static Sound piece6 = new Sound("se/piece6.wav");
    public static Sound levelup = new Sound("se/levelup.wav");
    public static Sound rotate = new Sound("se/rotate.wav");
    public static Sound gameover = new Sound("se/gameover.wav");
    public static Sound drop = new Sound("se/harddrop.wav");
    public static Sound move = new Sound("se/move.wav");
    public static Sound rotatefail = new Sound("se/rotfail.wav");
    public static Sound erase1 = new Sound("se/erase1.wav");
    public static Sound erase2 = new Sound("se/erase2.wav");
    public static Sound erase3 = new Sound("se/erase3.wav");
    public static Sound erase4 = new Sound("se/erase4.wav");
    /**
     * This is a constructor that creates the object Sound based on the route that is given as
     * a parameter through a String
     *
     * @param fileName String that indicates the route of the audio file we want to add.
     */
    public Sound (String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
            clip = AudioSystem.getClip(null);
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Instance method of type void that plays once the track included in the object Sound which
     * has been called.
     */
    public void play() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Instance method of type void that stops the track included in the object Sound which
     * has been called if it is being played.
     */
    public void stop(){
        if(clip == null) return;
        clip.stop();
    }

    /**
     * Instance method of type void that plays the track in a loop until the Sound.stop() method
     * is called.
     */
    public void loop() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Instance method of type boolean that checks if the object Sound that called the method is
     * playing or not
     * @return true if that sound is being played, false if it isn't.
     */
    public boolean isActive(){
        return clip.isActive();
    }
    
    
}