/**
 * @author Justin Nguyen
 */

public class Town {
    private String name;
    Town(String town){
        name = town;
    }
    Town(Town town){
        name = town.getName();
    }
    public int compareTo(Town town) {
		return name.compareTo(town.getName());
	}
    public boolean equals(Object obj) {
		Town town = (Town)obj;
		return this.name.equals(town.getName());
		
	}
    public String getName(){
        return name;
    }
    public int hashCode(){
        return name.hashCode();
    }
    public String toString(){
        return name;    
    }
}
