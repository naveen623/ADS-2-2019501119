import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;


public class SAP {
    private Digraph dg;

    // constructor takes a dg (not necessarily a DAG)
    public SAP(Digraph G) {
        this.dg = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(final int v, final int w) {
        int length = Integer.MAX_VALUE;

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(dg, v); 
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(dg, w); 

        for (int i = 0; i < dg.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int len = bfsv.distTo(i) + bfsw.distTo(i);
                if (len < length) {
                    length = len;
                }
            }
        }
        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(dg, v); 
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(dg, w); 
        
        int length = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < dg.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int len = bfsv.distTo(i) + bfsw.distTo(i);
                if (len < length) {
                    length = len;
                    ancestor = i;
                }
            }
        }
        return ancestor;

    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int length = Integer.MAX_VALUE;
        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                }
            }
        }
        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i : v) {
            for (int j : w) {
                int len = length(i, j);
                if (len != -1 && len < length) {
                    length = len;
                    ancestor = ancestor(i, j);
                }
            }
        }
        return ancestor;
    }
}