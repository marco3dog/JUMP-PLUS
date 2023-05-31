import { dbApi } from "../dbApi";
import { useNavigate} from "react-router-dom";

const Register = ({setUser}) => {
    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();
        const username = document.getElementById("registerUsername").value;
        const password = document.getElementById("registerPassword").value;
        if(username === "" || password === ""){
            return;
        }

        let userObject = {
            id: 0,
            username,
            password,
            cart: []
        }

        if(await dbApi.postUser(userObject)){
            const user = await dbApi.login(username, password);
            setUser(user);
            navigate("/shop");
        } else {
            alert("Username Already Exists");
        }
    }

    return (
        <div class="formCard">
          <form onSubmit={handleRegister}>
            
            <h2>Register</h2>
            <label htmlFor="registerUsername">Username</label><br />
            <input type="text" id="registerUsername" name="uname" /><br />
            <label htmlFor="registerPassword">Password</label><br />
            <input type="password" id="registerPassword" name="pw"/><br /><br />
            <input class="submit_button"  type="submit" value="Register"/>
        </form>
        </div>
      )

}

export default Register;