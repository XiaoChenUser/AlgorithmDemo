package com.example.graph.dijkstra;

import com.example.graph.bean.Edge;
import com.example.graph.bean.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 求出指定节点（随便指定）到其它各个节点的最短路径问题。
 * Dijkstra 算法适用范围：
 * 可以有权值为负数的边，但不能有累加值为负的环。
 */
public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra(Node head) {
        //从head出发到所有点的最小距离
        //key: 从 head 出发到达 key node
        //value: 从 head 出发到达 key 的最小距离
        //如果在表中，没有 Node T 的记录，表示从 head 出发到达 T 的距离为无穷大
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        //放入 head 到 head 的距离 0
        distanceMap.put(head, 0);
        //已经求过距离的节点，即已经处理过该节点的所有边，存在 selectedNodes 中，以后不再碰
        HashSet<Node> selectedNodes = new HashSet<>();
        //这一步选出的还是 head
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge :
                    minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, edge.weight);
                }
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * node A 与 node B, C, D 相邻，找出 B,C,D 中与 A 距离最近，且不在 selectedNodes 中的 node，并返回。
     * @param distanceMap
     * @param selectedNodes
     * @return
     */
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry :
                distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }
}
