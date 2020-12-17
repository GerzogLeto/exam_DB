import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Group extends BaseIdentify {

    @Column(nullable = false)
    private Mountain mountain;

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int duration;

    private List<Climber> climbers = new ArrayList<>();


    public Group() {
    }

    public Group(Mountain mountain, boolean isOpen, LocalDate date, int duration, List<Climber> climbers) {
        this.mountain = mountain;
        this.isOpen = isOpen;
        this.date = date;
        this.duration = duration;
        this.climbers = climbers;
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
}
