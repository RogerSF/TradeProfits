import java.util.LinkedList;
import java.util.List;

public class TradeProfits {
	//new O(n) solution
	public static List<Integer> trade(int[] price) {
		int l = price.length;
		int in = 0, out = 0, profit = 0, min = 0, max = 0;//in&out&profit will store the final return value, min&max will be the current min&max index;

		for (int i = 0; i < l; i++) {
			//checking whether the input price is valid
			if(price[i] <= 0) {
				System.out.println("Price at time " + i + " is invalid, please check again.");
				return new LinkedList<>();
			}
			//after seeing a new high price, update index_max;
			if(price[i] > price[max]) {
				max = i;
				int temp = price[max] - price[min];
				//if the new profit 'temp' is higher, update in and out
				if(temp > profit) {
					profit = temp;
					in = min; //update 'in' as current min index
					out = i;   //update 'out' as the current high index
				}
			}
			//after seeing a new low price, update index_min and index_max as a new round 
			else if (price[i] < price[min]) {
				min = i;
				max = i;
			}
		}	
		List<Integer> res = new LinkedList<>();
		res.add(in);
		res.add(out);
		res.add(profit);
		return res;
	}
	
	//the original O(n^2) solution
	public static List<Integer> trade_Matrix(int[] price) {
		int l = price.length;
		int[][] M = new int[l][l];
		int profit = 0, in = 0, out = 0; 
		for (int i = 0; i < l; i++) {
			for(int j = i; j < l; j++) {
				if(price[j] - price[i] > profit) {
					profit = price[j] - price[i];
					in = i;
					out = j;
				}
			}
		}
		
		List<Integer> res = new LinkedList<>();
		res.add(in);
		res.add(out);
		res.add(profit);
		return res;
		
	}
	
 	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] price = new int[100];
		for (int i = 0; i < 100; i++) {
			price[i] = i + 1 + (int)(Math.random() * 100);
			//System.out.println(price[i]);
		}
		List<Integer> res = trade(price);

		List<Integer> res_M = trade_Matrix(price);
		
		System.out.println("trade method output: ");
		System.out.println(res);
		System.out.println("trade_Matrix method output: ");
		System.out.println(res_M);
		
	}
	
	

}
