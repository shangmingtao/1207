package cn.milo.jd;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Strings.repeat;
import static com.google.common.collect.Sets.newHashSet;


/**
 * Hello world!
 *
 */
public class demo
{



    public static void GuavaOptional(){
        List<String> list = new LinkedList<String>();
        list.add("A");
        list.add(null);
        list.add("C");
        list.add(null);
        list.add("E");
        list.add("");
        Optional<String> possible;
        for(int i = 0 ; i < list.size(); i++){
            possible = Optional.fromNullable(emptyToNull(list.get(i)));
            System.out.println("index: "+i+" -  value: "+possible.or("no value"));
        }
    }

    public static void GuavaPreconditions(String name, int age, String desc){
        Preconditions.checkNotNull(name, "name may not be null");
        Preconditions.checkArgument(age >= 18 && age < 99, "age must in range (18,99)");
        Preconditions.checkArgument(desc !=null && desc.length() < 10, "desc too long, max length is ", 10);
    }

    public static void GuavaObjects(){
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal("null", "a"));
        System.out.println(Objects.equal("a", "null"));
        System.out.println(Objects.equal("null", "null"));
    }

    //结果为真返回0 为假返回-1
    public static int GuavaCompareTo(){
        String aString = "aString";
        String bString = "aString";
        int aint = 1;
        int bint = 1;
        return ComparisonChain.start()
                .compare(aint,bint)
                .compare(aString,bString)
                .result();
    }

    public static void GuavaOrdering(){
        //例子来自 ：https://www.cnblogs.com/peida/p/Guava_Ordering.html
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");
        list.add("abc");

        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();

        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));
        System.out.println("reverse:"+naturalOrdering.reverse().sortedCopy(list));
    }

    public static void GuavaStrings(){
        String input = "";
        boolean isNummOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNummOrEmpty?"is ":"isnot") + "null or empty.");
    }

    public static void GuavaStrings2(){
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }

    public static void GuavaStrings3(){
        String padEndResult = Strings.padEnd("123", 5, '2');
        System.out.println("padEndResult is " + padEndResult);

        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);
    }

    public static void GuavaSplitter(){
        //其后紧跟的trimResults()方法表示要对结果做trim，omitEmptyStrings()表示忽略空字符串
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults() //去除空串
                .omitEmptyStrings()
                .split("hello,  word,,世界，水平,    ,");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }

    public static void GuavaSplitter2(){
        String toSplitString = "a=  b;c=d,e=f,,   ,";
        Map<String,String> kvs = Splitter.onPattern("[,;]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator('=')
                .split(toSplitString);
        for (Map.Entry<String,String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }
    }

    public static void GuavaJoiner(){
        String joinResult = Joiner.on(" ").join(new String[]{"hello","world"});
        System.out.println(joinResult);
    }


    public static void GuavaJoiner2(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }

    public static void GuavaImmutableSet(){
        ImmutableSet<String> immutableNamedColors = ImmutableSet.<String>builder()
                .add("red", "green","black","white","grey")
                .build();
//        immutableNamedColors.add("abc");
        for (String color : immutableNamedColors) {
            System.out.println(color);
        }
        System.out.println(immutableNamedColors.asList());
    }

    public static void GuavaMultiset(){
        /*
        HashMultiset: 元素存放于 HashMap
        LinkedHashMultiset: 元素存放于 LinkedHashMap，即元素的排列顺序由第一次放入的顺序决定
        TreeMultiset:元素被排序存放于TreeMap
        EnumMultiset: 元素必须是 enum 类型
        ImmutableMultiset: 不可修改的 Mutiset
         */
        Multiset multiset = HashMultiset.create();
        String sentences = "this is a story, there is a good girl in the story.";
        Iterable<String> words = Splitter
                .onPattern("[^a-z]{1,}")
                .omitEmptyStrings()
                .trimResults()
                .split(sentences);
        for (String word : words) {
            multiset.add(word);
        }

         for (Object element : multiset.elementSet()) {
            System.out.println((String)element + ":" + multiset.count(element));
        }
    }

    public static void GuavaBiMap(){
        BiMap<String,String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一","Monday");
        weekNameMap.put("星期二","Tuesday");
        weekNameMap.put("星期三","Wednesday");
        weekNameMap.put("星期四","Thursday");
        weekNameMap.put("星期五","Friday");
        weekNameMap.put("星期六","Saturday");
        weekNameMap.put("星期日","Sunday");

        System.out.println("星期日的英文名是" + weekNameMap.get("星期日"));
        System.out.println("Sunday的中文是" + weekNameMap.inverse().get("Sunday"));
    }

    public static void GuavaMultimap(){
        Multimap<String, String> myMultimap = ArrayListMultimap.create();

        // 添加键值对
        myMultimap.put("Fruits", "Bannana");
        //给Fruits元素添加另一个元素
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // 获得multimap的size
        int size = myMultimap.size();
        System.out.println(size);  // 4

        // 获得Fruits对应的所有的值
        Collection<String> fruits = myMultimap.get("Fruits");
        System.out.println(fruits); // [Bannana, Apple, Pear]

        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println(vegetables); // [Carrot]

        //遍历Mutlimap
        for(String value : myMultimap.values()) {
            System.out.println(value);
        }

        // Removing a single value
        myMultimap.remove("Fruits","Pear");
        System.out.println(myMultimap.get("Fruits")); // [Bannana, Pear]

        // Remove all values for a key
        myMultimap.removeAll("Fruits");
        System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
    }

    public static void GuavaCharMatcher(){
        //示例都是这个博客的 https://blog.csdn.net/u010738033/article/details/79594427
        System.out.println(CharMatcher.is('a').matchesAllOf("aaa"));
        System.out.println(CharMatcher.is('a').matchesAnyOf("aba"));
        System.out.println(CharMatcher.is('a').matchesNoneOf("aba"));
        System.out.println(CharMatcher.is('a').countIn("aaa"));
        System.out.println(CharMatcher.is('a').indexIn("aj4va"));
        System.out.println(CharMatcher.is('a').retainFrom("bazaar"));
        System.out.println(CharMatcher.is('a').removeFrom("bazaar"));
        System.out.println(CharMatcher.anyOf("ab").trimFrom("abacatbab"));

    }

    public static void GuavaDiffent(){
        HashSet setA = newHashSet(1, 2, 3, 4, 5);
        HashSet setB = newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Object integer : union)
            System.out.println(integer);

        Sets.SetView difference = Sets.difference(setA, setB);
        System.out.println("difference:");
        for (Object integer : difference)
            System.out.println(integer);

        Sets.SetView intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:");
        for (Object integer : intersection)
            System.out.println(integer);
    }

    public static void GuavaTable(){
        Table<Integer, Integer, String> table = HashBasedTable.create();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 5; column++) {
                table.put(row, column, "value of cell (" + row + "," + column + ")");
            }
        }
        for (int row=0;row<table.rowMap().size();row++) {
            Map<Integer,String> rowData = table.row(row);
            for (int column =0;column < rowData.size(); column ++) {
                System.out.println("cell(" + row + "," + column + ") value is:" + rowData.get(column));
            }
        }
    }

    public static void GuavaCache() throws Exception {
        // https://blog.csdn.net/u012859681/article/details/75220605 写的很好
        CacheLoader<String, String> loader = new CacheLoader<String, String> () {
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        LoadingCache<String,String> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(3)
                .build(loader);//在构建时指定自动加载器

        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");
        System.out.println(loadingCache.get("key1"));
        Thread.currentThread().sleep(5000);
        System.out.println(loadingCache.get("key1"));
        Thread.currentThread().sleep(5000);
        loadingCache.get("key1");
    }

    public static void main( String[] args ) throws Exception {
        //大部分源于 : https://www.cnblogs.com/wihainan/p/7091775.html

        //importnew :  http://www.importnew.com/tag/guava

//        GuavaOptional();
//        GuavaPreconditions("Jim", 19, "hello world, hello java");
//        GuavaObjects();
//        System.out.println(GuavaCompareTo());
//        GuavaOrdering();
//        GuavaStrings();
//        GuavaStrings2();
//        GuavaStrings3();
//        GuavaSplitter();
//        GuavaSplitter2();
//        GuavaJoiner();
//        GuavaJoiner2();
//        GuavaImmutableSet();
//        GuavaMultiset();
//        GuavaBiMap();
//        GuavaMultimap();
//        GuavaCharMatcher();
//        GuavaDiffent();
//        GuavaTable();
        GuavaCache();
    }

}
