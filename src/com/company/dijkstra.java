//------------------------test case--------------
/*
undirected graph

static int numofnodes=9;
static int weightedGraph[][]= {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; ;

public static void buildGraph() {
    g.addVertex("0"); g.addVertex("1");
    g.addVertex("2"); g.addVertex("3");
    g.addVertex("4"); g.addVertex("5");
    g.addVertex("6"); g.addVertex("7");
    g.addVertex("8");

    MyEdge e= g.addEdge("0","1"); edges.put(e, String.valueOf(4));
    e= g.addEdge("0","7");        edges.put(e, String.valueOf(8));
    e= g.addEdge("1","2");        edges.put(e, String.valueOf(8));
    e= g.addEdge("1","7");        edges.put(e, String.valueOf(11));
    e= g.addEdge("2","3");        edges.put(e, String.valueOf(7));
    e= g.addEdge("2","5");        edges.put(e, String.valueOf(4));
    e= g.addEdge("2","8");        edges.put(e, String.valueOf(2));
    e= g.addEdge("3","4");        edges.put(e, String.valueOf(9));
    e= g.addEdge("3","5");        edges.put(e, String.valueOf(14));
    e= g.addEdge("4","5");        edges.put(e, String.valueOf(10));
    e= g.addEdge("5","6");        edges.put(e, String.valueOf(2));
    e= g.addEdge("6","7");        edges.put(e, String.valueOf(1));
    e= g.addEdge("6","8");        edges.put(e, String.valueOf(6));
    e= g.addEdge("7","8");        edges.put(e, String.valueOf(7));

    ShowGui(g);
}

Output:
Vertex   Distance from Source
0                0
1                4
2                12
3                19
4                21
5                11
6                9
7                8
8                14
 */
package com.company;

import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import javax.swing.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class dijkstra extends Main{

    dijkstra(String key){
        //apply algorith
        int dist[]= new int[numofnodes];
        Vector<Integer> set= new Vector();
        edges= new HashMap<>();
        g= new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);
        frame = new JFrame("Short Path Tree Graph");
        frame.setSize(600,600);
        frame.setLocation(400,0);

        //get src and dest
        String window= JOptionPane.showInputDialog("Enter Number of Source Node:- " );
        int src= Integer.parseInt(window);

        //Initialize all distances as INFINITE and stpSet[] as false
        for(int i=0; i<numofnodes; i++){
            g.addVertex(String.valueOf(i));
            dist[i]= Integer.MAX_VALUE;
        }

        //Distance of source vertex from itself is always 0
        dist[src]= 0;
        //////////////
        ShowGui(g);

        int u = 0;
        //Find shortest path for all vertices
        for(int count=0; count<numofnodes; count++){
            //Pick the minimum distance vertex from the set of vertices
            //not yet processed. u is always equal to src in first iteration.
            u= minDistance(dist, set);

            //Mark the picked vertex as processed
            JOptionPane.showMessageDialog(frame, "S = "+set+"\nThe Vertex "+u+" is picked");
            set.add(u);
            Collections.sort(set);

            //Update dist value of the adjacent vertices of the picked vertex.
            for(int v= 0; v<numofnodes; v++) {
                if (!set.contains(v) && weightedGraph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u]+weightedGraph[u][v] < dist[v]) {
                    dist[v] = dist[u] + weightedGraph[u][v];

                    if(g.removeAllEdges(g.edgesOf(String.valueOf(v))))
                    {
                        JOptionPane.showMessageDialog(frame, "Update Vertex "+v);
                    }

                    MyEdge e = g.addEdge(String.valueOf(u), String.valueOf(v));
                    edges.put(e, String.valueOf(dist[v]));
                }
            }

            JOptionPane.showMessageDialog(frame, "Next");
            ShowGui(g);
        }
        //JOptionPane.showMessageDialog(frame, "S = "+set+"\nThe Vertex "+u+" is picked");
        showSolution(dist);
    }

    //------------------------------------------------subFunctions------------------------------------------------------

    private static int minDistance(int[] dist, Vector<Integer>set){
        int min= Integer.MAX_VALUE, min_index= -1;

        for(int v=0; v<numofnodes; v++) {
            if(!set.contains(v) && dist[v]<=min){
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility function to print the constructed distance array
    private static void showSolution(int dist[]){
        String output= "Vertex   |    Distance from Source\n";
        for(int i=0; i<numofnodes; i++)
            output+= "         "+i + "    |    " + dist[i]+"\n";

        JOptionPane.showMessageDialog(frame, output);
    }

}

