import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> l = new LinkedList<Integer>();
        
        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            String p = st.nextToken();
            
            if(p.equals("push_front")){
                l.addFirst(Integer.parseInt(st.nextToken()));
            }
            
            else if(p.equals("push_back")){
                l.add(Integer.parseInt(st.nextToken()));
            }
            
            else if(p.equals("pop_front")){
                if(l.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(l.pollFirst());
            }
            
            else if(p.equals("pop_back")){
                if(l.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(l.pollLast());
            }
            
            else if(p.equals("size"))
                System.out.println(l.size());
            
            else if(p.equals("empty")){
                if(l.isEmpty())
                    System.out.println(1);
                else
                    System.out.println(0);
            }
            
            else if(p.equals("front")){
                if(l.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(l.peekFirst());
            }
            
            else if(p.equals("back")){
                if(l.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(l.peekLast());
            }
        }
    }
}