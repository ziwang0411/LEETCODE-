class CustomStack {
    int[] offset;
    int maxSize;
    Stack<Integer> stack;
    public CustomStack(int maxSize) {
        stack = new Stack<>();
        this.maxSize = maxSize;
        offset = new int[maxSize];
    }
    
    public void push(int x) {
        if (stack.size()<maxSize) stack.push(x);
    }
    
    public int pop() {
        if (stack.isEmpty()) return -1;
        int currentSize = stack.size();
        int increment = 0;
        if (offset[currentSize-1]!=0) {
            increment+=offset[currentSize-1];
            if (currentSize-1>0) {
                offset[currentSize-2] +=offset[currentSize-1]; 
            }
            offset[currentSize-1] = 0;
        }
        int res = stack.pop()+increment;
        return res;
    }
    
    public void increment(int k, int val) {
        k = Math.min(k, stack.size());
        if (k>0) offset[k-1]+=val; 
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */