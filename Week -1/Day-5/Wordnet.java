import java.util.*;
import java.io.*;
class Wordnet {

    private LinearProbingHashST<String, ArrayList<Integer>> ht;
    private LinearProbingHashST<Integer, String> ht1;
   
    int v;
    SAP sap;
    In in;
    Digraph d;

   // constructor takes the name of the two input files
   public Wordnet(String synsets, String hypernyms) throws Exception {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("File name is null");
        }
        v = 0;
        readSynsets(synsets);
        readHypernyms(hypernyms);
    }

    private void readSynsets(String synsets) throws Exception {
        ht = new LinearProbingHashST<String, ArrayList<Integer>>();
        ht1 = new LinearProbingHashST<Integer, String>();

        Scanner scanSyn = new Scanner(new File(synsets));
        while (scanSyn.hasNextLine()) {
            v++;
            String[] str = scanSyn.nextLine().split(",");
            int id = Integer.parseInt(str[0]);
            ht1.put(id, str[1]);

            String[] words = str[1].split(" ");
            for (int i = 0; i < words.length; i++) {
                if (ht.contains(words[i])) {
                    ArrayList<Integer> id_list = ht.get(words[i]);
                    id_list.add(id);
                    ht.put(words[i], id_list);
                }
                else {
                    ArrayList<Integer> new_id = new ArrayList<Integer>();
                    new_id.add(id);
                    ht.put(words[i], new_id);
                }
            }
        }
        // System.out.println("Vertices: " + v);
        // System.out.println(htable1);
    }

    private void readHypernyms(String hypernyms) throws Exception {

        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String[]> s2 = new ArrayList<String[]>();

        Scanner scanHyp = new Scanner(new File(hypernyms));
        while(scanHyp.hasNextLine()) {
            String[] str = scanHyp.nextLine().split(",",2);
            if (str.length > 1) {
                s1.add(str[0]);
                s2.add(str[1].split(","));
            }
            else {
                s1.add(str[0]);
                s2.add(null);
            }
            // for (int i = 1; i < str.length; i++) {
            //     System.out.println(str[0] + " " + str[i]);
            //     d.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[i]));
            // }
        }

        int e = 0;
        d = new Digraph(s1.size());
        for (int i = 0; i < s1.size(); i++) {
            if (s2.get(i) != null) {
                for (int j = 0; j < s2.get(i).length; j++) {
                    int d1 = Integer.parseInt(s1.get(i));
                    int d2 = Integer.parseInt(s2.get(i)[j]);
                    d.addEdge(d1, d2);
                    e++;
                }
            }
        }
        // System.out.println("Edges: " + e);
    }

   // returns all WordNet nouns
   public Iterable<String> nouns() {
        return ht.keys();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word is null");
        }
        return ht.contains(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounX, String nounY) {
        sap = new SAP(d);
        if (!isNoun(nounX) || !isNoun(nounY)) {
            throw new IllegalArgumentException("Not WordNet Noun");
        }
        return sap.length(ht.get(nounX), ht.get(nounY));
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounX, String nounY) {
        sap = new SAP(d);
        if (!isNoun(nounX) || !isNoun(nounY)) {
            throw new IllegalArgumentException("Not WordNet Noun");
        }  
        return ht1.get(sap.ancestor(ht.get(nounX), ht.get(nounY)));
   }
}