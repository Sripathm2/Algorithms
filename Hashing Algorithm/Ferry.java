import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class Ferry {
    public static void main(String[] args)throws Exception {
            int L =Integer.parseInt(args[0]);
            int numcar=StdIn.readInt();
            boolean [][] dp = new boolean [2][L+1];
            Arrays.fill(dp[0], false);
            dp[0][0] = true;
            int t=0, pt;
            int i = 0;
            int N = 0;
            int sumlen = 0;
            for(int j=0;j<numcar;j++){
                int curlen = StdIn.readInt();
                if(curlen==0) break;
                pt = t; t ^= 1;
                ++i;
                sumlen += curlen;
                Arrays.fill(dp[t], false);
                boolean canload = false;
                for(int len=0; len<=L; len++){
                    if(dp[pt][len]==false) continue;
                    if(len+curlen<=L && sumlen-(len+curlen)<=L){
                        dp[t][len+curlen] = true;
                        canload = true;
                    }
                    if(sumlen-len<=L){
                        dp[t][len] = true;
                        canload = true;
                    }
                }
                if(!canload) break;
                else N = i;
            }
            System.out.println(N);
    }
}
