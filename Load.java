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
    System.out.print("1[Random] or 0[Normal] or 2[Methodical] >>");
    final int rand=scan.nextInt();

    new AnimationTimer(){
      public int i=-1;
      public double dx=dxx;
      public double dy=dyx;
      public double s=sx;
      public boolean dark=false;
      public boolean light=false;

      public void handle(long currentNanoTime){
        if(i<width){
          i++;
          for(int j=0;j<height;j++){
            Point p=new Point(i,j,s,dx,dy);
            int c=p.getValue();
            gc.setFill(Color.rgb(c,c,c));
            if(c>100){
              light=true;
            }else if(c<100){
              dark=true;
            }
            gc.fillOval(i,j,1,1);
          }
        }else if(rand !=0){
          if(dark&&light){
            System.out.println("SEE ME!");
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
            dy=Math.random()*2-1;
            dx=Math.random()*2-1;
            s=Math.random()*20000;
          }else if(rand==2){
            dy+=height/s;
            if(dy>2){
              dx+=width/s;
              dy=-2;
              if(dx>2){
                dy=-2;
                dx=-2;
                s=s*2;
              }
            }
          }

          System.out.println("Scale ="+s+" dx="+dx+" dy="+dy);
        }


      }

    }.start();

    alpha.show();
  }
}
