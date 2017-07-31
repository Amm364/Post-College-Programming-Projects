import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Map {
	Room[][] map;
	int x;
	int y;
	int xMax;
	int yMax;
	
	public Map(File mapFile, ArrayList<Monster> monsterList){
		Scanner in = null;
		Random rand = new Random();
		try {
			in = new Scanner(new FileReader(mapFile));
		} catch (FileNotFoundException e) {
			System.out.println("Map file not found.");
			System.exit(0);
		}
		if (in.hasNextLine()){
			String[] params = in.nextLine().split(" ");
			if (params.length!=2){
				System.out.println("Incorrect parameter format. (L W)");
				System.exit(0);
			}
			xMax=Integer.parseInt(params[0]);
			yMax=Integer.parseInt(params[1]);
			map = new Room[xMax][yMax];
			x=0;
			y=0;
		}
		else{
			System.out.println("File has no contents");
		}
		while (in.hasNextLine()){
			String[] currentRow = in.nextLine().split(" ");
			for(String s:currentRow){
				if (s.equals("#")) map[x][y]=new Room(false);
				else{
					Chest c = null;
					Monster m = null;
					if (rand.nextDouble()>=0.35){
						m = monsterList.get(rand.nextInt(monsterList.size()));
					}
					if (rand.nextDouble()>=0.25){
						c = new Chest();
					}
					map[x][y]=new Room(true,m,c);
				}
				x++;
			}
			x=0;
			y++;
		}
	}
	
	public String toString(){
		String mapString="";
		for (int j=0;j<yMax;j++){
			for (int i=0; i<xMax;i++){
				mapString+=map[i][j].getMapSymbol();
			}
			mapString+="\n";
		}
		return mapString;
	}
	
	public void getChoices(Player player){
		Coordinate current = player.getLocation();
		if (map[current.xCord][current.yCord].isChest()){
			System.out.println("You see a chest in the room.");
		}
		if (map[current.xCord+1][current.yCord].isRoom){
			System.out.println("There is a room to the east.");
		}
		if (map[current.xCord][current.yCord-1].isRoom){
			System.out.println("There is a room to the north.");
		}
		if (map[current.xCord-1][current.yCord].isRoom){
			System.out.println("There is a room to the west.");
		}
		if (map[current.xCord][current.yCord+1].isRoom){
			System.out.println("There is a room to the south.");
		}
		if (map[current.xCord][current.yCord].isMonster()){
			if (!map[current.xCord][current.yCord].monster.isDead()){
				System.out.println("There is a " + map[current.xCord][current.yCord].monster.getName() + " in the room.");
			}
		}
	}
}
