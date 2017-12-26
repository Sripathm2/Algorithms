import java.util.*;
public class Rhyming {
    static  String data[];
    static ArrayList<String> key=new ArrayList<String>();
    static ArrayList<String> value=new ArrayList<String>();
    static Map.Entry me;
    static Map<String,String> arr1=new TreeMap<String,String>();
    static String arr[];
    static String arr2[];
    public static void main(String[] args) {       
	data=StdIn.readAllStrings();
        for(int i=0;i<data.length;i++)
        {
            for(int j=data[i].length()-1;j>-1;j--)
            {
                if(!arr1.containsKey(data[i].substring(j))){
                    arr1.put(data[i].substring(j),data[i]);
		    continue;		
		}
		if(arr1.containsKey(data[i].substring(j)))
                    arr1.replace(data[i].substring(j),"|"+arr1.get(data[i].substring(j))+"-"+data[i]);
            }
        }
	Set set=arr1.entrySet();
	Iterator i=set.iterator();
	while(i.hasNext())
	{
		me=(Map.Entry)i.next();
		if(String.valueOf(me.getValue()).indexOf('|')==-1)
		{
			arr1.replace(String.valueOf(me.getKey()),"*");
			continue;
		}
		String s=String.valueOf(me.getKey());
		for(int j=s.length()-1;j>0;j--)
            	{
		if(arr1.containsKey(s.substring(j))&&String.valueOf(me.getValue()).lastIndexOf('|')==String.valueOf(arr1.get(s.substring(j))).lastIndexOf('|'))
                    arr1.replace(s.substring(j),"*");
            	}
	}
	set=arr1.entrySet();
	i=set.iterator();
	while(i.hasNext())
	{
		me=(Map.Entry)i.next();
		if(!String.valueOf(me.getValue()).equals("*"))
		{
		key.add(String.valueOf(me.getKey()));
		value.add(String.valueOf(me.getValue()).substring(String.valueOf(me.getValue()).lastIndexOf('|')+1)+"-");
		}
	}
        arr=key.toArray(new String[0]);
	arr2=value.toArray(new String[0]);
        sort(arr,0,arr.length-1);
    	for(int j=0;j<arr.length;j++)
	{
		StdOut.print("[ ");
		String out[]=arr2[j].split("-");
		int ctr=1;
		for(int z=0;z<out.length;z++)
		{
		if(ctr==1) {
                    StdOut.print(out[z].substring(0, out[z].length() - arr[j].length()) + "|" +out[z].substring(out[z].length() - arr[j].length()));
                    ctr=0;
		    continue;
                }
                if(ctr==0)
                    StdOut.print(", "+out[z].substring(0,out[z].length()-arr[j].length())+"|"+out[z].substring(out[z].length()-arr[j].length()));
		}
		StdOut.print(" ]");
		StdOut.println();
	}
	StdOut.println(arr.length);
    }
    private static void sort(Comparable[] a, int lo, int hi) { 
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
            exch(a, ninther, lo);;
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
        String s=(String)v;
        String s1=(String)w;
        if(s.length()<s1.length())
            return true;
        if(s.length()>s1.length())
            return false;
        return v.compareTo(w) < 0;
    }
    private static boolean eq(Comparable v, Comparable w) {
        String s=(String)v;
        String s1=(String)w;
        if(s.length()!=s1.length())
            return false;
        return v.compareTo(w) == 0;
    }
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
	String temp=arr2[i];
	arr2[i]=arr2[j];
	arr2[j]=temp;
    }
}
