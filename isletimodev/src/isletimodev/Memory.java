package isletimodev;

import java.util.ArrayList;

public class Memory {
	
	public ArrayList<Page> pages;
	public int capacity;	
	public int pageCount;
	public int pageSize;
	public int realJobSize;
	public int index;
	
	
	public Memory(){
		this.index = 0;
		this.pageSize = 64;
		this.realJobSize = 64;
		this.pageCount = (capacity-realJobSize) / pageSize;
		this.pages = new ArrayList<Page>();
		
		for(int i = 0; i<pageCount; i++)
		{
			pages.add(new Page(i));
		}
	}
	
	public void PrintPages() {
		
		for(Page p:pages)
		{
			System.out.println("cap= "+p.capacity+", remSpace= "+p.remainingSpace);
		}
	}
	
}
