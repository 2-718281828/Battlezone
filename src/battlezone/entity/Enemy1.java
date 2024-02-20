package battlezone.entity;

import entity.Entity;
import entity.EntityHandler;
import maths.Vector3;
import renderer.Model;
import util.Console;

import java.awt.event.KeyListener;
import java.util.Random;

public class Enemy1 extends Entity {
    public ID id = ID.Enemy1;
    double t = 0;
    double speed = 0.01; //ustalamy domyślną prędkość obiektu
    double alfa = 45; //kąt względem którego obiekt porusza sie wzgłedem osi x
    double beta = alfa;  //ostatni kąt przed zmianą kierunku
    public Enemy1(Model model, Vector3 position, EntityHandler entityHandler){
        super(model, position, entityHandler);
        velocity = new Vector3(0, 0, 0);
        model.rotate(2, Math.PI);
        model.rotate(1, Math.PI);
        model.rotate(1, -alfa);
        model.scale(0.2);
    }

    public void logic () {
        position.add(velocity);
        model.move(velocity);
        velocity.x=speed*Math.cos(alfa);
        velocity.z=speed*Math.sin(alfa);   //zarządza prędkością
        if (alfa!=beta){                          //alfa to jest kąt kierunku ustalany przez nas na bierząco
            model.rotate(1, -(alfa-beta));      //beta to poprzedni kąt ustawienia modelu
            beta=alfa;     //dzięki temu model zawsze jest ustawiony w wybranym przez nas kierunku, a nie kręci się
        }
        //kąt między osiami x i z patrząc z góry od przodu jest pierwszej ćwiartce układu
        //alfa = t;  //test
        //t=t+0.01;  //test
    }
    Random random = new Random();
    public void AI() {
    	
    }
}
