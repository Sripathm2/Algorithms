import java.util.*;
/** * Created by sripath on 10/7/2017. */
public class Filter {
    public static void main(String[] args) {
        TreeMap<Integer,Integer> input= new TreeMap<Integer,Integer>();       
        ArrayList<Integer>  rem=new ArrayList<Integer>();        
        int size=StdIn.readInt();        
        for(int i=0;i<size;i++)
        {
            int x=StdIn.readInt();            
            int y=StdIn.readInt();           
             int ctr=0;           
              for(Map.Entry<Integer,Integer> entry : input.entrySet()) {
                Integer key= entry.getKey();               
                 Integer value = entry.getValue();         
                        if(key>x&&value>y) {
                    ctr = 1;                   
                     break;               
                      }
                if(x>key&&y>value)
                {
                    if(!rem.contains(key))
                    rem.add(key);             
                       }
            }
            if(ctr==0)
            input.put(x,y);     
               }
        for (Map.Entry<Integer, Integer> mapEntry : input.entrySet()) {
            if (!rem.contains(mapEntry.getKey())) {
                StdOut.println(mapEntry.getKey() + " " + mapEntry.getValue());         
                
                   }
        }
    }
}
