class Solution {
    Map<String, Map<String, Double>> connections;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        connections = new HashMap<>();
        for (int i = 0; i<values.length; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];
            if (!connections.containsKey(a)) {
                Map<String, Double> children = new HashMap<>();
                children.put(a, 1.0);
                connections.put(a, children);
            }
            if (!connections.containsKey(b)) {
                Map<String, Double> children = new HashMap<>();
                children.put(b, 1.0);
                connections.put(b, children);
            }
            connections.get(a).put(b, value);
            connections.get(b).put(a, 1.0/value);
        }
        double[] result = new double[queries.size()];
        int index = 0;
        for (List<String> query: queries) {
            String start = query.get(0);
            String end = query.get(1);
            if (!connections.containsKey(start)|| !connections.containsKey(end)) result[index++] = -1.0;
            else {
                result[index++] = dfs(new HashSet<String>(), start, end);
            }
        }
        return result;
    }
    
    private double dfs(Set<String> visited, String start, String end) {
        if (start.equals(end)) return 1.0;
        visited.add(start);
        double res = -1.0;
        Map<String, Double> neighbors = connections.get(start);
        for (String neighbor: neighbors.keySet()) {
            if (!visited.contains(neighbor)) {
                double candidate = neighbors.get(neighbor) * dfs(visited, neighbor, end);
                if (candidate>0) res = candidate;
            }
        }
        // visited.remove(start);
        return res;
    }
}