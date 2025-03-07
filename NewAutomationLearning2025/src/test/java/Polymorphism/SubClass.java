package Polymorphism;

public class SubClass extends SuperClass{

    public void superOverridingMethod(){
        System.out.println("this is subclass overriden method methodOne");
    }

    public void subClassMethodOne(){
        System.out.println("this is subclass method");
    }

    public void subClassMethodTwo(){
        System.out.println("this is subclass method two");
    }

    public void callingSuperClassMethods(){
        super.superOverridingMethod();
        super.superMethodOne();
        super.superMethodTwo();
    }

    public static void main(String[] args) {
        SuperClass obj1 = new SubClass();       //upcasting: access all parent methods and overriden methods in subclass
        obj1.superOverridingMethod();           //calling subclass overriden method
        obj1.superMethodOne();
        obj1.superMethodTwo();

        SubClass obj2 = new SubClass();     //it can access everything
        obj2.subClassMethodOne();
        obj2.subClassMethodTwo();
        obj2.superMethodOne();
        obj2.superOverridingMethod();       //which is defined in subclass
        obj2.superMethodTwo();
        obj2.callingSuperClassMethods();    //which is defined in superclass



    }
}
