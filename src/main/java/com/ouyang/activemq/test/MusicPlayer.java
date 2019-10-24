package com.ouyang.activemq.test;


import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author oy
 * @description
 * @date 2019/10/24
 */
public class MusicPlayer{

    private  Player player;

    public MusicPlayer(Player player) {
        this.player = player;
    }

    public MusicPlayer() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void play() throws Exception{
        player.play();
    }

    public void close()throws Exception{
        player.close();
    }

    public static void main(String[] args) throws Exception{
        File file = new File("D:\\test\\1.mp3");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream stream = new BufferedInputStream(fis);
        Player player = new Player(stream);
        MusicPlayer play = new MusicPlayer(player);

        MusicThread musicThread = new MusicThread();
        musicThread.setMusicPlayer(play);
        Thread thread = new Thread(musicThread);

        Scanner scanner = new Scanner(System.in);
        while (true){
            String next = scanner.next();
            if(next.equals("play")){
                thread.run();
                System.out.println("好的 继续播放");
            }else{
                thread.stop();
//                musicThread.setExit(false);
//                System.out.println("停止播放");
            }
        }
    }
}
