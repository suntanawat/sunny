package z.zz.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import z.zz.core.Myz;

public class MyzJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform.register(config);
    PlayN.run(new Myz());
  }
}
