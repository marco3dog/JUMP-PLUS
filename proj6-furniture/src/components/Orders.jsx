import { dbApi } from "../dbApi";
import React, { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";

const Orders = ({user}) => {

    const [orders, setOrders] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        const getAllOrders = async () =>{
            if(user === undefined){
                navigate("/login");
                return;
            }
            const ordersList = await dbApi.getOrdersByCustomer(user.id);
            setOrders(ordersList) ;
        }
        (async () => await getAllOrders())();
      }, []);

      return(
        <div class="page">
            <h3>Order History</h3>
            <table class="tbl">
                <thead>
                    <tr>
                        <th>Order Number</th>
                        <th>Items</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        orders.map( p => 
                            <tr key={p.id}>
                                <td>{p.id}</td>
                                <td>
                                    <ul>
                                        {
                                        p.cart.map( i =>
                                            <li>{i.name} ${i.price}</li>
                                        )
                                        }
                                    </ul>
                                </td>
                                <td>${p.total}</td>
                            </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
      )
}

export default Orders;