import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sripa on 11/5/2017.
 */
public class Cuckoo {
    private static long prime1;
    private static long prime2;
    private static int size;
    private static String hash[][];
    private static int numelements;
    private static boolean verbose=false;
    public static void main(String[] args) throws FileNotFoundException {
        prime1=StdIn.readLong();
        prime2=StdIn.readLong();
        size=256;
        hash=new String[size][size];
        //setVerbose(true);
        if(verbose)
        System.out.println("(hash "+prime1+" "+prime2+" "+size+")");
        int n=StdIn.readInt();
	StdIn.readLine();
        for(int i=0;i<n;i++)
        {
            String str=StdIn.readLine();
            if(str.contains("size"))
                System.out.println(size());
            else if(str.contains("contains"))
                System.out.println(contains(Integer.parseInt(str.substring(str.indexOf(" ")+1))));
            else if(str.contains("get"))
                System.out.println(get(Integer.parseInt(str.substring(str.indexOf(" ")+1))));
            else if(str.contains("delete"))
               delete(Integer.parseInt(str.substring(str.indexOf(" ")+1)));
            else{
                String arr[]=str.split(" ");
                if(contains(Integer.parseInt(arr[1])).equals("no")&&(((1+0.05)*(numelements+1)*2)>size))
                rehash();
                put(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
            }
        }
    }
    public static boolean put(int key, int value) {

        if(hash[hashcode1(key)][0]!=null&&hash[hashcode1(key)][0].equals(String.valueOf(key)))
        {
            hash[hashcode1(key)][1]=String.valueOf(value);
            if(verbose)
            System.out.println("("+hashcode1(key)+" "+key+" "+value+")");
            return true;
        }
        if(hash[hashcode2(key)][0]!=null&&hash[hashcode2(key)][0].equals(String.valueOf(key)))
        {
            hash[hashcode2(key)][1]=String.valueOf(value);
            if(verbose)
            System.out.println("("+hashcode2(key)+" "+key+" "+value+")");
            return true;
        }
        if(hash[hashcode1(key)][0]==null){
            hash[hashcode1(key)][0]=String.valueOf(key);
            hash[hashcode1(key)][1]=String.valueOf(value);
            if(verbose)
            System.out.println("("+hashcode1(key)+" "+key+" "+value+")");
            ++numelements;
            return true;
        }
        if(hash[hashcode2(key)][0]==null){
            hash[hashcode2(key)][0]=String.valueOf(key);
            hash[hashcode2(key)][1]=String.valueOf(value);
            if(verbose)
            System.out.println("("+hashcode2(key)+" "+key+" "+value+")");
            ++numelements;
            return true;
        }
        int oldkey=Integer.parseInt(hash[hashcode1(key)][0]);
        int oldvalue=Integer.parseInt(hash[hashcode1(key)][1]);
        int oldindex=hashcode1(key);
        hash[hashcode1(key)][0]=String.valueOf(key);
        hash[hashcode1(key)][1]=String.valueOf(value);
        if(verbose)
        System.out.println("("+hashcode1(key)+" "+key+" "+value+")");
        int rehashnum=1;
        while(rehashnum<(3*(Math.log(size+1)/Math.log(1+0.05)))){
            if(oldindex==hashcode1(oldkey))
            {
                int tempindex=hashcode2(oldkey);
                if(hash[tempindex][0]==null)
                {
                    hash[tempindex][0]=String.valueOf(oldkey);
                    hash[tempindex][1]=String.valueOf(oldvalue);
                    if(verbose)
                    System.out.println("("+tempindex+" "+oldkey+" "+oldvalue+")");
                    ++numelements;
                    return true;
                }
                int tempoldkey=Integer.parseInt(hash[tempindex][0]);
                int tempoldvalue=Integer.parseInt(hash[tempindex][1]);
                hash[tempindex][0]=String.valueOf(oldkey);
                hash[tempindex][1]=String.valueOf(oldvalue);
                if(verbose)
                System.out.println("("+tempindex+" "+oldkey+" "+oldvalue+")");
                oldindex=tempindex;
                oldkey=tempoldkey;
                oldvalue=tempoldvalue;
                rehashnum++;
            }
            else{
                int tempindex=hashcode1(oldkey);
                if(hash[tempindex][0]==null)
                {
                    hash[tempindex][0]=String.valueOf(oldkey);
                    hash[tempindex][1]=String.valueOf(oldvalue);
                    if(verbose)
                    System.out.println("("+tempindex+" "+oldkey+" "+oldvalue+")");
                    ++numelements;
                    return true;
                }
                int tempoldkey=Integer.parseInt(hash[tempindex][0]);
                int tempoldvalue=Integer.parseInt(hash[tempindex][1]);
                hash[tempindex][0]=String.valueOf(oldkey);
                hash[tempindex][1]=String.valueOf(oldvalue);
                if(verbose)
                System.out.println("("+tempindex+" "+oldkey+" "+oldvalue+")");
                oldindex=tempindex;
                oldkey=tempoldkey;
                oldvalue=tempoldvalue;
                rehashnum++;
            }
        }
        rehash();
        put(oldkey,oldvalue);
        return false;
    }
    public static void rehash() {
        ArrayList<String> num=new ArrayList<String>();
        for(int i=0;i<size;i++)
        {
            if(hash[i][0]!=null)
                num.add(hash[i][0]+"-"+hash[i][1]);
        }
        size=size*2;
        hash=new String[size][size];
        numelements=0;
        if(verbose)
        System.out.println("(hash "+prime1+" "+prime2+" "+size+")");
        for(int i=0;i<num.size();i++)
        {
            int key=Integer.parseInt(num.get(i).substring(0,num.get(i).indexOf("-")));
            int value=Integer.parseInt(num.get(i).substring(num.get(i).indexOf("-")+1));
            put(key,value);
        }
    }
    public static String get(int key) {
        if(hash[hashcode1(key)][0]!=null&&hash[hashcode1(key)][0].equals(String.valueOf(key)))
        {
            return hash[hashcode1(key)][1];
        }
        if(hash[hashcode2(key)][0]!=null&&hash[hashcode2(key)][0].equals(String.valueOf(key)))
        {
            return hash[hashcode2(key)][1];
        }
        return "none";
    }
    public static String contains(int key) {
        if(hash[hashcode1(key)][0]!=null&&hash[hashcode1(key)][0].equals(String.valueOf(key)))
        {
            return "yes";
        }
        if(hash[hashcode2(key)][0]!=null&&hash[hashcode2(key)][0].equals(String.valueOf(key)))
        {
            return "yes";
        }
        return "no";
    }
    public static void delete(int key) {
        if(hash[hashcode1(key)][0]!=null&&hash[hashcode1(key)][0].equals(String.valueOf(key)))
        {
            int index=hashcode1(key);
            hash[index][0]=null;
            hash[index][1]=null;
            --numelements;
        }
        if(hash[hashcode2(key)][0]!=null&&hash[hashcode2(key)][0].equals(String.valueOf(key)))
        {
            int index=hashcode2(key);
            hash[index][0]=null;
            hash[index][1]=null;
            --numelements;
        }
    }
    public static int size() {
        return numelements;
    }
    public static int hashcode1(int key)
    {
        return (int)((prime1*key)/Math.pow(2,16))%size;
    }
    public static int hashcode2(int key)
    {
        return (int)((prime2*key)/Math.pow(2,16))%size;
    }
    public static void setVerbose(boolean set)
    {
        verbose=set;
    }
}

