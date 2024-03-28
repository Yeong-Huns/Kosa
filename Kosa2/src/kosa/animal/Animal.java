package kosa.animal;

public abstract class Animal {
    private int size;
    private int age;
    private String name;

    private String species;
    private Animal() {
    }

    public String getName(){
        return this.name;
    }

    public Animal(int size, int age, String name, String species) {
        this.size = size;
        this.age = age;
        this.name = name;
        this.species = species;
    }

    public Animal(String name, String species) {
        this.name = name;
        this.age = 0;
        this.size = 0;
        this.species = species;
    }

    public void getStatus(){
        System.out.println(this.name+"은(는) " + this.species + "이고, 나이는 " + this.age + ", 크기는 " + this.size + "입니다." );
    }

    public String getNameAndSpecies(){
        return this.species + " " + this.name + "이(가) ";
    }

    public void move(String how){
        System.out.println(getNameAndSpecies()+ how + " 움직입니다.");
    }

    public void eat(String what){
        System.out.println(getNameAndSpecies() + "먹습니다.");
    }

    public void bark(String how){
        System.out.println(getNameAndSpecies() + how);
    }
}
