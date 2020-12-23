import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class ApplicationApp {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();
        ClimberDao climberDao = new ClimberDao(manager);
        GroupClimbersDao groupsDao = new GroupClimbersDao(manager);
        MountainDao mountainDao = new MountainDao(manager);

        Climber[] climbers = new Climber[]{
                new Climber("Oskar", "Moscow, Lenin street", 34),
                new Climber("Bratislav", "Tomsk, Pobeda street", 24),
                new Climber("Sergei", "Minsk, Mir street", 45),
                new Climber("Nikolay", "Kiev, Kr.comminarov street", 36),
                new Climber("Vladimir", "Penza, Oktyabrya street", 48),
                new Climber("Denis", "Boston, 12 street", 18),
                new Climber("Svetlana", "Podolsk, Pobeda street", 26),
                new Climber("Sam", "Virginia, 15 street", 47),
                new Climber("Rokky", "Kansas, west street", 27),
                new Climber("Vadim", "Rostov, Lenin street", 43)
        };

        Mountain[] mountains = new Mountain[]{
                new Mountain("Elbrus", "Russia", 5642),
                new Mountain("Monoblan", "France", 4809),
                new Mountain("PikPobedi", "China", 7439)
        };

        GroupClimbers[] groups = new GroupClimbers[]{
                new GroupClimbers(true, LocalDate.of(2021, 6, 20), 10, mountains[0]),
                new GroupClimbers(true, LocalDate.of(2021, 5, 15), 15, mountains[1]),
                new GroupClimbers(true, LocalDate.of(2021, 8, 10), 10, mountains[2])
        };

        for (int i = 0; i < 10; i++) {
            if (i < 3) {
                if (groups[0].addClimber(climbers[i]))
                    climbers[i].getGroups().add(groups[0]);
            }
            if (i >= 3 && i < 6) {
                if (groups[1].addClimber(climbers[i]))
                    climbers[i].getGroups().add(groups[1]);
                if (groups[2].addClimber(climbers[i]))
                    climbers[i].getGroups().add(groups[2]);
            }
            if (i >= 6 && i < 10) {
                if (groups[2].addClimber(climbers[i]))
                    climbers[i].getGroups().add(groups[2]);
            }
        }

        manager.getTransaction().begin();
        for (Mountain mountain : mountains) {
            mountainDao.add(mountain);
        }
        manager.getTransaction().commit();//unmanaged

        manager.getTransaction().begin();
        for (Climber climber : climbers) {
            climberDao.add(climber);//managed
        }
        manager.getTransaction().commit();//unmanaged

        manager.getTransaction().begin();
        for (GroupClimbers group : groups) {
            groupsDao.add(group);
        }
        manager.getTransaction().commit();//unmanaged

        //request of climbers aged from to
        manager.getTransaction().begin();
        List<Climber> climberList = climberDao.getSomeClimbers(30,46);
        manager.getTransaction().commit();//unmanaged
        for (Climber climber : climberList) {
            System.out.println("Name: " + climber.getName() +"\n"+
                    "Address: " + climber.getAddress() + "\n" +
                    "Age: " + climber.getAge());
        }

        //request of groups climbers by mountain
        manager.getTransaction().begin();
        List<GroupClimbers> groupClimbers = groupsDao.getGroupsByMountainName("Elbrus");
        manager.getTransaction().commit();//unmanaged
        for (GroupClimbers group : groupClimbers) {
            System.out.println("id: " + group.getId() +"\n"+
                    "mountain: " + group.getMountain().getName() + "\n" +
                    "date: " + group.getDate());
        }
        //request of list mountains by name country
        manager.getTransaction().begin();
        List<Mountain> mountains1 = mountainDao.getMountainByCountry("Russia");
        manager.getTransaction().commit();//unmanaged
        for (Mountain mount : mountains1) {
            System.out.println("id: " + mount.getId() +"\n"+
                    "name: " + mount.getName() + "\n" +
                    "height: " + mount.getHeight());
        }
        //request of list groups of climbers by condition is open
        manager.getTransaction().begin();
        List<GroupClimbers> groupClimbers1 = groupsDao.getGroupsByConditionIsOpen(true);;
        manager.getTransaction().commit();//unmanaged
        for (GroupClimbers group : groupClimbers1) {
            System.out.println("id: " + group.getId() +"\n"+
                    "mountain: " + group.getMountain().getName() + "\n" +
                    "date: " + group.getDate());

        }
        manager.close();


    }
}
