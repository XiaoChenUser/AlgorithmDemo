package com.example.graph.bean;

import java.util.ArrayList;

public class Node {
    public int value;
    //(有向图)节点的入度
    public int in;
    //(有向图)节点的出度
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
    }
}
