import edu.princeton.cs.algs4.*;
public class Percolation {
    char arr[];
    int size;
    WeightedQuickUnionUF wq;

   Percolation(int n)
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
       for(int i=0;i<size;i++)
	if(wq.connected(i*size+(size-1), x*size +y))
		return true;
	return false;
    }
    public boolean percolates()
    {
       for(int i=0;i<size;i++)
		for(int j=0;j<size;j++)
			if(wq.connected(i*size+(size-1), j*size))
				return true;
	return false;
    }
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int x =StdIn.readInt();
            int y =StdIn.readInt();
            percolation.open(x,y);
        }	
        if (percolation.percolates()) {
            StdOut.println("Yes");
        } else {
            StdOut.println("No");
        }

    }

}
