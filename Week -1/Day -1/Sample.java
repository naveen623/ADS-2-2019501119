import java.util.Scanner;
class Sample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("82187,zygotene,the second stage of the prophase of meiosis");
        String s = sc.nextLine();
        String st[] = s.split(" ");
        for(int i = 0; i < st.length-1; i++) {
            System.out.println(st[i]);
        }
    }
}