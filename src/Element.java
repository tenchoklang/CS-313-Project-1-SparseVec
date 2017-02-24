
public class Element implements Comparable<Element> {

	private int index;
	private double value;
	
	public Element(){//default constructor
		
	}
	
	public Element(int index, double value){
		
		this.setIndex(index);
		this.setValue(value);
	}

	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public String toThisString(){
		/*if(this.getValue() == 0){//don't print value with 0
			
			return "";
		}*/
		return "[" + this.getIndex() + "," + this.getValue()+ " ]";
	}
	@Override
	public int compareTo(Element otherElement) {
		
		if(this.getIndex() < otherElement.getIndex()){
			
			return -1;
			
		}
		else if(this.getIndex() > otherElement.getIndex()){
			
			return 1;
		}
		
		return 0;
	}
	
	
	
}
