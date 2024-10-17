//KIT107 Assignment 3
/**
 *	GameTree ADT
 *
 *	@author <<Your names and Student IDs>>
 *	@version September 2024
 *
 *	This file holds the GameTree ADT which is a
 *	general game tree.  The GameTree is built using
 *	TNode ADTs.  A GameTree object consists of a
 *	"root" field which refers to a TNode object
 *	which has a "data" field and "child" and
 *	"sibling" references, and a "level" value.
 *
 *	YOU NEED TO MAKE CHANGES TO THIS FILE!
*/


public class GameTree implements GameTreeInterface
{
	// finals
	protected final boolean TRACING=false;				// do we want to see trace output?

	// non-static property (i.e. all objects have their own value)
	protected TNode root;								// the node at the top of the tree
	// static properties (i.e. all objects share these values)
	protected static boolean moveRandomly;				// choose the first/random best move
	protected static boolean symmetryAllowed;			// include symmetrical moves or not


	/**
	 *	GameTree
	 *	Constructor method 1.
	 *	Pre-condition: none
	 *	Post-condition: the GameTree object's "root" field is null
	 *	Informally: creates an empty tree
	*/
	public GameTree()
	{
		trace("GameTree: constructor starts");

		this.root = null;

		trace("GameTree: constructor ends");
	}


	/**
	 *	GameTree
	 *	Constructor method 2.
	 *	Pre-condition: none
	 *	Post-condition: the GameTree object's "root" field refers
	 *					to a new TNode object containing the
	 *					parameter value (o) of level with
	 *					parameter value (l) with a null child,
	 *					and a null sibling
	 *	Informally: create a tree of a single node (i.e. a leaf)
	 *				with the node and level value provided on the
	 *				parameter list
	 *
	 *	@param o Object to include in GameTree node
	 *	@param l level number for GameTree
	*/
	public GameTree(Object o, int l)
	{
		trace("GameTree: constructor starts");

		this.root = new TNode(o,l);

		trace("GameTree: constructor ends");
	}


	public boolean isEmpty()
	/**
	 *	isEmpty
	 *	Emptiness test.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the GameTree object is
	 *					empty, false is returned otherwise
	 *	Informally: indicate if the GameTree contains no nodes
	 *
	 *	@return boolean whether or not the game tree is empty
	*/
	{
		trace("isEmpty: isEmpty starts and ends");

		return (this.root == null);
	}


	/**
	 *	getData
	 *	Get method for "root" instance variable's data value.
	 *	Pre-condition: none
	 *	Post-condition: the value of the GameTree object's data
	 *					field is returned
	 *	Informally: return the value within the root node,
	 *				throw an exception if the tree is empty
	 *
	 *	@return Object the data item of the root node
	*/
	public Object getData() throws EmptyGameTreeException
	{
		trace("getData: getData starts");

		if (isEmpty())
		{
			trace("getData: empty game tree");
			throw new EmptyGameTreeException();
		}

		trace("getData: getData ends");

		Object d = this.root.getData();

		return d;
	}


	/**
	 *	getRandomness
	 *	Get method for "moveRandomly" instance variable's value.
	 *	Pre-condition: none
	 *	Post-condition: the value of the GameTree object's moveRandomly
	 *					field is returned
	 *	Informally: return whether random moves will be chosen
	 *
	 *	@return boolean whether or not the next best move is chosen randomly
	*/
	public boolean getRandomness()
	{
		trace("getRandomness: getRandomness starts and ends");

		return moveRandomly;
}


	/**
	 *	getSymmetries
	 *	Get method for "symmetryAllowed" instance variable's value.
	 *	Pre-condition: none
	 *	Post-condition: the value of the GameTree object's symmetryAllowed
	 *					field is returned
	 *	Informally: return whether symmetric moves are allowed
	 *
	 *	@return boolean whether or not symmetrical moves should be considered
	*/
	public boolean getSymmetries()
	{
		trace("getSymmetries: getSymmetries starts and ends");

		return symmetryAllowed;
}


	/**
	 *	getLevel
	 *	Get method for "root" instance variable's level value.
	 *	Pre-condition: none
	 *	Post-condition: the value of the GameTree object's data
	 *					field's level is returned
	 *	Informally: return the level value within the root node,
	 *				throw an exception if the tree is empty
	 *
	 *	@return int the level number of the root node
	*/
	public int getLevel() throws EmptyGameTreeException
	{
		int level;
		trace("getLevel: getLevel starts");

		if (isEmpty())
		{
			trace("getLevel: empty game tree");
			throw new EmptyGameTreeException();
		}

		trace("getLevel: getLevel ends");

		level = this.root.level;

		return level;
	}


	/**
	 *	getChild
	 *	Get method for "root" instance variable's child value.
	 *	Pre-condition: none
	 *	Post-condition: the value of the GameTree object's child
	 *					field is returned in a newly
	 *					constructed GameTree object
	 *	Informally: return the GameTree object's child, throw
	 *					an exception if the tree is empty
	 *
	 *	@return GameTree the eldest child of the current node
	*/
	public GameTree getChild() throws EmptyGameTreeException
	{
		GameTree r;

		trace("getChild: getChild starts");

		if (isEmpty())
		{
			trace("getChild: empty game tree");
			throw new EmptyGameTreeException();
		}

		// need to create a game tree to hold the answer
		r=new GameTree();
		r.root=root.getChild();

		trace("getChild: getChild ends");
		return r;
	}


	/**
	 * 	getSibling
	 *	Get method for "root" instance variable's sibling value.
	 *	Pre-condition: none
	 *	Post-condition: the value of the GameTree object's sibling
	 *					field is returned in a newly
	 *					constructed GameTree object
	 *	Informally: return the GameTree object's sibling, throw
	 *					an exception if the tree is empty
	 *
	 *	@return GameTree the next sibling of the current node
	*/
	public GameTree getSibling() throws EmptyGameTreeException
	{
		GameTree r;

		trace("getSibling: getSibling starts");

		if (isEmpty())
		{
			trace("getSibling: empty game tree");
			throw new EmptyGameTreeException();
  		}

		// need to create a game tree to hold the answer
		r = new GameTree();
		r.root = root.getSibling();

		trace("getSibling: getSibling ends");
		return r;
	}


	/**
	 *	setData
	 *	Set method for "root" instance variable's data field.
	 *	Pre-condition: none
	 *	Post-condition: the TNode object's data field is altered to
	 *					hold the given (o) value
	 *	Informally: store the given value in the root node of the
	 *				GameTree object, throw an exception if the tree is
	 *				empty
	 *
	 *	@param o Object to install as data for root node
	*/
	public void setData(Object o) throws EmptyGameTreeException
	{
		trace("setData: setData starts");

		if (isEmpty())
		{
			trace("setData: empty game tree");
			throw new EmptyGameTreeException();
		}

		this.root.setData(o);
		trace("setData: setData ends");
	}


	/**
	 *	setRandomness
	 *	Set method for "moveRandomly" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the moveRandomly field is altered to
	 *					hold the given (r) value
	 *	Informally: record whether move selection will be random
	 *				if multiple moves of equal worth exist
	 *
	 *	@param r whether the best move should be selected at random or not
	*/
	public void setRandomness(boolean r)
	{
		trace("setRandomness: setRandomness starts");

		GameTree.moveRandomly = r;

		trace("setRandomness: setRandomness ends");
	}


	/**
	 *	setSymmetries
	 *	Set method for "symmetryAllowed" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the symmetryAllowed field is altered to
	 *					hold the given (s) value
	 *	Informally: record whether symmetrically equivalent moves
	 *					should be added to the game tree
	 *
	 *	@param s whether symmetrical moves should be considered or not
	*/
	public void setSymmetries(boolean s)
	{
		trace("setSymmetries: setSymmetries starts");

		GameTree.symmetryAllowed = s;

		trace("setSymmetries: setSymmetries ends");
	}


	/**
	 *	setLevel
	 *	Set method for "root" instance variable's level field.
	 *	Pre-condition: none
	 *	Post-condition: the TNode object's level field is altered
	 *					to hold the given (l) value
	 *	Informally: assign the given value as the level of the
	 *				GameTree object, throw an exception if the tree is
	 *				empty
	 *
	 *	@param l level number for root of current game tree
	*/
	public void setLevel(int l) throws EmptyGameTreeException
	{
		trace("setLevel: setLevel starts");

		if (isEmpty())
		{
			trace("setLevel: empty game tree");
			throw new EmptyGameTreeException();
  		}

		this.root.level = l;

		trace("setLevel: setLevel ends");
	}


	/**
	 *	setChild
	 *	Set method for "root" instance variable's child field.
	 *	Pre-condition: none
	 *	Post-condition: the TNode object's child field is altered
	 *					to hold the given (t) value
	 *	Informally: assign the given value as the child of the
	 *				GameTree object, throw an exception if the tree is
	 *				empty
	 *
	 *	@param t GameTree to be set as eldest child of current game tree
	*/
	public void setChild(GameTree t) throws EmptyGameTreeException
	{
		trace("setChild: setChild starts");

		if (isEmpty())
		{
			trace("setChild: empty game tree");
			throw new EmptyGameTreeException();
  		}

		root.setChild(t.root);
		trace("setChild: setChild ends");
	}


	/**
	 *	setSibling
	 *	Set method for "root" instance variable's sibling field.
	 *	Pre-condition: none
	 *	Post-condition: the TNode object's sibling field is altered
	 *					to hold the given (t) value
	 *	Informally: assign the given value as the sibling of the
	 *				GameTree object, throw an exception if the tree is
	 *				empty
	 *
	 *	@param t GameTree to be set as next sibling of current game tree
	*/
	public void setSibling(GameTree t) throws EmptyGameTreeException
	{
		trace("setSibling: setSibling starts");

		if (isEmpty())
		{
			trace("setSibling: empty game tree");
			throw new EmptyGameTreeException();
		}

		root.setSibling(t.root);
		trace("setSibling: setSibling ends");
	}



	/**
	 *	generateLevelDF
	 *	Generate the next level of the tree
	 *	Pre-condition: the given tree is defined, the given stack
	 *				   is defined, and the given player represents
	 *				   the current player
	 *	Post-condition: an additional level of possible moves has
	 *					been added to the given game tree and each
	 *					tree node of the new level also has been
	 *					pushed onto the stack.  Each move is for
	 *					the given player if the level number of the
	 *					level is even, and for its opponent
	 *					otherwise.  Each grid in the new level has
	 *					a value calculated from the opponent's
	 *					perspective
	 *	Informally: generate the next level of the game tree
	 *
	 *	@param s Stack of reachable but unexpanded game trees
	 *	@param curr current Player
	 *
	 * Instructions for generateLevelDF: if game not over and square not occupied, clone the grid, occupy the square, evaluate the grid, create a new tree, link it to the current tree as a child or sibling, push the new tree onto the stack
	*/
	public void generateLevelDF(Stack s,Player curr)
	{
		assert ((s!=null) && (curr!=null));

		trace("generateLevelDF: generateLevelDF starts");

//COMPLETE ME

	TNode t = this.root;
    Grid g = (Grid) t.getData();
    //System.out.println(g.toString());
    Symbol empty = new Symbol();

    //System.out.println("Generating level " + (getLevel() + 1));

    int dimension = g.getDimension();
    for (int i = 1; i <= dimension; i++) {
        for (int j = 1; j <= dimension; j++) {
            Location l = new Location(i, j);
            if (g.getSymbol(l).equals(empty) && !g.gameOver()) {
                Grid newGrid = (Grid) g.clone();
                newGrid.occupySquare(l, curr.opponent().getSymbol());

                TNode newTNode = new TNode(newGrid, getLevel() + 1);
                if (this.root.getChild() == null) {
                    this.root.setChild(newTNode);
                } else {
                    TNode sibling = this.root.getChild();
                    while (sibling.getSibling() != null) {
                    sibling = sibling.getSibling();
                    }
                    sibling.setSibling(newTNode);
                }
				GameTree newTree = new GameTree(newTNode, getLevel() + 1);
                Node newNode = new Node(newTree);
                s.push(newNode);
            }
        }
    }
		trace("generateLevelDF: generateLevelDF ends");
	}



	/**
	 *	buildGameDF
	 *	Generate the game tree in a depth-first manner
	 *	Pre-condition: the current tree isn't empty, the given stack
	 *				   is defined, the given player represents the
	 *				   current player, and the given int value
	 *				   represents the desired depth of the tree
	 *	Post-condition: If the given tree is not already deep enough,
	 *				    if there are children, then these are traversed
	 *					and all siblings are pushed onto the stack.  If
	 *					(when) there are no children an additional
	 *					level of possible moves is added to the given
	 *					game tree and each tree node of the new level
	 *					also is pushed onto the stack.  Finally, the
	 *					next tree is determined by removing the top of
	 *					the stack and the process continues until the
	 *					stack is empty
	 *	Informally: generate the game tree from the current point
	 *				in a depth-first manner until it is "d" levels
	 *				deep
	 *
	 *	@param s Stack of reachable but unexpanded game trees
	 *	@param curr current Player
	 *	@param d desired depth (number of moves ahead) that game tree should be built to
	 *
	 * Instructions for buildGameDF: while the game tree is not deep enough, generate the next level of the tree. If the stack is not empty, pop the top tree from the stack and repeat the process
	*/
	public void buildGameDF(Stack s, Player curr, int d)
	{
		GameTree t;
		Node n;

		assert ((!isEmpty()) && (s!=null) && (curr!=null) && (d>0));

		trace("buildGameDF: buildGameDF starts");
		// TNode tNode = this.root;
		// Grid g = (Grid) tNode.getData();

		generateLevelDF(s, curr);

		if (this.root.getChild() != null) {
			System.out.println("Has a child");
		}
		TNode sibling = this.root.getChild();
		while (sibling.getSibling() != null) {
			System.out.println("Child has a sibling");
			sibling = sibling.getSibling();
		}

		while (getLevel() < d) {

			// GameTree c = getChild();
			// if (!c.isEmpty()) {
			// 	c.buildGameDF(s, curr.opponent(), d);
			// 	this.setChild(c);
			// }
			// GameTree sib = getSibling();
			// if (!s.isEmpty()) {
			// 	sib.buildGameDF(s, curr, d);
			// 	this.setSibling(sib);
			// }

			if (!s.isEmpty())
			{
				n = (Node) s.top();
				s.pop();
				t = (GameTree) n.getData();

			}
			this.setLevel(getLevel() + 1);
			curr = curr.opponent();
		}

		System.out.println("\n\n\n\nGame tree: " + this.toString()+"\n\n\n\nend of tree");

		trace("buildGameDF: buildGameDF ends");
	}


	/**
	 *	generateLevelBF
	 *	Generate the next level of the tree
	 *	Pre-condition: the given tree is defined, the given queue
	 *				   is defined, and the given player represents
	 *				   the current player
	 *	Post-condition: an additional level of possible moves has
	 *					been added to the given game tree and each
	 *					tree node of the new level also has been
	 *					appended to the queue.  Each move is for
	 *					the given player if the level number of the
	 *					level is even, and for its opponent
	 *					otherwise.  Each grid in the new level has
	 *					a value calculated from the opponent's
	 *					perspective
	 *	Informally: generate the next level of the game tree
	 *
	 *	@param q Queue of reachable but unexpanded game trees
	 *	@param curr current Player
	*/
	public void generateLevelBF(Queue q,Player curr)
	{

		assert ((q!=null) && (curr!=null));

		trace("generateLevelBF: generateLevelBF starts");

//COMPLETE ME

		trace("generateLevelBF: generateLevelBF ends");
	}


	/**
	 *	buildGameBF
	 *	Generate the game tree in a breadth-first manner
	 *	Pre-condition: the current tree isn't empty, the given queue
	 *				   is defined, the given player represents the
	 *				   current player, and the given int value
	 *				   represents the desired depth of the tree
	 *	Post-condition: If the given tree is not already deep enough,
	 *				    if there are children, then these are traversed
	 *					and all siblings are added to the queue.  If
	 *					(when) there are no children an additional
	 *					level of possible moves is added to the given
	 *					game tree and each tree node of the new level
	 *					also is added to the queue.  Finally, the next
	 *					tree is determined by removing the front of
	 *					the queue and the process continues until the
	 *					queue is empty
	 *	Informally: generate the game tree from the current point
	 *				in a breadth-first manner until it is "d" levels
	 *				deep
	 *
	 *	@param q Queue of reachable but unexpanded game trees
	 *	@param curr current Player
	 *	@param d desired depth (number of moves ahead) that game tree should be built to
	*/
	public void buildGameBF(Queue q, Player curr, int d)
	{
		GameTree t;

		assert ((!isEmpty()) && (q!=null) && (curr!=null) && (d>0));

		trace("buildGameBF: buildGameBF starts");

//COMPLETE ME

		trace("buildGameBF: buildGameBF ends");
	}


	/**
	 *	symmetry
	 *	Determine whether the given board is a symmetry of those
	 *	already in the siblings of the given game tree
	 *	Pre-condition: the given board is defined
	 *	Post-condition: true is returned if the given board is a
	 *					symmetry of the boards on the child
	 *					level of the current tree, and false
	 *					otherwise
	 *	Informally: find out if this move is a mirror-image of
	 *				those already known
	 *
	 *	@param g Grid to search for symmetries of
	 *	@return boolean whether or not there are symmetries in the game tree
	*/
	protected boolean symmetry(Grid g)
	{
	  	boolean symm,hSymm,vSymm,lSymm,rSymm;
	  	int r1,c1,r2,c2;
	  	Location l1,l2;
	  	int d;
	  	GameTree t;

  		assert (g!=null);

		trace("symmetry: symmetry starts");

  		t=getChild();

	  	if (t.isEmpty())
	  	{
			// parent can't be a symmetry of a child if there are no children
	    	return false;
	    }
	  	else
	  	{
			// check all the children to determine if parent is a symmetry
	  		d=g.getDimension();
	    	symm=false;
	    	while ((! symm) && (! t.isEmpty()))
	    	{
	      		hSymm=vSymm=lSymm=rSymm=true;
	      		for (r1=1; r1<=d; r1++)
	      		{
	        		for (c1=1; c1<=d; c1++)
	        		{
	        			l1=new Location(r1,c1);

	          			r2=r1;
	          			c2=d-c1+1;
	          			l2=new Location(r2,c2);

	          			if (! ((Grid) t.getData()).getSymbol(l1).equals(g.getSymbol(l2)))
						{
	            			vSymm=false;
						}

	          			r2=d-r1+1;
	          			c2=c1;
	          			l2=new Location(r2,c2);
	          			if (! ((Grid) t.getData()).getSymbol(l1).equals(g.getSymbol(l2)))
						{
	            			hSymm=false;
						}

	          			r2=c1;
	          			c2=r1;
	          			l2=new Location(r2,c2);
	          			if (! ((Grid) t.getData()).getSymbol(l1).equals(g.getSymbol(l2)))
						{
	            			lSymm=false;
						}

	          			r2=d-c1+1;
	          			c2=d-r1+1;
	          			l2=new Location(r2,c2);
	          			if (! ((Grid) t.getData()).getSymbol(l1).equals(g.getSymbol(l2)))
						{
	            			rSymm=false;
						}
	        		}
	        	}

	      		if (hSymm||vSymm||lSymm||rSymm)
				{
	        		symm=true;
				}
	      		else
				{
	        		symm=false;
				}

	      		t=t.getSibling();
	    	}

			trace("symmetry: symmetry ends");
	    	return symm;
		}
	}


	/**
	 *	adjustLevel
	 *	Adjust the level numbers of the tree
	 *	Pre-condition: none
	 *	Post-condition: all level numbers in the tree are reduced
	 *					by one
	 *	Informally: decrement the level number of all nodes in
	 *				the game tree
	*/
	protected void adjustLevel()
	{
		trace("adjustLevel: adjustLevel starts");

		// traverse the entire tree, reducing the level value of every node by 1
  		if (! isEmpty()) {
    		getChild().adjustLevel();
    		getSibling().adjustLevel();
    		trace("adjustLevel: setting level to " + (getLevel()-1));
    		setLevel(getLevel()-1);
  		}

		trace("adjustLevel: adjustLevel ends");
	}


	/**
	 *	chooseBest
	 *	Find the value (and frequency) of the best move in the tree
	 *	Pre-condition: none
	 *	Post-condition: the value of the best move that can
	 *					be made is returned
	 *	Informally: examine the next alternate moves and return
	 *				the value (and frequency) of the best one
	 *
	 *	@return double signed whole number indicates worth of best move
	 *				and fraction indicates frequency
	*/
	protected double chooseBest()
	{
  		int i;
  		int f;
  		int v;
  		int d;
  		double a;
  		GameTree t;

		trace("chooseBest: chooseBest starts");
		trace("chooseBest: next move should have worth of " + ((Grid)getData()).getWorth());

  		v=(int)(((Grid)getData()).getWorth());
  		d=((Grid)getData()).getDimension();
  		f=1;
  		trace("chooseBest: starting with a worth of " + v + " and a frequency of " + f);

		// search all siblings for the best value and determine how many equal-valued alternatives there are
  		t=this;
  		while (! t.getSibling().isEmpty())
  		{
    		t=t.getSibling();
    		i=(int)(((Grid)t.getData()).getWorth());
    		trace("chooseBest: considering a worth of " + i);

    		if (i == v)
    		{
	    		trace("chooseBest: found another, frequency is now " + i);
    			f++;
    		}

   			if (t.getLevel() % 2 != 0)
   			{
       			if (i > v)
       			{
	    			trace("chooseBest: found a better worth of " + v);
          			v=i;
          			f=1;
          		}
      		}
      		else
      		{
        		if (i < v)
        		{
	    			trace("chooseBest: found a better worth of " + v);
          			v=i;
          			f=1;
          		}
      		}
  		}
  		trace("chooseBest: best worth is " + v + " and a frequency of " + f);

		// combine value and frequency into a single double, and negate it if the value is negative
  		a=(f / (d * d + 1.0));
  		if (v<0)
  		{
  			a=-a;
  		}

		trace("chooseBest: chooseBest ends with " + (v+a));

  		return v+a;
	}


	/**
	 *	traverse
	 *	Walk over the tree determining the best outcome so far
	 *	Pre-condition: none
	 *	Post-condition: the grids of all nodes in the tree have
	 *					up-to-date values
	 *	Informally: filter the best move values from the leaf
	 *				nodes up to the root
	*/
	public void traverse()
	{
  		double v;
  		GameTree t;
  		Grid b;

		trace("traverse: traverse starts");

		// search the entire game tree for the value of the best move and percolate that up
  		if (! isEmpty())
  		{
    		if (! getChild().isEmpty())
    		{
      			t=getChild();
      			while (! t.isEmpty())
      			{
        			t.traverse();
        			t=t.getSibling();
      			}
      			trace("traverse: siblings traversed");
      			v=getChild().chooseBest();
      			b=(Grid)getData();
      			b.setWorth((int)v);
      			setData(b);
      			trace("traverse: worth of " + v + " set");
    		}
  		}

		trace("traverse: traverse ends");
	}


	/**
	 *	findBest
	 *	Find the best move in the tree for the computer
	 *	Pre-condition: moves exist in the child level of the
	 *				   current tree with a worth of the whole
	 *				   number part of the given double; the
	 *				   frequency of such moves is given by the
	 *				   fractional part of the given double
	 *				   multiplied by the number of squares in
	 *				   the grid
	 *	Post-condition: the tree is adjusted so that the current
	 *					state is overriden by the game tree with
	 *					the best next move as its root (chosen
	 *					at random if permitted, or the first
	 *					encountered otherwise) and all level
	 *					numbers are decremented accordingly
	 *	Informally: computer has a turn!
	 *
	 *	@param s signed whole number indicates worth of best move
	 *				and fraction indicates frequency
	*/
	public void findBest(double s)
	{
  		GameTree t;
  		int v;
  		int f;
  		int c;
		int r;
		int d;
  		boolean found;

  		assert ((! isEmpty()) && (((int)s) != s));

		trace("findBest: findBest starts");

		// given the combined value and frequency of the move to choose from the game tree
		d=((Grid)getData()).getDimension();
		v=(int)s;
		s=Math.abs(s-v);
		f=(int)Math.round(s*(d*d+1));
		r=1;
		// which move will we select
		if (moveRandomly)
		{
			// select it randomly from those of the desired value
			trace("findBest: looking randomly for move #" + r + " out of " + f + " with a worth of " + v);
			r=(int)(Math.random()*f)+1;
		}
		else
		{
			// select the first one we find
			trace("findBest: looking non-randomly for first move with a worth of " + v);
		}

		// go to the next level and find the move we've chosen
  		t=getChild();
  		c=1;
  		found=false;
    	while (!found)
    	{
      		trace("findBest: Move is: " + ((Grid)t.getData()).toString());
    		if (((Grid)t.getData()).getWorth() == v)
      		{
   				trace("findBest: got move #" + c + " of " + f + " (and looking for move #" + r + ")");
     			if (c == r)
      			{
      				trace("findBest: stopping search");
      				found=true;
      			}
      			else
      			{
      				trace("findBest: going for another of this worth");
	 	      		t=t.getSibling();
	 	      		c++;
      			}
      		}
      		else
      		{
	      		trace("findBest: not this move, trying sibling");
 	      		t=t.getSibling();
     		}
  		}

		// this should now be the root of game tree, so delete the redundant parent and prune all siblings
  		trace("findBest: found it; cutting off remaining siblings and adjusting");
        t.setSibling(new GameTree());
    	t.adjustLevel();

    	root=t.root;

		trace("findBest: findBest ends");
	}


	/**
	 *	findMove
	 *	Find the nominated move in the tree (located at the top and as one child)
	 *	Pre-condition: none
	 *	Post-condition: the tree is adjusted so that the current
	 *					state is overriden by the game tree with
	 *					the grid as specified in the parameter as
	 *					its root and all level numbers are
	 *					decremented accordingly
	 *	Informally: find the move the human just made!
	*/
	public void findMove()
	{
	  	GameTree t;

		trace("findMove: findMove starts");

		// go down to the child level and find the move in the game tree that matches the user's move
	  	t=getChild();
	  	if (! t.isEmpty())
	  	{
	    	while ((! t.isEmpty()) && (! ((Grid) t.getData()).equals((Grid) getData())))
	      	{
	      		trace("findMove: not this move, trying sibling");
	      		t=t.getSibling();
      		}

	    	if (t.isEmpty())
	    	{
				// hmmm something went wrong, that just shouldn't happen
	    		trace("findMove: not there, creating empty tree as sentinel");
				t=new GameTree();
	    	}
	    	else
	    	{
				// this should now be the root of game tree, so delete the redundant parent and prune all siblings
				trace("findMove: found it, cutting off remaining siblings and adjusting");
	      		t.setSibling(new GameTree());
	      		t.adjustLevel();
	    	}
	  	}

		root=t.root;
		trace("findMove: findMove ends");
	}


	/**
	 *	rootNodeToString
	 *	String conversion for root node value
	 *	Pre-condition: none
	 *	Post-condition: a String object is returned consisting of the
	 *				String representation of the value within the
	 *				root node, followed by " " or "<>" if the GameTree
	 *				object is the empty tree
	 *	Informally: produce a String representation of the tree's root
	 *				node
	 *
	 *	@return String printable equivalent of root node contents
	*/
	protected String rootNodeToString()
	{
		String s="";

		trace("rootNodeToString: rootNodeToString starts");

		if (isEmpty())
		{
			s="<>";
		}
		else
		{
			s+=(getData().toString() + " ");
		}

		trace("rootNodeToString: rootNodeToString ends");
		return s;
	}


	public String toString()
	/**
	 *	toString
	 *	String conversion for tree
	 *	Pre-condition: none
	 *	Post-condition: a String object is returned consisting of the
	 *				String representation of all items in the GameTree,
	 *				from top to bottom in depth-first order, separated by
	 *				" " and contained within "<" and ">"
	 *	Informally: produce a String representation of the Stack
	 *
	 *	@return String printable contents of game tree
	*/
	{
		GameTree c;
		String s="";

		trace("toString: toString starts");

		if (isEmpty())
		{
			trace("toString: toString ends");
			s="<>";
		}
		else
		{
			s+=rootNodeToString();
			c=getChild();
			if (! c.isEmpty())
			{
				s+="\nchild"+c.toString();
			}
			c=getSibling();
			if (! c.isEmpty())
			{
				s+="\nsibling"+c.toString();
			}
		}

		trace("toString: toString ends");
		return s;
	}


	/**
	 *	trace
	 *	Provide trace output.
	 *	Pre-condition: none
	 *	Post-condition: if trace output is desired then the given String
	 *					parameter is shown on the console
	 *	Informally: show the given message for tracing purposes
	 *
	 *	@param s String to display as tracing message
	*/
	protected void trace(String s)
	{
		if (TRACING)
		{
			System.out.println("GameTree: " + s);
		}
	}
}
