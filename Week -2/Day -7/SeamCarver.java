import java.awt.Color;

public class SeamCarver {
    int width;
    int height;
    Picture pic;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture pic) {
        if(pic == null) {
            throw new IllegalArgumentException("pic is null");
        }

        width = pic.width();
        height = pic.height();
        this.pic = pic;
    }
 
    // current picture
    public Picture pic() {
        return pic;
    }
 
    // width of current picture
    public int width() {
        return width;
    }
 
    // height of current picture
    public int height() {
        return height;
    }
 
    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if(x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException();
        }
        if(x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
        }

        int deltaX = del_energyCal(x + 1, y, x - 1, y);
        int deltaY = del_energyCal(x - 1, y, x - 1, y);
        
        double energy = Math.sqrt(deltaX + deltaY);
        return energy;
    }

    private int del_energyCal(int x1, int y1, int x2, int y2) {
        Color a = pic.get(x1, y1);
        Color b = pic.get(x2, y2);

        int Red = a.getRed() - b.getRed();
        int Green = a.getGreen() - b.getGreen();
        int Blue = a.getBlue() - b.getBlue();

        int RedGreenBlue = (Red * Red) + (Green * Green) + (Blue * Blue);
        return RedGreenBlue;

    } 
}


 
//     // sequence of indices for horizontal seam
//     public int[] findHorizontalSeam() {

//     }
 
//     // sequence of indices for vertical seam
//     public int[] findVerticalSeam() {

//     }
 
//     // remove horizontal seam from current picture
//     public void removeHorizontalSeam(int[] seam) {

//     }
 
//     // remove vertical seam from current picture
//     public void removeVerticalSeam(int[] seam) {

//     }
 
//     //  unit testing (optional)
//     public static void main(String[] args) {

//     }
 
//  }