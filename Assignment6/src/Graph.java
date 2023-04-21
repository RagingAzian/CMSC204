import java.util.*;

public class Graph {
    HashMap<String, HashMap<String, Road>> adjMatrix = new HashMap<>();
    private int[] distance;
    private String[] prev;
    private ArrayList<String> towns;

    public Object getEdge(Object sourceVertex, Object destinationVertex) {
        try {
            return (Road) adjMatrix.get(((Town) sourceVertex).getName()).get(((Town) destinationVertex).getName());
        } catch (Exception e) {
            return null;
        }
    }

    public Object addEdge(Object sourceVertex, Object destinationVertex, int weight, String description) {
        Town src = (Town) sourceVertex;
        Town dest = (Town) destinationVertex;
        if (adjMatrix.get(src.getName()) == null || adjMatrix.get(dest.getName()) == null) {
            throw new IllegalArgumentException();
        }
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        Road edge = new Road(src, dest, weight, description);
        adjMatrix.get(src.getName()).put(dest.getName(), edge);
        adjMatrix.get(dest.getName()).put(src.getName(), edge);
        return edge;
    }

    public boolean addVertex(Object v) {
        Town town = (Town) v;
        if (adjMatrix.get(town.getName()) != null) {
            return false;
        }
        if (v == null) {
            throw new NullPointerException();
        }
        adjMatrix.put(town.getName(), new HashMap<String, Road>());
        adjMatrix.get(town.getName()).put(town.getName(), null);
        return false;
    }

    public boolean containsEdge(Object sourceVertex, Object destinationVertex) {
        try {
            return (adjMatrix.get(((Town) sourceVertex).getName()).get(((Town) destinationVertex).getName()) != null);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean containsVertex(Object v) {
        return (adjMatrix.get(((Town) v).getName()) != null);
    }

    public Set edgeSet() {
        Set<Road> road = new HashSet<Road>();
        for (String keys : adjMatrix.keySet()) {
            road.addAll(adjMatrix.get(keys).values());
        }
        road.remove(null);
        return road;
    }

    public Set edgesOf(Object vertex) {
        Town t = (Town) vertex;
        if (t == null) {
            throw new NullPointerException();
        }
        if (adjMatrix.get(t.getName()) == null) {
            throw new IllegalArgumentException();
        }

        Set<Road> road = new HashSet<Road>(adjMatrix.get(t.getName()).values());
        road.remove(null);
        return road;
    }

    public Object removeEdge(Object sourceVertex, Object destinationVertex, int weight, String description) {
        if (weight > -1 && description != null) {
            Town source = (Town) sourceVertex;
            Town destination = (Town) destinationVertex;
            Road road = adjMatrix.get(source.getName()).get(destination.getName());
            adjMatrix.get(source.getName()).put(destination.getName(), null);
            adjMatrix.get(destination.getName()).put(source.getName(), null);
            return road;
        }
        return null;
    }

    public boolean removeVertex(Object v) {
        Town town = (Town) v;
        if (v == null || adjMatrix.get(town.getName()) == null) {
            return false;
        }
        adjMatrix.remove(town.getName());
        return true;
    }

    public Set vertexSet() {
        Set<Town> road = new HashSet<Town>();
        for (String keys : adjMatrix.keySet()) {
            road.add(new Town(keys));
        }
        return road;
    }
}
