package z.zz.core;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.ImageLayer;
import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Callback;
import tripleplay.game.UIScreen;
import z.zz.sprite.Sprite;
import z.zz.sprite.SpriteLoader;

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
    private int e=0,r=0;
    private int offset=0,check=0;
    Body body;

    public Seelot(final World world,final float x, final float y)
    {
        sprite= SpriteLoader.getSprite("images/tp.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f,sprite.height()/2f);
                sprite.layer().setTranslation(x,y);

                body=initPhysicsBody(world,Page2.M_PER_PIXEL*x,Page2.M_PER_PIXEL*y);
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

                check=1;
                HP=100;
            state=State.IDLE;
            spriteIndex=-1;
                e=0;
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
        shape.setAsBox(1f,1f);
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


    public void update(int delta){
        if (!hasLoaded)return;
        e+=delta;
        if(check==1){r+=delta;if(r>1000){HP=HP-50;r=0;}}else{r=0;}


        if(HP<=0){state=State.ATT;r=0;offset=8;
            if(e>150){
                if(spriteIndex==11){spriteIndex=spriteIndex+0;}
                else{
                spriteIndex=((spriteIndex+1));
                sprite.setSprite(spriteIndex);
                }
                e=0;
            }
        }
else{

        if(e>150){
            switch(state){
                case IDLE : offset=0;
                    break;
                case RUN : offset=4;
                    break;
                case ATT : offset=8;
                    if(spriteIndex==11){state=State.ATT;}
                    break;
            }
            //if(spriteIndex==11){spriteIndex=spriteIndex+0;}else{
            spriteIndex=offset+((spriteIndex+1)%4);
            spriteIndex=spriteIndex%4;
            sprite.setSprite(spriteIndex);
        //}
            e=0;
        }
    }}

    public void dying(int delta){

    }



}
