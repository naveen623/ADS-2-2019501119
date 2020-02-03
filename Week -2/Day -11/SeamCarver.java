import java.awt.Color;
import java.util.Arrays;

public class SeamCarver {
    private Picture picture;
    private double[] distTo;
    private int[][] edgeTo;
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.picture = new Picture(picture);
    }
    public Picture picture() {
        return new Picture(picture);
    }                          
    public int width() {
        return this.picture.width();
    }
    public int height() {
        return this.picture.height();
    }
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= width() || y >= height()) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return 1000;
        }
        double deltaX=0.0, deltaY=0.0;
        Color x1    = this.picture.get(x, y + 1);
        Color x2 = this.picture.get(x, y - 1);
        Color y1   = this.picture.get(x - 1, y);
        Color y2  = this.picture.get(x + 1, y);
        deltaX = Math.pow((x1.getRed() - x2.getRed()), 2) + Math.pow((x1.getGreen() - x2.getGreen()), 2) + Math.pow((x1.getBlue() - x2.getBlue()), 2);
        deltaY = Math.pow((y1.getRed() - y2.getRed()), 2) + Math.pow((y1.getGreen() - y2.getGreen()), 2) + Math.pow((y1.getBlue() - y2.getBlue()), 2);
        return Math.sqrt(deltaX + deltaY);
    }



    public int[] findVerticalSeam() {
        // return seam(VERTICAL);
        this.distTo = new double[width()];
        this.edgeTo = new int[width()][height()];
        int maxI,maxJ;
        for (int i = 0; i < this.distTo.length; i++) {
            this.distTo[i] = 1000;
        }
        
        for (int i = 1; i < height(); i++) {
            double[] lastDistTo = this.distTo.clone();
            for (int k = 0; k < this.distTo.length; k++) {
                this.distTo[k] = Double.POSITIVE_INFINITY;
            }
            for (int j = 1; j < width(); j++) {
                int x = j;
                int y = i;
                double energy = energy(x, y);
                v_relax(j - 1, x, y, energy, lastDistTo);
                v_relax(j, x, y, energy, lastDistTo);
                v_relax(j + 1, x, y, energy, lastDistTo);
            }
        }
        double minWeight = Double.POSITIVE_INFINITY;
        int min = 0;
        for (int i = 0; i < this.distTo.length; i++) {
            double weight = this.distTo[i];
            if (weight < minWeight) {
                min = i;
                minWeight = weight;
            }
        }
        int[] seam = new int[this.height()];
        for (int y = this.height() - 1; y >= 0; y--) {
            seam[y] = min;
            min = edgeTo[min][y];
        }
        return seam;
    }

    private void v_relax(int prev, int x, int y, double energy, double[] lastDistTo) {
        if (prev < 0 || prev >= lastDistTo.length) {
            return;
        }
        double weight = lastDistTo[prev];
        int index = x;
        if (this.distTo[index] > weight + energy) {
            this.distTo[index] = weight + energy;
            this.edgeTo[x][y] = prev;
        }
    }

    public int[] findHorizontalSeam() {
        // return seam(HORIZONTAL);
        this.distTo = new double[this.height()];
        this.edgeTo = new int[this.width()][this.height()];
        
        for (int i = 0; i < this.distTo.length; i++) {
            this.distTo[i] = 1000;
        }
        
        for (int i = 1; i < width(); i++) {
            double[] lastDistTo = this.distTo.clone();
            for (int k = 0; k < this.distTo.length; k++) {
                this.distTo[k] = Double.POSITIVE_INFINITY;
            }
            for (int j = 1; j < height(); j++) {
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


    private void relaxation(int prev, int x, int y, double energy, double[] lastDistTo) {
        if (prev < 0 || prev >= lastDistTo.length) {
            return;
        }
        double weight = lastDistTo[prev];
        int index = y;
        if (this.distTo[index] > weight + energy) {
            this.distTo[index] = weight + energy;
            this.edgeTo[x][y] = prev;
        }
    }


    public void removeVerticalSeam(int[] seam) {
        if (seam == null || this.width() <= 1 || seam.length != this.height()) {
            throw new IllegalArgumentException();
        }
        Picture newPicture = new Picture(this.width() - 1, this.height());
        int prevSeam = seam[0];
        for (int x = 0; x < this.height(); x++) {
            prevSeam = seam[x];
            for (int y = 0; y < this.width(); y++) {
                if (seam[x] == y)
                    continue;
                Color color = this.picture.get(y, x);
                if(seam[x] > y)
                    newPicture.set(y, x, color);
                else 
                    newPicture.set(y - 1, x, color);
            }
        }
        this.picture = newPicture;
    }


    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || this.height() <= 1 || seam.length != this.width()) {
            throw new IllegalArgumentException();
        }
        Picture newPicture = new Picture(this.width(), this.height() - 1);
        int prevSeam = seam[0];
        for (int x = 0; x < this.width(); x++) {
            prevSeam = seam[x];
            for (int y = 0; y < this.height(); y++) {
                if (seam[x] == y)
                    continue;
                Color color = this.picture.get(x, y);
                if (seam[x] > y)
                    newPicture.set(x, y, color);
                else
                    newPicture.set(x, y - 1, color);

            }
        }
        this.picture = newPicture;
    }

}





