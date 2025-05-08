package sir.smarthome.commands;

import sir.smarthome.common.Product;
import sir.smarthome.device_service.devices.Fridge;

import java.util.logging.Logger;

/**
 * Represents an action to load a {@link Product} into a {@link Fridge}.
 */
public class LoadProductAction extends BaseAction<Fridge> {
    private static final Logger logger = Logger.getLogger(LoadProductAction.class.getName());
    /**
     * The product to be loaded into the fridge.
     */
    private final Product product;

    /**
     * Constructs a new {@code LoadProductAction} with the specified receiver, executor, and product.
     *
     * @param receiver the {@link Fridge} where the product will be loaded
     * @param product  the {@link Product} to be loaded into the fridge
     */
    public LoadProductAction(Fridge receiver, Product product) {
        super(receiver);
        this.product = product;
    }

    /**
     * Executes the product loading action.
     * <p>
     * Loads the specified product into the {@link Fridge} and prints a message indicating the action.
     */
    @Override
    public void execute() {
        logger.info("Loading product");
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
        return "LoadProductAction with product: " + product.getName();
    }
}
