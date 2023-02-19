public class Employee extends Human{
    private String company;
    private int experience;

    public Employee(String name, int age, String company, int experience) {
        super(name, age);
        this.company = company;
        this.experience = experience;
    }

    public String getCompany() {
        return company;
    }

    @MetodAnnotation
    public void setCompany(@ParameterAnnotation String company) {
        this.company = company;
    }

    public int getExperience() {
        return experience;
    }

    @MetodAnnotation
    public void setExperience(@ParameterAnnotation int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "company='" + company + '\'' +
                ", experience=" + experience +
                '}';
    }
}
