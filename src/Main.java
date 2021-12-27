import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        int[][] matrix = new int[n][n];
        Random rand = new Random();
        int r;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    r = rand.nextInt(2);
                    matrix[i][j] = r;
                    matrix[j][i] = r;
                }
                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }


        Main m = new Main();
        m.Print(matrix, n);

        double dt = m.Determinant(matrix, n);
        System.out.println("Определитель равен " + dt);

        m.Graft(matrix, n);

    }

    int m [][];
    double Determinant(int[][] matrix, int n){
        double dt = 0;
        if (n==1){
            dt = matrix[0][0];
        }
        if (n==2){
            dt =  matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        if (n==3){
            dt = Determinant3(matrix);
        }
        if (n>3) {
            dt = 0;
            for (int i = 0; i < n; i++) {
                m = Matrix(matrix, n, i);
                dt = dt + Math.pow(-1, i) * matrix[0][i] * Determinant(m, n-1);
            }
        }
        return dt;
    }

    void Print (int[][] m, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" " + m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    int Determinant3 (int[][] m){
        int d = m[0][0]*m[1][1]*m[2][2]+m[0][1]*m[1][2]*m[2][0]+m[1][0]*m[2][1]*m[0][2]-
                m[0][2]*m[1][1]*m[2][0]-m[0][1]*m[1][0]*m[2][2]-m[2][1]*m[1][2]*m[0][0];
        return d;
    }

    int[][] Matrix (int[][] matrix, int n, int numStr){
        m = new int[n-1][n-1];
        int a;
        for (int i = 1; i < n; i++){
            a=0;
            for (int j=0; j<n; j++){
                if(j != numStr) {
                    m[i - 1][a] = matrix[i][j];
                    a++;
                }
            }
        }
        return m;
    }

    int k;
    int[][] mt = new int[k][k];

    public void Graft (int[][] matrix, int n){
        JFrame frame = new JFrame();
        //Завершает работу программы при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        k = n;
        mt = matrix;
        Paint graft = new Paint();
        frame.getContentPane().add(graft);
        frame.setSize(700,250);
        //Делает фрейм видимым
        frame.setVisible(true);


    }

    class Paint extends JPanel{

        public void paint(Graphics g){
            int[] xCoordinate = new int[k];
            int x = 0;
            int y = 160;
            for (int i = 0; i<k; i++){
                x = x + 55;
                //y = y + 40;
                g.setColor(Color.black);
                g.fillOval(x,y,25,25);
                g.setColor(Color.white);
                if (i<9) {
                    g.drawString(i + 1 + "", x + 9, y + 17);
                } else {
                    g.drawString(i + 1 + "", x + 6, y + 17);
                }
                xCoordinate[i] = x;
            }

            int a, b;
            int h = 0;
            int t = 0;
            Graphics2D g2d = (Graphics2D) g;
            for (int i = 0; i<k; i++){
                for (int j = i + 1; j<k; j++){
                    if (mt[i][j]==1){
                        switch (i%3){
                            case (0): g2d.setColor(Color.black); break;
                            case (1): g2d.setColor(Color.gray); break;
                            case (2): g2d.setColor(Color.orange); break;
                        }
                        g2d.setStroke(new BasicStroke(2));
                        a = xCoordinate[i]+10;
                        b = xCoordinate[j]+10;
                        h = h + 10;
                        t = t + 5;
                        g2d.drawArc(a, y - t, b - a, h, 0, 180);

                    }
                }
            }
        }

    }
}



