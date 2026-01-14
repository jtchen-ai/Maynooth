package experiment14_2;

interface Subject {
    void register(Observer observer);
    void unRegister(Observer observer);
    void notifyRegisteredUsers(String notifiedValue);
}