package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple = child.next();
		while(tuple != null){
			if(child.from.equals(whereTablePredicate)){
				for(int x=0; x<tuple.getAttributeList().size();x++){
					if(whereAttributePredicate.equals(tuple.getAttributeName(x))){
						if(whereValuePredicate.equals(tuple.getAttributeValue(x))){
							attributeList=new ArrayList<Attribute>();
							for(int y=0; y<tuple.getAttributeList().size(); y++){
								attributeList.add(tuple.getAttributeList().get(y));
							}
							tuple= new Tuple(attributeList);
							return tuple;
						}
				}}
			}else{
				return tuple;
		}
		tuple = child.next();
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}