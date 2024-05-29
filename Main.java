public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(v1, v2, 1);
        graph.addEdge(v2, v3, 2);
        graph.addEdge(v3, v4, 3);
        graph.addEdge(v4, v1, 4);

        Search<String> bfs = new BreadthFirstSearch<>(graph);
        System.out.println("BFS Path from A to D: " + bfs.search(v1, v4));

        Search<String> dijkstra = new DijkstraSearch<>(graph);
        System.out.println("Dijkstra Path from A to D: " + dijkstra.search(v1, v4));
    }
}
