package cheryl.exception;

public class FileCorruptedException extends RuntimeException {
  public FileCorruptedException() {
    super("File corrupted");
  }
}
