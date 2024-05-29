import java.util.*;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        map.computeIfAbsent(source, k -> new ArrayList<>()).add(dest);
        source.addAdjacentVertex(dest, weight);
    }

    public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        return map.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<Vertex<V>> getVertices() {
        return map.keySet();
    }
}
