package com.company;

import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import javax.swing.*;

public class graph extends Main{

    public static ListenableDirectedWeightedGraph dir_Graph() {
        g = new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);
        String[] data;
        String window = JOptionPane.showInputDialog("Number Of Vertices:");
        numofnodes = Integer.parseInt(window);
        for (int i = 0; i < numofnodes; i++) {
            g.addVertex(String.valueOf(i));
        }
        ShowGui(g);

        weightedGraph= new int[numofnodes][numofnodes]; //[from-node][to-node]
        for(int i=0; i<numofnodes; i++)
            for(int j=0; j<numofnodes; j++)
                weightedGraph[i][j]=0;

        int i=0;
        window = JOptionPane.showInputDialog("Edge " + (i + 1) + ": (From,To,Capacity), if finished enter 0");
        while (!window.equals("0")) {
            data = window.split(",");
            while (data.length != 3) {
                window = JOptionPane.showInputDialog("Error, Enter right input\n Edge" + (i + 1) + ": (From,To,Capacity)");
                data = window.split(",");
            }

            Main.MyEdge e = g.addEdge(data[0], data[1]);
            edges.put(e, data[2]);
            weightedGraph[Integer.parseInt(data[0])][Integer.parseInt(data[1])]=Integer.parseInt(data[2]);
            i++;
            window = JOptionPane.showInputDialog("Edge " + (i + 1) + ": (From,To,Capacity), if finished enter 0");
        }
        ShowGui(g);
        return (ListenableDirectedWeightedGraph) g;
    }

    public static ListenableUndirectedWeightedGraph undir_Graph() {
        g = new ListenableUndirectedWeightedGraph<String, MyEdge>(MyEdge.class);

        String[] data;
        String window = JOptionPane.showInputDialog("Number Of Vertices:");
        numofnodes = Integer.parseInt(window);
        for (int i = 0; i < numofnodes; i++) {
            g.addVertex(String.valueOf(i));
        }
        ShowGui(g);

        weightedGraph= new int[numofnodes][numofnodes]; //[from-node][to-node]
        for(int i=0; i<numofnodes; i++)
            for(int j=0; j<numofnodes; j++)
                weightedGraph[i][j]=0;

        int i=0;
        window = JOptionPane.showInputDialog("Edge " + (i + 1) + ": (Vertex1,Vertex2,Capacity), if finished enter 0");
        while (!window.equals("0")) {
            data = window.split(",");
            while (data.length != 3) {
                window = JOptionPane.showInputDialog("Error, Enter right input\n Edge" + (i + 1) + ": (Vertex1,Vertex2,Capacity)");
                data = window.split(",");
            }

            Main.MyEdge e = g.addEdge(data[0], data[1]);
            edges.put(e, data[2]);
            weightedGraph[Integer.parseInt(data[0])][Integer.parseInt(data[1])]=Integer.parseInt(data[2]);
            weightedGraph[Integer.parseInt(data[1])][Integer.parseInt(data[0])]=Integer.parseInt(data[2]);
            i++;
            window = JOptionPane.showInputDialog("Edge " + (i + 1) + ": (Vertex1,Vertex2,Capacity), if finished enter 0");
        }
        ShowGui(g);


        return (ListenableUndirectedWeightedGraph) g;
    }
}
