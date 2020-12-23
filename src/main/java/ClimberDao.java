import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class ClimberDao implements Dao<Climber, Integer> {
    private EntityManager entityManager;

    public ClimberDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Climber climber) {
        entityManager.persist(climber);

    }

    @Override
    public void update(Climber climber) {
        entityManager.merge(climber);

    }

    @Override
    public Climber getByPK(Integer integer) {
        return entityManager.find(Climber.class, integer);
    }

    @Override
    public void delete(Climber climber) {
        entityManager.remove(climber);

    }

    @Override
    public void deleteByPK(Integer integer) {
        Climber climber = getByPK(integer);
        if (climber != null) {
            delete(climber);
        }

    }

    public List<Climber> getSomeClimbers(int from, int to){
        TypedQuery<Climber> query = entityManager.createNamedQuery("Climber.getByAge", Climber.class);
        query.setParameter("from",from);
        query.setParameter("to",to);
        List<Climber> climbers = query.getResultList();
        return climbers;
    }
}
