package baekjoon_14725;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Node root = new Node("");

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            int eatInfoNum = Integer.parseInt(st.nextToken());
            Node parentNode = root;

            while (eatInfoNum-- > 0) {
                String child = st.nextToken();
                Node childNode = parentContains(parentNode.childs, child);

                if (childNode == null) {           //자식의 정보가 부모에 존재 X
                    childNode = new Node(child);
                    parentNode.childs.add(childNode);
                }

                parentNode = childNode;             //현재 자식 노드를 다음 자식의 부모로 설정
            }
        }

        makeStringBuilder(sb, root, 0);
        System.out.println(sb);
    }

    private static Node parentContains(List<Node> parent, String value) {

        for (Node node : parent) {
            if (node.value.equals(value))
                return node;
        }

        return null;
    }

    private static void makeStringBuilder(StringBuilder sb, Node parent, int rootCnt) {

        parent.childs.sort(Comparator.comparing(node -> node.value));   //오름차순으로 정렬
        for (Node node : parent.childs) {
            sb.append("-".repeat(rootCnt * 2));
            sb.append(node.value).append("\n");

            makeStringBuilder(sb, node, rootCnt + 1);
        }
    }


    private static class Node {
        String value;
        List<Node> childs = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }
    }
}
