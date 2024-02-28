package battlezone.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
   static  String classPath= Sounds.class.getResource("").getPath();
    public static synchronized void playSound(String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(classPath+"\\"+name));
                    clip.open(inputStream);
                    clip.start();
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
        System.out.println(Main.class.getResourceAsStream(classPath+"\\"+name));
    }
}
