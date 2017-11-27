import java.awt.*;
import java.util.Scanner;

public class UI {
    private String runPath;
    private String fileName;
    public static void main(String[] args) throws InterruptedException {
        UI proG=new UI();
        
        System.out.println("----------------文本转换为有向图-------------------");
        Scanner in=new Scanner(System.in);
        System.out.println("请输入图片保存的目录路径");
        proG.runPath=in.next();//D:\TestDel
        System.out.println("请输入保存的文件名");
        proG.fileName=in.next();
        //proG.showDirectedGraph(proG.graph);
        proG.userCommand();
    }
    public void userCommand() throws InterruptedException {
    	Control Ctl = new Control(runPath,fileName);
        Scanner in = new Scanner(System.in);
        String enter;
        do{
            System.out.println("\n*******请选择功能（输入选项的字母序号即可）*******");
            System.out.println("a.展示文本生成的有向图");
            System.out.println("b.查询桥接词");
            System.out.println("c.根据bridge word生成新文本");
            System.out.println("d.计算word1和word2之间的最短路径");
            System.out.println("e.随机游走");
            System.out.println("f.计算一个word与所有其他单词最短的路径");
            System.out.println("q.退出");
            System.out.println("**************************************************\n");
            enter=in.next();
            Ctl.action(enter);
        } while(true);
    }
}