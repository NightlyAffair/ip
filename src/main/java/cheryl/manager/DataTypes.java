package cheryl.manager;

public enum DataTypes {
  TASK,
  CONTACT;

  public String toString() {
    switch (this) {
      case TASK:
        return "TASK";
      case CONTACT:
        return "CONTACT";
      default:
        return "UNKNOWN";
    }
  }
}
