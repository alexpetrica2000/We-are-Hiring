public interface Subject {

    public void addObserver(User user);
    public void removeObserver(User c);
    public void notifyAllObservers(Notification notification);
}
