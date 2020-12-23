import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "climber")
@NamedQueries({
        @NamedQuery(name = "Climber.getByAge",
                query = "SELECT c FROM Climber c WHERE c.age >= :from and c.age < :to" ), // JPQL
})
public class Climber extends BaseIdentify {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "climbers")
    private List<GroupClimbers> groupClimbers = new ArrayList<>();

    public Climber() {
    }

    public Climber(String name, String address, int age) throws Exception {
        setName(name);
        setAddress(address);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 3) {
            throw new Exception("name of climber should contain 3 or more characters");
        } else {
            this.name = name;
        }
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        if (address.length() < 5) {
            throw new Exception("address of climber should contain 5 or more characters");
        } else {
            this.address = address;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if (age < 18) {
            throw new Exception("age of climber should be 18 or more years");
        } else {
            this.age = age;
        }
    }

    public List<GroupClimbers> getGroups() {
        return groupClimbers;
    }

    public void setGroups(List<GroupClimbers> groupClimbers) {
        this.groupClimbers = groupClimbers;
    }
}
