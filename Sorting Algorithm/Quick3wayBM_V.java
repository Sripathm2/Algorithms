public class Quick3wayBM_V{
    public static void sort(Comparable[] a) {
        sort(a, 0, (a.length-1));
    }
    private static void sort(Comparable[] a, int lo, int hi) { 
	if(hi-lo<0)
	return;        
	int n = hi - lo+1;
        if (n <= 8) {
            insertionSort(a, lo, hi);
            return;
        }
        else if (n <= 40) {
            int m = median3(a, lo, lo + (n-1)/2, lo+(n-1));
            exch(a, m, lo);
        }
        else  {
            int eps = (n-1)/8;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a,lo +(3* eps),lo+(4*eps),lo+(5*eps));
            int m3 = median3(a,lo +(6* eps),lo+(7*eps),lo+(8*eps)); 
            int ninther = median3(a, m1, m2, m3);
            exch(a, ninther, lo);
        }
        int i = lo, j = hi+1;
        int p = lo, q = hi+1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
                
            if (i == j && eq(a[i], v)){
                exch(a, --q, j);
                j--;
                break;
                }
            if (i > j) break;
            if(i==j){
            j--;
            break;
            }

            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);
        }
        i = j + 1;
        for (int k = lo; k <= p; k++)
            exch(a, k, j--);
        for (int k = hi; k >= q; k--)
            exch(a, k, i++);
	for(int z=0;z<a.length;z++)
	StdOut.print(a[z]+" ");
	StdOut.println();
        sort(a, lo, j);
        sort(a, i, hi);
    }
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args) {
        
        int size=StdIn.readInt();
        Integer a[]=new Integer[size];
        for(int i=0;i<size;i++)
         a[i]=Integer.valueOf(StdIn.readInt());
	for(int i=0;i<a.length;i++)
	StdOut.print(a[i]+" ");
	StdOut.println();
        sort(a);
	for(int i=0;i<a.length;i++)
	StdOut.print(a[i]+" ");
	StdOut.println();
    }

}

