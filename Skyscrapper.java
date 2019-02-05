import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;


public class Skyscrapper {
    public static void main(String[] args) throws IOException {
        if (args[0].equals("1")){
            program1();
        }
        else if (args[0].equals("2")){
            program2();
        }
        else if (args[0].equals("3")){
            program3();
        }
        else if (args[0].equals("4")){
            program1();
        }
    }
    static void program1() throws IOException {
        //input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String strline = in.readLine();

        int N = Integer.parseInt(strline.split(" ")[0]);
        int M = Integer.parseInt(strline.split(" ")[1]);
        int c = Integer.parseInt(strline.split(" ")[2]);


        int i,j;
        int m = N;
        int n = M;
        int[] s = new int[n];
        int[] s1 = new int[n];

        int max_i = 0;
        int max_j = 0;
        int max = 0;

        //dynamic programming
        for(i = 0; i < m; i++)
        {
            s1 = new int[n];
            strline = in.readLine();
            for(j = 0; j < n; j++)
            {
                if (Integer.parseInt(strline.split(" ")[j]) != c){
                    s1[j] = 0;
                }else if (i == 0 || j == 0){
                    s1[j] = 1;
                }else{
                    s1[j] = Math.min(s1[j-1],Math.min(s[j], s[j-1])) + 1;
                }
                if (s1[j] > max){
                    max = s1[j];
                    max_i = i;
                    max_j = j;
                }
            }
            s = s1;
        }
        System.out.print(max_i - max + 2);
        System.out.print(" ");
        System.out.print(max_j - max + 2);
        System.out.print(" ");
        System.out.print(max_i + 1);
        System.out.print(" ");
        System.out.print(max_j + 1);

        in.close();
    }
    static void program2() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String strline = in.readLine();

        int N = Integer.parseInt(strline.split(" ")[0]);
        int M = Integer.parseInt(strline.split(" ")[1]);
        int c = Integer.parseInt(strline.split(" ")[2]);

        int R = N;
        int C = M;
        int height = c;

        int[] s = new int[C];
        int[] s1 = new int[C];

        strline = in.readLine();
        for (int j = 0; j < C; j++) {
            if (Integer.parseInt(strline.split(" ")[j]) != height) {
                //A[0][j] = 0;
                s[j] = 0;
            }
            else {
                //A[0][j] = 1;
                s[j] = 1;
            }
        }
        MaxArea maxArea = maxBlock(R, C, height, s);


        //find maximum rectangular area, every row is a histogram
        for (int i = 1; i < R; i++) {
            s1 = new int[C];
            strline = in.readLine();
            for (int j = 0; j < C; j++){
                // if A[i][j] is 1 then add A[i -1][j]
                if (Integer.parseInt(strline.split(" ")[j]) == height) {
                    //int a = A[i][j];
                    //A[i][j] = 1;
                    //A[i][j] = A[i - 1][j] + 1;
                    s1[j] = s[j] + 1;
                } else{
                    s1[j] = 0;
                }
            }

            MaxArea newmaxarea = maxBlock(R, C, height, s1);
            if (maxArea.max_area < newmaxarea.max_area) {
                maxArea = newmaxarea;
                maxArea.max_i = i;
            }
            s = s1;
        }
        System.out.print(maxArea.max_i - maxArea.max_ilength + 2);
        System.out.print(" ");
        System.out.print(maxArea.max_j - maxArea.max_jlength + 2);
        System.out.print(" ");
        System.out.print(maxArea.max_i + 1);
        System.out.print(" ");
        System.out.print(maxArea.max_j + 1);

        in.close();
    }
    static void program3() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String strline = in.readLine();

        int N = Integer.parseInt(strline.split(" ")[0]);
        int M = Integer.parseInt(strline.split(" ")[1]);
        int c = Integer.parseInt(strline.split(" ")[2]);

        int R = N;
        int C = M;
        int height = c;

        int[] s = new int[C];
        int[] s1 = new int[C];

        strline = in.readLine();
        for (int j = 0; j < C; j++) {
            if (Integer.parseInt(strline.split(" ")[j]) != height) {
                s[j] = 0;
                //A[0][i] = 0;
            }else{
                s[j] = 1;
            }
        }
        MaxArea maxArea = maxHist(R, C, height, s);

        //find maximum rectangular area, every row is a histogram
        for (int i = 1; i < R; i++)
        {
            s1 = new int[C];
            strline = in.readLine();
            for (int j = 0; j < C; j++) {
                // if A[i][j] is 1 then add A[i -1][j]
                if (Integer.parseInt(strline.split(" ")[j]) == height) {
                    //A[i][j] += A[i - 1][j];
                    s1[j] = 1 + s[j];
                } else {
                    //A[i][j] = 0;
                    s1[j] = 0;
                }
            }
            MaxArea newmaxarea = maxHist(R,C,height, s1);
            if (maxArea.max_area < newmaxarea.max_area){
                maxArea = newmaxarea;
                maxArea.max_i = i;
            }
            s = s1;

        }

        System.out.print(maxArea.max_i - maxArea.max_ilength + 2);
        System.out.print(" ");
        System.out.print(maxArea.max_j - maxArea.max_jlength + 2);
        System.out.print(" ");
        System.out.print(maxArea.max_i + 1);
        System.out.print(" ");
        System.out.print(maxArea.max_j + 1);

        in.close();
    }
    static void program4() throws IOException {


    }
    public static MaxArea maxHist(int R,int C,int height,int row[]){
        //create a stack for indexes of histogram array
        Stack<Integer> result = new Stack<Integer>();
        MaxArea maxarea = new MaxArea(0, 0 , 0,0,0);

        //stack top
        int top_val;
        int length = 0;

        int area = 0;

        int i = 0;
        while (i < C){
            if (result.empty() || row[result.peek()] <= row[i]){
                result.push(i++);
            }
            else {
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;
                length = i;

                if (!result.empty()){
                    length = i - result.peek() - 1;
                    area = top_val * length;
                }
                //max_area = Math.max(area, max_area);
                if (maxarea.max_area < area){
                    maxarea.max_area = area;
                    maxarea.max_j = i - 1;
                    maxarea.max_ilength = top_val;
                    maxarea.max_jlength = length;
                }
            }
        }


        // pop the remaining bars
        while (!result.empty())
        {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * i;
            length = i;
            if (!result.empty()){
                length = i - result.peek() - 1;
                area = top_val * length;
            }

            if (maxarea.max_area < area){
                maxarea.max_area = area;
                maxarea.max_j = i - 1;
                maxarea.max_ilength = top_val;
                maxarea.max_jlength = length;
            }
        }
        return maxarea;
    }
    public static MaxArea maxBlock(int R, int C, int height, int row[]) {
        int area = 0;
        MaxArea maxarea = new MaxArea(0, 0, 0, 0, 0);
        for (int k = 0; k < C; k++){
            int min_length = row[k];
            for (int x = 0; x < C - k; x++){
                area = (min_length) * (x + 1);
                if (row[k + x] < min_length) {
                    min_length = row[k + x];
                    area = (min_length) * (x + 1);
                }

                if (maxarea.max_area < area){
                    maxarea.max_area = area;
                    maxarea.max_j = x + k;
                    maxarea.max_ilength = area / (x + 1);
                    maxarea.max_jlength = x + 1;
                }
            }
        }
        return maxarea;
    }

    static void testprogram2(){
        int R = 4;
        int C = 5;
        int height = 3;

        int A[][] = {{0, 5, 5, 0, 5},
                {5, 5, 5, 5, 5},
                {5, 3, 5, 1, 1},
                {1, 1, 1, 0, 0},
        };
        int[] s = new int[C];
        int[] s1 = new int[C];

        for (int j = 0; j < C; j++) {
            if (A[0][j] != height) {
                //A[0][j] = 0;
                s[j] = 0;
            }
            else {
                //A[0][j] = 1;
                s[j] = 1;
            }
        }
        MaxArea maxArea = maxBlock(R, C, height, s);


        for (int i = 1; i < R; i++) {
            s1 = new int[C];
            for (int j = 0; j < C; j++){
                // if A[i][j] is 1 then add A[i -1][j]
                if (A[i][j] == height) {
                    //int a = A[i][j];
                    //A[i][j] = 1;
                    //A[i][j] = A[i - 1][j] + 1;
                    s1[j] = s[j] + 1;
                } else{
                    s1[j] = 0;
                }
            }

            MaxArea newmaxarea = maxBlock(R, C, height, s1);
            if (maxArea.max_area < newmaxarea.max_area) {
                maxArea = newmaxarea;
                maxArea.max_i = i;
            }
            s = s1;
        }

        int upperleft_i = maxArea.max_i - maxArea.max_ilength + 1;
        int upperleft_j = maxArea.max_j - maxArea.max_jlength + 1;
        System.out.print("Area of maximum rectangle is ");
        System.out.print(maxArea.max_area + "\n");
        System.out.print("lower right corner of maximum rectangle is ");
        System.out.print("(" + maxArea.max_i + " , ");
        System.out.print(maxArea.max_j + ")" + "\n");

        System.out.print("upper left corner of maximum rectangle is ");
        System.out.print("(" + upperleft_i + " , ");
        System.out.print(upperleft_j + ")");
    }
    static void testprogram3(){
        int R = 4;
        int C = 5;
        int height = 5;

        int A[][] = { {0, 5, 5, 0, 5},
                {5, 5, 5, 5, 5},
                {5, 3, 5, 1, 1},
                {1, 1, 1, 0, 0},
        };
        int[] s = new int[C];
        int[] s1 = new int[C];

        for (int j = 0; j < C; j++) {
            if (A[0][j] != height) {
                s[j] = 0;
                //A[0][i] = 0;
            }else{
                s[j] = 1;
            }
        }
        MaxArea maxArea = maxHist(R, C, height, s);

        for (int i = 1; i < R; i++)
        {
            s1 = new int[C];
            for (int j = 0; j < C; j++) {
                // if A[i][j] is 1 then add A[i -1][j]
                if (A[i][j] == height) {
                    //A[i][j] += A[i - 1][j];
                    s1[j] = 1 + s[j];
                } else {
                    //A[i][j] = 0;
                    s1[j] = 0;
                }
            }
            MaxArea newmaxarea = maxHist(R,C,height, s1);
            if (maxArea.max_area < newmaxarea.max_area){
                maxArea = newmaxarea;
                maxArea.max_i = i;
            }
            s = s1;

        }
        int upperleft_i = maxArea.max_i - maxArea.max_ilength + 1;
        int upperleft_j = maxArea.max_j - maxArea.max_jlength + 1;
        System.out.print("Area of maximum rectangle is ");
        System.out.print(maxArea.max_area + "\n");
        System.out.print("lower right corner of maximum rectangle is ");
        System.out.print("(" + maxArea.max_i + " , ");
        System.out.print(maxArea.max_j + ")" + "\n");

        System.out.print("upper left corner of maximum rectangle is ");
        System.out.print("(" + upperleft_i + " , ");
        System.out.print(upperleft_j + ")");
    }
    static void testprogram4(){
        int R = 4;
        int C = 5;
        int height = 8;

        int A[][] = { {0, 5, 5, 0, 5},
                {5, 5, 5, 5, 5},
                {5, 3, 5, 1, 1},
                {1, 1, 1, 0, 0},
        };

        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int m = 0; m < C; m++){
            max = Math.max(max, A[0][m]);
            min = Math.min(min, A[0][m]);
            System.out.print(min);
            System.out.print(max);
        }
        int[][] s = new int[max - min + 1][C];
        int[][] s1;
        MaxArea maxArea = new MaxArea(0,0,0,0,0);
        for (int k = 0; k < max - min + 1; k++ ) {
            for (int j = 0; j < C; j++) {
                if (A[0][j] < (min + k) || A[0][j] > (min + k + height)) {
                    s[k][j] = 0;
                    //A[0][i] = 0;
                } else {
                    s[k][j] = 1;
                }
            }
            maxArea = maxHist(R, C, height, s[k]);
        }

        for (int k = 0; k < max - min + 1; k++ ) {
            for (int i = 1; i < R; i++) {
                s1 = new int[max - min + 1][C];
                for (int j = 0; j < C; j++) {
                    // if A[i][j] is 1 then add A[i -1][j]
                    if (A[0][j] >= (min + k) && A[0][j] <= (min + k + height)) {
                        //A[i][j] += A[i - 1][j];
                        s1[k][j] = 1 + s[k][j];
                    } else {
                        //A[i][j] = 0;
                        s1[k][j] = 0;
                    }
                }
                MaxArea newmaxarea = maxHist(R, C, height, s1[k]);
                if (maxArea.max_area < newmaxarea.max_area) {
                    maxArea = newmaxarea;
                    maxArea.max_i = i;
                }
                s[k] = s1[k];
            }
        }
        System.out.print("Area of maximum rectangle is ");
        System.out.print(maxArea.max_area + "\n");
        System.out.print(maxArea.max_i - maxArea.max_ilength + 2);
        System.out.print(" ");
        System.out.print(maxArea.max_j - maxArea.max_jlength + 2);
        System.out.print(" ");
        System.out.print(maxArea.max_i + 1);
        System.out.print(" ");
        System.out.print(maxArea.max_j + 1);

    }
}
