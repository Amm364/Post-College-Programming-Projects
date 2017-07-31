import java.util.Random;

public class Chest {
	boolean potion;
	boolean gem;
	
	public Chest(){
		Random rand = new Random();
		if (rand.nextDouble()<0.7) gem = true;
		else gem = false;
		if (rand.nextDouble()<0.4) potion = true;
		else potion = false;
		
	}
	
	public String toString(){
		if (potion && gem){
			return "The chest contains a health potion and a gem.";
		}
		else if (potion && !gem){
			return "The chest contains a potion.";
		}
		else if (!potion && gem){
			return "The chest contains a gem.";
		}
		else{
			return "The chest contains nothing.";
		}
	}
	
	public int takeTreasure(){
		if (potion && gem){
			potion = false;
			gem = false;
			return 0;
		}
		else if (potion && !gem){
			potion = false;
			return 1;
		}
		else if (!potion && gem){
			gem = false;
			return 2;
		}
		else{
			return 3;
		}
	}
}
