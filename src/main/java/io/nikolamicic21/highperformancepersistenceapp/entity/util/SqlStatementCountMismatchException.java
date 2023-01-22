package io.nikolamicic21.highperformancepersistenceapp.entity.util;

public class SqlStatementCountMismatchException extends RuntimeException {

    private final long expected;
    private final long recorded;

    public SqlStatementCountMismatchException(long expected, long recorded) {
        super(String.format("Expected %d statement(s) but recorded %d instead!",
                expected, recorded)
        );
        this.expected = expected;
        this.recorded = recorded;
    }

    public long getExpected() {
        return expected;
    }

    public long getRecorded() {
        return recorded;
    }

}
