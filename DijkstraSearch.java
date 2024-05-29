import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> search(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> predecessors = new HashMap<>();
        PriorityQueue<VertexDistancePair<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(VertexDistancePair::getDistance));
        Set<Vertex<V>> visited = new HashSet<>();

        distances.put(source, 0.0);
        priorityQueue.add(new VertexDistancePair<>(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll().getVertex();
            if (visited.contains(current)) continue;
            visited.add(current);

            if (current.equals(destination)) {
                return constructPath(predecessors, source, destination);
            }

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distances.get(current) + weight;
                if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    priorityQueue.add(new VertexDistancePair<>(neighbor, newDist));
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

    private static class VertexDistancePair<V> {
        private final Vertex<V> vertex;
        private final double distance;

        public VertexDistancePair(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex<V> getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }
}
