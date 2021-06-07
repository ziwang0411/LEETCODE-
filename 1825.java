class MKAverage {
    int m;
    int k;
    int size = 0;
    int sum = 0;
    static class Element {
        int id;
        int val;
        
        Element(int id, int val) {
            this.id=id;
            this.val = val;
        }
    }
    Comparator<Element> cmp = new Comparator<>() {
        public int compare(Element a, Element b) {
            if (a.val==b.val) {
                return a.id-b.id;
            }
            return a.val-b.val;
        }
    };
    TreeSet<Element> window = new TreeSet<>(cmp);
    TreeSet<Element> topK = new TreeSet<>(cmp);
    TreeSet<Element> bottomK = new TreeSet<>(cmp);
    Queue<Element> queue = new LinkedList<>();
    
    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;       
    }
    
    public void addElement(int num) {
        Element element = new Element(size++, num);
        queue.add(element);
        window.add(element);
        sum+=num;
        
        if (size==m) {
            while (window.size()>m-2*k) {
                balance();
            }
        } else if (size>m) {
            if (window.last().val>topK.first().val) {
                move(window, topK);
            } else {
                move(window, bottomK);
            }
            Element toRemove = queue.poll();
            topK.remove(toRemove);
            bottomK.remove(toRemove);
            if (window.remove(toRemove)) {
                sum-=toRemove.val;
            }
            balance();
        }
    }
    
    private void balance() {
        if (topK.size()<k) {
            move(window, topK);
        } else if (bottomK.size() <k) {
            move(window, bottomK);
        }
        
        if (topK.size()>k) {
            move(topK, window);
        } else if (bottomK.size()>k) {
            move(bottomK, window);
        }
    }
    
    private void move(TreeSet<Element> from, TreeSet<Element> to) {
        Element element = (from == topK || to ==bottomK) ? from.pollFirst() : from.pollLast();
        to.add(element);
        sum += (to ==window? element.val : -element.val);
    }
    
    public int calculateMKAverage() {
        return size < m ? -1 : sum / (m - 2 * k);
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */