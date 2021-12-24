package com.wh.designPattern.strategy;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

/**
 * 如果入参不是int[], 而是double，或者类比如Cat
 * 任何类想要排序 只要自行实现comparable接口即可
 * 具体类中强制类型转换 挺别扭 这个怎么优化呢 使用泛型！！
 * 泛型的类型检查是在编译的时候检查吗？ 是，真正运行起来进行类型擦除。。是不是看看编译后的代码就知道了
 *
 * 希望比较大小的策略去灵活指定
 * 对修改关闭，对扩展开放：尽量不动原来代码，怎么扩展呢，把排序策略也当做入参传入
 * 策略： 封装了做一件事情的不同方式 ，即多态
 * 应用： Tank.fire   不同的打子弹方式：一颗子弹， 四个方向同时打子弹， 打核弹
 * 消消乐  不同的消除方式：三个一排， 四个一排， 冰块， 火箭
 * 我理解贪吃蛇 和 俄罗斯方块 不是策略不同，吃的方式只有一种，就是碰到了 或者 一行满了
 */
public class Sorter {

    public static void sort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void sort(Cat[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i].compareTo(arr[j]) > 0){
                    Cat temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void sort(Comparable[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i].compareTo(arr[j]) > 0){
                    Comparable temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void sort(Comparable[] arr, Comparator comparator){
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                if(comparator.compare(arr[i], arr[j]) > 0){
                    Comparable temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

//    // 在类上加泛型 则方法不能静态       Sorter<Dog> sorter = new Sorter<>();
//    public void sort(T[] arr, Comparator comparator){
//        for(int i = 0; i < arr.length; i++){
//            for(int j = i+1; j < arr.length; j++){
//                if(comparator.compare(arr[i], arr[j]) > 0){
//                    T temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
//        int[] arr = new int[]{3,2,5,4,7,8};
//        arr = Sorter.sort(arr);
//        for (int i: arr) {
//            System.out.println(i);
//        }
//        Cat[] arr = new Cat[]{new Cat(1, 5), new Cat(5, 1), new Cat(3, 4)};
//        Sorter.sort(arr);
//        for (Cat i: arr) {
//            System.out.println(i);
//        }
//        Dog[] arr = new Dog[]{new Dog(1), new Dog(5), new Dog(3)};
//        Sorter.sort(arr);
//        for (Dog i: arr) {
//            System.out.println(i);
//        }

//        Cat[] arr = new Cat[]{new Cat(1, 5), new Cat(5, 1), new Cat(3, 4)};
//        System.out.println("按身高排序");
//        Sorter.sort(arr, new CatHeightComparator());
//        for (Cat i: arr) {
//            System.out.println(i);
//        }
//        System.out.println("按体重排序");
//        Sorter.sort(arr, new CatWeightComparator());
//        for (Cat i: arr) {
//            System.out.println(i);
//        }

        System.out.println("排狗狗");
        Dog[] arr = new Dog[]{new Dog(1), new Dog(5), new Dog(3)};

        Sorter.sort(arr, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                if(o1.weight > o2.weight){
                    return 1;
                } else if(o1.weight < o2.weight){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
//        函数式接口写法报错，拿不到weight，不知道为啥老师的视频里就可以
//        Sorter.sort(arr, (o1, o2)->{
//            if(o1.weight > o2.weight){
//                return 1;
//            } else if(o1.weight < o2.weight){
//                return -1;
//            } else {
//                return 0;
//            }
//        });
        for (Dog i: arr) {
            System.out.println(i);
        }
    }
}
