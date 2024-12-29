package sir.smarthome.commands;

import sir.smarthome.common.Product;
import sir.smarthome.devices.Fridge;
import sir.smarthome.residents.Resident;

public class LoadProductAction extends BaseAction<Fridge, Resident>{
    private Product product;

    public LoadProductAction(Fridge receiver, Resident executor, Product product) {
        super(receiver, executor);
        this.product = product;
    }

    @Override
    public void execute() {
        System.out.println("Loading product");
        receiver.loadProduct(product);
    }

    @Override
    public String toString() {
        return "LoadProductAction by " + executor.getName() + " with product: " + product.getName();
    }
}
