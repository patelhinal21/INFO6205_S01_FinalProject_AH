package TSP.Prim;

import java.util.*;

public class Prims {
    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
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

    public static void main(String[] args) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(9);
        Edge e1 = new Edge(0, 1, 12);
        Edge e2 = new Edge(0, 2, 10);
        Edge e3 = new Edge(0, 3, 19);
        Edge e4 = new Edge(0, 4, 8);
        Edge e5 = new Edge(1, 2, 3);
        Edge e6 = new Edge(1, 3, 7);
        Edge e7 = new Edge(1, 4, 2);
        Edge e8 = new Edge(2, 3, 6);
        Edge e9 = new Edge(2, 4, 4);
        Edge e10 = new Edge(3, 4, 4);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);
        edgeWeightedGraph.addEdge(e10);

        List<CityDetails> cityDetailsList = new ArrayList<>();

        CityDetails c0 = new CityDetails(0, -0.016542, 51.515192);
        CityDetails c1 = new CityDetails(1, -0.236815, 51.406763);
        CityDetails c2 = new CityDetails(2, -0.184411, 51.495871);
        CityDetails c3 = new CityDetails(3, -0.268832, 51.464685);
        CityDetails c4 = new CityDetails(4, -0.098618, 51.415897);
        CityDetails c5 = new CityDetails(5, -0.120151, 51.516341);
        CityDetails c6 = new CityDetails(6, -0.13278, 51.525068);
        CityDetails c7 = new CityDetails(7, -0.123817, 51.459387);
        CityDetails c8 = new CityDetails(8, 0.064469, 51.511582);
        CityDetails c9 = new CityDetails(9, -0.365332, 51.440091);
        CityDetails c10 = new CityDetails(10, -0.145735, 51.514195);
        CityDetails c11 = new CityDetails(11, -0.399253, 51.527082);
        CityDetails c12 = new CityDetails(12, -0.069064, 51.61255);
        CityDetails c13 = new CityDetails(13, -0.020991, 51.609006);
        CityDetails c14 = new CityDetails(14, -0.13228, 51.509458);
        CityDetails c15 = new CityDetails(15, -0.070715, 51.582452);
        CityDetails c16 = new CityDetails(16, -0.127849, 51.445612);
        CityDetails c17 = new CityDetails(17, -0.139092, 51.56648);
        CityDetails c18 = new CityDetails(18, -0.062154, 51.589955);
        CityDetails c19 = new CityDetails(19, -0.018941, 51.498093);
        CityDetails c20 = new CityDetails(20, -0.092147, 51.504262);
        CityDetails c21 = new CityDetails(21, 0.084965, 0.084965);
        CityDetails c22 = new CityDetails(22, 0.051925, 51.431874);
        CityDetails c23 = new CityDetails(23, -0.143083, 51.538657);
        CityDetails c24 = new CityDetails(24, -0.180905, 51.575851);
        CityDetails c25 = new CityDetails(25, 0.012614, 51.433033);
        CityDetails c26 = new CityDetails(26, -0.156134, 51.651286);
        CityDetails c27 = new CityDetails(27, -0.118487, 51.557069);
        CityDetails c28 = new CityDetails(28, -0.13273, 51.603418);
        CityDetails c29 = new CityDetails(29, -0.006898, 51.543293);
        CityDetails c30 = new CityDetails(30, -0.073686, 51.525497);
        CityDetails c31 = new CityDetails(31, -0.026728, 51.510616);
        CityDetails c32 = new CityDetails(32, 0.015237, 51.40404);
        CityDetails c33 = new CityDetails(33, -5.85E-04, 51.553068);
        CityDetails c34 = new CityDetails(34, 0.144788, 51.530932);
        CityDetails c35 = new CityDetails(35, -0.01727, 51.574736);
        CityDetails c36 = new CityDetails(36, 0.104233, 51.501289);
        CityDetails c37 = new CityDetails(37, -0.25237, 51.582339);
        CityDetails c38 = new CityDetails(38, -0.305328, 51.514388);
        CityDetails c39 = new CityDetails(39, -0.070089, 51.562283);
        CityDetails c40 = new CityDetails(40, -0.259035, 51.566954);
        CityDetails c41 = new CityDetails(41, -0.259035, 51.566954);
        CityDetails c42 = new CityDetails(42, -0.096709, 51.374949);
        CityDetails c43 = new CityDetails(43, -0.216092, 51.458561);
        CityDetails c44 = new CityDetails(44, -0.340541, 51.542511);
        CityDetails c45 = new CityDetails(45, -0.128984, 51.51084);
        CityDetails c46 = new CityDetails(46, -0.027052, 51.531449);
        CityDetails c47 = new CityDetails(47, 0.012013, 51.492674);
        CityDetails c48 = new CityDetails(48, -0.371762, 51.562286);
        CityDetails c49 = new CityDetails(49, 0.1314, 51.495556);
        CityDetails c50 = new CityDetails(50, -0.176162, 51.482739);
        CityDetails c51 = new CityDetails(51, -0.113383, 51.586976);
        CityDetails c52 = new CityDetails(52, -0.029554, 51.648052);
        CityDetails c53 = new CityDetails(53, -0.043735, 51.383978);
        CityDetails c54 = new CityDetails(54, -0.222961, 51.444035);
        CityDetails c55 = new CityDetails(55, -0.124436, 51.580077);
        CityDetails c56 = new CityDetails(56, -0.114788, 51.462606);
        CityDetails c57 = new CityDetails(57, -0.389191, 51.512359);
        CityDetails c58 = new CityDetails(58, -0.352282, 51.472047);
        CityDetails c59 = new CityDetails(59, -0.129438, 51.513064);
        CityDetails c60 = new CityDetails(60, -0.201098, 51.486245);
        CityDetails c61 = new CityDetails(61, -0.162469, 51.371845);
        CityDetails c62 = new CityDetails(62, -0.049096, 51.543283);
        CityDetails c63 = new CityDetails(63, -0.189609, 51.353616);
        CityDetails c64 = new CityDetails(64, 0.135924, 51.587353);
        CityDetails c65 = new CityDetails(65, -0.285675, 51.557634);
        CityDetails c66 = new CityDetails(66, 0.022099, 51.529829);
        CityDetails c67 = new CityDetails(67, -0.192743, 51.465515);
        CityDetails c68 = new CityDetails(68, 0.039396, 51.601003);
        CityDetails c69 = new CityDetails(69, -0.193059, 51.361536);
        CityDetails c70 = new CityDetails(70, -0.146304, 51.52289);
        CityDetails c71 = new CityDetails(71, -0.105091, 51.623181);
        CityDetails c72 = new CityDetails(72, -0.074819, 51.557857);
        CityDetails c73 = new CityDetails(73, -0.287144, 51.607599);
        CityDetails c74 = new CityDetails(74, -0.2679, 51.384027);
        CityDetails c75 = new CityDetails(75, -0.201293, 51.544582);
        CityDetails c76 = new CityDetails(76, -0.130495, 51.511534);
        CityDetails c77 = new CityDetails(77, -0.099674, 51.399107);
        CityDetails c78 = new CityDetails(78, -0.038932, 51.670564);
        CityDetails c79 = new CityDetails(79, 0.045955, 51.503993);
        CityDetails c80 = new CityDetails(80, -0.176131, 51.42212);
        CityDetails c81 = new CityDetails(81, -0.084172, 51.632796);
        CityDetails c82 = new CityDetails(82, -0.11047, 51.54757);
        CityDetails c83 = new CityDetails(83, -0.033458, 51.590538);
        CityDetails c84 = new CityDetails(84, -0.217565, 51.500183);
        CityDetails c85 = new CityDetails(85, -0.452714, 51.47116);
        CityDetails c86 = new CityDetails(86, -0.151335, 51.492989);
        CityDetails c87 = new CityDetails(87, -0.072534, 51.558445);
        CityDetails c88 = new CityDetails(88, -0.223084, 51.522019);
        CityDetails c89 = new CityDetails(89, -0.133438, 51.514971);
        CityDetails c90 = new CityDetails(90, -0.081058, 51.497246);
        CityDetails c91 = new CityDetails(91, -0.235161, 51.536616);
        CityDetails c92 = new CityDetails(92, -0.415037, 51.516749);
        CityDetails c93 = new CityDetails(93, -0.048139, 51.442558);
        CityDetails c94 = new CityDetails(94, -0.199465, 51.527693);
        CityDetails c95 = new CityDetails(95, 0.053359, 51.541417);
        CityDetails c96 = new CityDetails(96, -0.186273, 51.516834);
        CityDetails c97 = new CityDetails(97, -0.235846, 51.508067);
        CityDetails c98 = new CityDetails(98, -0.135545, 51.543142);
        CityDetails c99 = new CityDetails(99, -0.081278, 51.527339);
        CityDetails c100 = new CityDetails(100, -0.059564, 51.490094);
        CityDetails c101 = new CityDetails(101, -0.069592, 51.473327);
        CityDetails c102 = new CityDetails(102, -0.338618, 51.45779);
        CityDetails c103 = new CityDetails(103, -0.409133, 51.440037);
        CityDetails c104 = new CityDetails(104, -0.1058, 51.534986);
        CityDetails c105 = new CityDetails(105, -0.151711, 51.613943);
        CityDetails c106 = new CityDetails(106, 0.177936, 51.45086);
        CityDetails c107 = new CityDetails(107, -0.141346, 51.528531);
        CityDetails c108 = new CityDetails(108, -0.138803, 51.513114);
        CityDetails c109 = new CityDetails(109, -0.263714, 51.588425);
        CityDetails c110 = new CityDetails(110, -0.477183, 51.545753);
        CityDetails c111 = new CityDetails(111, -0.107398, 51.338396);
        CityDetails c112 = new CityDetails(112, -0.084944, 51.484289);
        CityDetails c113 = new CityDetails(113, -0.172996, 51.493841);
        CityDetails c114 = new CityDetails(114, -0.125167, 51.484381);
        CityDetails c115 = new CityDetails(115, -0.233776, 51.546694);
        CityDetails c116 = new CityDetails(116, -0.117357, 51.432692);
        CityDetails c117 = new CityDetails(117, -0.130495, 51.511534);
        CityDetails c118 = new CityDetails(118, 0.02152, 51.550594);
        CityDetails c119 = new CityDetails(119, -0.017371, 51.468867);
        CityDetails c120 = new CityDetails(120, 0.054855, 51.535887);
        CityDetails c121 = new CityDetails(121, -0.250787, 51.588251);
        CityDetails c122 = new CityDetails(122, -0.262582, 51.409774);
        CityDetails c123 = new CityDetails(123, 0.01632, 51.429651);
        CityDetails c124 = new CityDetails(124, -0.076966, 51.456173);
        CityDetails c125 = new CityDetails(125, -0.173787, 51.465752);
        CityDetails c126 = new CityDetails(126, 0.154514, 51.546069);
        CityDetails c127 = new CityDetails(127, -0.185725, 51.520818);
        CityDetails c128 = new CityDetails(128, 0.189512, 51.468972);
        CityDetails c129 = new CityDetails(129, -0.043404, 51.66277);
        CityDetails c130 = new CityDetails(130, -0.175109, 51.544807);
        CityDetails c131 = new CityDetails(131, 0.081158, 51.489444);
        CityDetails c132 = new CityDetails(132, -0.168349, 51.609663);
        CityDetails c133 = new CityDetails(133, -0.143092, 51.515753);
        CityDetails c134 = new CityDetails(134, -0.052931, 51.538589);
        CityDetails c135 = new CityDetails(135, 0.015471, 51.399233);
        CityDetails c136 = new CityDetails(136, -0.283703, 51.489273);
        CityDetails c137 = new CityDetails(137, -0.140222, 51.515088);
        CityDetails c138 = new CityDetails(138, 0.018498, 51.42062);
        CityDetails c139 = new CityDetails(139, 0.092913, 51.568047);
        CityDetails c140 = new CityDetails(140,0.206925,	51.599034);
        CityDetails c141 = new CityDetails(141, -0.131211,	51.492976);
        CityDetails c142 = new CityDetails(142, -0.334209,	51.58825);
        CityDetails c143 = new CityDetails(143, 0.106915,	51.564406);
        CityDetails c144 = new CityDetails(144, -0.39846,	51.558319);
        CityDetails c145 = new CityDetails(145, -0.101675,	51.373681);
        CityDetails c146 = new CityDetails(146, -0.321417,	51.504449);
        CityDetails c147 = new CityDetails(147, -0.09777,	51.524586);
        CityDetails c148 = new CityDetails(148, -0.112677,	51.505035);
        CityDetails c149 = new CityDetails(149, -0.032364,	51.474443);
        CityDetails c150 = new CityDetails(150, -0.16685,	51.428675);
        CityDetails c151 = new CityDetails(151, -0.436283,	51.602918);
        CityDetails c152 = new CityDetails(152, 0.224117,	51.56227);
        CityDetails c153 = new CityDetails(153, -0.023898,	51.386406);
        CityDetails c154 = new CityDetails(154, 0.072159,	51.540025);
        CityDetails c155 = new CityDetails(155, -0.303896,	51.527343);

        cityDetailsList.add(c0);
        cityDetailsList.add(c1);
        cityDetailsList.add(c2);
        cityDetailsList.add(c3);
        cityDetailsList.add(c4);
        cityDetailsList.add(c5);
        cityDetailsList.add(c6);
        cityDetailsList.add(c7);
        cityDetailsList.add(c8);
        cityDetailsList.add(c9);
        cityDetailsList.add(c10);
        cityDetailsList.add(c11);
        cityDetailsList.add(c12);
        cityDetailsList.add(c13);
        cityDetailsList.add(c14);
        cityDetailsList.add(c15);
        cityDetailsList.add(c16);
        cityDetailsList.add(c17);
        cityDetailsList.add(c18);
        cityDetailsList.add(c19);
        cityDetailsList.add(c20);
        cityDetailsList.add(c21);
        cityDetailsList.add(c22);
        cityDetailsList.add(c23);
        cityDetailsList.add(c24);
        cityDetailsList.add(c25);
        cityDetailsList.add(c26);
        cityDetailsList.add(c27);
        cityDetailsList.add(c28);
        cityDetailsList.add(c29);
        cityDetailsList.add(c30);
        cityDetailsList.add(c31);
        cityDetailsList.add(c32);
        cityDetailsList.add(c33);
        cityDetailsList.add(c34);
        cityDetailsList.add(c35);
        cityDetailsList.add(c36);
        cityDetailsList.add(c37);
        cityDetailsList.add(c38);
        cityDetailsList.add(c39);
        cityDetailsList.add(c40);
        cityDetailsList.add(c41);
        cityDetailsList.add(c42);
        cityDetailsList.add(c43);
        cityDetailsList.add(c44);
        cityDetailsList.add(c45);
        cityDetailsList.add(c46);
        cityDetailsList.add(c47);
        cityDetailsList.add(c48);
        cityDetailsList.add(c49);
        cityDetailsList.add(c50);
        cityDetailsList.add(c51);
        cityDetailsList.add(c52);
        cityDetailsList.add(c53);
        cityDetailsList.add(c54);
        cityDetailsList.add(c55);
        cityDetailsList.add(c56);
        cityDetailsList.add(c57);
        cityDetailsList.add(c58);
        cityDetailsList.add(c59);
        cityDetailsList.add(c60);
        cityDetailsList.add(c61);
        cityDetailsList.add(c62);
        cityDetailsList.add(c63);
        cityDetailsList.add(c64);
        cityDetailsList.add(c65);
        cityDetailsList.add(c66);
        cityDetailsList.add(c67);
        cityDetailsList.add(c68);
        cityDetailsList.add(c69);
        cityDetailsList.add(c70);
        cityDetailsList.add(c71);
        cityDetailsList.add(c72);
        cityDetailsList.add(c73);
        cityDetailsList.add(c74);
        cityDetailsList.add(c75);
        cityDetailsList.add(c76);
        cityDetailsList.add(c77);
        cityDetailsList.add(c78);
        cityDetailsList.add(c79);
        cityDetailsList.add(c80);
        cityDetailsList.add(c81);
        cityDetailsList.add(c82);
        cityDetailsList.add(c83);
        cityDetailsList.add(c84);
        cityDetailsList.add(c85);
        cityDetailsList.add(c86);
        cityDetailsList.add(c87);
        cityDetailsList.add(c88);
        cityDetailsList.add(c89);
        cityDetailsList.add(c90);
        cityDetailsList.add(c91);
        cityDetailsList.add(c92);
        cityDetailsList.add(c93);
        cityDetailsList.add(c94);
        cityDetailsList.add(c95);
        cityDetailsList.add(c96);
        cityDetailsList.add(c97);
        cityDetailsList.add(c98);
        cityDetailsList.add(c99);
        cityDetailsList.add(c100);
        cityDetailsList.add(c101);
        cityDetailsList.add(c102);
        cityDetailsList.add(c103);
        cityDetailsList.add(c104);
        cityDetailsList.add(c105);
        cityDetailsList.add(c106);
        cityDetailsList.add(c107);
        cityDetailsList.add(c108);
        cityDetailsList.add(c109);
        cityDetailsList.add(c110);
        cityDetailsList.add(c111);
        cityDetailsList.add(c112);
        cityDetailsList.add(c113);
        cityDetailsList.add(c114);
        cityDetailsList.add(c115);
        cityDetailsList.add(c116);
        cityDetailsList.add(c117);
        cityDetailsList.add(c118);
        cityDetailsList.add(c119);
        cityDetailsList.add(c120);
        cityDetailsList.add(c121);
        cityDetailsList.add(c123);
        cityDetailsList.add(c124);
        cityDetailsList.add(c125);
        cityDetailsList.add(c126);
        cityDetailsList.add(c127);
        cityDetailsList.add(c128);
        cityDetailsList.add(c129);
        cityDetailsList.add(c130);
        cityDetailsList.add(c131);
        cityDetailsList.add(c132);
        cityDetailsList.add(c133);
        cityDetailsList.add(c134);
        cityDetailsList.add(c135);
        cityDetailsList.add(c136);
        cityDetailsList.add(c137);
        cityDetailsList.add(c138);
        cityDetailsList.add(c139);
        cityDetailsList.add(c140);
        cityDetailsList.add(c141);
        cityDetailsList.add(c142);
        cityDetailsList.add(c143);
        cityDetailsList.add(c144);
        cityDetailsList.add(c145);
        cityDetailsList.add(c146);
        cityDetailsList.add(c147);
        cityDetailsList.add(c148);
        cityDetailsList.add(c149);
        cityDetailsList.add(c150);
        cityDetailsList.add(c151);
        cityDetailsList.add(c152);
        cityDetailsList.add(c153);
        cityDetailsList.add(c154);
        cityDetailsList.add(c155);

        Prims prims = new Prims(edgeWeightedGraph);

        System.out.println("minimum spanning tree " + prims.mst);
        HashMap<Integer, Integer> oddEvenVertices = FindOddVertices.getOddEvenVertices(prims.mst);
        System.out.println("odd even vertices " + oddEvenVertices);
        System.out.println("array entries of odd vertices " + FindOddVertices.oddVertices(oddEvenVertices));
        List<Edge> pairs = PerfectMatching.PerfectMatchingPairs(edgeWeightedGraph, FindOddVertices.oddVertices(oddEvenVertices));
        System.out.println("pairs " + pairs);
        prims.mst.addAll(pairs);
        System.out.println("multigraph after adding pairs " + prims.mst);

        EulerianCycle eu = new EulerianCycle(prims.mst);
        System.out.println("printing Eulerian cycle " + eu.eulerianCycle());
        System.out.println("has Eulerian cycle " + eu.hasEulerianCycle());
        Queue<Integer> eulerTour = eu.eulerianCycle();
        System.out.println("euler tour " + eulerTour);
        System.out.println(eu.hamiltonianCircuit(eulerTour, cityDetailsList));

    }
}
