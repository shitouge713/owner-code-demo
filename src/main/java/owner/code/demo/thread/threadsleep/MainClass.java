package owner.code.demo.thread.threadsleep;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        List<School> list = new ArrayList<>(10);
        Map<String,School> map = new HashMap<>();
        map.put("1",new School(1,"第一中学"));
        map.put("2",new School(2,"第二中学"));
        map.put("3",new School(3,"第三中学"));

        List<Map.Entry<String,School>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, School>>() {
            @Override
            public int compare(Map.Entry<String, School> o1, Map.Entry<String, School> o2) {
                //compareTo
                return o1.getValue().getShoolid()-(o2.getValue().getShoolid());
            }
        });
        entryList.forEach(a-> System.out.println(a.getValue().getShoolid()));
    }



}

