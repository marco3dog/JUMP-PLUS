import { dbApi } from "../dbApi";
import React, { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";

const Shop = ({user, setUser}) => {

    const [productList, setProductList] = useState([]);

    const navigate = useNavigate();

    useEffect( () => {
        const populateProducts = async () =>{
            setProductList(await dbApi.getAllItems());
        };
        populateProducts();
    }, [] );

    const addToCart = async (itemId) => {
        if(user === undefined){
            navigate("/login");
            return;
        }
        let prod = await dbApi.getItemById(itemId);
        let tempUser = user;
        console.log(tempUser);   
        tempUser.cart.push(prod);
        console.log(tempUser);
        setUser(tempUser);
        await dbApi.putUser(user);
    }

    return (
        <div>
            <h3>Shop</h3>
            <table class="tbl">
                <thead>
                    <tr>
                        <th> </th>
                        <th>Name</th>
                        <th> Price </th>
                        <th>Add To Cart</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        productList.map( p => 
                            <tr key={p.id}>
                                <td><img src={p.src}></img></td>
                                <td>{p.name}</td>
                                <td> ${p.price} </td>
                                <td><button class="submit_button" onClick={() => addToCart(p.id)}>+</button></td>
                            </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
    );
}

export default Shop;