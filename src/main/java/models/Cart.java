package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<LineItem> items;

    public Cart() {
        items = new ArrayList<LineItem>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        int id = item.getProduct().getId();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getId()== id) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
    public void Clear(LineItem item) {
        int id = item.getProduct().getId();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getId()== id) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.clear();
    }
    public void removeItem(LineItem item) {
        int id = item.getProduct().getId();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getId()==id) {
                items.remove(i);
                return;
            }
        }
    }
}