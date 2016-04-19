package model;


/**
 * @authors Julia Sutula and Corentin Rejaud
 *
 *Functional Interface which indicates if there are pieces inbetween a long movement
 */
public interface LongMovement {
	/**
	 * hasPieceInbetween()
	 * Checks if there are pieces between the initial and final location
	 * @param initFile
	 * @param initRank
	 * @param finalFile
	 * @param finalRank
	 * @param board
	 * @return
	 */
	default public boolean hasPiecesInbetween(char initFile, char initRank, char finalFile, char finalRank, Square[][] board) {
		int f,r;
		int inRank = Character.getNumericValue(initRank);
		int finRank = Character.getNumericValue(finalRank);
		// if it's horizontal movement
		if (inRank == finRank && initFile != finalFile) {
			if (initFile < finalFile) //movement to the right
				for (f = initFile-'a'+1; f < finalFile-'a'; f++)
					if (board[f][inRank-1].getPiece() != null)
						return true;
			if (initFile > finalFile) //movement to the left
				for (f = initFile-'a'-1; f > finalFile-'a'; f--)
					if (board[f][inRank-1].getPiece() != null)
						return true;
		}
		
		// if it's vertical movement
		if (inRank != finRank && initFile == finalFile) {
			if (inRank < finRank) //movement upwards
				for (r = inRank; r < finRank - 1; r++)
					if (board[initFile-'a'][r].getPiece() != null)
						return true;
			if (inRank > finRank) //movement downwards
				for (r = inRank-2; r >= finRank; r--)
					if (board[initFile-'a'][r].getPiece() != null)
						return true;
		}
		
		// if it's diagonal movement
		int rankDif = inRank - finRank;
		int fileDif = initFile - finalFile;
		if (Math.abs(rankDif) == Math.abs(fileDif)) {
			// up right if both quantities are negative
			if (fileDif < 0 && rankDif < 0) {
				r = inRank;
				for (f = initFile-'a'+1; f < finalFile-'a'; f++) {
					
					if (board[f][r].getPiece() != null)
						return true;
					r++;
				}
			}
			// up left if the fileDif is positive and the rankDif is negative
			if (fileDif > 0 && rankDif < 0) {
				r = inRank;
				for (f = initFile-'a'-1; f > finalFile-'a'; f--) {
					if (board[f][r].getPiece() != null)
						return true;
					r++;
				}
			}
			// down right if the fileDif is negative and the rankDif is positive
			if (fileDif < 0 && rankDif > 0) {
				r = inRank-2;
				for (f = initFile-'a'+1; f < finalFile-'a'; f++) {
					if (board[f][r].getPiece() != null) {
						return true;
					}
					r--;
				}
			}
			// downleft if both quantities are positive
			if (fileDif > 0 && rankDif > 0) {

				r= inRank-2;
				for (f = initFile-'a'-1; f > finalFile-'a'; f--) {
					if (board[f][r].getPiece() != null)
						return true;
					r--;
				}
			}
		}
		
		return false;
	}
}
