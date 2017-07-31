
public class MonsterStats {
	String name;
	String description;
	int health;
	int defense;
	int attack;
	
	public MonsterStats(String n,String desc,int hp, int def, int atk){
		name = n;
		description = desc;
		health = hp;
		defense = def;
		attack = atk;
	}
	
	public String GetName(){
		return name;
	}
	
	public String GetDescription(){
		return description;
	}
	
	public int GetHealth(){
		return health;
	}
	
	public int GetDefense(){
		return defense;
	}
	
	public int GetAttack(){
		return attack;
	}
}
