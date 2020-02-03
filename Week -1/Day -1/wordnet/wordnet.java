import java.io.*;
class wordnet {
    public void parsesynsets() throws IOException{

        // pass the path to the file as parameter.
        File file = new File("E:\\MSIT\\ADS-2 1119\\Day -1\\wordnet\\synsets.txt");

        // Reading the file through bufferedreader.
        BufferedReader burd = new BufferedReader(new FileReader(file));
        String strng;

        int a = 0;

        // If the line we are reading is not null then go into while loop.
        while((strng = burd.readLine()) != null) {
            //we will split the line with ',' and storing in array
            String arr1[] = strng.split(",");
            if(a == 7) {
                break;
            }
            System.out.println(arr1[0] + " " + arr1[1]);
            a++;
        }
    }
        public void parsehypernyms() throws IOException {

        // pass the path to the fila as parameter.
        File file1 = new File("E:\\MSIT\\ADS-2 1119\\Day -1\\wordnet\\hypernyms.txt");

        // Reading the file through bufferedreader.
        BufferedReader burd = new BufferedReader(new FileReader(file1));

        String strng1;
        
        int b = 0;
        while((strng1 = burd.readLine()) != null) {
            String arr2[] = strng1.split(",");
            if(b == 7) {
                break;
            }
            System.out.println(arr2[0] + " " + arr2[1]);
            b++;
        }
    }

    public static void main(String[] args) throws IOException {
        wordnet w = new wordnet();
        w.parsesynsets();
        w.parsehypernyms();

    }
}