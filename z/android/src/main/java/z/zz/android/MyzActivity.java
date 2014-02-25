package z.zz.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import z.zz.core.Myz;

public class MyzActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new Myz());
  }
}
