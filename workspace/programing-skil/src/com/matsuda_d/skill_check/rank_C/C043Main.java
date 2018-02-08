package com.matsuda_d.skill_check.rank_C;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author matsuda_d
 *
 */
public class C043Main {

    public static void main(String[] args) {
    	C043Main main = new C043Main();
        main.execute();
    }

    private void execute() {
        try (Scanner scanner = new Scanner(System.in)) {
            int totalUsedCount = Integer.parseInt(getLine(scanner));
            List<String> useUserIds = Arrays.asList(getLine(scanner).split(" "));
            HashMap<String, Integer> userMap = new HashMap<>();
            for (String userId : useUserIds) {
                setUserData(userMap, userId);
            }
            List<String> mostUseUserList = getMostUseUserList(userMap);
            System.out.println(getOutPutData(mostUseUserList));
        }
    }

    private String getOutPutData(List<String> mostUseUserList) {
        StringBuilder sb = new StringBuilder();
        sb.append(mostUseUserList.get(0));
        for (int i = 1; i < mostUseUserList.size(); i++) {
            sb.append(" ");
            sb.append(mostUseUserList.get(i));
        }
        return sb.toString();
    }

    private List<String> getMostUseUserList(HashMap<String, Integer> userMap) {
        List<String> userIds = userMap.entrySet().stream().map(e -> e.getKey()).collect(Collectors.toList());
        Integer maxCount = 0;
        for (String userId : userIds) {
            Integer count = userMap.get(userId);
            maxCount = getMaxCount(maxCount, count);
        }
        final Integer finalMaxCount = maxCount;
        return userMap.entrySet().stream()
                .filter(e -> e.getValue() == finalMaxCount)
                .map(e -> e.getKey())
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());
    }

    private Integer getMaxCount(Integer maxCount, Integer count) {
        return Integer.compare(maxCount, count) == 1 ? maxCount : count;
    }

    private void setUserData(HashMap<String, Integer> userMap, String userId) {
        Integer count = 1;
        if (userMap.containsKey(userId)) {
            count = count + userMap.get(userId);
        }
        userMap.put(userId, count);
    }

    private String getLine(Scanner scanner) {
        return scanner.nextLine();
    }
}