public class Calculator {
  //fields
  int currentValue = 0;
  //constructor
  public Calculator() {}

  //add
  public int add(int a) {
    currentValue += a;
    return currentValue;
  }

  //subtract
  public int subtract(int a) {
    currentValue -= a;
    return currentValue;
  }

  //multiply
  public int multiply(int a) {
    currentValue *= a;
    return currentValue;
  }

  //divide
  public int divide (int a) {
    currentValue /= a;
    return currentValue;
  }

  //modulo
  public int modulo (int a) {
    currentValue %= a;
    return currentValue;
  }

  //clear
  public void clear() {
    currentValue = 0;
  }

  //main class
  public static void main(String[] args) {
    Calculator myCalculator = new Calculator();
    System.out.println(myCalculator.add(5));
    System.out.println(myCalculator.multiply(11));
  }
}