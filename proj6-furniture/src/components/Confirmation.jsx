import { dbApi } from "../dbApi";
import React, { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";

const Confimation = ({user}) => {
    const navigate = useNavigate();



    const [lastOrder, setLastOrder] = useState({cart: [], id: 0, password: "", username: ""});
    
    useEffect(() => {
        const getLastOrder = async () =>{
            const orders = await dbApi.getOrdersByCustomer(user.id);
            console.log(orders);
            setLastOrder(orders[orders.length-1]) ;
        }
        (async () => await getLastOrder())();
      }, []);

    console.log(lastOrder);

    return(
        <div>
        <h3>Order Confirmed</h3>
          <table class="tbl">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        lastOrder.cart.map( (p) => 
                            <tr>
                                <td>{p.name}</td>
                                <td>{p.price}</td>
                            </tr>
                            )
                    }
                </tbody>
                </table>
            <h4>Total: ${lastOrder.total}</h4>
            <button class="submit_button" onClick={() => navigate("/shop")}>Back To Shop</button>
        </div>
    )
}
export default Confimation;