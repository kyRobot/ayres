/**
 * CLI Entry Point for Ayres
 */
package com.kyrobot.ayres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Main provider for Ayres Project
 */
public class Ayres {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		parseArgs(Arrays.asList(args)).stream()
			.forEach(arg -> System.out.println(arg.display()));
	}
	
	private static List<Argument<?>> parseArgs(List<String> argsIn) {
		final List<Argument<? extends Object>> arguments = new ArrayList<Argument<?>>();
		final ListIterator<String> argsIterator = argsIn.listIterator();
		while(argsIterator.hasNext()) {
			final String argFragment = argsIterator.next();
			final String nextFragment;
			if(argsIterator.hasNext()) {
				nextFragment = argsIterator.next();
				argsIterator.previous();
			} else {
				nextFragment = "";
			}
			
			if (isArgKey(argFragment)) {
				if (!nextFragment.isEmpty() && !isArgKey(nextFragment)) {
					arguments.add(new Ayres().new StringValueArg(argFragment.substring(1), nextFragment));
				} else {
					arguments.add(new Ayres().new OnOffArg(argFragment));
				}
			}
		}
		return arguments;
	}
	
	private static boolean isArgKey(String maybeKey) {
		return maybeKey.startsWith("-");
	}
	
	public interface Argument<T> {
		String getArgKey();
		T getArgValue();
		
		default String display() { 
			return "Argument: " + getArgKey() + 
					" Value: "+ getArgValue().toString();
		}
	}
	
	public class StringValueArg implements Argument<String> {
		private String argKey;
		private String argValue;

		public StringValueArg(String key, String value) {
			this.argKey = key;
			this.argValue = value;
		}

		@Override
		public String getArgKey() {
			return argKey;
		}

		@Override
		public String getArgValue() {
			return argValue;
		}
		
	}
	
	public class OnOffArg implements Argument<Boolean> {
		private String argKey;

		public OnOffArg(String argKey) {
			this.argKey = argKey;
		}
		
		@Override
		public String getArgKey() {
			return argKey;
		}

		@Override
		public Boolean getArgValue() {
			return Boolean.TRUE;
		}
		
		@Override
		public String display() {
			return "Boolean Argument: " + getArgKey();
		}
	}

}
