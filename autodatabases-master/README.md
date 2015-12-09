# autodatabases
This is a module (library) to be used in an Android Application. 
This module is a mechanism to create relational databases from a java object without the need for SQL code . The module also provide methods for data manipulation. (CRUD)


// Table name

@Table(name = "Costumers")

public class Costumer extends Model {

    //All the Model subclasses must have a zero argument constructor or default constructor

    @Column(name = "Name") //column name
    private String name;

    @HasMany    //relations with other tables
    List<Address> adress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save(){
        super.save();
    }



}

