import java.util.List;

public class City {
    private int id;
    private String name;
    private List<Route> routes;

    public City(int id, String name, List<Route> routes) {
        this.id = id;
        this.name = name;
        this.routes = routes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void removeRoute(Route route) {

    }
}
