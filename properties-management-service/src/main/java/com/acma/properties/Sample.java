package com.acma.properties;

public class Sample {

	public static void main(String[] args) {
		int num[]= {2,7,3,6,5,8};
		int index=1;
		int reqNum=9;
		
		int[] newArray = new int[num.length+1];
		for(int i=0;i<index;i++) {
			newArray[i]=num[i];			
		}
		newArray[index]=reqNum;
		for(int i=index+1;i<newArray.length;i++) {
			newArray[i]=num[i-1];
		}
		
		System.out.println(num);
		for(int i=0;i<newArray.length;i++) {
			System.out.println(newArray[i]);
		}
	}

}
