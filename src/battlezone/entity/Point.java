package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
public class Point extends Entity {
    public ID id = ID.Point;
    public Point(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        Vector3 wektor = new Vector3(position);
        wektor.multiply(-1);
        velocity = new Vector3(0, 0, 0);
        model.move(wektor);
        for(int i=0; i<model.triangles.size(); i++){
            for(int j=0; j<3; j++){
                model.triangles.get(i).verticies[j].multiply(0.1);
            }
        }
        model.move(position);
    }
    double t = 0;
    public void logic () {
        position.add(velocity);
        model.move(velocity);
    }
}