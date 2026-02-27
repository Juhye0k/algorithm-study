import java.util.*;
class Node implements Comparable<Node>{
    int id;
    int count;
    public Node(int id, int count) {
        this.id=id;
        this.count=count;
    }
    @Override
    public int compareTo(Node o){
        if(this.count==o.count) {
            return this.id-o.id;
        }
        return o.count-this.count;
    }
}
class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        List<Integer> answer = new LinkedList<>();
        // 가장 많이 재생된 장르
        Map<String,Integer> count = new HashMap<>();
        Map<String,List<Node>> list = new HashMap<>();
        for(int i=0; i<genres.length; i++) {
            // 노래 리스트에 넣기
            // 장르 카운트에 넣기
            count.put(genres[i],count.getOrDefault(genres[i],0)+plays[i]);
            list.putIfAbsent(genres[i],new ArrayList<>());
            list.get(genres[i]).add(new Node(i,plays[i]));

        }
        // 장르 내에서 많이 재생된 노래
        List<String> sortedGenres = new ArrayList<>(count.keySet());
        sortedGenres.sort((a,b)->count.get(b)-count.get(a));
        int index=0;
        for(String genre : sortedGenres) {
                List<Node> songs=list.get(genre);
            Collections.sort(songs);
            int cnt=0;
            for(Node song : songs) {
                if(cnt == 2) break; 
                answer.add(song.id); 
                cnt++;
            }
            
        }
        return answer;
    }
}