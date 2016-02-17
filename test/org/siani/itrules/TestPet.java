package org.siani.itrules;

import org.junit.Test;
import org.siani.itrules.model.Frame;

/**
 * Created by Osvaldo on 1/29/2016.
 */
public class TestPet {
    @Test
    public void testPets() throws Exception {
        Frame person = new Frame();
        person.addTypes("Person");
        person.addFrame("Name", "Osvaldo"," fariñas"," fernández");
        Frame [] r = new Frame[]{new Frame()};
        person.addFrame("PetsCount",3);
        person.addFrame("Pets", new Frame().addTypes("Cat").addFrame("Name", "Missy").addFrame("age", 1));
        person.addFrame("Pets", new Frame().addTypes("Dog").addFrame("Name","Toby").addFrame("age",3));
        person.addFrame("Pets",  new Frame().addTypes("Dog").addFrame("Name","Toby").addFrame("age",3));
        TemplateEngine templateEngine = new TemplateEngine().use("test-res/template/CustomCondition.itr");
        System.out.println(templateEngine.render(person));
    }

    @org.junit.Test
    public void testasd() throws Exception {


    }

    //    public static class Person {
//        private String name;
//        private Pet[] pets;
//
//        public Person(String name, Pet... pets) {
//            this.name = name;
//            this.pets = pets;
//        }
//
//        public static void main(String[] args) {
//            Person person = new Person("Roger Dickens",
//                    new Dog("Ruffo", 5),
//                    new Cat("Missy", 1),
//                    new Dog("Toby", 3)
//            );
//
//            TemplateEngine engine = new TemplateEngine().use("testDate-res/template/CustomCondition.itr");
//            engine.add("one", (trigger, parameter) -> trigger.frame().isPrimitive() && trigger.frame().value().equals(1));
//            System.out.println(engine.render(person));
//        }
//
//        public static abstract class Pet {
//            private String name;
//            private int age;
//
//            public Pet(String name, int age) {
//                this.name = name;
//                this.age = age;
//            }
//        }
//
//        public static class Dog extends Pet {
//            public Dog(String name, int age) {
//                super(name, age);
//            }
//        }
//
//        public static class Cat extends Pet {
//            public Cat(String name, int age) {
//                super(name, age);
//            }
//        }
//    }
}
