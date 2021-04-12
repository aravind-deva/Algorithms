import java.util.Arrays;

public class RaceCar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(racecar(11));
	}

	public static int racecar(int target) {// A state(position) can be reached from forward direction or reverse
											// direction.
		// What it takes for position to reset to forward direction with a speed of +1 ?
		// The goal of resetting is to reuse, existing values
		// Ex: 2 can be reached from 1 -> ARRA , here the pos is 2, speed is +2
		// 2 can be reached from 3 -> AARA , here the pos is 2, speed is -2
		// For the car to reset in forward direction with +1 speed , 1st case has to add
		// RR (double reverse) so
		// minimum steps for 1st case to reset -> ARRARR -> steps 6, speed +1
		// minimum steps for 2nd case to reset -> AARAR-> steps 5, speed +1
		// The second case has less steps..
		// if we use assume we are starting from 2 with speed +1, it becomes a problem
		// with position 0 with +1 speed problem. SO we can reuse values
		// so for calculating say , target =5 , it can start from 2 with a speed +1, so
		// from 2 , AA in 2 steps u will reach 5
		// therefore minimum steps to reach 5 = AARAR + AA = 7

		// target +1
		// minimum target*2 +1 ensures Right bound
		int[] minDistances = new int[target * 2 + 1 + 1];
		// This assumes that at every
		int[] minDistanceForSpeedPositiveOne = new int[target * 2 + 1 + 1];
		Arrays.fill(minDistances, Integer.MAX_VALUE);
		Arrays.fill(minDistanceForSpeedPositiveOne, Integer.MAX_VALUE);
		int pos = 0;
		int speed = 1;
		minDistanceForSpeedPositiveOne[pos] = 0;
		minDistances[pos] = 0;
		int prev = 0;
		pos += speed;
		speed *= 2;
		while (prev <= target) {
			minDistances[pos] = minDistances[prev] + 1;
			minDistanceForSpeedPositiveOne[pos] = minDistances[pos] + 2;
			prev = pos;
			pos += speed;
			speed *= 2;
		}
		int left = 1;
		int right = left * 2 + 1;
		for (int i = 2; i <= target; i++) {

			if (minDistances[i] != Integer.MAX_VALUE) {
				left = i;
				right = left * 2 + 1;
				continue;
			}
			int mid = (left + right) / 2;
			for (int j = 1; j <= left; j++) {

				int temp = minDistanceForSpeedPositiveOne[j] + minDistances[i - j];
				minDistances[i] = Math.min(minDistances[i], temp);
				minDistanceForSpeedPositiveOne[i] = Math.min(minDistanceForSpeedPositiveOne[i], temp + 2);

			}

			// 1 R will change speed to -1 and direction is reversed
			int temp = minDistances[right] + minDistances[right - i] + 1;
			minDistances[i] = Math.min(minDistances[i], temp);
			minDistanceForSpeedPositiveOne[i] = Math.min(minDistanceForSpeedPositiveOne[i], temp + 1);

		}
		return minDistances[target];
	}
}
