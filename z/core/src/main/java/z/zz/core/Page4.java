package z.zz.core;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
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

public class Page4 extends UIScreen {

    boolean checkDestroy=false;
    int diecount=0;

    ImageLayer bgLayer4;
    enum State{LEFT,FORWARD,RIGHT};
    State state =State.FORWARD;


    int console=0;
    float  atktime=999;
    Body body;
    // Body body2;
    Body nabody;
    public static float M_PER_PIXEL = 1/ 26.667f;
    private static int width = 24;
    private static int height = 18;
    public static World world;
    private World world2;
    private Boolean showDebugDraw=false;
    ImageLayer bgLayer2;
    private DebugDrawBox2D debugDraw;


    private Root root2;
    private ScreenStack ss = new ScreenStack();
    public Page4(ScreenStack ss) {
        this.ss = ss;
    }

    Seelot z =new Seelot(world,320f,55f);
    //   Seelot z2 =new Seelot(world,0,0);
    Behide b1=new Behide();
    Bullet1 bullet1=new Bullet1();
    Bullet2 bullet2=new Bullet2();
    Bullet3 bullet3=new Bullet3();
    Body bulletBody;
    ImageLayer winLayer;
    ImageLayer loseLayer;
    Number n100=new Number();
    Number n10=new Number();
    Number n1=new Number();

    int hp=3;
    boolean isdamaged=false;
    ImageLayer hqLayer;
    ImageLayer pulse;
    boolean pulseWalk=false;
    ImageLayer skillLayer1;
    ImageLayer skillLayer2;
    ImageLayer skillLayer3;
    Body bossdy;
    ImageLayer bossimg;
    int bossHP=10;



    @Override
    public void wasAdded() {
        super.wasAdded();
        try{

//        layer.add(b1.layer());
        graphics().rootLayer().add(b1.layer());


        Image bgImage = assets().getImage("images/exit.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);
        bgLayer.setTranslation(585,10);



        Image bgImage4 = assets().getImage("images/na.png");
        bgLayer4 = graphics().createImageLayer(bgImage4);
        graphics().rootLayer().add(bgLayer4);


        graphics().rootLayer().add(bullet1.pic().setTranslation(999,150));
        graphics().rootLayer().add(bullet2.pic().setTranslation(999,200));
        graphics().rootLayer().add(bullet3.pic().setTranslation(999,250));
        layer.add(z.layer());
        layer.setTranslation(30,30);
        // layer.add(z2.layer());



        Vec2 gravity2 = new Vec2(0.0f,0.0f);
        world2=new World(gravity2, true);
        world2.setWarmStarting(true);
        world2.setAutoClearForces(true);




        bgLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.save();
                ss.remove(ss.top());
            }
        });


        Vec2 gravity = new Vec2(0.0f,0.0f);
        world=new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                if ((contact.getFixtureA().getBody() == bossdy&&contact.getFixtureB().getBody() == bulletBody)||
                    (contact.getFixtureA().getBody() == bulletBody&&contact.getFixtureB().getBody() == bossdy)){
                    checkDestroy=true;
                    bossHP-=1;
                }

                for(int i=0;i<10;i++){
                    Body now=bodyEnemys.get(i);
                    if ((contact.getFixtureA().getBody() == now&&contact.getFixtureB().getBody() == bulletBody)||
                            (contact.getFixtureA().getBody() == bulletBody&&contact.getFixtureB().getBody() == now)){

                        checkDestroy=true;

                        int xxx =booEnemys.get(i);
                        xxx=0;
                        booEnemys.remove(i);
                        booEnemys.add(i, xxx);
                        alpaTimes=0;
                        alparun=true;
                        System.out.println(i);

                    }}
                if (contact.getFixtureA().getBody() == bulletBody||contact.getFixtureB().getBody() == bulletBody){
                    checkDestroy=true;
                }


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




        keyboard().setListener(new Keyboard.Adapter(){
            @Override
            public void onKeyDown(Keyboard.Event event) {
                switch (event.key())
                {
                    case LEFT:
                        if(state==State.RIGHT)break;
                        state =State.LEFT;
                        break;
                    case RIGHT:
                        if(state==State.LEFT)break;
                        state =State.RIGHT;
                        break;
                    case SPACE:
                        checkDestroy=false;
                        bulletBody.setLinearVelocity(new Vec2(0,0));
                        bulletBody.setAngularVelocity(0);
                        bulletBody.setTransform(new Vec2(12, 2), 0f);
                        z.atk();
                        atktime=0;
                        break;
                    case NP1:
                        for(Body body:bodyEnemys){
                            pulseWalk=true;
                            body.applyLinearImpulse(new Vec2(0,15),body.getPosition());
                        }
                        break;
                    case NP2:
                        hp=0;
                        break;
                    case NP3:
                        SaveStatic.money+=1;
                        break;



                }
            }

            @Override
            public void onKeyUp(Keyboard.Event event) {
                switch (event.key())
                {
                    case LEFT:
                        if(state==State.RIGHT)break;
                        state=State.FORWARD;
                        break;
                    case RIGHT:
                        if(state==State.LEFT)break;
                        state=State.FORWARD;
                        break;
                    case ENTER:
                        if(console==-3){bossHP=0;}
                        else if(console!=0){console=0;}
                        break;
                    case W:
                        if(console==0){console-=1;}
                        else if(console!=0){console=0;}
                        break;
                    case I:
                        if(console==-1){console-=1;}
                        else if(console!=-1){console=0;}
                        break;
                    case N:
                        if(console==-2){console-=1;}
                        else if(console!=-2){console=0;}
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




        for(int i=0;i<30;i++){
            Enemy2 e1=new Enemy2();
            bodyEnemys.add(initPhysicsBody(world, 12, 99,0.6f,0.6f));
            enemylayers.add(e1.enemyadd());
            int h=1;
            booEnemys.add(h);
        }
        Image dollar = assets().getImage("images/dolla.png");
        ImageLayer dollarLayer = graphics().createImageLayer(dollar);
        graphics().rootLayer().add(dollarLayer);


        graphics().rootLayer().add(n100.pic().setTranslation(30,35));
        graphics().rootLayer().add(n10.pic().setTranslation(55,35));
        graphics().rootLayer().add(n1.pic().setTranslation(80,35));
        dollarLayer.setOrigin(13,23).setTranslation(115,35);

        Image win = assets().getImage("images/win.png");
        winLayer = graphics().createImageLayer(win);
        graphics().rootLayer().add(winLayer);
        winLayer.setAlpha(0);

        Image lose = assets().getImage("images/lose.png");
        loseLayer = graphics().createImageLayer(lose);
        graphics().rootLayer().add(loseLayer);
        loseLayer.setAlpha(0);

        Image skill1 = assets().getImage("images/skill1.png");
        skillLayer1 = graphics().createImageLayer(skill1);
        graphics().rootLayer().add(skillLayer1);
        Image skill2 = assets().getImage("images/skill2.png");
        skillLayer2 = graphics().createImageLayer(skill2);
        graphics().rootLayer().add(skillLayer2);
        Image skill3 = assets().getImage("images/skill3.png");
        skillLayer3 = graphics().createImageLayer(skill3);
        graphics().rootLayer().add(skillLayer3);
        skillLayer1.setTranslation(408,25);
        skillLayer2.setTranslation(456,25);
        skillLayer3.setTranslation(504,25);
        skillLayer1.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                for(Body body:bodyEnemys){
                    pulseWalk=true;
                    body.applyLinearImpulse(new Vec2(0,15),body.getPosition());
                }
            }});
        skillLayer2.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                hp=0;
            }
        });
        skillLayer3.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.money+=1;
            }
        });
        Image hq = assets().getImage("images/hq.png");
        hqLayer = graphics().createImageLayer(hq);
        graphics().rootLayer().add(hqLayer);
        hqLayer.setTranslation(250,0);
        Image pulseim = assets().getImage("images/pulse.png");
        pulse = graphics().createImageLayer(pulseim);
        graphics().rootLayer().add(pulse);
        pulse.setTranslation(0,-50);

        Image boss = assets().getImage("images/boss.png");
        bossimg = graphics().createImageLayer(boss);
        graphics().rootLayer().add(bossimg);
        bossimg.setOrigin(180,180);


        BodyDef bd = new BodyDef();
        bd.position.set(12, 17);
        bd.type = BodyType.DYNAMIC;
        CircleShape cs = new CircleShape();
        cs.m_radius = 5f;
        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 1.5f;
        fd.friction = 0.3f;
        fd.restitution = 0.5f;
        bossdy =  world.createBody(bd);
        bossdy.createFixture(fd);



    }catch (Exception e){System.out.println("!BAD!");}


    }
    List<Body> bodyEnemys=new ArrayList<Body>();
    List<Integer> booEnemys=new ArrayList<Integer>();
    List<ImageLayer> enemylayers=new ArrayList<ImageLayer>();

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
        body.setLinearDamping(0.25f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }
    float alpacount=0;
    float alpa=0.0f;
    int alpaTimes=0;
    int alpaTimes2=0;
    boolean alparun=false;
    float i=0;
    float l=0;
    float rotate;
    Random rand = new Random();
    float win=0;
    float enemyDelta=0;
    int enemyI=0;
    float pulseVec=0;
    float bossDelta=0;
    @Override
    public void update(int delta) {
        try{
        if (pulseWalk==true){
            pulseVec+=75;
            pulse.setTranslation(0,pulseVec);
            if (pulseVec>500){
                pulse.setTranslation(0,-50);
                pulseVec=0;
                pulseWalk=false;}
        }


        enemyDelta+=delta;
        if (enemyDelta>=3500 && enemyI<10){
            int x=(rand.nextInt(23)+1);
            int y=(rand.nextInt(6)+12);
            bodyEnemys.get(enemyI).setTransform(new Vec2(x, y), 0f);
            bodyEnemys.get(enemyI).setLinearVelocity(new Vec2(0, 0));

            enemyI+=1;
            enemyDelta=0;
        }


        alpacount+=delta;
        if(alpacount>150){
            if(alpa>=0.5f)alpa=0.0f;
            else{alpa=0.8f;}
            if(alparun==true&&alpaTimes<8){alpaTimes=alpaTimes+1;}
            if(alpaTimes2<9){alpaTimes2=alpaTimes2+1;}
            alpacount=0.0f;
        }
        b1.update(delta);
        if(atktime<=800+delta){atktime=atktime+delta;}
        if(atktime>800&&atktime<=800+delta&&SaveStatic.bulletType!=SaveStatic.BulletType.NONE){
            float x=0,y=0;
            float ii=i;
            if(ii>=0){x=-i/1.575f;}else{x=-i/1.575f;}
            y=1-(Math.abs(i)/1.575f);
            if(SaveStatic.bulletType== SaveStatic.BulletType.BULLET1){
                bulletBody.applyLinearImpulse(new Vec2(20*x,20*y),bulletBody.getPosition());
            }
            else if(SaveStatic.bulletType== SaveStatic.BulletType.BULLET2){
                bulletBody.applyLinearImpulse(new Vec2(60*x,60*y),bulletBody.getPosition());
            }
            else if(SaveStatic.bulletType== SaveStatic.BulletType.BULLET3){
                bulletBody.applyLinearImpulse(new Vec2(45*x,45*y),bulletBody.getPosition());
            }
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

            for(Body body:bodyEnemys){
                float y=(rand.nextInt(100)/1500.0f);
                float x=(rand.nextInt(5)+3);
                if(rand.nextInt(2)==0){x=-x;}


                body.applyLinearImpulse(new Vec2(x,-(y)),body.getPosition());
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
        //z2.update(delta);
        bullet1.update(delta);
        bullet2.update(delta);
        bullet3.update(delta);

        if(state==State.LEFT){ if(i<1.575)i=i+0.025f;}
        else if(state==State.RIGHT){ if(i>-1.575)i=i-0.025f;}

        rotate+=0.015;

        if(isdamaged==true){

            hqLayer.setAlpha(alpa);
            if(alpaTimes2>=8){
                hqLayer.setAlpha(1);
                isdamaged=false;
            }
        }


        if(checkDestroy==true){bulletBody.setTransform(new Vec2(99, 99), 0f);}
        for (int i = 0; i < bodyEnemys.size(); i++) {
            Body bodyx = bodyEnemys.get(i);
            ImageLayer layerx = enemylayers.get(i);
            layerx.setTranslation(bodyx.getPosition().x/M_PER_PIXEL,bodyx.getPosition().y/M_PER_PIXEL);
            layerx.setRotation(bodyx.getAngle());
            int booX=booEnemys.get(i);
            if(booX==0&&layerx.destroyed()==false){
                layerx.setAlpha(alpa);
                if(alpaTimes==6){
                    world.destroyBody(bodyx);
                    layerx.destroy();
                    diecount++;
                    alparun=false;
                    SaveStatic.money+=1;
                    System.out.println("ALPHA = "+alpaTimes);
                    System.out.println("die = "+diecount);
                    z.layer().setAlpha(1);


                }

            }
        }
        if(bossHP<=0){
            bossimg.setAlpha(0);
            skillLayer1.setTranslation(999,25);
            skillLayer2.setTranslation(999,25);
            skillLayer3.setTranslation(999,25);
            z.layer().setTranslation(999,999);
            hqLayer.setTranslation(999,999);
            winLayer.setAlpha(win);
            win+=0.03f;
            if(win>6){
                SaveStatic.save();
                ss.remove(ss.top());
            }
        }
        if(hp<=0){

            bossimg.setAlpha(0);
            skillLayer1.setTranslation(999,25);
            skillLayer2.setTranslation(999,25);
            skillLayer3.setTranslation(999,25);
            z.layer().setTranslation(999,999);
            hqLayer.setTranslation(999,999);
            loseLayer.setAlpha(win);
            win+=0.03f;
            if(win>6){
                SaveStatic.save();
                ss.remove(ss.top());}
        }

        int money=SaveStatic.money;
        int money100=(money%1000)/100;
        int money10=(money%100)/10;
        int money1=(money%10)/1;

        n100.setSpriteNumber(money100);
        n10.setSpriteNumber(money10);
        n1.setSpriteNumber(money1);

        switch (SaveStatic.bulletType){
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
                bulletBody.setLinearVelocity(new Vec2(0, 0));
                break;

        }



        for(int i=0;i<10;i++){
            Body now=bodyEnemys.get(i);

            float xx=now.getPosition().x;
            float yy=now.getPosition().y;
            if((xx>=9.0)&&(xx<=15.0)&&(yy>=0)&&(yy<=5)&&isdamaged==false){
                alpaTimes2=0;
                hp=hp-1;
                isdamaged=true;
                now.applyLinearImpulse(new Vec2(0,8),now.getPosition());
            }
            if((xx>=0)&&(xx<=26)&&(yy>=0)&&(yy<=3)){
                now.applyLinearImpulse(new Vec2(0,12),now.getPosition());
            }
        }



        bossDelta+=delta;
    if (bossDelta>2000){

        float x=rand.nextInt(100);
        x=x-50;
        bossdy.applyLinearImpulse(new Vec2(x,-10),bossdy.getPosition());
        bossDelta=0;
    }
    }catch (Exception e){System.out.println("!BAD!");}

    }


    @Override
    public void paint(Clock clock) {
        try{
        super.paint(clock);
        bgLayer4.setRotation(nabody.getAngle());
        bgLayer4.setRotation(rotate);
        z.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
        z.layer().setRotation(i);
        //z.layer().setTranslation(body.getPosition().x/M_PER_PIXEL,body.getPosition().y/M_PER_PIXEL);
        //  z2.layer().setTranslation(body2.getPosition().x / M_PER_PIXEL, body2.getPosition().y / M_PER_PIXEL);
        bgLayer4.setTranslation(nabody.getPosition().x/M_PER_PIXEL,nabody.getPosition().y/M_PER_PIXEL);
        bossimg.setTranslation(bossdy.getPosition().x/M_PER_PIXEL,bossdy.getPosition().y/M_PER_PIXEL);
        bossimg.setRotation(bossdy.getAngle());



        for (int i = 0; i < bodys.size(); i++) {
            Body bodyx = bodys.get(i);
            ImageLayer layerx = nalayers.get(i);
            layerx.setTranslation(bodyx.getPosition().x/M_PER_PIXEL,bodyx.getPosition().y/M_PER_PIXEL);
            layerx.setRotation(rotate);

        }



    }catch (Exception e){System.out.println("!BAD!");}



    }

}












