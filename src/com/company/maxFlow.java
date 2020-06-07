//------------------------test case--------------
/*
directed graph
static int numofnodes=6;
static int weight[][]= new int[][]{
    {0, 6, 5, 3, 0, 0},
    {0, 0, 0, 2, 0, 3},
    {0, 0, 0, 1, 2, 0},
    {0, 0, 0, 0, 9, 7},
    {0, 0, 0, 0, 0, 5},
    {0, 0, 0, 0, 0, 0}
 };

public static void buildGraph() {
    g.addVertex("0"); g.addVertex("1");
    g.addVertex("2"); g.addVertex("3");
    g.addVertex("4"); g.addVertex("5");

    MyEdge e= g.addEdge("0","1"); edges.put(e, String.valueOf(6));
    e= g.addEdge("0","2");        edges.put(e, String.valueOf(5));
    e= g.addEdge("0","3");        edges.put(e, String.valueOf(3));
    e= g.addEdge("1","3");        edges.put(e, String.valueOf(2));
    e= g.addEdge("1","5");        edges.put(e, String.valueOf(3));
    e= g.addEdge("2","3");        edges.put(e, String.valueOf(1));
    e= g.addEdge("2","4");        edges.put(e, String.valueOf(2));
    e= g.addEdge("3","4");        edges.put(e, String.valueOf(9));
    e= g.addEdge("3","5");        edges.put(e, String.valueOf(7));
    e= g.addEdge("4","5");        edges.put(e, String.valueOf(5));

    ShowGui(g);
}
max_flow from vertex 0 to vertex 5 => 11

 */
package com.company;

import javax.swing.*;
import java.util.LinkedList;

public class maxFlow extends Main {

    maxFlow(){
        //apply algorithm
        int src, dest, u, v;

        //get src and dest
        String window = JOptionPane.showInputDialog("Get Maximum Flow(From,To)");
        String data[] = window.split(",");
        while (data.length != 2 || data[0] == data[1]) {
            window = JOptionPane.showInputDialog("Error, Enter right input\nGet Maximum Flow(From,To)");
            data = window.split(",");
        }
        src = Integer.parseInt(data[0]);
        dest = Integer.parseInt(data[1]);

        int rGraph[][] = new int[numofnodes][numofnodes];
        for (u = 0; u < numofnodes; u++)
            for (v = 0; v < numofnodes; v++)
                rGraph[u][v] = weightedGraph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[numofnodes];
        int max_flow = 0;  // There is no flow initially

        while (bfs(rGraph, src, dest, parent)) {
            int path_flow = Integer.MAX_VALUE;

            String root = String.valueOf(dest); //for gui
            for (v = dest; v != src; v = parent[v]) {
                u = parent[v];
                root = String.valueOf(u) + "-" + root;
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }


            // update residual capacities of the edges and reverse edges along the path
            JOptionPane.showMessageDialog(frame, "Select the root: " + root + ", with path flow= " + path_flow + "\nMaximum flow= " + max_flow);
            for (v = dest; v != src; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;

                MyEdge e = g.getEdge(String.valueOf(u), String.valueOf(v));
                if (rGraph[u][v] == 0) {
                    g.removeEdge(e);
                    edges.remove(e);
                } else {
                    edges.replace(e, String.valueOf(rGraph[u][v]));
                }

                e = g.getEdge(String.valueOf(v), String.valueOf(u));
                if (rGraph[v][u] == 0) {
                    g.removeEdge(e);
                    edges.remove(e);
                } else {
                    edges.replace(e, String.valueOf(rGraph[v][u]));
                }
            }
            //update gui
            ShowGui(g);
            //Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        JOptionPane.showMessageDialog(frame, "The maximum possible flow From " + src + " To " + dest + " is " + max_flow);
    }

    private static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not visited
        boolean visited[] = new boolean[numofnodes];
        for (int i = 0; i < numofnodes; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < numofnodes; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }
}

