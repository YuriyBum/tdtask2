package tandemservicetask1;

import java.util.ArrayList;
import java.util.List;


public class tdstask1 {
	
	
	//<h3>Вспомогательный класс для проверки реализации с тестовыми входными данными</h3>
	
	public static void main(String[] args) {
				
		Task1Impl task = new Task1Impl();
		//<p>Тестовый входной надор данных</p>
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
			case 4: {String[] e = {"" , "Шоколадка 100г 438 ккал", "Барбарис 200г 800ккал", "Шоколадка 50г шоколадная 5г шоколад 50г начинка", "Чебуречная Водка",null, "12137", "60000", 
					"8 Мой дядя самый честный 4652 года выпуска плавал 34 раза 7 раз тонул 12 раз спасся. 6152 дня бегал 7583 дня летал", 
					"8 Мой папа честный 8137 года выпуска плавал 52 раза 9 раз тонул 11 раз спасся. 1723 дня бегал 92 дня летал",
					"8 Мой брат честный 9188 года выпуска плавал 67 раза 56 раз тонул 5 раз спасся. 7445 дня бегал 92 дня летал",
					"8 Я же честный 8137 года выпуска плавал 52 раза 9 раз тонул 11 раз спасся. 1482 дня бегал 92 дня летал",
					"8 Я же честный 9188 года выпуска плавал 67 раза 56 раз тонул 5 раз спасся. 1923 дня бегал 92 дня летал",
					"Водка Чебуречная", null
			};
			testList.add(e);}
			break;
			case 5: {String[] e = {"Самаритянин 86 лет родился в 1886 году, нашел в себе силы думать" , "12345", "jrg 5 ok 32", "49687498", "","", "xcom 5 ok", "givan ok", null, null, null, null, null, null, null};
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
		
	//<p>Проверяем геттер</p>
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
		
	//<p>Проверка разделителя</p>	 

	}
	


}
