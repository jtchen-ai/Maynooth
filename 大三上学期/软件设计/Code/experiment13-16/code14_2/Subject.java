package experiment14_1;

interface Subject {
    void register(Observer observer);
    void unRegister(Observer observer);
    void notifyRegisteredUsers(int notifiedValue);
}
