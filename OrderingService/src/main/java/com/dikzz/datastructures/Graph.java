package com.dikzz.datastructures;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by dikzz on 8/16/16.
 */
public class Graph {

    public static class Mapping {

        private Integer parent;
        private List<Integer> children;

        public List<Integer> getChildren() {
            return children;
        }

        public void setChildren(List<Integer> children) {
            this.children = children;
        }

        public Integer getParent() {
            return parent;
        }

        public void setParent(Integer parent) {
            this.parent = parent;
        }
    }

    public static class Node {

        private Integer id;
        private boolean isStart;
        private String value;
        private List<Node> next;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public boolean isStart() {
            return isStart;
        }

        public void setIsStart(boolean isStart) {
            this.isStart = isStart;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Node> getNext() {
            return next;
        }

        public void setNext(List<Node> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", isStart=" + isStart +
                    ", value='" + value + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    private List<Mapping> mapping;
    private List<Node> nodes;
    private Node startNode;

    public List<Mapping> getMapping() {
        return mapping;
    }

    public void setMapping(List<Mapping> mapping) {
        this.mapping = mapping;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public static Graph readValue(InputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Graph graph = mapper.readValue(inputStream, Graph.class);
        graph.init();
        return graph;
    }

    public void init() {
        Map<Integer, Node> nodesMap = nodes.stream().map(n -> n).collect(Collectors.toMap(n -> n.getId(), n -> n));
        Map<Integer, List<Integer>> mappings = mapping.stream().map(m -> m).collect(Collectors.toMap(m -> m.getParent(), m -> m.getChildren()));
        for (Integer parent : mappings.keySet()) {
            Node node = nodesMap.get(parent);
            List<Node> children = mappings.get(parent).stream().map(nodesMap::get).collect(Collectors.toList());
            node.setNext(children);
        }
        startNode = nodes.stream().filter(n -> n.isStart()).findFirst().get();
    }

    public void traversRecursive(Consumer<Node> consumer) {
        recursiveAction(Collections.singletonList(startNode), consumer);
    }

    private void recursiveAction(List<Node> nodes, Consumer<Node> consumer) {
        for (Node node : nodes) {
            consumer.accept(node);
            List<Node> next = node.getNext();
            if (next != null) {
                recursiveAction(next, consumer);
            }
        }
    }

    public void traversUpToDown(Consumer<Node> consumer) {
        Deque<Node> stack = new LinkedList<>();
        Set<Integer> marked = new HashSet<>();

        stack.push(startNode);
        do {
            if (allChildrenVisited(stack.peek(), marked)) {
                Node item = stack.pop();
                consumer.accept(item);
                marked.add(item.getId());
            } else {
                List<Node> children = stack.peek().getNext();
                if (children != null && !children.isEmpty()) {
                    for (Node child : children) {
                        if (!marked.contains(child.getId())) {
                            stack.push(child);
                            break;
                        }
                    }
                }
            }
        } while (!stack.isEmpty());
    }

    private boolean allChildrenVisited(Node parent, Set<Integer> marked) {
        boolean result = true;
        List<Node> children = parent.getNext();
        if (children == null) {
            return true;
        }
        for (Node node : children) {
            result &= marked.contains(node.getId());
        }
        return result;
    }

    public void traverseRightToLeft(Consumer<Node> consumer) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        do {
            Node current = queue.poll();
            consumer.accept(current);
            List<Node> next = current.getNext();
            if (next == null) {
                continue;
            }
            for (Node node : next) {
                queue.add(node);
            }
        } while (!queue.isEmpty());
    }

    public static void main(String[] args) throws IOException {
        Graph graph = Graph.readValue(Graph.class.getClassLoader().getResourceAsStream("com/dikzz/datastructures/graph.json"));
        graph.traverseRightToLeft(System.out::println);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "startNode=" + startNode +
                '}';
    }
}
