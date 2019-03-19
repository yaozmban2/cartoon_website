/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/4 10:00
 **/
public class Test2 {

    public static void main(String[] args) {
        SingleClass singleClass = SingleClass.getSingle();
        System.out.println(singleClass);
        singleClass.see();
    }

}

class SingleClass {
    private SingleClass() {};

    public static SingleClass getSingle() {
        return CreateSingleClass.singleClass;
    }

    public void see() {
        System.out.println("看见了什么");
    }

    private static class CreateSingleClass {
        private static SingleClass singleClass = new SingleClass();
    }
}
