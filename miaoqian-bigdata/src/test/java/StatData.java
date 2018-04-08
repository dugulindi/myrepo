import java.util.Date;

/**
 * Created by admin on 2017/3/1.
 */
public class StatData {
    Integer mCount;
    Date mFromTime;
    Date mToTime;
    boolean mIsCompleted = true;

    public StatData() {
    }

    public StatData(Integer mCount, Date mFromTime, Date mToTime) {
        this.mCount = mCount;
        this.mFromTime = mFromTime;
        this.mToTime = mToTime;
    }

    public StatData(Integer mCount, Date mFromTime, Date mToTime, boolean mIsCompleted) {
        this.mCount = mCount;
        this.mFromTime = mFromTime;
        this.mToTime = mToTime;
        this.mIsCompleted = mIsCompleted;
    }

    public Integer getmCount() {
        return mCount;
    }

    public void setmCount(Integer mCount) {
        this.mCount = mCount;
    }

    public Date getmFromTime() {
        return mFromTime;
    }

    public void setmFromTime(Date mFromTime) {
        this.mFromTime = mFromTime;
    }

    public Date getmToTime() {
        return mToTime;
    }

    public void setmToTime(Date mToTime) {
        this.mToTime = mToTime;
    }

    public boolean ismIsCompleted() {
        return mIsCompleted;
    }

    public void setmIsCompleted(boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }
}
