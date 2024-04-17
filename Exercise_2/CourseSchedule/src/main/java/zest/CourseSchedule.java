package zest;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) throw new IllegalArgumentException("Cannot visit a negative amount of courses!");
        for (int[] prerequisite : prerequisites) {
            if (prerequisite.length == 0) throw new IllegalArgumentException("Cannot provide empty arrays!");
            if (prerequisite[0] < 0 || prerequisite[0] >= numCourses)
                throw new IllegalArgumentException("Course " + prerequisite[0] + " does not exist!");
            if (prerequisite[1] < 0 || prerequisite[1] >= numCourses)
                throw new IllegalArgumentException("Course " + prerequisite[1] + " does not exist!");
            if (prerequisite[0] == prerequisite[1])
                throw new IllegalArgumentException("Courses cannot be dependent on themselves!");
        }


        // Create a graph from prerequisites
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        if (graph.size() != numCourses) throw new RuntimeException("Length of graph list is wrong!");

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);

            List<Integer> prerequisiteList = graph.get(prerequisite[1]);
            if (prerequisiteList.get(prerequisiteList.size() - 1) < 0 ||
                    prerequisiteList.get(prerequisiteList.size() - 1) >= numCourses)
                throw new RuntimeException("A list with an invalid course number was appended to the graph!");
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(graph, i, visited, onPath)) {
                return false; // Cycle detected
            }

            if (!visited[i]) throw new RuntimeException("A visited node is set as not visited!");
            if (onPath[i]) throw new RuntimeException("A node is still set as onPath after being visited!");
        }
        return true; // No cycle detected
    }

    private static boolean hasCycle(List<List<Integer>> graph, int current, boolean[] visited, boolean[] onPath) {
        if (onPath[current]) return true; // Cycle detected
        if (visited[current]) return false; // Already visited

        visited[current] = true;
        onPath[current] = true;

        for (int neighbor : graph.get(current)) {
            if (hasCycle(graph, neighbor, visited, onPath)) {
                return true;
            }

            if (!visited[current]) throw new RuntimeException("A node currently being visited is set as not visited!");
            if (!onPath[current]) throw new RuntimeException("A node is not set as onPath while being visited!");
        }

        onPath[current] = false; // Backtrack
        return false;
    }
}
