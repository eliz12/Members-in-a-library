

/**
 * This class represents a book.
 * it's includes the code of the book, ad pointer to the member
 * 
 * @author Eli
 *
 */

public class Book {

	
	private String _code;
	private Member _member;
	
	
	
	/** 
	 * This is a constructor for this class that recieves the code of the book and the member
	 * and gives their values to the matching instance variables. 
	 */
	public Book(String code, Member member) {
		_code = code;
		_member = member;	
	}
	
	
	/**
	 * Return the code of the book
	 */
	public String getCode(){
		return _code;
	}
	
	/**
	 * Return the member of the book
	 */
	public Member getMember(){
		return _member;
	}
	
	
}
