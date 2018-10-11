
/*
Created by: GREG WOO
Program: Experimenting with graphs
*/

public class Graph {

	boolean[][] adjacency;
	int nbNodes;

	public Graph (int nb){
		this.nbNodes = nb;
		this.adjacency = new boolean [nb][nb];
		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				this.adjacency[i][j] = false;
			}
		}
	}

	public void addEdge (int i, int j){

		adjacency[i][j] = true;
		adjacency[j][i] = true;

	}

	public void removeEdge (int i, int j){

		adjacency[i][j] = false;
		adjacency[j][i] = false;

	}

	public int nbEdges(){

		int nb = this.nbNodes;
		int counter = 0;

		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				if (this.adjacency[i][j]){

					if(i == j) {

						counter ++;
						counter ++;

					} else {

						counter++;

					}
				}
			}
		}

		if(counter % 2 != 0 ) {

			return (counter + 1) / 2;

		} else  {

			return counter / 2;
		}
	}


	public int linksInCommon(int node1, int node2){

		int nb = this.nbNodes;
		int counter = 0;

		for (int j = 0; j < nb; j++){
			if (this.adjacency[node1][j] && this.adjacency[node2][j]){
				counter++;

			}
		}

		return counter;
	}

	public boolean cycle(int start){

		boolean[] visited = new boolean[nbNodes];
		for (int i = 0; i < nbNodes; i++) {
			visited[i] = false;
		}
		return cycleHelper(visited, start, start);
	}

	public boolean cycleHelper(boolean[] visited, int start, int old) {
		for (int i = 0; i < nbNodes; i++) {
			if (i != start) {
				if (old == start) {
					if (adjacency[start][i]) {

						boolean[] temp = new boolean[nbNodes];

						for (int j = 0; j < nbNodes; j++) {

							temp[j] = visited[j];
						}
						temp[i] = true;

						if (cycleHelper(temp, start, i)) {

							return true;
						}
					}
				} else {
					if (i != old) {
						if (adjacency[old][i]) {
							if (!visited[i]) {
								if (adjacency[start][i]) {

									return true;
								} else {

									boolean[] temp = new boolean[nbNodes];

									for (int j = 0; j < nbNodes; j++) {

										temp[j] = visited[j];
									}
									temp[i] = true;
									if (cycleHelper(temp, start, i)) {

										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	// Helper method
	public int nbLinks(int start) {

		int nb = this.nbNodes;
		int counter = 0;

		for (int i = 0; i < nb; i++){
			if (this.adjacency[start][i] && i != start){
				counter++;
			}
		}

		return counter;
	}

	public int shortestPath(int start, int end){

		if(start == end) {

			return 0;
		}

		boolean[] visited = new boolean[this.nbNodes];

		for(boolean b : visited) {

			b = false;
		}

		return pathFinder(visited, start,  start, end);
	}

	public int pathFinder(boolean[] visited, int old, int start, int end) {

		// Extreme case where the two nodes are not connected or ...
		if(this.nbLinks(start) == 0 || this.nbLinks(end) == 0){
			return this.nbNodes + 1;
			// ... if they are directly connected
		} else if (this.adjacency[start][end]){

			return 1;
		}

		int nb = this.nbNodes;
		int counter = 1;
		int shortest = nb;

		boolean[] temp = new boolean[this.nbNodes];

		for(boolean b : temp) {

			b = false;
		}

		for (int i = 0; i < nb; i++){

			if (this.adjacency[start][i] && i != start && visited[i] == false){
				visited[start] = true;

				for(int j = 0; j < nb; j++) {

					temp[j] = visited[j];

				}

				counter += this.pathFinder(temp, start, i, end);

				if(counter < shortest) {

					shortest = counter;
				}

			}
			counter = 1;

			if(i == nb - 1 && shortest >= nb) {

				return this.nbNodes + 1;
			}
		}

		return shortest;
	}
}
