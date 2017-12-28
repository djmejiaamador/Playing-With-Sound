package project1;

import java.util.Iterator;

public class test {
	public static void main(String[] args) {
		MusicLinkedList list = new MusicLinkedList(3, 3);
		float[] sample1 = {1,2,3};
		list.addSample(sample1);
		float[] sample2 = {4,5,6};
		list.addSample(sample2);
		
		MusicLinkedList list2 = new MusicLinkedList(3, 3);
		float[] sample3 = {7,8,9};
		list2.addSample(sample3);
		float[] sample4 = {10,11,12};
		list2.addSample(sample4);
		
		System.out.println("reversing...\n");	
		//list.combine(list2, true);
		//list2.print();
		//System.out.println("Combing...done");
		
		Iterator<Float>sIter = list.iterator(2);
		System.out.println("this is the original ");
		//System.out.println("IsIter...dons"+sIter.next() );
		list.print();
		
		list.reverse();
		System.out.println("this is after reversing  ");
		list.changeSampleRate(10);
		list.print();
		
		
		float test =  2;
		test = 2 /1
				;
	}	
}