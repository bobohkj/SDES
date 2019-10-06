public class SDES {
    final int[] p10;
    final int[] p8;
    final int[] p4;
    final int[] ep;
    final int[] ip;
    final int[] ipReverse;
    int[] key1;
    int[] key2;
    final int[][] s1;
    final int[][] s2;

    public SDES(){
        p10=new int[]{3,5,2,7,4,10,1,9,8,6};
        p8=new int[]{6,3,7,4,8,5,10,9};
        p4=new int[]{2,4,3,1};
        ep=new int[]{4,1,2,3,2,3,4,1};
        ip=new int[]{2,6,3,1,4,8,5,7};
        ipReverse=new int[]{4,1,3,5,7,2,8,6};
        s1=new int[][]{
                {2,1,2,1},
                {2,3,3,1},
                {2,3,2,2},
                {2,3,1,1},
        };
        s2=new int[][]{
                {1,2,3,2},
                {1,2,1,2},
                {2,2,3,0},
                {3,2,3,1},
        };
//        s1=new int[][]{
//                {1,0,3,2},
//                {3,2,1,0},
//                {0,2,1,3},
//                {3,1,3,2},
//        };
//        s2=new int[][]{
//                {0,1,2,3},
//                {2,0,1,3},
//                {3,0,1,0},
//                {2,1,0,3},
//        };


    }

    private void funcSW(int[] arr){
        leftShift(arr,0,arr.length-1,arr.length/2);
    }

    private int[] funcFK(int[] input,int[] subkey,int round){
        int[] left=new int[4];
        int[] right=new int[4];
        for (int i=0;i<4;i++){
            left[i]=input[i];
            right[i]=input[i+4];
        }
        showlog(right,"Right Half of IP, Begin round"+round);
        int[] outputOfF=funcF(right,subkey);
        showlog(left,"Left Half of IP");
        xor(left,0,outputOfF,0,4);
        showlog(left,"XOR Left Half with F");
        int[] output=new int[input.length];
        for (int i=0;i<4;i++){
            output[i]=left[i];
            output[i+4]=right[i];
        }
        return output;
    }

    private int[] funcF(int[] input, int[] subkey){
        int[] n=epm(input);
        showlog(n,"Expansion Permutation(IP)");
        xor(n,0,subkey,0,8);
        showlog(n,"XOR with Ki");
        showlog(n,0,4,"Left Half of XOR");
        int firstTwo=sbox(s1,n,0);
        int secondTwo=sbox(s2,n,4);
        int[] res=new int[]{firstTwo/2,firstTwo%2,secondTwo/2,secondTwo%2};
        showlog(res,0,2,"S-box Zero");
        showlog(n,4,4,"Right Half of XOR");
        showlog(res,2,2,"S-box One");
        showlog(res,0,4,"Join S-box Outputs");
        res=pfour(res);
        showlog(res,"Permute 4(F)");
        return res;
    }

    private int sbox(int[][] s,int[] input,int start){
        int row=input[start]*2+input[start+3];
        int col=input[start+1]*2+input[start+2];
        return s[row][col];
    }

    private void xor(int[] arr1, int start1,int[] arr2,int start2,int len){
        for (int i=0;i<len;i++){
            arr1[start1+i]^=arr2[start2+i];
        }
    }

    private int[] pip(int[] arr) {return p(arr,ip);}

    private int[] pipReverse(int[] arr) {return p(arr,ipReverse);}

    private int[] epm(int[] arr){
        return p(arr,ep);
    }

    private int[] peight(int[] arr){
        return p(arr,p8);
    }

    private int[] pfour(int[] arr){
        return p(arr,p4);
    }

    private int[] pten(int[] arr){
        return p(arr,p10);
    }

    private int[] p(int[] arr,int[] pm){
        int[] res=new int[pm.length];
        for(int i=0;i<pm.length;i++){
            res[i]=arr[pm[i]-1];
        }
        return res;
    }

    private void leftShift(int[] arr,int idx1,int idx2, int shift){
        shift%=idx2-idx1+1;
        int[] cache=new int[shift];
        for (int i=0;i<cache.length;i++){
            cache[i]=arr[idx1+i];
        }
        for (int i=idx1;i<=idx2-shift;i++){
            arr[i]=arr[i+shift];
        }
        for (int i=0;i<cache.length;i++){
            arr[idx2-shift+i+1]=cache[i];
        }
    }

    private void showlog(int[] res,String message){
        showlog(res,0,res.length,message);
    }

    private void showlog(int[] res,int start, int len, String message){
        StringBuilder sb=new StringBuilder(message+":                                                       ");
        sb.setLength(40);
        System.out.print(sb.toString()+"   ");
        for (int i=start;i<start+len;i++){
            System.out.print(res[i]);
        }
        System.out.println();
    }

    public void generateKeys(int[] key){
        int[] subkey=pten(key);
        showlog(subkey,"Key,Permute 10");
        leftShift(subkey,0,4,1);
        leftShift(subkey,5,9,1);
        showlog(subkey,"Left Shift Key (on 5's)");
        key1=peight(subkey);
        showlog(key1,"Key, Permute 8(K1)");
        leftShift(subkey,0,4,2);
        leftShift(subkey,5,9,2);
        showlog(subkey,"Key, Shift Left twice (on 5's)");
        key2=peight(subkey);
        showlog(key2,"Key, Permute 8(K2)");
    }

    public void encryption(int[] plaintext, int[] key){
        generateKeys(key);
        int[] ptext=pip(plaintext);
        int round=1;
        showlog(ptext,"Initial Permutation (IP)");
        ptext=funcFK(ptext,key1,round);
        showlog(ptext,"Replace Left Half of IP, End Round"+round);
        funcSW(ptext);
        showlog(ptext,"Swap Halves(SW)");
        round=2;
        ptext=funcFK(ptext,key2,round);
        showlog(ptext,"Replace Left Half of IP, End Round"+round);
        ptext=pipReverse(ptext);
        showlog(ptext,"Inverse Initial Permutation, Result");
    }

    public static void main(String[] args) {
        SDES sdes=new SDES();
        int[] plaintext=new int[]{1,0,0,1,0,1,1,1};
        int[] key=new int[]{0,1,0,1,0,1,0,1,0,1};
        sdes.encryption(plaintext,key);
    }
}
