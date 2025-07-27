package com.example.switch2025.dsa.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {


}

class TimeMap {

    private final Map<String, List<Data>> timeMap;

    public TimeMap() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<Data> dataList = timeMap.getOrDefault(key, new ArrayList<>());
        dataList.add(new Data(timestamp, value));
        timeMap.put(key, dataList);
    }

    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) {
            return "";
        }

        // do binary search over the data entries in the map to find the most recent timestamp entry
        List<Data> dataList = timeMap.get(key);
        int low = 0;
        int high = dataList.size() - 1;
        int resIndex = -1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (dataList.get(mid).getTimestamp() <= timestamp) {
                resIndex = mid;
                low = mid + 1; // trying to find a better option
            } else {
                high = mid - 1;
            }
        }

        return resIndex != -1 ? dataList.get(resIndex).getValue() : "";
    }
}

class Data {
    private final int timestamp;
    private final String value;

    Data(int timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }
}
