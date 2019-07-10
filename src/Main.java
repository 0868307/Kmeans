import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Wouter on 24-2-2017.
 */
public class Main {
    List<KObject> wineList = new ArrayList();
    List<KObject> bestCluster = new ArrayList<>();
    Random random = new Random();
    double bestDistanceToCluster = Double.MAX_VALUE;
    public Main(){
        loadFile();
        double last = 0;
        for (int clusterAmount = 1; clusterAmount < 32; clusterAmount++) {
            double curr = kmeans(clusterAmount,1000);
            System.out.println(Math.abs(last - curr));
            last = curr;
        }
    }

    public List<KObject> findCentroid(int clusters){
        List<KObject> centroids = new ArrayList<>();
        for (int clusterIndex = 0; clusterIndex < clusters; clusterIndex++) {
            centroids.add(wineList.get(random.nextInt(wineList.size()-1)));
        }
        return centroids;
    }
    public List<Double> findCentroidDouble(int clusters){
        List<Double> centroids = new ArrayList<>();
        for (int clusterIndex = 0; clusterIndex < clusters; clusterIndex++) {
            centroids.add(random.nextDouble()*32);
        }
        return centroids;
    }
    public double kmeans(int clusters, int repeats){
        for (int repeatIndex = 0; repeatIndex < repeats; repeatIndex++) {
            List<KObject> centroids = findCentroid(clusters);
            List<Double> centroidsDouble = findCentroidDouble(clusters);
            for (KObject wine : wineList) {
                double distance = Double.MAX_VALUE;
                for (int centroidIndex = 0; centroidIndex < centroids.size(); centroidIndex++) {
                    double currDistance = calculateDistance(centroids.get(centroidIndex),wine);
                    if(currDistance < distance){
                        distance = currDistance;
                        wine.setDistanceToCluster(currDistance);
                        wine.setCluster(centroidIndex);
                    }
                }
            }
            double sum = 0;
            for (KObject kObject : wineList) {
                sum += kObject.distanceToCluster;
            }
            if(sum < bestDistanceToCluster){
                bestDistanceToCluster = sum;
                bestCluster = wineList;
            }
        }
//        System.out.println("-----------------------");
//        System.out.println(bestCluster);
        return bestDistanceToCluster;
    }

    private double calculateDistance(KObject centroid,KObject point) {
        double distance = 0;
        double d = 0;
        for (String s : point.dimensions) {
            distance += Math.pow(Integer.parseInt(s),2);
        }
        for (String s : centroid.dimensions) {
            d += Math.pow(Integer.parseInt(s),2);
        }
        distance = distance -d;
        distance = Math.sqrt(Math.abs(distance));
        return distance;
    }
    private double calculateDistance(Double centroid,KObject point) {
        double distance = 0;
        double d = 0;
        for (String s : point.dimensions) {
            distance += Math.pow(Integer.parseInt(s),2);
        }
        distance = distance - centroid;
        distance = Math.sqrt(Math.abs(distance));
        return distance;
    }

    public void loadFile(){
        String csv = "data/WineData.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        try{
            br = new BufferedReader(new FileReader(csv));
            int lineNumber = 0;
            while((line = br.readLine()) != null){
                String[] users = line.split(splitBy);
                wineList.add(new KObject(users));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}
