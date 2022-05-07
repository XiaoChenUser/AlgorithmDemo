package com.example.graph.bean;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    //节点编号和节点的 map
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph(HashMap<Integer, Node> nodes, HashSet<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
}
