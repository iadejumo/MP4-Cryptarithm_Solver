package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp4.avltree.AvlTreeSet;

public class AvlTreeTest {
	
	@Test
	public void Test1() {
		//Test the basic functionality of the AvlTree with small tree
		AvlTreeSet avl = new AvlTreeSet();
		
		assertTrue(avl.isEmpty());
		
		avl.insert(0);
		
		assertEquals(0, avl.getHeight());
		assertFalse(avl.isEmpty());
		
		avl.insert(3);
		avl.insert(1);
		avl.insert(2);
		avl.insert(2);
		
		assertEquals(4, avl.size());
		assertEquals(2, avl.getHeight());
		assertTrue(avl.contains(2));
		assertFalse(avl.contains(-2));
		assertEquals(3, avl.getMax());
		assertEquals(0, avl.getMin());
		
		avl.remove(2);
		assertEquals(3, avl.size());
		
		avl.insert(2);
		avl.remove(0);		
		
	
		//System.out.println(avl.size());		
	}
	
	@Test (expected = IllegalStateException.class)
	public void Test2() {
		//Test whether exception is thrown (Get minimum on empty tree)
		AvlTreeSet avl = new AvlTreeSet();
		
		int min = avl.getMin();
	}
	
	@Test (expected = IllegalStateException.class)
	public void Test3() {
		//Test whether exception is thrown (Get maximum on empty tree)
		AvlTreeSet avl = new AvlTreeSet();
		
		int max = avl.getMax();
	}
	
	@Test (expected = IllegalStateException.class)
	public void Test4() {
		//Test whether exception is thrown (remove from empty tree)
		AvlTreeSet avl = new AvlTreeSet();
		
		avl.remove(1);;
	}
	
	@Test
	public void Test5() {
		//Test balancing of tree on with more branches
		AvlTreeSet avl = new AvlTreeSet();
				
		avl.insert(1);
		avl.insert(2);
		avl.insert(3);
		avl.insert(4);
		avl.insert(5);
		avl.insert(10);
		avl.insert(7);
		avl.insert(8);
		avl.insert(6);
		avl.insert(9);
		avl.remove(1);
		//avl.insert(0);
		
		avl.insert(1);
		assertEquals(10, avl.size());
		System.out.print(avl);
		assertEquals(3, avl.getHeight());
		
		avl.insert(-1);
		avl.insert(-2);
		avl.insert(-3);
		//avl.insert(13);
		//avl.insert(4);	
		
	
		//System.out.println(avl.size());		
	}

}
