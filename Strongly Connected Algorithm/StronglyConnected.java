
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
* Created by sripath Mishra on 11/14/2017.
*/
public class StronglyConnected {
    private static int size;
    private static int edgenum;
    private static int minedgesrequired;
    private static ArrayList<Integer> process=new ArrayList<Integer>();
    private static ArrayList<String> output= new ArrayList<String>();
    private static HashMap<Integer,ArrayList<Integer>> edges=new HashMap<Integer,ArrayList<Integer>>();
    private static HashMap<Integer,Integer> edgesfinal=new HashMap<Integer,Integer>();
    private static HashMap<String,Integer> counter= new HashMap<String,Integer>();
    private static Digraph finalgraph;
    public static void main(String[] args)throws Exception {
        size = StdIn.readInt();
        edgenum= StdIn.readInt();
        minedgesrequired=0;
        finalgraph=new Digraph(size);
        for(int i=0;i<size;i++)
        {
            edges.put(i,new ArrayList<Integer>());
        }
        for(int i=0;i<edgenum;i++)
        {
            int x= StdIn.readInt();
            int y= StdIn.readInt();
            edges.get(x).add(y);
            edges.get(y).add(x);
            if(counter.containsKey(x+" "+y))
            {
                counter.replace(x+" "+y,counter.get(x+" "+y)+1);
                counter.replace(y+" "+x,counter.get(y+" "+x)+1);
            }
            else
            {
                counter.put(x+" "+y,1);
                counter.put(y+" "+x,1);
            }
        }
        for(int i=0;i<size;i++)
        {
            Collections.sort(edges.get(i));
        }
        depthfirstSerach(size-1);
        for(int i=0;i<output.size();i++)
        {
            int x=Integer.parseInt(output.get(i).substring(0,output.get(i).indexOf(" ")));
            int y=Integer.parseInt(output.get(i).substring(output.get(i).indexOf(" ")+1));
            finalgraph.addEdge(x,y);
        }
        for(int i=0;i<size;i++){
            DirectedDFS dd=new DirectedDFS(finalgraph,i);
            edgesfinal.put(i,dd.count());
        }
        while(true)
        {
            int min_edge=size-1;
            int min_ctr=edgesfinal.get(size-1);
            for(int i=0;i<size;i++){
                if(min_ctr>edgesfinal.get(i)) {
                    min_ctr = edgesfinal.get(i);
                    min_edge = i;
                }
            }
            if(min_ctr==edgesfinal.get(size-1))
                break;
            finalgraph.addEdge(min_edge,size-1);
            minedgesrequired+=1;
            int ctr=0;
            for(int i=0;i<size;i++){
                DirectedDFS dd=new DirectedDFS(finalgraph,i);
                edgesfinal.replace(i,dd.count());
                if(dd.count()!=edgesfinal.get(size-1))
                    ctr=-1;
            }
            if(ctr==0)
            break;
        }
        System.out.println(minedgesrequired);
        for(int i=0;i<output.size();i++)
        {
            StdOut.println(output.get(i));
        }
    }
    public static void depthfirstSerach(int i)
    {
        if(process.contains(i))
            return;
        process.add(i);
        for(int j=0;j<edges.get(i).size();j++)
        {
            if(i==edges.get(i).get(j)&&counter.get(i+" "+i)>0)
            {
                output.add(i+" "+edges.get(i).get(j));
                counter.replace(i+" "+i,counter.get(i+" "+i)-2);
            }
            if((counter.get(i+" "+edges.get(i).get(j))>0)) {
                output.add(i + " " + edges.get(i).get(j));
                counter.replace(i+" "+edges.get(i).get(j),counter.get(i+" "+edges.get(i).get(j))-1);
                counter.replace(edges.get(i).get(j)+" "+i,counter.get(edges.get(i).get(j)+" "+i)-1);
                depthfirstSerach(edges.get(i).get(j));
            }
        }
    }
}

