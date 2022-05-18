package domains.unit.metaserver.model;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    public static int DEFAULT_PAGE_SIZE = 10;
    private final long start;
    private final List<T> data;
    private final long totalCount;
    private final int pageSize;


    public Page() {
        this(0,
             0,
             DEFAULT_PAGE_SIZE,
             new ArrayList<>());
    }


    public Page(long start,
                long totalSize,
                int pageSize,
                List<T> data) {
        this.pageSize = pageSize;
        this.start = start;
        totalCount = totalSize;
        this.data = data;
    }


    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo,
                              DEFAULT_PAGE_SIZE);
    }


    public static int getStartOfPage(int pageNo,
                                     int pageSize) {
        return (pageNo - 1) * pageSize;
    }


    public static int GetPageIndex(int pageNo,
                                   int pageSize) {
        return pageNo - 1;
    }


    public long getTotalCount() {
        return totalCount;
    }

    private long getTotalPageCount() {
        if (totalCount % pageSize == 0) return totalCount / pageSize;
        else return totalCount / pageSize + 1;
    }


    public int getPageSize() {
        return pageSize;
    }


    public List<T> getResult() {
        return data;
    }

    private long getCurrentPageNo() {
        return start / pageSize + 1;
    }


    public boolean isHasNextPage() {
        return getCurrentPageNo() < getTotalPageCount();
    }

    public boolean isHasPreviousPage() {
        return getCurrentPageNo() > 1;
    }

    public long getStart() {
        return start;
    }
}
