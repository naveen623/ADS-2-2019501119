public class Outcast {
	private WordNet wordnet;
	// constructor takes a WordNet object
   public Outcast(WordNet wordnet) {
   		this.wordnet = wordnet;
   } 
   // given an array of WordNet nouns, return an outcast      
   public String outcast(String[] nouns) {
   		int d = 0;
   		String outcast = null;

   		for (String i : nouns) {
   			int distance = 0;
   			for (String j : nouns) {
   				int dist_len = wordnet.distance(i, j);
   				distance += dist_len;
   			}
   			if (distance > d) {
   				d = distance;
   				outcast = i;
   			}
   		}
   		return outcast;
   } 
   // see test client below
//    public static void main(String[] args) throws Exception {
// 	   String s = "synsets.txt";
// 	   String h = "hypernyms.txt";
//    		Wordnet wordnet = new Wordnet(s, h);
//     	Outcast outcast = new Outcast(wordnet);
//     	for (int t = 0; t < args.length; t++) {
//         	In in = new In(args[t]);
//         	String[] nouns = in.readAllStrings();
//         	System.out.println(args[t] + ": " + outcast.outcast(nouns));
//     	}
//    	}
}