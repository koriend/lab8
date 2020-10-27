package graphic;

public class Thing {
    private String name;
    private String value;
    private String id;
    private String planet_name;
    private String date;
    private String user;


    public Thing(String[][] arr){
        this.name = arr[0][1];
        this.value = arr[1][1];
        this.id = arr[2][1];
        this.planet_name = arr[3][1];
        this.date = arr[7][1];
        this.user = arr[8][1];

    }






    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public String getPlanet_name() {
        return planet_name;
    }

    public String getValue() {
        return value;
    }
}
