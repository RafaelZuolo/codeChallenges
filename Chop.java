import java.util.*;

public class Chop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		while (testCases > 0) {
			testCases--;
			int k = sc.nextInt() + 8;
			int n = sc.nextInt();
			int[] sticks = new int[n];
			int[][] mem = new int[k+1][n];
			for (int i = 0; i < n; i++) {
				sticks[n-1-i] = sc.nextInt();
			}
			for (int i = 1; i < k+1; i++) {
				Arrays.fill(mem[i], -1); // we will treat -1 as infinity, since working with MAX_INT can lead to overflows.
			}
			// ---------- finished the initialization -----------------------
			//System.out.println(Arrays.toString(sticks));
			//System.out.println(Arrays.deepToString(mem));
			for (int i = 1; i < k+1; i++) {
				for (int j = 0; j < n; j++) {
					if (j+1 < 3*i) continue; // keep the -1 value
					if (mem[i][j-1] == -1) {
						assert mem[i-1][j-2] != -1;
						mem[i][j] = mem[i-1][j-2] + (sticks[j] - sticks[j-1])*(sticks[j] - sticks[j-1]);
					} else {
						mem[i][j] = Math.min(mem[i-1][j-2] + (sticks[j-1] - sticks[j])*(sticks[j-1] - sticks[j]), 
										 mem[i][j-1]);
					}
				}
			}
			System.out.println(mem[k][n-1]);
			// we print an optimal solution
			if (args.length > 0) {
				for (int i = k; i > 0; ) {
					for (int j = n-1; j>0; ) {
						if (mem[i][j] < mem[i][j-1] || mem[i][j-1] == -1) {
							System.out.println("[" + sticks[j-1]+", "+sticks[j]+"]");
							j -= 2;
							i -= 1;
						} else {
							j -= 1;
						}
					}
				}
			}
		}
		sc.close();
	}
}
