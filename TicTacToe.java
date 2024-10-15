//KIT107 Assignment 3
/**
 *	Graphical User Interface and Solution
 *
 *	@author Julian Dermoudy
 *	@version September 2024
 *
 *	This file holds the play() and other related
 *	routines from the sample solution.  The class is
 *	a frame that contains the graphical user interface.
 *
 *	THIS FILE IS COMPLETE BUT YOU SHOULD MODIFY SOME FINAL VARIABLES DURING DEVELOPMENT
*/


import java.awt.*;
import java.awt.event.*;


public class TicTacToe extends Frame implements TicTacToeInterface, ActionListener, MouseListener
{
	// finals
	protected final boolean TRACING=false;			// do we want to see trace output?
	protected final boolean HUMAN_VS_COMPUTER=true;	// is the user player against the computer?  CHANGE THIS ONCE GAME IS WORKING

	protected final int LOW_DIMENSION=3;			// low value for grid size range
	protected final int HIGH_DIMENSION=19;			// high value for grid size range
	protected final int BEGINNER=1;					// beginner look-ahead level
	protected final int INTERMEDIATE=3;				// intermediate look-ahead level
	protected final int EXPERT=5;					// expert look-ahead level
	protected final Player HUMAN=new Player(new Symbol(false));		// human (player 1) symbol (nought)
	protected final Player COMPUTER=new Player(new Symbol(true));	// computer (player 2) symbol (cross)

	// GUI widgets
	protected Label dimPrompt;			// prompt for dimension value
	protected Label userStartsPrompt;	// prompt to see if player 1 wishes to start
	protected Label lookPrompt;			// prompt for lookahead value (i.e. computer's intelligence)
	protected Label symmPrompt;			// prompt for symmetry inclusion
	protected Label randPrompt;			// prompt for random movement
	protected Choice dimL;				// menu for choosing dimension value
	protected Choice userStartsL;		// menu for selecting whether player 1 wishes to start
	protected Choice lookL;				// menu for selecting lookahead value
	protected Choice symmL;				// menu for determining whether symmetries are allowed or not
	protected Choice randL;				// menu for determining whether randome moves are allowed or not
	protected Button playDF;			// button to play via Depth-First generated game tree
	protected Button playBF;			// button to play via Breadth-First generated game tree (and two-human-player mode)

	// algorithmic values and settings
   	protected Stack dfst;				// an intermediate stack used for the depth-first search
   	protected Queue bfst;				// an intermediate queue used for the breadth-first search
   	protected Display window;			// the screen variable used for display
   	protected boolean started;			// used to indicate if the frame has been initialised
	protected GameTree game;			// game tree representing the current and future states of the game
	protected Grid board;				// initial board
	protected int dim;					// dimension value for the game
	protected int lookAhead;			// lookahead value for the game
	protected boolean symmetries;		// should symmetric moves be allowed?
	protected boolean randomness;		// should random moves be allowed?
	protected Player player;			// current player symbol
	protected boolean playing;			// is the game underway?
	protected boolean starting;			// is the human going first?
	protected boolean yourTurn;			// is it the human's turn?
	protected boolean myTurn;			// is it the computer's turn?
	protected boolean depthFirst;		// is the game tree to be generated via Depth-First?


  	/**
	 *	TicTacToe
	 *	Constructor method.
	 *	Pre-condition: the given String value contains the name
	 *				   for the frame (window) being created
	 *	Post-condition: a frame is created showing two buttons
	 *					and three drop-down menus
	 *	Informally: construct the window for displaying the
	 *				game and the game controls
	 *
	 *	@param a the title for the application's window
	*/
	public TicTacToe(String a)
   	{
   		assert (a != null);

      	trace("TicTacToe: Constructor starts");

      	window=new Display();

      	// create frame and window listener
      	setLayout(new FlowLayout());
      	setTitle(a);
      	setVisible(true);
      	addWindowListener(
      		new WindowAdapter() {
      			public void windowClosing(WindowEvent e) {
      				dispose();
      				System.exit(0);
      			}
      		}
      	);
     	setSize(630,705);
     	setResizable(false);

     	// initialise widgets
     	trace("TicTacToe: Adding GUI widgets");
		dimPrompt=new Label("Dim.:");
		dimL=new Choice();
		for (int i=LOW_DIMENSION; i<=HIGH_DIMENSION; i+=2)
		{
			dimL.add(Integer.toString(i));
		}
		add(dimPrompt);
		add(dimL);
		userStartsPrompt=new Label("Start:");
		userStartsL=new Choice();
		userStartsL.add("Y");
		userStartsL.add("N");
		lookPrompt=new Label("Level:");
		lookL=new Choice();
		for (int i=BEGINNER; i<=EXPERT; i++)
		{
			lookL.add(Integer.toString(i));
		}
		lookL.select(INTERMEDIATE);
		symmPrompt=new Label("Symm.:");
		symmL=new Choice();
		symmL.add("Y");
		symmL.add("N");
		randPrompt=new Label("Rand.:");
		randL=new Choice();
		randL.add("Y");
		randL.add("N");
		playDF=new Button("Start DF");
		if (HUMAN_VS_COMPUTER)
		{
			trace("TicTacToe: Single-player mode");
			add(userStartsPrompt);
			add(userStartsL);
			add(lookPrompt);
			add(lookL);
			add(symmPrompt);
			add(symmL);
			add(randPrompt);
			add(randL);
			add(playDF);
			playBF=new Button("Start BF");
		}
		else
		{
			trace("TicTacToe: Two-human-player mode");
			playBF=new Button("Start");
		}
		add(playBF);

		// initialise game controls
		trace("TicTacToe: Initialising game controls");
		game=new GameTree();
		playing=false;
		starting=false;
		yourTurn=false;
		myTurn=false;
		depthFirst=true;
		symmetries=true;
		randomness=true;

		if (HUMAN_VS_COMPUTER)
		{
			playDF.addActionListener(this);
		}
		playBF.addActionListener(this);
		addMouseListener(this);

		// the big reveal
		trace("TicTacToe: Display it all and wait!");
      	setVisible(true);
      	window.setGraphics(getGraphics());
      	repaint();

      	trace("TicTacToe: Contructor ends");
   	}


	/**
	 *	paint
	 *	Paint method.
	 *	Pre-condition: the given Graphics value is a valid
	 *				   graphics context and corresponds to the
	 *				   context stored within the "window"
	 *				   instance variable
	 *	Post-condition: the grid at the root of the tree
	 *					referred to by the "game" instance
	 *					variable is displayed.  The next turn
	 *					is also completed.
	 *	Informally: display the 'current' grid and have the next
	 *				turn.
	 *
	 *	@param g the current Graphics context
	*/
	public void paint(Graphics g)
	{
		Grid board;
		Stack s;
		Queue q;
		double v;

		assert (g != null);

		trace("paint: paint starts");

		super.paint(g);
      	window.setGraphics(g);

      	// deal with default set-up
		if (starting)
		{
			trace("paint: 'starting'");
			board = (Grid)game.getData();
			board.showGrid(window);
			myTurn=true;
		}

		// game underway
		if (playing)
		{
			trace("paint: 'playing'");
			board = (Grid)game.getData();
			board.showGrid(window);
			if (HUMAN_VS_COMPUTER)
			{
				if (!myTurn)
				{
					yourTurn=true;
				}
				else
				{
					trace("paint: starting a turn");
					board = (Grid)game.getData();
					board.showGrid(window);
					if (! board.gameOver())
					{
						// find user's move and then generate computer's alternatives
						trace("paint: game isn't over");
				        window.getGraphics().drawString("I'm thinking...",10,695);
						game.findMove();
						if (game.isEmpty())
						{
							game= new GameTree(board,0);	// new game tree
						}
				        if (depthFirst)
				        {
				        	trace("paint: generating the game tree depth-first");
							s=new Stack();
		  	      			game.buildGameDF(s,HUMAN,lookAhead);
		  	      		}
		  	      		else
		  	      		{
		  	      			trace("paint: generating the game tree breadth-first");
		  	      			q=new Queue();
		  	      			game.buildGameBF(q,HUMAN,lookAhead);
		  	      		}

		  	      		// evaluate options and select move
		  	      		trace("paint: game generated, evaluating options");
  						game.traverse();
   						v=game.getChild().chooseBest();
    					trace("paint: options evaluated, best outcome: " + v);
		        		game.findBest(v);
						board = (Grid)game.getData();
						trace("paint: move selected");
						myTurn=false;
						repaint();
					}
				}
			}

			// is it over?
      		if (board.gameOver())
      		{
      			trace("paint: game over");
      			if (board.draw())
      			{
					// a draw!
      				trace("paint: because of a draw");
	        		window.getGraphics().drawString("Game over: game is a DRAW",10,695);
	        	}
	        	else
	        	{
					// win or loss, but whose?
	        		if (COMPUTER.getSymbol().compareTo(board.win())==0)
	        		{
						// a loss for the human/player 2
	        			trace("paint: because of loss");
						if (HUMAN_VS_COMPUTER)
						{
	        				window.getGraphics().drawString("Game over: you LOST",10,695);
						}
						else
						{
	        				window.getGraphics().drawString("Game over: Player 2 WON",10,695);
						}
	        		}
	        		else
	        		{
						// a loss for the computer/player 1
	        			trace("paint: because of win");
						if (HUMAN_VS_COMPUTER)
						{
							window.getGraphics().drawString("Game over: you WON",10,695);
						}
						else
						{
	        				window.getGraphics().drawString("Game over: Player 1 WON",10,695);
						}
	        		}
	        	}
	        }
	    }

	    trace("paint: paint ends");
   	}


	/**
	 *	actionPerformed
	 *	Handle mouse-clicks
	 *	Pre-condition: the ActionEvent parameter indicates whether
	 *				   the "dfs" or "bfs" buttons were pressed
	 *	Post-condition: the game tree is generated using either a
	 *					depth- or breadth-first approach and the
	 *					solution displayed
	 *	Informally: initiate generation of the game tree
	 *
	 *	@param e the ActionEvent generated by the user
	*/
	public void actionPerformed(ActionEvent e)
   	{
   		assert (e != null);

   		trace("actionPerformed: actionPerformed starts");

		// which button was pressed?
 		if (e.getSource() == playDF)
 		{
			// the DFS button (human vs computer only)
 			trace("actionPerformed: DFS chosen");
			depthFirst=true;
		}
		else
		{
			// the BFS/start button
 			trace("actionPerformed: BFS chosen");
			depthFirst=false;
		}

		// play out turn
		play();

		trace("actionPerformed: actionPerformed ends");
   	}


	/**
	 *	play
	 *	Set-up game parameters; mouse activity drives remainder of game
	 *	Pre-condition: none
	 *	Post-condition: the parameters of the game are defined
	 *	Informally: get the details on how the game should be played
	*/
	protected void play()
	{
		int maxLevels;
		boolean userStarts;
		Symbol y;

		trace("play: play starts");

		//initialise variables
		starting=false;
		playing=false;
		// get user's parameters
		try
		{
			// determine dimension
			dim=Integer.parseInt(dimL.getSelectedItem());
			if ((dim < LOW_DIMENSION) || (dim > HIGH_DIMENSION))
			{
				trace("play: illegal dimension entered");
				window.getGraphics().drawString("Dimension must be > "
						+ LOW_DIMENSION + " and < " + HIGH_DIMENSION,10,695);
			}
			else
			{
				trace("play: " + dim + " x " + dim);
				// determine computer's intelligence
				lookAhead=Integer.parseInt(lookL.getSelectedItem());
				trace("play: computer thinks " + lookAhead + " moves ahead");
				// who starts
				userStarts=(userStartsL.getSelectedItem().equals("Y"));
				symmetries=(symmL.getSelectedItem().equals("Y"));
				randomness=(randL.getSelectedItem().equals("Y"));
				maxLevels=dim*dim;
				if (userStarts)
				{
					maxLevels--;
				}

				// create game proper
				board=new Grid(dim);
				game=new GameTree(board,1);
				game.setSymmetries(symmetries);
				game.setRandomness(randomness);
				if (userStarts)
				{
					// user is starting
					trace("play: user starts");
					starting=true;
					player=HUMAN;
					yourTurn=true;
					if (HUMAN_VS_COMPUTER)
					{
						myTurn=false;
					}
				}
				else
				{
					// computer is starting
					trace("play: computer/player 2 starts");
					playing=true;
					yourTurn=false;
					myTurn=true;
					if (HUMAN_VS_COMPUTER)
					{
						player=HUMAN;
					}
					else
					{
						player=COMPUTER;
					}
				}
 			}
		}
		catch (NumberFormatException n)
		{
			trace("play: illegal input value");
			window.getGraphics().drawString("Number please!",10,695);
		}
		repaint();

		trace("play: play ends");
	}


	public void mousePressed(MouseEvent e)
	{
		//method is complete
	}


	public void mouseClicked(MouseEvent e)
	{
		//method is complete
	}


	/**
	 *	mouseReleased
	 *	Deal with moves from human player(s).
	 *	Pre-condition: the mouse has been clicked
	 *	Post-condition: if the game is underway the human's intended move
	 *					is deduced and if the move is legal, is made.  The
	 *					resulting state of the game is displayed through a
	 *					call to paint()
	 *	Informally: get the user's move and update the board (moving to the
	 *					computer's move if the human's move was legal)
	 *
	 *	@param e the MouseEvent generated by the user
	*/
	public void mouseReleased(MouseEvent e)
	{
		Grid board;
		int dim;
		Location l;
		int width;
		int r,c;
		int x,y;

		assert (e != null);

		trace("mouseReleased: mouseReleased starts");

		if ((! game.isEmpty()) && (starting || playing) && (yourTurn))
		{
			// game is underway, determine move by elmination from bottom row up
			board=(Grid)game.getData();
			dim=board.getDimension();
			width=(600-10-((dim-1)*5)) / dim;
			x=e.getX();
			y=e.getY();
			r=(y-75) / (width+5) + 1;
			c=(x-5) / (width+5) + 1;

			if (! board.gameOver())
			{
				// game not over, so work out what move to make based on location of mouse click
				trace("mouseReleased: column " + c + " chosen");
				trace("mouseReleased: trying row " + r);
				l=new Location(r,c);
				if ((y>75) && (board.validMove(l)))
				{
					// the click was within the bounds of the grid
					if (! board.squareOccupied(l))
					{
						// and the square wasn't occupied, so move there and continue the game
						trace("mouseReleased: ok, making move");
						board.occupySquare(l,player.getSymbol());

						game.setData(board);
						if (starting) {
							starting=false;
							playing=true;
						}
						myTurn=true;
						if (HUMAN_VS_COMPUTER)
						{
							yourTurn=false;
						}
						else
						{
							player=player.opponent();
						}
						repaint();
					}
					else
					{
						// but the square was occupied
						trace("mouseReleased: the square was taken");

						getGraphics().drawString("Square is taken!",10,695);
					}
				}
				else
				{
					// the click was outside the bounds of the grid
					trace("mouseReleased: the square was outside the grid");
					getGraphics().drawString("Square is not on board!",10,695);
				}
			}
		}

		trace("mouseReleased: mouseReleased ends");
	}


	public void mouseEntered(MouseEvent e)
	{
		//method is complete
	}


	public void mouseExited(MouseEvent e)
	{
		//method is complete
	}


	/**
	 *	trace
	 *	Provide trace output.
	 *	Pre-condition: none
	 *	Post-condition: if trace output is desired then the given String
	 *					parameter is shown on the console
	 *	Informally: show the given message for tracing purposes
	 *
	 *	@param s the String to be displayed as the trace message
	*/
	protected void trace(String s)
	{
		if (TRACING)
		{
			System.out.println("TicTacToe: " + s);
		}
	}
}
