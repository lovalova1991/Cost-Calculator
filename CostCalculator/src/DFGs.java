import java.util.LinkedList;

public class DFGs 
{
	private LinkedList<Node> list;
	private int cost;
	
	public DFGs(LinkedList<Node> list, int cost)
	{
		this.list = list;
		this.cost = cost;
	}
	
	public int getCost()
	{
		return cost;
	}
}
