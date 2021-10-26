import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String typeCourse;
		float typeScore;
		int choice;
		
		String type;
		String typeNumber;
		String typeName;
		String typeEmail;
		String typeDepartment;
		String typeNumStu;
		
		Scanner scan=new Scanner(System.in);
		LinkedList<Grade> Record=new LinkedList<Grade>();
		LinkedList<Student> StudentRecord=new LinkedList<Student>();
		
		System.out.println("Please enter the address of data.txt (ex.C:＼example＼data.txt)"); //輸入資料檔案的位置
		System.out.print("Address : ");

		try(Scanner input=new Scanner(Paths.get(scan.nextLine())))     //由輸入的檔案位置找到資料並且匯入系統，以利接下來的操作
		{
			for(int x=0;x<6;x++)
				type=input.next();
			while(input.hasNext())
			{
				typeNumber=input.next();
				typeName=input.next();
				typeEmail=input.next();
				typeDepartment=input.next();
				typeCourse=input.next();
				typeScore=input.nextFloat();				
				int c=0;
				Grade AGrade=new Grade(typeCourse,typeScore);
				Student AStudent=new Student(typeNumber,typeName,typeEmail,typeDepartment);
				for(int j=0;j<StudentRecord.size();j++)                            
				{
					
						if(StudentRecord.get(j).number.contains(typeNumber))  //先前有的學生資料就再加上新的成績資料
						{
							c=1;
							StudentRecord.get(j).StudentGrade.add(AGrade);
						}					
						else
						{
						}
				}
				if(c==0)           //第一次出現的學生資料直接在學生的基本資料建檔，連同成績一起
				{
					StudentRecord.add(AStudent);
							StudentRecord.get(StudentRecord.size()-1).StudentGrade.add(AGrade);
				}
				
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		do {       //系統程式開始執行，總共有12種選項提供給使用者選擇，其中包括學生資料和課堂成績上的新增、刪除、修改、查詢，還有其他額外的功能
		    float average=0;
		    float Hbound=0,Lbound=0;
			int check=0;
			
			System.out.println("(1)Add Course Scores (2)Search Someone's Scores (3)Add Student information "
					+ "(4)Print Student information\n(5)List of designated average of the course (6)Make a designated fail list "
					+ "(7)Delete Someone's information \n(8)Delete Someone's designated grade (9)Revise someone's information "
					+ "(10)Revise someone's grade \n(11)Print all student record that score already have record  (12)Exit");
			System.out.print("Please choose which action that you want to take : ");
			choice=scan.nextInt();
			if(choice==1)//將新成績登錄到某個學生的紀錄中
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)
				{
					if(StudentRecord.get(j).number.contains(typeNumStu))
					{
						check=1;
						System.out.println("Success!");
						System.out.println("Please enter the course name and score that you want to record :");
						System.out.print("Type Course Name : ");
						typeCourse=scan.next();
						System.out.print("Type Score : ");
						typeScore=scan.nextFloat();			
						Grade Agrade=new Grade(typeCourse,typeScore);
						StudentRecord.get(j).StudentGrade.add(Agrade);
					}
						
				}
				
				if(check!=1)
				{
					System.out.println("Wrong entry!!!Please check again.");
					
				}
				
			}
			
			
			else if(choice==2)//搜尋某人的基本資料與各科修課成績
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)
				{
					if(StudentRecord.get(j).number.contains(typeNumStu))
					{
						check=1;
						
						System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
						System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(j).number,
								StudentRecord.get(j).name,StudentRecord.get(j).email,StudentRecord.get(j).department);
						System.out.println("");
						
						System.out.println("Course"+"\t\t"+"Score");
						for(int z=0;z<StudentRecord.get(j).StudentGrade.size();z++)
							{
								System.out.println(StudentRecord.get(j).StudentGrade.get(z).course_name+"\t\t"+StudentRecord.get(j).StudentGrade.get(z).score);
							}
						System.out.println("-----------------------------------------------------------");
					}
						
				}
				
				if(check!=1)
				{
					System.out.println("Wrong entry!!!Please check again.");
					
				}
				
			}
			
			
			else if(choice==3)//新增學生資料
			{
				System.out.println("Please enter the student name and informations that you want to record :");
				System.out.print("Type Number : ");
				typeNumber=scan.next();
				System.out.print("Type Name : ");
				typeName=scan.next();
				System.out.print("Type Email : ");
				typeEmail=scan.next();
				System.out.print("Type Department : ");
				typeDepartment=scan.next();
				Student AStudent=new Student(typeNumber,typeName,typeEmail,typeDepartment);
				StudentRecord.add(AStudent);
			}
			else if(choice==4)//列出現在有的學生資料
			{
				System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
				for(int i=0;i<StudentRecord.size();i++)
					System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(i).number,
				StudentRecord.get(i).name,StudentRecord.get(i).email,StudentRecord.get(i).department);
				System.out.println("-----------------------------------------------------------");
			}
				
			
				
			
			else if(choice==5)//輸入平均成績的上下界來尋找成績有符合的學生，並且印出他們資料
			{
				System.out.println("Please enter the boundary of high and low bound of score that you want to search :");
				
				System.out.print("Low Bound : ");
				Lbound=scan.nextFloat();
				System.out.print("High Bound : ");
				Hbound=scan.nextFloat();
				for(int a=0;a<StudentRecord.size();a++) //從每個學生資料中去查曾經修過的課程成績，用average當作計算平均分數的變數，並由使用者輸入的查詢上、下界來找是否有吻合的修課成績紀錄
				{
					for(int b=0;b<StudentRecord.get(a).StudentGrade.size();b++)
					{
						average+=StudentRecord.get(a).StudentGrade.get(b).score;
					}
					average/=StudentRecord.get(a).StudentGrade.size();
					if(average>=Lbound&&average<=Hbound)
					{
						System.out.println("Number"+"\t\t"+"Name"+"\t"+"Average");
						System.out.format("%-16s%-8s%-5s\n",StudentRecord.get(a).number,
						StudentRecord.get(a).name,average);
						System.out.println("");
								
						System.out.println("Course"+"\t\t"+"Score");
						for(int k=0;k<StudentRecord.get(a).StudentGrade.size();k++)
						{
							System.out.println(StudentRecord.get(a).StudentGrade.get(k).course_name+"\t\t"+StudentRecord.get(a).StudentGrade.get(k).score);
						}
						System.out.println("-----------------------------------------------------------");
					}
					average=0;
				}
				
			}
			
			else if(choice==6)//輸入某個課程的名稱，用來尋找該科目前成績不及格的學生
			{
				System.out.print("Please enter course name : ");
				typeCourse=scan.next();
				for(int s=0;s<StudentRecord.size();s++)//從學生的資料中找尋成績紀錄，並找到有修此門輸入課程的成績，如有不及格的就顯示出來
				{
					for(int t=0;t<StudentRecord.get(s).StudentGrade.size();t++)
					{
						if(StudentRecord.get(s).StudentGrade.get(t).course_name.contains(typeCourse))
						{
							if(StudentRecord.get(s).StudentGrade.get(t).score<60)
							{
								System.out.println("Number"+"\t\t"+"Name"+"\t"+"Score");
								System.out.format("%-16s%-8s%-5s\n",StudentRecord.get(s).number,
										StudentRecord.get(s).name,StudentRecord.get(s).StudentGrade.get(t).score);
								System.out.println("-----------------------------------------------------------");
							}
						}
						
					}
					
				}
				
				
			}
			
			else if(choice==7)//刪除某個學生資料
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)//由學生的學號當作找尋資料的依據，如果有該生的資料存在，就可以刪除
				{
					if(StudentRecord.get(j).number.contains(typeNumStu))
					{
						check=1;
						
						System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
						System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(j).number,
								StudentRecord.get(j).name,StudentRecord.get(j).email,StudentRecord.get(j).department);
						System.out.println("");
						
						/*String commit="n";
						System.out.format("Are you sure to delete the information of %s? (y/n) : ",StudentRecord.get(j).number);
						commit=scan.next();
						if(commit=="y")*/						
						StudentRecord.remove(j);
					}
						
				}
				
				if(check!=1)
				{
					System.out.println("Wrong entry!!!Please check again.");
					
				}
			}
			
			else if(choice==8)//刪除某科成績
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++) //由輸入的學號找尋該學生的修課紀錄，並且再由輸入的課程名稱找尋對應的修課成績紀錄，並把該科的成績紀錄刪除
				{
					if(StudentRecord.get(j).number.contains(typeNumStu))
					{
						check=1;
						
						System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
						System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(j).number,
								StudentRecord.get(j).name,StudentRecord.get(j).email,StudentRecord.get(j).department);
						System.out.println("");
						
						System.out.println("Course"+"\t\t"+"Score");
						for(int z=0;z<StudentRecord.get(j).StudentGrade.size();z++)
							{
								System.out.println(StudentRecord.get(j).StudentGrade.get(z).course_name+"\t\t"+StudentRecord.get(j).StudentGrade.get(z).score);
							}
						System.out.println("-----------------------------------------------------------");
						
						System.out.print("Please enter the course name that you want to delete(include grade) : ");
						typeCourse=scan.next();
						
						for(int s=0;s<StudentRecord.size();s++)
						{
							for(int t=0;t<StudentRecord.get(s).StudentGrade.size();t++)
							{
								if(StudentRecord.get(s).StudentGrade.get(t).course_name.contains(typeCourse))
								{
									StudentRecord.get(j).StudentGrade.remove(t);
								}
								
							}
							
						}
					}
						
				}
				
				if(check!=1)
				{
					System.out.println("Wrong entry!!!Please check again.");
					
				}
				
			}
			
			
			else if(choice==9)//把某個學生的基本資料做修改
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)    //先從學號找尋相符的資料，並由使用者重新輸入每個學生會有的四項基本個人資料
				{
					if(StudentRecord.get(j).number.contains(typeNumStu))
					{
						check=1;
						
						System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
						System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(j).number,
								StudentRecord.get(j).name,StudentRecord.get(j).email,StudentRecord.get(j).department);
						System.out.println("");
						
						/*String commit="n";
						System.out.format("Are you sure to delete the information of %s? (y/n) : ",StudentRecord.get(j).number);
						commit=scan.next();
						if(commit=="y")*/			
						
						System.out.println("Please enter the student name and informations that you want to revise :");
						System.out.print("Type Number : ");
						typeNumber=scan.next();
						System.out.print("Type Name : ");
						typeName=scan.next();
						System.out.print("Type Email : ");
						typeEmail=scan.next();
						System.out.print("Type Department : ");
						typeDepartment=scan.next();
						StudentRecord.get(j).name=typeName;
						StudentRecord.get(j).number=typeNumber;
						StudentRecord.get(j).email=typeEmail;
						StudentRecord.get(j).department=typeDepartment;
					}
						
				}
				
				
				
				if(check!=1)
				{
					System.out.println("Wrong entry!!!Please check again.");
					
				}
			}
			
			
			else if(choice==10)//修改某課程成績與名稱
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)//由學生的學號進入到修課紀錄，並由使用者輸入的課程名稱找尋相符的課程紀錄，並由輸入的內容去更改此科目成績或名稱
				{
					if(StudentRecord.get(j).number.contains(typeNumStu))
					{
						check=1;
						
						System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
						System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(j).number,
								StudentRecord.get(j).name,StudentRecord.get(j).email,StudentRecord.get(j).department);
						System.out.println("");
						
						System.out.println("Course"+"\t\t"+"Score");
						for(int z=0;z<StudentRecord.get(j).StudentGrade.size();z++)
							{
								System.out.println(StudentRecord.get(j).StudentGrade.get(z).course_name+"\t\t"+StudentRecord.get(j).StudentGrade.get(z).score);
							}
						System.out.println("-----------------------------------------------------------");
						
						System.out.print("Please enter the course name that you want to revise(include grade) : ");
						typeCourse=scan.next();
						
						for(int s=0;s<StudentRecord.size();s++)
						{
							for(int t=0;t<StudentRecord.get(s).StudentGrade.size();t++)
							{
								if(StudentRecord.get(s).StudentGrade.get(t).course_name.contains(typeCourse))
								{
									System.out.print("Please enter the course name : ");
									typeCourse=scan.next();
									System.out.print("Please enter the score : ");
									typeScore=scan.nextFloat();
									StudentRecord.get(j).StudentGrade.get(t).course_name=typeCourse;
									StudentRecord.get(j).StudentGrade.get(t).score=typeScore;
								}
								
							}
							
						}
					}
						
				}
				
				if(check!=1)
				{
					System.out.println("Wrong entry!!!Please check again.");
					
				}
				
			}
			
			else if(choice==11)//顯示所有修課同學的資料和成績，確認目前的紀錄有如錯誤或做其他進一步的查詢、修改
			{
				System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department"+"\t"+"Course"+"\t"+"Score");
				for(int y=0;y<StudentRecord.size();y++)
				{
					for(int z=0;z<StudentRecord.get(y).StudentGrade.size();z++)
					{
						System.out.format("%-16s%-8s%-24s%-16s%-8s%-5s\n",StudentRecord.get(y).number,
								StudentRecord.get(y).name,StudentRecord.get(y).email,StudentRecord.get(y).department
								,StudentRecord.get(y).StudentGrade.get(z).course_name,StudentRecord.get(y).StudentGrade.get(z).score);
						System.out.println("");
					}
				}
			}
			else if(choice==12)//離開系統
			{
				try(Formatter output=new Formatter("Report.txt"))//離開系統的重要動作是將更改過後的資料紀錄並放在一個檔案中，會有各科修課同學的資料和成績
				{
					output.format("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department"+"\t"+"Course"+"\t"+"Score\n");
					for(int y=0;y<StudentRecord.size();y++)
					{
						for(int z=0;z<StudentRecord.get(y).StudentGrade.size();z++)
						{
							output.format("%-16s%-8s%-24s%-16s%-8s%-5s\n",StudentRecord.get(y).number,
									StudentRecord.get(y).name,StudentRecord.get(y).email,StudentRecord.get(y).department
									,StudentRecord.get(y).StudentGrade.get(z).course_name,StudentRecord.get(y).StudentGrade.get(z).score);
							System.out.println("");
						}
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				System.exit(0);
			}
			
			
			else//輸入的選項沒在設定的範圍內
			{
				System.out.println("Wrong entry!!!Please check again.");
			}
			
		}while(choice!=12);
	}

}
