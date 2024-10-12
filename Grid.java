//KIT107 Assignment 3
/**
 *	Grid ADT
 *
 *	@author <<Your names and Student IDs>>
 *	@version September 2024
 *
 *	This file holds the Grid ADT which represents
 *	the Tic-Tac-Toe board.  The Grid consists of a location
 *	(of	the current position of the solver), a dimension,
 *	a value (of the current board), and a two-dimensional
 *	array (table/matrix) of the squares in the board.
 *
 *	YOU NEED TO MAKE CHANGES TO THIS FILE!
*/

import java.awt.*;


public class Grid implements GridInterface, Cloneable, Comparable
{
	//finals
	protected final boolean TRACING=true;	// do we want to see trace output?

	// properties
	protected int dimension;			// size of the grid
	protected Square board[][];			// all the Squares within the grid
	protected int value;				// 'worth' of grid


	/**
	 *	Grid
	 *	Constructor method 1.
	 *	Pre-condition: none
	 *	Post-condition: a 3x3 grid is created in which all
	 *					squares are empty
	 *	Informally: create an empty 3x3 grid
	*/
	public Grid()
	{
      	trace("Grid: Constructor starts");

		dimension=3;
		initialiseGrid();

      	trace("Grid: Constructor ends");
	}


	/**
	 *	Grid
	 *	Constructor method 2.
	 *	Pre-condition: none
	 *	Post-condition: a grid of given dimension where all
	 *					squares are empty is created
	 *	Informally: create an unoccupied grid of given dimension
	 *
	 *	@param d number of rows and columns in grid
	*/
	public Grid(int d)
	{
      	trace("Grid: Constructor starts");

//COMPLETE ME

      	trace("Grid: Constructor ends");
	}


	/*
	 *	Grid
	 *	Constructor method 3.
	 *	Pre-condition: the given Dimension, Location, and Symbol
	 *					values are defined and valid
	 *	Post-condition: a Grid of given Dimension is created
	 *					where all squares have no symbol but the
	 *					square at the given location is occupied
	 *					by the given symbol
	 *	Informally: create a grid of given dimension where all
	 *					squares are empty except the one at the
	 *					given location which is occupied by the
	 *					given symbol
	 *
	 *	@param d number of rows and columns in grid
	 *	@param l Location for first move
	 *	@param s Symbol for first move
	*/
	public Grid(int d, Location l, Symbol s) throws IllegalGridException
	{
		assert ((d>0) && (l!=null) && (s!=null));

      	trace("Grid: Constructor starts");

		// create an empty grid of required size
		dimension=d;
		initialiseGrid();

		// add the move to the empty grid (unless it is illegal)
		if (validMove(l))
		{
			trace("Grid: occupying initial square");
			occupySquare(l,s);
		}
		else
		{
			trace("Grid: initial square not on grid");
			throw new IllegalGridException();
		}

      	trace("Grid: Constructor ends");
	}


	/**
	 *	initialiseGrid
	 *	Set up the grid.
	 *	Pre-condition: none
	 *	Post-condition: the two-dimensional array of Squares is
	 *					instantiated and filled with newly
	 *					created empty squares each with the
	 *					correct location
	 *	Informally: create an empty grid of known dimension
	*/
	protected void initialiseGrid()
	{
		int r,c;
		Location l;

      	trace("initialiseGrid: initialiseGrid starts");

		// instantiate the table (array of arrays)
		board=new Square[dimension][dimension];
		// initialise every square on the board
		for (r=1; r<=dimension; r++)
		{
			for (c=1; c<=dimension; c++)
			{
				l=new Location(r,c);
				board[r-1][c-1]=new Square(l);
			}
		}
		// initialise the starting value
		value=0;

      	trace("initialiseGrid: initialiseGrid ends");
	}


	/**
	 *	clone
	 *	Copy the grid.
	 *	Pre-condition: the current Grid object is validly defined
	 *	Post-condition: the Grid object is copied
	 *	Informally: copy the current Grid
	 *
	 *	@return Object the new created copy of the grid
	*/
	public Object clone()
	{
		Grid b;
		int r,c;
		Location l;

      	trace("clone: clone starts");

//COMPLETE ME

      	trace("clone: clone ends");
		return null;	//CHANGE ME
	}


	/**
	 *	setSquare
	 *	Set method for an element of the "board" instance variable.
	 *	Pre-condition: the given location and square are defined
	 *	Post-condition: the given square is assigned to an element
	 *					of the Grid object selected according to
	 *					the given location within the grid
	 *	Informally: insert the given square into the grid at the
	 *				appropriate location
	 *
	 *	@param l Location for the square
	 *	@param s Square to be stored within grid
	*/
	public void setSquare(Location l, Square s) throws IllegalGridException
	{
		int r;
		int c;

		assert ((l!=null) && (s!=null));

      	trace("setSquare: setSquare starts");

		if (!validMove(l))
		{
			trace("setSquare: location not on the grid");
			throw new IllegalGridException();
		}

		// update the grid at the indicated location with the given square
		s.setLocation(l);
		r=l.getRow();
		c=l.getColumn();

		board[r-1][c-1]=s;

      	trace("setSquare: setSquare ends");
	}


	/**
	 *	getSquare
	 *	Get method for an element of the "board" instance variable.
	 *	Pre-condition: the given location is defined
	 *	Post-condition: the Square object at the appropriate
	 *					element of the "board" selected according
	 *					to the given Location value is returned
	 *	Informally: return the square of the grid at the given
	 *				location, an exception is thrown if the
	 *				location is not within the grid
	 *
	 *	@param l Location of desired square
	 *	@return Square the desired Square
	*/
	public Square getSquare(Location l) throws IllegalGridException
	{
		int r;
		int c;

		assert (l!=null);

      	trace("getSquare: getSquare starts");

		r=l.getRow();
		c=l.getColumn();

		if (!validMove(l))
		{
			trace("getSquare: location not on grid");
			throw new IllegalGridException();
		}

		// obtain the square at the indicated location from the grid
      	trace("getSquare: getSquare ends");
		return board[r-1][c-1];
	}


	/**
	 *	setDimension
	 *	Set method for the "dimension" instance variable.
	 *	Pre-condition: the given Dimension value is defined and
	 *				   valid
	 *	Post-condition: the instance variable "dimension" is
	 *					assigned the given Dimension value
	 *	Informally: assign the given dimension to the Grid object
	 *
	 *	@param d the number of rows and columns in the grid
	*/
	public void setDimension(int d)
	{
		assert (d>0);

      	trace("setDimension: setDimension starts");

//COMPLETE ME
      	trace("setDimension: setDimension ends");
	}


	/**
	 *	getDimension
	 *	Get method for "dimension" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the value of the Grid object's dimension
	 *					field is returned
	 *	Informally: return the current grid's dimension
	 *
	 *	@return int the number of rows and columns in the grid
	*/
	public int getDimension()
	{
      	trace("getDimension: getDimension starts and ends");
//COMPLETE ME
		return 0;	//CHANGE ME
	}


	/**
	 *	setWorth
	 *	Set method for the "value" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the instance variable "value" is assigned
	 *					the given value
	 *	Informally: set the worth of the Grid
	 *
	 *	@param v the value (worth) of the grid
	*/
	public void setWorth(int v)
	{
      	trace("setWorth: setWorth starts");

//COMPLETE ME
      	trace("setWorth: setWorth ends");
	}


	/**
	 *	getWorth
	 *	Get method for "value" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the value of the Grid object's worth is
	 *					returned
	 *	Informally: return the worth of the Grid
	 *
	 *	@return int the value (worth) of the grid
	*/
	public double getWorth()
	{
      	trace("getWorth: getWorth starts and ends");
//COMPLETE ME
		return 0.0;	//CHANGE ME
	}


	/**
	 *	occupySquare
	 *	Make a move.
	 *	Pre-condition: the given location and symbol are defined
	 *	Post-condition: the square at the position in the
	 *					grid indicated by the given Location
	 *					value is altered to the given Symbol
	 *	Informally: update the square at the nominated location
	 *				of the grid with the given symbol
	 *
	 *	@param l Location to place symbol at
	 *	@param s Symbol to place
	*/
	public void occupySquare(Location l, Symbol s)
	{
		Square q;

		assert ((l!=null) && (s!=null));

      	trace("occupySquare: occupySquare starts");

//COMPLETE ME

      	trace("occupySquare: occupySquare ends");
	}


	/**
	 *	squareOccupied
	 *	Check if a square is already taken
	 *	Pre-condition: the given location is defined
	 *	Post-condition: a Boolean value is returned which
	 *					represents whether the symbol of
	 *					the square of the current Grid
	 *					object with the given Location
	 *					value is empty
	 *	Informally: return whether or not the square at
	 *				the given location is occupied
	 *
	 *	@param l Location of square to check
	*/
	public boolean squareOccupied(Location l)
	{
		assert (l!=null);

      	trace("squareOccupied: squareOccupied starts and ends");
//COMPLETE ME

		return false;	//CHANGE ME
	}


	/**
	 *	getSymbol
	 *	Observe symbol at given location.
	 *	Pre-condition: the given location is defined
	 *	Post-condition: return the symbol of the square of
	 *					the current Grid object with the given
	 *					Location
	 *	Informally: return the symbol at the given location
	 *					of the grid
	 *
	 *	@param l Location of square lo look in
	 *	@return Symbol whatever is in the square of given Location
	*/
	public Symbol getSymbol(Location l)
	{
		assert (l!=null);

      	trace("getSymbol: getSymbol starts and ends");
//COMPLETE ME
		return null;	//CHANGE ME
	}


	/**
	 *	validMove
	 *	Check if a location is in the grid.
	 *	Pre-condition: the given location is defined
	 *	Post-condition: true is returned if the given
	 *					Location is within the bounds of
	 *					the current Grid object, false is
	 *					returned if it is not
	 *	Informally: return whether or not the given
	 *				location lies within the current grid
	 *
	 *	@param l Location to consider
	 *	@return boolean whether or not Location is on the grid
	*/
	public boolean validMove(Location l)
	{
		int r;
		int c;

		assert (l!=null);

      	trace("validMove: validMove starts");

//COMPLETE ME

      	trace("validMove: validMove ends");
		return false;	//CHANGE ME
	}


	/**
	 *	fullGrid
	 *	Check if the grid is full of symbols.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the grid has
	 *					all squares occupied, false is
	 *					returned if it is not
	 *	Informally: return whether or not the given
	 *				grid is full
	 *
	 *	@return boolean whether or not all squares have a
	 *				non-empty symbol within them
	*/
	protected boolean fullGrid()
	{
  		int r,c;
  		Location l;

      	trace("fullGrid: fullGrid starts");

		// check every square on the grid
		for (r=1; r<=dimension; r++)
  		{
  			for (c=1; c<=dimension; c++)
			{
				l=new Location(r,c);
  				if (! squareOccupied(l))
    			{
					// this is isn't occupied, and so grid cannot be full
		      		trace("fullGrid: found an empty square");
    				return false;
    			}
    		}
		}

      	trace("fullGrid: fullGrid ends");
  		return true;
	}


	/**
	 *	gameOver
	 *	Check if the game is over.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the game is
	 *					over because of either a win/loss
	 *					or a draw
	 *	Informally: return whether or not the game is over
	 *
	 *	@return boolean whether or not the game is over
	*/
	public boolean gameOver()
	{
		boolean res=false;

      	trace("gameOver: gameOver starts");

//COMPLETE ME

		trace("gameOver: gameOver ends");
		return false;	//CHANGE ME
    }


	/**
	 *	win
	 *	Find out who (if anyone) has won.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the game is
	 *					over because of a win/loss
	 *	Informally: return whether or not the game has
	 *				been won or lost
	 *
	 *	@return Symbol the winner (or empty if noone)
	*/
	public Symbol win()
	{
  		Symbol res;

      	trace("win: win starts");

		// check the diagonals
  		res=diagWin();
  		if (res.compareTo(new Symbol()) == 0)
  		{
			// no diagonal win, check the rows
    		res=horizWin();

    		if (res.compareTo(new Symbol()) == 0)
      		{
				// no diagonal or horizontal win, check the columns
      			res=vertWin();
      		}
  		}

      	trace("win: win ends");
  		return res;
	}


	/**
	 *	draw
	 *	Check if the game is drawn.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the game is
	 *					over because of a draw
	 *	Informally: return whether or not the game has
	 *				been drawn
	 *
	 *	@return boolean whether or not the game is a draw
	*/
	public boolean draw()
	{
		boolean res=false;

      	trace("draw: draw starts");

	  	if ((fullGrid()) && (win().isEmpty()))
    	{
			// the grid is full and there's no winner, must be a draw
    	  	trace("draw: draw ends");
    		res=true;
    	}
	  	else
    	{
			// there's a winner, or the game continues
	      	trace("draw: draw ends");
    	}

		return res;
    }


	/**
	 *	diagWin
	 *	Check whether a player has won diagonally.
	 *	Pre-condition: none
	 *	Post-condition: the winning player's symbol is returned if the
	 *					game is over because of a diagonal win/loss
	 *	Informally: return whether or not the game has
	 *				been won or lost diagonally
	 *
	 *	@return Symbol who has won diagonally (or empty if noone)
	*/
	protected Symbol diagWin()
	{
		Symbol res;

      	trace("diagWin: diagWin starts");

		// check the left diagonal
		res=leftDiagWin();
		if (res.compareTo(new Symbol()) == 0)
		{
			// no left diagonal win, check the right diagonal
	      	trace("diagWin: diagWin ends");
			return rightDiagWin();
		}
		else
		{
			// left diagonal win
    	  	trace("diagWin: diagWin ends");
			return res;
		}
	}


	/**
	 *	leftDiagWin
	 *	Check whether a player has won on the left diagonal.
	 *	Pre-condition: none
	 *	Post-condition: the winning player's symbol is returned if the
	 *					game is over because of a left-diagonal
	 *					win/loss
	 *	Informally: return whether or not the game has
	 *				been won or lost on a left-diagonal
	 *
	 *	@return Symbol who has won diagonally (or empty if noone)
	*/
	protected Symbol leftDiagWin()
	{
		Symbol res;
		int r,c;
		Location l;
  		boolean win;

      	trace("leftDiagWin: leftDiagWin starts");
		// systematically, check the left diagonal
  		r=1;
  		c=1;
  		l=new Location(r,c);
  		res=getSquare(l).getSymbol();

  		win=true;
  		c++;
  		r++;
  		l=new Location(r,c);
  		while ((win) && (c<=dimension))
  		{
    		if (res.compareTo(getSquare(l).getSymbol()) != 0)
    		{
		      	win=false;
		    }
    		else
    		{
      			c++;
      			r++;
  				l=new Location(r,c);
      		}
  		}

  		if ((win) && (res.compareTo(new Symbol()) != 0))
  		{
			// left diagonal win
	      	trace("leftDiagWin: leftDiagWin ends");
    		return res;
    	}

		// no win on the left diagonal
      	trace("leftDiagWin: leftDiagWin ends");
		return (new Symbol());
	}


	/**
	 *	rightDiagWin
	 *	Check whether a player has won on the left diagonal.
	 *	Pre-condition: none
	 *	Post-condition: the winning player's symbol is returned if the
	 *					game is over because of a right-diagonal
	 *					win/loss
	 *	Informally: return whether or not the game has
	 *				been won or lost on a right-diagonal
	 *
	 *	@return Symbol who has won diagonally (or empty if noone)
	*/
	protected Symbol rightDiagWin()
	{
 		Symbol res;
		int r,c;
		Location l;
  		boolean win;

      	trace("rightDiagWin: rightDiagWin starts");
		// systematically, check the right diagonal
		r=1;
  		c=dimension;
  		l=new Location(r,c);
  		res=getSquare(l).getSymbol();

  		win=true;
  		c--;
  		r++;
  		l=new Location(r,c);
  		while ((win) && (c>=1))
  		{
    		if (res.compareTo(getSquare(l).getSymbol()) != 0)
    		{
		      	win=false;
		    }
    		else
    		{
      			c--;
      			r++;
  				l=new Location(r,c);
      		}
  		}

  		if ((win) && (res.compareTo(new Symbol()) != 0))
    	{
			// right diagonal win
			trace("rightDiagWin: rightDiagWin ends");
    		return res;
    	}

		// no win on the right diagonal
		trace("rightDiagWin: rightDiagWin ends");
		return (new Symbol());
  	}


	/**
	 *	horizWin
	 *	Check whether a player has won horizontally.
	 *	Pre-condition: none
	 *	Post-condition: the winning player's symbol is returned if the
	 *					game is over because of a horizontal win/loss
	 *	Informally: return whether or not the game has
	 *				been won or lost horizontally
	 *
	 *	@return Symbol who has won horizontally (or empty if noone)
	*/
	protected Symbol horizWin()
	{
  		Symbol res=new Symbol();
  		int r,c;
  		Location l;
  		boolean win;

      	trace("horizWin: horizWin starts");
		// systematically, check the rows
  		for (r=1; r<=dimension; r++)
  		{
    		c=1;
    		l=new Location(r,c);
		    res=getSquare(l).getSymbol();
    		win=true;
    		c++;
    		while ((c<=dimension) && (win))
    		{
	    		l=new Location(r,c);
		      	if (res.compareTo(getSquare(l).getSymbol()) != 0)
		      	{
        			win=false;
        		}
      			else
      			{
        			c++;
        		}
    		}
    		if ((win) && (res.compareTo(new Symbol()) != 0))
		    {
			// horizontal win
			trace("horizWin: horizWin ends");
      			return res;
      		}
	    }

		// no win on the horizontal
		trace("horizWin: horizWin ends");
    	return new Symbol();
	}


	/**
	 *	vertWin
	 *	Check whether a player has won vertically.
	 *	Pre-condition: none
	 *	Post-condition: the winning player's symbol is returned if the
	 *					game is over because of a vertical win/loss
	 *	Informally: return whether or not the game has
	 *				been won or lost vertically
	 *
	 *	@return Symbol who has won vertically (or empty if noone)
	*/
	protected Symbol vertWin()
	{
  		Symbol res=new Symbol();
  		int r,c;
  		Location l;
  		boolean win;

      	trace("vertWin: vertWin starts");
		// systematically, check the columns
		for (c=1; c<=dimension; c++)
  		{
    		r=1;
    		l=new Location(r,c);
		    res=getSquare(l).getSymbol();
    		win=true;
    		r++;
    		while ((r<=dimension) && (win))
    		{
	    		l=new Location(r,c);
		      	if (res.compareTo(getSquare(l).getSymbol()) != 0)
        			win=false;
      			else
      			{
        			r++;
        		}
    		}
    		if ((win) && (res.compareTo(new Symbol()) != 0))
		    {
			// vertical win
			trace("vertWin: vertWin ends");
      			return res;
      		}
	    }

		// no win on the vertical
		trace("vertWin: vertWin ends");
    	return new Symbol();
	}


	/**
	 *	evaluateGrid
	 *	Find the value of a grid from a player's perspective.
	 *	Pre-condition: the player is defined
	 *	Post-condition: the value of the grid from the given
	 *					player's perspective is returned
	 *	Informally: count the given player's advantage
	 *
	 *	@param p Player from whose perspective the grid should be
	 *				evaluated
	 *	@return int worth of the grid
	*/
	public int evaluateGrid(Player p)
	{
  		Symbol w;
  		int playerTotal;

		assert (p!=null);

      	trace("evaluateGrid: evaluateGrid starts");

		// find out what the grid's worth from the given player's perspective
  		if (draw())
  		{
			// it's a draw, so it's neither good nor bad
	      	trace("evaluateGrid: evaluateGrid ends (draw)");
  			return 0;
  		}
  		else
  		{
	  		w=win();
	  		if (w.compareTo(p.getSymbol())==0)
	   		{
				// it's a win, so it's great!
      			trace("evaluateGrid: evaluateGrid ends (win)");
	   			return dimension*dimension;
	   		}
	  		else
	  		{
	    		if (w.compareTo(new Symbol())!=0)
	      		{
					// it's a loss, so it's not great at all!
			      	trace("evaluateGrid: evaluateGrid ends (loss)");
	      			return -(dimension*dimension);
	      		}
	      		else
	      		{
					// determine value based on sole occupancy of rows, columns, and diagonals
	      			playerTotal=evaluateRows(p);
	      			playerTotal+=evaluateCols(p);
	      			playerTotal+=evaluateLDiags(p);
	      			playerTotal+=evaluateRDiags(p);
			      	trace("evaluateGrid: evaluateGrid ends (" + playerTotal + ")");
	      			return playerTotal;
	      		}
	      	}
	    }
	}


	/**
	 *	evaluateRows
	 *	Find the value of rows in a grid from a player's perspective.
	 *	Pre-condition: the given player is defined
	 *	Post-condition: the value of the grid's rows from the
	 *					given player's perspective is returned
	 *	Informally: count the given player's advantage in terms of
	 *				rows
	 *
	 *	@param p Player from whose perspective the grid should be
	 *				evaluated
	 *	@return int worth of the grid's rows
	*/
	protected int evaluateRows(Player p)
	{
		Symbol w;
		int r,c;
		Location l;
		int playerRow, oppRow;
		int playerTotal;

		assert (p!=null);

      	trace("evaluateRows: evaluateRows starts");
		// count the rows solely occupied by the given player
		playerTotal=0;
		for (r=1; r<=dimension; r++)
		{
			playerRow=0;
			oppRow=0;
			for (c=1; c<=dimension; c++)
			{
				l=new Location(r,c);
				if (squareOccupied(l))
				{
					w=getSquare(l).getSymbol();
					if (w.compareTo(p.getSymbol())==0)
					{
						playerRow++;
					}
					else
					{
						oppRow++;
					}
				}
			}
			if ((playerRow > 0) && (oppRow == 0))
			{
				playerTotal++;
			}
			if ((oppRow > 0) && (playerRow == 0))
			{
				playerTotal--;
			}
		}

      	trace("evaluateRows: evaluateRows ends (" + playerTotal + ")");
		return playerTotal;
	}


	/**
	 *	evaluateCols
	 *	Find the value of columns in a grid from a player's
	 *		perspective.
	 *	Pre-condition: the given player is defined
	 *	Post-condition: the value of the grid's columns from
	 *					the given player's perspective is returned
	 *	Informally: count the given player's advantage in terms of
	 *				columns
	 *
	 *	@param p Player from whose perspective the grid should be
	 *				evaluated
	 *	@return int worth of the grid's columns
	*/
	protected int evaluateCols(Player p)
	{
		Symbol w;
		int r,c;
		Location l;
		int playerCol, oppCol;
		int playerTotal;

		assert (p!=null);

      	trace("evaluateCols: evaluateCols starts");
		// count the columns solely occupied by the given player
		playerTotal=0;
		for (c=1; c<=dimension; c++)
		{
			playerCol=0;
			oppCol=0;
			for (r=1; r<=dimension; r++)
			{
				l=new Location(r,c);
				if (squareOccupied(l))
				{
					w=getSquare(l).getSymbol();
					if (w.compareTo(p.getSymbol())==0)
					{
						playerCol++;
					}
					else
					{
						oppCol++;
					}
				}
			}
			if ((playerCol > 0) && (oppCol == 0))
			{
				playerTotal++;
			}
			if ((oppCol > 0) && (playerCol == 0))
			{
				playerTotal--;
			}
		}

      	trace("evaluateCols: evaluateCols ends (" + playerTotal + ")");
		return playerTotal;
	}


	/**
	 *	evaluateLDiags
	 *	Find the value of left-diagonals in a grid from a player's
	 *		perspective.
	 *	Pre-condition: the given player is defined
	 *	Post-condition: the value of the grid's left-diagonals from
	 *					the given player's perspective is returned
	 *	Informally: count the given player's advantage in terms of
	 *				left-diagonals
	 *
	 *	@param p Player from whose perspective the grid should be
	 *				evaluated
	 *	@return int worth of the grid's left diagonal
	*/
	protected int evaluateLDiags(Player p)
	{
		Symbol w;
		int r,c;
		Location l;
		int playerDiag, oppDiag;
		int playerTotal;

		assert (p!=null);

      	trace("evaluateLDiags: evaluateLDiags starts");
		// check if the left diagonal is solely occupied by the given player
		playerTotal=0;
  		playerDiag=0;
  		oppDiag=0;
	  	for (r=1; r<=dimension; r++)
	  	{
  			c=r;
	  		l=new Location(r,c);
	  		if (squareOccupied(l))
	  		{
		  		w=getSquare(l).getSymbol();
				if (w.compareTo(p.getSymbol())==0)
				{
					playerDiag++;
				}
				else
				{
					oppDiag++;
				}
	  		}
	  	}
		if ((playerDiag > 0) && (oppDiag == 0))
		{
			playerTotal++;
		}
		if ((oppDiag > 0) && (playerDiag == 0))
		{
			playerTotal--;
		}

      	trace("evaluateLDiags: evaluateLDiags ends (" + playerTotal + ")");
		return playerTotal;
	}


	/**
	 *	evaluateRDiags
	 *	Find the value of right-diagonals in a grid from a
	 *		player's perspective.
	 *	Pre-condition: the given player is defined
	 *	Post-condition: the value of the grid's right-diagonals
	 *					from the given player's perspective is returned
	 *	Informally: count the given player's advantage in terms of
	 *				right-diagonals
	 *
	 *	@param p Player from whose perspective the grid should be
	 *				evaluated
	 *	@return int worth of the grid's right diagonal
	*/
	protected int evaluateRDiags(Player p)
	{
		Symbol w;
		int r,c;
		Location l;
		int playerDiag, oppDiag;
		int playerTotal;

		assert (p!=null);

      	trace("evaluateRDiags: evaluateRDiags starts");
		// check if the right diagonal is solely occupied by the given player
		playerTotal=0;
  		playerDiag=0;
  		oppDiag=0;
		r=1;
	  	for (c=dimension; c>=1; c--)
	  	{
	  		l=new Location(r,c);
	  		if (squareOccupied(l))
	  		{
		  		w=getSquare(l).getSymbol();
				if (w.compareTo(p.getSymbol())==0)
				{
					playerDiag++;
				}
				else
				{
					oppDiag++;
				}
	  		}
	  		r++;
	  	}
		if ((playerDiag > 0) && (oppDiag == 0))
		{
			playerTotal++;
		}
		if ((oppDiag > 0) && (playerDiag == 0))
		{
			playerTotal--;
		}

      	trace("evaluateRDiags: evaluateRDiags ends (" + playerTotal + ")");
		return playerTotal;
	}


	/**
	 *	toString
	 *	Convert the grid to a String representation.
	 *	Pre-condition: none
	 *	Post-condition: a String representation of the grid
	 *					is returned
	 *	Informally: find a String representation of the grid
	 *
	 *	@return String representation of the grid
	*/
	public String toString()
	{
		int r,c;
		Location l;
		String s;

	   	trace("toString: toString starts");

		s="\n+";
		for (int k=1; k<=dimension; k++)
		{
			s+="-+";
		}
		s+="\n";
		for (r=1; r<=dimension; r++)
		{
			s+="|";
			for (c=1; c<=dimension; c++)
			{
				l=new Location(r,c);
				s=s+getSquare(l);
				s+="|";
			}
			s+="\n";
			s+="+";
			for (int k=1; k<=dimension; k++)
			{
				s+="-+";
			}
			s+="\n";
		}
		s+="Worth: " + getWorth() + "\n";

      	trace("toString: toString ends");
		return s;
	}


	/**
	 *	showGrid
	 *	Display the grid.
	 *	Pre-condition: the Screen parameter is correctly defined
	 *	Post-condition: the screen representation of the Grid
	 *					object is displayed on the given Screen
	 *	Informally: display the current grid
	 *
	 *	@param s the Display upon which the grid should be shown
	*/
	public void showGrid(Display s)
	{
		int r,c;
		int x,y;
		Location l;
		Graphics g;
		int width;
		Square q;

		assert (s!=null);

      	trace("showGrid: showGrid starts");

		width=(620-10-((dimension-1)*5)) / dimension;
		g=s.getGraphics();

		for (r=1; r<=dimension; r++)
		{
			for (c=1; c<=dimension; c++)
			{
				l=new Location(r,c);
				q=getSquare(l);
				q.showSquare(s,width);
				g.setColor(Color.WHITE);

				x=(c-1)*(width+5) + 5;
				y=70 + (r-1)*(width+5);
				if (c != 1)
				{
					g.fillRect(x,y,5,width+10);
				}
				if (r != 1)
				{
					g.fillRect(x,y,width+10,5);
				}
				if (c != dimension)
				{
					g.fillRect(x+width+5,y,5,width+10);
				}
				if (r != dimension)
				{
					g.fillRect(x,y+width+5,width+10,5);
				}
			}
		}
		g.setColor(Color.black);

		trace("showGrid: grid is " + toString() + " with worth " + getWorth());

      	trace("showGrid: showGrid ends");
 	}

	/**
	 *	compareTo
	 *	Comparison method for Grid.
	 *	Pre-condition: the parameter o is a defined Grid object
	 *	Post-condition: 0 is returned if the Grids are the same, +/-1
	 *					is returned otherwise
	 *	Informally: check if two Grids are equivalent
	 *
	 *	@param o the Grid object to compare with the current grid
	 *	@return int 0 if the same, +/-1 otherwise
	*/
	public int compareTo(Object o)
 	{
 		int d1,d2;
 		Location l;
 		int r,c;
 		Grid g;

 		assert (o instanceof Grid);

      	trace("compareTo: compareTo starts");

 		g=(Grid) o;
 		d1=getDimension();
 		d2=g.getDimension();

 		if (d1 != d2)
 		{
			// can't be the same grid -- they're different sizes!
			trace("compareTo: different dimension");
 			if (d1 < d2)
 			{
 				return -1;
 			}
 			else
 			{
 				return 1;
 			}
 		}
 		else
 		{
			// could be the same, better check all the squares
 			trace("compareTo: same dimension");
 			for (r=1; r<=d1; r++)
 			{
 				for (c=1; c<=d1; c++)
 				{
 					l=new Location(r,c);
 					if (getSquare(l).getSymbol().compareTo(g.getSquare(l).getSymbol()) != 0)
 					{
						// found a difference, they're not the same grid
				      	trace("compareTo: different square content");
 						return -1; //it doesn't matter whether it's +/-1
 					}
				}
			}

	      	trace("compareTo: compareTo ends");
			// didn't find a difference, they must be equal
			return 0;
		}
 	}


	/**
	 *	equals
	 *	Comparison method for Grid.
	 *	Pre-condition: the parameter o is a defined Grid object
	 *	Post-condition: true is returned if the Grids are the same,
	 *					false is returned otherwise
	 *	Informally: check if two Grids are equivalent
	 *
	 *	@param g the Grid to compare with the current grid
	 *	@return boolean whether or not the two grids are equivalent
	*/
	public boolean equals(Grid g)
 	{
 		assert (g!=null);

      	trace("equals: equals starts and ends");
//COMPLETE ME
		return false;	//CHANGE ME
 	}


	/**
	 *	trace
	 *	Provide trace output.
	 *	Pre-condition: none
	 *	Post-condition: if trace output is desired then the given String
	 *					parameter is shown on the console
	 *	Informally: show the given message for tracing purposes
	 *
	 *	@param s String to display as trace message
	*/
	protected void trace(String s)
	{
		if (TRACING)
		{
			System.out.println("Grid: " + s);
		}
	}
}
