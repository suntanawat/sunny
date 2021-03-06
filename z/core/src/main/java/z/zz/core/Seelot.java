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
public class Seelot extends UIScreen {
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


    public Seelot(final World world,final float x, final float y)
    {
        sprite= SpriteLoader.getSprite("images/tp.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f,sprite.height()/2f-5);
                sprite.layer().setTranslation(x,y);

             //   body=initPhysicsBody(world,Page2.M_PER_PIXEL*x,Page2.M_PER_PIXEL*y);
                hasLoaded=true;
            }
            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("HIAAAA_MANG_ERROR!",cause);
            }

        });


        sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {

         //       check=1;
          //      HP=100;
            state=State.ATT;
                spriteIndex=-1;
          //  spriteIndex=-1;
           //     e=0;

            }


        });


    }



    private Body initPhysicsBody(World world,float x ,float y){
        BodyDef bf = new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position=new Vec2(200f,200f);
        Body body = world.createBody(bf);
        //EdgeShape shape = new EdgeShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.4f,0.4f);
        // body.setFixedRotation(false);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=0.4f;
        fixtureDef.friction=0.1f;
        fixtureDef.restitution=1f;
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }





    public ImageLayer layer(){
        return this.sprite.layer();
    }


    public void atk(){
        state=State.ATT;
    }

    Bullet1 bullet1;
    ImageLayer imagelayer;
    Body body;

    public void update(int delta){
        if (!hasLoaded)return;
        //sprite.layer().setRotation(body.getAngle());
        e+=delta;
        if(check==1){r+=delta;if(r>1000){HP=HP-50;r=0;}}else{r=0;}


        if(HP<=0){state=State.ATT;r=0;offset=5;
            if(e>150){
                if(spriteIndex==9){
                    state=State.IDLE;

                    }
                else{
                spriteIndex=((spriteIndex+1));
                sprite.setSprite(spriteIndex);
                }
                e=0;
            }
        }
else{

        if(e>200){
            switch(state){
                case IDLE : offset=0;
                    break;
                case ATT : offset=5;
                    if(spriteIndex==8){
                        state=State.IDLE;
//                        Image bgImage2 = assets().getImage("images/x.png");
//                        ImageLayer bgLayer2 = graphics().createImageLayer(bgImage2);
//                        graphics().rootLayer().add(bgLayer2);
//                        bgLayer2.setTranslation(500,400);

//                        if(bulletType==BulletType.NONE){return;}
//                        else if(bulletType==BulletType.BULLET1){
//                            bullet1=new Bullet1();
//                            imagelayer = bullet1.pic();
//                            graphics().rootLayer().add(imagelayer);
//                            body=bullet1.body();
//                            imagelayer.setTranslation(0,0);
//
//                        }


                    }
                    break;
            }
            //if(spriteIndex==11){spriteIndex=spriteIndex+0;}else{
            spriteIndex=offset+((spriteIndex+1)%5);
          //  spriteIndex=spriteIndex%5;
            sprite.setSprite(spriteIndex);
        //}
            e=0;
        }
    }

//        if(bulletType==BulletType.NONE){return;}
//        else if(bulletType==BulletType.BULLET1){
//           // imagelayer.setTranslation(body.getPosition().x,body.getPosition().y);
//          //  bullet1.update(delta);
//        }


    }



}
