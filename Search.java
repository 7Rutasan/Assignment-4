import java.util.List;

public interface Search<V> {
    List<Vertex<V>> search(Vertex<V> source, Vertex<V> destination);
}
