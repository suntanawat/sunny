package z.zz.core;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.PlayN.*;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import playn.core.*;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import z.zz.sprite.Sprite;
import z.zz.sprite.SpriteLoader;

import static playn.core.PlayN.*;


public class UpGrade extends UIScreen {

    private ScreenStack ss = new ScreenStack();
    ImageLayer bgLayer2;
    ImageLayer bgLayer;
    ImageLayer selectLayer1;
    ImageLayer selectLayer2;
    ImageLayer selectLayer3;
    ImageLayer buyLayer1;
    ImageLayer buyLayer2;
    ImageLayer buyLayer3;

    Seenot z =new Seenot(120,170);
    Bullet1 bullet1=new Bullet1();
    Bullet2 bullet2=new Bullet2();
    Bullet3 bullet3=new Bullet3();

    Bullet1 bullet11=new Bullet1();
    Bullet2 bullet22=new Bullet2();
    Bullet3 bullet33=new Bullet3();
    float y=210;
    int x1=999;
    int x2=999;
    int x3=999;
    Number n100=new Number();
    Number n10=new Number();
    Number n1=new Number();
    boolean pageClose = false;



    public UpGrade(ScreenStack ss) {
        this.ss = ss;
    }

    @Override
    public void wasAdded() {
        try{
        super.wasAdded();
            SaveStatic.load();
        Image bgImage = assets().getImage("images/upgrade.png");
        bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        Image bgImage2 = assets().getImage("images/bg.png");
        bgLayer2 = graphics().createImageLayer(bgImage2);
        graphics().rootLayer().add(bgLayer2);

        Image select1 = assets().getImage("images/selectbutt.png");
        selectLayer1 = graphics().createImageLayer(select1);
        graphics().rootLayer().add(selectLayer1);
        selectLayer2 = graphics().createImageLayer(select1);
        graphics().rootLayer().add(selectLayer2);
        selectLayer3 = graphics().createImageLayer(select1);
        graphics().rootLayer().add(selectLayer3);

        Image buy1 = assets().getImage("images/buybutt.png");
        buyLayer1 = graphics().createImageLayer(buy1);
        graphics().rootLayer().add(buyLayer1);
        buyLayer2 = graphics().createImageLayer(buy1);
        graphics().rootLayer().add(buyLayer2);
        buyLayer3 = graphics().createImageLayer(buy1);
        graphics().rootLayer().add(buyLayer3);


        bgLayer2.setTranslation(342,339);
        layer.add(z.layer());

        graphics().rootLayer().add(bullet1.pic().setTranslation(315,90));
        graphics().rootLayer().add(bullet2.pic().setTranslation(440,90));
        graphics().rootLayer().add(bullet3.pic().setTranslation(570,90));

        graphics().rootLayer().add(bullet11.pic().setTranslation(999,90));
        graphics().rootLayer().add(bullet22.pic().setTranslation(999,90));
        graphics().rootLayer().add(bullet33.pic().setTranslation(999,90));

        graphics().rootLayer().add(n100.pic().setTranslation(140,378));
        graphics().rootLayer().add(n10.pic().setTranslation(165,378));
        graphics().rootLayer().add(n1.pic().setTranslation(190,378));



        if(SaveStatic.bullet1buyed==true){
            selectLayer1.setTranslation(269,276);
            buyLayer1.setTranslation(999,276);}
        else{
            selectLayer1.setTranslation(999,276);
            buyLayer1.setTranslation(269,276);}

        if(SaveStatic.bullet2buyed==true){
            selectLayer2.setTranslation(398,276);
            buyLayer2.setTranslation(999,276);}
        else{
            selectLayer2.setTranslation(999,276);
            buyLayer2.setTranslation(398,276);}

        if(SaveStatic.bullet3buyed==true){
            selectLayer3.setTranslation(527,276);
            buyLayer3.setTranslation(999,276);}
        else{
            selectLayer3.setTranslation(999,276);
            buyLayer3.setTranslation(527,276);}


        selectLayer1.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.bulletType=SaveStatic.BulletType.BULLET1;
                SaveStatic.save();
            }
        });
        selectLayer2.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.bulletType=SaveStatic.BulletType.BULLET2;
                SaveStatic.save();
            }
        });
        selectLayer3.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.bulletType=SaveStatic.BulletType.BULLET3;
                SaveStatic.save();
            }
        });
        buyLayer1.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                    SaveStatic.bullet2buyed=true;
                    selectLayer1.setTranslation(269,276);
                    buyLayer1.setTranslation(999,276);}
        });
        SaveStatic.save();

        buyLayer2.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                if(SaveStatic.money>=15){
                    SaveStatic.money=SaveStatic.money-15;
                    SaveStatic.bullet2buyed=true;
                    selectLayer2.setTranslation(398,276);
                    buyLayer2.setTranslation(999,276);}
                SaveStatic.save();
            }
        });
        buyLayer3.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.bullet3buyed=true;
                selectLayer3.setTranslation(527,276);
                buyLayer3.setTranslation(999,276);
                SaveStatic.save();
            }
        });

        bgLayer2.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                SaveStatic.save();
                pageClose=true;
//                n100.setSpriteNumber(0);
//                n10.setSpriteNumber(0);
//                n1.setSpriteNumber(0);
//                z.setSpriteNumber(0);
//                bullet1.setSpriteNumber(0);
//                bullet2.setSpriteNumber(0);
//                bullet3.setSpriteNumber(0);
//                bullet11.setSpriteNumber(0);
//                bullet22.setSpriteNumber(0);
//                bullet33.setSpriteNumber(0);
//                alpa=0;
//                ss.remove(ss.top());
             //   ss.push(new Welcome(ss));
            }
        });
    }catch (Exception e){System.out.println("!BAD!");}


    }

    float alpa=0.0f;
    float e=210;
    boolean down=true;
    @Override
    public void update(int delta) {
        try{
        e+=delta;
        if(e>5){
            if(down){y=y+1;}
            else{y=y-1;}
            if (y>240){down=false;}
            if (y<210){down=true;}

            bullet11.pic().setTranslation(x1,y);
            bullet22.pic().setTranslation(x2,y);
            bullet33.pic().setTranslation(x3,y);
        }


        if(SaveStatic.bulletType == SaveStatic.BulletType.BULLET1)
        {   x1=119;
            x2=999;
            x3=999;}
        if(SaveStatic.bulletType == SaveStatic.BulletType.BULLET2)
        {   x1=999;
            x2=119;
            x3=999;}
        if(SaveStatic.bulletType == SaveStatic.BulletType.BULLET3)
        {   x1=999;
            x2=999;
            x3=119;}


        if(alpa<1){alpa=alpa+0.03f;

            bgLayer.setAlpha(alpa);
            bgLayer2.setAlpha(alpa);

        }
        z.update(delta);
        bullet1.update(delta);
        bullet2.update(delta);
        bullet3.update(delta);
        bullet11.update(delta);
        bullet22.update(delta);
        bullet33.update(delta);

        int money=SaveStatic.money;
        if(money>999){money=999;}
        int money100=(money%1000)/100;
        int money10=(money%100)/10;
        int money1=(money%10);

        n100.setSpriteNumber(money100);
        n10.setSpriteNumber(money10);
        n1.setSpriteNumber(money1);
        if(pageClose==true){
            n100.destroy();
            n10.destroy();
            n1.destroy();
            z.destroy();
            bullet1.destroy();
            bullet2.destroy();
            bullet3.destroy();
            bullet11.destroy();
            bullet22.destroy();
            bullet33.destroy();
            pageClose=false;
            ss.remove(ss.top());

        }

        }catch (Exception e){System.out.println("!BAD!");}


    }
}














