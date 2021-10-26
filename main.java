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
		
		System.out.println("Please enter the address of data.txt (ex.C:�@example�@data.txt)"); //��J����ɮת���m
		System.out.print("Address : ");

		try(Scanner input=new Scanner(Paths.get(scan.nextLine())))     //�ѿ�J���ɮצ�m����ƨåB�פJ�t�ΡA�H�Q���U�Ӫ��ާ@
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
					
						if(StudentRecord.get(j).number.contains(typeNumber))  //���e�����ǥ͸�ƴN�A�[�W�s�����Z���
						{
							c=1;
							StudentRecord.get(j).StudentGrade.add(AGrade);
						}					
						else
						{
						}
				}
				if(c==0)           //�Ĥ@���X�{���ǥ͸�ƪ����b�ǥͪ��򥻸�ƫ��ɡA�s�P���Z�@�_
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
		
		
		
		do {       //�t�ε{���}�l����A�`�@��12�ؿﶵ���ѵ��ϥΪ̿�ܡA�䤤�]�A�ǥ͸�ƩM�Ұ��Z�W���s�W�B�R���B�ק�B�d�ߡA�٦���L�B�~���\��
		    float average=0;
		    float Hbound=0,Lbound=0;
			int check=0;
			
			System.out.println("(1)Add Course Scores (2)Search Someone's Scores (3)Add Student information "
					+ "(4)Print Student information\n(5)List of designated average of the course (6)Make a designated fail list "
					+ "(7)Delete Someone's information \n(8)Delete Someone's designated grade (9)Revise someone's information "
					+ "(10)Revise someone's grade \n(11)Print all student record that score already have record  (12)Exit");
			System.out.print("Please choose which action that you want to take : ");
			choice=scan.nextInt();
			if(choice==1)//�N�s���Z�n����Y�Ӿǥͪ�������
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
			
			
			else if(choice==2)//�j�M�Y�H���򥻸�ƻP�U��׽Ҧ��Z
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
			
			
			else if(choice==3)//�s�W�ǥ͸��
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
			else if(choice==4)//�C�X�{�b�����ǥ͸��
			{
				System.out.println("Number"+"\t\t"+"Name"+"\t"+"Email"+"\t\t\t"+"Department");
				for(int i=0;i<StudentRecord.size();i++)
					System.out.format("%-16s%-8s%-24s%-5s\n",StudentRecord.get(i).number,
				StudentRecord.get(i).name,StudentRecord.get(i).email,StudentRecord.get(i).department);
				System.out.println("-----------------------------------------------------------");
			}
				
			
				
			
			else if(choice==5)//��J�������Z���W�U�ɨӴM�䦨�Z���ŦX���ǥ͡A�åB�L�X�L�̸��
			{
				System.out.println("Please enter the boundary of high and low bound of score that you want to search :");
				
				System.out.print("Low Bound : ");
				Lbound=scan.nextFloat();
				System.out.print("High Bound : ");
				Hbound=scan.nextFloat();
				for(int a=0;a<StudentRecord.size();a++) //�q�C�Ӿǥ͸�Ƥ��h�d���g�׹L���ҵ{���Z�A��average��@�p�⥭�����ƪ��ܼơA�åѨϥΪ̿�J���d�ߤW�B�U�ɨӧ�O�_���k�X���׽Ҧ��Z����
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
			
			else if(choice==6)//��J�Y�ӽҵ{���W�١A�ΨӴM��Ӭ�ثe���Z���ή檺�ǥ�
			{
				System.out.print("Please enter course name : ");
				typeCourse=scan.next();
				for(int s=0;s<StudentRecord.size();s++)//�q�ǥͪ���Ƥ���M���Z�����A�ç�즳�צ�����J�ҵ{�����Z�A�p�����ή檺�N��ܥX��
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
			
			else if(choice==7)//�R���Y�Ӿǥ͸��
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)//�Ѿǥͪ��Ǹ���@��M��ƪ��̾ڡA�p�G���ӥͪ���Ʀs�b�A�N�i�H�R��
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
			
			else if(choice==8)//�R���Y�즨�Z
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++) //�ѿ�J���Ǹ���M�Ӿǥͪ��׽Ҭ����A�åB�A�ѿ�J���ҵ{�W�٧�M�������׽Ҧ��Z�����A�ç�Ӭ쪺���Z�����R��
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
			
			
			else if(choice==9)//��Y�Ӿǥͪ��򥻸�ư��ק�
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)    //���q�Ǹ���M�۲Ū���ơA�åѨϥΪ̭��s��J�C�Ӿǥͷ|�����|���򥻭ӤH���
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
			
			
			else if(choice==10)//�ק�Y�ҵ{���Z�P�W��
			{
				System.out.print("Please enter the number of the student :");
				typeNumStu=scan.next();
				for(int j=0;j<StudentRecord.size();j++)//�Ѿǥͪ��Ǹ��i�J��׽Ҭ����A�åѨϥΪ̿�J���ҵ{�W�٧�M�۲Ū��ҵ{�����A�åѿ�J�����e�h��惡��ئ��Z�ΦW��
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
			
			else if(choice==11)//��ܩҦ��׽ҦP�Ǫ���ƩM���Z�A�T�{�ثe���������p���~�ΰ���L�i�@�B���d�ߡB�ק�
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
			else if(choice==12)//���}�t��
			{
				try(Formatter output=new Formatter("Report.txt"))//���}�t�Ϊ����n�ʧ@�O�N���L�᪺��Ƭ����é�b�@���ɮפ��A�|���U��׽ҦP�Ǫ���ƩM���Z
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
			
			
			else//��J���ﶵ�S�b�]�w���d��
			{
				System.out.println("Wrong entry!!!Please check again.");
			}
			
		}while(choice!=12);
	}

}
