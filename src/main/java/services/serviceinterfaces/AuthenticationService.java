package services.serviceinterfaces;

public interface AuthenticationService {
    public boolean authenticateAdmin(String username, String password);
    public boolean authenticateRecruiter (String username, String password);
    public boolean authenticateEmployee (String username, String password);
    public Object getUser(String entityName, String username, String password);

}
