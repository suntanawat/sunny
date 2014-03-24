package z.zz.core;

import java.io.*;

/**
 * Created by sunny on 3/20/14.
 */
public class SaveStatic {
    public static boolean bullet1buyed=true;
    public static boolean bullet2buyed=false;
    public static boolean bullet3buyed=false;

    public static enum BulletType{BULLET1,BULLET2,BULLET3,NONE};
    public static BulletType bulletType = BulletType.BULLET1;

    public static int money=0;
    public static int stage=1;





    public static void save(){
        String fileName = "SAVE_GAME/SunnySave.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("==Money==");
            bufferedWriter.newLine();
            bufferedWriter.write(""+SaveStatic.money);
            bufferedWriter.newLine();
            bufferedWriter.write("==BulletType==");
            bufferedWriter.newLine();
            String btype="";
            if(SaveStatic.bulletType==SaveStatic.BulletType.BULLET1){ btype="1";}
            else if(SaveStatic.bulletType==SaveStatic.BulletType.BULLET2){ btype="2";}
            else if(SaveStatic.bulletType==SaveStatic.BulletType.BULLET3){ btype="3";}
            else { btype="0";}
            bufferedWriter.write(""+btype);
            bufferedWriter.newLine();
            bufferedWriter.write("==Bullet1==");
            bufferedWriter.newLine();
            bufferedWriter.write(""+SaveStatic.bullet1buyed);
            bufferedWriter.newLine();
            bufferedWriter.write("==Bullet2==");
            bufferedWriter.newLine();
            bufferedWriter.write(""+SaveStatic.bullet2buyed);
            bufferedWriter.newLine();
            bufferedWriter.write("==Bullet3==");
            bufferedWriter.newLine();
            bufferedWriter.write(""+SaveStatic.bullet3buyed);
            bufferedWriter.newLine();
            bufferedWriter.write("==STAGE==");
            bufferedWriter.newLine();
            bufferedWriter.write(""+SaveStatic.stage);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Saving...");
    }



    public static void load(){

        String fileName = "SAVE_GAME/SunnySave.txt";
        String line = null;
        int i=0;
        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                if(i==0){
                    if (line==null){System.out.println("Save NULL!!");break;}
                }

                if(i==1){
                    int money=Integer.valueOf(line);
                    SaveStatic.money=Integer.valueOf(line);
                }
                if(i==3){
                    if(line.equals("1")){SaveStatic.bulletType= SaveStatic.BulletType.BULLET1;}
                    else if(line.equals("2")){SaveStatic.bulletType= SaveStatic.BulletType.BULLET2;}
                    else if(line.equals("3")){SaveStatic.bulletType= SaveStatic.BulletType.BULLET3;}
                    else {SaveStatic.bulletType= SaveStatic.BulletType.NONE;}
                }
                if(i==5){
                    if(line.equals("true")){SaveStatic.bullet1buyed=true;}
                    else{SaveStatic.bullet1buyed=false;}
                }
                if(i==7){
                    if(line.equals("true")){SaveStatic.bullet2buyed=true;}
                    else{SaveStatic.bullet2buyed=false;}
                }
                if(i==9){
                    if(line.equals("true")){SaveStatic.bullet3buyed=true;}
                    else{SaveStatic.bullet3buyed=false;}

                }
                if(i==11){
                    if(line.equals("1")){SaveStatic.stage=1;}
                    else if(line.equals("2")){SaveStatic.stage=2;}
                    else if(line.equals("3")){SaveStatic.stage=3;}
                    else{SaveStatic.stage=1;}
                }

                i+=1;
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '"+fileName+"'");}
        catch(IOException ex) {
            System.out.println("Error reading file '"+fileName+"'");
        }
        System.out.println("Loadeding...");
    }

}
