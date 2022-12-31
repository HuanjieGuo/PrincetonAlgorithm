public class QuickSort {
    void quickSort(double[] a, int start, int end){
        if(start>=end) return;

        int l = start;
        int r = end;

        double pivot = a[l];
        while (l<r){
            while (pivot < a[r] && l < r ) r--;
            if(l<r) {
                a[l] = a[r];
                l++;
            }
            while (pivot > a[l] && l<r) l++;
            if(l<r){
                a[r] = a[l];
                r--;
            }
            a[l] = pivot;
            quickSort(a, start,l-1);
            quickSort(a,l+1,end);
        }
    }

    public static void main(String[] args) {
        double[] a = new double[]{1.0,2.0,1.4,1.3,11.2,1.1};
        new QuickSort().quickSort(a,0,a.length-1);
        for(int i=0;i<a.length;i++) System.out.println(a[i]);
    }
}
