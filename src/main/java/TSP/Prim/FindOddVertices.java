package TSP.Prim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class FindOddVertices {

    private Queue<Edge> mst;

    public FindOddVertices(Queue<Edge> mst) {
        this.mst = mst;
    }

    public static HashMap<Integer, Integer> getOddEvenVertices(Queue<Edge> mst) {
        HashMap<Integer, Integer> vertices = new HashMap<>();
        int count = 1;
        int newCount;
        for(Edge edge : mst)
        {
            int v1 = edge.either();
            if(vertices.containsKey(v1))
            {
                newCount = vertices.get(v1) + 1;
                vertices.put(v1, newCount);
            }
            else {
                vertices.put(v1,count);
            }

            int v2 = edge.other(v1);
            if(vertices.containsKey(v2))
            {
                newCount = vertices.get(v2) + 1;
                vertices.put(v2, newCount);
            }
            else {
                vertices.put(v2,count);
            }

        }

        return vertices;
    }

    public static ArrayList<Integer> oddVertices(HashMap<Integer, Integer> vertices) {
        ArrayList<Integer> verticesArray = new ArrayList<>();
        for(Map.Entry<Integer, Integer> itr : vertices.entrySet())
        {
            int val = itr.getValue();
            if(!(val%2==0))
            {
                verticesArray.add(itr.getKey());
            }

        }
        System.out.println("number of odd vertices and size of array " + verticesArray.size());
        return verticesArray;
    }
}
