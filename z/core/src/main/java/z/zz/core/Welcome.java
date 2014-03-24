package z.zz.core;

import playn.core.Font;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import react.UnitSlot;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.PlayN.*;

import java.io.*;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;


public class Welcome extends UIScreen {

    private ScreenStack ss = new ScreenStack();
 //   Screen stage =new StageSelect(ss);
    ImageLayer bgLayer;
    ImageLayer bgLayer2;
    ImageLayer bgLayer3;
    boolean checker=true;



    public Welcome(ScreenStack ss) {
        this.ss = ss;
    }

    @Override
    public void wasShown() {
        try{

        Image bgImage = assets().getImage("images/celldf.png");
         bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        Image bgImage2 = assets().getImage("images/lowstart.png");
         bgLayer2 = graphics().createImageLayer(bgImage2);
        graphics().rootLayer().add(bgLayer2);

        Image bgImage3 = assets().getImage("images/lowgrade.png");
         bgLayer3 = graphics().createImageLayer(bgImage3);
        graphics().rootLayer().add(bgLayer3);

        bgLayer2.setTranslation(100,340);
        bgLayer3.setTranslation(330,340);

        bgLayer2.addListener(new Pointer.Adapter()
        {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                checker=false;
            }
        }
        );

        bgLayer3.addListener(new Pointer.Adapter()
        {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new UpGrade(ss));
            }
        }
        );

    SaveStatic.load();
        }catch (Exception e){System.out.println("!BAD!");}

    }


    float alpa=0.0f;
    float vicealpa=1f;
    @Override
    public void update(int delta) {
        try{
        super.update(delta);
        if(alpa<1&&checker==true){alpa=alpa+0.04f;
        bgLayer3.setAlpha(alpa);
        bgLayer2.setAlpha(alpa);
        bgLayer.setAlpha(alpa);
        }
        if(vicealpa>0&&checker==false){
            vicealpa=vicealpa-0.1f;
            bgLayer3.setAlpha(vicealpa);
            bgLayer2.setAlpha(vicealpa);
            bgLayer.setAlpha(vicealpa);
        }
        if(vicealpa<=0&&checker==false){
            checker=true;
            alpa=0.0f;
            vicealpa=1f;
            ss.push(new StageSelect(ss));
        }


    }catch (Exception e){System.out.println("!BAD!");}


    }


}
