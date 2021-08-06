package blog.dskim.designPatterns.facade.complexLibrary;

import java.io.File;

public class AudioMixer {
    public File fix(VideoFile result) {
        System.out.println("AudioMixer: mixing audio...");
        return new File("tmp");
    }
}
