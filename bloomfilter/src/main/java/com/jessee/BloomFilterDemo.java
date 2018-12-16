package com.jessee;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

import static com.google.common.base.Charsets.UTF_8;

/**
 * Created by helo on 2018/11/25.
 */
public class BloomFilterDemo {
    private static final int insertions = 1000000;

    public static void main(String[] args) {
        //初始化一个存储string的布隆过滤器，初始大小100w 默认误判率是0.03
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(UTF_8), insertions, 0.01);
        //用于存放所有实际存在的key，判断key是否存在
        Set<String> sets = new HashSet<>();
        //用于存放所有实际存在的key，可以取出使用
        List<String> list = new ArrayList<>(insertions);
        //向三个容器初始化100w个随机并且唯一的字符串
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            list.add(uuid);
        }

        int right = 0;//正确判断的次数
        int wrong = 0;//错误判断的次数
        for (int i = 0; i < 10000; i++) {
            String data = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();
            if (bf.mightContain(data)) {
                if (sets.contains(data)) {
                    // 判断实际存在
                    right++;
                    continue;
                }
                //布隆过滤器判断存在，但实际不存在
                wrong++;
            }
        }
        float percent = (float) wrong / 9900;
        System.out.println("100个实际存在的元素，判断存在的：" + right);
        System.out.println("9900个实际不存在的元素，误认为存在的：" + wrong + "，误判率：" + percent);
    }
}
