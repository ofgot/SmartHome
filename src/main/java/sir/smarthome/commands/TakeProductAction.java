package sir.smarthome.commands;

import sir.smarthome.common.Product;
import sir.smarthome.devices.Fridge;
import sir.smarthome.residents.Resident;

/**
 * Represents an action to take a {@link Product} from a {@link Fridge}.
 * This action is performed by a {@link Resident} who retrieves a specific product from the fridge.
 */
public class TakeProductAction extends BaseAction<Fridge, Resident> {

    /**
     * The product to be taken from the fridge.
     */
    private final Product product;

    /**
     * Constructs a new {@code TakeProductAction} with the specified receiver, executor, and product.
     *
     * @param receiver the {@link Fridge} from which the product will be taken
     * @param executor the {@link Resident} who initiates the action
     * @param product  the {@link Product} to be taken from the fridge
     */
    public TakeProductAction(Fridge receiver, Resident executor, Product product) {
        super(receiver, executor);
        this.product = product;
    }

    /**
     * Executes the product retrieval action.
     * <p>
     * Takes the specified product from the {@link Fridge} and prints a message indicating the action.
     */
    @Override
    public void execute() {
        System.out.println("Taking product");
        receiver.takeProduct(product);
    }

    /**
     * Returns a string representation of the {@code TakeProductAction}.
     * <p>
     * The string includes the executor's name and the name of the product being taken.
     *
     * @return a string describing the action
     */
    @Override
    public String toString() {
        return "TakeProductAction by " + executor.getName() + " with product: " + product.getName();
    }
}
