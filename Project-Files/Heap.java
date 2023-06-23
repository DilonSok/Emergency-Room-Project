import java.util.*;

public class Heap<T> {
    private final ArrayList<T> list = new ArrayList<T>();
    private final Comparator<T> comparator;

    public Heap(Comparator<T> compare){
        comparator = compare;
    }

    private int parent(int index){
        return (index-1) / 2; 
    }

    private int left(int index){
        return (2*index + 1); 
    }
    private int right(int index){
        return (2*index + 2); 
    }
    
    private void swap(int first, int second){
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    
    public void add(T element){
        list.add(element);

        for(int x=list.size()-1; x>0; x=parent(x)){
            if(comparator.compare(list.get(x), list.get(parent(x))) < 0){ //if current element is less than parent, swap current element and parent
                swap(x, parent(x));
            }
            else{
                break; //end loop if added element is in correct position
            }
        }
    }

    public ArrayList<T> returnList(){
        return list;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public T remove(){

        if(list.isEmpty()){
            return null;
        }
            T ret = list.get(0); //return var will return root
            list.set(0,list.get(list.size()-1));    //set root to last element
            list.remove(list.size()-1); //take out swapped element

            for(int x = 0; left(x) < list.size();){ //find correct position for root
                    //must compare children of curre element and swap with the largest of the two (if they exist)
                int least = left(x);
                if(right(x) < list.size() && comparator.compare(list.get(left(x)), list.get(right(x))) > 0){
                    least = right(x);   //if there is a right child and left is bigger, have the least one be right then
                }
                if(comparator.compare(list.get(x), list.get(least)) > 0){//if curr element is bigger than the least, then swap
                    swap(x,least);
                    x=least; //set x to be least in order to ensure it is sorted correctly on the next iteration
                }
                else{
                    break;//end loop if heap becomes fully sorted
                }
            }

            return ret;

    }
    
}
