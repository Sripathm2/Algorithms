import java.util.*;
import java.io.*;
/** * Created by sripath Mishra on 10/9/2017.*/
public class Query {
    public static ArrayList<String> arrinput=new ArrayList<String>();
    public static HashMap<Integer,ArrayList<String>> L=new HashMap<Integer,ArrayList<String>>();
    public static void main(String[] args)throws IOException {
        int size=StdIn.readInt();
        for(int i=0;i<size;i++)
        {
            int x=StdIn.readInt();
            int y=StdIn.readInt();
            arrinput.add(x+" "+y);
        }
        Collections.sort(arrinput, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return(Integer.parseInt(o1.substring(0,o1.indexOf(' ')))>Integer.parseInt(o2.substring(0,o2.indexOf(' '))))?1:(Integer.parseInt(o1.substring(0,o1.indexOf(' ')))==Integer.parseInt(o2.substring(0,o2.indexOf(' '))))?0:-1;
            }
        });
        for(int i=size-1;i>-1;i--)
        {
            L.put(i,new ArrayList<String>());
            L.get(i).add(arrinput.get(i));
            if(i<size-1)
                L.get(i).addAll(L.get(i+1));
            Collections.sort(L.get(i), new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return(Integer.parseInt(o1.substring(o1.indexOf(' ')+1))>Integer.parseInt(o2.substring(o2.indexOf(' ')+1)))?1:(Integer.parseInt(o1.substring(o1.indexOf(' ')+1))==Integer.parseInt(o2.substring(o2.indexOf(' ')+1)))?0:-1;
                }
            });
        }
        int query= StdIn.readInt();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 2048);
        String output[]=new String[query];
        for(int i=0;i<query;i++)
        {
            int x=StdIn.readInt();
            int y=StdIn.readInt();
            int xstart=checkx(x);
            int ystart=checky(y,xstart);
	    if(ystart==-1){
                output[i] = "none\n";
                continue;
            }

            ArrayList<String> arrout= new ArrayList<String>(L.get(xstart).subList(checky(y,xstart),L.get(xstart).size()));
            Collections.sort(arrout, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return(Integer.parseInt(o1.substring(0,o1.indexOf(' ')))>Integer.parseInt(o2.substring(0,o2.indexOf(' '))))?1:(Integer.parseInt(o1.substring(0,o1.indexOf(' ')))==Integer.parseInt(o2.substring(0,o2.indexOf(' '))))?0:-1;
                }
            });
            if(arrout.size()==0) {
                output[i] = "none\n";
                continue;
            }
            String str=arrout.toString();
            str=str.substring(1,str.length()-1);
            str=str.replace(", ","\n");
            output[i]=str+"\n";
        }
        for(int i=0;i<query;i++)
            out.write(output[i]);
        out.flush();
    }
    public static int checkx(int x)
    {
        int low = 0, high = arrinput.size();
        while (low != high) {
            int mid = (low + high) / 2;
            if (Integer.parseInt(arrinput.get(mid).substring(0,arrinput.get(mid).indexOf(' '))) <= x) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
    public static int checky(int y,int x)
    {
	if(x>L.size()-1)
	return -1;
        int low = 0, high = L.get(x).size(); 
        while (low != high) {
            int mid = (low + high) / 2;
            if (Integer.parseInt(L.get(x).get(mid).substring(L.get(x).get(mid).indexOf(' ')+1)) <= y) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
}


