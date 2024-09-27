package Database;

import Model.ModelBase;
import java.util.List;

public class Repository<T extends ModelBase> {
    private DbContext context;

    // Constructor to initialize DbContext
    public Repository(DbContext context) {
        this.context = context;
    }

    // Create a new model
    public T create(T aModel) {
        // Logic to create a model in the database
        System.out.println("Creating model: " + aModel);
        return aModel;
    }

    // Delete a model
    public boolean delete(T aModel) {
        // Logic to delete a model from the database
        System.out.println("Deleting model: " + aModel);
        return true;  // Return true if successful
    }

    // Update a model
    public T update(T aModel) {
        // Logic to update a model in the database
        System.out.println("Updating model: " + aModel);
        return aModel;
    }

    // Select a model by ID
    public T selectById(long id) {
        // Logic to select a model by its ID
        System.out.println("Selecting model by ID: " + id);
        return null;  // Return the found model or null
    }

    // Select models based on a query
    public List<T> selectByQuery(String aQuery) {
        // Logic to select models based on a query
        System.out.println("Selecting models by query: " + aQuery);
        return null;  // Return a list of models
    }
}
