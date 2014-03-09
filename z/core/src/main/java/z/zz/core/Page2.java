package z.zz.core;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

import org.jbox2d.dynamics.contacts.Contact;
import react.UnitSlot;

import org.jbox2d.common.Vec2;

import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import static playn.core.PlayN.*;
import playn.core.*;
import playn.core.util.Clock;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;

import playn.core.Font;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.PlayN.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static playn.core.PlayN.graphics;

public class Page2 extends UIScreen {

    ImageLayer bgLayer4;
    enum State{LEFT,FORWARD,RIGHT};
    State state =State.FORWARD;

    public enum BulletType{BULLET1,BULLET2,BULLET3,NONE};
    private BulletType bulletType = BulletType.NONE;

    float  atktime=999;
    Body body;
    Body body2;
    Body nabody;
    public static float M_PER_PIXEL = 1/ 26.667f;
    private static int width = 24;
    private static int height = 18;
    public static World world;
    private World world2;
    private Boolean showDebugDraw=true;
    ImageLayer bgLayer2;
    private DebugDrawBox2D debugDraw;


    private Root root2;
    private ScreenStack ss = new ScreenStack();
    public Page2(ScreenStack ss) {
        this.ss = ss;
    }

    Seelot z =new Seelot(world,320f,55f);
    Seelot z2 =new Seelot(world,0,0);
    Behide b1=new Behide();
    Bullet1 bullet1=new Bullet1();
    Bullet2 bullet2=new Bullet2();
    Bullet3 bullet3=new Bullet3();
    Body bulletBody;


    @Override
    public void wasAdded() {
        super.wasAdded();


//        layer.add(b1.layer());
        graphics().rootLayer().add(b1.layer());


//        Image bgImage3 = assets().getImage("images/cellbg.png");
//        ImageLayer bgLayer3 = graphics().createImageLayer(bgImage3);
//        graphics().rootLayer().add(bgLayer3);

        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);


        Image bgImage2 = assets().getImage("images/x.png");
         bgLayer2 = graphics().createImageLayer(bgImage2);
        graphics().rootLayer().add(bgLayer2);
        bgLayer2.setTranslation(400,0);

        Image bgImage4 = assets().getImage("images/na.png");
        bgLayer4 = graphics().createImageLayer(bgImage4);
        graphics().rootLayer().add(bgLayer4);


        graphics().rootLayer().add(bullet1.pic().setTranslation(150,150));
        graphics().rootLayer().add(bullet2.pic().setTranslation(200,200));
        graphics().rootLayer().add(bullet3.pic().setTranslation(250,250));
        layer.add(z.layer());
        layer.setTranslation(30,30);
        layer.add(z2.layer());



        Vec2 gravity2 = new Vec2(0.0f,0.0f);
        world2=new World(gravity2, true);
        world2.setWarmStarting(true);
        world2.setAutoClearForces(true);
        



        bgLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(Page2.this);
            }
        });


        Vec2 gravity = new Vec2(0.0f,0.0f);
        world=new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });

        if (showDebugDraw){
            CanvasImage image = graphics().createImage(
                    (int)(width / Page2.M_PER_PIXEL),
                    (int)(height / Page2.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw=new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit|DebugDraw.e_jointBit|DebugDraw.e_aabbBit);
            debugDraw.setCamera(0,0,1f/Page2.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);
        }

//        Body ground = world.createBody(new BodyDef());
//        PolygonShape groundShape = new PolygonShape();
//        groundShape.setAsEdge(new Vec2(2f,height-2),new Vec2(width-2f,height-2));
//        ground.createFixture(groundShape,0.0f);

        Body ground1 = world.createBody(new BodyDef());
        PolygonShape groundShape1 = new PolygonShape();
        groundShape1.setAsEdge(new Vec2(0f,0),new Vec2(width,0));
        ground1.createFixture(groundShape1,0.0f);

        Body ground2 = world.createBody(new BodyDef());
        PolygonShape groundShape2 = new PolygonShape();
        groundShape2.setAsEdge(new Vec2(0f,height),new Vec2(width,height));
        ground2.createFixture(groundShape2,0.0f);

        Body ground3 = world.createBody(new BodyDef());
        PolygonShape groundShape3 = new PolygonShape();
        groundShape3.setAsEdge(new Vec2(0,0),new Vec2(0,height));
        ground3.createFixture(groundShape3,0.0f);

        Body ground4 = world.createBody(new BodyDef());
        PolygonShape groundShape4 = new PolygonShape();
        groundShape4.setAsEdge(new Vec2(width,0),new Vec2(width,height));
        ground4.createFixture(groundShape4,0.0f);
//====================================================================
        Body ground21 = world2.createBody(new BodyDef());
        PolygonShape groundShape21 = new PolygonShape();
        groundShape21.setAsEdge(new Vec2(0f,0),new Vec2(width,0));
        ground21.createFixture(groundShape21,0.0f);

        Body ground22 = world2.createBody(new BodyDef());
        PolygonShape groundShape22 = new PolygonShape();
        groundShape22.setAsEdge(new Vec2(0f,height),new Vec2(width,height));
        ground22.createFixture(groundShape22,0.0f);

        Body ground23 = world2.createBody(new BodyDef());
        PolygonShape groundShape23 = new PolygonShape();
        groundShape23.setAsEdge(new Vec2(0,0),new Vec2(0,height));
        ground23.createFixture(groundShape3,0.0f);

        Body ground24 = world2.createBody(new BodyDef());
        PolygonShape groundShape24 = new PolygonShape();
        groundShape24.setAsEdge(new Vec2(width,0),new Vec2(width,height));
        ground24.createFixture(groundShape24,0.0f);


        body2=initPhysicsBody(world,16,5,0.2f,0.2f);

        bgLayer2.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                body2.applyLinearImpulse(new Vec2(-50f, -100f), body2.getPosition());
            }
        });


        keyboard().setListener(new Keyboard.Adapter(){
            @Override
            public void onKeyDown(Keyboard.Event event) {
                switch (event.key())
                {
                    case LEFT:
                        state =State.LEFT;
                        break;
                    case RIGHT:
                        state =State.RIGHT;
                        break;
                    case SPACE:
//                        world.clearForces();
                        bulletBody.setLinearVelocity(new Vec2(0,0));
                        bulletBody.setAngularVelocity(0);
                        bulletBody.setTransform(new Vec2(12, 2), 0f);
                        z.atk();
                        atktime=0;
                        bulletType=BulletType.BULLET1;

                        break;

                }
            }

            @Override
            public void onKeyUp(Keyboard.Event event) {
                switch (event.key())
                {
                    case LEFT:
                        state=State.FORWARD;
                        break;
                    case RIGHT:
                        state=State.FORWARD;
                        break;
                }
            }
        });
        int n;
        n=(rand.nextInt(23)+1);
        int xline=n;
        n=(rand.nextInt(17)+1);
        int yline=n;
        nabody=initPhysicsBody(world2,xline,yline,0.2f,0.2f);
        bulletBody=initPhysicsBody(world,12,2,0.4f,0.4f);


       for(int hh = 0;hh<100;hh++)
        {
            n=(rand.nextInt(23)+1);
             xline=n;
            n=(rand.nextInt(17)+1);
             yline=n;
            bodys.add(initPhysicsBody(world2, xline, yline,0.2f,0.2f));

            n=(rand.nextInt(23)+1);
            xline=n;
            n=(rand.nextInt(17)+1);
            yline=n;
            Na na=new Na();
            nalayers.add(na.add());
        }




    }
    List<Body> bodys=new ArrayList<Body>();
    List<ImageLayer> nalayers=new ArrayList<ImageLayer>();


    private Body initPhysicsBody(World world,float x ,float y,float sizex ,float sizey){
        BodyDef bf = new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position=new Vec2(200f,200f);
        Body body = world.createBody(bf);
        //EdgeShape shape = new EdgeShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizex,sizey);
        body.setFixedRotation(false);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=8f;
        fixtureDef.friction=0.1f;
        fixtureDef.restitution=0.2f;
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }

    float i=0;
    float l=0;
    float rotate;
    Random rand = new Random();
    @Override
    public void update(int delta) {
        b1.update(delta);
        if(atktime<=800+delta){atktime=atktime+delta;}
        if(atktime>800&&atktime<=800+delta&&bulletType!=BulletType.NONE){
            float x=0,y=0;
            float ii=i;
            if(ii>=0){x=-i/1.575f;}else{x=-i/1.575f;}
            y=1-(Math.abs(i)/1.575f);
            bulletBody.applyLinearImpulse(new Vec2(50*x,50*y),bulletBody.getPosition());
        }

        l=l+delta;
        if(l>4000){
            float n=(((rand.nextInt(200))-100)/300.0f);
            float x=n;
            nabody.applyLinearImpulse(new Vec2(x,0),nabody.getPosition());

        }
        if(l>8000){
            float n=(((rand.nextInt(200))-100)/300.0f);
            float y=n;
            nabody.applyLinearImpulse(new Vec2(0,y),nabody.getPosition());
            l=0;
        }
        if(l>100){
            for (Body body : bodys) {
            float n=(((rand.nextInt(200))-100)/1000.0f);
            float x=n;
            body.applyLinearImpulse(new Vec2(x,0),body.getPosition());
            }
        }
        if(l>2100){
            for (Body body : bodys) {
            float n=(((rand.nextInt(200))-100)/1000.0f);
            float y=n;
            body.applyLinearImpulse(new Vec2(0,y),body.getPosition());
            }
        }

        world.step(0.033f,10,10);
        world2.step(0.033f,10,10);
        z.update(delta);
        z2.update(delta);
        bullet1.update(delta);
        bullet2.update(delta);
        bullet3.update(delta);

        if(state==State.LEFT){ if(i<1.575)i=i+0.025f;}
        else if(state==State.RIGHT){ if(i>-1.575)i=i-0.025f;}
        else{}

        rotate+=0.015;




    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        bgLayer4.setRotation(nabody.getAngle());
        bgLayer4.setRotation(rotate);
        z.paint(clock);
//        if(showDebugDraw){
//            debugDraw.getCanvas().clear();
//            world.drawDebugData();
//        }
        z.layer().setRotation(i);
        //z.layer().setTranslation(body.getPosition().x/M_PER_PIXEL,body.getPosition().y/M_PER_PIXEL);
       z2.layer().setTranslation(body2.getPosition().x / M_PER_PIXEL, body2.getPosition().y / M_PER_PIXEL);
        bgLayer4.setTranslation(nabody.getPosition().x/M_PER_PIXEL,nabody.getPosition().y/M_PER_PIXEL);



        for (int i = 0; i < bodys.size(); i++) {
            Body bodyx = bodys.get(i);
            ImageLayer layerx = nalayers.get(i);
            layerx.setTranslation(bodyx.getPosition().x/M_PER_PIXEL,bodyx.getPosition().y/M_PER_PIXEL);
            layerx.setRotation(rotate);

        }


        switch (bulletType){
            case BULLET1:
                bullet1.pic().setTranslation(bulletBody.getPosition().x / M_PER_PIXEL, bulletBody.getPosition().y / M_PER_PIXEL);
                break;
            case BULLET2:
                bullet2.pic().setTranslation(bulletBody.getPosition().x / M_PER_PIXEL, bulletBody.getPosition().y / M_PER_PIXEL);
                break;
            case BULLET3:
                bullet3.pic().setTranslation(bulletBody.getPosition().x / M_PER_PIXEL, bulletBody.getPosition().y / M_PER_PIXEL);
                break;
            case NONE:
                bulletBody.setTransform(new Vec2(12, 2), 0f);
                bulletBody.setLinearVelocity(new Vec2(12, 2));
                break;

        }

    }
}












