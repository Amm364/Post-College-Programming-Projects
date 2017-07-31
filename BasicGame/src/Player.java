import java.util.Random;

public class Player {
	int health;
	int defence;
	int stealth;
	int attack;
	int numberOfPotions;
	int gems;
	String name;
	Coordinate location;
	
	public Player(String n, int h, int d, int s, int a,int x, int y){
		name=n;
		health=h;
		defence=d;
		stealth=s;
		attack=a;
		numberOfPotions=0;
		gems=0;
		location = new Coordinate(x,y);
	}
	
	public void changeLocation(int x, int y){
		location.setNewCoordinates(x, y);
	}
	
	public void attack(Monster monster){
		Random rand = new Random();
		int monsterDefence = monster.defend();
		int playerAttack = (int)(rand.nextDouble() * attack);
		int damageDone = playerAttack - monsterDefence;
		if (damageDone > 0){
			monster.damageHealth(damageDone);
			System.out.println("You have done " + damageDone + " damage.");
		}
		else{
			System.out.println("You did not do any damage.");
		}
	}
	
	public int defend(){
		Random rand = new Random();
		int defenceValue = (int)(rand.nextDouble() * defence);
		return defenceValue;
	}
	
	public void damageHealth(int damage){
		health-=damage;
	}
	
	public boolean isDead(){
		if (health<=0)return true;
		else return false;
	}
	
	public String getName(){
		return name;
	}
	
	public Coordinate getLocation(){
		return location;
	}
	
	public void openChest(Chest chest){
		System.out.println(chest);
		if (chest.takeTreasure()==0){
			numberOfPotions++;
			gems++;
		}
		else if (chest.takeTreasure()==1){
			numberOfPotions++;
		}
		else if (chest.takeTreasure()==2){
			gems++;
		}
	}
}
