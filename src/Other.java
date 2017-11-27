import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

class RandomNum{
    /*随机函数
     *方法：生成一定范围的随机数int randomNum(int min,int max)
     */
    public static int randomNum(int min,int max){
        Random num=new Random();
        int rst = num.nextInt(max)%(max-min+1)+min;
        return rst;
    }
}

class ReadFromFile{
    /*文本读入
     *方法：读入文本String readFileByChars(String fileName)
     */
    public static String readFileByChars(String fileName){
        /*文本读入
         *形式参数：文件的绝对路径String
         * 返回：一个文本的String
         */
        StringBuilder dest = new StringBuilder();
        File file = new File(fileName);
        Reader reader = null;
        try{
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1){
                if(((char)tempchar) != '\r' && ((char)tempchar) != '\n'){
                    dest.append((char)tempchar);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if (reader != null){
                try{
                    reader.close();
                }
                catch(IOException e1){
                    e1.printStackTrace();
                }
            }
        }
        return dest.toString();
    }
}

class StringToGraph{
    private static String SEPARATOR=" |,|!|\\.|\\?|:|;|-";
    public static Graph getGraph(String object) {
        Graph graph;
        String[] wordArray;
        String[] wordSet;
        String[] tmpWordArray = object.split(SEPARATOR);
        int wordNum = 0;
        for (String str : tmpWordArray) {
            if (!str.isEmpty()) {
                wordNum++;
            }
        }
        wordArray = new String[wordNum];
        int n = 0;
        for (String str : tmpWordArray) {
            if (!str.isEmpty()) {
                wordArray[n++] = str.toLowerCase();
            }
        }
        n = 0;
        String[] tmpWordSet = new String[wordNum];
        HashSet set=new HashSet();
        for (String str : wordArray) {
            if (!set.contains(str)) {
                set.add(str);
                tmpWordSet[n++] = str;
            }
        }
        wordSet = new String[n];
        n = 0;
        for (String str : tmpWordSet) {
            if(str!=null){
                wordSet[n++]=str;
            }
        }
        graph=new Graph(wordSet,wordArray);
        return graph;
    }
}


class VertexDist{
    /*
     *顶点距离的类
     *方法：构造；顶点距离的比较接口
     */
    public int index;
    public int dist;
    public VertexDist(int vertexIndex,int vertexdist){
        index=vertexIndex;
        dist=vertexdist;
    }
    public static Comparator<VertexDist> distComparator = new Comparator<VertexDist>(){
        @Override
        public int compare(VertexDist v1, VertexDist v2) {
            return (int) (v2.dist - v1.dist);
        }
    };
}

class  Graphviz{
    private String runPath = "";    //"D:\\TestDel"

    //  dot程序的路径
    //private String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
    private String dotPath = "E:\\selfprogram\\Graphviz2.38\\bin\\dot.exe";

    private String runCmd="";
    private String gvFile="";
    private String genaPic="";
    private StringBuilder gvText = new StringBuilder();

    Runtime runtime=Runtime.getRuntime();

    public Graphviz(String path,String fileName) {
        this.runPath=path;
        this.gvFile=fileName;
        this.genaPic=fileName;
    }

    public void genaCmd(){
        runCmd+=dotPath+" ";
        runCmd+=runPath;
        runCmd+="\\"+gvFile+".txt ";
        runCmd+="-T png ";
        runCmd+="-o ";
        runCmd+=runPath;
        runCmd+="\\"+genaPic+".png";
        //System.out.println(runCmd);
    }

    public void run() {
        File file=new File(runPath);
        if(!file.exists())
        {
            file.mkdirs();
        }
        writeGraphToFile(gvText.toString(), runPath);
        genaCmd();
        try {
            runtime.exec(runCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGraphToFile(String dotText, String dirName) {
        try {
            File file = new File(dirName+"\\"+gvFile+".txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotText.getBytes());
            fos.close();
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void add(String line) {
        gvText.append("\t"+line);
    }

    public void addLn(String line) {
        gvText.append("\t"+line + "\n");
    }

    public void addLn() {
        gvText.append('\n');
    }

    public void startGraph() {
        gvText.append("digraph G {\n") ;
    }

    public void endGraph() {
        gvText.append("}") ;
    }
}
