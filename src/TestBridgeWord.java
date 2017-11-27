import static org.junit.Assert.*;

import org.junit.Test;

public class TestBridgeWord {

	@Test
	public void test1() {
		String sourceText=ReadFromFile.readFileByChars("C:\\Users\\Tom\\"
				+ "Desktop\\Code\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("C:\\TestDel","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText); 
		String rst = tmp.queryBridgeWords(tmpGraph, "running", "he");
		assertEquals("No running or he in the graph!",rst);
	}
	
	@Test
	public void test2() {
		String sourceText=ReadFromFile.readFileByChars("C:\\Users\\Tom\\"
				+ "Desktop\\Code\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("C:\\TestDel","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String rst = tmp.queryBridgeWords(tmpGraph, "can", "well");
		assertEquals("No bridge words from can to well!",rst);
	}
	
	@Test
	public void test3() {
		String sourceText=ReadFromFile.readFileByChars("C:\\Users\\Tom\\"
				+ "Desktop\\Code\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("C:\\TestDel","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String rst = tmp.queryBridgeWords(tmpGraph, "no", "can");
		assertEquals("The bridge words from no to can are: one.",rst);
	}
	
	@Test
	public void test4() {
		String sourceText=ReadFromFile.readFileByChars("C:\\Users\\Tom\\"
				+ "Desktop\\Code\\Lab7\\src\\Lab6_w_test.txt");
		Control tmp = new Control("C:\\TestDel","Lab7");
		Graph tmpGraph = tmp.createDirectedGraph(sourceText);
		String rst = tmp.queryBridgeWords(tmpGraph, "who", "play");
		assertEquals("The bridge words from who to play are: can, and should.",rst);
	}

}
