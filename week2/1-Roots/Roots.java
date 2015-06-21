public class Roots {

    public static double squareRoot(int number) {
        if (number < 0) {
            return -1;
        }
        
        if (number==0 || number==1) {
            return number;
        }
        
        double precision = 0.00001;
        double start = 0;
        double end = number;
        
        while (end-start > precision) {
            double mid = (start+end)/2;
            double midSqrt=mid*mid;
            if (midSqrt==number) {
                return mid;
            }else if (midSqrt<number) {
                start = mid;
            }else{
                end=mid;
            }
        }
        
        return (start+end)/2;
    }

    public static void main(String[] args) {
        System.out.println(squareRoot(5));

    }

}
