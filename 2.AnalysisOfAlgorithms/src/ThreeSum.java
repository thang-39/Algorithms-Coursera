public class ThreeSum {
    public static void main(String[] args) {
        System.out.println(count1(new int[]{1,2,3,4,5,6}));
    }
    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
                }
            }
        }
        return count;
    }

    public static int count1(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                for (int k = 1; k < n; k = k*2)
                    if (a[i] + a[j] >= a[k]) sum++;
        return sum;
    }
}
