public class Position extends Employee {
    private String title;
    private String function;
    private String requirement;
    private double salary;

    public Position(String name, int age, String company, int experience, String title, String function, String requirement, double salary) {
        super(name, age, company, experience);
        this.title = title;
        this.function = function;
        this.requirement = requirement;
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    @MetodAnnotation
    public void setTitle(@ParameterAnnotation String title) {
        this.title = title;
    }

    @MetodAnnotation
    public String getFunction() {
        return function;
    }

    public void setFunction(@ParameterAnnotation String function) {
        this.function = function;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @MetodAnnotation
    public double getSalary() {
        return salary;
    }

    @MetodAnnotation
    public void setSalary(@ParameterAnnotation double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", function='" + function + '\'' +
                ", requirement='" + requirement + '\'' +
                ", salary=" + salary +
                '}';
    }
}


