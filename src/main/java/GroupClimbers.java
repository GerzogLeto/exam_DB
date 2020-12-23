import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group_climbers")
@NamedQueries({
        @NamedQuery(name = "GroupClimbers.getByMountainName",
                query = "SELECT g FROM GroupClimbers g JOIN FETCH g.mountain m  WHERE m.name = :name"), // JPQL
        @NamedQuery(name = "GroupClimbers.getByConditionIsOpen",
                query = "SELECT g FROM GroupClimbers g WHERE g.isOpen = :condition"), // JPQL
})
public class GroupClimbers extends BaseIdentify {

    private int capacity = 0;

    @ManyToOne(fetch = FetchType.LAZY)//описание связей сущностей
    @JoinColumn(nullable = false)
    //присоед столбец который появиться у группы от горы по умолчанию это ключ
    private Mountain mountain;

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int duration;

    @ManyToMany//связь многие ко многим форм доп таблица по умолчанию с двумя полями
    @JoinTable(name = "cl_group",
            joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "cl_id"))
    //joinColumns = @JoinColumn(name = "group_id") - текущая сторона
    //inverseJoinColumns = @JoinColumn(name = "cl_id") - обратная сторона со (стороны студента)
    private List<Climber> climbers = new ArrayList<>();


    public GroupClimbers() {
    }

    public GroupClimbers(boolean isOpen, LocalDate date, int duration, Mountain mountain) {
        this.isOpen = isOpen;
        this.date = date;
        this.duration = duration;
        this.mountain = mountain;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = mountain;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Climber> getClimbers() {
        return climbers;
    }

    public void setClimbers(List<Climber> climbers) {
        this.climbers = climbers;
    }

    public boolean addClimber(Climber climber){
        if(isOpen){
            climbers.add(climber);
            capacity++;
            if(capacity == 7) setOpen(false);
            return true;
        }
        return false;
    }
}
