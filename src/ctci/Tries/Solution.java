package ctci.Tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/ctci-contacts
 */

class TrieNode {
	char ch;
	int visitors;
	Map <Character, TrieNode> childMap;

	TrieNode(char ch) {
		this.ch = ch;
		this.visitors = 0;
		childMap = new HashMap <Character, TrieNode> ();
	}

	void insert(String contact) {
		(this.visitors)++;
		if (contact.length() > 0) {
			TrieNode childNode;
			char firstChar = contact.charAt(0);
			if (this.childMap.containsKey(firstChar)) {
				childNode = this.childMap.get(firstChar);
			} else {
				childNode = new TrieNode(firstChar);
				childMap.put(firstChar, childNode);
			}
			childNode.insert(contact.substring(1));			
		} else if (contact.length() == 0) {
			childMap.put('*', null);
		}
	}

	int find(String partial) {
		if (partial.length() > 0) {
			char firstChar = partial.charAt(0);
			if (childMap.containsKey(firstChar)) {
				return childMap.get(firstChar).find(partial.substring(1));
			} else {
				return 0;
			}
		} else {
			return this.visitors;
		}
	}

	List <String> suggest(String partial) {
		List <String> suggestions = new ArrayList <String> ();
		if (partial.length() > 0) {
			char firstChar = partial.charAt(0);
			if (childMap.containsKey(firstChar)) {
				return childMap.get(firstChar).suggest(partial.substring(1));
			} else {
				return suggestions;
			}
		} else {
			for (Character c: childMap.keySet()) {
				if (!c.equals('*')) {
					List <String> tempSuggestions = childMap.get(c).suggest(partial);
					for (String s: tempSuggestions) {
						suggestions.add(c + s);
					}
				} else {
					List <String> tempSuggestions = new ArrayList <String> ();
					tempSuggestions.add("");
					return tempSuggestions;
				}
			}
		}
		return suggestions;
	}
}

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		TrieNode root = new TrieNode('_');
		while (n-- > 0) {
			String op = in.next();
			String contact = in.next();
			if (op.equals("add")) {
				root.insert(contact);
			} else if (op.equals("find")) {
				System.out.println(root.find(contact));
			} else if (op.equals("suggest")) {
				List <String> suggestions = root.suggest(contact);
				System.out.println("suggestions count " + suggestions.size());
				for (String sug: suggestions) {
					System.out.println(sug);
				}
			}
		}
		in.close();
	}
}