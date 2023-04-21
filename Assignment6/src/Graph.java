import java.util.*;

public class Graph implements GraphInterface<Town, Road> {
	HashMap<String, HashMap<String, Road>> matrix = new HashMap<String, HashMap<String, Road>>();
	private int[] distance;
	private String[] prev;
	private ArrayList<String> towns;

	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		try {
			return matrix.get((sourceVertex).getName()).get((destinationVertex).getName());
		} catch (Exception e) {
			return null;
		}
	}

	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (matrix.get(sourceVertex.getName()) == null && matrix.get(destinationVertex.getName()) == null)
			throw new IllegalArgumentException();
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		matrix.get(sourceVertex.getName()).put(destinationVertex.getName(), road);
		matrix.get(destinationVertex.getName()).put(sourceVertex.getName(), road);
		return road;
	}

	public boolean addVertex(Town v) {
		if (v == null) {
			throw new NullPointerException();
		}
		if (matrix.containsKey(v.getName())) {
			return false;
		}
		matrix.put(v.getName(), new HashMap<String, Road>());
		matrix.get(v.getName()).put(v.getName(), null);
		return true;
	}

	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if (matrix.containsKey(sourceVertex.getName()) && matrix.containsKey(destinationVertex.getName())) {
			return matrix.get(sourceVertex.getName()).get(destinationVertex.getName()) != null;
		}
		return false;
	}

	public boolean containsVertex(Town v) {
		return (matrix.get(((Town) v).getName()) != null);
	}

	public Set<Road> edgeSet() {
		Set<Road> road = new HashSet<Road>();
		for (String key : matrix.keySet()) {
			Map<String, Road> edges = matrix.get(key);
			for (Road roads : edges.values()) {
				if (roads != null) {
					road.add(roads);
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
		if (matrix.get(t.getName()) == null) {
			throw new IllegalArgumentException();
		}

		Set<Road> road = new HashSet<Road>(matrix.get(t.getName()).values());
		road.remove(null);
		return road;
	}

	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (weight > -1 && description != null) {
			Road road = matrix.get(sourceVertex.getName()).get(destinationVertex.getName());
			matrix.get(sourceVertex.getName()).put(destinationVertex.getName(), null);
			matrix.get(destinationVertex.getName()).put(destinationVertex.getName(), null);
			return road;
		}
		return null;
	}

	public boolean removeVertex(Town v) {
		if (v == null || matrix.get(v.getName()) == null) {
			return false;
		}
		matrix.remove(v.getName());
		return true;
	}

	public Set<Town> vertexSet() {
		Set<Town> road = new HashSet<Town>();
		for (String key : matrix.keySet()) {
			road.add(new Town(key));
		}
		return road;
	}

	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> path = new ArrayList<>();
		ArrayList<Integer> pathWeight = new ArrayList<>();
		ArrayList<String> finalPath = new ArrayList<>();

		int index = towns.indexOf(destinationVertex.getName());
		path.add(destinationVertex.getName());
		while (prev[index] != null) {
			path.add(prev[index]);
			pathWeight.add(distance[index]);
			index = towns.indexOf(prev[index]);
		}
		Collections.reverse(path);
		Collections.reverse(pathWeight);
		int count = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			String sourceName = path.get(i);
			String destinationName = path.get(i + 1);
			Road road = (Road) this.getEdge(new Town(sourceName), new Town(destinationName));
			String roadName = road.getName();
			int distance = pathWeight.get(i) - count;
			String pathString = sourceName + " via " + roadName + " to " + destinationName + " " + distance + " mi";
			finalPath.add(pathString);
			count += distance;
		}
		return finalPath;
	}

	public void dijkstraShortestPath(Town sourceVertex) {
		String source = sourceVertex.getName();
		List<Town> vertices = new ArrayList<>(vertexSet());
		towns = new ArrayList<>();
		ArrayList<String> unvisited = new ArrayList<>();
		for (Town v : vertices) {
			towns.add(v.getName());
			unvisited.add(v.getName());
		}

		distance = new int[towns.size()];
		prev = new String[towns.size()];

		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[towns.indexOf(source)] = 0;
		while (!unvisited.isEmpty()) {
			HashMap<String, Road> connected = matrix.get(source);
			for (String key : connected.keySet()) {
				if (unvisited.indexOf(key) == -1 || connected.get(key) == null) {
					continue;
				}
				int ind = towns.indexOf(key);
				int curr = towns.indexOf(source);
				int weight = connected.get(key).getWeight();
				if (distance[curr] + weight < distance[ind]) {
					distance[ind] = weight + distance[curr];
					prev[ind] = source;
				}
			}
			unvisited.remove(unvisited.indexOf(source));
			if (unvisited.isEmpty()) {
				break;
			}
			int shortest = Integer.MAX_VALUE;
			int shortestIndex = -1;
			for (String t : unvisited) {
				int ind = towns.indexOf(t);
				if (distance[ind] < shortest) {
					shortest = distance[ind];
					shortestIndex = ind;
				}
			}
			if (shortestIndex == -1) {
				break;
			}
			source = towns.get(shortestIndex);
		}
	}
}
