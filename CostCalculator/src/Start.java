import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Start {

	public static void main(String[] args) throws Exception, Exception 
	{
		LinkedList<LinkedList<Node>> result = new LinkedList<LinkedList<Node>>();
		LinkedList<DFGs> listsWithCost = new LinkedList<DFGs>();
		LinkedList<Node> dfg = new LinkedList<Node>();
		InputStreamReader ireader = new InputStreamReader(System.in);
        BufferedReader myInput = new BufferedReader(ireader);
        System.out.println("Insert DFG's path:");
        String myPath = myInput.readLine();
        
        //leggo il file di input
        FileHelper file = new FileHelper();
        dfg = file.readFile(myPath);		//ho il dfg caricato in una lista
        
        //Chiedo all'utente il costo dei componenti che compongono il dfg
        MenuHelper menu = new MenuHelper();
        menu.showMenu(dfg);
        int clock = menu.getMinimumLatency(dfg);
        
        //Calcolo tutti i possibili dfg per il clock dato
        Combinations comb = new Combinations();
        comb.getCombinations(dfg, myPath.replace("scheduling", "combinations"), clock);
        
        //leggo le combinazioni scritte nel file
        result = file.readCombinations(myPath.replace("scheduling", "combinations"));
        
        //Calcolo il numero di soluzioni valide per ogni costo
        CostCalculator calc = new CostCalculator();
        listsWithCost = calc.insertDFGcost(result, clock);
        
        System.out.println("Generating Graph...");
        GraphHelper graph = new GraphHelper("demo", listsWithCost);
        graph.showGraph(listsWithCost);
        System.out.println("##############################");
        System.out.println("Deleting temp files....");
        Files.delete(Paths.get(myPath.replace("scheduling", "combinations")));
        System.out.println("##############################");
        System.out.println("Done!");
	}
}