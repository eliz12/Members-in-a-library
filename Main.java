import java.util.Scanner;

/**
 * in this class i build the trees and also the questions are here
 * @author Eli Z
 *
 */

public class Main {
	
	/**
	 * its the only method in this class;
	 *
	 * 
	 */
	public static void main( String [ ] args ){
		RedBlackTreeMember Members = new RedBlackTreeMember();//build an empty tree
		RedBlackTreeBook Books = new RedBlackTreeBook();//build an empty tree
		Member a[] = new Member[100];
		Book b[] = new Book[100];
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("please press the input ");

		for (int i = 0; i < 25; i++)
		{
			
			input = in.next();
				
			if (input.length() < 24)
			{
				if (input.charAt(0) == '+')
				{
					a[i] = new Member(Integer.parseInt(input.substring(13, 22)), input.substring(2, 9)); 
					Members.add(a[i]);
				}
				else
					Members.remove(Integer.parseInt(input.substring(13, 22)));	//remove the member when the first char is not +
			}
			else
				if (input.charAt(0) == '+'){
					b[i] = new Book(input.substring(23, 29), Members.find(Integer.parseInt(input.substring(13, 22))));
					Books.add(b[i]);
					b[i].getMember().setBook(b[i]);
				}
				else
					Books.remove(input.substring(23, 29));	//remove the book when the first char is not +					
			
		}
		int n ;

		while (true) //it will stop if we press 4
		{
			System.out.println("For question number 1 please press 1, for question number 2 please press 2, for question number 3 please press 3. to stop - please press 4");
			n = in.nextInt();
			if (n==4)
				break;
			if (n ==1)
			{
				System.out.println("please insert the id");
				n = in.nextInt();
				System.out.println(Members.find(n).allBooks());//question 1

			}
			else if (n==2)
			{
				System.out.println("please insert the code of the book");
				input = in.next();
				System.out.println("The ID of the member who has the book "+ input +" is " + Books.find(input).getMember().getId() + " and his name is " + Books.find(input).getMember().getFamily());//question 2

			}
			else if (n==3)
			System.out.println(Members.maxBooks());//question 3

		}
	}

}
