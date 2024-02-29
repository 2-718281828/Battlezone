package battlezone.main;

import maths.Vector2;
import renderer.*;

import java.awt.*;
import java.io.File;

public class MainRenderer extends Renderer {

    public Model model;
    public Triangles triangles;
    String classPath = getClass().getResource("").getPath() + "/torus.model";
    HUD hud;
    public MainRenderer(Vector2 vector2, Camera camera) {
        super(vector2, camera);
        triangles = new Triangles();
	hud = new HUD();
    }

    public void render(Graphics2D graphics2D) {
        triangles.render(graphics2D);
	hud.render(graphics2D);
    }
}
