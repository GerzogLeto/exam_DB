
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity//делаем класс сущностью - в базе будет таблица
@NamedQueries({
        @NamedQuery(name = "Mountain.getMountainByCountryName",
                query = "SELECT m FROM Mountain m WHERE m.country = :country"), // JPQL
})
public class Mountain extends BaseIdentify{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int height;


    public Mountain() {
    }

    public Mountain(String name, String country, int height) throws Exception {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 4) {
            throw new Exception("name of mountain should contain 4 or more characters");
        } else {
            this.name = name;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws Exception {
        if (country.length() < 4) {
            throw new Exception("country of mountain should contain 4 or more characters");
        } else {
            this.country = country;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws Exception {
        if (height < 100) {
            throw new Exception("height of mountain should be 100 or more metres");
        } else {
            this.height = height;
        }
    }
}
