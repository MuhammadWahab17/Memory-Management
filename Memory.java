import java.util.LinkedList;
public class Memory {
	
	//stringinterval inner class
	public class StringInterval{
		//data members are public as asked.
		public int id;
		public int start;
		public int length;
		
		//constructor for initializing purpose
		public StringInterval(int _id,int _start,int _length) {
			id = _id;
			start = _start;
			length = _length;
		}

	}
	
	private LinkedList<StringInterval> intervalList = new LinkedList<StringInterval>();
	private char[] memoryArray ;
	static int idCount = 0; 
	
	
	
	public Memory(int length) {
		memoryArray = new char[length];
		idCount = 0;
	}
	
	
	public String get(int id) {
		String str = "";
		for(int i=0;i<intervalList.size();i++) {
        	StringInterval StrObj = intervalList.get(i);
        	if(id == StrObj.id) {
        		for(int j=StrObj.start;j<StrObj.length;j++)
        			str += memoryArray[j];
        		return str;
        	}
        }
		return null;
	}
	
	//string //6
	//mystring //i=2
	public int get(String s) {
		int i;
		for(i=0;i<memoryArray.length;i++) {
			if(memoryArray[i] == s.charAt(0)) {
				int j;
				//1 3
				//2 4
				//3 5
				//4 6
				//5 7
				for(j=1;j<s.length();j++) {
					if(memoryArray[i+j] != s.charAt(j)) {
						break;
					}
				}if(j == s.length()) {
					break;
				}
			}
		}
//		System.out.print(i);
		//i has the starting position for string s
		if(i==memoryArray.length) {
			return -1;
		}
	
		for(int j=0;j<intervalList.size();j++) {
        	StringInterval StrObj = intervalList.get(j);
        	if(i == StrObj.start) {
        		return StrObj.id;
        	}
        }
        return -1;
	}
	
	public String remove(int id) {
		String str = "";
		for(int j=0;j<intervalList.size();j++) {
        	StringInterval StrObj = intervalList.get(j);
        	if(id == StrObj.id) {
        		for(int i=StrObj.start;i<StrObj.length;i++)
        			str += memoryArray[i];
        		intervalList.remove(StrObj);
        		return str;
        	}
        }
		return null;
	}
	
	public int remove(String s) {
		int i;
		for(i=0;i<memoryArray.length;i++) {
			if(memoryArray[i] == s.charAt(0)) {
				int j;
				for(j=1;j<s.length();j++) {
					if(memoryArray[i+j] != s.charAt(j)) {
						break;
					}
				}if(j == s.length()) {
					break;
				}
			}
		}
		//i has the starting position for string s
		if(i==memoryArray.length) {
			return -1;
		}
	
		for(int j=0;j<intervalList.size();j++) {
        	StringInterval StrObj = intervalList.get(j);
        	if(i == StrObj.start) {
        		intervalList.remove(StrObj);
        		return StrObj.id;
        	}
        }
        return -1;
	}
	
	public void defragement() {
		StringInterval prev = intervalList.getFirst();
		for(int j=1;j<intervalList.size();j++) {
			 StringInterval next = intervalList.get(j);
			 if((prev.start+prev.length) != next.start) {
				 int s = prev.start+prev.length;
				 int l = next.length;
				 for(int i=s;i<l;i++) {
					 memoryArray[i] = memoryArray[i+1];
				 }
				 next.start = s;
			 }
			 prev=next;
		 }
	}
	public int put(String s) {
		StringInterval StrObj = null;
		if(idCount == 0) {
			for(int i=0;i<s.length();i++) {
				if(i >= memoryArray.length) {
					return -1;
				}
				memoryArray[i] = s.charAt(i);
			}
			StrObj = new StringInterval(idCount,0,s.length());
			intervalList.add(StrObj);
			idCount++;
			return StrObj.id;
		}
		
		StrObj = intervalList.getFirst();
		for(int j=1;j<intervalList.size();j++){
        	StringInterval StrObj2 = intervalList.get(j);
        	if(s.length()<= StrObj2.start-(StrObj.length+StrObj.start)) {
        		int k=0;
        		for(int i=StrObj.length+StrObj.start;i<s.length()+StrObj.length+StrObj.start;i++) {
					memoryArray[i] = s.charAt(k);
					k++;
				}
        		int index = intervalList.indexOf(StrObj);
				StrObj = new StringInterval(idCount,StrObj.start+StrObj.length,s.length());
				intervalList.add(index+1, StrObj);
				idCount++;
				return StrObj.id;
        	}
        	StrObj = StrObj2;
        }
		if(!intervalList.isEmpty())
			{
				StrObj = intervalList.getLast();
				if(StrObj.start+StrObj.length < memoryArray.length) {
					int k = 0;
					if(s.length()+StrObj.start+StrObj.length >= memoryArray.length) {
						StrObj = intervalList.getLast();
					}
					for(int i=StrObj.start+StrObj.length;i<s.length()+StrObj.start+StrObj.length;i++) {
						if(i > memoryArray.length) {
							return -1;
						}
						memoryArray[i] = s.charAt(k);
						k++;
					}
					StrObj = new StringInterval(idCount,StrObj.start+StrObj.length,s.length());
					intervalList.add(StrObj);
					idCount++;
					return StrObj.id;
				}
		}
		return -1;
	}
	
	public void display() {
		for(int i=0;i<intervalList.size();i++) {
			StringInterval s= intervalList.get(i);
        	System.out.print("Id:");
        	System.out.print(s.id);
        	System.out.print("\tStart:");
        	System.out.print(s.start);
        	System.out.print("\tLength:");
        	System.out.print(s.length);
        	System.out.print("\n");
		}
//		ListIterator<StringInterval> list_Iter = intervalList.listIterator(0);
//        while(list_Iter.hasNext()){
//        	StringInterval s= list_Iter.next();
//        	System.out.print("Id:");
//        	System.out.print(s.id);
//        	System.out.print("\tStart:");
//        	System.out.print(s.start);
//        	System.out.print("\tLength:");
//        	System.out.print(s.length);
//        	System.out.print("\n");
//        }
        System.out.print("Memory§Array View:\n");
        for(int i=0;i<memoryArray.length;i++) {
        	System.out.print(memoryArray[i]);
        }
	}
	
	
	public static void main(String[] args) {
		
		Memory m1 = new Memory(22);
		m1.put("if");
		m1.put("hello");
		m1.put("and");
		m1.put("goodbye");
		System.out.print("\n");
		m1.display();
		m1.remove(0);
		m1.remove(2);
		System.out.print("\n");
		System.out.print("\nAfter removing id 1 and 2 :\n");
		m1.display();
		
			Memory m = new Memory(24);
			m.put("Kiwi");
			m.put("pomegranate");
			m.put("apple");
			m.remove("pomegranate");
			
			m.put("grape");
			m.put("fig");
			System.out.print(m.put("pear"));
			System.out.print("\n\n");
			m.display();
	}

}
