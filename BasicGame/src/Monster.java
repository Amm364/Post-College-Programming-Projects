import java.util.Random;

public class Monster {
	
	int attack;
	int defence;
	String description;
	int health;
	int stealthThreshold;
	String name;
	
	public Monster(String n,String desc,int hp, int def, int atk, int ht){
		attack = atk;
		defence = def;
		description = desc;
		health = hp;
		name = n;
		stealthThreshold=ht;
	}
	
	public void attack(Player player){
		Random rand = new Random();
		int PlayerDefence = player.defend();
		int MonsterAttack = (int)(rand.nextDouble() * attack);
		int damageDone = MonsterAttack - PlayerDefence;
		if (damageDone > 0){
			player.damageHealth(damageDone);
			System.out.println("You have taken " + damageDone + " damage.");
		}
		else{
			System.out.println("You did not take any damage.");
		}
	}
	public int defend(){
		Random rand = new Random();
		int defenceValue = (int)(rand.nextDouble() * defence);
		return defenceValue;
	}
	public String getDescription(){
		return description;
	}
	public int getCurrentHealth(){
		return health;
	}
	public String getName(){
		return name;
	}
	public void damageHealth(int damage){
		health-=damage;
	}
	public boolean isDead(){
		if (health<=0)return true;
		else return false;
	}
}
