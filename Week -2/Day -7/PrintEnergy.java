public class PrintEnergy {

    public static void main(String[] args) {
        Picture pic = new Picture(args[0]);
        System.out.printf("image is %d pixels wide by %d pixels high.\n", pic.width(), pic.height());
        
        SeamCarver sc = new SeamCarver(pic);
        
        System.out.println("Printing energy calculated for each pixel.\n");        

        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                System.out.printf("%9.0f ", sc.energy(col, row));
            System.out.println();
        }
    }

}