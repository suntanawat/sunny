package z.zz.core;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import tripleplay.game.UIScreen;
import z.zz.sprite.Sprite;
import z.zz.sprite.SpriteLoader;

import javax.swing.*;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static z.zz.core.Page2.world;

/**
 * Created by all user on 28/1/2557.
 */
public class Bullet3 extends UIScreen {
    private Sprite sprite;
    private int spriteIndex=0;
    private boolean hasLoaded=false;

    public enum State{IDLE,RUN,ATT};
    private State state = State.IDLE;
    private int e=0,r=0;
    private int offset=0,check=0;
    Body body;

    public Bullet3()
    {
        sprite= SpriteLoader.getSprite("images/bullet3.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f,sprite.height()/2f);

                hasLoaded=true;
            }
            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("HIAAAA_MANG_ERROR!",cause);
            }

        });



    }







    public ImageLayer pic(){
        return this.sprite.layer();
    }
    public Body body(){
        return this.body;
    }
    public void setSpriteNumber(int n){
        sprite.setSprite(n);
    }
    public void destroy(){
        sprite.layer().destroy();
    }



    public void update(int delta){
        if (!hasLoaded)return;
        e+=delta;
        if(e>350){
            spriteIndex=offset+((spriteIndex+1)%6);
            sprite.setSprite(spriteIndex);
            e=0;
        }

    }

}


