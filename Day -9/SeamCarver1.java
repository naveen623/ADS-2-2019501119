import java.awt.Color;
public class SeamCarver1 {
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL   = false;
    private Picture pic;
    private double[] distTo;
    private int[][] edgeTo;
    public SeamCarver1(Picture pic) {
        if (pic == null) {
            throw new IllegalArgumentException();
        }
        this.pic = new Picture(pic);
    }
    public Picture pic() {
        return new Picture(this.pic);
    }                          
    public int width() {
        return this.pic.width();
    }
    public int height() {
        return this.pic.height();
    }
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > width() - 1 || y > height() - 1) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return 1000;
        }
        double deltaX=0.0, deltaY=0.0;
        Color a   = this.pic.get(x, y + 1);
        Color b = this.pic.get(x, y - 1);
        Color c   = this.pic.get(x - 1, y);
        Color d  = this.pic.get(x + 1, y);
        deltaX = Math.pow((a.getRed() - c.getRed()), 2) + Math.pow((a.getGreen() -c.getGreen()), 2) + Math.pow((a.getBlue() - c.getBlue()), 2);
        deltaY = Math.pow((b.getRed() - d.getRed()), 2) + Math.pow((b.getGreen() - d.getGreen()), 2) + Math.pow((b.getBlue() - d.getBlue()), 2);
        return Math.sqrt(deltaX + deltaY);
    }
    public int[] findHorizontalSeam() {
        // return seam(HORIZONTAL);
        distTo = new double[this.height()];
        edgeTo = new int[this.width()][this.height()];
        int maxI,maxJ;
        for (int i = 0; i < this.distTo.length; i++) {
            this.distTo[i] = 1000;
        }
        maxI = this.width();
        maxJ = this.height();
        for (int i = 1; i < maxI; i++) {
            double[] lastDistTo = this.distTo.clone();
            for (int k = 0; k < this.distTo.length; k++) {
                this.distTo[k] = Double.POSITIVE_INFINITY;
            }
            for (int j = 1; j < maxJ; j++) {
                int x = i;
                int y = j;
                double energy = energy(x, y);
               relaxation(j - 1, x, y, energy, lastDistTo);
               relaxation(j, x, y, energy, lastDistTo);
               relaxation(j + 1, x, y, energy, lastDistTo);
            }
        }
        double minWeight = Double.POSITIVE_INFINITY;
        int min = 0;
        for (int i = 0; i < this.distTo.length; i++) {
            double weight = this.distTo[i];
            if(weight < minWeight) {
                min = i;
                minWeight = weight;
            }
        }
        int[] seam = new int[this.width()];
           for (int x = this.width() - 1; x >= 0; x--) {
                seam[x] = min;
                min = edgeTo[x][min];
            }
        
        
        return seam;
    }


    private void relaxation(int previous, int x, int y, double energy, double[] lastDistTo) {
        if (previous < 0 || previous >= lastDistTo.length) {
            return;
        }
        double weight = lastDistTo[previous];
        int index = x;
        if (this.distTo[index] > weight + energy) {
            this.distTo[index] = weight + energy;
            this.edgeTo[x][y] = previous;
        }
    }
}
    