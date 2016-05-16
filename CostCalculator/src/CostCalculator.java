import java.util.LinkedList;

public class CostCalculator 
{
	public LinkedList<DFGs> insertDFGcost(LinkedList<LinkedList<Node>> result, int clock)
	{
		DFGs graph = new DFGs(null, 0);
		LinkedList<DFGs> toreturn = new LinkedList<DFGs>();
		LinkedList<Node> temp = new LinkedList<Node>();
		int numOfAdder = 0;
		int numOfMulti = 0;
		int numOfAlu = 0;
		int numOfDiv = 0;
		int numOfSub = 0;
		int tempAdder = 0;
		int tempSub = 0;
		int tempAlu = 0;
		int tempDiv = 0;
		int tempMulti = 0;
		int costOfAdd = 0;
		int costOfSub = 0;
		int costOfMulti = 0;
		int costOfDiv = 0;
		int costOfAlu = 0;
		
		for(LinkedList<Node> list : result)
		{
			for(int i = 0; i < clock; i++)
			{
				for(Node n : list)
				{
					if(n.getClock() == i)
					{
						temp.add(n);
					}
				}
				for(Node n : temp)
				{
					switch(n.getOperator())
					{
						case "+":
							costOfAdd = n.getCost();
							tempAdder ++;
							break;
						case "-":
							costOfSub = n.getCost();
							tempSub ++;
							break;
						case "*":
							costOfMulti = n.getCost();
							tempMulti ++;
							break;
						case "/":
							costOfDiv = n.getCost();
							tempDiv ++;
							break;
						case "+-":
							costOfAlu = n.getCost();
							tempAlu ++;
							break;
						default:
							break;
					}
				}
				//confronto per vedere il costo
				if(tempAdder > numOfAdder)
				{
					numOfAdder = tempAdder;
				}
				else if(tempSub > numOfSub)
				{
					numOfSub = tempSub;
				}
				else if(tempMulti > numOfMulti)
				{
					numOfMulti = tempMulti;
				}
				else if(tempDiv > numOfDiv)
				{
					numOfDiv = tempDiv;
				}
				else if(tempAlu > numOfAlu)
				{
					numOfAlu = tempAlu;
				}
				
				//reset delle variabili
				tempAlu = 0;
				tempDiv = 0;
				tempMulti = 0;
				tempSub = 0;
				tempAdder = 0;
				temp.clear();		
			}
			int totCost = (costOfAdd * numOfAdder) + (costOfSub * numOfSub) + (costOfMulti * numOfMulti) + (costOfDiv * numOfDiv) + (costOfAlu * numOfAlu);
			graph = new DFGs(list, totCost);
			toreturn.add(graph);
		}
		return toreturn;
	}
}
