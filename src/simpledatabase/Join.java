package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple tuple= leftChild.next();
		if(tuples1.isEmpty()){
			while(tuple !=null){
			tuples1.add(tuple);
			tuple=leftChild.next();
		}
			}
		
		tuple = rightChild.next();
		while(tuple!=null){
			for(int x=0; x<tuples1.size(); x++){
				newAttributeList = new ArrayList<Attribute>();
				for(int y=0; y<tuples1.get(x).getAttributeList().size();y++){
					for(int z=0; z<tuple.getAttributeList().size(); z++){
						if(tuples1.get(x).getAttributeName(y).equals(tuple.getAttributeName(z))){
							if(tuples1.get(x).getAttributeValue(y).equals(tuple.getAttributeValue(z))){
								newAttributeList.addAll(tuples1.get(x).getAttributeList());
								 tuple = new Tuple(newAttributeList);
								return tuple;
							}
						}
					}
				}
			}

		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}