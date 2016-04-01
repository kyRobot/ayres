/**
 * CLI Entry Point for Ayres
 */
package com.kyrobot.ayres;

import java.util.Arrays;

/**
 * Main provider for Ayres Project
 */
public class Ayres {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Arguments accepted:");
		Arrays.asList(args).stream().forEach(System.out::println);
	}

}
