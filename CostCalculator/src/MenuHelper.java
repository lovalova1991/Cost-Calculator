import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MenuHelper 
{
	public void showMenu(LinkedList<Node> l) throws IllegalArgumentException, IOException
	{
		LinkedList<String> tempOp = new LinkedList<String>();
		InputStreamReader ireader = new InputStreamReader(System.in);
        BufferedReader myInput = new BufferedReader(ireader);
		for(Node n : l)
		{
			if(tempOp.contains(n.getOperator()) == false)
			{
				tempOp.add(n.getOperator());
			}
		}
		System.out.println("Found those operators:");
		for(String s : tempOp)
		{
			System.out.print("  " + s);
		}
		System.out.println("");
		while(tempOp.size() > 0)
		{
			String tmp = tempOp.poll();
			System.out.print("Insert cost of " + tmp + " : (Enter to confirm)  ");
			int insertCost = Integer.parseInt(myInput.readLine());
			//inserisco il costo indicato in ogni nodo
			for(Node n : l)
			{
				if(tmp.equals(n.getOperator()))
				{
					n.insertCost(insertCost);
				}
			}
			insertCost = 0;
		}
		myInput.close();
	}
	
	public int getMinimumLatency(LinkedList<Node> dfg)		//funzione che calcola la latenza ASAP
	{
		LinkedList<Node> temp = new LinkedList<Node>();
		for(Node n : dfg)
		{
			if(n.isDependent())
			{
				for(Node x : new LinkedList<Node>(temp))
				{
					if(n.getOp1().equals(x.result()))
					{
						n.insertClock(x.getClock() + 1);
						temp.add(n);
					}
				}
			}
			else
			{
				//se non è dipendente metto tutto al primo clock, sto determinando lo scheduling ASAP
				n.insertClock(1);
				temp.add(n);
			}
		}
		return temp.getLast().getClock();
	}
}
