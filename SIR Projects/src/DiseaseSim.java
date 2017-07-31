
public class DiseaseSim {

	public static void main(String[] args) {
		int mapLength = Integer.parseInt(args[0]);
		int infected = Integer.parseInt(args[1]);
		double baseRateSI = Double.parseDouble(args[2]);	// Base SI Rate
		double rateSI = baseRateSI;							// Susceptible to Infected
		double rateIR = Double.parseDouble(args[3]);		// Infected to Recovered
		double rateSR = Double.parseDouble(args[4]);		// Susceptible to Recovered
		double rateDeath = Double.parseDouble(args[5]);		// Infected to Dead
		int day = 0;
		Map grid = new Map(mapLength,rateSI,rateDeath,rateIR,rateSR);
		grid.PopulateMap();
		grid.InitializeInfected(infected);
		while(grid.StatusCheck()){
			day++;
			grid.UpdateMap();
			if (day % 500 == 0){
				//PrintGrid(mapLength,grid);
				//System.out.println("-------------------");
			}
		}
		PrintGrid(mapLength,grid);
	}
	public static void PrintGrid(int mapLength, Map grid){
		for(int i=0;i<mapLength;i++){
			for(int j=0;j<mapLength;j++){
				if (grid.map[i][j].isImmune==true){
					System.out.print("^ ");
				}
				else if (grid.map[i][j].isDead==true){
					System.out.print("x ");
				}
				else if (grid.map[i][j].isInfected==true){
					System.out.print("* ");
				}
				else{
					System.out.print("- ");
				}
			}
			System.out.println();
		}
	}
}
