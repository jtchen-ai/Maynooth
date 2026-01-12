package experiment2_2;

abstract class Prototype implements Cloneable{
    public Prototype clone() throws CloneNotSupportedException{
      return (Prototype)super.clone();
}
}