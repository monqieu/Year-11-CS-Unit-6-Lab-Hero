public class Hero {
    private String name;
    private int hitPoints;

    //Constructor
    public Hero(String name){
        this.name = name;   //sets the Hero's name from the parameter
        this.hitPoints = 100;   //New Hero starts with 100 pts
    }

    public String getName(){
        return name;    // Returns hero's name when requested
    }

    public int getHitPoints(){
        return hitPoints;   //Returns hero's hitpoints when requested
    }

    public String toString(){
        return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
    }

    public void attack(Hero opponent){
        double random =  Math.random();
        if (random < 0.5) {
            opponent.hitPoints -= 10;
        } else {
            this.hitPoints -= 10;
        }
    }

    public void senzuBean(){
        this.hitPoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opponent){
        while(this.hitPoints>0 && opponent.hitPoints>0){
            attack(opponent);   //keeps on attacking until someone dies
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();

        fightUntilTheDeathHelper(opponent);

        return this.name + ": " + this.hitPoints + "\n" + opponent.name + ": " + opponent.hitPoints;
    }

    //private helper method that does the actual fighting and counting
    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        // Create array to store wins: index 0 for this hero, index 1 for opponent
        int[] wins = new int[2];    // wins[0] = 0, wins[1] = 0

        // loop n times (n is the number of fights requested
        for(int i = 0; i < n; i++){
            //restore each hero's health
            this.senzuBean();
            opponent.senzuBean();

            //fight until once someone dies
            fightUntilTheDeathHelper(opponent);

            if(this.hitPoints > 0){
                wins[0]++;   // this hero won, increment their wins
            }else{
                wins[1]++;  //opponent won, increment their wins
            }
        }
        return wins;    // return the array containing the total wins for each hero
    }

    // Public method that creates the formatted output string
    public String nFightsToTheDeath(Hero opponent, int n) {
        // Get the results of all fights
        int[] results = nFightsToTheDeathHelper(opponent, n);

        // Build the output string starting with win counts
        String output = this.name + ": " + results[0] + " wins\n" +
                opponent.name + ": " + results[1] + " wins\n";

        // Add the final outcome message
        if (results[0] == results[1]) {
            output += "OMG! It was actually a draw!";
        } else {
            output += (results[0] > results[1] ? this.name : opponent.name) + " wins!";
        }

        return output;
    }

    public void dramaticFightToTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();

        while(this.hitPoints > 0 && opponent.hitPoints > 0){    //while both are alive
           attack(opponent);
            System.out.println(this.name + ": " + this.hitPoints + "\t" + opponent.name + " :" + opponent.hitPoints);
        }

        if(this.hitPoints > 0){
            System.out.println(this.name + " wins!");
        } else {
            System.out.println(opponent.name + " wins!");
        }
    }

}
