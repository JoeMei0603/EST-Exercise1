package zest;

import java.util.HashMap;

class Frac2Dec {

    private static final int MAX_LENGTH = 140;
    public static String fractionToDecimal(int numerator, int denominator) {

        if (denominator == 0) return null;
        if (numerator == 0) return "0";

        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        // length of the answer string cannot be >=104
        if (res.length() >= MAX_LENGTH) {
            throw new IllegalArgumentException("Length of the answer string exceeds the maximum.");
        }
        return res.toString();
    }
}
