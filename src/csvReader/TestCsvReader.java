package csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

/**
 * csv를 읽어서 파싱하는 테스트
 * https://jeong-pro.tistory.com/212
 * resources/test.csv 에 파일이 있다.
 *
 *
 * try with resource 를 활용한 자원 닫기 미스 방지
 * member 클래스 생성을 이용한 객체리
 * stream 을 활용한 처리? -> for vs Stream API
 * 가독성 그리고 참조타입이라는 점에서 stream 과 for 의 차이가 미미. stream 의 가독성이 현재 이슈에서는 맞다.
 */
public class TestCsvReader {
    private static Pattern likePattern = Pattern.compile("좋아");
    public static void main(String[] args) {

        TestCsvReader testCsvReader = new TestCsvReader();
        List<Member> lists = testCsvReader.readCsv();
        // 취미별 인원수
        Map<String, Integer> numByHobby = calNumByHobby(lists);

        // 취미별 정씨 성 인원수
        Map<String, Integer> numByLastName = calNumByLastName(lists, "정");

        // 소개내용에 좋아 등장 횟수
        int countLike = calLikeCount(lists);

    }

    private static int calLikeCount(List<Member> lists) {
        return lists.stream().mapToInt(Member::likeInComment).sum();
    }

    private static Map<String, Integer> calNumByLastName(List<Member> memberList, String lastName) {
        return memberList.stream()
                .filter(member -> member.getFirstName().equals(lastName))
                .flatMap(member -> member.getHobby().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(i -> 1)));
    }

    private static Map<String, Integer> calNumByHobby(List<Member> lists) {
        return lists.stream()
                .flatMap(member -> member.getHobby().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(i-> 1)));
    }

    // todo : need test
    private List<Member> readCsv() {

        try (BufferedReader br = new BufferedReader(new FileReader("resources/test.csv"))) {
            List<Member> memberList = new ArrayList<>();
            String s = br.readLine();// 첫 head 날림
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                memberList.add(new Member(row));
            }
            return memberList;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Member{
        private String name;
        private List<String> hobby;
        private String comment;

        public Member(String[] row) {
            this.name = row[0].trim();
            String[] hobbies = row[1].trim().split(":");
            this.hobby = new ArrayList<>(List.of(hobbies));
            this.comment = row[2].trim();
        }

        public String getComment() {
            return comment;
        }

        public List<String> getHobby() {
            return hobby;
        }

        public String getName() {
            return name;
        }

        public String getFirstName() {
            return this.name.substring(0, 1);
        }

        public int likeInComment() {
            Matcher matcher = likePattern.matcher(comment);
            return (int) matcher.results().count();
        }
    }
}
