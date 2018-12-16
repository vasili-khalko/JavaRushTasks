package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basillio on 04.11.2018.
 */
@XmlRootElement
@XmlType(name = "shop")
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    public static class Goods {
        @XmlElement
        public List<String> names = new ArrayList<>();
    }


    // для более наглядного вывода, валидатору это ненадо.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("goods (" + goods.names.size() + "):\n");
        for (String str : goods.names) {
            sb.append(" - " + str + "\n");
        }
        sb.append("count: " + count + "\n");
        sb.append("profit: " + profit + "\n");
        sb.append("secretData (" + secretData.length + "):\n");
        for (String str : secretData) {
            sb.append(" - " + str + "\n");
        }
        return sb.toString();
    }
}
