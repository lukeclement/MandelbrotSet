class Point{
  private int xTrue;
  private int yTrue;

  private double value;
  private Complex start;

  private double x;
  private double y;

  public Point(int x, int y, double scale, double dx, double dy){
    xTrue=x;
    yTrue=y;
    this.x=(double)x/scale+dx;
    this.y=(double)y/scale+dy;
    start=new Complex(this.x,this.y);
  }

  public int getValue(){
    Complex z=new Complex(0,0);
    int iterations=0;
    while((z.getI()<=2.0E+307||z.getR()<=2.0E+307)&&iterations<255){
      z=z.square().add(start);
      iterations++;
    }
    return iterations;
  }
}
