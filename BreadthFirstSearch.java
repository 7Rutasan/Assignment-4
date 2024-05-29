import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final WeightedGraph<V> graph;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> search(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Vertex<V>> predecessors = new HashMap<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            if (current.equals(destination)) {
                return constructPath(predecessors, source, destination);
            }
            for (Vertex<V> neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList(); // Path not found
    }

    private List<Vertex<V>> constructPath(Map<Vertex<V>, Vertex<V>> predecessors, Vertex<V> source, Vertex<V> destination) {
        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> at = destination; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }
        return path;
    }
}
