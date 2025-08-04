abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark!");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();       // Create an object of Dog
        dog.makeSound();           // Call the method
    }
}
