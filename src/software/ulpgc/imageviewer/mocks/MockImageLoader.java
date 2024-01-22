package software.ulpgc.imageviewer.mocks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import software.ulpgc.imageviewer.Image;
import software.ulpgc.imageviewer.ImageLoader;

public class MockImageLoader implements ImageLoader {
    private final String[] ids = new String[] {"w1", "w2", "w3"};

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                return ids[i];
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % ids.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i - 1 : ids.length - 1);
            }
        };
    }
}
