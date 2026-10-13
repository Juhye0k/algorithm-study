import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = new int[genres.length];
        Map<String,Integer> totalPlays = new HashMap<>();
        
        Map<String, List<Integer>> songIds = new HashMap<>();
        for(int i=0; i<genres.length; i++) {
            totalPlays.put(genres[i], totalPlays.getOrDefault(genres[i],0)+plays[i]);
            songIds.computeIfAbsent(genres[i],key->new ArrayList<>()).add(i);
        }
        List<String> sortedGenres = new ArrayList<>(totalPlays.keySet());
        sortedGenres.sort((a,b) ->
                         Long.compare(totalPlays.get(b),totalPlays.get(a)));
        List<Integer> result = new ArrayList<>();
        
        for(String genre : sortedGenres) {
            List<Integer> ids = songIds.get(genre);
            
            ids.sort((a,b) ->{
                int comparison = Integer.compare(plays[b],plays[a]);
                return comparison!=0 ? comparison : Integer.compare(a,b);
            });
            
            for(int i=0; i<Math.min(2,ids.size()); i++) {
                result.add(ids.get(i));
            }
        }
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}