public class Road {
    private Town source;
    private Town destination;
    private int weight;
    private String name;

    public Road(Town source,Town destination,int degree,String name ){
        this.source = source;
        this.destination = destination;
        weight = degree;
        this.name = name;
    }
    public Road (Town source, Town destination, String name){
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.name = name;
	}
    public int compareTo(Road road){
        return this.name.compareTo(road.getName());
    }
    public boolean contains(Town town){
        return(source.equals(town) || destination.equals(town));
    }
    public boolean equals(Road road){
        return(this.equals(road));
    } 
    public String getName(){
        return name;
    }
    public Town getSource(){
        return source;
    }
    public Town getDestination(){
        return destination;
    }
    public int getWeight(){
        return weight;
    }
    public String toString(){
        return name;
    }
}
