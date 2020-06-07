package com.company;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import javax.swing.*;
import java.util.HashMap;

public class Main{

    static ListenableGraph<String, MyEdge> g= new ListenableUndirectedWeightedGraph<String, MyEdge>(MyEdge.class);

    static JFrame  frame;
    static HashMap<MyEdge, String> edges= new HashMap<>();
    static int numofnodes=0;
    static int weightedGraph[][];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                while(true) {
                    String input = JOptionPane.showInputDialog("1-Maximum Flow Algorithm\n2-Dijkstra Algorithm\n0-Exit");
                    if (input.equals("1")) {
                        frame = new JFrame("Maximum Flow Graph");
                        frame.setSize(600,600);
                        input = JOptionPane.showInputDialog("1-Directed Graph\n2-UnDirected Graph");
                        if(input.equals("1")) {
                            g= new graph().dir_Graph();
                            new maxFlow();
                        }else if(input.equals("2")) {
                            g= new graph().undir_Graph();
                                new maxFlow();
                        }

                    } else if (input.equals("2")) {
                        frame = new JFrame("Dijkstra Graph");
                        frame.setSize(400,400);
                        input = JOptionPane.showInputDialog("1-Directed Graph\n2-UnDirected Graph");
                        if (input.equals("1")){
                            g= new graph().dir_Graph();
                            new dijkstra("directed");
                        }else if(input.equals("2")) {
                            g= new graph().undir_Graph();
                            new dijkstra("undirected");
                        }

                    }
                    else if(input.equals("0"))
                        return;
                    else
                        JOptionPane.showMessageDialog(frame, "Error, Enter right choice");
                }
                //buildGraph();
                //maxFlow();
                //new dijkstra();
            }
        });
    }

    public static void ShowGui(ListenableGraph<String, MyEdge> g){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JGraphXAdapter<String, MyEdge> graphAdapter =
                new JGraphXAdapter<String, MyEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        frame.add(new mxGraphComponent(graphAdapter));
        frame.setVisible(true);
    }

    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return edges.get(this);
        }
    }

}

