package com.thealgorithms.sda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TP3 {
	private static InputReader in;
    private static PrintWriter out;
	private static Node[] pos;
	private static long[] dist;
	// private static long[] maxPred;
	private static long[][] allDistS;
	private static long[][] distSuper;
	private static long[][][] allDistSuper;
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	private static long[][] length;
	private static long[][] size;
	private static boolean[] visited;
	private static Kurcaci[] arrKurcaci;

	private static int N;

	public static void main(String[] args) {
		InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

		N = in.nextInt(); // banyak pos
		int M = in.nextInt(); // banyak terowongan

		// initiate panjang container
		pos = new Node[N];
		visited = new boolean[N];

		length = new long[N][N];
		size = new long[N][N];
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
		}
		
		dist = new long[N];
		distSuper = new long[N][2];
		// maxPred = new long[N];
		allDistS = new long[N][N];
		allDistSuper = new long[N][N][2];

		// initiate each pos
		for (int i = 1; i <= N; i++) {
			pos[i - 1] = new Node(i);
		}


		for (int i = 0; i < M; i++) {
			int A = in.nextInt();
			int B = in.nextInt();
			int L = in.nextInt();
			int S = in.nextInt();

			// fill adj
			adj.get(A - 1).add(B);
			adj.get(B - 1).add(A);

			// fill length & size
			length[A-1][B-1] = length[B-1][A-1] = L;
			size[A-1][B-1] = size[B-1][A-1] = S;
		}

		int P = in.nextInt(); // banyak kurcaci

		arrKurcaci = new Kurcaci[P]; // inisiasi panjang array kurcaci

		for (int i = 0; i < P; i++) {
			int R = in.nextInt();

			Kurcaci kurcaci = new Kurcaci(R);
			arrKurcaci[i] = kurcaci;
		}

		// call dijkstra for each pos
		for (int i = 0; i < N; i++) {
			dijkstraMax(i+1);
            System.arraycopy(dist, 0, allDistS[i], 0, N);
		}

		for (int i = 0; i < N; i++) {
			dijkstraSuper(i+1);
			for (int j = 0; j < N; j++) {
				allDistSuper[i][j][0] = distSuper[j][0];
				allDistSuper[i][j][1] = distSuper[j][1];
			}
		}

		int Q = in.nextInt();

		for (int i = 0; i < Q; i++) {
			String query = in.next();
            switch (query) {
                case "KABUR" -> {
                    int F = in.nextInt();
                    int E = in.nextInt();
                    handleKabur(F, E);
                }
                case "SIMULASI" -> {
                    int K = in.nextInt();
                    int[] arrK = new int[K];
                    for (int j = 0; j < K; j++) {
                        int V = in.nextInt();
                        arrK[j] = V;
                    }
                    handleSimulasi(arrK);
                }
                case "SUPER" -> {
                    int V1 = in.nextInt();
                    int V2 = in.nextInt();
                    int V3 = in.nextInt();
                    handleSuper(V1, V2, V3);
                }
                default -> {
                }
            }
		}
		
		out.close();
	}

	static void handleKabur(int F, int E) {
		out.println(allDistS[F-1][E-1]);
	}	

	static void handleSimulasi(int[] arrK) {
		long minKurcaci;
		long longestTime = Integer.MIN_VALUE;

		for (Kurcaci k : arrKurcaci) {
			minKurcaci = Integer.MAX_VALUE;
			int idPos = k.getPos();
			for (int p : arrK) {
                // p for pos
				if (minKurcaci > allDistSuper[idPos - 1][p - 1][0]) {
					minKurcaci = allDistSuper[idPos - 1][p - 1][0];
				}

			}
			
			if (longestTime < minKurcaci) {
			longestTime = minKurcaci;
			}
		}

		out.println(longestTime);
	}

	static void handleSuper(int V1, int V2, int V3) {
		long a = allDistSuper[V1 - 1][V2 - 1][0];
		long b = allDistSuper[V1 - 1][V2 - 1][1];
		long c = allDistSuper[V2 - 1][V3 - 1][0];
		long d = allDistSuper[V2 - 1][V3 - 1][1];

		if (a + d < b + c) {
			out.println(a + d);
		} else {
			out.println(b + c);
		}

	}


	static void dijkstraMax(int S) {
        // inisialisasi
        for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MIN_VALUE;
        }
        dist[S - 1] = 0;

        // inisialisasi heap
        MaxBinaryHeap<Node> heap = new MaxBinaryHeap<>();
        for (int i : adj.get(S - 1)) {
            heap.insert(new Node(i, size[S - 1][i - 1]));
        }

		out.println();
		boolean first = true;
        while (!heap.data.isEmpty()) {
			Node u;
			if (first) {
				u = new Node(S, 0);
				first = false;
			} else {
				u = heap.remove();
			}
            visited[u.getIndex() - 1] = true;

			// iterate every adj of pos u
            for (int i = 0; i < adj.get(u.getIndex() - 1).size(); i++) {
                int v = adj.get(u.getIndex() - 1).get(i);
                if (!visited[v - 1]) {
					if (dist[u.getIndex() - 1] != 0 && dist[u.getIndex() - 1] < size[u.getIndex() - 1][v - 1]) {
						dist[v - 1] = dist[u.getIndex() - 1];
						heap.insert(new Node(v, dist[v - 1]));
					} else if (dist[v - 1] < size[u.getIndex() - 1][v - 1]) {
                        dist[v - 1] = size[u.getIndex() - 1][v - 1];
                        heap.insert(new Node(v, dist[v - 1]));
                    }

                }
            }
        }

        // reset visited
        visited = new boolean[visited.length];
    }

	// static void dijkstraMin(int S) {
    //     // inisialisasi
    //     for (int i = 0; i < dist.length; i++) {
    //         dist[i] = Integer.MAX_VALUE;
	// 		maxPred[i] = Integer.MIN_VALUE;
    //     }
    //     dist[S - 1] = 0;
	// 	maxPred[S - 1] = 0;

    //     // inisialisasi heap
    //     MinBinaryHeap<Node> heap = new MinBinaryHeap<>();
    //     heap.insert(new Node(S, 0));
    //     for (int i : adj.get(S - 1)) {
    //         heap.insert(new Node(i, length[S - 1][i - 1]));
    //     }

    //     while (!heap.data.isEmpty()) {
    //         Node u = heap.remove();
    //         visited[u.getIndex() - 1] = true;

    //         for (int i = 0; i < adj.get(u.getIndex() - 1).size(); i++) {
    //             int v = adj.get(u.getIndex() - 1).get(i);
    //             if (!visited[v - 1]) {

    //                 if (dist[v - 1] > dist[u.getIndex() - 1] + length[u.getIndex() - 1][v - 1]) {
    //                     dist[v - 1] = dist[u.getIndex() - 1] + length[u.getIndex() - 1][v - 1];
    //                     heap.insert(new Node(v, dist[v - 1]));
						
	// 					if (maxPred[v - 1] < length[u.getIndex() - 1][v - 1]) {
	// 						maxPred[v - 1] = length[u.getIndex() - 1][v - 1];
	// 					}
    //                 }

    //             }
    //         }
    //     }

    //     // reset visited
    //     visited = new boolean[visited.length];
    // }

	static void dijkstraSuper(int S) {
        // inisialisasi
        for (int i = 0; i < dist.length; i++) {
            distSuper[i][0] = Integer.MAX_VALUE;
			distSuper[i][1] = Integer.MAX_VALUE;
        }
		distSuper[S - 1][0] = 0;
		distSuper[S - 1][1] = 0;

        // inisialisasi heap
        MinBinaryHeap<Node> heap = new MinBinaryHeap<>();
        heap.insert(new Node(S, 0, false));
        for (int i : adj.get(S - 1)) {
            heap.insert(new Node(i, length[S - 1][i - 1], false));
        }

        while (!heap.data.isEmpty()) {
            Node u = heap.remove();

            for (int i = 0; i < adj.get(u.getIndex() - 1).size(); i++) {
                int v = adj.get(u.getIndex() - 1).get(i);

                if (!visited[v - 1]) {

					if (u.getSkip() == false) {
						
						// state 0
						if (distSuper[v - 1][0] > distSuper[u.getIndex() - 1][0] + length[u.getIndex() - 1][v - 1]) {
							distSuper[v - 1][0] = distSuper[u.getIndex() - 1][0] + length[u.getIndex() - 1][v - 1];
							heap.insert(new Node(v, distSuper[v - 1][0], false));
						} 
						
						// state 1
						if (distSuper[v - 1][1] > distSuper[u.getIndex() - 1][0]) {
							distSuper[v - 1][1] = distSuper[u.getIndex() - 1][0];
							heap.insert(new Node(v, distSuper[v - 1][1], true));
						} 
						
					} else {
						if (distSuper[v - 1][1] > distSuper[u.getIndex() - 1][1] + length[u.getIndex() - 1][v - 1]) {
							distSuper[v - 1][1] = distSuper[u.getIndex() - 1][1] + length[u.getIndex() - 1][v - 1];
							heap.insert(new Node(v, distSuper[v - 1][1], true));
						}
					}

                }
            }
        }

        // reset visited
        visited = new boolean[visited.length];
    }

	// taken from https://codeforces.com/submissions/Petr
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

class Kurcaci {
	private int idPos;

	public Kurcaci(int idPos) {
		this.idPos = idPos;
	}

	public int getPos() {
		return this.idPos;
	}

	public void setPos(int idPos) {
		this.idPos = idPos;
	}
}

class Node implements Comparable<Node> {
	private int index;
	private long length;
	private boolean skip;

	public Node(int index) {
		this.index = index;
	}

	public Node(int index, long length) {
		this.index = index;
		this.length = length;
	}

	public Node(int index, long length, boolean skip) {
		this.index = index;
		this.length = length;
		this.skip = skip;
	}

	public boolean getSkip() {
		return this.skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public int getIndex() {
		return this.index;
	}
	
	public long getLength() {
		return this.length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Override
	public int compareTo(Node o) {
		return Long.compare(this.length, o.length);
	}
}

// max heap
class MaxBinaryHeap<T extends Comparable<T>> {
	ArrayList<T> data;

	public MaxBinaryHeap() {
		data = new ArrayList<>();
	}

	public MaxBinaryHeap(ArrayList<T> arr) {
		data = arr;
		heapify();
	}

	public T peek() {
		if (data.isEmpty())
			return null;
		return data.get(0);
	}

	public void insert(T value) {
		data.add(value);
		percolateUp(data.size() - 1);
	}

	public T remove() {
		T removedObject = peek();

		if (data.size() == 1)
			data.clear();
		else {
			data.set(0, data.get(data.size() - 1));
			data.remove(data.size() - 1);
			percolateDown(0);
		}

		return removedObject;
	}

	private void percolateDown(int idx) {
		T node = data.get(idx);
		int heapSize = data.size();

		while (true) {
			int leftIdx = getLeftChildIdx(idx);
			if (leftIdx >= heapSize) {
				data.set(idx, node);
				break;
			} else {
				int minChildIdx = leftIdx;
				int rightIdx = getRightChildIdx(idx);
				if (rightIdx < heapSize && data.get(rightIdx).compareTo(data.get(leftIdx)) > 0)
					minChildIdx = rightIdx;

				if (node.compareTo(data.get(minChildIdx)) < 0) {
					data.set(idx, data.get(minChildIdx));
					idx = minChildIdx;
				} else {
					data.set(idx, node);
					break;
				}
			}
		}
	}

	private void percolateUp(int idx) {
		T node = data.get(idx);
		int parentIdx = getParentIdx(idx);
		while (idx > 0 && node.compareTo(data.get(parentIdx)) > 0) {
			data.set(idx, data.get(parentIdx));
			idx = parentIdx;
			parentIdx = getParentIdx(idx);
		}

		data.set(idx, node);
	}

	private int getParentIdx(int i) {
		return (i - 1) / 2;
	}

	private int getLeftChildIdx(int i) {
		return 2 * i + 1;
	}

	private int getRightChildIdx(int i) {
		return 2 * i + 2;
	}

	private void heapify() {
		for (int i = data.size() / 2 - 1; i >= 0; i--)
			percolateDown(i);
	}

	public void sort() {
		int n = data.size();
		while (n > 1) {
			data.set(n - 1, remove(n));
			n--;
		}
	}

	public T remove(int n) {
		T removedObject = peek();

		if (n > 1) {
			data.set(0, data.get(n - 1));
			percolateDown(0, n - 1);
		}

		return removedObject;
	}

	private void percolateDown(int idx, int n) {
		T node = data.get(idx);
		int heapSize = n;

		while (true) {
			int leftIdx = getLeftChildIdx(idx);
			if (leftIdx >= heapSize) {
				data.set(idx, node);
				break;
			} else {
				int minChildIdx = leftIdx;
				int rightIdx = getRightChildIdx(idx);
				if (rightIdx < heapSize && data.get(rightIdx).compareTo(data.get(leftIdx)) > 0)
					minChildIdx = rightIdx;

				if (node.compareTo(data.get(minChildIdx)) < 0) {
					data.set(idx, data.get(minChildIdx));
					idx = minChildIdx;
				} else {
					data.set(idx, node);
					break;
				}
			}
		}
	}

}

class MinBinaryHeap<T extends Comparable<T>> {
    ArrayList<T> data;

    public MinBinaryHeap() {
        data = new ArrayList<>();
    }

    public MinBinaryHeap(ArrayList<T> arr) {
        data = arr;
        heapify();
    }

    public T peek() {
        if (data.isEmpty())
            return null;
        return data.get(0);
    }

    public void insert(T value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    public T remove() {
        T removedObject = peek();

        if (data.size() == 1)
            data.clear();
        else {
            data.set(0, data.get(data.size() - 1));
            data.remove(data.size() - 1);
            percolateDown(0);
        }

        return removedObject;
    }

    private void percolateDown(int idx) {
        T node = data.get(idx);
        int heapSize = data.size();

        while (true) {
            int leftIdx = getLeftChildIdx(idx);
            if (leftIdx >= heapSize) {
                data.set(idx, node);
                break;
            } else {
                int minChildIdx = leftIdx;
                int rightIdx = getRightChildIdx(idx);
                if (rightIdx < heapSize && data.get(rightIdx).compareTo(data.get(leftIdx)) < 0)
                    minChildIdx = rightIdx;

                if (node.compareTo(data.get(minChildIdx)) > 0) {
                    data.set(idx, data.get(minChildIdx));
                    idx = minChildIdx;
                } else {
                    data.set(idx, node);
                    break;
                }
            }
        }
    }

    private void percolateUp(int idx) {
        T node = data.get(idx);
        int parentIdx = getParentIdx(idx);
        while (idx > 0 && node.compareTo(data.get(parentIdx)) < 0) {
            data.set(idx, data.get(parentIdx));
            idx = parentIdx;
            parentIdx = getParentIdx(idx);
        }

        data.set(idx, node);
    }

    private int getParentIdx(int i) {
        return (i - 1) / 2;
    }

    private int getLeftChildIdx(int i) {
        return 2 * i + 1;
    }

    private int getRightChildIdx(int i) {
        return 2 * i + 2;
    }

    private void heapify() {
        for (int i = data.size() / 2 - 1; i >= 0; i--)
            percolateDown(i);
    }

    public void sort() {
        int n = data.size();
        while (n > 1) {
            data.set(n - 1, remove(n));
            n--;
        }
    }

    public T remove(int n) {
        T removedObject = peek();

        if (n > 1) {
            data.set(0, data.get(n - 1));
            percolateDown(0, n - 1);
        }

        return removedObject;
    }

    private void percolateDown(int idx, int n) {
        T node = data.get(idx);
        int heapSize = n;

        while (true) {
            int leftIdx = getLeftChildIdx(idx);
            if (leftIdx >= heapSize) {
                data.set(idx, node);
                break;
            } else {
                int minChildIdx = leftIdx;
                int rightIdx = getRightChildIdx(idx);
                if (rightIdx < heapSize && data.get(rightIdx).compareTo(data.get(leftIdx)) < 0)
                    minChildIdx = rightIdx;

                if (node.compareTo(data.get(minChildIdx)) > 0) {
                    data.set(idx, data.get(minChildIdx));
                    idx = minChildIdx;
                } else {
                    data.set(idx, node);
                    break;
                }
            }
        }
    }

}