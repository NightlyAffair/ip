package cheryl.main;

import cheryl.task.Serialized;
import cheryl.task.Task;
import cheryl.util.DataListTypes;
import cheryl.util.DataStore;
import cheryl.util.TaskList;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataList implements Serialized {
    private final HashMap<DataListTypes, ArrayList<DataStore>> dataLists;

    public DataList() {
        this.dataLists = new HashMap<>();
        for (DataListTypes type : DataListTypes.values()) {
            dataLists.put(type, new ArrayList<>());
        }
    }

    public DataList(DataList dataList) {
        this.dataLists = dataList.dataLists;
    }

    public void add(String line) {

    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<DataListTypes, ArrayList<DataStore>> entry : dataLists.entrySet()) {
            for (DataStore dataStore : entry.getValue()) {
                sb.append(entry.getKey()).append("|").append(dataStore.serialize()).append("\n");
            }
        }
        return sb.toString();
    }

    public String deserialize(String line) {
        String[] split = line.split("\\|");
        try {
            switch (DataListTypes.valueOf(split[0])) {
                case TASKLIST -> {
                    return 
                }
            }
        }
    }
}
