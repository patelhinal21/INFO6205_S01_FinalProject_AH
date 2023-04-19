package TSP.Prim;

import Optimization.SimulatedAnnealing;
import Optimization.ThreeOpt;
import Optimization.TwoOpt;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Prims {
    private boolean[] marked; // MST vertices
    Queue<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // crossing (and ineligible) edges

    public Prims(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        visit(G, 0); // assumes G is connected
        while (!pq.isEmpty()) {
            Edge e = pq.delMin(); // Get lowest-weight
            int v = e.either(), w = e.other(v); // edge from pq.
            if (marked[v] && marked[w]) continue; // Skip if ineligible.
            mst.add(e); // Add edge to tree.
            if (!marked[v]) visit(G, v); // Add vertex to tree
            if (!marked[w]) visit(G, w); // (either v or w).
        }
    }

    private void visit(EdgeWeightedGraph G, int v) { // Mark v and add to pq all edges from v to unmarked vertices.
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

}
