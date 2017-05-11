class Complex{
  private double i=0;
  private double r=0;

  public Complex(double i, double r){
    this.i=i;
    this.r=r;
  }

  public Complex square(){
    return new Complex((r*r-i*i),(2*r*i));
  }

  public Complex times(Complex x){
    return new Complex((r*x.getR()-i*x.getI()),(i*x.getR()+r*x.getI()));
  }
  public Complex add(Complex x){
    return new Complex((x.getR()+r),(x.getI()+i));
  }

  public double getI(){
    return i;
  }
  public double getR(){
      return r;
  }



}
