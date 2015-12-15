package Domain;

/**
 * Created by noe.rosell on 14/12/15.
 */
public interface PermisionsRepository {
    public boolean add(Role role);
    public Role get(int index);
}
