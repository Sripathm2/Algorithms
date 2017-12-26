import java.awt.Font;
import edu.princeton.cs.algs4.*;

public class PercolationVisualizer {
    static PercolationVisualizer percolation;
    static int n;
    char arr[];
    int size;
    WeightedQuickUnionUF wq;

    PercolationVisualizer(int n)
    {
        size=n;
        wq = new WeightedQuickUnionUF(size * size);
        arr=new char[size*size];
        for(int i=0;i<arr.length;i++)
            arr[i]='*';
    }
    public void open(int x,int y)
    {
        arr[x*size+y]='0';
        if (x > 0 && isOpen(x - 1, y)) {
            wq.union(x*size+y,(x-1)*size+y);
        }
        if (x < size-1 && isOpen(x + 1, y)) {
            wq.union(x*size+y,(x+1)*size+y);
        }
        if (y > 0 && isOpen(x, y - 1)) {
            wq.union(x*size+y,x*size+(y-1));
        }
        if (y < size-1 && isOpen(x,y + 1)) {
            wq.union(x * size + y, x * size + (y + 1));
        }
    }
    public boolean isOpen(int x,int y)
    {
        return  (arr[x*size+y]=='0');
    }
    public boolean isFull(int x,int y)
    {
       for(int i=0;i<n;i++)
	if(wq.connected(i*size+(size-1), x*size +y))
		return true;
	return false;
    }
    public static void draw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05*n, 1.05*n);
        StdDraw.setYscale(-0.05*n, 1.05*n);
        StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);
        int ctr=0;
        int arr[][]=new int[n][n];
        for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			 if (percolation.isOpen(i,j)){
                   		arr[i][j]=1;
				ctr++;
			 }
			 
			 if (percolation.isFull(i, j)){
                   		arr[i][j]=2;
			 }
                	 if(!percolation.isOpen(i,j))
				arr[i][j]=0;
		}
	} 

	for (int i=0;i < n/2; i++)
        {
            for (int j = i; j< n-i-1; j++)
            {
                int temp = arr[i][j];
                arr[i][j] = arr[j][n-1-i];
                arr[j][n-1-i] = arr[n-1-i][n-1-j];
                arr[n-1-i][n-1-j] = arr[n-1-j][i];
                arr[n-1-j][i] = temp;
            }
        }
         for (int i = 0; i< n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]==2) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                }
                if (arr[i][j]==1) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                }
                  if (arr[i][j]==0){
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(j + 0.5, n - i - 0.5, 0.45);
            }
        }
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5*n, -0.02*n, ctr + " open sites");
    }

    public static void main(String args[]) {
        n = StdIn.readInt();
        percolation = new PercolationVisualizer(n);
        StdDraw.enableDoubleBuffering();
        draw();
        StdDraw.show();
        StdDraw.pause(200);
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            percolation.open(x,y);
            draw();
            StdDraw.show();
            StdDraw.pause(200);
        }
    }
}
