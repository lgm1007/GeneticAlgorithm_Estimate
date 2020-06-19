import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
	
	//함수 계산
	public static int fx(int a, int b, int x) {
		return (a * x) + b;
	}
	
	//랜덤 a와 b를 뽑을 함수
	public static int[] init() {
		Random r = new Random();
		int[] arr = new int[4];
		System.out.printf("init 결과: ");
		for(int i = 0; i < 4; i++) {
			// 7자리 비트(1111111)까지 랜덤으로 얻음 (-127 <= ~ <= 127)
            arr[i] = r.nextInt(254 + 1) - 127;
            System.out.printf("%d ", arr[i]);
        }
		System.out.println();
		return arr;
	}
	
	// 선택연산
	 public static int[] selection_a(int[] a, int[] b) {
		 int sum = 0;
		 int[] farr = new int[a.length];
		 for(int i = 0; i < a.length; i++) {
			 farr[i] = fx(a[i], b[i], 1);
			 sum += farr[i];
		 }
		 
		 double[] ratio = new double[a.length];
		 for(int i = 0; i < a.length; i++) {
			 if(i == 0) ratio[i] = (double)farr[i] / (double) sum;
			 else ratio[i] = ratio[i - 1] + (double)farr[i] / (double)sum;
		 }
		 
		 int[] res = new int[a.length];
		 Random r = new Random();
		 for(int i = 0; i < a.length; i++) {
			 double p = r.nextDouble();
			 if(p < ratio[0]) res[i] = a[0];
			 else if(p < ratio[1]) res[i] = a[1];
			 else if(p < ratio[2]) res[i] = a[2];
			 else res[i] = a[3];
		 }
		 
		 return res;
	 }
	 
	 public static int[] selection_b(int[] a, int[] b) {
		 int sum = 0;
		 int[] farr = new int[b.length];
		 for(int i = 0; i < b.length; i++) {
			 farr[i] = fx(a[i], b[i], 1);
			 sum += farr[i];
		 }
		 
		 double[] ratio = new double[b.length];
		 for(int i = 0; i < b.length; i++) {
			 if(i == 0) ratio[i] = (double)farr[i] / (double) sum;
			 else ratio[i] = ratio[i - 1] + (double)farr[i] / (double)sum;
		 }
		 
		 int[] res = new int[b.length];
		 Random r = new Random();
		 for(int i = 0; i < b.length; i++) {
			 double p = r.nextDouble();
			 if(p < ratio[0]) res[i] = b[0];
			 else if(p < ratio[1]) res[i] = b[1];
			 else if(p < ratio[2]) res[i] = b[2];
			 else res[i] = b[3];
		 }
		 
		 return res;
	 }
	 
	 // 2진수로 만든 String 값의 0 Padding
	 public static String int2String(String s) {
	        return String.format("%7s", s).replace(' ', '0');
	 }
	 
	 // 교차연산
	 public static String[] crossover(int[] a) {
		 String[] arr = new String[a.length];
		 for(int i = 0; i < a.length; i += 2) {
			 String bit1 = int2String(Integer.toBinaryString(a[i]));
	         String bit2 = int2String(Integer.toBinaryString(a[i + 1]));
	         
	         arr[i] = bit1.substring(0, 3) + bit2.substring(3, 7);
	         arr[i + 1] = bit2.substring(0, 3) + bit1.substring(3, 7);
		 }
		 return arr;
	 }
	 
	 // 2진수 String형태 값 int형으로 변환하는 함수
	 public static int invert(String s) {
		 Random r = new Random();
		 int a = Integer.parseInt(s, 2);
		 for(int i = 0; i < s.length(); i++) {
			 double p = (double)1 / (double)128;
			 if(r.nextDouble() < p) {
				a = 1 << i ^ a;
			 }
		 }
		 return a;
	 }
	 
	 //돌연변이 연산
	 public static int[] mutation(String[] ss) {
		 int[] arr = new int[ss.length];
		 for(int i = 0; i < ss.length; i++) {
			 arr[i] = invert(ss[i]);
		 }
		 return arr;
	 }

	public static void main(String[] args) {
		int[] a = init();
		int[] b = init();
		System.out.println("-----------------------");
		int[] x ={1, 2, 3, 4, 5, 6, 7};
		int[] y = {10, 12, 13, 15, 17, 20, 23};
		int ra = 0, rb = 0;
		
		//ra, rb값이 변하게 되면 최적화된 a, b값이 나왔다는 뜻으로 반복 중지
		while(ra == 0 && rb == 0) {
			
			int[] sa = selection_a(a, b);
			String[] ca = crossover(sa);
			int[] ma = mutation(ca);
			
			int[] sb = selection_b(a, b);
			String[] cb = crossover(sb);
			int[] mb = mutation(cb);
			
			int[] estiY = new int[y.length];
			double MSE = 0;
			
			for(int i = 0; i < ma.length; i++) {
				for(int j = 0; j < x.length; j++) {
					estiY[j] = fx(ma[i], mb[i], x[j]);
					// Mean Sequence Error : (원래 y값 - 추정한 y값)^2 의 합
					MSE += MSE + (((double)y[j] - (double)estiY[j]) * ((double)y[j] - (double)estiY[j]));
				}
				if(MSE >= 0 && MSE <= 100) {
					ra = ma[i];
					rb = mb[i];
					break;
				}
			}
		}
		
		System.out.printf("최종 a, b의 값  a = %d, b = %d", ra, rb);
		
	}

}
