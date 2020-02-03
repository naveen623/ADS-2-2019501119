import java.util.Scanner;
class BlowingFuse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int s = 1;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();

        while(n != 0 && m != 0 && c != 0) {
            int cons[] = new int[n];
            boolean state[] = new boolean[n];

            for(int i = 0; i < n; i++) {
                cons[i] = sc.nextInt();

            }

            int consumption = 0;
            int maxConsumption = 0;
            boolean blown = false;
            for(int i = 0; i < m; i++) {
                int device = sc.nextInt() - 1;
                if(!blown) {
                    state[device] = !state[device];
                    consumption += cons[device] * (state[device] ? 1 : -1);


                    if(consumption > maxConsumption) {
                        maxConsumption = consumption;
                        // System.out.println(maxConsumption);
                        if(maxConsumption > c) {

                            blown = true;
                        }
                    }

                }

            }

            System.out.println("Sequence "+ s++);
            if(blown) {
                System.out.println("Fuse was Blown.");
            } else {
                System.out.println("Fuse was not Blown.");
                System.out.println("Maximal power consumption was "
                        + maxConsumption + " amperes.");
            }

            System.out.println();
            n = sc.nextInt();
            m = sc.nextInt();
            c = sc.nextInt();
        }

    }
}