public class Human {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MetodAnnotation
    public String getGender() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setGender(@ParameterAnnotation String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "gender='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
