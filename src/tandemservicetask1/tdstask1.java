package tandemservicetask1;

import java.util.ArrayList;
import java.util.List;


public class tdstask1 {
	
	
	//<h3>��������������� ����� ��� �������� ���������� � ��������� �������� �������</h3>
	
	public static void main(String[] args) {
				
		Task1Impl task = new Task1Impl();
		//<p>�������� ������� ����� ������</p>
		List<String[]> testList = new ArrayList<String[]>();
		
		
		for (int i = 0; i<7;i++) {
			switch (i){
			case 0: {String[] e = {"Organization 584 test1", "test2", "test3 ", null, "come to me","", "deg42k", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;
			case 1: {String[] e = {"tog1", null, "", null, "45bfg","", "gothic", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;
			case 2: {String[] e = {null, "organize", "apple3", null, "","", "xcom 5 ok", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;
			case 3: {String[] e = {"Location con" , "Sheduled 568 units", "jrg 5 ok 32", "Attached 13 units", "","", "xcom 5 ok", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;
			case 4: {String[] e = {"" , "��������� 100� 438 ����", "�������� 200� 800����", "��������� 50� ���������� 5� ������� 50� �������", "���������� �����",null, "12137", "60000", 
					"8 ��� ���� ����� ������� 4652 ���� ������� ������ 34 ���� 7 ��� ����� 12 ��� ������. 6152 ��� ����� 7583 ��� �����", 
					"8 ��� ���� ������� 8137 ���� ������� ������ 52 ���� 9 ��� ����� 11 ��� ������. 1723 ��� ����� 92 ��� �����",
					"8 ��� ���� ������� 9188 ���� ������� ������ 67 ���� 56 ��� ����� 5 ��� ������. 7445 ��� ����� 92 ��� �����",
					"8 � �� ������� 8137 ���� ������� ������ 52 ���� 9 ��� ����� 11 ��� ������. 1482 ��� ����� 92 ��� �����",
					"8 � �� ������� 9188 ���� ������� ������ 67 ���� 56 ��� ����� 5 ��� ������. 1923 ��� ����� 92 ��� �����",
					"����� ����������", null
			};
			testList.add(e);}
			break;
			case 5: {String[] e = {"����������� 86 ��� ������� � 1886 ����, ����� � ���� ���� ������" , "12345", "jrg 5 ok 32", "49687498", "","", "xcom 5 ok", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;	
			case 6: {String[] e = {"Location con" , "Sheduled 568 units", "jrg 5 ok 32", "42", "","", "xcom 5 ok", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;
			case 7: {String[] e = {"Location con" , "41,18", "jrg 5 ok 32", "Something 32 short", "sdfgh","", "xcom 5 ok", "givan ok", null, null, null, null, null, null, null};
			testList.add(e);}
			break;
			}
			
		}
		
		task.sort(testList, 4);
		
	//<p>��������� ������</p>
try {
		 for (String[] ob : task.getSortedRows()) { 
			 for (String s : ob) {
		 System.out.print(s + ", "); 
		 } 
		 System.out.println("\n");
		 }
} catch (Exception e) {
	System.err.println(e);
}
		
	//<p>�������� �����������</p>	 

	}
	


}
