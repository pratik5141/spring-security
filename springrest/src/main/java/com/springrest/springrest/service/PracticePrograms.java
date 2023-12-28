package com.springrest.springrest.service;

import org.springframework.stereotype.Service;

@Service
public class PracticePrograms {

	public void patternPrinting() {
		
		int i, j;
		int n = 6 ;

		for ( i = 1; i < n; i++) {
			for( j = 1 ; j <= n ; j++) {
				System.out.print(" ");
			}
			for ( j = 1; j <= i; j++) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
}
