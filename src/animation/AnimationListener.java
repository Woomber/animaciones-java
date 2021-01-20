package animation;

import java.awt.image.BufferedImage;

public interface AnimationListener {
    void drawFrame(BufferedImage frame);
    void animationFinished(Object sender, BufferedImage lastFrame);
}
