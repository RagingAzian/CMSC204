/**
 * @author Justin Nguyen
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph = new Graph();

    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        return (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null);
    }

    public String getRoad(String town1, String town2) {
        return ((Road) graph.getEdge(new Town(town1), new Town(town2))).getName();
    }

    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    public Town getTown(String name) {
        return new Town(name);
    }

    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    public ArrayList<String> allRoads() {
        ArrayList<Road> roads = new ArrayList<Road>(graph.edgeSet());
        ArrayList<String> roadsList = new ArrayList<String>();
        for (Road r : roads) {
            roadsList.add(r.getName());
        }
        Collections.sort(roadsList);
        return roadsList;
    }

    public boolean deleteRoadConnection(String town1, String town2, String road) {
        return (graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null);
    }

    public boolean deleteTown(String v) {
        return graph.removeVertex(new Town(v));
    }

    public ArrayList<String> allTowns() {
        ArrayList<Town> towns = new ArrayList<>(graph.vertexSet());
        ArrayList<String> townsList = new ArrayList<>();
        for (Town town : towns) {
            townsList.add(town.getName());
        }
        Collections.sort(townsList);
        return townsList;
    }

    public ArrayList<String> getPath(String town1, String town2) {
        return graph.shortestPath(new Town(town1), new Town(town2));
    }

    public void populateTownGraph(File file) throws FileNotFoundException{
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] splitText = line.split(",");
            String roadName = splitText[0];
            splitText = splitText[1].split(";");
            int weight = Integer.parseInt(splitText[0]);
            String town1 = splitText[1];
            String town2 = splitText[2];
            graph.addVertex(new Town(town1));
            graph.addVertex(new Town(town2));
            graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
        }
        scan.close();
    }
}