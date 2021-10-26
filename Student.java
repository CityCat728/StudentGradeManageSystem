import java.util.LinkedList;
import java.util.Scanner;

public class Student {
String number;
String name;
String email;
String department;
LinkedList<Grade> StudentGrade=new LinkedList<Grade>();
public Student(String newNumber,String newName,String newEmail,String newDepartment)
{
	number=newNumber;
	name=newName;
	email=newEmail;
	department=newDepartment;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*int choice;
		Scanner scan=new Scanner(System.in);
		
		
		do {
			System.out.println("Please choose which action do you want to take :");
			System.out.println("(1)Add (2)Print (3)Exit");
			choice=scan.nextInt();
			if(choice==1)
			{
				
			}
			
			else if(choice==2)
			{
				
			}
				
			else if(choice==3)
			{
				System.exit(0);
			}
				
			else
			{
				System.out.println("Wrong entry!!!Please check again.");
			}
		}while(choice!='3');*/
	}

}
