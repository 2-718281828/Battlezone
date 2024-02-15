package battlezone.main;

import engine.Logic;
import renderer.Camera;

public class MainLogic implements Logic {

    Camera camera;

    public MainLogic(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        camera.update();
    }
}
