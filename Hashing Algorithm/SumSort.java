import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Sripath Mishra on 11/5/2017.
 */
public class SumSort {
    private static int input[];
    private static int size;
    private static int sum[][];
    private static ArrayList<String> output=new ArrayList<String>();
    public static void main(String[] args) throws FileNotFoundException {
        size = StdIn.readInt();
        sum=new int[(size*(size-1))/2][3];
        int ctr=0;
        input = StdIn.readAllInts();
	for(int i=0;i<size-1;i++)
	{
            for(int j=i+1;j<size;j++)
            {
                sum[ctr][0]=input[i]+input[j];
                sum[ctr][1]=i;
                sum[ctr][2]=j;
                ctr+=1;
            }
        }
        Arrays.sort(sum, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0],o1[0]);
            }
        });
        for(int i=0;i<sum.length;i++)
        {
            int sumtemp=sum[i][0];
            int j=i+1;
            while(j<sum.length&&sum[j][0]==sumtemp)
            {
                int arr[]=new int[4];
                if(sum[j][1]>sum[i][1])
                {
                    arr[0]=sum[i][1];
                    arr[1]=sum[i][2];
                    arr[2]=sum[j][1];
                    arr[3]=sum[j][2];
                }
                else
                {
                    arr[0]=sum[j][1];
                    arr[1]=sum[j][2];
                    arr[2]=sum[i][1];
                    arr[3]=sum[i][2];
                }
                if(arr[0]<arr[1]&&arr[0]<arr[2]&&arr[2]<arr[3]&&arr[1]!=arr[2]&&arr[1]!=arr[3]) {
                    output.add(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]);
                }
                j+=1;
            }

        }
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
        for(int i=0;i<output.size();i++) {
		System.out.println(output.get(i));
        }
    }
}

