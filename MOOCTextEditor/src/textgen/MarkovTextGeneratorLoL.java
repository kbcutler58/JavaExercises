package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{

		String[] strList = sourceText.split("[ ]+");
		starter = strList[0];
		int matchInt = -2;
		ListNode matchedNode;
		ListNode previousWordNode = null;
		for (int i = 0; i < strList.length; i++)
		{

			if (i == 0 & wordList.size() == 0) 
			{
				previousWordNode = new ListNode(strList[i]);
				wordList.add(previousWordNode);
			}
			if (i == 0 & wordList.size() > 0 )
			{
				matchInt = 0;
			}
			else
			{
				if(matchInt > -1)
				{
					matchedNode = wordList.get(matchInt);
					matchedNode.addNextWord(strList[i]);
				}
				else
				{
					previousWordNode.addNextWord(strList[i]);
				}

				int counter=0;
				matchInt=-1;

				for (ListNode list : wordList)
				{

					// Determine if word matches previous node
					if(list.getWord().equals(strList[i]))
					{
						matchInt = counter;
					}
					counter++;
				}
				if (matchInt==-1)
				{
					previousWordNode = new ListNode(strList[i]);
					wordList.add(previousWordNode);
				}
				else
				{
					previousWordNode = wordList.get(matchInt);
				}
			//System.out.println(matchInt);
			}
		}
		previousWordNode = wordList.get(wordList.size()-1);
		previousWordNode.addNextWord(strList[0]);
		// TODO: Implement this method
	}	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String newString="";
		String prevString="";
//		ListNode prevWord = wordList.get(0);
		for (int i = 0; i < numWords; i++)
		{
			if(i == 0) 
				{
				prevString = starter; 
				newString += prevString;
				}
			else{
			boolean match = false;
			for (ListNode list: wordList)
			{
				if ( list.getWord().equals(prevString) & (match==false))
				{
					prevString = list.getRandomNextWord(rnGenerator);
					match = true;
					//System.out.print("Previous String = " + prevString);
				}
			}
			newString +=" ";
			newString += prevString;
			}
			// find node which corresponds to last word
			// select new random word based on that node
			// add word to output string
		}
		return newString;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList.clear();
		this.train(sourceText);
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		gen.train(textString);
		System.out.println(gen);
		
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int randomChoice = generator.nextInt(nextWords.size());
		
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    return nextWords.get(randomChoice);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


