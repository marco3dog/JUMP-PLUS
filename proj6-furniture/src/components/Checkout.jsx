import { dbApi } from "../dbApi";
import React, { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";

const Checkout = ({user, setUser}) =>{

    const navigate = useNavigate();

    const calculateTotal = () =>{
        if(user === undefined){
            navigate("/login");
            return;
        }
        let t = 0;
        for(let i = 0; i < user.cart.length; i++){
            t += user.cart[i].price;
        }
        return t;
    }

    const [productList, setProductList] = useState([]);
    const [total, setTotal] = useState(calculateTotal());



    const populateProducts = () =>{
        if(user === undefined){
            navigate("/login");
            return;
        }
        setProductList(user.cart);
        setTotal(calculateTotal());
    };

    useEffect( () => {
        populateProducts();
    }, [] );

    const removeFromCart = async (i) =>{
        let tempUser = user;
        if(tempUser.cart.length < 2){
            tempUser.cart = [];
        }
        else {tempUser.cart.splice(i,1);}
        setUser(tempUser);
        await dbApi.putUser(user);
        populateProducts();
    }

    const confirmOrder = async () =>{

        const cart = user.cart;
        if(cart.length === 0){
            return;
        }
        const userId = user.id;
        let orderObject = {
            id: 0,
            userId,
            total,
            cart
        };
        await dbApi.postOrder(orderObject);
        user.cart = [];
        setUser(user);
        await dbApi.putUser(user);
        navigate("/confirmation");
    }

    return (
        <div>
            <h3>Checkout</h3>
            <h5>Total: ${total}</h5>
            <button class="submit_button" onClick={() => confirmOrder()}>Confirm Order</button>
            <table class="tbl">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Remove From Cart</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        productList.map( (p, i) => 
                            <tr key={i}>
                                <td>{p.name}</td>
                                <td>{p.price}</td>
                                <td><button onClick={() => removeFromCart(i)}>Remove</button></td>
                            </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
    );
}

export default Checkout;