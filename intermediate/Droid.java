public class Droid {
  //fields
  String name;
  double batteryLevel;
  
  //constructor
  public Droid(String droidName) {
    name = droidName;
    batteryLevel = 100;
  }

  //toString
  public String toString() {
    return "Hello, I'm a droid and my name is " + name + ".";
  }

  //perform a task
  public void performTask(String task) {
    System.out.println(name + " is now performing the task: " + task + "!");
    batteryLevel -= 10;
  }

  //energy report
  public double energyReport() {
    System.out.println(batteryLevel);
    return batteryLevel;
  }

  //energy transfer
  public void transferEnergy(Droid bot, double energy) {
    batteryLevel -= energy;
    bot.batteryLevel += energy;
  }



  public static void main (String[] args) {
    Droid bot = new Droid("Codey");
    System.out.println(bot);

    bot.performTask("Hunt rabbits");
    bot.performTask("Fight discord bots");
    bot.performTask("Code java");

    Droid newBot = new Droid("Codey");
    System.out.println(newBot);
    newBot.energyReport();
    newBot.transferEnergy(bot, 20);
    System.out.println(bot.batteryLevel);
  }

}
