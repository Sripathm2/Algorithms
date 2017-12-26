import edu.princeton.cs.algs4.*;
import java.util.*;
public class PercolationStats {
    char arr[];
    int size;
    WeightedQuickUnionUF wq;
    QuickFindUF qf;
    public void Percolationwq(int n)
    {
        size=n;
        wq = new WeightedQuickUnionUF(size * size + 2);
        arr=new char[size*size];
        for(int i=0;i<arr.length;i++)
            arr[i]='*';
    }
    public void Percolationqf(int n)
    {
        size=n;
        qf = new QuickFindUF(size * size + 2);
        arr=new char[size*size];
        for(int i=0;i<arr.length;i++)
            arr[i]='*';
    }
    public void openwq(int x,int y)
    {
        arr[x*size+y]='0';
        if(y==0)
            wq.union(x*size+y,size*size);
        if(y==size-1)
            wq.union(x*size+y,size*size+1);
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
    public void openqf(int x,int y)
    {
        arr[x*size+y]='0';
        if(y==0)
            qf.union(x*size+y,size*size);
        if(y==size-1)
            qf.union(x*size+y,size*size+1);
        if (x > 0 && isOpen(x - 1, y)) {
            qf.union(x*size+y,(x-1)*size+y);
        }
        if (x < size-1 && isOpen(x + 1, y)) {
            qf.union(x*size+y,(x+1)*size+y);
        }
        if (y > 0 && isOpen(x, y - 1)) {
            qf.union(x*size+y,x*size+(y-1));
        }
        if (y < size-1 && isOpen(x,y + 1)) {
            qf.union(x * size + y, x * size + (y + 1));
        }
    }
    public boolean percolateswq()
    {
        return (wq.connected(size*size,size*size+1));
    }
    public boolean percolatesqf()
    {
        return (qf.connected(size*size,size*size+1));
    }
    public boolean isOpen(int x,int y)
    {
        return  (arr[x*size+y]=='0');
    }
    public static void main(String[] args) {
        int n=Integer.parseInt(args[0]);
	int t=Integer.parseInt(args[1]);
	String speed=args[2];
	speed=speed.toLowerCase();
	int sp=0;
	if(speed.equals("fast"))
	sp=1;
        double pthres[]=new double[t];
	double time[]=new double[t];
	for(int i=0;i<t;i++)
	{
		Random rn=new Random();
		PercolationStats pr=new PercolationStats();
		if(sp==0)
			pr.Percolationqf(n);
		if(sp==1)
			pr.Percolationwq(n);
		long stime=System.nanoTime()/1000;
		double ctr=0;
		while(true)
		{     
	                  int x1=StdRandom.uniform(n);
	                  int x2=StdRandom.uniform(n);
	                  int y1=StdRandom.uniform(n);
	                  int y2=StdRandom.uniform(n);
	                  if(!pr.isOpen(x1,y1)&&sp==0){
	                  ctr++;
	                  pr.openqf(x1,y1);
	                  }
	                  if(!pr.isOpen(x2,y2)&&sp==0){
	                  ctr++;
	                  pr.openqf(x2,y2);
	                  }
			  if(!pr.isOpen(x1,y1)&&sp==1){
	                  ctr++;
	                  pr.openwq(x1,y1);
	                  }
	                  if(!pr.isOpen(x2,y2)&&sp==1){
	                  ctr++;
	                  pr.openwq(x2,y2);
	                  }
	                  if(sp==0)
			 	if(pr.percolatesqf())
					break;
			   if(sp==1)
			 	if(pr.percolateswq())
					break;
		}
		time[i]=((System.nanoTime()/1000)-stime);
		time[i]=time[i]/1000;
		pthres[i]=ctr/(n*n);
	
	}
	StdOut.println("mean threshold="+StdStats.mean(pthres)); 
	StdOut.println("std dev="+ StdStats.stddev(pthres));
	double ttime=0.0;
	for(int i=0;i<t;i++){
		ttime+=time[i];
		time[i]=time[i]/1000;
	}
	StdOut.println("time="+ (ttime/1000));
	StdOut.println("mean time="+StdStats.mean(time)); 
	StdOut.println("stddev time="+ StdStats.stddev(time));	
    }

}
