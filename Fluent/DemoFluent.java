import java.util.ArrayList;

class OrderItem {
  String item;
  int unitPrice;
  int amount;  

  public OrderItem(String item, int unitPrice, int amount) {
    this.item = item;
    this.unitPrice = unitPrice;
    this.amount = amount;
  }
}

class Order {
  String customerName;
  String customerMobile;
  String shippingAddress;
  ArrayList<OrderItem> orderItems;
  int totalAmount;

  public Order(String customerName, String customerMobile, String shippingAddress) {
    this.customerName = customerName;
    this.customerMobile = customerMobile;
    this.shippingAddress = shippingAddress;
    orderItems = new ArrayList<>();
    totalAmount = 0;
  }
public Order addItem(OrderItem item) {
  orderItems.add(item);
  System.out.println(orderItems.size() + " . Sub total = " + item.amount * item.unitPrice);
  return this;
}

  public Order passToKitchen() {
    System.out.println("Pass this order to kitchen");
    return this;
  }

  public Order calculateTotalMount() {
    /*for (OrderItem orderItem : orderItems) {
      totalAmount += orderItem.amount * orderItem.unitPrice;
    }*/

    totalAmount = orderItems
    .stream()
    .mapToInt(item -> item.amount * item.unitPrice)
    .sum();

    return this;
  }

  public Order printInvoice() {
    System.out.println("Total amount is " + totalAmount);
    return this;
  }

  public Order ship() {
    System.out.println("Ship this order to " + customerName 
    + " with mobile " + customerMobile 
    + " at " + shippingAddress);
    return this;
  }

  public void close() {
    System.out.println("Order complete");  
  }
}

public class DemoFluent {
  public static void main(String[] args) {
    new Order("Cuong", "0902209011", "Tầng 12A, Viwaseen Tower, 48 Tố Hữu")
      .addItem(new OrderItem("Pizza cá ngừ", 200000, 1))
      .addItem(new OrderItem("Pizza nấm", 180000, 2))
      .addItem(new OrderItem("Pizza bò Úc", 190000, 1))
      .passToKitchen()
      .calculateTotalMount()
      .printInvoice()
      .ship()
      .close();
  }
}