import java.util.*;
import java.io.*;
class wordnet {
    static ArrayList<String> key = new ArrayList<String>();
    static  ArrayList<String[]> values = new ArrayList<String[]>();
        public void parsehypernyms() throws IOException {

        // pass the path to the fila as parameter.
        File file1 = new File("E:\\MSIT\\ADS-2 1119\\Day -1\\wordnet\\hypernyms.txt");

        // Reading the file through bufferedreader.
        BufferedReader burd = new BufferedReader(new FileReader(file1));

        String strng1;
        
        int v = 0;
        int s1,s2;
        while((strng1 = burd.readLine()) != null) {
            String arr2[] = strng1.split(",", 2);
            v++;
        if(arr2.length > 1) {
            key.add(arr2[0]);
            values.add(arr2[1].split(","));
        }
        else {
            key.add(arr2[0]);
            values.add(null);
        }
    }
    System.out.println("Vertices:" + v);
}
    public static void main(String[] args) throws IOException {
        In in = new In("E:\\MSIT\\ADS-2 1119\\Day -1\\wordnet\\digraph1.txt");
        Digraph g = new Digraph(in);
        SAP sap = new SAP(g);
        System.out.println("Length " + sap.length(3,11) + " Ancestor " + sap.ancestor(3,11));
    }
}
