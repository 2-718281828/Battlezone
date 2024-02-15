package battlezone.main;

import maths.Vector2;
import renderer.*;

import java.awt.*;
import java.io.File;
import java.util.Locale;

public class MainRenderer extends Renderer {

    public Model model;
    public Triangles triangles;
    String classPath = getClass().getResource("").getPath() + "/torus.model";
    public MainRenderer(Vector2 vector2, Camera camera) {
        super(vector2, camera);

        triangles = new Triangles();
        model = LoadModel.loadModel(new File(classPath), Color.red, this, camera);
        model.init(triangles);
    }

    public void render(Graphics2D graphics2D) {
        triangles.render(graphics2D);
    }
}
