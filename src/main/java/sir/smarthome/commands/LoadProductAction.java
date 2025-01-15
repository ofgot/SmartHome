package sir.smarthome.commands;

import sir.smarthome.common.Product;
import sir.smarthome.devices.Fridge;
import sir.smarthome.residents.Resident;

/**
 * Represents an action to load a {@link Product} into a {@link Fridge}.
 * This action is performed by a {@link Resident} who loads a specific product into the fridge.
 */
public class LoadProductAction extends BaseAction<Fridge, Resident> {

    /**
     * The product to be loaded into the fridge.
     */
    private final Product product;

    /**
     * Constructs a new {@code LoadProductAction} with the specified receiver, executor, and product.
     *
     * @param receiver the {@link Fridge} where the product will be loaded
     * @param executor the {@link Resident} who initiates the action
     * @param product  the {@link Product} to be loaded into the fridge
     */
    public LoadProductAction(Fridge receiver, Resident executor, Product product) {
        super(receiver, executor);
        this.product = product;
    }

    /**
     * Executes the product loading action.
     * <p>
     * Loads the specified product into the {@link Fridge} and prints a message indicating the action.
     */
    @Override
    public void execute() {
        System.out.println("Loading product");
        receiver.loadProduct(product);
    }

    /**
     * Returns a string representation of the {@code LoadProductAction}.
     * <p>
     * The string includes the executor's name and the name of the product being loaded.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "LoadProductAction by " + executor.getName() + " with product: " + product.getName();
    }
}
