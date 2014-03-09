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

/**
 * Created by all user on 28/1/2557.
 */
public class Behide extends UIScreen {

    private Sprite sprite;
    private int spriteIndex=0;
    private boolean hasLoaded=false;



    public Behide()
    {
        sprite= SpriteLoader.getSprite("images/bg2.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
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


int e=0;
    public void update(int delta){
        if (!hasLoaded)return;

        e+=delta;
        if(e==3000){
            sprite.setSprite(0);
        }
        if(e==3400){
            sprite.setSprite(1);
        }
        if(e==3500){
            sprite.setSprite(2);
        }
        if(e==4000){
            sprite.setSprite(1);
        }
        if(e==4150){
            sprite.setSprite(0);
            e=0;
        }
        }



}
