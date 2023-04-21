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
    public boolean equals(Town town){
        return this.equals(town);
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
