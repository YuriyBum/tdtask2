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
 * <h1>������� �1</h1>
 * ���������� ��������� {@link IStringRowsListSorter}.
 *
 * <p>�� ����� �������� �������� � ������ ������� �� ��������� ���� � �������� ������������ ���������� java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

	 // ���� ���������� ������ ��������, ��� singleton. ���� ��� ������������� �� ���������� �������.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();
    //<p>������� ��������� ����������� ����������</p>
    private List<String[]> theSortedRow;

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
    	
    	 // <p>�������� ����� ���� ����������. �� ���� �� ��� ������ ������������������, ������������������ � ��������� ����.</p>
    	 // <p>���������� ��������� ����.</p>    	
    	 // <p>��� �������� ������������ ����� {@link tdtask1}</p>
    	 class DigitComparator implements Comparator<List<String>> {
						
			private boolean IsStringDigit (String a) throws NumberFormatException {
				
				// <p>��������� ��������� ������ �� ������� ������� �� ����, ���������� boolean</p>
				boolean b;	 
				    try {
				        Integer.parseInt(a);
				        b = true;
				    } catch (NumberFormatException e) {
				        b = false;
				    }		
				
				return b;
			}
			
			//<p>������ ���� �������� ������ �� ���������. �������� �� ����� �� ������ � ����. ���������� List<String>. </p>  

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

			//<p>������ ���� ��������� ������� ����������. ���������� List<String>. ������ ���� ���������� ������ �������, ������� ��������� ��� ������
			// ����� �� ���������</p>  
			//<p>������ "." �� �������� ������, ����� � ����� - ������ �������. ��� �������� "3445" � "31.344523" ������� ����� ��������� "344523"</p> 
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
						
						// ������������ ��� ���������� ���������� ������ ��� ������
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
	    	 * <p>����� ������������ ��������� ��������� ���������� ������. ��������� ��������</p>
	    	 * <p>�������� ��������� �� ���� (���������� ������������ ����������� ��������). ����������� ��������</p>
	    	 * <p>������ �� ������������ � ������ ��������� ����������. ����������� �����</p>
	    	 * <p>���������� ��������������, �������������� ��� �������� ��������. ��� �����������������
	    	 * ��������� ����������, �������� ����������� � ���������� ��������� �������������� ����������. 
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
	    	//<p>������� �������� null. ���������� ��������� ������ ������ ����� �� �����������.</p>
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
	    			//<p>������� ������ ������. ����� ��������� ���������� �����������. </p>
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
		    			//<p>����� �� ������ ����������� ������ ������ �� ����</p>
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
			    			//<p>������ ���� ������ ����������. �������� ������ � �������, ��������� �� ���� � ����</p>
			    			
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
				    				//<p>����� �� �����������, ���� ������ ��������� �����, �� ��������� ���� ������</p>
				    				appCompare = 0;
				    			}
		
			    			} else if (IsStringDigit(splittedO1.get(0)) && !IsStringDigit(splittedO2.get(0))) {
			    				appCompare = - baseNumber;
			    			} else if (!IsStringDigit(splittedO1.get(0)) && IsStringDigit(splittedO2.get(0))) {
			    				appCompare = baseNumber;
			    			} else {
			    				//<p>�� ���� ����� �������� ��� ��������� ����� ��� ����</p>
			    				appCompare = splittedO1.get(0).compareTo(splittedO2.get(0));
			    			}
			    			
			    			for(int i = 1; i < Collections.min(sizes); i++) {
			    				if (IsStringDigit(splittedO1.get(i)) && IsStringDigit(splittedO2.get(i))) {
			    					int k = (Integer.parseInt(splittedO1.get(i-1)) - Integer.parseInt(splittedO2.get(i-1)));
			    					if (k == 0) {
			    						int e = (Integer.parseInt(splittedO1.get(i)) - Integer.parseInt(splittedO2.get(i)));
			    						appCompare += (e/(2^(i+2)));
			    					} else {
			    						//<p>���� ������������ ������� ������ �� ����������� ���������, ���������� ��������� ��������������.
			    						// �������� ���������� ��� ���������� ������.</p>
			    						break;
			    					} 			    		
				    			
				    			} else if (IsStringDigit(splittedO1.get(i)) && !IsStringDigit(splittedO2.get(i))) {
				    				appCompare += - baseNumber/(2^(i+2));
			    					
				    			} else if (!IsStringDigit(splittedO1.get(i)) && IsStringDigit(splittedO2.get(i))) {
				    				appCompare += baseNumber/(2^(i+2));
				    			} else {
				    				//<p>�������� � ������, ���� �������� ��������� ��� �����.</p>
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
    		//<br>����������� ������ � ����� ���������. ��� ����������� ������ ��� ����� ������ ������� ������������� � ������ �����</br>    		
    	

             for (int k = 0; k< rows.get(columnIndex).length; k++) {
            	 List<String> newRow = new ArrayList();
            	 for(int i = 0; i< rows.size(); i++) {
            		 newRow.add(rows.get(i)[k]);
            	 }
            	 sortableRows.add(newRow);
             }
 
    	//<p>���������� ������ ��������� ����� ����. ����������, ���� ���������� ���� ��������� �������</p>    
    	Collections.sort(sortableRows, new DigitComparator());
    	//<p>�������� ���������� ����������� ������� � ��������� ������.</p>
    		List<String[]> newRows = new ArrayList();
             for (int i = 0; i<sortableRows.size();i++) {
            		String[] newColumn = new String[sortableRows.get(i).size()];
            		for (int j = 0; j<sortableRows.get(i).size();j++) {
            			newColumn[j] = sortableRows.get(i).get(j);            			
            		}
            		newRows.add(newColumn);
            	}
             
           //<p>��������� ���������</p>      
           theSortedRow = newRows;
          
    		
    	} catch (IndexOutOfBoundsException e) {
    		//<p>������������ ����� ������ ������ ������ ����� �������, ������� ������ ��������</p>
    		System.out.println("Operation not completed, may be too large column index: " + e);
		} 
			 catch (Exception e) { System.out.println(e); }
			   
    }
    
    // <p>��������� �����, ������������ ��������� ����������. 
    //����� ������ ������ ���� �������� � ������� �� ������, ���� ���������� �� �������������� �����.</p>
    public List<String[]> getSortedRows() {
    return theSortedRow;
    }
} 
