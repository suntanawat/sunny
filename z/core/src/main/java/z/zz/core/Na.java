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
public class Na extends UIScreen {

    Body body;
    ImageLayer bgLayer;
    Random rand = new Random();


    public Na()
    {
        Image bgImage1 = assets().getImage("images/na1.png");
        Image bgImage2 = assets().getImage("images/na2.png");
        Image bgImage3 = assets().getImage("images/na3.png");
        Image bgImage4 = assets().getImage("images/na4.png");
        Image bgImage5 = assets().getImage("images/na5.png");
        Image bgImage6 = assets().getImage("images/na6.png");
        Image bgImage7 = assets().getImage("images/na7.png");
        Image bgImage8 = assets().getImage("images/na8.png");
        int n=rand.nextInt(8)+1;
        int m=rand.nextInt(10)+5;
        if(n==1){bgLayer = graphics().createImageLayer(bgImage1);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==2){bgLayer = graphics().createImageLayer(bgImage2);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==3){bgLayer = graphics().createImageLayer(bgImage3);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==4){bgLayer = graphics().createImageLayer(bgImage4);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==5){bgLayer = graphics().createImageLayer(bgImage5);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==6){bgLayer = graphics().createImageLayer(bgImage6);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==7){bgLayer = graphics().createImageLayer(bgImage7);bgLayer.setOrigin(m/2f,m/2f);}
        if(n==8){bgLayer = graphics().createImageLayer(bgImage8);bgLayer.setOrigin(m/2f,m/2f);}


        bgLayer.setSize(m,m);
        graphics().rootLayer().add(bgLayer);

    }

    public ImageLayer add(){
        return bgLayer;
    }



}
