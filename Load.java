import java.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.animation.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;

public class Load extends Application{
  public static int width=750;
  public static int height=750;
  public static void main(String[] args){
    launch(args);
  }
  public void start(Stage alpha){
    alpha.setTitle("HeyHo");

    Group root=new Group();
    Scene scene=new Scene(root);
    Scanner scan=new Scanner(System.in);

    alpha.setScene(scene);

    Canvas canvas=new Canvas(width,height);
    root.getChildren().add(canvas);

    GraphicsContext gc=canvas.getGraphicsContext2D();

    System.out.println("Scale:");
    System.out.print(">>");
    final double sx=scan.nextDouble();
    System.out.println("dx:");
    System.out.print(">>");
    final double dxx=scan.nextDouble();
    System.out.println("dy:");
    System.out.print(">>");
    final double dyx=scan.nextDouble()*(-1);

    System.out.println("Mode?");
    System.out.print("1[Random] or 2[Normal] or 0[Methodical] >>");
    final int rand=scan.nextInt();

    new AnimationTimer(){
      public int i=-1;
      public double dx=dxx;
      public double dy=dyx;
      public double s=sx;
      public boolean dark=false;
      public boolean light=false;
      public int fail=10;

      public void handle(long currentNanoTime){
        if(i<width){
          i++;
          for(int j=0;j<height;j++){
            Point p=new Point(i,j,s,dx,dy);
            int c=p.getValue();
            gc.setFill(Color.rgb(c%256,c%256,c%256));
            if(c>100){
              light=true;
            }else if(c<100){
              dark=true;
            }
            gc.fillOval(i,j,1,1);
          }
        }else if(rand !=2){
          boolean boop=false;
          if(dark&&light){
            System.out.println("SEE ME!");
            boop=true;
          }
          dark=false;
          light=false;
          //System.out.println("Resetting...");
          for(int q=0;q<width;q++){
            for(int w=0;w<height;w++){
              gc.setFill(Color.rgb(255,255,255));
              gc.fillOval(q,w,1,1);
            }
          }
          i=0;
          if(rand==1){
            dy=Math.random()*4-2;
            dx=Math.random()*4-2;
            s=Math.random()*10000;
          }else if(rand==0){
            if(boop){
              s=s*2;
              fail=0;
            }else{
              fail++;
              dy+=height/s;
              if(dy>1){
                dx+=width/s;
                dy=-1;
                if(dx>1){
                  dy=-1;
                  dx=-1;
                  s=s*2;
                }
              }

              if(fail>10||s>=1.0E+12){
                dy=Math.random()*4-2;
                dx=Math.random()*4-2;
                s=Math.random()*10000;
              }
            }

          }

          System.out.println("Scale ="+s+" dx="+dx+" dy=-"+dy);
        }


      }

    }.start();

    alpha.show();
  }
}
