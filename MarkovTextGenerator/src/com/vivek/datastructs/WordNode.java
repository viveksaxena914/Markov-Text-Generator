package com.vivek.datastructs;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WordNode {
	
	private static final WordNode EMPTY_NODE = new WordNode("");

	private final String word;
	private final List<WordNode> probabilityList;

	public WordNode(String word) {
		super();
		this.word = word;
		this.probabilityList = new LinkedList<>();
	}

	public WordNode getNextWordNode(Random random) {
		if (this.probabilityList.size() == 0)
			return EMPTY_NODE;

		int index = random.nextInt(this.probabilityList.size());
		return this.probabilityList.get(index);
	}

	public String getWord() {
		return word;
	}

	public List<WordNode> getProbabilityList() {
		return probabilityList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordNode other = (WordNode) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
}
