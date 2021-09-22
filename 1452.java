class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Set<String> companies = new HashSet<>();
        for (List<String> list : favoriteCompanies) {
            companies.addAll(list);
        }
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (String key : companies) {
            map.put(key, index);
            index++;
        }
        Set<Integer>[] bits = new Set[favoriteCompanies.size()];
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            bits[i] = new HashSet<Integer>();
            for (String name : favoriteCompanies.get(i)) {
                bits[i].add(map.get(name));
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < bits.length; i++) {
            boolean iIsUnique = true;
            for (int j = 0; j < bits.length; j++) {
                if (i == j)
                    continue;
                boolean iIsSubsetOfJ = true;
                for (int element : bits[i]) {
                    if (!bits[j].contains(element)) {
                        iIsSubsetOfJ = false;
                        break;
                    }
                }
                if (iIsSubsetOfJ) {
                    iIsUnique = false;
                    break;
                }
            }
            if (iIsUnique)
                res.add(i);
        }
        return res;
    }
}