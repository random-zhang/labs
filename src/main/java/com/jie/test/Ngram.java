package com.jie.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ngram {

    private static Map<String, AtomicInteger> trainingData;

    static {
        String path = ".zhuxian.txt";
        trainingData = initTrainingData(path);//初始化训练数据，加载到内存
    }

    /**
     * 计算输入句子的合理性概率
     * @param sentence 要评估的语句
     * @param n N-gram的N
     * @return 合理性概率
     */
    private static float getProbability(String sentence, int n) {
        final String sen = "@" + sentence + "#";
        return Stream
                .iterate(0, i -> ++i)
                .limit(sen.length() - n + 1)
                .map(start -> sen.substring(start, start + n))
                .map(s -> {
                    float nu = (float) (null == trainingData.get(s) ? 1 : trainingData.get(s).get() + 1);
                    float de = (float) (null == trainingData.get(s.substring(0, s.length() - 1)) ? 1 : trainingData.get
                            (s.substring(0, s.length() - 1)).get() + 1);
                    System.out.println(s + "/" + s.substring(0, s.length() - 1) + " " + nu / de);
                    return nu / de;
                })
                .reduce(1f, (f1, f2) -> f1 * f2);
    }

    /**
     * 把训练数据处理过后加载到内存，统计每个分词的出现频次
     * @param path 训练集路径
     * @return 统计数据map
     */
    private static Map<String, AtomicInteger> initTrainingData(String path) {
        return readFileOrDir(path)
                .map(s -> s.replaceAll("[”“\\w\\s《》.：:*‘’、\"<>\\[\\]^`~]", ""))//去掉文字里的无意义字符,这里只处理中文
                .flatMap(s -> Stream.of(s.split("[，,。；;！!？?]")))//分割句子
                .filter(s -> !"".equals(s))//去掉空行
//          .peek(System.out::println)
                .map(s -> "@" + s + "#")//加上句首和句尾标记
                .flatMap(s -> Stream
                        .iterate(1, i -> ++i)//支持的N-gram的N为1、2、3、4
                        .limit(s.length() > 4 ? 4 : s.length())//N-gram的N最大为4，太大了内存容易爆，实际应用中4基本就够了
                        .parallel()
                        .flatMap(n -> Stream
                                .iterate(0, i -> ++i)
                                .limit(s.length() - n + 1)
                                .parallel()
                                .map(start -> s.substring(start, start + n))//分割句子为n个字的集合
                        )
                )
                .collect(Collectors.toConcurrentMap(o -> o,
                        o -> new AtomicInteger(1), (e1, e2) -> {
                            e1.incrementAndGet();
                            return e1;
                        }));
    }

    /**
     * 文件读取工具，以行为单位输出
     * @param path 文件路径
     * @return Stream lines 流
     */
    private static Stream<String> readFileOrDir(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] paths = file.list((dir, name) -> !name.startsWith("."));
            assert paths != null;
            return Arrays.stream(paths)
                    .flatMap(p -> readFileOrDir(path + File.separator + p));
        } else {
            try {
                return new BufferedReader(new java.io.FileReader(path)).lines();
            } catch (Exception e) {
                System.err.println("read file " + path + " error!" + e.getMessage());
                return Stream.empty();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.lines().forEach(s -> System.out.println(getProbability(s, 3)));
    }
}
