import java.util.*;
import java.io.*;
class Sample {
    static ArrayList<String> key = new ArrayList<String>();
    static ArrayList<String[]> values = new ArrayList<String[]>();
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
            b++;
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
        Sample w = new Sample();
        // w.parsesynsets();
        w.parsehypernyms();


        Digraph d = new Digraph(key.size());
        int edge_count = 0;
        for(int i = 0; i < key.size(); i++) {
            if(values.get(i) != null) {
                for(int j = 0; j < values.get(i).length; j++) {
                    int s1 = Integer.parseInt(key.get(i));
                    int s2 = Integer.parseInt(values.get(i)[j]);
                    d.addEdge(s1,s2);
                    edge_count++;
                }
            }
        }
        System.out.println("Edges:" + edge_count);


    }
}