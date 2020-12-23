import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class MountainDao implements Dao<Mountain, Integer> {

    private EntityManager entityManager;

    public MountainDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Mountain mountain) {
        entityManager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        entityManager.merge(mountain);

    }

    @Override
    public Mountain getByPK(Integer integer) {
        return entityManager.find(Mountain.class, integer);
    }

    @Override
    public void delete(Mountain mountain) {
        entityManager.remove(mountain);

    }

    @Override
    public void deleteByPK(Integer integer) {
        Mountain mountain = getByPK(integer);
        if(mountain != null)
            entityManager.remove(mountain);

    }

    public List<Mountain> getMountainByCountry(String country){
        TypedQuery<Mountain> query = entityManager.createNamedQuery("Mountain.getMountainByCountryName",
                Mountain.class);
        query.setParameter("country", country);
        List<Mountain> mountains = query.getResultList();
        return mountains;
    }

}
