package com.vivek.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.vivek.datastructs.WordNode;

public class MarkovModel {

	private final Random random;
	private final Map<String, WordNode> model;
	
	public MarkovModel() {
		this.model = new HashMap<>();
		this.random = new Random();
	}

	public MarkovModel(Map<String, WordNode> model, Random random) {
		super();
		this.model = model;
		this.random = random;
	}

	public String getNextWord(String word) {
		WordNode wordNode = this.model.get(word);
		if (wordNode != null) {
			return wordNode.getNextWordNode(random).getWord();
		}
		return "";
	}

	public void train(List<String> words) {
		if (words == null || words.isEmpty()) {
			return;
		}

		WordNode firstNode = null;
		WordNode curNode = null;
		for (String word : words) {
			word = word.trim();
			if (word.isEmpty()) {
				continue;
			}

			if (model.isEmpty()) {
				curNode = new WordNode(word);
				this.model.put(word, curNode);
				firstNode = curNode;
			} else {
				WordNode wordNode = this.model.get(word);
				if (wordNode == null) {
					wordNode = new WordNode(word);
					this.model.put(word, wordNode);
				}
				curNode.getProbabilityList().add(wordNode);
				curNode = wordNode;
			}
		}
		curNode.getProbabilityList().add(firstNode);
	}

	public void displayModel() {
		for (Map.Entry<String, WordNode> entry : this.model.entrySet()) {
			System.out.print(entry.getKey() + " -> ");
			WordNode node = entry.getValue();
			for (WordNode w : node.getProbabilityList()) {
				System.out.print(w.getWord() + " ");
			}
			System.out.println();
		}
	}

}
