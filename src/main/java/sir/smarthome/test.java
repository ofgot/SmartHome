package sir.smarthome;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        int[] nums = {1,4,3,8,13,7};
        int target = 10;

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int point = target - nums[i];
            System.out.println("Point: " + point);

            if (map.containsKey(point)){
                System.out.println("___________________");
                System.out.println("Result: " + map.get(point) + " " + nums[i]);
                System.out.println("___________________");
            }
            System.out.println("Put to map: " + nums[i] + " " + i);
            map.put(nums[i], i);
        }
    }
}
