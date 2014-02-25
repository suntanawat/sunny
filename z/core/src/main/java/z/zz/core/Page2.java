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

import static playn.core.PlayN.graphics;

public class Page2 extends UIScreen {
    Body body;
    Body body2;
    public static float M_PER_PIXEL = 1/ 26.667f;
    private static int width = 24;
    private static int height = 18;
    private World world;
    private Boolean showDebugDraw=true;
    ImageLayer bgLayer2;
    private DebugDrawBox2D debugDraw;


    private Root root2;
    private ScreenStack ss = new ScreenStack();
    public Page2(ScreenStack ss) {
        this.ss = ss;
    }

    Seelot z =new Seelot(world,300f,200f);
    Seelot z2 =new Seelot(world,500f,200f);
    @Override
    public void wasAdded() {
        super.wasAdded();



        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        Image bgImage2 = assets().getImage("images/x.png");
         bgLayer2 = graphics().createImageLayer(bgImage2);
        graphics().rootLayer().add(bgLayer2);
        bgLayer2.setTranslation(400,0);

        layer.add(z.layer());
        layer.add(z2.layer());
        



        bgLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(Page2.this);
            }
        });


        Vec2 gravity = new Vec2(0.0f,10.0f);
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

        Body ground2 = world.createBody(new BodyDef());
        PolygonShape groundShape2 = new PolygonShape();
        groundShape2.setAsEdge(new Vec2(0f,height-1),new Vec2(width,height-1));
        ground2.createFixture(groundShape2,0.0f);

        Body ground3 = world.createBody(new BodyDef());
        PolygonShape groundShape3 = new PolygonShape();
        groundShape3.setAsEdge(new Vec2(1,0),new Vec2(1,height));
        ground3.createFixture(groundShape3,0.0f);

        Body ground4 = world.createBody(new BodyDef());
        PolygonShape groundShape4 = new PolygonShape();
        groundShape4.setAsEdge(new Vec2(width-1,0),new Vec2(width-1,height));
        ground4.createFixture(groundShape4,0.0f);

         body = initPhysicsBody(world,5, Page2.M_PER_PIXEL);
        body2=initPhysicsBody(world,16, Page2.M_PER_PIXEL);

        bgLayer2.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                body.applyLinearImpulse(new Vec2(100f, 0f), body.getPosition());
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
        shape.setAsBox(2f,8f);

       // body.setFixedRotation(false);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=0.2f;
        fixtureDef.friction=0.1f;
        fixtureDef.restitution=1f;
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }


    @Override
    public void update(int delta) {

        world.step(0.033f,10,10);
        z.update(delta);
        z2.update(delta);

    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        z.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }

        z.layer().setTranslation(body.getPosition().x/M_PER_PIXEL,body.getPosition().y/M_PER_PIXEL);
        z2.layer().setTranslation(body2.getPosition().x / M_PER_PIXEL, body2.getPosition().y / M_PER_PIXEL);

    }
}












