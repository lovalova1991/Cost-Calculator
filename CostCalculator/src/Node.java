
public class Node 
{
	private String op1;
	private String op2;
	private String operator;
	private String result;
	private int clock;
	private int cost;
	private boolean isDependent;
	
	public Node(String op1, String op2, String operator, String result, int clock, int cost,  boolean isDependent)
	{
		this.op1 = op1;
		this.op2 = op2;
		this.operator = operator;
		this.result = result;
		this.clock = clock;
		this.cost = cost;
		this.isDependent = isDependent;
	}
	
	public String getOp1()
	{
		return op1;
	}
	public String getOp2()
	{
		return op2;
	}
	public String getOperator()
	{
		return operator;
	}
	public String result()
	{
		return result;
	}
	public int getClock()
	{
		return clock;
	}
	public int getCost()
	{
		return cost;
	}
	public boolean isDependent()
	{
		return isDependent;
	}
	public void insertCost(int cost)
	{
		this.cost = cost;
	}
	public void insertClock(int n)
	{
		this.clock = n;
	}
}
