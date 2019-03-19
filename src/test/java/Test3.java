import java.util.*;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/13 22:54
 **/
public class Test3 {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("四", "第一次插入");
        map.put("三", "第二次插入");
        map.put("一", "第三次插入");
        map.put("五", "第四次插入");
        map.put("二", "第五次插入");
        map.put("六", "第六次插入");

        Set<String> set = map.keySet();
        for (String s : set) {
            String mapValue = map.get(s);
            System.out.println(s + "---" + mapValue);
        }

        Map<String, String> linkedMap = new LinkedHashMap<>();

        linkedMap.put("四", "第一次插入");
        linkedMap.put("三", "第二次插入");
        linkedMap.put("一", "第三次插入");
        linkedMap.put("五", "第四次插入");
        linkedMap.put("二", "第五次插入");
        linkedMap.put("六", "第六次插入");

        Set<String> linkedSet = linkedMap.keySet();
        for (String s : linkedSet) {
            String mapValue = linkedMap.get(s);
            System.out.println(s + "---" + mapValue);
        }
    }

}
