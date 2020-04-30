package tandemservicetask1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

	 // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();
    //<p>Создаем хранилище полученного результата</p>
    private List<String[]> theSortedRow;

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
    	
    	 // <p>Напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного, документированного и понятного кода.</p>
    	 // <p>Реализация риводится ниже.</p>    	
    	 // <p>Для проверки используется класс {@link tdtask1}</p>
    	 class DigitComparator implements Comparator<List<String>> {
						
			private boolean IsStringDigit (String a) throws NumberFormatException {
				
				// <p>Проверяем отдельную строку на предмет состава из цифр, возвращает boolean</p>
				boolean b;	 
				    try {
				        Integer.parseInt(a);
				        b = true;
				    } catch (NumberFormatException e) {
				        b = false;
				    }		
				
				return b;
			}
			
			//<p>Первый этап разбивки строки на фрагменты. Разбивка на части из текста и цифр. Возвращает List<String>. </p>  

			private List<String> SplittedString (String ob) {
				List<String> fragments = new ArrayList();
							
				for (int i = 0; i < ob.length(); i++) {
					if (Character.isDigit(ob.charAt(i))) {
						int k = i;						
						StringBuffer resours = new StringBuffer("");
						if(k > 1)
						resours.append(ob.charAt(k-1));
						
						while(Character.isDigit(ob.charAt(k))) {
						resours.append(ob.charAt(k));
						if (k < (ob.length()-1)) {
							k++;
							} else {
								break;
					} 
						}
						
						fragments.add(resours.toString());
						resours.setLength(0);			
						i = k;
				} else  {
					int k = i;
					StringBuffer resours = new StringBuffer("");
					while(!Character.isDigit(ob.charAt(k))) {
						resours.append(ob.charAt(k));
						if (k < (ob.length()-1)) {
							k++;
							} else {
								break;
							}
				}
					fragments.add(resours.toString());
					resours.setLength(0);			
					i = k;
				}
				}

				return fragments;
				}

			//<p>Второй этап получения массива фрагментов. Возвращает List<String>. Первый этап вызывается внутри второго, поэтому отдельная его запись
			// более не требуется</p>  
			//<p>Символ "." не является цифрой, цифра и число - разные понятия. Для значений "3445" и "31.344523" искомым будет считаться "344523"</p> 
			  private List<String> FinallySplittedString (String ob2) {
				  List<String> ob = SplittedString(ob2);
				  List<String> fragments = new ArrayList();
				  StringBuffer combine = new StringBuffer();
				  List<Integer> buffer = new ArrayList();

				  int maxnumbers = 0;
				  for(int i = 0; i < ob.size(); i++) {
					  if(IsStringDigit(ob.get(i))) {
						  buffer.add(Integer.parseInt(ob.get(i)));
					  }
				  }
				  
				  if(buffer.size() > 0) {
					  maxnumbers = (int)Math.log10(Collections.max(buffer))+1;
				  } 
				  
				  for(int i = 0; i < ob.size(); i++) {
					  if(!IsStringDigit(ob.get(i))) {
						  combine.append(ob.get(i));
					  } else if (ob.get(i).length() == maxnumbers) {
						 if(combine.length()>0) fragments.add(combine.toString());
						  fragments.add(ob.get(i));
						combine.setLength(0);
					  } else {
						  combine.append(ob.get(i) + " ");
					  }
				  }
				  if(combine.length()>0) fragments.add(combine.toString());
				  combine.setLength(0);
				  Collections.sort(fragments, new MonoComparator());
				return fragments;
				  	  
			  }
			  
				class MonoComparator implements Comparator<String> {
					@Override
					public int compare(String o1, String o2) throws IndexOutOfBoundsException {
						
						// Используется для сортировки фрагментов строки под фильтр
						int r = 0;
						if (IsStringDigit(o1) && IsStringDigit(o2)) {
						r = 20;
						}
						if (!IsStringDigit(o1) && IsStringDigit(o2)) {
							r = 10;
							}
						if (IsStringDigit(o1) && !IsStringDigit(o2)) {
							r = -10;
							}
						if (!IsStringDigit(o1) && !IsStringDigit(o2)) {
							r = 0;
							}
						
						return r;
					}

				}

	    	/*
	    	 * <p>Метод предполагает поэтапное сравнение фрагментов строки. Приоритет наиболее</p>
	    	 * <p>длинного фрагмента из цифр (наибольший максимальный непрерывный фрагмент). Последующий фрагмент</p>
	    	 * <p>влияет на ранжирование в случае равенства предыдущих. Реализовано путем</p>
	    	 * <p>присвоения положительного, отрицательного или нулевого значения. Для последовательного
	    	 * сравнения фрагментов, значения уменьшаются в бесконечно убывающей геометрической прогрессии. 
	    	 * </p>
	    	 */

	    	@Override
	    	public int compare(List<String> o1, List<String> o2) throws IndexOutOfBoundsException {
	    	int r = 0;	    	
	    	int baseNumber = 1024;
	    	double appCompare = 0;
	    	System.out.println("Comparing started! " + baseNumber);
	    	
	    	String o1column = o1.get(columnIndex);
	    	String o2column = o2.get(columnIndex);
	    	//<p>Сначала выделяем null. Дальнейшее сравнение пойдет только после их прохождения.</p>
	    		int comparingCase = 0;
	    		if (o1column == null && o2column == null) {
	    			appCompare = 0;  
	    		} else
	    		if (o1column == null && o2column != null) {
	    			appCompare = - baseNumber/4;   
	    		} else
	    		if (o1column != null && o2column == null) {
	    			appCompare = baseNumber/4;
	    		} else
	    		if (o1column != null && o2column != null) {
	    			//<p>Находим пустые ячейки. Здесь поступаем аналогично предыдущему. </p>
	    			if (o1column == "" && o2column == "") {
	    				appCompare = 0;
		    		} else
	    			if (o1column == "" && o2column != "") {
	    				appCompare = - baseNumber/2;
		    		} else
		    		if (o1column != "" && o2column == "") {
		    			appCompare = baseNumber/2;
		    		} else
		    		if (o1column!= "" && o2column != "") {
		    			//<p>Далее по списку добавляются ячейки только из цифр</p>
		    			if (IsStringDigit(o1column) && IsStringDigit(o2column)) {
			    			if ((Integer.parseInt(o1column) - Integer.parseInt(o2column)) > 0) {
			    				appCompare = (double) baseNumber;
			    			} else {
			    				appCompare = (double) - baseNumber;
			    			}
			    		}
			    		if (IsStringDigit(o1column) && !IsStringDigit(o2column)) {
			    			appCompare = (double) - baseNumber;
			    		}
			    		if (!IsStringDigit(o1column) && IsStringDigit(o2column)) {
			    			appCompare = (double) baseNumber;
			    		}
			    		if (!IsStringDigit(o1column) && !IsStringDigit(o2column)) {
			    			//<p>Теперь идет анализ фрагментов. Применим только к строкам, состоящим из букв и цифр</p>
			    			
			    			List<String> splittedO1 = FinallySplittedString(o1column);
			    			List<String> splittedO2 = FinallySplittedString(o2column);
			    			List<Integer> sizes = new ArrayList();
			    			sizes.add(splittedO1.size());
			    			sizes.add(splittedO2.size());
			    			
			    			if (IsStringDigit(splittedO1.get(0)) && IsStringDigit(splittedO2.get(0))) {
	
			    				if ((Integer.parseInt(splittedO1.get(0)) - Integer.parseInt(splittedO2.get(0))) > 0) {
				    				appCompare = baseNumber;
				    			} else if ((Integer.parseInt(splittedO1.get(0)) - Integer.parseInt(splittedO2.get(0))) < 0) {
				    				appCompare = - baseNumber;
				    			} else {
				    				//<p>Здесь мы оказываемся, если первые фрагменты равны, но сравнение идет дальше</p>
				    				appCompare = 0;
				    			}
		
			    			} else if (IsStringDigit(splittedO1.get(0)) && !IsStringDigit(splittedO2.get(0))) {
			    				appCompare = - baseNumber;
			    			} else if (!IsStringDigit(splittedO1.get(0)) && IsStringDigit(splittedO2.get(0))) {
			    				appCompare = baseNumber;
			    			} else {
			    				//<p>На этом этапе проходит все сравнение строк без цифр</p>
			    				appCompare = splittedO1.get(0).compareTo(splittedO2.get(0));
			    			}
			    			
			    			for(int i = 1; i < Collections.min(sizes); i++) {
			    				if (IsStringDigit(splittedO1.get(i)) && IsStringDigit(splittedO2.get(i))) {
			    					int k = (Integer.parseInt(splittedO1.get(i-1)) - Integer.parseInt(splittedO2.get(i-1)));
			    					if (k == 0) {
			    						int e = (Integer.parseInt(splittedO1.get(i)) - Integer.parseInt(splittedO2.get(i)));
			    						appCompare += (e/(2^(i+2)));
			    					} else {
			    						//<p>Если ранжирование успешно прошло по предыдущему фрагменту, дальнейшее сравнение нецелесобразно.
			    						// Проверка необходима для корректной работы.</p>
			    						break;
			    					} 			    		
				    			
				    			} else if (IsStringDigit(splittedO1.get(i)) && !IsStringDigit(splittedO2.get(i))) {
				    				appCompare += - baseNumber/(2^(i+2));
			    					
				    			} else if (!IsStringDigit(splittedO1.get(i)) && IsStringDigit(splittedO2.get(i))) {
				    				appCompare += baseNumber/(2^(i+2));
				    			} else {
				    				//<p>Действие в случае, если цифровые фрагменты все равны.</p>
				    				appCompare += (splittedO1.get(i).compareTo(splittedO2.get(i)))/(2^(i+2));
				    			}
			    			}
			    			}
			    		}
		    		}
	    			
	    			if (appCompare < 0 && appCompare > -1) {
	    				appCompare = -1;
	    			}
	    			
	    			if (appCompare > 0 && appCompare < -1) {
	    				appCompare = 1;
	    			}
	    			
	    			r = (int) appCompare;	    		
	    	
	    		return r;
	    	}

	    }
	    
	    List<List<String>> sortableRows = new ArrayList();
    	try {
    		//<br>Преобразуем массив в более мобильный. Для эффективной работы нам нужно массив колонок преобразовать в массив строк</br>    		
    	

             for (int k = 0; k< rows.get(columnIndex).length; k++) {
            	 List<String> newRow = new ArrayList();
            	 for(int i = 0; i< rows.size(); i++) {
            		 newRow.add(rows.get(i)[k]);
            	 }
            	 sortableRows.add(newRow);
             }
 
    	//<p>Полученный массив сортируем сразу весь. Собственно, сама сортировка идет следующей строкой</p>    
    	Collections.sort(sortableRows, new DigitComparator());
    	//<p>езультат сортировки преобразуем обратно в требуемый массив.</p>
    		List<String[]> newRows = new ArrayList();
             for (int i = 0; i<sortableRows.size();i++) {
            		String[] newColumn = new String[sortableRows.get(i).size()];
            		for (int j = 0; j<sortableRows.get(i).size();j++) {
            			newColumn[j] = sortableRows.get(i).get(j);            			
            		}
            		newRows.add(newColumn);
            	}
             
           //<p>Результат сохраняем</p>      
           theSortedRow = newRows;
          
    		
    	} catch (IndexOutOfBoundsException e) {
    		//<p>Пользователь может ввести индекс больше длины массива, поэтому делаем проверку</p>
    		System.out.println("Operation not completed, may be too large column index: " + e);
		} 
			 catch (Exception e) { System.out.println(e); }
			   
    }
    
    // <p>Добавляем метод, возвращающий результат сортировки. 
    //Вызов метода должен быть заключен в попытку на случай, если сортировка не осуществлялась ранее.</p>
    public List<String[]> getSortedRows() {
    return theSortedRow;
    }
} 
