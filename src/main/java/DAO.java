import model.User;

import java.util.List;

public interface DAO {

    void create();

    void update();

    List<User> get();

    void remove();
}
