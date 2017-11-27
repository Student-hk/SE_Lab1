import static org.junit.Assert.*;

import org.junit.Test;

public class TestShortestPath {

	@Test
	public void testCalcShortestPath1() throws InterruptedException {
		String sourceText=ReadFromFile.readFileByChars("D:\\1150310620\\软件工程\\实验\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("D:\\Test","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String s = tmp.calcShortestPath(tmpGraph, "play", "no");
		assertEquals("The path from play to no: play-->basketball-->well-->no",s);
	}
	
	@Test
	public void testCalcShortestPath2() throws InterruptedException {
		String sourceText=ReadFromFile.readFileByChars("D:\\1150310620\\软件工程\\实验\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("D:\\Test","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String s = tmp.calcShortestPath(tmpGraph, "who", "play");
		assertEquals("The path from who to play: who-->can-->play",s);
	}
	@Test
	public void testCalcShortestPath3() throws InterruptedException {
		String sourceText=ReadFromFile.readFileByChars("D:\\1150310620\\软件工程\\实验\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("D:\\Test","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String s = tmp.calcShortestPath(tmpGraph, "man", "a");
		assertEquals("no path from man to a",s);
	}
	
	@Test
	public void testCalcShortestPath4() throws InterruptedException {
		String sourceText=ReadFromFile.readFileByChars("D:\\1150310620\\软件工程\\实验\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("D:\\Test","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String s = tmp.calcShortestPath(tmpGraph, "hello", "world");
		assertEquals("no hello or no world in the graph",s);
	}
}
