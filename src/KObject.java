import java.util.Arrays;

/**
 * Created by Wouter on 10-3-2017.
 */
public class KObject {
    String[] dimensions;
    Integer cluster;
    Double distanceToCluster;

    public KObject(String[] dimensions) {
        this.dimensions = dimensions;
    }

    public String[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(String[] dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getCluster() {
        return cluster;
    }

    public void setCluster(Integer cluster) {
        this.cluster = cluster;
    }

    public Double getDistanceToCluster() {
        return distanceToCluster;
    }

    public void setDistanceToCluster(Double distanceToCluster) {
        this.distanceToCluster = distanceToCluster;
    }

    @Override
    public String toString() {
        return "KObject{" +
                "cluster=" + cluster +
                ", distanceToCluster=" + distanceToCluster +
                '}';
    }
}
