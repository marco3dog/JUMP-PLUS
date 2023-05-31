import { dbApi } from "../dbApi";
import { useNavigate} from "react-router-dom";

const Login = ({ setUser}) => {
    const navigate = useNavigate()
  
    const handleLogin = async (event) => {
      event.preventDefault();
      const username = document.getElementById("loginUsername").value;
      const password = document.getElementById("loginPassword").value;
      const user = await dbApi.login(username, password);
      if (user) {
        setUser(user);
        navigate("/shop");
      } else {
        alert("Wrong Credentials");
      }
    }
  
    return (
      <div class="formCard">
        <form onSubmit={handleLogin}>
          
          <h2>Login</h2>
          <label htmlFor="loginUsername">Username</label><br />
          <input type="text" id="loginUsername" name="uname" /><br />
          <label htmlFor="loginPassword">Password</label><br />
          <input type="password" id="loginPassword" name="pw"/><br /><br />
          <input class="submit_button" type="submit" value="Login"/>
      </form>
      </div>
    )
  }
  
  export default Login;