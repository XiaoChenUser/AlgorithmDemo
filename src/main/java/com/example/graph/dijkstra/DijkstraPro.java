package com.example.graph.dijkstra;

import com.example.graph.bean.Edge;
import com.example.graph.bean.Node;

import java.util.HashMap;

/**
 * 改进的 Dijkstra 算法，使用自定义堆
 */
public class DijkstraPro {
    public static HashMap<Node, Integer> dijkstra(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            HeapNode heapNode = nodeHeap.pop();
            Node cur = heapNode.node;
            int distance = heapNode.distance;
            for (Edge edge :
                    cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
            result.put(cur, distance);
        }
        return result;
    }


    public static class HeapNode{
        public Node node;
        public int distance;

        public HeapNode(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public HeapNode() {
        }
    }

    public static class NodeHeap{
        public Node[] nodes;
        public HashMap<Node,Integer> nodeIndexMap;
        public HashMap<Node,Integer> distanceMap;
        public int size;

        public NodeHeap(int capacity) {
            this.nodes = new Node[capacity];
            this.nodeIndexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return this.size==0;
        }

        public void addOrUpdateOrIgnore(Node node,int distance) {
            if (isInHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, nodeIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                nodeIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node,size++);
            }
        }

        public HeapNode pop() {
            HeapNode heapNode = new HeapNode(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            nodeIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size-1] = null;
            heapify(0, --size);
            return heapNode;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index-1)/2;
            }
        }

        private void heapify(int index, int size) {

        }

        public boolean isEntered(Node node) {
            return nodeIndexMap.containsKey(node);
        }

        public boolean isInHeap(Node node) {
            return isEntered(node) && nodeIndexMap.get(node)!=-1;
        }

        public void swap(int index1, int index2) {
            nodeIndexMap.put(nodes[index1], index2);
            nodeIndexMap.put(nodes[index2], index1);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }
    }
}
