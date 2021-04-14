
public class SudokuSolver {

	    public void solveSudoku(char[][] board) {
	        
	     int filledRows[][]=new int[9][10];
	     int filledCols[][]= new int[9][10];
	     int filledBoxes[][]=new int[9][10];
	     
	     for(int i=0;i<9;i++){
	         for(int j=0;j<9;j++){
	             if(board[i][j] !='.'){
	                 int num=board[i][j] -'0';
	                 filledRows[i][num]=1;
	                 filledCols[j][num]=1;
	                 int boxNum=(i/3) * 3  + j/3;
	                 filledBoxes[boxNum][num]=1;
	             }
	         }
	     } 
	     
	     dfs(0,0,board,filledRows,filledCols,filledBoxes);
	    }
	    
	   boolean dfs(int x,int y,char[][]board,int filledRows[][],int filledCols[][],int filledBoxes[][]){
	       
	        if(y>=9) {
	            x=x+1;y=0;
	        }
	        if(x>=9) return true;
	        boolean puzzle=false;
	        int boxNum=(x/3) * 3  + y/3;
	        if(board[x][y] =='.'){
	                  for(int i=1;i<=9;i++){
	                      int sum=filledRows[x][i]+filledCols[y][i]+filledBoxes[boxNum][i];
	                      if(sum==0) {
	                          filledRows[x][i]=i;
	                          filledCols[y][i]=i;
	                          filledBoxes[boxNum][i]=i;
	                          board[x][y]= (char) (i+'0') ;
	                          puzzle=dfs(x,y+1,board,filledRows,filledCols,filledBoxes);
	                          if(puzzle) break;
	                          filledRows[x][i]=0;
	                          filledCols[y][i]=0;
	                          filledBoxes[boxNum][i]=0;
	                          board[x][y]='.';
	                      }
	                      else continue;
	                    }
	        }
	        else {
	            puzzle=dfs(x,y+1,board,filledRows,filledCols,filledBoxes);
	        }
	      return puzzle;
	       
	    }
	
}
