package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		newAttributeList = new ArrayList<Attribute>();
		this.child = child;
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple temp=child.next();
		newAttributeList= new ArrayList<Attribute>();
			if(temp!=null){
			for (int x=0; x<temp.getAttributeList().size();x++){
				if(attributePredicate.equals(temp.getAttributeName(x)))
					newAttributeList.add(temp.getAttributeList().get(x));}
		Tuple tuple= new Tuple(newAttributeList);
		return tuple;
			}else{
				return null;
			}}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}