import { useNavigate} from "react-router-dom";

const Navbar = ({setUser}) =>{

    const navigate = useNavigate();

    const signOut = () =>{
        setUser(undefined);
        navigate("/login");
    }

    return(
        <nav class="navbar">
        <div class="navbar_container">
            <button id="navbar_logo">FURNITURE</button>
            <div class="navbar_toggle" id="mobile-menu">
                <span class="bar"></span>
            </div>
            <ul class="navbar_menu">
                <li class="navbar_item">
                    <button onClick={()=> navigate("/shop")} class="navbar_links">Shop</button>
                </li>
                <li class="navbar_item">
                    <button onClick={()=> navigate("/checkout")} class="navbar_links">Checkout</button>
                </li>
                <li class="navbar_item">
                    <button onClick={()=> navigate("/orders")} class="navbar_links">Order History</button>
                </li>
                <li class="navbar_item">
                    <button onClick={()=> navigate("/register")} class="navbar_links">Register</button>
                </li>
                <li class="navbar_item">
                    <button onClick={()=> signOut()} class="navbar_links">Sign Out</button>
                </li>
            </ul>
        </div>
    </nav>
    );
}

export default Navbar;