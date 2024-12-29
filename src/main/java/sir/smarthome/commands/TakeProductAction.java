package sir.smarthome.commands;

import sir.smarthome.common.Product;
import sir.smarthome.devices.Fridge;
import sir.smarthome.residents.Resident;

public class TakeProductAction extends BaseAction<Fridge, Resident>{
    Product product;

    public TakeProductAction(Fridge receiver, Resident executor, Product product) {
        super(receiver, executor);
        this.product = product;
    }

    @Override
    public void execute() {
        System.out.println("Taking product");
        receiver.takeProduct(product);
    }

    @Override
    public String toString() {
        return "TakeProductAction by " + executor.getName() + " with product: " + product.getName();
    }
}
