
import java.util.*;
import java.util.stream.*;

public class Main {
    public int[] solution(String[] genres, int[] plays) throws Exception {
        // iter and cal
        // 장르별 최다 재생 곡 -> 재생 횟수 + 고유번호가 낮은 것 우선
        Map<String, Integer> playCountByGenre = new HashMap<>();
        Map<String, TreeSet<Song>> mostPlayedByGenre = new HashMap<>();

        
        
    
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            Song song = new Song(i, play);

            // 장르별 재생횟수
            Integer presentValue = playCountByGenre.getOrDefault(genre, 0);
            playCountByGenre.put(genre, presentValue + play);

            // 장르별 최다 재생 곡 -> 같으면 고유번호 낮은 것==같은 재생횟수가 있으면 넣지 않음
            TreeSet<Song> topSongs = mostPlayedByGenre.computeIfAbsent(genre, k -> 
                new TreeSet<>(
                    Comparator.comparingInt(Song::getPlayCount).reversed().thenComparing(Song::getId))
                );

                topSongs.add(song);
                if (topSongs.size() > 2) {
                    topSongs.pollLast();
                }
        }
        List<Integer> resultList = new ArrayList<>();
        // 최다 재생 순으로 가져온다.
        List<String> mostPlayedGenreList = playCountByGenre.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry::getKey).collect(Collectors.toList());

        // 각 장르별 최대 2개씩 뽑는다.
        for (String genre : mostPlayedGenreList) {
            TreeSet<Song> treeSet = mostPlayedByGenre.get(genre);
            resultList.addAll(treeSet.stream().map(Song::getId).collect(Collectors.toList()));
        }
        
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) throws Exception {
        String[] genres = { "classic", "pop", "classic", "classic", "pop" };
        int[] plays = {500, 600, 150, 800, 2500};

        int[] result = new Main().solution(genres, plays);
        for (int i : result) {
            System.out.print(i);
        }
    }

}

class Song {
    private int id;
    private int playCount;

    public Song(int id, int count) {
        this.id = id;
        this.playCount = count;
    }

    public int getId() {
        return this.id;
    }

    public int getPlayCount() {
        return this.playCount;
    }
}