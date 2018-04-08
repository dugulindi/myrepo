import com.miaoqian.bigdata.common.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/3/1.
 */
public class TimeTest {
    static int second = 1;
    static int minute = 60;
    static int hour = 60 * minute;
    static int date = 24 * hour;
    static int week = 7 * date;
    static int month = 30 * date;
    static int year = 365 * date;

    public static List<StatData> getStatData(Date from, Date to) {
        int seconds = DateUtils.getSecondBetweenDate(from, to);
        if (seconds >= 0 && seconds < 20) {
            return getStatData(from, to, 1);
        } else if (seconds >= 20 && seconds < 40) {
            return getStatData(from, to, 2);
        } else if (seconds >= 40 && seconds < minute) {
            return getStatData(from, to, 3);
        } else if (seconds >= minute && seconds < hour) {
            if (seconds >= minute && seconds < 100) {
                return getStatData(from, to, 4);
            } else if (seconds >= 100 && seconds < 200) {
                return getStatData(from, to, 8);
            } else if (seconds >= 200 && seconds < 400) {
                return getStatData(from, to, 12);
            } else if (seconds >= 400 && seconds < 900) {
                return getStatData(from, to, 25);
            } else if (seconds >= 900 && seconds < 1800) {
                return getStatData(from, to, minute);
            } else if (seconds >= 1800 && seconds < hour) {
                return getStatData(from, to, 2*minute);
            }
        } else if (seconds >= hour && seconds < date) {
            if (seconds>=hour && seconds <2*hour){
                return getStatData(from, to, 4*minute);
            } else if (seconds>=2*hour && seconds <4*hour){
                return getStatData(from, to, 12*minute);
            } else if (seconds>=4*hour && seconds <6*hour){
                return getStatData(from, to, 20*minute);
            } else if (seconds>=6*hour && seconds <9*hour){
                return getStatData(from, to, 30*minute);
            }else if (seconds>=9*hour && seconds <date){
                return getStatData(from, to, hour);
            }
        } else if (seconds >= date && seconds < week) {
            if (seconds>=date && seconds <2*date){
                return getStatData(from, to, 2*hour);
            } else if (seconds>=2*date && seconds <4*date){
                return getStatData(from, to, 6*hour);
            } else if (seconds>=4*date && seconds <week){
                return getStatData(from, to, 12*hour);
            }
        } else if (seconds >= week && seconds < month) {
            return getStatData(from, to, date);
        } else if (seconds >= month && seconds < year) {
            if (seconds>=month && seconds <2*month){
                return getStatData(from, to, 3*date);
            } else if (seconds>=2*month && seconds <4*month){
                return getStatData(from, to, 6*date);
            } else if (seconds>=4*month && seconds <6*month){
                return getStatData(from, to, 14*date);
            }else if (seconds>=6*month && seconds <year){
                return getStatData(from, to, month);
            }
        } else if (seconds >= year) {
            if (seconds<2*year){
                return getStatData(from, to, month);
            }else if (seconds<4*year){
                return getStatData(from, to, 2*month);
            }else if (seconds<6*year){
                return getStatData(from, to, 3*month);
            }else if (seconds<12*year){
                return getStatData(from, to, 6*month);
            }else {
                return getStatData(from, to, year);
            }
        }
        System.out.println(seconds);
        return null;
    }

    public static List<StatData> getStatData(Date from, Date to, int interval) {
        List<StatData> statDataList = new ArrayList<StatData>();
        int begin = (int) (from.getTime() / 1000);
        int end = (int) (to.getTime() / 1000);
        while (begin < end) {
            if (begin + interval <= end) {
                to = DateUtils.addSeconds(from, interval);
            } else {
                to = DateUtils.addSeconds(from, end - begin);
            }
            StatData statData = new StatData(0, from, to);
            statDataList.add(statData);
            from = to;
            begin = begin + interval;
        }
        return statDataList;
    }

    public static List<StatData> getStatDataBySecondInterval(Long from, Long to, int interval) {
        List<StatData> statDataList = new ArrayList<StatData>();
        return statDataList;
    }

    public static void main(String[] args) throws ParseException {
        Date from = DateUtils.getSimpeFormat().parse("2017-03-02 15:20:12");
        Date to = DateUtils.addSeconds(from, 1);
        getStatData(from, to);
//        System.out.println(from.getTime());
//        System.out.println(to.getTime());
//        System.out.println(getStatData(from, to));
    }
}
