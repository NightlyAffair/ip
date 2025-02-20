package cheryl.manager;

public interface Manager {
    
    public void setPointer(ManagerTypes pointer);
    public String run();
    public String write();
    public void read(String readString);
    public void clear();
}
