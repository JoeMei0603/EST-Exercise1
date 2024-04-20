package zest;

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {

        // Pre-condition
        if (a > Integer.MAX_VALUE || a < Integer.MIN_VALUE ) {
            throw new IllegalArgumentException("Input `a` is out of range");
        }
        if (b > Integer.MAX_VALUE || b < Integer.MIN_VALUE ) {
            throw new IllegalArgumentException("Input `b` is out of range");
        }
        int a_initial=a;
        int b_initial=b;

        while (b != 0) {
            int carry = (a & b) << 1;  // Carry now contains common set bits of a and b
            a = a ^ b;  // Sum of bits of a and b where at least one of the bits is not set
            b = carry;  // Carry is shifted by one so that adding it to a gives the required sum
        }

        // Post-condition
        assert a == (a_initial+b_initial) : "Calculated sum is incorrect";

        return a;
    }
}
