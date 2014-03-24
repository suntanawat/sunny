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
public class Seenot extends UIScreen {
    public int HP=100;
    private Sprite sprite;
    private int spriteIndex=0;
    private boolean hasLoaded=false;

    public enum State{IDLE,RUN,ATT};
    private State state = State.IDLE;
    public enum BulletType{BULLET1,BULLET2,BULLET3,NONE};
    private BulletType bulletType = BulletType.BULLET1;
    private int e=0,r=0;
    private int offset=0,check=0;


    public Seenot(final float x, final float y)
    {
        sprite= SpriteLoader.getSprite("images/tp.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f,sprite.height()/2f-5);
                sprite.layer().setTranslation(x,y);

                hasLoaded=true;
            }
            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("HIAAAA_MANG_ERROR!",cause);
            }

        });


    }



    public ImageLayer layer(){
        return this.sprite.layer();
    }
    public void setSpriteNumber(int n){
        sprite.setSprite(n);
    }
    public void destroy(){
        sprite.layer().destroy();
    }


    ImageLayer imagelayer;

    public void update(int delta){
        if (!hasLoaded)return;
        e+=delta;
            if(e>200){
                spriteIndex=offset+((spriteIndex+1)%10);
                sprite.setSprite(spriteIndex);
                e=0;
            }





    }



}
