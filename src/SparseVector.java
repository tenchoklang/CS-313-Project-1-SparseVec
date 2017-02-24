
public class SparseVector {

	private DoublyLinkedList<Element> SparseVec;
	
	public SparseVector(){
		
		SparseVec = new DoublyLinkedList<Element>();
	
	}
	
	public void operation(String [] line, String [] line2 , String operation){
		
		SparseVector a = new SparseVector();
		SparseVector b = new SparseVector();
		
		if(operation.equalsIgnoreCase("add")){
			
			
			a = a.insertElements(line, 1);//inserts every element from array and returns sparseVector
			System.out.println("+");
			b = b.insertElements(line2, 2);//1 or two indicates which sparsevector it is
			
			System.out.println("=");
			SparseVector answer;
			
			answer = a.add(b);
			
			for(int i = 0; i < answer.getSparseVec().size(); i++){
				
				if(answer.getSparseVec().get(i).getValue() !=0){//dont print sparse vector if value is zero
					
					System.out.print(answer.getSparseVec().get(i).toThisString());//use toString to print Elements
			
				}
			}
		
		}
		
		if(operation.equalsIgnoreCase("sub")){
			a = a.insertElements(line, 1);
			System.out.println("-");
			b = b.insertElements(line2, 2);
			System.out.println("=");
			
			SparseVector answer;
			
			answer = a.sub(b);

			for(int i = 0; i < answer.getSparseVec().size(); i++){
				if(answer.getSparseVec().get(i).getValue() !=0){//dont print sparse vector if value is zero
				
					System.out.print(answer.getSparseVec().get(i).toThisString());//use toString to print Elements
			
				}
			}
				
		}
		
		if(operation.equalsIgnoreCase("dot")){
			char dotCharacter = (char) 183;
			a = a.insertElements(line, 1);
			System.out.println(dotCharacter);
			b = b.insertElements(line2, 2);
			System.out.println("=");
			
			double answer;
			
			answer = a.dot(b);
			
			System.out.println(answer);

			
		}
	}
	
	
	public SparseVector insertElements(String [] line, int oneORtwo){
		
		if(oneORtwo == 1){//sparse vectorOne
		
		SparseVector a = new SparseVector();
		int ctr = 0;
		for(int i = 0; i< line.length; i+=2){//use array and insert value into doublyLinkedList
			
			int index = Integer.parseInt(line[i]);
			double coefficient = Double.parseDouble(line[i+1]);
			
			
			
			Element newElement = new Element(index, coefficient);
			
			a.getSparseVec().add(newElement);
			
			System.out.print(a.getSparseVec().get(ctr).toThisString());//use toString to print 
			
			ctr++;
			}
		System.out.println();
		return a;
		
		}
		
		else{//sparseVectotwo
		
		SparseVector b = new SparseVector();
		int ctr = 0;
		for(int i = 0; i < line.length; i+=2){
			
			
			int index = Integer.parseInt(line[i]);
			double coefficient = Double.parseDouble(line[i+1]);
			
			
			Element newElement = new Element(index, coefficient);
			
			b.getSparseVec().add(newElement);
			
			System.out.print(b.getSparseVec().get(ctr).toThisString());
			
			ctr++;
		}
		System.out.println();
			return b;
		}

	}

	
	public SparseVector add(SparseVector callee){
		
		SparseVector finalSparseVector = new SparseVector();
		
		SparseVector caller = this;
		
		int vectorOne_index=0;//caller 
		int vectorTwo_index=0;//callee

		while(caller.getSparseVec().size() > vectorOne_index && callee.getSparseVec().size() > vectorTwo_index){
			
			
			int check = caller.getSparseVec().get(vectorOne_index).compareTo(callee.getSparseVec().get(vectorTwo_index));

			if(check == 0){//use compareTo to check if index are the same
				
				double callerValue = caller.getSparseVec().get(vectorOne_index).getValue();
				double calleeValue = callee.getSparseVec().get(vectorTwo_index).getValue();
				
				double combinedValue = callerValue + calleeValue;
				
				int calleeORcaller_index = caller.getSparseVec().get(vectorOne_index).getIndex();//sets the index both have same index
				
				insertInto_DoublyLL(calleeORcaller_index, combinedValue, finalSparseVector);
				
				vectorOne_index++;
				vectorTwo_index++;
			}
			
			else{
				
				if(check == -1){//caller index < callee index
					
					int callerIndex = caller.getSparseVec().get(vectorOne_index).getIndex();
					double callerValue = caller.getSparseVec().get(vectorOne_index).getValue();
					
					insertInto_DoublyLL(callerIndex, callerValue, finalSparseVector);
					vectorOne_index++;
					}
				else{//caller index > callee index
					
					int calleeIndex = callee.getSparseVec().get(vectorTwo_index).getIndex();
					double calleeValue = callee.getSparseVec().get(vectorTwo_index).getValue();
					
					insertInto_DoublyLL(calleeIndex, calleeValue, finalSparseVector);
					
					vectorTwo_index++;
					
					
					}
				}
			}//while, ended means either the caller or callee's have no more elements 
		
		if(vectorOne_index == caller.getSparseVec().size()){//no more elements in caller
			
			for(int i = vectorTwo_index; i < callee.getSparseVec().size(); i++){//add everything from the callee to sparseVec
				
				int callee_Index = callee.getSparseVec().get(i).getIndex();
				double callee_Value = callee.getSparseVec().get(i).getValue();
				
				insertInto_DoublyLL(callee_Index, callee_Value, finalSparseVector);
			}
		}
		else{//no more elements in callee
			
			for(int i = vectorOne_index; i < caller.getSparseVec().size(); i++){//add everything from the caller to sparseVec
				
				int caller_Index = caller.getSparseVec().get(i).getIndex();
				double caller_Value = caller.getSparseVec().get(i).getValue();
				
				insertInto_DoublyLL(caller_Index, caller_Value, finalSparseVector);
			}
			
		}
		return finalSparseVector;
		
	}
	
	public SparseVector sub(SparseVector callee){
		
		SparseVector finalSparseVector = new SparseVector();
		
		SparseVector caller = this;
		
		int vectorOne_index=0;//caller 
		int vectorTwo_index=0;//callee
		
		while(caller.getSparseVec().size() > vectorOne_index && callee.getSparseVec().size() > vectorTwo_index){
			
			
			int check = caller.getSparseVec().get(vectorOne_index).compareTo(callee.getSparseVec().get(vectorTwo_index));

			if(check == 0){//caller and callee index same
				
				double callerValue = caller.getSparseVec().get(vectorOne_index).getValue();
				double calleeValue = callee.getSparseVec().get(vectorTwo_index).getValue();
				
				double combinedValue = callerValue - calleeValue;
				
				int calleeORcaller_index = caller.getSparseVec().get(vectorOne_index).getIndex();//sets the index both have same index
				
				insertInto_DoublyLL(calleeORcaller_index, combinedValue, finalSparseVector);
				
				vectorOne_index++;
				vectorTwo_index++;
			}
			else{
				
				if(check == -1){//caller index < callee index
					
					int callerIndex = caller.getSparseVec().get(vectorOne_index).getIndex();
					double callerValue = caller.getSparseVec().get(vectorOne_index).getValue();
					
					insertInto_DoublyLL(callerIndex, callerValue, finalSparseVector);
					vectorOne_index++;
					}
				else{//callee index < caller index
					
					int calleeIndex = callee.getSparseVec().get(vectorTwo_index).getIndex();
					double calleeValue = callee.getSparseVec().get(vectorTwo_index).getValue();
					
					insertInto_DoublyLL(calleeIndex, calleeValue*-1, finalSparseVector);
					
					vectorTwo_index++;
					}
				}
		}
		
		if(vectorOne_index == caller.getSparseVec().size()){//no more elements in caller
			
			for(int i = vectorTwo_index; i < callee.getSparseVec().size(); i++){
				
				int callee_Index = callee.getSparseVec().get(i).getIndex();
				double callee_Value = callee.getSparseVec().get(i).getValue();
				
				insertInto_DoublyLL(callee_Index, callee_Value*-1, finalSparseVector);
				
			}
		}
		else{
			
			for(int i = vectorOne_index; i < caller.getSparseVec().size(); i++){//no more elements in callee
				
				int caller_Index = caller.getSparseVec().get(i).getIndex();
				double caller_Value = caller.getSparseVec().get(i).getValue();
				
				insertInto_DoublyLL(caller_Index, caller_Value, finalSparseVector);	
			}	
		}
		return finalSparseVector;
		
	}

	public double dot(SparseVector callee){
		
		SparseVector caller = this;
		
		int vectorOne_index=0;//caller 
		int vectorTwo_index=0;//callee
		
		double total = 0;
	
		while(caller.getSparseVec().size() > vectorOne_index && callee.getSparseVec().size() > vectorTwo_index){
			
			int check = caller.getSparseVec().get(vectorOne_index).compareTo(callee.getSparseVec().get(vectorTwo_index));
			
			if(check == 0){//use compareTo, same index
				
				double callerValue = caller.getSparseVec().get(vectorOne_index).getValue();
				double calleeValue = callee.getSparseVec().get(vectorTwo_index).getValue();
				
				total = (callerValue * calleeValue) + total;
				
				vectorOne_index++;
				vectorTwo_index++;
			}
			
			else{
				
				if(check == -1){vectorOne_index++;}//caller index < callee index
				
				else{vectorTwo_index++;}//callee index < caller index	
			}
		}

		return total;
	}
	
	
	
	private void insertInto_DoublyLL(int index, double value, SparseVector sv){ //to help reduce duplication of code
		
		Element newElement = new Element(index, value);
		
		sv.getSparseVec().add(newElement);	
	}
	
	public DoublyLinkedList<Element> getSparseVec() {
		return SparseVec;
	}
	public void setSparseVec(DoublyLinkedList<Element> sparseVec) {
		SparseVec = sparseVec;
	}
}

