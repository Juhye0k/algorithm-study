import java.util.*;

class Node implements Comparable<Node> {

    int id;
    int count;

    public Node(int id, int count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public int compareTo(Node other) {
        // 재생 횟수가 같으면 고유 번호가 낮은 순서
        if (this.count == other.count) {
            return Integer.compare(this.id, other.id);
        }

        // 재생 횟수가 많은 순서
        return Integer.compare(other.count, this.count);
    }
}

class Solution {

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        // 장르별 전체 재생 횟수
        Map<String, Integer> genrePlayCount = new HashMap<>();

        // 장르별 노래 목록
        Map<String, List<Node>> songsByGenre = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];

            // 장르별 전체 재생 횟수 누적
            genrePlayCount.put(
                genre,
                genrePlayCount.getOrDefault(genre, 0) + playCount
            );

            // 장르별 노래 목록에 현재 노래 추가
            songsByGenre
                .computeIfAbsent(genre, key -> new ArrayList<>())
                .add(new Node(i, playCount));
        }

        // 장르 이름을 리스트로 만든다.
        List<String> genreOrder = new ArrayList<>(genrePlayCount.keySet());

        // 전체 재생 횟수가 많은 장르 순서로 정렬
        genreOrder.sort(
            (genre1, genre2) -> Integer.compare(
                genrePlayCount.get(genre2),
                genrePlayCount.get(genre1)
            )
        );

        for (String genre : genreOrder) {
            List<Node> songs = songsByGenre.get(genre);

            // 장르 안에서 재생 횟수 내림차순,
            // 재생 횟수가 같으면 고유 번호 오름차순
            Collections.sort(songs);

            // 첫 번째 노래
            answer.add(songs.get(0).id);

            // 노래가 2개 이상이면 두 번째 노래도 추가
            if (songs.size() >= 2) {
                answer.add(songs.get(1).id);
            }
        }

        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}