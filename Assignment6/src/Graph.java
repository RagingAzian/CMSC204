import java.util.*;

public class Graph implements GraphInterface<Town, Road>{
    HashMap<String, HashMap<String, Road>> adjMatrix = new HashMap<>();
    private int[] distance;
    private String[] prev;
    private ArrayList<String> towns;

    public Road getEdge(Town sourceVertex,Town destinationVertex) {
		try {
			return (Road) adjMatrix.get(((Town) sourceVertex).getName()).get(((Town) destinationVertex).getName());
		} catch (Exception e) {
			return null;
		}
	}
    public Road addEdge(Town sourceVertex,Town destinationVertex,int weight,String description){
        if(adjMatrix.get(sourceVertex.getName())==null&&adjMatrix.get(destinationVertex.getName())==null)
            throw new IllegalArgumentException();
        if (sourceVertex == null || destinationVertex == null) 
            throw new NullPointerException();
        Road road = new Road(sourceVertex,destinationVertex,weight,description);
        adjMatrix.get(sourceVertex.getName()).put(destinationVertex.getName(),road);
        adjMatrix.get(destinationVertex.getName()).put(sourceVertex.getName(),road);
        return road;
    }
    public boolean addVertex(Town v) {
		if (v == null) {
			throw new NullPointerException();
		}
		if (adjMatrix.containsKey(v.getName())) {
			return false;
		}
		adjMatrix.put(v.getName(), new HashMap<String, Road>());
		adjMatrix.get(v.getName()).put(v.getName(), null);
		return true;
	}
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        if (adjMatrix.containsKey(sourceVertex.getName()) && adjMatrix.containsKey(destinationVertex.getName())) {
            return adjMatrix.get(sourceVertex.getName()).get(destinationVertex.getName()) != null;
        }
        return false;
    }
    public boolean containsVertex(Town v){
        return (adjMatrix.get(((Town) v).getName())!=null);
    }
    public Set<Road> edgeSet() {
        Set<Road> road = new HashSet<Road>();
        for (String key : adjMatrix.keySet()) {
            Map<String, Road> edges = adjMatrix.get(key);
            for (Road edge : edges.values()) {
                if (edge != null) {
                    road.add(edge);
                }
            }
        }
        return road;
    }
    public Set<Road> edgesOf(Town vertex) {

		Town t = (Town) vertex;
		if (t == null) {
			throw new NullPointerException();
		}
		if (adjMatrix.get(t.getName())==null) {
			throw new IllegalArgumentException();
		}

		Set<Road> road = new HashSet<Road>(adjMatrix.get(t.getName()).values());
		road.remove(null);
		return road;
	}
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (weight > -1 && description != null) {
			Road road = adjMatrix.get(sourceVertex.getName()).get(destinationVertex.getName());
			adjMatrix.get(sourceVertex.getName()).put(destinationVertex.getName(), null);
			adjMatrix.get(destinationVertex.getName()).put(destinationVertex.getName(), null);
			return road;
		}
		return null;
	}
    public boolean removeVertex(Town v) {
		if (v == null || !adjMatrix.containsKey(v.getName())) {
			return false;
		}
		adjMatrix.remove(v.getName());
		return true;
	}
    public Set<Town> vertexSet() {
		Set<Town> road = new HashSet<Town>();
		for (String key : adjMatrix.keySet()) {
			road.add(new Town(key));
		}
		return road;
	}
    public ArrayList shortestPath(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		dijkstraShortestPath(sourceVertex);
		Town dest = (Town) destinationVertex;
		// get the destination vertex and then just trace back until we get to the
		// source vertex.
		ArrayList<String> path = new ArrayList<String>();
		ArrayList<Integer> path_weight = new ArrayList<Integer>();
		List<Town> verts = new ArrayList<Town>(vertexSet());

		int ind = towns.indexOf(dest.getName());
		path.add(dest.getName());
		while (prev[ind] != null) {

			path.add(prev[ind]);
			path_weight.add(distance[ind]);
			ind = towns.indexOf(prev[ind]);

		}

		Collections.reverse(path);
		Collections.reverse(path_weight);
		ArrayList<String> finalPath = new ArrayList<String>();
		int runningCount = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			finalPath.add(path.get(i) + " via "
					+ (((Road) this.getEdge(new Town(path.get(i)), new Town(path.get(i + 1)))).getName()) + " to "
					+ path.get(i + 1) + " " +(path_weight.get(i)- runningCount)+" mi");
			runningCount += path_weight.get(i)- runningCount;
		}

		return finalPath;
	}
    public void dijkstraShortestPath(Town sourceVertex) {
		String src = ((Town) sourceVertex).getName();
		List<Town> verts = new ArrayList<Town>(vertexSet());
		towns = new ArrayList<String>();
		ArrayList<String> unvisited = new ArrayList<String>();
		for (Town v : verts) {
			towns.add(v.getName());
			unvisited.add(v.getName());
		}
//		unvisited.remove(unvisited.indexOf(src));

		distance = new int[towns.size()];
		prev = new String[towns.size()];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[towns.indexOf(src)] = 0;

		while (!unvisited.isEmpty()) {

			// get neighboring verticies next to the source
			HashMap<String, Road> connected_nodes = adjMatrix.get(src);
			for (String t : connected_nodes.keySet()) {
				if (unvisited.indexOf(t) != -1 && connected_nodes.get(t) != null) {
					int ind = towns.indexOf(t);
					int curr_ind = towns.indexOf(src);
					int weight = connected_nodes.get(t).getWeight();
					if (distance[curr_ind] + weight < distance[ind]) {

						distance[ind] = weight + distance[curr_ind];
						prev[ind] = src;
					}

				}

			}
			unvisited.remove(unvisited.indexOf(src));
			if(unvisited.isEmpty()) {
				break;
			}
			// get unvisited smallest distance, and set it as source

			
			int shortest = Integer.MAX_VALUE;
			int shortest_ind = -1;
			for (String t : unvisited) {
				int ind = towns.indexOf(t);
				if (distance[ind] < shortest) {
					shortest = distance[ind];
					shortest_ind = ind;
				}
			}
			
			if(shortest_ind== -1) {
	
				break;
			}
			src = towns.get(shortest_ind);

		}

	}
}
