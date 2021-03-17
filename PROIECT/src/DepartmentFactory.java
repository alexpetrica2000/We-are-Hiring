public class DepartmentFactory {
    public static Department createDepartment(String name){
            if(name.equals("IT"))
                return new IT();
            if(name.equals("Marketing"))
                return new Marketing();
            if(name.equals("Management"))
                return new Management();
            if(name.equals("Finance"))
                return new Finance();

            return null;
    }
}
