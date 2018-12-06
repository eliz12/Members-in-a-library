
/**
 * This class represents a member.
 * it's includes ID, last name and array of books
 * 
 * @author Eli
 *
 */

public class Member {

	
	
	/** The id of the member */
	private int _id; 
	
	/** The y-coordinate of the point. */
	private String _family;
	
	
	/** this is the list of the books*/
	private Book[] _book;
	
	/**this is the num of the books*/
	private int _numOfBooks = 0;
	
	/** 
	 * This is a constructor for this class that recieves the id and tha last name of thr member
	 * and gives their values to the matching instance variables. 
	 * 
	 */
	public Member(int id, String string) {
		_id = id;
		_family = string;
		_numOfBooks = 0;
		_book = new Book[10];
	}


	/**
	 * Returns the last name.
	 * @return The last name
	 */
	public String getFamily() {
		return _family;
	}
	/**
	 * Returns the ID.
	 * @return The ID.
	 */
	public int getId() {
		return _id;
	}
	/**
	 * Returns the number of books the member has
	 */
	public int getNumOfBooks(){
		return _numOfBooks;
	}
	
	/**
	 * Returns the num of books
	 */
	public int numOfBooks() {
		return _numOfBooks;
	}	 
	
	
	 /**
	  * Changes the x coordinate of the point.
	  * @param num -  The new x coordinate
	  */
	public void setBook(Book book) {
		for (int i = 0; i < _book.length; i++) {
			if (_book[i]==null){
					_book[i]=book;
					break;
			}
			
		}
		
		_numOfBooks++;
	}

	public void removeBook(String code) {
		for (int i = 0; i < _book.length; i++) {
			if (_book[i]!=null && _book[i].getCode()==code){
					_book[i]=null;
					break;
			}
			
		}
		
		_numOfBooks--;
	}

	
	/**
	 * Return list of the books
	 */
	
	public String allBooks(){
		String a = "the books Mr "+ _family + " has are: ";
		for (int i = 0; i < _book.length; i++) {
			if (_book[i]!=null){
					a= a+_book[i].getCode()+" ";
					
			}
			
		}
		
		
		
		return a;
	}
	
	
	}

	


	

