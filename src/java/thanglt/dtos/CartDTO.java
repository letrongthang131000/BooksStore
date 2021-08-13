/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thang
 */
public class CartDTO implements Serializable {

    private Map<Integer, OrderDetailDTO> items;

    public CartDTO() {
    }

    public CartDTO(Map<Integer, OrderDetailDTO> items) {
        this.items = items;
    }

    public Map<Integer, OrderDetailDTO> getItems() {
        return items;
    }

    public void setItems(Map<Integer, OrderDetailDTO> items) {
        this.items = items;
    }

    public void addItemToCart(int bookID, OrderDetailDTO detail) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        if (this.items.containsKey(bookID)) {

        } else {
            this.items.put(bookID, detail);
        }

    }

    public void addMore(int bookID, OrderDetailDTO detail) {
        for (Map.Entry<Integer, OrderDetailDTO> item : items.entrySet()) {
            if (bookID == item.getKey()) {
                OrderDetailDTO dto = (OrderDetailDTO) item.getValue();

                detail.setQuantity(detail.getQuantity() + dto.getQuantity());

                float newTotal = dto.getTotal() + detail.getTotal();
                detail.setTotal(newTotal);

                this.items.put(bookID, detail);
                break;
            }

        }
    }

    public void removeItem(int bookID) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(bookID)) {
            this.items.remove(bookID);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public void updateItemInCart(int bookID, OrderDetailDTO detail) {
        for (Map.Entry<Integer, OrderDetailDTO> item : items.entrySet()) {
            if (bookID == item.getKey()) {
                this.items.put(bookID, detail);
                break;
            }
        }
    }

}
