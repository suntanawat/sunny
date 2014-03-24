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

import static playn.core.PlayN.*;


public class StageSelect extends UIScreen {

    private ScreenStack ss = new ScreenStack();
    ImageLayer bgLayer2;
    ImageLayer bgLayer;
    ImageLayer bgLayer31;
    ImageLayer bgLayer32;
    ImageLayer bgLayer33;



    public StageSelect(ScreenStack ss) {
        this.ss = ss;
    }

    @Override
    public void wasShown() {
        try{
        super.wasShown();
            SaveStatic.load();
        Image bgImage = assets().getImage("images/celldf2.png");
        bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        Image bgImage2 = assets().getImage("images/lowback.png");
        bgLayer2 = graphics().createImageLayer(bgImage2);
        graphics().rootLayer().add(bgLayer2);

        Image bgImage31 = assets().getImage("images/stage1.png");
        bgLayer31 = graphics().createImageLayer(bgImage31);
        graphics().rootLayer().add(bgLayer31);

        Image bgImage32 = assets().getImage("images/stage2.png");
        bgLayer32 = graphics().createImageLayer(bgImage32);
        graphics().rootLayer().add(bgLayer32);

        Image bgImage33 = assets().getImage("images/stage3.png");
        bgLayer33 = graphics().createImageLayer(bgImage33);
        graphics().rootLayer().add(bgLayer33);

        final Image bgImage4 = assets().getImage("images/select.png");
        final ImageLayer bgLayer4 = graphics().createImageLayer(bgImage4);
        graphics().rootLayer().add(bgLayer4);

        Image bgImage5 = assets().getImage("images/black.png");
        final ImageLayer bgLayer5 = graphics().createImageLayer(bgImage5);


        bgLayer2.setTranslation(220,280);

        bgLayer31.setTranslation(160, 160);
        bgLayer32.setTranslation(280, 130);
        bgLayer33.setTranslation(390, 160);

        if(SaveStatic.stage==1){
            bgLayer31.setTranslation(160, 160);
            bgLayer32.setTranslation(999, 130);
            bgLayer33.setTranslation(999, 160);
        }
        else if(SaveStatic.stage==2){
            bgLayer31.setTranslation(160, 160);
            bgLayer32.setTranslation(280, 130);
            bgLayer33.setTranslation(999, 160);
        }
        else if(SaveStatic.stage==3){
            bgLayer31.setTranslation(160, 160);
            bgLayer32.setTranslation(280, 130);
            bgLayer33.setTranslation(390, 160);
        }

        bgLayer4.setTranslation(120, 0);

        bgLayer2.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());
            }
        });

        bgLayer31.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {

                graphics().rootLayer().add(bgLayer5);
                ss.push(new Page2(ss));
            }
        }
        );
        bgLayer32.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {

                graphics().rootLayer().add(bgLayer5);
                ss.push(new Page3(ss));
            }
        }
        );
        bgLayer33.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {

                graphics().rootLayer().add(bgLayer5);
                ss.push(new Page4(ss));
            }
        }
        );
    }catch (Exception e){System.out.println("!BAD!");}

    }

    float alpa=0.0f;
    @Override
    public void update(int delta) {
        try{
        super.update(delta);
        if(alpa<1){alpa=alpa+0.03f;
        bgLayer2.setAlpha(alpa);
        bgLayer.setAlpha(alpa);
            bgLayer31.setAlpha(alpa);
            bgLayer32.setAlpha(alpa);
            bgLayer33.setAlpha(alpa);
        }
        }catch (Exception e){System.out.println("!BAD!");}

    }
}














