import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class GroupClimbersDao implements Dao<GroupClimbers, Integer> {
    private EntityManager entityManager;

    public GroupClimbersDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(GroupClimbers groupClimbers) {
        entityManager.persist(groupClimbers);

    }

    @Override
    public void update(GroupClimbers groupClimbers) {
        entityManager.merge(groupClimbers);

    }

    @Override
    public GroupClimbers getByPK(Integer integer) {
        return entityManager.find(GroupClimbers.class, integer);
    }

    @Override
    public void delete(GroupClimbers groupClimbers) {
        entityManager.remove(groupClimbers);

    }

    @Override
    public void deleteByPK(Integer integer) {
        GroupClimbers groupClimbers = getByPK(integer);
        if(groupClimbers != null){
            delete(groupClimbers);
        }

    }

    public List<GroupClimbers> getGroupsByMountainName(String mountainName){
        TypedQuery<GroupClimbers> query = entityManager.createNamedQuery("GroupClimbers.getByMountainName",
                GroupClimbers.class);
        query.setParameter("name",mountainName);
        List<GroupClimbers> groups = query.getResultList();
        return groups;
    }


    public List<GroupClimbers> getGroupsByConditionIsOpen(Boolean condition){
        TypedQuery<GroupClimbers> query = entityManager.createNamedQuery("GroupClimbers.getByConditionIsOpen",
                GroupClimbers.class);
        query.setParameter("condition",condition);
        List<GroupClimbers> groups = query.getResultList();
        return groups;
    }
}

