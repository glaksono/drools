#/ debug: display result and usage

[when]There is a customer with no open orders = $c:Customer($orders: orders) forall (Order(paid == true) from $orders)
[when]- currently has no discount applied= $c.discount == null
[when]- with previous orders are more than {value} = eval($c.orders.size() > {value})
[then]Give discount {d} % with validity of {v} days = $c.setDiscount(new Discount({d}/100, Date.from(LocalDate.now().plusDays({v}).atStartOfDay(ZoneId.systemDefault()).toInstant())));