import javax.sound.sampled.*;
import java.io.File;

/**
 * @ClassName: SoundEffect
 * @Description 音效工具类
 * @author Linux_Mumu
 */

public class SoundEffect {

    private static final File backgroundSoundPath = new File("static/sound/background.wav");
    private static final File clickSoundPath = new File("static/sound/click.wav");
    private static final File eraseSoundPath = new File("static/sound/erase.wav");

    private SoundEffect() {
    }

    public static void playBackgroundSound() {
        try {
            if (backgroundSoundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(backgroundSoundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // 无限循环

                // 阻塞线程，使得主线程在音乐播放完毕之前不会结束。
                // Thread.sleep(clip.getMicrosecondLength() / 1000);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playClickSound() {
        try {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(clickSoundPath);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

            // 阻塞线程，使得主线程在音乐播放完毕之前不会结束。
            // Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playEraseSound() {
        try {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(eraseSoundPath);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

            // 阻塞线程，使得主线程在音乐播放完毕之前不会结束。
            // Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
