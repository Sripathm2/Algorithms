import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
* Created by sripath Mishra on 11/5/2017.
*/
public class SumHash {
    private static int input[];
    private static int size;
    private static Hashtable<Integer,String> sum;
    private static ArrayList<String> output=new ArrayList<String>();
    public static void main(String[] args) throws FileNotFoundException {
        Set<Integer> keys=new HashSet<Integer>();
        size = StdIn.readInt();
        input =StdIn.readAllInts();
        sum=new Hashtable<Integer,String>((int)(((size)*(size-1))/1.5));
        for(int i=0;i<size-1;i++)
        {
            for(int j=i+1;j<size;j++)
            {
                if(sum.containsKey(input[i]+input[j])) {
                    String temp=sum.get(input[i] + input[j]);
                    sum.put(input[i]+input[j],temp+"-"+i+"/"+j);
                    keys.add(input[i]+input[j]);
                }
                else {
                    sum.put((input[i] + input[j]),i+"/"+j);
                }
            }
        }
        input=null;
        for(int i : keys)
        {
            //System.out.println(sum.get(i));
            String pairs[]=sum.get(i).split("-");
            for(int j=0;j<pairs.length;j++)
            {
                for(int k=j+1;k<pairs.length;k++)
                {
                    int arr[]=new int[4];
                    arr[0]=Integer.parseInt(pairs[j].substring(0,pairs[j].indexOf("/")));
                    arr[1]=Integer.parseInt(pairs[j].substring(pairs[j].indexOf("/")+1));
                    arr[2]=Integer.parseInt(pairs[k].substring(0,pairs[k].indexOf("/")));
                    arr[3]=Integer.parseInt(pairs[k].substring(pairs[k].indexOf("/")+1));
                    if(arr[0]<arr[1]&&arr[0]<arr[2]&&arr[2]<arr[3]&&arr[1]!=arr[2]&&arr[1]!=arr[3]) {
                        output.add(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]);
                    }
                }
            }
        }
        keys=null;
        sum=null;
        Collections.sort(output, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String arr1[]=o1.split(" ");
                String arr2[]=o2.split(" ");
                if(arr1[0].equals(arr2[0]))
                {
                    if(arr1[1].equals(arr2[1])) {
                        if(arr1[2].equals(arr2[2])) {
                            return Integer.parseInt(arr1[3])-Integer.parseInt(arr2[3]);
                        }
                        return Integer.parseInt(arr1[2])-Integer.parseInt(arr2[2]);
                    }
                    return Integer.parseInt(arr1[1])-Integer.parseInt(arr2[1]);
                }
                return Integer.parseInt(arr1[0])-Integer.parseInt(arr2[0]);
            }
        });
        System.out.println(output.size());
        for(int i=0;i<output.size();i++)
            System.out.println(output.get(i));
    }
}

