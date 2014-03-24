package z.zz.core;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import tripleplay.game.UIScreen;
import z.zz.sprite.Sprite;
import z.zz.sprite.SpriteLoader;

import java.util.Random;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by all user on 28/1/2557.
 */
public class Enemy extends UIScreen {

    Body body;
    ImageLayer bgLayer;
    Random rand = new Random();
    int hp=1;
       boolean diecheck=false;


    public Enemy()
    {
        Image bgImage1 = assets().getImage("images/enemy2.png");
        bgLayer = graphics().createImageLayer(bgImage1);
        bgLayer.setOrigin(34,34);
        graphics().rootLayer().add(bgLayer);
        int x=rand.nextInt(638)+1;
        int y=rand.nextInt(40)+400;
        bgLayer.setTranslation(x,y);
    }

    public ImageLayer enemyadd(){
        return bgLayer;
    }

    public void hpDown(){
        hp=hp-1;

    }

    float alpa=0.0f;
    int e=0;
    public void update(int delta){
        e+=delta;
        if(e>150){
            if(alpa<0.5)alpa=1;
            else{alpa=0.0f;}
            e=0;
        }
        if(hp<=0){
             diecheck=true;
        }

        if(diecheck==true){
            bgLayer.setAlpha(alpa);
        }

    }



}
