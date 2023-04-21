public class Town {
    private String name;
    Town(String town){
        name = town;
    }
    Town(Town town){
        this.name = town.getName();
    }

    int compareTo(Town town){
        return name.compareTo(town.getName());
    }
    public boolean equals(Object object){
        Town town = (Town)object;
        return this.name.equals(town.getName());
    }
    public String getName(){
        return name;
    }
    public int hashCode(){
        return name.hashCode();
    }
    public String toString(){
        return getName();    
    }
}
