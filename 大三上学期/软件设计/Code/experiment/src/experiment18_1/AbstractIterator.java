package experiment18_1;

interface AbstractIterator {
    void next();
    boolean isLast();
    void previous();
    boolean isFirst();
    Object getNextItem();
}
