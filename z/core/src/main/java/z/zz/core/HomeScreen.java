package z.zz.core;

import playn.core.Font;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import playn.core.PlayN.*;

import static playn.core.PlayN.graphics;


public class HomeScreen extends UIScreen {

    public static final  Font TITLE_FONT = graphics().createFont("Helvetica",Font.Style.PLAIN,24);
    private ScreenStack ss = new ScreenStack();
    private Root root;

    public HomeScreen(ScreenStack ss) {
        this.ss = ss;
    }


    @Override
    public void wasShown() {
        super.wasShown();
        root=iface.createRoot(
                AxisLayout.vertical().gap(15),
                SimpleStyles.newSheet(),layer);
        root.addStyles(Style.BACKGROUND
                .is(Background.bordered(0xFFCCCCCC, 0xFF000000, 5)
                .inset(5, 10)));
        root.setSize(width(), height());
        root.add(new Label("ERROR PROGRAM GAMING").addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
        root.add(new Button("Satart").onClick(new UnitSlot() {

            public void onEmit() {
                ss.push(new Page2(ss));

            }
        }));
    }
}
