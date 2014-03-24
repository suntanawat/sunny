package z.zz.core;

import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.util.Callback;
import tripleplay.game.UIScreen;
import z.zz.sprite.Sprite;
import z.zz.sprite.SpriteLoader;

/**
 * Created by sunny on 3/20/14.
 */
public class Number extends UIScreen {

    private Sprite sprite;
    private int spriteIndex=0;
    private boolean hasLoaded=false;

    public Number()
    {
        sprite= SpriteLoader.getSprite("images/number.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(13,23);

                hasLoaded=true;
            }
            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("HIAAAA_MANG_ERROR!",cause);
            }

        });



    }
    public ImageLayer pic(){
        return this.sprite.layer();
    }
    public void setSpriteNumber(int n){
        if(n>9){n=n%10;}
        sprite.setSprite(n);
    }
    public void destroy(){
        sprite.layer().destroy();
    }

    float e=0;
    public void update(int delta){
        if (!hasLoaded)return;
        e+=delta;
        if(e>200){
            spriteIndex=((spriteIndex+1)%10);
            sprite.setSprite(spriteIndex);
            e=0;
        }

    }
}
