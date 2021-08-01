import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        if (m == 1){ 
            for(int i = 1; i <= n; i++)
                System.out.print(i);
            System.out.print("\n");
        }
        else{
            System.out.print(1);
            myArray array = new myArray(n);
            while (true){
                int current = array.nextNum(m);
                if (current == -1)
                    break;
                System.out.print(current);
            }
            System.out.print("\n");
        }
    }
    
    static class myArray{

        private final List<Integer> nums;
        private ListIterator<Integer> iterator;
        
        public myArray(int n){
            this.nums = new ArrayList<Integer>();
            for(int i = 0; i < n; i++)
                this.nums.add(i+1);
            this.iterator = this.nums.listIterator();
        }
        public int nextNum(int m){
            for (int i = 0; i < m -1; i++){
                if (iterator.hasNext())
                    iterator.next();
                else {
                    iterator = nums.listIterator();
                    iterator.next();
                }
            }
            if (iterator.hasNext())
                return(nums.get(iterator.nextIndex()));
            else
                return (-1);
        }
    }
}
