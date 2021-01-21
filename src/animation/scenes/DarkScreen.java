package animation.scenes;

import animation.Animation;

import java.awt.image.ImageObserver;

public class DarkScreen extends Animation {

    public DarkScreen(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        background.fill(1, 1);
    }
}
