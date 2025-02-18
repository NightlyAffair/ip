package cheryl.main;
import cheryl.util.DataTypes;

import java.io.Serializable;

public class Data implements Serializable {
    public DataTypes type;

    public Data(DataTypes type) {
        this.type = type;
    }
}
