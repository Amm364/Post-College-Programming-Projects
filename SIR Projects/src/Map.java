import java.util.Random;

public class Map {
	Random rand = new Random();
	Unit[][] map;
	int length;
	double infectionChance;
	double deathChance;
	double recoveryChanceInfected;
	double recoveryChanceSusceptible;
	int immune;
	int dead;
	public Map(int dimension, double infect, double death, double recoverInfect, double recoverSuscept){
		map = new Unit[dimension][dimension];
		length = dimension;
		infectionChance = infect;
		deathChance = death;
		recoveryChanceInfected = recoverInfect;
		recoveryChanceSusceptible = recoverSuscept;
		immune = 0;
		dead = 0;
	}
	public void PopulateMap(){
		for (int i = 0; i < length; i++){
			for (int j = 0; j < length; j++){
				map[i][j] = new Unit(i,j);
			}
		}
	}
	
	public void UpdateMap(){
		for (int i = 0; i < length; i++){
			for (int j = 0; j < length; j++){
				double chance = map[i][j].chanceOfInfection;
				if (map[i][j].isDead == true || map[i][j].isImmune == true) continue;
				if (rand.nextDouble() <= recoveryChanceInfected && map[i][j].isInfected == true){
					map[i][j].MakeImmune();
					immune++;
					continue;
				}
				if (rand.nextDouble() <= recoveryChanceSusceptible && map[i][j].isInfected == false){
					map[i][j].MakeImmune();
					immune++;
					continue;
				}
				if (rand.nextDouble() <= deathChance){
					map[i][j].Death();
					dead++;
					continue;
				}
				if (rand.nextDouble() <= chance){
					map[i][j].Infect(map, infectionChance);
				}
			}
		}
	}
	
	public void InitializeInfected(int numberOfInfected){
		for(int i = 0; i<numberOfInfected;i++){
			int x=rand.nextInt(length);
			int y=rand.nextInt(length);
			if (map[x][y].isInfected == false) map[x][y].Infect(map, infectionChance);
			else{
				i-=1;
				continue;
			}
		}
	}
	
	public boolean StatusCheck(){
		if (dead + immune == length * length){
			System.out.println(dead + " " + immune + " " + (length*length));
			return false;
		}
		else{
			return true;
		}
	}
}
