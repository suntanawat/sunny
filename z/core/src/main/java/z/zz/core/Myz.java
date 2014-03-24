package z.zz.core;

import static playn.core.PlayN.*;

import playn.core.*;
import playn.core.util.Clock;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

public class Myz extends Game.Default {

    public static final int UPDATE_RATE =25;
    private ScreenStack ss = new ScreenStack();
    private Clock.Source clock = new Clock.Source(UPDATE_RATE);

  public Myz() {
    super(UPDATE_RATE); // call update every 33ms (30 times per second)
  }


  @Override
  public void init() {
    // create and add background image layer
      /*
    Image bgImage = assets().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);
    */
      /*
      final Screen home = new HomeScreen(ss);
      ss.push(new HomeScreen(ss));
      PlayN.keyboard().setListener(new Keyboard.Adapter(){
          @Override
      public void onKeyUp(Keyboard.Event event){if(event.key()==Key.ENTER)
          {ss.popTo(home);}
          }
            });
            */
      final Screen home = new HomeScreen(ss);
      final Screen home2 = new Page2(ss);
      final Screen well = new Welcome(ss);
      ss.push(well);


  }

  @Override
  public void update(int delta) {
       ss.update(delta);



  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
    clock.paint(alpha);
    ss.paint(clock);

  }
}
