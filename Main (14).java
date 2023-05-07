import java.util.*;
class Main {
 
    public static void
    DFS(HashMap<Integer, ArrayList<Integer> > adj, int node,
        boolean visited[])
    {
        if (visited[node])
            return;
 
        visited[node] = true;
 
        for (int x : adj.get(node)) {
 
            if (visited[x] == false)
                DFS(adj, x, visited);
        }
    }
 
    public static int
    makeConnectedUtil(int n, int connections[][], int m)
    {
        boolean visited[] = new boolean[n];
        HashMap<Integer, ArrayList<Integer> > adj= new HashMap<>();
 
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<Integer>());
        }
 
        int edges = 0;
        for (int i = 0; i < m; ++i) {
            ArrayList<Integer> l1= adj.get(connections[i][0]);
            ArrayList<Integer> l2= adj.get(connections[i][0]);
            l1.add(connections[i][1]);
            l2.add(connections[i][0]);
            edges=edges+1;
        }
        int components = 0;
 
        for (int i = 0; i < n; ++i) {
            if (visited[i] == false) {
                components += 1;
                DFS(adj, i, visited);
            }
        }
 
        if (edges < n - 1)
            return -1;
        int redundant
            = edges - ((n - 1) - (components - 1));
        if (redundant >= (components - 1))
            return components - 1;
 
        return -1;
    }
    public static void
    makeConnected(int n, int connections[][], int m)
    {
        int minOps = makeConnectedUtil(n, connections, m);
        System.out.println(minOps);
    }
 
    
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int connections[][]= new int[n-1][2];
        for(int i=0;i<n-1;i++)
        {
            for(int j=0;j<2;j++)
            {
                connections[i][j]=sc.nextInt();
            }
        }
        int m = connections.length;
        makeConnected(n, connections,m);
    }
}