package com.ouyang.activemq.test;

/**
 * @author oy
 * @description
 * @date 2019/10/24
 */
public class MusicThread extends Thread {

    private volatile boolean exit = true;
    private MusicPlayer musicPlayer;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public void run(){
        while (exit){
            try {
                musicPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
