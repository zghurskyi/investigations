import java.util.function.Function;
import java.util.stream.IntStream;

public class Simulation {

    private static int binarySearch(int[] nums, int key, int from, int to) {
        int l = from - 1;
        int r = to + 1;
        int ops = 0;
        while (r - l > 1) {
            int m = (l + r) / 2;
            ops++;
            if (nums[m] >= key) {
                r = m;
            } else {
                l = m;
            }
        }
        return ops;
    }

    private static int linearSearch(int[] nums, int key, int from, int to) {
        int ops = 0;
        for (int i = from; i < to; i++) {
            ops++;
            if (nums[i] == key) {
                return ops;
            }
        }
        return ops;
    }

    private static double case1(Function<Integer, Integer> operation) {
        long sum = IntStream.range(0, 10_000)
                .mapToLong(operation::apply)
                .sum();
        return sum / 10_000.0;
    }

    private static double case2(Function<Integer, Integer> operation1,
                                Function<Integer, Integer> operation2,
                                Function<Integer, Integer> operation3) {
        long sum = IntStream.range(0, 10_000)
                .mapToLong(key -> {
                    if (Math.random() <= 0.6) {
                        int good = (int) (4_000 * Math.random());
                        return operation1.apply(good);
                    } else {
                        int bad = (int) (4_000 + 6000 * Math.random());
                        int ops1 = operation2.apply(bad);
                        int ops2 = operation3.apply(bad);
                        return ops1 + ops2;
                    }
                })
                .sum();
        return sum / 10_000.0;
    }

    public static void main(String[] args) {

        int[] nums = IntStream.range(0, 10_000).toArray();

        System.out.println("Binary search");

        double avg1 = case1(key -> binarySearch(nums, key, 0, nums.length - 1));
        System.out.println("Case 1 average complexity: " + avg1);

        double avg2 = case2(
                good -> binarySearch(nums, good, 0, 4_000),
                bad -> binarySearch(nums, bad, 0, 4_000),
                bad -> binarySearch(nums, bad, 4_000, 10_000)
        );
        System.out.println("Case 2 average complexity: " + avg2);

        System.out.println("Linear search");

        double avg3 = case1(key -> linearSearch(nums, key, 0, nums.length - 1));
        System.out.println("Case 1 average complexity: " + avg3);

        double avg4 = case2(
                good -> linearSearch(nums, good, 0, 4_000),
                bad -> linearSearch(nums, bad, 0, 4_000),
                bad -> linearSearch(nums, bad, 4_000, 10_000)
        );
        System.out.println("Case 2 average complexity: " + avg4);
    }
}
