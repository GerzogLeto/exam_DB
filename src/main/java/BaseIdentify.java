

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass//сам класс сущн не будет и табл на его основе не создаются
public abstract class BaseIdentify {
    @Id//это уникальный идентфтикатор
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ключ генерируется автоматически.без этой аннотации
    //автогенерация не работает
    //автогенерация работает только типом поля int
    private int id;

    public int getId() {
        return id;
    }
}
