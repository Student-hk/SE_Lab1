import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
//lab3 change test02
class AdjacentEdge{
    /*����ͼ���ڽӱ�
     *���ԣ��ڽӱߵ�β����ļ���ֵ���ڽӱ��Ȩ�أ���һ���ڽӱߵ�����
     */
    public int edgeTailIndex=0;
    public int weight=0;
    public AdjacentEdge next;
}

class Vertex{
    /*����ͼ�Ķ���
     *���ԣ����㵥�ʣ����ȱߵ���Ŀ����һ���ߵ�����
     */
    public String word;
    public int edgeNum=0;
    public AdjacentEdge next;
}


class Graph {
    /*����ͼ
     *����:����ͼ�Ķ�������Vertex[] vertexArray
     *     �������int vertexNum
     *����������ͼ�Ĺ���Graph(String[] wordSet, String[] wordArray)
     *      �ŽӴʵ�����String[] bridgeWord(String word1, String word2)
     *      �����ŽӴ��������ı�String generateText(String text)
     *      �������ʵ����·��int[][] shortestPath(String word1,String word2)
     *      ���·��String randomGraph()
     */
    public Vertex[] vertexArray;
    int vertexNum;

    public Graph(String[] wordSet, String[] wordArray) {
        /*����ͼ�Ĺ���
         *��ʽ���������ʵļ������飬����ԭ����������
         */
        vertexNum = wordSet.length;
        vertexArray = new Vertex[vertexNum];
        for (int i = 0; i < wordSet.length; i++) {
            vertexArray[i] = new Vertex();
            vertexArray[i].word = wordSet[i];
        }
        int vertexIndex = 0; 
        int nextVertexIndex = 0;
        AdjacentEdge tmpEdge;
        for (int i = 0; i < wordArray.length - 1; i++) {
            //Ѱ���ڽӱߵ�����������Ϣ
            for (int j = 0; j < wordSet.length; j++) {
                if (wordSet[j].equals(wordArray[i])) {
                    vertexIndex = j;
                    vertexArray[j].edgeNum++;
                    break;  
                }
            }
            for (int j = 0; j < wordSet.length; j++) {
                if (wordSet[j].equals(wordArray[i + 1])) {
                    nextVertexIndex = j;
                    break;
                }
            }
            //����ڽӱ�
            AdjacentEdge curEdge;
            if (vertexArray[vertexIndex].next == null) {
                tmpEdge = new AdjacentEdge();
                tmpEdge.edgeTailIndex = nextVertexIndex;
                tmpEdge.weight++;
                vertexArray[vertexIndex].next = tmpEdge;
            } else {
                curEdge = vertexArray[vertexIndex].next;
                while (true) {
                    if (curEdge.edgeTailIndex == nextVertexIndex) {
                        vertexArray[vertexIndex].edgeNum--;
                        curEdge.weight++;
                        break;
                    } else {
                        if (curEdge.next == null) {
                            tmpEdge = new AdjacentEdge();
                            tmpEdge.edgeTailIndex = nextVertexIndex;
                            tmpEdge.weight++;
                            curEdge.next = tmpEdge;
                            break;
                        } else {
                            curEdge = curEdge.next;
                        }
                    }
                }
            }
        }
    }

    public String[] bridgeWord(String word1, String word2) {
        /*�ŽӴʵ�����
         *��ʽ����������1������2
         * ���أ��ŽӴʵ�����
         */
        int word1Index = -1;
        int word2Index = -1;
        String[] bridgeWords = null;
        //�����Ƿ���ڵ���1�͵���2
        for (int i = 0; i < vertexNum; i++) {
            if (vertexArray[i].word.equals(word1)) {
                word1Index = i;
            } else if (vertexArray[i].word.equals(word2)) {
                word2Index = i;
            } else if (word1Index < 0 || word2Index < 0) {
                continue;
            } else {
                break;
            }
        }
        if (word1Index < 0 || word2Index < 0) {
            //�����ڷ���null
            return bridgeWords;
        }
        bridgeWords = new String[vertexNum];
        AdjacentEdge bridgeEdge1 = vertexArray[word1Index].next, bridgeEdge2;
        int tmpBridge;
        int bridgeWordNum = 0;
        //�����ŽӴ�
        while (bridgeEdge1 != null) {
            tmpBridge = bridgeEdge1.edgeTailIndex;
            bridgeEdge2 = vertexArray[tmpBridge].next;
            while (bridgeEdge2 != null) {
                if (bridgeEdge2.edgeTailIndex == word2Index) {
                    bridgeWords[bridgeWordNum++] = vertexArray[bridgeEdge1.edgeTailIndex].word;
                    break;
                } else {
                    bridgeEdge2 = bridgeEdge2.next;
                }
            }
            bridgeEdge1 = bridgeEdge1.next;
        }
        bridgeWords[bridgeWordNum] = null;
        return bridgeWords;
    }

    public String generateText(String text){
        /*�������ı�
         *��ʽ�������û������String�ı�
         * ���أ����ı���String
         */
        String SEPARATOR=" |,|!|\\.|\\?|:|;|-|@";
        String[] textWords = text.split(SEPARATOR);
        String[] bridgeWords;
        int bridgeWordsNum;
        StringBuilder result = new StringBuilder();
        for(int i=0;i<textWords.length-1;i++){
            result.append(textWords[i]+" ");
            //�������ʵ��ŽӴʲ�ѯ
            bridgeWords=bridgeWord(textWords[i],textWords[i+1]);
            if(bridgeWords==null){
                continue;
            }
            else{
                if(bridgeWords[0]==null){
                    continue;
                } 
                bridgeWordsNum=0;
                for(String str:bridgeWords){
                    if(str!=null){
                        bridgeWordsNum++;
                        continue;
                    }
                    break;
                }
                //���ѡȡ�ŽӴʽ������
                result.append(bridgeWords[RandomNum.randomNum(1,bridgeWordsNum)-1]+" ");
            }
        }
        result.append(textWords[textWords.length-1]+".");
        return result.toString();
    }

    public int[][] shortestPath(String word1,String word2){
        /*���������ʵ����·��
         *��ʽ����������1������2
         * ���أ�Dijkstra�е�Dist[]���������飩��preNode[]��ǰ�������飩��������
         * ����Dijkstra�㷨�������·�������
         */
        int MAX= 32676;
        int word1Index = -1, word2Index = -1;
        //��������1�͵���2
        for (int i = 0; i < vertexNum; i++) {
            if (vertexArray[i].word.equals(word1)) {
                word1Index = i;
            } else if (vertexArray[i].word.equals(word2)) {
                word2Index = i;
            } else if (word1Index < 0 || word2Index < 0) {
                continue;
            } else {
                break;
            }
        }
        if(word1Index<0||word2Index<0){
            return null;
        }
        //������С���ȶ��н����Ż�
        Queue<VertexDist> distQueue=new PriorityQueue<>(vertexNum,VertexDist.distComparator);
        int[] dist= new int[vertexNum];
        int[] preNode = new int[vertexNum];
        boolean[] inSet = new boolean[vertexNum];
        for(int i=0;i<vertexNum;i++){
            dist[i]=MAX;
            inSet[i]=false;
        }
        dist[word1Index]=0;
        AdjacentEdge curEdge;
        int curVertex=word1Index;
        for(int i=1;i<vertexNum;i++){
            if(distQueue.peek()!=null && !inSet[distQueue.peek().index]){
                curVertex=distQueue.poll().index;
            }
            curEdge=vertexArray[curVertex].next;
            inSet[curVertex]=true;
            while(curEdge!=null){
                if(!inSet[curEdge.edgeTailIndex] && dist[curVertex]+curEdge.weight<dist[curEdge.edgeTailIndex]){
                    dist[curEdge.edgeTailIndex]=dist[curVertex]+curEdge.weight;
                    preNode[curEdge.edgeTailIndex]=curVertex;
                    distQueue.add(new VertexDist(curEdge.edgeTailIndex,dist[curEdge.edgeTailIndex]));
                }
                curEdge=curEdge.next;
            }
        }
        //Dist[]��preNode[]�ϳ�һ����ά����
        int[][] rst=new int[2][vertexNum];
        for(int i=0;i<vertexNum;i++){
            rst[0][i]=dist[i];
            rst[1][i]=preNode[i];
        }
        return rst;
    }

    public String randomGraph(){
        /*���·��
         *���أ�������ȫ�����·��String
         */
        Scanner in=new Scanner(System.in);
        String input;
        do{
            System.out.println("���� 's' ��ʼ");
            input = in.next();
        } while(!input.equals("s"));

        StringBuilder rst = new StringBuilder();
        int curIndex=RandomNum.randomNum(0,vertexNum-1);
        int nextIndex,randomEdge;
        AdjacentEdge curEdge;
        boolean[][] travelEdge=new boolean[vertexNum][vertexNum];
        while(true){
            rst.append(vertexArray[curIndex].word+" ");
            curEdge=vertexArray[curIndex].next;
            System.out.println(rst.toString());
            if(curEdge==null){
                break;
            }

            //���·������ʱ�Ŀ���
            if(!input.equals("f")){
                System.out.println("���� 'n' ���õ���һ������ or ���� 'f' ���Զ�������·�� or ���� 'c' ��ȡ������");
                do{
                    input = in.next();
                }while(!input.equals("n") && !input.equals("f") && !input.equals("c"));
                if (input.equals("c")){
                    break;
                }
            }
            randomEdge=RandomNum.randomNum(1,vertexArray[curIndex].edgeNum);
            for(int i=1;i<randomEdge;i++){
                curEdge=curEdge.next;
            }
            nextIndex=curEdge.edgeTailIndex;
            if(travelEdge[curIndex][nextIndex]){
                //rst.append(vertexArray[curIndex].word+" ");
                rst.append(vertexArray[nextIndex].word);
                System.out.println(rst.toString());
                break;
            }
            travelEdge[curIndex][nextIndex]=true;
            curIndex=nextIndex;
        }
        System.out.println("\n��������Ѿ�����");
        return rst.toString();
    }
}