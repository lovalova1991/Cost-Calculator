import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Combinations
{	
	public Boolean isLegit(LinkedList<Node> toTest, int clock)
	{	
		LinkedList<Node> temp1 = new LinkedList<Node>();
		LinkedList<Node> temp2 = new LinkedList<Node>(toTest);
		LinkedList<Node> scan = new LinkedList<Node>(toTest);
		for(int i = scan.size() - 1; i >= 0; i--)
		{
			//Se se ho un nodo con il clock maggiore a quello dato scarto le soluzioni
			if(scan.get(i).getClock() < clock)
			{
				if(scan.get(i).isDependent())
				{
					temp1.addFirst(toTest.get(i));
					temp2.remove(toTest.get(i));
					for(Node n : new LinkedList<Node>(toTest))
					{
						if(n.result().equals(scan.get(i).getOp1()) || n.result().equals(scan.get(i).getOp2()))
						{
							if(n.getClock() < scan.get(i).getClock())
							{
								temp1.addFirst(n);
							}
							else
							{
								return false;
							}
						}
					}
				}
				else
				{
					temp1.addFirst(scan.get(i));
					temp2.remove(toTest.get(i));
				}
			}
			else
			{
				return false;
			}
		}
		temp1.clear();
		return true;
	}
	
	public void getCombinations(LinkedList<Node> dfg, String path, int clock) throws IOException
	{
		File file = new File(path);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		int lengthOfSinglePermutation =  dfg.size();
		LinkedList<Node> temp = new LinkedList<Node>();
		int lecitSolutions = 0;
	    // we need to check number of unique values in array
	    Set<Integer> arrValues = new HashSet<Integer>();
	    for (Node n : dfg) 
	    {
	        arrValues.add(Integer.parseInt(n.result()));
	    }
	    int noOfUniqueValues = arrValues.size();

	    int[] indexes = new int[lengthOfSinglePermutation];
	    int totalPermutations = (int) Math.pow(noOfUniqueValues, lengthOfSinglePermutation);
	    System.out.println("##############################");
	    System.out.println("Calculating...");
	    if(dfg.size() > 8)
    		System.out.println("Warning! This will take a while!");
	    for (int i = 0; i < totalPermutations; i++) 
	    {
	    	long percent = (i * 100) / totalPermutations;
	    	System.out.print("\r" + percent +"%");
	        for (int j = 0; j < lengthOfSinglePermutation; j++) 
	        {
	        	dfg.get(j).insertClock(indexes[j]);
	        	temp.add(dfg.get(j));
	        }
	        
	        //qui per ogni dfg controllo se è valido
	        if(isLegit(temp, clock))
	        {
	        	lecitSolutions ++;
	        	for(Node n : temp)
	        	{
	        		bw.write(n.getOp1() + "," + n.getOperator() + "," + n.getOp2() + "," + n.result() + "," + n.getClock() + "," + n.getCost());
	        		bw.newLine();
	        	}
	        	bw.write("#\n");
	        }
	        temp.clear();
	        for (int j = 0; j < lengthOfSinglePermutation; j++) 
	        {
	            if (indexes[j] >= noOfUniqueValues - 1) 
	            {
	                indexes[j] = 0;
	            }
	            else 
	            {
	                indexes[j]++;
	                break;
	            }
	        }
	    }
	    bw.close();
	    System.out.println();
	    System.out.println("Number of lecit solutions: " + lecitSolutions);
	    System.out.println("##############################");
	}
}
