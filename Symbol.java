//KIT107 Assignment 3
/**
 *	Symbol ADT
 *
 *	@author <<Your names and Student IDs>>
 *	@version September 2024
 *
 *	This file holds the Symbol ADT which represents
 *	pieces within the two-dimensional grid.
 *	A Symbol consists of a representation of a nought, cross
 *	or empty.
 *
 *	YOU NEED TO MAKE CHANGES TO THIS FILE!
*/

import java.awt.*;


public class Symbol implements SymbolInterface, Cloneable
{
	// class-level types
	enum SymbolValue {EMPTY, NOUGHT, CROSS};

	// finals (i.e. all objects have their own value)
	protected final boolean TRACING=false;		// do we want to see trace output?

	// properties
	protected SymbolValue symbol;	// empty, nought, or cross


	/**
	 *	Symbol
	 *	Constructor method 1.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object is created empty
	 *	Informally: intialises the instance variable of the newly
	 *				created Symbol object to EMPTY
	*/
	public Symbol()
	{
      	trace("Symbol: Constructor starts");

		this.symbol=SymbolValue.EMPTY;

      	trace("Symbol: Constructor ends");
	}


	/**
	 *	Symbol
	 *	Constructor method 2.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object is created of the specified
	 *					symbol -- CROSS if the parameter is true and
	 *					NOUGHT if the parameter is false
	 *	Informally: intialises the instance variable of the newly
	 *				created Symbol object to the symbol value indicated
	 *
	 *	@param x boolean for whether it should be a CROSS
	*/
	public Symbol(boolean x)
	{
      	trace("Symbol: Constructor starts");

		if (x)
		{
			this.symbol = SymbolValue.CROSS;
		}
		else
		{
			this.symbol = SymbolValue.NOUGHT;
		}

      	trace("Symbol: Constructor ends");
	}


	/**
	 *	Symbol
	 *	Constructor method 3.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object is of the specified symbol
	 *	Informally: intialises the instance variable of the newly
	 *				created Symbol object to the numeric value supplied
	 *
	 *	@param s the enumerated value for EMPTY, NOUGHT, or CROSS
	*/
	protected Symbol(SymbolValue s)
	{
     	trace("Symbol: Constructor starts");

		this.symbol = s;

      	trace("Symbol: Constructor ends");
	}


	/**
	 *	setSymbol
	 *	Set method for "symbol" instance variable.
	 *	Pre-condition: the symbol (s) is valid
	 *	Post-condition: the Symbol object's value is altered to hold
	 *					the given (s) value
	 *	Informally: assign the value of the parameter to the Symbol
	 *				object's symbol instance variable
	 *
	 *	@param s the numeric value for EMPTY, NOUGHT, or CROSS
	*/
	protected void setSymbol(SymbolValue s)
	{
		assert (s != null);

      	trace("setSymbol: setSymbol starts");

		this.symbol = s;

      	trace("setSymbol: setSymbol ends");
	}


	/**
	 *	makeEmpty
	 *	Set method for "symbol" instance variables.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object's value is altered to hold
	 *					EMPTY
	 *	Informally: assign the value of EMPTY to the Symbol object's
	 *					symbol instance variable
	*/
	public void makeEmpty()
	{
      	trace("makeEmpty: makeEmpty starts");

		this.symbol = SymbolValue.EMPTY;

    	trace("makeEmpty: makeEmpty ends");
	}


	/**
	 *	getSymbol
	 *	Get method for "symbol" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object's symbol value is returned
	 *	Informally: examine the Symbol object's symbol instance
	 *					variable returning its value
	 *
	 *	@return int the numeric value for EMPTY, NOUGHT, or CROSS
	*/
	protected SymbolValue getSymbol()
	{
      	trace("getSymbol: getSymbol starts and ends");

		// int result = 0;
		// if (this.symbol == SymbolValue.EMPTY)
		// {
		// 	result = 0;
		// }
		// else if (this.symbol == SymbolValue.NOUGHT)
		// {
		// 	result = 1;
		// }
		// else if (this.symbol == SymbolValue.CROSS)
		// {
		// 	result = 2;
		// }

		return this.symbol;	// CHANGE ME
	}


	/**
	 *	isEmpty
	 *	Doer method for determining if symbol instance variable is EMPTY.
	 *	Pre-condition: none
	 *	Post-condition: the result of comparing the Symbol object's symbol
	 *					instance variable with EMPTY is returned
	 *	Informally: examine the Symbol object's symbol instance variable
	 *					returning whether it is EMPTY
	 *
	 *	@return boolean whether or not the symbol is empty
	*/
	public boolean isEmpty()
	{
      	trace("isEmpty: isEmpty starts and ends");

		return (this.symbol == SymbolValue.EMPTY);	// CHANGE ME
	}


	/**
	 *	compareTo
	 *	Test equality of symbols.
	 *	Pre-condition: parameter is a validly defined Symbol value
	 *	Post-condition: 0 is returned if current symbol and given value
	 *					are identical, +1 otherwise
	 *	Informally: check whether the given symbol has the same icon as
	 *					the current symbol value (including whether both
	 *					are empty)
	 *
	 *	@param o the Symbol to be compared to the current Symbol
	 *	@return int 0 if the symbols are the same 1 otherwise
	*/
	public int compareTo(Object o)
	{
		Symbol s;

 		assert ((o instanceof Symbol) && (o!=null));

     	trace("compareTo: compareTo starts");

		s=(Symbol) o;
		if (getSymbol() == s.getSymbol())
  	    {
			// same value
  	    	trace("compareTo: compareTo ends (0)");
			return 0;
		}
		else
		{
			// different value
  	    	trace("compareTo: compareTo ends (1)");
			return 1;  // order is arbitrary
		}
	}


	/**
	 *	equals
	 *	Test equality of symbols.
	 *	Pre-condition: parameter is a validly defined Symbol value
	 *	Post-condition: true is returned if current symbol and given value
	 *					possess identical icons, false otherwise
	 *	Informally: check whether the given symbol has the same icon as
	 *					the current symbol value
	 *
	 *	@param s the Symbol to be compared to the current Symbol
	 *	@return boolean whether or not the two symbols are the same
	*/
	public boolean equals(Symbol s)
	{
 		assert (s!=null);

      	trace("equals: equals starts and ends");

		boolean result = false;

		if (compareTo(s) == 0)
		{
			result = true;
		}
		else
		{
			result = false;
		}

		return result;
	}


	/**
	 *	opponent
	 *	Provide the opponent to a symbol.
	 *	Pre-condition: none
	 *	Post-condition: the opposite Symbol object is returned or an
	 *					empty symbol if the current Symbol is empty
	 *	Informally: find the opponent's Symbol
	 *
	 *	@return Symbol the 'opposite' of the Symbol
	*/
	public Symbol opponent()
	{
		Symbol s;	// result

      	trace("opponent: opponent starts");

		s=new Symbol();
		switch (getSymbol())
		{
			case CROSS:  s.setSymbol(SymbolValue.NOUGHT);
						 break;
			case NOUGHT: s.setSymbol(SymbolValue.CROSS);
						 break;
			case EMPTY:  break;
		}

      	trace("opponent: opponent ends");
		return s;
	}


	/**
	 *	clone
	 *	Clone a symbol.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object is copied
	 *	Informally: copy the current Symbol
	 *
	 *	@return Object the copy of the Symbol
	*/
	public Object clone()
	{
		Symbol s;	// result

      	trace("clone: clone starts");

		s=new Symbol(getSymbol());

      	trace("clone: clone ends");
		return s;
	}


	/**
	 *	showSymbol
	 *	Display method for Symbol
	 *	Pre-condition: the Screen and Location parameters are
	 *					correctly defined and w is a positive
	 *					integer
	 *	Post-condition: the screen representation of the Symbol
	 *					object is displayed on the given Screen at
	 *					the given location in the grid using the
	 *					Symbol object's colour to display the
	 *					Symbol (of width w pixels)
	 *	Informally: display the current symbol
	 *
	 *	@param s the Display to show the Symbol upon
	 *	@param l the Location to show the Symbol at
	 *	@param w the maximum width/height for the Symbol in pixels
	*/
	public void showSymbol(Display s, Location l, int w)
	{
		int r, c;
		int x, y;
		Polygon p;

		assert ((s!=null) && (l!=null) && (w>0));

      	trace("showSymbol: showSymbol starts");

		r=l.getRow();
		c=l.getColumn();

		switch (symbol) {
		  case EMPTY:  trace("showSymbol: it's empty");
		  			   break;
		  case NOUGHT: trace("showSymbol: it's a nought");
		  			   x=(c-1)*(w+5) + 5 + 3 + 5;
		  			   y=75 + (r-1)*(w+5) + 3;
		  			   s.getGraphics().setColor(new Color(172,172,100));	// rose
		  			   s.getGraphics().fillOval(x,y,w-6,w-6);
		  			   s.getGraphics().setColor(new Color(77,77,77));		// dark grey
		  			   s.getGraphics().fillOval(x+10,y+10,w-26,w-26);
		               break;
		  case CROSS:  trace("showSymbol: it's a cross");
		  			   x=(c-1)*(w+5) + 5 + 5;
		  			   y=75 + (r-1)*(w+5);
		  			   s.getGraphics().setColor(new Color(255,100,100));	// 'tan'
		  			   p=new Polygon();
		  			   p.addPoint(x+10,y+3);
		  			   p.addPoint(x+3,y+10);
		  			   p.addPoint(x+w-10,y+w-3);
		  			   p.addPoint(x+w-3,y+w-10);
		  			   s.getGraphics().fillPolygon(p);
		  			   p=new Polygon();
		  			   p.addPoint(x+w-10,y+3);
		  			   p.addPoint(x+w-3,y+10);
		  			   p.addPoint(x+10,y+w-3);
		  			   p.addPoint(x+3,y+w-10);
		  			   s.getGraphics().fillPolygon(p);
		               break;
		}
		s.getGraphics().setColor(Color.black);

      	trace("showSymbol: showSymbol ends");
	}


	/**
	 *	toString
	 *	Convert the symbol to a printable representation.
	 *	Pre-condition: none
	 *	Post-condition: a String representation of the current
	 *					Symbol is returned
	 *	Informally: find the String equivalent of the current
	 *				symbol ("X" for player with CROSS symbol;
	 *				"O" for player with NOUGHT, " " if symbol
	 *				is EMPTY)
	 *
	 *	@return String the printable representation of the Symbol
	*/
	public String toString()
	{
      	trace("toString: toString starts");

		if (isEmpty())
      	{
      		trace("toString: toString ends (empty)");
			return " ";
		}
		else
		{
			if (this.equals(new Symbol(SymbolValue.CROSS)))
      		{
      			trace("toString: toString ends (X)");
				return "X";
			}
			else
      		{
      			trace("toString: toString ends (O)");
				return "O";
			}
		}
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
			System.out.println("Symbol: " + s);
		}
	}
}
