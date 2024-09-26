package SFX;

import game.world.World;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public Clip walkingClip;

    public Sound(){
        walkingClip = getWalkingClip();
    }

    public void update(){
        //plays the player's walking sound
        if (World.player.isWalking){
            walkingClip.start();
        }
        if (World.player.isWalking && !walkingClip.isRunning()) { //if the Player is walking but the clip has ended, restart the clip
            walkingClip.setFramePosition(0);
            walkingClip.start();
        }
    }
    public void playObjectRetrieved(){
        Clip clip = getClip("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\sound\\objectretrieved.wav");
        clip.start();
    }

    public void playDoorOpening(){
        Clip clip = getClip("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\sound\\dooropening.wav");
        clip.start();
    }

    public void playDoorUnlock(){
        Clip clip = getClip("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\sound\\doorunlock.wav");
        clip.start();
    }

    public void playDoorLocked(){
        Clip clip = getClip("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\sound\\doorlocked.wav");
        clip.start();
    }

    private Clip getWalkingClip(){
        return getClip("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\sound\\woodenfloor.wav");
    }


    private Clip getClip(String pathname){
        Clip clip = null;
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(pathname));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.err.println(e + " in SFX");
        }
        return clip;
    }
}
