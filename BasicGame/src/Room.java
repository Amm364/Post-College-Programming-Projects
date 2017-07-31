
public class Room {
	boolean isRoom;
	Monster monster;
	Chest chest;
	
	public Room(boolean room){
		isRoom = room;
	}
	
	public Room(boolean room, Monster m, Chest c){
		isRoom = room;
		monster = m;
		chest = c;
	}
	
	public boolean isWall(){
		if (isRoom) return true;
		else return false;
	}
	
	public boolean isMonster(){
		if (monster == null) return false;
		else return true;
	}
	
	public boolean isChest(){
		if (chest == null) return false;
		else return true;
	}
	
	public String toString(){
		if (isChest() && isMonster()){
			return "The room contains a chest and a monster(" + monster.getName() + ").";
		}
		else if (isChest() && !isMonster()){
			return "The room contains a chest.";
		}
		else if (!isChest() && isMonster()){
			return "The room contains a monster(" + monster.getName() + ").";
		}
		else{
			return "The room contains nothing.";
		}
	}
	
	public String getMapSymbol(){
		if (isRoom) return "! ";
		else return "# ";
	}
}
