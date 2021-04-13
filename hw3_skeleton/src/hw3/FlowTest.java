package hw3;
import api.Cell;
import api.Flow;


public class FlowTest {
	public static void main(String[] args){
		 Flow f = new Flow(new Cell(2, 3, 'R'), new Cell(4, 5, 'R'));
	    System.out.println(f);
	    f.add(new Cell(6, 7, 'R'));
	    System.out.println(f);   
	    f.clear();
	    System.out.println(f);	
	}
}
