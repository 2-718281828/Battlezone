package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;

public class Player extends Entity {
    public ID id = ID.Enemy1;
    public Player(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
    }
    public void logic (){
        //jaka znowu logika? ;-;
    }
}
