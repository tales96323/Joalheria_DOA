package doa_jewelry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Manager") // Valor discriminador para esta subclasse
public class Manager extends Employee {

    @Column(name = "sales_goal", nullable = false)
    private double salesGoal;

    // Construtor padrão (obrigatório para JPA)
    public Manager() {
        super();
    }

    // Construtor com argumentos
    public Manager(String name, String nif, LocalDate hireDate, double salary, double salesGoal) {
        super(name, nif, hireDate, salary);
        this.salesGoal = salesGoal;
    }

    public double getSalesGoal() {
        return salesGoal;
    }

    public void setSalesGoal(double salesGoal) {
        this.salesGoal = salesGoal;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "ID=" + getId() +
                ", Nome='" + getName() + '\'' +
                ", NIF='" + getNif() + '\'' +
                ", Data de Contratação=" + getHireDate() +
                ", Salário=" + getSalary() +
                ", Goals=" + salesGoal +
                '}';
    }
}
