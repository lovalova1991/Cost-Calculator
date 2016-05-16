import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class FileHelper 
{
	public LinkedList<Node> readFile(String pathName)
	{
		LinkedList<Node> list = new LinkedList<Node>();
		System.out.println("Reading file....");
		try
		{
			BufferedReader fileReader = new BufferedReader(new FileReader(pathName));
			String readed = fileReader.readLine();
			while(readed != null)
			{ 	
				String op1 = readed.split(",")[0];
				String operator = readed.split(",")[1];
				String op2 = readed.split(",")[2];
				String result = readed.split(",")[3];
				try
				{
					Integer.parseInt(op1);
					Node toInsert = new Node(op1, op2, operator, result, 0, 0, true);
					list.add(toInsert);
				}
				catch(Exception ex)
				{	
					Node toInsert = new Node(op1, op2, operator, result, 0, 0, false);
					list.add(toInsert);
				}
				readed = fileReader.readLine();
			}
			fileReader.close();
			System.out.println("File readed correctly!");
			System.out.println("###############################");
		}
		catch(Exception e)
		{
			System.out.println("Error in Reading your DFG File. Check if your file is correct.");
		}
		
		return list;
	}
	
	public LinkedList<LinkedList<Node>> readCombinations(String pathName)
	{
		LinkedList<LinkedList<Node>> res = new LinkedList<LinkedList<Node>>();
		LinkedList<Node> list = new LinkedList<Node>();
		try
		{
			BufferedReader fileReader = new BufferedReader(new FileReader(pathName));
			String readed = fileReader.readLine();
			while(readed != null)
			{ 	
				if(readed.equals("#"))
				{
					res.add(new LinkedList<Node>(list));
					list.clear();
					readed = fileReader.readLine();
				}
				String op1 = readed.split(",")[0];
				String operator = readed.split(",")[1];
				String op2 = readed.split(",")[2];
				String result = readed.split(",")[3];
				String clock = readed.split(",")[4];
				String cost = readed.split(",")[5];
				try
				{
					Integer.parseInt(op1);
					Node toInsert = new Node(op1, op2, operator, result, Integer.parseInt(clock), Integer.parseInt(cost), true);
					list.add(toInsert);
				}
				catch(Exception ex)
				{	
					Node toInsert = new Node(op1, op2, operator, result, Integer.parseInt(clock), Integer.parseInt(cost), false);
					list.add(toInsert);
				}
				readed = fileReader.readLine();
			}
			fileReader.close();
			System.out.println("File readed correctly!");
			System.out.println("###############################");
		}
		catch(Exception e)
		{
			
		}
		return res;
	}
	
}

