package owner.code.demo.transitive;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * # 值传递和引用传递
 * 基本类型变量是值传递，不会改变本身的值
 * 对象类型是引用传递，对其操作会改变自身的值
 * 这里要注意：
 * 1. 如果操作方法中new了一个新的对象，对原值没有影响
 * 2. 如果对象的class类型是final类型的，操作方法中改变其值相当于是new一个新对象，对原值也没有影响
 */
@Data
public class ObjectCar {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        //测试int传递
        int args1 = 2;
        method(args1);
        System.out.println("测试int值传递：" + args1);//2
        //测试Integer传递
        Integer args2 = 2;
        method1(args2);
        System.out.println("测试Integer引用传递：" + args2);//2
        //测试String传递
        String args3 = "2";
        method2(args3);
        System.out.println("测试String引用传递：" + args3);//"2"
        //测试ObjectCar传递
        ObjectCar car = new ObjectCar();
        car.setAge(2);
        car.setName("2");
        method3(car);
        System.out.println("测试ObjectCar引用传递(非new)：" + car.getAge());//"10"
        //测试List<Integer>传递
        List<Integer> list = new ArrayList<>();
        list.add(2);
        method4(list);
        System.out.println("测试List<Integer>引用传递(非new)：" + list);//[2, 10]
        //测试ObjectCar传递
        ObjectCar car2 = new ObjectCar();
        car2.setAge(2);
        car2.setName("2");
        method5(car2);
        System.out.println("测试ObjectCar引用传递(new)：" + car2.getAge());//"2"
        //测试List<Integer>传递
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        method6(list2);
        System.out.println("测试List<Integer>引用传递(new)：" + list2);//[2]
        Integer intA = 68;
        Integer intA1 = 68;
        Integer intB = new Integer(68);
        Integer intC = Integer.valueOf(68);
        System.out.println("68==比较："+(intA == intA1));  //true
        System.out.println("68 equal比较："+(intA.equals(intA1))); //true
        System.out.println("68==比较（new）："+(intA == intB)); //false
        System.out.println("68 equal比较（new）："+(intA.equals(intB))); //true
        System.out.println("68==比较（valueOf）："+(intA == intC)); //true
        System.out.println("68 equal比较（valueOf）："+(intA.equals(intC))); //true
    }

    public static void method(int age) {
        age = 10;
    }

    public static void method1(Integer age) {
        age = 10;
    }

    public static void method2(String name) {
        name = "10";
    }

    public static void method3(ObjectCar obj) {
        obj.setAge(10);
        obj.setName("10");
    }

    public static void method4(List<Integer> list) {
        list.add(10);
    }

    public static void method5(ObjectCar obj) {
        obj = new ObjectCar();
        obj.setAge(10);
        obj.setName("10");
    }

    public static void method6(List<Integer> list) {
        list = new ArrayList<>();
        list.add(10);
    }
}
