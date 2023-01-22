package io.nikolamicic21.highperformancepersistenceapp.entity.util;

import net.ttddyy.dsproxy.QueryCount;
import net.ttddyy.dsproxy.QueryCountHolder;

public class SqlStatementCountValidator {

    public static void reset() {
        QueryCountHolder.clear();
    }

    public static void assertSelectCount(long expectedSelectCount) {
        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        var recordedSelectCount = queryCount.getSelect();
        if (expectedSelectCount != recordedSelectCount) {
            throw new SqlStatementCountMismatchException(expectedSelectCount,
                    recordedSelectCount);
        }
    }

    public static void assertInsertCount(long expectedInsertCount) {
        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        var recordedInsertCount = queryCount.getInsert();
        if (expectedInsertCount != recordedInsertCount) {
            throw new SqlStatementCountMismatchException(expectedInsertCount,
                    recordedInsertCount);
        }
    }

    public static void assertUpdateCount(long expectedUpdateCount) {
        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        var recordedUpdateCount = queryCount.getUpdate();
        if (expectedUpdateCount != recordedUpdateCount) {
            throw new SqlStatementCountMismatchException(expectedUpdateCount,
                    recordedUpdateCount);
        }
    }

    public static void assertDeleteCount(long expectedDeleteCount) {
        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        var recordedDeleteCount = queryCount.getDelete();
        if (expectedDeleteCount != recordedDeleteCount) {
            throw new SqlStatementCountMismatchException(expectedDeleteCount,
                    recordedDeleteCount);
        }
    }

}
