package animation.scenes;

import animation.Animation;
import matrix.Coordenadas2D;

import java.awt.image.ImageObserver;

public class RocketArrival extends Animation {

    public RocketArrival(int width, int height, ImageObserver observer) {
        super(width, height, observer);
        setDelay(30);
        setOrigin(new Coordenadas2D(width/2, height/2));

        setupForeground();
    }

    protected void setupForeground() {

    }

}
