package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{	
		if (element.equals(null)) throw new NullPointerException();
			
		if(this.size()==0)
		{
			//System.out.println("add to empty list");
			new LLNode<E>(element,head,tail);
			
			//LLNode<E> prevNode = head;
			//LLNode<E> nextNode = tail;
			
		}
		else
		{
			new LLNode<E>(element,tail.prev,tail);
		}
		
		//LLNode<E> addNode = new LLNode<E>(element,prevNode,nextNode);
		//LLNode<E> addNode = new LLNode<E>(element);
		
		//.next = addNode;
		//addNode.prev = head
		size = this.size();
//		System.out.println(size);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		
		LLNode<E> tempNode = new LLNode<E>();
		
		if (head.next== tail | index < 0 | index > this.size()-1)
		{
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i<=index; i++) 
		{
			if(i == 0)
				{
				tempNode.next = head.next;
				tempNode.data = (tempNode.next).data;
				//System.out.println(tempNode.data);
				}
			else{
				tempNode.next = (tempNode.next).next;
				tempNode.data = (tempNode.next).data;
				//System.out.println(tempNode.data);
				}
		}
		
		return tempNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element.equals(null)) throw new NullPointerException();
		LLNode<E> tempNode = new LLNode<E>();
		if(index<0)
			{
			throw new IndexOutOfBoundsException();
			}
		if(this.size() == 0 & index == 0)
		{
			new LLNode<E>(element,head,tail);
		}
		else
		{
		if (index > (this.size()-1))
		{
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i<=index; i++) 
		{
			if(i == 0)
				{
				tempNode.next = head.next;
				tempNode.prev = head;
				//System.out.println(tempNode.data);
				}
			else{
				tempNode.next = (tempNode.next).next;
				tempNode.prev = (tempNode.next).prev;
				//System.out.println(tempNode.data);
				}
		}
		new LLNode<E>(element,tempNode.prev,tempNode.next);
		}
		size = this.size();
		// Need to fin where index is then add the node in the right place
		//this(LLNode<E>())
	}


	/** Return the size of the list */
	public int size() 
	{
		LLNode<E> tempNode = new LLNode<E>();
		tempNode.next = head.next;
		int counter = 0;
		if (tempNode.next.equals(tail))
		{
			return 0;
		}
		
		while (!tempNode.next.equals(tail))
		{
			tempNode.next = (tempNode.next).next;
			counter++;
		}
		size = counter;
		return counter;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> tempNode = new LLNode<E>();
		if (head.next == tail | index < 0 | index > this.size()-1)
		{	
			//System.out.println(this.size());
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i<=index; i++) 
		{
			if(i == 0)
				{
				tempNode.next = head.next;
				tempNode.data = (head.next).data;
				}
			else{
				tempNode.next = (tempNode.next).next;
				tempNode.prev = (tempNode.next).prev;
				tempNode.data = (tempNode.next).data;
				//System.out.println(tempNode.data);
				}
		}
		
		if (index == 0)
		{
			tempNode.next = (tempNode.next).next;
			head.next = tempNode.next;
			(tempNode.next).prev = head; 
//			tempNode.prev = (tempNode.next).prev;
		}
		else{
		(tempNode.prev).next = (tempNode.next).next;
		((tempNode.next).next).prev = tempNode.prev;
		}
		size = this.size();
		return tempNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element.equals(null)) throw new NullPointerException();
		
		LLNode<E> tempNode = new LLNode<E>();
		
		if (head.next == tail | index < 0 | index > this.size()-1)
		{
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i<=index; i++) 
		{
			if(i == 0)
				{
				tempNode.next = head.next;
				tempNode.data = (tempNode.next).data;
				//System.out.println(tempNode.data);
				}
			else{
				tempNode.next = (tempNode.next).next;
				tempNode.data = (tempNode.next).data;
				//System.out.println(tempNode.data);
				}
		}
		(tempNode.next).data = element;
		
		// TODO: Implement this method
		return tempNode.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
	}
	
	public LLNode()
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode)
	{
		this(e);
		this.next = prevNode.next;
		prevNode.next = this;
		
		this.prev = nextNode.prev;
		nextNode.prev = this;
		
		this.next = nextNode;
		this.prev = prevNode;
	}

}
