package com.example.tree.question.greedy;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 有多场会议都要占用某一间会议室，会议时间不能有交集，如何才能安排最多的会议。返回安排会议的厂数。
 * 解题思路：
 * 按照会议结束时间排序，结束时间早的优先安排。
 */
public class MeetingRoom {
    /**
     * @param meetings
     * @param timePoint 下一场会议能够开始的最早时间，初始值可以是会议室开放时间，比如：6:00
     * @return 最多能安排的会议数
     */
    public static int bestArrange(Meeting[] meetings, LocalTime timePoint) {
        if (meetings == null) {
            return 0;
        }
        Arrays.sort(meetings, Comparator.comparing(o -> o.end));
        int num = 0;
        for (Meeting meeting : meetings) {
            if (timePoint.compareTo(meeting.start) <= 0) {
                num++;
                timePoint = meeting.end;
            }
        }
        return num;
    }


    public static class Meeting {
        public LocalTime start;
        public LocalTime end;

        public Meeting(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }
    }
}
