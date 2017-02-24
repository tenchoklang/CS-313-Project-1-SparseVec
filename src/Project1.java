
//Author: Tenzin Choklang
//Date: 10/18/2016
//Class: Data Structures cs 313

import java.io.BufferedReader;
import java.io.FileReader;
public class Project1 {

	public static void main(String args[]) throws Exception
	{
				
		FileReader file = new FileReader("Project1.txt");
		BufferedReader reader = new BufferedReader(file);
		
		
		String[] line = reader.readLine().split(" ");
		String[] line2 = reader.readLine().split(" ");
		
		String operation = reader.readLine();
		
		if(reader.readLine() != null){
			System.out.println("ERROR IN TEXT FILE, UNSPECIFIED LINE");
		}
		
		
		reader.close();
		
		SparseVector sparseVec = new SparseVector();
	
		sparseVec.operation(line, line2, operation);

	}
}
